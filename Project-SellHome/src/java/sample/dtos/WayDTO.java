/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dtos;

import java.sql.SQLException;

/**
 *
 * @author sonho
 */
public class WayDTO {

    String idWay;
    String wayName;
    String idCounty;

    public WayDTO(String idWay) throws SQLException {
        this.idWay = idWay;
//        this.wayName = WayDAO.getWayNameByIdWay(idWay);
//        this.idCounty = WayDAO.getIdCityByIdWay(idWay);
    }

    public WayDTO(String idWay, String wayName) {
        this.idWay = idWay;
        this.wayName = wayName;
    }

    public WayDTO(String idWay, String wayName, String idCounty) {
        this.idWay = idWay;
        this.wayName = wayName;
        this.idCounty = idCounty;
    }

    public String getIdWay() {
        return idWay;
    }

    public void setIdWay(String idWay) {
        this.idWay = idWay;
    }

    public String getWayName() {
        return wayName;
    }

    public void setWayName(String wayName) {
        this.wayName = wayName;
    }

    public String getIdCounty() {
        return idCounty;
    }

    public void setIdCounty(String idCounty) {
        this.idCounty = idCounty;
    }

    @Override
    public String toString() {
        return this.idWay + "-" + this.wayName;
    }

}
