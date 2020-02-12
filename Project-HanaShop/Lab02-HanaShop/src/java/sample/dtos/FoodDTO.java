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
public class FoodDTO {

    String idFood;
    String imgFood;
    String nameFood;
    String description;
    String shortDescription;
    int quantity;
    float price;
    String categoryID;
    String createDate;
    int statusCode;
    String kind;
    String categoryName;

    public FoodDTO(String idFood, String imgFood, String nameFood, String description, String shortDescription, int quantity, float price, String categoryID, String createDate, int statusCode) {
        this.idFood = idFood;
        this.imgFood = imgFood;
        this.nameFood = nameFood;
        this.description = description;
        this.shortDescription = shortDescription;
        this.quantity = quantity;
        this.price = price;
        this.categoryID = categoryID;
        this.createDate = createDate;
        this.statusCode = statusCode;
    }

    public FoodDTO(String idFood, String imgFood, String nameFood, String description, int quantity, float price, String categoryID, String createDate, int statusCode) {
        this.idFood = idFood;
        this.imgFood = imgFood;
        this.nameFood = nameFood;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.categoryID = categoryID;
        this.createDate = createDate;
        this.statusCode = statusCode;
    }

    public String getIdFood() {
        return idFood;
    }

    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }

    public String getImgFood() {
        return imgFood;
    }

    public void setImgFood(String imgFood) {
        this.imgFood = imgFood;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
