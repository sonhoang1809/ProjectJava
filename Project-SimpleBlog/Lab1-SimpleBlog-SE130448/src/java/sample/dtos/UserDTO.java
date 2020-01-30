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

    private String email;
    private String avatar;
    private String name;
    private String password;
    private String roleID;
    private String statusID;

    public UserDTO(String email, String avatar, String name, String roleID) {
        this.email = email;
        this.avatar = avatar;
        this.name = name;
        this.roleID = roleID;
    }

    public UserDTO(String email, String avatar, String name, String roleID, String statusID) {
        this.email = email;
        this.avatar = avatar;
        this.name = name;
        this.roleID = roleID;
        this.statusID = statusID;

    }

    public UserDTO(String email, String avatar, String name, String password, String roleID, String statusID) {
        this.email = email;
        this.avatar = avatar;
        this.name = name;
        this.password = password;
        this.roleID = roleID;
        this.statusID = statusID;

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

    public String getStatusID() {
        return statusID;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

}
