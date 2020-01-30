/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static sample.daos.DAO.cnn;
import sample.dtos.HouseDTO;
import sample.dtos.ProductsInBillDTO;
import sample.utils.DBUtils;

/**
 *
 * @author sonho
 */
public class ProductsInBillDAO extends DAO {

    public static boolean removeAHouseInBill(String idHouse, String idBill) throws SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "DELETE FROM tblProductsInBill WHERE IdHouse = ? AND IdBill = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, idHouse);
                ps.setString(2, idBill);
                ps.executeUpdate();
                result = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
        } finally {
            closeConnection();
        }
        return result;
    }

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
        } catch (ClassNotFoundException | SQLException ex) {
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
        } catch (ClassNotFoundException | SQLException ex) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<ProductsInBillDTO> getListProductInBill(String idBill) throws SQLException {
        List<ProductsInBillDTO> result = null;
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
                    result.add(new ProductsInBillDTO(idBill, rs.getString("IdHouse"), rs.getFloat("Price")));
                }

            }
        } catch (ClassNotFoundException | SQLException ex) {

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
        } catch (ClassNotFoundException | SQLException ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean insertProductToBill(HouseDTO houseDTO, String idBill) throws SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "INSERT INTO tblProductsInBill(IdBill, IdHouse, Price)"
                        + " VALUES(?,?,?) ";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, idBill);
                ps.setString(2, houseDTO.getIdHouse());
                ps.setFloat(3, houseDTO.getPrice());
                ps.executeUpdate();
                result = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

}
