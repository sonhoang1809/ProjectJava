/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import sample.daos.ArticleDAO;
import sample.mails.SendMailSSL;

/**
 *
 * @author sonho
 */
public class ActiveArticleController extends HttpServlet {

    private final String SUCCESS = "SearchController";
    private static final Logger log = Logger.getLogger(ActiveArticleController.class.getName());

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
        String url = SUCCESS;
        try {
            String articleID = request.getParameter("articleID");
            String email = request.getParameter("emailUserArticle");
            boolean result = ArticleDAO.activeArticle(articleID);
            String statusArticleID = request.getParameter("statusArticleID");
            request.setAttribute("ARTICLESTATUSID", statusArticleID);
            if (result) {
                String content = "Admin just activated your article (ArticleID:" + articleID + ")\n"
                        + "Refesh (f5) this page to load your article!!";
                SendMailSSL.sendMessageToUser("Active success!!", content, email);
                request.setAttribute("MESSAGE", "Active  user's articleID: " + articleID + " successfully");
            }
        } catch (SQLException | NamingException ex) {
            log.error("Error at ActiveArticleController: " + ex.toString());
            SendMailSSL.sendToAdmin("Error at ActiveArticleController: " + ex.toString(), "Error!!");
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
