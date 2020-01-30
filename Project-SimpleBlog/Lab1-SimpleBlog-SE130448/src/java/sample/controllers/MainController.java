/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import sample.mails.SendMailSSL;

/**
 *
 * @author sonho
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class MainController extends HttpServlet {

    private static final Logger log = Logger.getLogger(MainController.class.getName());

    private final String DEFAULT = "SearchController";
    private final String SEARCH = "SearchController";
    private final String SIGNUPUSER = "SignupController";
    private final String SIGNUPBYGOOGLE = "SignupByGoogleController";
    private final String CONFIRMCODEMAIL = "VerifyMailController";
    private final String SENDCODETOEMAIL = "SendCodeConfirmEmailController";
    private final String LOGIN = "LoginController";
    private final String LOGINBYGOOGLE = "LoginByGoogleController";
    private final String VIEWDETAILUSER = "ViewDetailUserController";
    private final String VIEWFULLAARTICLE = "ViewArticleController";
    private final String GOTOCREATEARTICLE = "create-a-article.jsp";
    private final String POSTARTICLE = "PostArticleController";
    private final String ACTIVEARTICLE = "ActiveArticleController";
    private final String DELETEARTICLE = "DeleteArticleController";
    private final String COMMENTARTICLE = "PostCommentController";
    private final String LOGOUT = "LogoutController";
    private final String UPDATEUSER = "UpdateUserController";

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
        String url = DEFAULT;
        String action = request.getParameter("action");
        try {
            if (action != null) {
                if (action.equalsIgnoreCase("Sign up")) {
                    url = SIGNUPUSER;
                } else if (action.equalsIgnoreCase("Confirm")) {
                    url = CONFIRMCODEMAIL;
                } else if (action.equalsIgnoreCase("Send code again")) {
                    url = SENDCODETOEMAIL;
                } else if (action.equalsIgnoreCase("Login")) {
                    url = LOGIN;
                } else if (action.equalsIgnoreCase("LoginByGoogle")) {
                    url = LOGINBYGOOGLE;
                } else if (action.equalsIgnoreCase("SignupByGoogle")) {
                    url = SIGNUPBYGOOGLE;
                } else if (action.equalsIgnoreCase("ViewDetailUser")) {
                    url = VIEWDETAILUSER;
                } else if (action.equalsIgnoreCase("Search")) {
                    url = SEARCH;
                } else if (action.equalsIgnoreCase("Read more")) {
                    url = VIEWFULLAARTICLE;
                } else if (action.equalsIgnoreCase("Create a article")) {
                    url = GOTOCREATEARTICLE;
                } else if (action.equalsIgnoreCase("Post Article")) {
                    url = POSTARTICLE;
                } else if (action.equalsIgnoreCase("Active Article")) {
                    url = ACTIVEARTICLE;
                } else if (action.equalsIgnoreCase("Delete Article")) {
                    url = DELETEARTICLE;
                } else if (action.equalsIgnoreCase("Post Comment")) {
                    url = COMMENTARTICLE;
                } else if (action.equalsIgnoreCase("Logout")) {
                    url = LOGOUT;
                } else if (action.equalsIgnoreCase("Update Profile")) {
                    url = UPDATEUSER;
                }
            }
        } catch (Exception ex) {
            log.error("Error at MainController: " + ex.toString());
            SendMailSSL.sendToAdmin("Error at MainController: " + ex.toString(), "Error!!");
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
