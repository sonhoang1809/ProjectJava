/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dtos;

/**
 *
 * @author sonho
 */
public class ProductsInBillDTO {

    String idBill;
    String idHouse;
    float price;
    String picHouse;
    String description;

    public ProductsInBillDTO(String idBill, String idHouse, float price) {
        this.idBill = idBill;
        this.idHouse = idHouse;
        this.price = price;
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

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getIdHouse() {
        return idHouse;
    }

    public void setIdHouse(String idHouse) {
        this.idHouse = idHouse;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
