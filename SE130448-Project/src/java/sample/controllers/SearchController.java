/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.daos.CityDAO;
import sample.daos.HouseDAO;
import sample.daos.TypeDAO;
import sample.daos.WayDAO;
import sample.dtos.CityDTO;
import sample.dtos.DetailHouseDTO;
import sample.dtos.HouseDTO;
import sample.dtos.TypeDTO;
import sample.dtos.WayDTO;

/**
 *
 * @author sonho
 */
public class SearchController extends HttpServlet {

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
            HttpSession session = request.getSession();
            String idCity = request.getParameter("idCity");
            String idWay = request.getParameter("idWay");
            String typeId = request.getParameter("typeID");
            if (idCity == null) {
                idCity = "";
            }
            if (idWay == null) {
                idWay = "";
            }
            if (typeId == null) {
                typeId = "";
            }
            String pLower = request.getParameter("priceLower");
            if(pLower == null){
                pLower = "1000000";
            }
            String pHigher = request.getParameter("priceHigher");
            if(pHigher == null){
                pHigher = "100000";
            }
            request.setAttribute("IDCITY", idCity);
            request.setAttribute("IDWAY", idWay);
            request.setAttribute("TYPEID", typeId);
            if (pLower == null) {
                pLower = (String) request.getAttribute("PRICELOWER");
            }
            if (pHigher == null) {
                pHigher = (String) request.getAttribute("PRICEHIGHER");
            }
            float priceLower = Float.parseFloat(pLower);
            float priceHigher = Float.parseFloat(pHigher);
            request.setAttribute("PRICELOWER", priceLower + "");
            request.setAttribute("PRICEHIGHER", priceHigher + "");
            List<CityDTO> listCity = null;
            List<HouseDTO> listHouse = null;
            List<TypeDTO> listType = null;
            List<WayDTO> listWay = null;
            if (idCity == null) {
                session.setAttribute("LISTWAY", null);
            } else {
                if (idCity.equals("")) {
                    session.setAttribute("LISTWAY", null);
                }
            }
            Map<String, DetailHouseDTO> detail = new HashMap<>();
            //000
            if ((idCity == null && idWay == null && typeId == null)
                    || (idCity.equals("") && idWay.equals("") && typeId.equals(""))) {
                listCity = CityDAO.getAllListCity();
                //get list house with price default
                listType = TypeDAO.getAllListType();
                listHouse = HouseDAO.getListHouseWithPriceBetween(priceLower, priceHigher);
                //            session.setAttribute("LISTWAY", null);
                //            session.setAttribute("TYPEID", null);
                //            session.setAttribute("IDCITY", null);
                request.setAttribute("LISTHOUSE", listHouse);
                session.setAttribute("LISTCITY", listCity);
                session.setAttribute("LISTTYPE", listType);
            } //001
            else if ((idCity == null && idWay == null && typeId != null)
                    || (idCity.equals("") && idWay.equals("") && !typeId.equals(""))) {

                listCity = CityDAO.getAllListCity();
                session.setAttribute("LISTCITY", listCity);
                listHouse = HouseDAO.getListHouseByTypeId(typeId, priceLower, priceHigher);
                request.setAttribute("LISTHOUSE", listHouse);
                request.setAttribute("TYPEID", typeId);
            } //010
            else if ((idCity == null && idWay != null && typeId == null)
                    || (idCity.equals("") && !idWay.equals("") && typeId.equals(""))) {
                listCity = CityDAO.getAllListCity();
                listType = TypeDAO.getAllListType();
                listHouse = HouseDAO.getListHouseWithPriceBetween(priceLower, priceHigher);
                session.setAttribute("LISTWAY", null);
                session.setAttribute("LISTCITY", listCity);
                request.setAttribute("IDWAY", null);
                request.setAttribute("LISTHOUSE", listHouse);
            } //011
            else if ((idCity == null && idWay != null && typeId != null)
                    || (idCity.equals("") && !idWay.equals("") && !typeId.equals(""))) {
                //vo ly
                listCity = CityDAO.getAllListCity();
                request.setAttribute("IDWAY", null);
                listHouse = HouseDAO.getListHouseByTypeId(typeId, priceLower, priceHigher);
                request.setAttribute("LISTHOUSE", listHouse);
                session.setAttribute("LISTWAY", null);
                request.setAttribute("TYPEID", typeId);
            } //100
            else if ((idCity != null && idWay == null && typeId == null)
                    || (!idCity.equals("") && idWay.equals("") && typeId.equals(""))) {
                listWay = WayDAO.getListWayByIdCity(idCity);
                List<String> listWayId = WayDAO.getListWayIdByIdCity(idCity);
                listHouse = HouseDAO.getListHouseWithListWay(listWayId, priceLower, priceHigher);
                listType = TypeDAO.getAllListType();
                request.setAttribute("LISTHOUSE", listHouse);
                session.setAttribute("LISTWAY", listWay);
                session.setAttribute("LISTTYPE", listType);
                //    session.setAttribute("", url);
                //           session.setAttribute("TYPEID", null);
                //           session.setAttribute("IDWAY", null);
            }//101
            else if ((idCity != null && idWay == null && typeId != null)
                    || (!idCity.equals("") && idWay.equals("") && !typeId.equals(""))) {
                listWay = WayDAO.getListWayByIdCity(idCity);
                List<String> listWayId = WayDAO.getListWayIdByIdCity(idCity);
                listHouse = HouseDAO.getListHouseByTypeIdAndListWayId(priceLower, priceHigher, typeId, listWayId);
                request.setAttribute("LISTHOUSE", listHouse);
                //            session.setAttribute("IDCITY", idCity);
                //            session.setAttribute("IDWAY", null);
                session.setAttribute("LISTWAY", listWay);
            }//110
            else if ((idCity != null && idWay != null && typeId == null)
                    || (!idCity.equals("") && !idWay.equals("") && typeId.equals(""))) {
                listWay = WayDAO.getListWayByIdCity(idCity);
                List<String> listWayId = WayDAO.getListWayIdByIdCity(idCity);
                boolean check = WayDAO.checkWayBelongToIdCity(idWay, idCity);
                //            session.setAttribute("IDCITY", idCity);
                if (check) {
                    listHouse = HouseDAO.getListHouseByIdWay(idWay, priceLower, priceHigher);
                } else {
                    listHouse = HouseDAO.getListHouseWithListWay(listWayId, priceLower, priceHigher);
                }
                request.setAttribute("LISTHOUSE", listHouse);
                session.setAttribute("LISTWAY", listWay);
            }//111
            else if ((idCity != null && idWay != null && typeId != null)
                    || (!idCity.equals("") && !idWay.equals("") && !typeId.equals(""))) {
                //    listWay = WayDAO.getListWayByIdCity(idCity);
                listWay = WayDAO.getListWayByIdCity(idCity);
                boolean check = WayDAO.checkWayBelongToIdCity(idWay, idCity);
                if (check) {
                    listHouse = HouseDAO.getListHouseByIdWayAndTypeId(idWay, typeId, priceLower, priceHigher);
                } else {
                    //            session.setAttribute("IDWAY", null);
                    List<String> listWayId = WayDAO.getListWayIdByIdCity(idCity);
                    listHouse = HouseDAO.getListHouseWithListWay(listWayId, priceLower, priceHigher);
                }
                //    listHouse = HouseDAO.getListHouseByIdWayAndTypeId(idWay, typeId, priceLower, priceHigher);
                request.setAttribute("LISTHOUSE", listHouse);
                request.setAttribute("IDWAY", idWay);
            }
            if (listHouse != null) {
                for (HouseDTO x : listHouse) {
                    String wayName = WayDAO.getWayNameByIdWay(x.getIdWay());
                    String idCity2 = WayDAO.getIdCityByIdWay(x.getIdWay());
                    String cityName = CityDAO.getCityNameByCityId(idCity2);
                    String typeName = TypeDAO.getTypeNameByTypeId(x.getTypeId());
                    DetailHouseDTO dhDTO = new DetailHouseDTO(wayName, idCity2, cityName, typeName);
                    detail.put(x.getIdHouse(), dhDTO);
                }
            }
            request.setAttribute("LISTHOUSEDETAIL", detail);
            if (listCity != null) {
                session.setAttribute("LISTCITY", listCity);
            }
            if (listWay != null) {
                session.setAttribute("LISTWAY", listWay);
            }
            if (listType != null) {
                session.setAttribute("LISTTYPE", listType);
            }

        } catch (NumberFormatException | SQLException ex) {
            log("Error at SearchController: " + ex.toString());
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
