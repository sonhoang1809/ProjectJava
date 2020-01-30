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
public class DetailHouseDTO {

    private String wayName;
    private String cityId;
    private String cityName;
    private String typeName;

    public DetailHouseDTO(String wayName, String cityId, String cityName, String typeName) {
        this.wayName = wayName;
        this.cityId = cityId;
        this.cityName = cityName;
        this.typeName = typeName;
    }

    public DetailHouseDTO(String wayName, String cityName, String typeName) {
        this.wayName = wayName;
        this.cityName = cityName;
        this.typeName = typeName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getWayName() {
        return wayName;
    }

    public void setWayName(String wayName) {
        this.wayName = wayName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
