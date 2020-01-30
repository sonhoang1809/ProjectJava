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
import sample.daos.ProductsInBillDAO;
import sample.dtos.BillDTO;
import sample.dtos.HouseDTO;
import sample.dtos.ProductsInBillDTO;
import sample.dtos.UserDTO;

/**
 *
 * @author sonho
 */
public class AddToCartController extends HttpServlet {

    private final String SUCCESS = "SearchController";
    private final String NOTLOGIN = "MainController?action=Go to login";
    private final String ERROR = "single-property.jsp";

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
            String idHouse = request.getParameter("idHouse");
            String idCity = request.getParameter("idCity");
            String idWay = request.getParameter("idWay");
            String typeID = request.getParameter("typeID");
            request.setAttribute("IDCITY", idCity);
            request.setAttribute("IDWAY", idWay);
            request.setAttribute("TYPEID", typeID);
            String s = "&idCity=" + idCity + "&idWay=" + idWay + "&typeID=" + typeID;
            if (userDTO != null) {
                String userID = userDTO.getUserID();
//                String idHouse = request.getParameter("idHouse");
//                String idCity = request.getParameter("idCity");
//                String idWay = request.getParameter("idWay");
//                String typeID = request.getParameter("typeID");
//                String priceText = request.getParameter("price");
//                float price = Float.parseFloat(priceText);
                HouseDTO houseDTO = HouseDAO.getHouseDTOByHouseID(idHouse);
                if (houseDTO.getStatusCode() == 0) {
                    request.setAttribute("ERRORMESSAGE", "THE PRODUCT's ID: +" + idHouse + " IS ALREADY SOLD");
                    url = SUCCESS;
                } else {
                    if (userDTO.getRoleID().equalsIgnoreCase("user")) {
                        //neu user dang co bill
                        if (BillDAO.checkUserHasBill(userID)) {
                            BillDTO billDTO = null;
                            String billID = BillDAO.getLastBillIsNotPaid(userID);
                            if (billID == null) {
                                int numBill = BillDAO.getBillNumLast(userID) + 1;
                                billDTO = BillDAO.createNewBillForUser(userID, numBill);
                                ProductsInBillDAO.insertProductToBill(houseDTO, billDTO.getIdBill());
                                billDTO.setTotal(houseDTO.getPrice());
                                BillDAO.updateBillDetail(billDTO);
                                int numProductInBill = 1;
                                ProductsInBillDTO pInBill = new ProductsInBillDTO(billDTO.getIdBill(), houseDTO.getIdHouse(), houseDTO.getPrice());
                                pInBill.setPicHouse(houseDTO.getPicHouse());
                                pInBill.setDescription(houseDTO.getDescription());
                                List<ProductsInBillDTO> listProducts = new ArrayList<>();
                                listProducts.add(pInBill);
                                session.setAttribute("LISTPRODUCTINBILL", listProducts);
                                session.setAttribute("NUMPRODUCT", numProductInBill + "");
                                session.setAttribute("BILLDTO", billDTO);
                                url = SUCCESS;
                            } else {
                                //kiem tra xem product co trong bill chua
                                //neu chua 
                                if (!ProductsInBillDAO.checkContainProduct(billID, idHouse)) {
                                    ProductsInBillDAO.insertProductToBill(houseDTO, billID);
                                    billDTO = BillDAO.getBillDetailByBillID(billID);
                                    float lastTotal = ProductsInBillDAO.getTotalInBill(billID);
                                    billDTO.setTotal(lastTotal);
                                    BillDAO.updateBillDetail(billDTO);
                                    List<ProductsInBillDTO> listProducts = ProductsInBillDAO.getListProductInBill(billID);
                                    for (ProductsInBillDTO x : listProducts) {
                                        HouseDTO dtoTmp = HouseDAO.getPicHouseAndDescriptionById(x.getIdHouse());
                                        x.setPicHouse(dtoTmp.getPicHouse());
                                        x.setDescription(dtoTmp.getDescription());
                                    }
                                    int numProductInBill = listProducts.size();
                                    session.setAttribute("LISTPRODUCTINBILL", listProducts);
                                    session.setAttribute("NUMPRODUCT", numProductInBill + "");
                                    session.setAttribute("BILLDTO", billDTO);
                                    url = SUCCESS;
                                } //neu da co
                                else {
                                    request.setAttribute("ERRORMESSAGE", "THE PRODUCT's ID: +" + idHouse + " IS ALREADY IN YOUR CART");
                                    url = SUCCESS;
                                }
                            }
                        } //k co thi tao moi bill
                        else {
                            int numBill = 1;
                            BillDTO billDTO = BillDAO.createNewBillForUser(userID, numBill);
                            ProductsInBillDAO.insertProductToBill(houseDTO, billDTO.getIdBill());
                            billDTO.setTotal(houseDTO.getPrice());
                            BillDAO.updateBillDetail(billDTO);
                            ProductsInBillDTO pInBill = new ProductsInBillDTO(billDTO.getIdBill(), houseDTO.getIdHouse(), houseDTO.getPrice());
                            pInBill.setPicHouse(houseDTO.getPicHouse());
                            pInBill.setDescription(houseDTO.getDescription());
                            List<ProductsInBillDTO> listProducts = new ArrayList<>();
                            listProducts.add(pInBill);
                            int numProductInBill = 1;
                            session.setAttribute("LISTPRODUCTINBILL", listProducts);
                            session.setAttribute("NUMPRODUCT", numProductInBill + "");
                            session.setAttribute("BILLDTO", billDTO);
                            url = SUCCESS;
                        }
                    }
                }
            } else {
                url = NOTLOGIN + s;
                session.setAttribute("POSITION", "AddToCartController");
            }
        } catch (NumberFormatException | SQLException ex) {
            log("Error at AddToCartController: " + ex.toString());
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
