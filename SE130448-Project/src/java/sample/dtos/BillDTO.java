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
public class BillDTO {

    String idBill;
    String userID;
    float total;
    String date;
    int idStatusBill;
    String statusBill;
    int billNum;

    public BillDTO(String idBill, String userID, float total, String date, int idStatusBill, int billNum) {
        this.idBill = idBill;
        this.userID = userID;
        this.total = total;
        this.date = date;
        this.idStatusBill = idStatusBill;
        if (idStatusBill == 1) {
            statusBill = "Payed";
        } else if (idStatusBill == 0) {
            statusBill = "Not Payed Yet";
        }
        this.billNum = billNum;
    }

    public BillDTO(String idBill, String userID, String date, int idStatusBill, int billNum) {
        this.idBill = idBill;
        this.userID = userID;
        this.date = date;
        this.idStatusBill = idStatusBill;
        this.billNum = billNum;
    }

    public BillDTO(String idBill, String userID, float total) {
        this.idBill = idBill;
        this.userID = userID;
        this.total = total;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdStatusBill() {
        return idStatusBill;
    }

    public void setIdStatusBill(int idStatusBill) {
        this.idStatusBill = idStatusBill;
    }

    public int getBillNum() {
        return billNum;
    }

    public void setBillNum(int billNum) {
        this.billNum = billNum;
    }

    public String getStatusBill() {
        return statusBill;
    }

    public void setStatusBill(String statusBill) {
        this.statusBill = statusBill;
    }
    

}
