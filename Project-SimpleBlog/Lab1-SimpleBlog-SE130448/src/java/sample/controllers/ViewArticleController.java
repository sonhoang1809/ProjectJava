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
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.daos.ArticleDAO;
import sample.daos.CommentDAO;
import sample.daos.StatusArticleDAO;
import sample.daos.UserDAO;
import sample.dtos.ArticleDTO;
import sample.dtos.CommentDTO;
import sample.dtos.StatusArticleDTO;
import sample.dtos.UserDTO;
import sample.mails.SendMailSSL;

/**
 *
 * @author sonho
 */
public class ViewArticleController extends HttpServlet {

    private final String SUCCESS = "view-single-article.jsp";
    private final String ERROR = "index.jsp";

    private static final Logger log = Logger.getLogger(ViewArticleController.class.getName());

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
            String email = request.getParameter("emailUserArticle");
            String articleID = request.getParameter("articleID");
            ArticleDTO artDTO = ArticleDAO.getArticleByArticleID(articleID);
            HttpSession session = request.getSession();
            UserDTO dto = (UserDTO) session.getAttribute("USERDTO");
            //neu bai viet do da bi admin xoa
            if (artDTO.getStatusArticle() == 2) {
                if (dto != null) {
                    //neu la admin thi hien bai viet da delete ra luon
                    if (dto.getRoleID().equalsIgnoreCase("AD")) {
                        artDTO.setAvatarUserPost(UserDAO.getAvatarUser(email));
                        List<CommentDTO> listComments = CommentDAO.getListCommentByArticleID(articleID);
                        if (listComments != null) {
                            for (CommentDTO x : listComments) {
                                x.setAvatarUserComment(UserDAO.getAvatarUser(x.getEmail()));
                            }
                        }
                        request.setAttribute("ARTICLEDTO", artDTO);
                        request.setAttribute("LISTCOMMENT", listComments);
                    }
                    //neu la member thi thong bao bai viet da xoa
                    if (dto.getRoleID().equalsIgnoreCase("ME")) {
                        request.setAttribute("MESSAGE", "This article is deleted!!");
                    }
                } else {
                    request.setAttribute("MESSAGE", "This article is deleted!!");
                }
            } else {
                artDTO.setAvatarUserPost(UserDAO.getAvatarUser(email));
                List<CommentDTO> listComments = CommentDAO.getListCommentByArticleID(articleID);
                if (listComments != null) {
                    for (CommentDTO x : listComments) {
                        x.setAvatarUserComment(UserDAO.getAvatarUser(x.getEmail()));
                    }
                }
                request.setAttribute("ARTICLEDTO", artDTO);
                request.setAttribute("LISTCOMMENT", listComments);
            }
            if (dto != null) {
                if (dto.getRoleID().equalsIgnoreCase("AD")) {
                    List<StatusArticleDTO> listStatusArticle = StatusArticleDAO.getAllListStatusArticle();
                    String statusArticleID = request.getParameter("statusArticleID");
                    request.setAttribute("LISTSTATUSARTICLE", listStatusArticle);
                    request.setAttribute("ARTICLESTATUSID", statusArticleID);
                }
            }
            url = SUCCESS;
        } catch (SQLException | NamingException ex) {
            log.error("Error at ViewArticleController: " + ex.toString());
            SendMailSSL.sendToAdmin("Error at ViewArticleController: " + ex.toString(), "Error!!");
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
