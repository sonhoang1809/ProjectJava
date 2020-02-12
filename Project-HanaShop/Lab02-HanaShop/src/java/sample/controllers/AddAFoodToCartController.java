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
import org.apache.log4j.Logger;
import sample.daos.BillDAO;
import sample.daos.FoodDAO;
import sample.daos.FoodInBillDAO;
import sample.dtos.BillDTO;
import sample.dtos.FoodDTO;
import sample.dtos.FoodsInBillDTO;
import sample.dtos.UserDTO;
import sample.errors.LoginError;
import sample.mails.SendMailSSL;

/**
 *
 * @author sonho
 */
public class AddAFoodToCartController extends HttpServlet {

    private final String SUCCESS = "SearchController";
    private final String ERROR = "SearchController";
    private static final Logger log = Logger.getLogger(MainController.class.getName());

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
            UserDTO userDTO = (UserDTO) session.getAttribute("USERDTO");
            String idFood = request.getParameter("idFood");
            if (userDTO != null) {
                String userID = userDTO.getUserID();
                FoodDTO foodDTO = FoodDAO.getFoodDTO(idFood);
                String strQuantity = request.getParameter("quantity");
                if (strQuantity == null || strQuantity.equals("")) {
                    strQuantity = "1";
                }
                int quantity = Integer.parseInt(strQuantity);
                String idBillLastBillNotPayYet = BillDAO.getLastBillIsNotPaid(userID);
                if (idBillLastBillNotPayYet == null) {
                    int numBill = BillDAO.getNumBill(userID);
                    BillDTO billDTO = BillDAO.createNewBillForUser(userID, numBill);
                    String idBill = billDTO.getIdBill();
                    int statusFoodID = 1;
                    if (foodDTO.getStatusCode() == 0 || foodDTO.getStatusCode() == 2 || foodDTO.getQuantity() < quantity) {
                        statusFoodID = 0;
                    }
                    float total = foodDTO.getPrice() * quantity;
                    FoodsInBillDTO foodInBillDTO = new FoodsInBillDTO(idBill, idFood, quantity, statusFoodID, foodDTO.getPrice(), total);
                    boolean resultInsertProduct = FoodInBillDAO.insertProductToBill(foodInBillDTO);
                    float totalBill = billDTO.getTotal() + foodInBillDTO.getTotal();
                    billDTO.setTotal(totalBill);
                    boolean resultUpdateBill = BillDAO.updateBillTotal(idBill, totalBill);

                    List<FoodsInBillDTO> listFoodInBill = new ArrayList<>();
                    foodInBillDTO.setImgFood(foodDTO.getImgFood());
                    foodInBillDTO.setNameFood(foodDTO.getNameFood());
                    foodInBillDTO.setShortDescription(foodDTO.getShortDescription());
                    listFoodInBill.add(foodInBillDTO);
                    session.setAttribute("LISTFOODINBILL", listFoodInBill);
                    session.setAttribute("BILLDTO", billDTO);
                    url = SUCCESS;
                }
            } else {
                LoginError err = new LoginError();
                err.setErrorNotLoginYet("Please Login First!!");
                request.setAttribute("LOGINERROR", err);
            }
        } catch (Exception ex) {
            log.error("Error at AddAFoodToCartController: " + ex.toString());
            SendMailSSL.sendToAdmin("Error at AddAFoodToCartController: " + ex.toString(), "Error!!");
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
