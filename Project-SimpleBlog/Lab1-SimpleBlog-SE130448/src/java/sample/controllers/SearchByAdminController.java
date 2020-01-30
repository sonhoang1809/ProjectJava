/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import sample.daos.ArticleDAO;
import sample.daos.StatusArticleDAO;
import sample.daos.UserDAO;
import sample.dtos.ArticleDTO;
import sample.dtos.StatusArticleDTO;
import sample.mails.SendMailSSL;
import sample.supports.Support;

/**
 *
 * @author sonho
 */
public class SearchByAdminController extends HttpServlet {

    private final String SUCCESS = "index.jsp";
    private static final Logger log = Logger.getLogger(SearchByAdminController.class.getName());

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
            //    HttpSession session = request.getSession();
            String searchTitle = request.getParameter("searchTitle");
            String statusArticleID = request.getParameter("statusArticleID");
            List<StatusArticleDTO> listStatusArticle = StatusArticleDAO.getAllListStatusArticle();
            request.setAttribute("LISTSTATUSARTICLE", listStatusArticle);
            int numArticle;
            int numberOfPage;
            if (searchTitle == null || searchTitle.equals("") && statusArticleID == null || statusArticleID.equals("")) {
                numArticle = ArticleDAO.getNumberArticle();
                numberOfPage = Support.calculateNumPage(numArticle);
                request.setAttribute("NUMBEROFPAGE", numberOfPage);
                String numPage = request.getParameter("numPage");
                if (numPage == null) {
                    numPage = "1";
                }
                int page = Integer.parseInt(numPage);
                List<ArticleDTO> listArt = ArticleDAO.getListArticleByPage(page);
                for (ArticleDTO x : listArt) {
                    String avatar = UserDAO.getAvatarUser(x.getEmail());
                    x.setAvatarUserPost(avatar);
                }
                request.setAttribute("LISTARTICLE", listArt);

            } else if (searchTitle != null && statusArticleID == null || statusArticleID.equals("")) {
                numArticle = ArticleDAO.getNumberArticle(searchTitle);
                if (numArticle > 0) {
                    numberOfPage = Support.calculateNumPage(numArticle);
                    request.setAttribute("NUMBEROFPAGE", numberOfPage);
                    String numPage = request.getParameter("numPage");
                    if (numPage == null) {
                        numPage = "1";
                    }
                    int page = Integer.parseInt(numPage);
                    List<ArticleDTO> listArt = ArticleDAO.getListArticleByPage(page, searchTitle);
                    for (ArticleDTO x : listArt) {
                        String avatar = UserDAO.getAvatarUser(x.getEmail());
                        x.setAvatarUserPost(avatar);
                    }
                    request.setAttribute("LISTARTICLE", listArt);
                } else {
                    request.setAttribute("SEARCHNULL", "Result not Found!!");
                }
            } else if (searchTitle == null || searchTitle.equals("") && statusArticleID != null) {
                int stArticleID = Integer.parseInt(statusArticleID);
                numArticle = ArticleDAO.getNumberArticle(stArticleID);
                if (numArticle > 0) {
                    numberOfPage = Support.calculateNumPage(numArticle);
                    request.setAttribute("NUMBEROFPAGE", numberOfPage);
                    String numPage = request.getParameter("numPage");
                    if (numPage == null) {
                        numPage = "1";
                    }
                    int page = Integer.parseInt(numPage);
                    List<ArticleDTO> listArt = ArticleDAO.getListArticleByPage(page, stArticleID);
                    for (ArticleDTO x : listArt) {
                        String avatar = UserDAO.getAvatarUser(x.getEmail());
                        x.setAvatarUserPost(avatar);
                    }
                    request.setAttribute("ARTICLESTATUSID", statusArticleID);
                    request.setAttribute("LISTARTICLE", listArt);
                } else {
                    request.setAttribute("ARTICLESTATUSID", statusArticleID);
                    request.setAttribute("SEARCHNULL", "Result not Found!!");
                }
            } else if (searchTitle != null && statusArticleID != null) {
                int stArticleID = Integer.parseInt(statusArticleID);
                numArticle = ArticleDAO.getNumberArticle(stArticleID, searchTitle);
                if (numArticle > 0) {
                    numberOfPage = Support.calculateNumPage(numArticle);
                    request.setAttribute("NUMBEROFPAGE", numberOfPage);
                    String numPage = request.getParameter("numPage");
                    if (numPage == null) {
                        numPage = "1";
                    }
                    int page = Integer.parseInt(numPage);
                    List<ArticleDTO> listArt = ArticleDAO.getListArticleByPage(page, searchTitle, stArticleID);
                    for (ArticleDTO x : listArt) {
                        String avatar = UserDAO.getAvatarUser(x.getEmail());
                        x.setAvatarUserPost(avatar);
                    }
                    request.setAttribute("ARTICLESTATUSID", statusArticleID);
                    request.setAttribute("LISTARTICLE", listArt);
                } else {
                    request.setAttribute("ARTICLESTATUSID", statusArticleID);
                    request.setAttribute("SEARCHNULL", "Result not Found!!");
                }
            }
        } catch (NumberFormatException | SQLException | NamingException ex) {
            log.error("Error at SearchByAdminController: " + ex.toString());
            SendMailSSL.sendToAdmin("Error at SearchByAdminController: " + ex.toString(), "Error!!");
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
