/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.daos.CityDAO;
import sample.daos.HouseDAO;
import sample.daos.PropertyDAO;
import sample.daos.TypeDAO;
import sample.daos.WayDAO;
import sample.dtos.DetailHouseDTO;
import sample.dtos.HouseDTO;
import sample.dtos.PropertyDTO;

/**
 *
 * @author sonho
 */
public class ViewPropertyController extends HttpServlet {

    private final String SUCCESS = "single-property.jsp";

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

            HouseDTO houseDTO = HouseDAO.getHouseDTOByHouseID(idHouse);
            HttpSession session = request.getSession();
            if (houseDTO != null) {
                String idWay = houseDTO.getIdWay();
                String wayName = WayDAO.getWayNameByIdWay(idWay);
                String idCity = WayDAO.getIdCityByIdWay(idWay);
                String cityName = CityDAO.getCityNameByCityId(idCity);
                String typeName = TypeDAO.getTypeNameByTypeId(houseDTO.getTypeId());
                DetailHouseDTO detailHouse = new DetailHouseDTO(wayName, idCity, cityName, typeName);
                PropertyDTO propertyDTO = PropertyDAO.getPropertyByIdHouse(idHouse);
                String pL = request.getParameter("priceLower");
                String pH = request.getParameter("priceHigher");
                float priceLower = Float.parseFloat(pL);
                float priceHigher = Float.parseFloat(pH);
                List<HouseDTO> houseDTORelated = HouseDAO.getListHouseRelated(houseDTO, priceLower, priceHigher);
                if (houseDTORelated != null) {
                    if (houseDTORelated.size() > 1) {
                        Random r = new Random();
                        int n = r.nextInt(houseDTORelated.size());
                        request.setAttribute("HOUSERELATED", houseDTORelated.get(n));
                    } else {
                        request.setAttribute("HOUSERELATED", houseDTORelated.get(0));
                    }
                    DetailHouseDTO detailHouseRelated = detailHouse;
                    request.setAttribute("DETAILHOUSERELATED", detailHouseRelated);
                }
                String typeID = request.getParameter("typeID");
//                request.setAttribute("IDCITY", idCity);
//                request.setAttribute("IDWAY", idWay);
//                request.setAttribute("TYPEID", typeID);
                request.setAttribute("PROPERTYDTO", propertyDTO);
                request.setAttribute("HOUSEDTO", houseDTO);
                request.setAttribute("DETAILHOUSE", detailHouse);
            }
//            session.setAttribute("POSITION", "ViewPropertyController");
        } catch (NumberFormatException | SQLException ex) {
            log("Error at ViewPropertyController:" + ex.toString());
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
