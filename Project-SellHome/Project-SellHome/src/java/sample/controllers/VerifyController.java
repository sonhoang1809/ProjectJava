/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.dtos.UserDTO;

/**
 *
 * @author sonho
 */
public class VerifyController extends HttpServlet {

    private final String SUCCESS = "SignupController";
    private final String ERROR = "verify-email.jsp";

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
            String verify = request.getParameter("verify");
            String idCity = request.getParameter("idCity");
            String idWay = request.getParameter("idWay");
            String typeID = request.getParameter("typeID");
            String priceLower = request.getParameter("priceLower");
            String priceHigher = request.getParameter("priceHigher");
            String code = request.getParameter("code");
            if (code.equals(verify)) {
                url = SUCCESS;
            } else {
                request.setAttribute("CODE", code);
                request.setAttribute("IDCITY", idCity);
                request.setAttribute("IDWAY", idWay);
                request.setAttribute("TYPEID", typeID);
                request.setAttribute("PRICELOWER", priceLower);
                request.setAttribute("PRICEHIGHER", priceHigher);
                request.setAttribute("ERRORCODE", "Your code input is not valid");
            }
            String userID = request.getParameter("userID");
            String userName = request.getParameter("userName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String roleID = "user";
            UserDTO dto = new UserDTO(userID, userName, email, password, roleID);
            request.setAttribute("USERDTOSIGNUP", dto);
            request.setAttribute("CONFIRM", confirm);
        } catch (Exception ex) {
            log("Error at VerifyController: " + ex.toString());
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
