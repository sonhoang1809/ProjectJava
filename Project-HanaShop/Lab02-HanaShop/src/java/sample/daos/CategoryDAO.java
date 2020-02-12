/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.dtos.CategoryDTO;
import sample.mails.SendMailSSL;
import static sample.utils.DBUtils.getConnection;

/**
 *
 * @author sonho
 */
public class CategoryDAO extends DAO {

    public static CategoryDTO getCategoryByCategoryID(String categoryID) throws SQLException {
        CategoryDTO result = null;
        try {
            cnn = getConnection();
            if (cnn != null) {
                String sql = "SELECT CategoryID, CategoryName, KindID "
                        + " FROM tblCategories"
                        + " WHERE CategoryID = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, categoryID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = new CategoryDTO(rs.getString("CategoryID"), rs.getString("CategoryName"), rs.getString("KindID"));
                }
            }
        } catch (SQLException | NamingException ex) {
            log.error("Error at getCategoryByCategoryID(String categoryID): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getCategoryByCategoryID(String categoryID): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<CategoryDTO> getListCategoryByKindID(String kindID) throws SQLException {
        List<CategoryDTO> result = null;
        try {
            cnn = getConnection();
            if (cnn != null) {
                String sql = "SELECT CategoryID, CategoryName "
                        + " FROM tblCategories"
                        + " WHERE KindID = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, kindID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new CategoryDTO(rs.getString("CategoryID"), rs.getString("CategoryName")));
                }
            }
        } catch (SQLException | NamingException ex) {
            log.error("Error at getListCategoryByKindID(String kindID): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getListCategoryByKindID(String kindID): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }
}
