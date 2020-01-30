/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.daos.HouseDAO;
import sample.dtos.HouseDTO;

/**
 *
 * @author sonho
 */
public class DeleteHouseController extends HttpServlet {

    private final String SUCCESS = "GetListHouseController";

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
            String idHouse = request.getParameter("idHouse");
            String picHouse = request.getParameter("picHouse");
            String description = request.getParameter("description");
            String furniture = request.getParameter("lotSize") + "-" + request.getParameter("numBed")
                    + "-" + request.getParameter("numBath") + "-" + request.getParameter("numGarage");
            String typeID = request.getParameter("typeID");
            String priceText = request.getParameter("price");
            float price = Float.parseFloat(priceText);
            String idWay = request.getParameter("idWay");
            String statusCodeText = request.getParameter("statusCode");
            int statusCode = Integer.parseInt(statusCodeText);
            HouseDTO dto = new HouseDTO(idHouse, picHouse, description, furniture, typeID, price, idWay, statusCode);
            HouseDAO.deleteAHouse(idHouse);
            //HouseDAO.insertToTblHouseDelete(dto);
        } catch (NumberFormatException | SQLException ex) {
            log("Error at DeleteHouseController: " + ex.toString());
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
