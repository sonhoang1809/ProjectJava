/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
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
        try {
            String userID = request.getParameter("ID");
            String password = Support.encryptedPassword(request.getParameter("password"));
            UserDTO userDTO = UserDAO.checkLogin(userID, password);
            LoginError err = new LoginError();
            if (userDTO != null) {
                HttpSession session = request.getSession();
                session.setAttribute("USERDTO", userDTO);
                String lastPosition = (String) session.getAttribute("POSITION");
                if (lastPosition != null) {
                    url = lastPosition;
                } else {
                    url = SUCCESS;
                }
            } else {
                if (!UserDAO.checkExistUserID(userID)) {
                    err.setErrorExistUserID("This UserID is not exists!!");
                } else {
                    err.setErrorPassword("Error Password!!");
                }
                request.setAttribute("LOGINERROR", err);
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
