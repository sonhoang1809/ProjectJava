/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.supports;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import sample.dtos.CategoryDTO;

/**
 *
 * @author sonho
 */
public class Support {

    public static String getShortDescription(String description) {
        String result = null;
        if (description.length() > 90) {
            result = description.substring(0, 90) + " ...";
        } else {
            result = description;
        }
        return result;
    }

    public static String getSQL(List<CategoryDTO> listCategory) {
        String result = "( ";
        for (int i = 0; i < listCategory.size(); i++) {
            if (i != listCategory.size() - 1) {
                result = result + "CategoryID = ? OR ";
            } else {
                result = result + "CategoryID = ? )";
            }
        }
        return result;
    }

    public static String getTime(String postingDate) {
        Date dt = new Date();
        SimpleDateFormat sdf
                = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        String dayMonYearPostingDate = postingDate.split(" ")[0];
        String dayMonYearCurrTime = currentTime.split(" ")[0];
        if (dayMonYearPostingDate.equals(dayMonYearCurrTime)) {
            String hourMinSecPostingDate = postingDate.split(" ")[1];
            String hourMinSecCurrTime = currentTime.split(" ")[1];
            String hourPostingDate = hourMinSecPostingDate.split(":")[0];
            String hourCurrTime = hourMinSecCurrTime.split(":")[0];
            if (!hourPostingDate.equals(hourCurrTime)) {
                int hourPost = Integer.parseInt(hourPostingDate);
                int hourCurr = Integer.parseInt(hourCurrTime);
                return (hourCurr - hourPost) + " hours ago";
            } else {
                String minPostingDate = hourMinSecPostingDate.split(":")[1];
                String minCurrTime = hourMinSecCurrTime.split(":")[1];
                if (!minPostingDate.equals(minCurrTime)) {
                    int minPost = Integer.parseInt(minPostingDate);
                    int minCurr = Integer.parseInt(minCurrTime);
                    return (minCurr - minPost) + " minutes ago";
                } else {
                    String secPostingDate = hourMinSecPostingDate.split(":")[2];
                    String secCurrTime = hourMinSecCurrTime.split(":")[2];
                    if (secPostingDate.contains(".")) {
                        secPostingDate = secPostingDate.split("[.]")[0];
                    }
                    if (secCurrTime.contains(".")) {
                        secCurrTime = secCurrTime.split("[.]")[0];
                    }
                    int secPost = Integer.parseInt(secPostingDate);
                    int secCurr = Integer.parseInt(secCurrTime);
                    return (secCurr - secPost) + " seconds ago";
                }
            }
        } else {
            String yearPostingDate = dayMonYearPostingDate.split("-")[0];
            String yearCurrTime = dayMonYearCurrTime.split("-")[0];
            if (!yearPostingDate.equals(yearCurrTime)) {
                int yearPost = Integer.parseInt(yearPostingDate);
                int yearCurr = Integer.parseInt(yearCurrTime);
                return (yearCurr - yearPost) + " years ago";
            } else {
                String monthPostingDate = dayMonYearPostingDate.split("-")[1];
                String monthCurrTime = dayMonYearCurrTime.split("-")[1];
                if (!monthPostingDate.equals(monthCurrTime)) {
                    int monthPost = Integer.parseInt(monthPostingDate);
                    int monthCurr = Integer.parseInt(monthCurrTime);
                    return (monthCurr - monthPost) + " months ago";
                } else {
                    String dayPostingDate = dayMonYearPostingDate.split("-")[2];
                    String dayCurrTime = dayMonYearCurrTime.split("-")[2];
                    int dayPost = Integer.parseInt(dayPostingDate);
                    int dayCurr = Integer.parseInt(dayCurrTime);
                    return (dayCurr - dayPost) + " days ago";
                }
            }
        }
    }

    public static int calculateNumPage(int numFood) {
        if (numFood % 20 == 0) {
            return numFood / 20;
        } else {
            return (numFood / 20 + 1);
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
