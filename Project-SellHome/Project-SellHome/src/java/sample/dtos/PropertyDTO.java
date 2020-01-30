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
public class PropertyDTO {

    String idHouse;
    boolean mediaRoom;
    boolean familyRoom;
    boolean gymRoom;
    boolean Library;
    boolean pool;
    boolean TV;
    boolean kitchen;
    boolean livingRoom;
    boolean garden;

    public PropertyDTO(String idHouse, boolean mediaRoom, boolean familyRoom, boolean gymRoom, boolean Library, boolean pool, boolean TV, boolean kitchen, boolean livingRoom, boolean garden) {
        this.idHouse = idHouse;
        this.mediaRoom = mediaRoom;
        this.familyRoom = familyRoom;
        this.gymRoom = gymRoom;
        this.Library = Library;
        this.pool = pool;
        this.TV = TV;
        this.kitchen = kitchen;
        this.livingRoom = livingRoom;
        this.garden = garden;
    }

    public String getIdHouse() {
        return idHouse;
    }

    public void setIdHouse(String idHouse) {
        this.idHouse = idHouse;
    }

    public boolean isMediaRoom() {
        return mediaRoom;
    }

    public void setMediaRoom(boolean mediaRoom) {
        this.mediaRoom = mediaRoom;
    }

    public boolean isFamilyRoom() {
        return familyRoom;
    }

    public void setFamilyRoom(boolean familyRoom) {
        this.familyRoom = familyRoom;
    }

    public boolean isGymRoom() {
        return gymRoom;
    }

    public void setGymRoom(boolean gymRoom) {
        this.gymRoom = gymRoom;
    }

    public boolean isLibrary() {
        return Library;
    }

    public void setLibrary(boolean Library) {
        this.Library = Library;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isTV() {
        return TV;
    }

    public void setTV(boolean TV) {
        this.TV = TV;
    }

    public boolean isKitchen() {
        return kitchen;
    }

    public void setKitchen(boolean kitchen) {
        this.kitchen = kitchen;
    }

    public boolean isLivingRoom() {
        return livingRoom;
    }

    public void setLivingRoom(boolean livingRoom) {
        this.livingRoom = livingRoom;
    }

    public boolean isGarden() {
        return garden;
    }

    public void setGarden(boolean garden) {
        this.garden = garden;
    }
    
    
}
