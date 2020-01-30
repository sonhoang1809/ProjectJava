/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.daos.BillDAO;
import sample.daos.HouseDAO;
import sample.dtos.BillDTO;
import sample.dtos.HouseDTO;
import sample.dtos.ProductsInBillDTO;
import sample.dtos.UserDTO;
import sample.mails.SendMailSSL;

/**
 *
 * @author sonho
 */
public class PayController extends HttpServlet {

    private final String SUCCESS = "SearchController";
    private final String ERROR = "error.html";

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
            BillDTO billDTO = (BillDTO) session.getAttribute("BILLDTO");
            UserDTO userDTO = (UserDTO) session.getAttribute("USERDTO");
            if (billDTO == null) {
                request.setAttribute("ERRORMESSAGE", "YOU HAVE NO PRODUCT IN YOUR BILL");
                url = SUCCESS;
            } else {
                List<ProductsInBillDTO> listProduct = (List<ProductsInBillDTO>) session.getAttribute("LISTPRODUCTINBILL");
                if (listProduct != null) {
                    if (listProduct.isEmpty()) {
                        request.setAttribute("ERRORMESSAGE", "YOU HAVE NO PRODUCT IN YOUR BILL");
                        url = SUCCESS;
                    } else {
                        //here
                        boolean checkValidHouse = true;
                        ArrayList<String> ListIdHouseIsNotValid = new ArrayList<>();
                        for (ProductsInBillDTO x : listProduct) {
                            if (HouseDAO.checkHouseIsSold(x.getIdHouse())) {
                                ListIdHouseIsNotValid.add(x.getIdHouse());
                                checkValidHouse = false;
                            }
                        }
                        if (checkValidHouse) {
                            String text = "User's ID:" + userDTO.getUserID() + "\n"
                                    + "User's name: " + userDTO.getUserName() + "\n"
                                    + "Just paid The Bill's ID: " + billDTO.getIdBill() + "\n"
                                    + "This bill includes: \n"
                                    + "STT|House ID  - Price \n";
                            //1 - da thanh toan bill
                            billDTO.setIdStatusBill(1);
                            BillDAO.updateBillDetail(billDTO);
                            int count = 0;
                            for (ProductsInBillDTO x : listProduct) {
                                HouseDTO houseDTO = HouseDAO.getHouseDTOByHouseID(x.getIdHouse());
                                //0- house da dc ban
                                houseDTO.setStatusCode(0);
                                //update house statusCode thanh sold
                                HouseDAO.updateHouseStatus(houseDTO);
                                String t = ++count + "  |" + houseDTO.toString() + "\n";
                                text = text + t;
                            }
                            session.setAttribute("BILLDTO", null);
                            session.setAttribute("LISTPRODUCTINBILL", null);
                            session.setAttribute("NUMPRODUCT", 0 + "");
                            request.setAttribute("MESSAGEPAYSUCCESS", "You have successfully paid!!, you bill's total: " + billDTO.getTotal());
                            text = text + "\nTotal Bill: $" + billDTO.getTotal();
                            SendMailSSL.send(text, "User Payment");
                            url = SUCCESS;
                        } else {
                            url = SUCCESS;
                            request.setAttribute("ERRORMESSAGE", "YOU HAVE PRODUCT IN YOUR BILL IS SOLD!!");
                            request.setAttribute("LISTPRODUCTISSOLD", ListIdHouseIsNotValid);
                        }
                    }
                } else {
                    request.setAttribute("ERRORMESSAGE", "YOU HAVE NO PRODUCT IN YOUR BILL");
                    url = SUCCESS;
                }
            }
        } catch (SQLException ex) {
            log("Error at PayController: " + ex.toString());
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
