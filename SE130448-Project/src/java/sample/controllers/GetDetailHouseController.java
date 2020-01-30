/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.daos.CityDAO;
import sample.daos.TypeDAO;
import sample.daos.WayDAO;
import sample.dtos.DetailHouseDTO;
import sample.dtos.HouseDTO;

/**
 *
 * @author sonho
 */
public class GetDetailHouseController extends HttpServlet {

    private final String SUCCESS = "index.jsp";

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
            Map<String, DetailHouseDTO> detail = new HashMap<>();
            HttpSession session = request.getSession();
            List<HouseDTO> listHouse = (List<HouseDTO>) session.getAttribute("LISTHOUSE");
            for (HouseDTO x : listHouse) {
                String wayName = WayDAO.getWayNameByIdWay(x.getIdWay());
                String idCity = WayDAO.getIdCityByIdWay(x.getIdWay());
                String cityName = CityDAO.getCityNameByCityId(idCity);
                String typeName = TypeDAO.getTypeNameByTypeId(x.getTypeId());
                DetailHouseDTO dhDTO = new DetailHouseDTO(wayName, idCity, cityName, typeName);
                detail.put(x.getIdHouse(), dhDTO);
            }
            session.setAttribute("LISTHOUSEDETAIL", detail);
//            String idWay = request.getParameter("idWay");
//            String typeId = request.getParameter("idType");
//            String wayName = WayDAO.getWayNameByIdWay(idWay);
//            String idCity = WayDAO.getIdCityByIdWay(idWay);
//            String cityName = CityDAO.getCityNameByCityId(idCity);
//            String typeName = TypeDAO.getTypeNameByTypeId(typeId);
//            DetailHouseDTO dhDTO = new DetailHouseDTO(wayName, cityName, typeName);
//            session.setAttribute("DETAILHOUSE", dhDTO);
        } catch (Exception ex) {
            log("Error at GetDetailHouseController: " + ex.toString());
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
