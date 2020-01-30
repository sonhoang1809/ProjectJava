/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dtos;

import java.sql.SQLException;
import sample.daos.TypeDAO;
import sample.daos.WayDAO;

/**
 *
 * @author sonho
 */
public class HouseDTO {

    String idHouse;
    String picHouse; //url
    String description;
    FurnitureDTO furniture;
    String typeId;
    float price;
    String idWay;
    int statusCode;

    public HouseDTO(String idHouse, float price) {
        this.idHouse = idHouse;
        this.price = price;
    }

    public HouseDTO(String idHouse, String picHouse, String description) {
        this.idHouse = idHouse;
        this.picHouse = picHouse;
        this.description = description;
    }

    public HouseDTO(String idHouse, String picHouse, String description, String furniture, String typeId, float price, String idWay, int statusCode) throws SQLException {
        this.idHouse = idHouse;
        this.picHouse = picHouse;
        this.description = description;
        this.furniture = new FurnitureDTO(furniture);
        this.typeId = typeId;
        this.price = price;
        this.idWay = idWay;
        this.statusCode = statusCode;
    }

    public String getWayName() throws SQLException {
        return WayDAO.getWayNameByIdWay(idWay);
    }

    public String getTypeName() throws SQLException {
        return TypeDAO.getTypeNameByTypeId(this.typeId);
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getIdWay() {
        return idWay;
    }

    public void setIdWay(String idWay) {
        this.idWay = idWay;
    }

    public FurnitureDTO getFurniture() {
        return furniture;
    }

    public void setFurniture(FurnitureDTO furniture) {
        this.furniture = furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = new FurnitureDTO(furniture);
    }

    public String getIdHouse() {
        return idHouse;
    }

    public void setIdHouse(String idHouse) {
        this.idHouse = idHouse;
    }

    public String getPicHouse() {
        return picHouse;
    }

    public void setPicHouse(String picHouse) {
        this.picHouse = picHouse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        String s = this.idHouse + "-" + this.price;
        return s;
    }

}
