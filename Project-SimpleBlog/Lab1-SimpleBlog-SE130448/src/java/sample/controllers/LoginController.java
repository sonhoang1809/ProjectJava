/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.daos.UserDAO;
import sample.dtos.UserDTO;
import sample.errors.LoginError;
import sample.mails.SendMailSSL;
import sample.supports.Support;

/**
 *
 * @author sonho
 */
public class LoginController extends HttpServlet {

    private final String SUCCESS = "SearchController";
    private final String ERROR = "SearchController";
    private final String ACCOUNTNOTCONFIRMEMAIL = "SendCodeConfirmEmailController";
    private static final Logger log = Logger.getLogger(LoginController.class.getName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            password = Support.encryptedPassword(password);
            LoginError loginError = new LoginError();
            if (UserDAO.checkExistEmail(email)) {
                UserDTO userDTO = UserDAO.checkLogin(email, password);
                if (userDTO != null) {
                    if (userDTO.getStatusID().equalsIgnoreCase("1")) {
                        session.setAttribute("USERDTO", userDTO);
                        String lastPosition = (String) session.getAttribute("POSITION");
                        if (lastPosition != null) {
                            url = lastPosition;
                        } else {
                            url = SUCCESS;
                        }
                    } else {
                        request.setAttribute("NOTCONFIRMYET", "Your email is not confirm yet, please confirm"
                                + " email to use this account!!");
                        url = ACCOUNTNOTCONFIRMEMAIL;
                    }
                } else {
                    String lastPosition = (String) session.getAttribute("POSITION");
                    if (lastPosition != null) {
                        url = lastPosition;
                    } else {
                        url = ERROR;
                    }
                    loginError.setErrorPassword("Email or Password is not correct!!");
                    request.setAttribute("LOGINERROR", loginError);
                }
            } else {
                String lastPosition = (String) session.getAttribute("POSITION");
                if (lastPosition != null) {
                    url = lastPosition;
                } else {
                    url = ERROR;
                }
                loginError.setErrorExistEmail("This email is not exist!!");
                request.setAttribute("LOGINERROR", loginError);
            }

        } catch (NoSuchAlgorithmException | SQLException | NamingException ex) {
            log.error("Error at LoginController: " + ex.toString());
            SendMailSSL.sendToAdmin("Error at LoginController: " + ex.toString(), "Error!!");
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
