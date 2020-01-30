/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.errors;

/**
 *
 * @author sonho
 */
public class LoginError {

    public String errorExistEmail;
    public String errorPassword;

    public LoginError() {
    }

    public String getErrorExistEmail() {
        return errorExistEmail;
    }

    public void setErrorExistEmail(String errorExistEmail) {
        this.errorExistEmail = errorExistEmail;
    }

    public String getErrorPassword() {
        return errorPassword;
    }

    public void setErrorPassword(String errorPassword) {
        this.errorPassword = errorPassword;
    }

}
