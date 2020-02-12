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
import static sample.daos.DAO.closeConnection;
import static sample.daos.DAO.cnn;
import static sample.daos.DAO.log;
import static sample.daos.DAO.ps;
import static sample.daos.DAO.rs;
import sample.dtos.FoodDTO;
import sample.dtos.FoodsInBillDTO;
import sample.mails.SendMailSSL;
import sample.utils.DBUtils;

/**
 *
 * @author sonho
 */
public class FoodInBillDAO extends DAO {

    public static float getTotalInBill(String idBill) throws SQLException {
        float result = 0;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT Price FROM tblProductsInBill WHERE IdBill = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, idBill);
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = result + rs.getFloat("Price");
                }
            }
        } catch (Exception ex) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean checkContainProduct(String idBill, String idHouse) throws SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdHouse FROM tblProductsInBill WHERE IdBill = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, idBill);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString("IdHouse").equalsIgnoreCase(idHouse)) {
                        result = true;
                    }
                }
            }
        } catch (Exception ex) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<FoodsInBillDTO> getListProductInBill(String idBill) throws SQLException {
        List<FoodsInBillDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdHouse, Price FROM tblProductsInBill WHERE IdBill = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, idBill);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new FoodsInBillDTO(idBill, rs.getString("IdHouse"), rs.getFloat("Price")));
                }

            }
        } catch (Exception ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static int getNumProductInBill(String idBill) throws SQLException {
        int result = 0;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdHouse FROM tblProductsInBill WHERE IdBill = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, idBill);
                rs = ps.executeQuery();
                while (rs.next()) {
                    result++;
                }

            }
        } catch (Exception ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean insertProductToBill(FoodsInBillDTO dto) throws SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "INSERT INTO tblFoodsInBill(IdBill, IdFood, Quantity, StatusFoodID, Price, Total)"
                        + " VALUES(?,?,?,?,?,?) ";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, dto.getIdBill());
                ps.setString(2, dto.getIdFood());
                ps.setInt(3, dto.getQuantity());
                ps.setInt(4, dto.getStatusFoodID());
                ps.setFloat(5, dto.getPrice());
                ps.setFloat(6, dto.getTotal());
                ps.executeUpdate();
                result = true;
            }
        } catch (SQLException | NamingException ex) {
            log.error("Error at insertProductToBill(FoodsInBillDTO dto): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at insertProductToBill(FoodsInBillDTO dto): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }
}
