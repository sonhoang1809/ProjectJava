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

    String userID;
    String userName;
    String email;
    String password;
    String roleID;

    public UserDTO(String userName, String roleID) {
        this.userName = userName;
        this.roleID = roleID;
    }
    

    public UserDTO(String userID, String userName, String email, String password, String roleID) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roleID = roleID;
    }
    

    public UserDTO(String userID, String userName, String password, String roleID) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.roleID = roleID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
