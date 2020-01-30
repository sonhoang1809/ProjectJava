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
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.daos.CommentDAO;
import sample.dtos.CommentDTO;
import sample.dtos.UserDTO;
import sample.mails.SendMailSSL;

/**
 *
 * @author sonho
 */
public class PostCommentController extends HttpServlet {

    private final String ERROR = "index.jsp";
    private static final Logger log = Logger.getLogger(PostCommentController.class.getName());

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
            HttpSession session = request.getSession();
            UserDTO dto = (UserDTO) session.getAttribute("USERDTO");
            String email = dto.getEmail();
            String author = dto.getName();
            String content = request.getParameter("txtCommentUser").trim();
            String articleID = request.getParameter("articleID");
            int nextComment = CommentDAO.getNextNumberCommentArticleOfUser(articleID, email);
            String commentID = articleID + "-" + email + "-" + nextComment;
            java.util.Date dt = new java.util.Date();

            java.text.SimpleDateFormat sdf
                    = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String dateComment = sdf.format(dt);

            CommentDTO cmDTO = new CommentDTO(commentID, content, email, dateComment, articleID, author);
            boolean result = CommentDAO.createAComment(cmDTO);
            if (result) {
                url = (String) session.getAttribute("POSITION");
            }
        } catch (SQLException | NamingException ex) {
            log.error("Error at PostCommentController: " + ex.toString());
            SendMailSSL.sendToAdmin("Error at PostCommentController: " + ex.toString(), "Error!!");
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
