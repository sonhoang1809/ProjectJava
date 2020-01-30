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
public class SignupByGoogleController extends HttpServlet {

    private final String SUCCESS = "LoginByGoogleController";
    private final String ERROR = "SearchController";
    private static final Logger log = Logger.getLogger(SignupByGoogleController.class.getName());

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
            String userName = request.getParameter("name");
            String ID = request.getParameter("ID");
            String password = Support.encryptedPassword(ID);
            SignupError suError = new SignupError();
            boolean valid = true;
            if (UserDAO.checkExistEmail(email)) {
                suError.setErrorEmail("This email is already exist!!");
                //request.setAttribute("ERROREMAIL", "This email is already exist!!");
                valid = false;
            }
            if (valid) {
                String avatar = request.getParameter("avatar");
                String roleID = "ME";
                String statusID = "1";
                UserDTO dto = new UserDTO(email, avatar, userName, password, roleID, statusID);
                UserDAO.insertAUser(dto);
                request.setAttribute("USERDTO", dto);
                url = SUCCESS;
            } else {
                request.setAttribute("SIGNUPERROR", suError);
            }
        } catch (NoSuchAlgorithmException | SQLException | NamingException ex) {
            log.error("Error at SignupByGoogleController: " + ex.toString());
            SendMailSSL.sendToAdmin("Error at SignupByGoogleController: " + ex.toString(), "Error!!");
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
