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
import org.apache.log4j.Logger;
import sample.daos.UserDAO;
import sample.dtos.UserDTO;
import sample.errors.SignupError;
import sample.mails.SendMailSSL;
import sample.supports.Support;

/**
 *
 * @author sonho
 */
public class SignupController extends HttpServlet {

    private final String SUCCESS = "SendCodeConfirmEmailController";
    private final String ERROR = "SearchController";
    private static final Logger log = Logger.getLogger(SignupController.class.getName());

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
            String email = request.getParameter("email");
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            SignupError suError = new SignupError();
            boolean valid = true;
            if (UserDAO.checkExistEmail(email)) {
                suError.setErrorEmail("This email is already exist!!");
                //request.setAttribute("ERROREMAIL", "This email is already exist!!");
                valid = false;
            }
            if (userName.trim().length() == 0) {
                suError.setErrorName("Name must be required!!");
//                request.setAttribute("ERRORNAMENULL", "Name must be required!!");
                valid = false;
            }
            if (password.trim().length() == 0) {
                suError.setErrorPassword("Password must be required!!");
                //               request.setAttribute("ERRORPASSWORDNULL", "Password must be required!!");
                valid = false;
            }
            if (confirm.trim().length() == 0) {
                suError.setErrorPassword("Confirm must be required!!");
//                request.setAttribute("ERRORCONFIRMNULL", "Confirm must be required!!");
                valid = false;
            }
            if (!password.equalsIgnoreCase(confirm)) {
                suError.setErrorPassword("Password and confirm is not the same!!");
//                request.setAttribute("ERRORCONFIRMPASSWORD", "Password and confirm is not the same!!");
                valid = false;
            }
            if (valid) {
                String avatar = "img/avatar/avatar-default.jpg";
                String roleID = "ME";
                String statusID = "0";
                password = Support.encryptedPassword(password);
                UserDTO dto = new UserDTO(email, avatar, userName, password, roleID, statusID);
                UserDAO.insertAUser(dto);
                request.setAttribute("USERDTO", dto);
                url = SUCCESS;
            } else {
                request.setAttribute("SIGNUPERROR", suError);
            }
        } catch (NoSuchAlgorithmException | SQLException | NamingException ex) {
            log.error("Error at SignupController: " + ex.toString());
            SendMailSSL.sendToAdmin("Error at SignupController: " + ex.toString(), "Error!!");
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
