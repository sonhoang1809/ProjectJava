/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sonho
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class MainController extends HttpServlet {

    private final String SEARCHUSER = "SearchUserController";
    private final String UPDATEHOUSE = "UpdateHouseController";
    private final String DELETEHOUSE = "DeleteHouseController";
    private final String GETALLHOUSE = "GetListHouseController";
    private final String VIEWDETAILBILL = "ViewDetailBillController";
    private final String VIEWBILLUSER = "ViewBillUserController";
    private final String UPDATEUSER = "UpdateUserController";
    private final String GOTOSIGNUP = "signup.jsp";
    private final String GOTOLOGIN = "login.jsp";
    private final String GOTOVERIFY = "verify-email.jsp";
    private final String SENDCODETOVERIFY="SendCodeToVerifyController";
    private final String VERIFYEMAIL = "VerifyController";
    private final String LOGIN = "LoginController";
    private final String SIGNUP = "SignupController";
    private final String SEARCH = "SearchController";
    private final String LOGOUT = "LogoutController";
    private final String ERROR = "index.jsp";
    private final String INSERTHOUSE = "InsertHouseController";
    private final String GOTOINSERTHOUSE = "insert-house.jsp";
    private final String MANAGERUSER = "GetListUserController";
    private final String MANAGEHOUSE = "GetListHouseController";
    private final String MANAGEBILL = "GetListBillController";
    private final String VIEWDETAILUSER = "user-detail.jsp";
//    private final String CREATE = "create.jsp";
    private final String UPDATEUSERBYADMIN = "UpdateUserByAdminController";
    private final String DELETEUSERBYADMIN = "DeleteUserByAdminController";
    private final String GOTOCREATE = "create-user.jsp";
    private final String CREATEUSERBYADMIN = "CreateUserByAdminController";
//    private final String CREATEUSER = "CreateController";
    private final String DELETE = "DeleteController";
    private final String UPDATE = "UpdateController";
    private final String GETLISTTYPE = "GetListTypeController";
//    private final String GETLISTCITY = "GetListCityController";
    private final String GETLISTWAY = "GetListWayController";
//    private final String GETCITYNAME = "GetCityNameController";
//    private final String GETWAYNAME = "GetWayNameController";
//    private final String GETTYPENAME = "GetTypeNameController";
//    private final String GETDETAILHOUSE = "GetDetailHouseController";
//    private final String GETLISTBEGINNING = "GetListWhenBeginningController";
    private final String VIEWPROPERTYHOUSE = "ViewPropertyController";
    private final String ADDTOCART = "AddToCartController";
    private final String DELETEPRODUCTINCART = "DeleteProductInCartController";
    private final String PAY = "PayController";
    private final String VIEWDETAILBILLUSER = "ViewDetailBillByAdminController";

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
            String action = request.getParameter("action");
            if (action != null) {
                if (action.equalsIgnoreCase("Login")) {
                    url = LOGIN;
                } else if (action.equalsIgnoreCase("Sign Up")) {
                    url = SENDCODETOVERIFY;
                } else if (action.equalsIgnoreCase("Go to verify")) {
                    url = GOTOVERIFY;
                }else if (action.equalsIgnoreCase("Verify")) {
                    url = VERIFYEMAIL;
                }else if (action.equalsIgnoreCase("User Signup")) {
                    url = SIGNUP;
                } else if (action.equalsIgnoreCase("Go to login")) {
                    url = GOTOLOGIN;
                } else if (action.equalsIgnoreCase("Go to signup")) {
                    url = GOTOSIGNUP;
                } else if (action.equalsIgnoreCase("Logout")) {
                    url = LOGOUT;
                } else if (action.equalsIgnoreCase("Search")) {
                    url = SEARCH;
                } else if (action.equalsIgnoreCase("Delete")) {
                    url = DELETE;
                } else if (action.equalsIgnoreCase("Update")) {
                    url = UPDATE;
                } else if (action.equalsIgnoreCase("View Property")) {
                    url = VIEWPROPERTYHOUSE;
                } else if (action.equalsIgnoreCase("Add To Cart")) {
                    url = ADDTOCART;
                } else if (action.equalsIgnoreCase("DeleteHouseInBill")) {
                    url = DELETEPRODUCTINCART;
                } else if (action.equalsIgnoreCase("PAY")) {
                    url = PAY;
                } else if (action.equalsIgnoreCase("Update Detail")) {
                    url = UPDATEUSER;
                } else if (action.equalsIgnoreCase("View Bill User")) {
                    url = VIEWBILLUSER;
                } else if (action.equalsIgnoreCase("View Detail Bill")) {
                    url = VIEWDETAILBILL;
                } else if (action.equalsIgnoreCase("GetAllHouse")) {
                    url = GETALLHOUSE;
                } else if (action.equalsIgnoreCase("Delete House")) {
                    url = DELETEHOUSE;
                } else if (action.equalsIgnoreCase("Update House")) {
                    url = UPDATEHOUSE;
                } else if (action.equalsIgnoreCase("GetListType")) {
                    url = GETLISTTYPE;
                } else if (action.equalsIgnoreCase("GetListWay")) {
                    url = GETLISTWAY;
                } else if (action.equalsIgnoreCase("Insert New House")) {
                    url = GOTOINSERTHOUSE;
                } else if (action.equalsIgnoreCase("Insert House")) {
                    url = INSERTHOUSE;
                } else if (action.equalsIgnoreCase("Search User")) {
                    url = SEARCHUSER;
                } else if (action.equalsIgnoreCase("Update User")) {
                    url = UPDATEUSERBYADMIN;
                } else if (action.equalsIgnoreCase("Delete User")) {
                    url = DELETEUSERBYADMIN;
                } else if (action.equalsIgnoreCase("Create new User")) {
                    url = GOTOCREATE;
                } else if (action.equalsIgnoreCase("Create User")) {
                    url = CREATEUSERBYADMIN;
                } else if (action.equalsIgnoreCase("ManageUser")) {
                    url = MANAGERUSER;
                } else if (action.equalsIgnoreCase("ManageHouse")) {
                    url = MANAGEHOUSE;
                } else if (action.equalsIgnoreCase("ManageBill")) {
                    url = MANAGEBILL;
                } else if (action.equalsIgnoreCase("ViewDetailUser")) {
                    url = VIEWDETAILUSER;
                } else if (action.equalsIgnoreCase("View Detail Bill User")) {
                    url = VIEWDETAILBILLUSER;
                }
            }
        } catch (Exception ex) {
            log("Error at MainController: " + ex.toString());
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
