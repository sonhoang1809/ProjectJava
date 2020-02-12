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
public class UserDTO {

    private String userID;
    private String email;
    private String avatar;
    private String name;
    private String password;
    private String roleID;

    public UserDTO(String userID, String email, String avatar, String name, String password, String roleID) {
        this.userID = userID;
        this.email = email;
        this.avatar = avatar;
        this.name = name;
        this.password = password;
        this.roleID = roleID;
    }

    public UserDTO(String userID, String email, String avatar, String name, String roleID) {
        this.userID = userID;
        this.email = email;
        this.avatar = avatar;
        this.name = name;
        this.roleID = roleID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
}
