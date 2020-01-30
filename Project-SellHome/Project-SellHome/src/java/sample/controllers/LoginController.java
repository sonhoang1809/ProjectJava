/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.daos.BillDAO;
import sample.daos.HouseDAO;
import sample.daos.ProductsInBillDAO;
import sample.daos.UserDAO;
import sample.dtos.BillDTO;
import sample.dtos.HouseDTO;
import sample.dtos.ProductsInBillDTO;
import sample.dtos.UserDTO;

/**
 *
 * @author sonho
 */
public class LoginController extends HttpServlet {

    private final String SUCCESS = "SearchController";
    private final String ERROR = "login.jsp";

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
        HttpSession session = request.getSession();
        try {
 //           String idHouse = request.getParameter("idHouse");
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            UserDTO userDTO = UserDAO.checkLogin(userID, password);
            String lastBillIdUser = BillDAO.getLastBillIsNotPaid(userID);
            if (lastBillIdUser != null) {
                List<ProductsInBillDTO> listProducts = ProductsInBillDAO.getListProductInBill(lastBillIdUser);
                if (listProducts != null) {
                    for (ProductsInBillDTO x : listProducts) {
                        HouseDTO dtohouse = HouseDAO.getHouseDTOByHouseID(x.getIdHouse());
                        x.setPicHouse(dtohouse.getPicHouse());
                        x.setDescription(dtohouse.getDescription());
                    }
                    int numProductInBill = ProductsInBillDAO.getNumProductInBill(lastBillIdUser);
                    BillDTO billDTO = BillDAO.getBillDetailByBillID(lastBillIdUser);
                    session.setAttribute("BILLDTO", billDTO);
                    session.setAttribute("NUMPRODUCT", numProductInBill + "");
                    session.setAttribute("LISTPRODUCTINBILL", listProducts);
                }
            }
            session.setAttribute("USERDTO", userDTO);
            String lastPosition = (String) session.getAttribute("POSITION");
            if (lastPosition != null) {
                url = lastPosition;
            } else {
                url = SUCCESS;
            }
        } catch (SQLException ex) {
            log("Error at LoginController: " + ex.toString());
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
