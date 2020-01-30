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
public class FurnitureDTO {

    private int lotSize;
    private int numBed;
    private int numBath;
    private int numGarage;

    public FurnitureDTO(int lotSize, int numBed, int numBath, int numGarage) {
        this.lotSize = lotSize;
        this.numBed = numBed;
        this.numBath = numBath;
        this.numGarage = numGarage;
    }

    public FurnitureDTO(String s) {
        String tmp[] = s.split("-");
        this.lotSize = Integer.parseInt(tmp[0]);
        this.numBed = Integer.parseInt(tmp[1]);
        this.numBath = Integer.parseInt(tmp[2]);
        this.numGarage = Integer.parseInt(tmp[3]);
    }

    public int getLotSize() {
        return lotSize;
    }

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }

    public int getNumBed() {
        return numBed;
    }

    public void setNumBed(int numBed) {
        this.numBed = numBed;
    }

    public int getNumBath() {
        return numBath;
    }

    public void setNumBath(int numBath) {
        this.numBath = numBath;
    }

    public int getNumGarage() {
        return numGarage;
    }

    public void setNumGarage(int numGarage) {
        this.numGarage = numGarage;
    }

    public String toString() {
        return this.lotSize + "-" + this.numBed + "-" + this.numBath + "-" + this.numGarage;
    }

}
