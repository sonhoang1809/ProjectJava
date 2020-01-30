/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import sample.daos.UserDAO;
import sample.dtos.UserDTO;
import sample.mails.SendMailSSL;

/**
 *
 * @author sonho
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UpdateUserController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final String SUCCESS = "ViewDetailUserController";
    private final String SAVE_DIRECTORY = "img/avatar";
    private static final Logger log = Logger.getLogger(UpdateUserController.class.getName());

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
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            HttpSession session = request.getSession();
            UserDTO dto = (UserDTO) session.getAttribute("USERDTO");
            String email = dto.getEmail();

            String avatar = null;

            // xu ly file anh
            // parses the request's content to extract file data
            String appPath = request.getServletContext().getRealPath("");
            appPath = appPath.replace('\\', '/');
            int indexOfBuild = appPath.indexOf("build");
            appPath = appPath.substring(0, indexOfBuild) + appPath.substring(indexOfBuild + 6);
            String fullSavePath = null;
            if (appPath.endsWith("/")) {
                fullSavePath = appPath + SAVE_DIRECTORY + "/" + email;
            } else {
                fullSavePath = appPath + "/" + SAVE_DIRECTORY + "/" + email;
            }
            // Tạo thư mục nếu nó không tồn tại.
            File fileSaveDir = new File(fullSavePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }
            // Luu file upload lên (Có thể là nhiều file).
            Part part = request.getPart("avatar");
            String fileName = extractFileName(part);
            if (fileName != null && fileName.length() > 0) {
                String filePath = fullSavePath + File.separator + fileName;
                //    System.out.println("Edit user - Write file to: " + filePath);
                avatar = SAVE_DIRECTORY + "/" + email + "/" + fileName;
                part.write(filePath);
            }
//            java.util.Date dt = new java.util.Date();
//
//            java.text.SimpleDateFormat sdf
//                    = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//            String currentTime = sdf.format(dt);

//            ArticleDTO dtoArticle = new ArticleDTO(
//                    articleID, title, shortDescription, img, postContent, author, avatarUserPost, currentTime, email);
//            boolean result = ArticleDAO.insertArticle(dtoArticle);
//            String notification = "Username: " + author + "\n(Email : " + email + ") is waiting for you to active her(his) Article";
//            SendMailSSL.sendToAdmin(notification);
            boolean result = UserDAO.updateAvatar(avatar, email);
            if (result) {
                request.setAttribute("MESSAGE", "UPDATE AVATAR SUCCESS!!");
            }
        } catch (IOException | SQLException | NamingException | ServletException ex) {
            request.setAttribute("MESSAGE", "ERROR: " + ex.toString());
            log.error("Error at UpdateUserController: " + ex.toString());
            SendMailSSL.sendToAdmin("Error at UpdateUserController: " + ex.toString(), "Error!!");
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
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
