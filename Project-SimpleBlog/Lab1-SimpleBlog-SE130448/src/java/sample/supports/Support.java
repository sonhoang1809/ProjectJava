/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.supports;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 *
 * @author sonho
 */
public class Support {

    public static int calculateNumPage(int numArticle) {
        if (numArticle % 5 == 0) {
            return numArticle / 5;
        } else {
            return (numArticle / 5 + 1);
        }
    }

    public static String getCode() {
        Random r = new Random();
        String code = (r.nextInt(10000) + 1000) + "";
        return code;
    }

    /*SHA-256*/
    public static String encryptedPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < encodedhash.length; i++) {
            String hex = Integer.toHexString(0xff & encodedhash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
