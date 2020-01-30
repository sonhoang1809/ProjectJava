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
public class CountyDTO {

    String IdCounty;
    String IdName;
    String IdCity;

    public CountyDTO(String IdCounty, String IdName, String IdCity) {
        this.IdCounty = IdCounty;
        this.IdName = IdName;
        this.IdCity = IdCity;
    }

    public String getIdCounty() {
        return IdCounty;
    }

    public void setIdCounty(String IdCounty) {
        this.IdCounty = IdCounty;
    }

    public String getIdName() {
        return IdName;
    }

    public void setIdName(String IdName) {
        this.IdName = IdName;
    }

    public String getIdCity() {
        return IdCity;
    }

    public void setIdCity(String IdCity) {
        this.IdCity = IdCity;
    }

}
