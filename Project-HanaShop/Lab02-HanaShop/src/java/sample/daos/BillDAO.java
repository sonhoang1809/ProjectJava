/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import static sample.daos.DAO.closeConnection;
import static sample.daos.DAO.cnn;
import static sample.daos.DAO.ps;
import static sample.daos.DAO.rs;
import sample.dtos.BillDTO;
import sample.mails.SendMailSSL;
import sample.utils.DBUtils;

/**
 *
 * @author sonho
 */
public class BillDAO extends DAO {

    public static List<BillDTO> getListAllBill() throws SQLException {
        List<BillDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdBill,UserID, Total, Date, IDStatusBill, BillNum FROM tblBills";
                ps = cnn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new BillDTO(rs.getString("IdBill"), rs.getString("UserID"), rs.getFloat("Total"),
                            rs.getString("Date"), rs.getInt("IDStatusBill"), rs.getInt("BillNum")));
                }
            }
        } catch (Exception ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<BillDTO> getListBillByUserID(String userID) throws SQLException {
        List<BillDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdBill, Total, Date, IDStatusBill, BillNum FROM tblBills WHERE UserID = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new BillDTO(rs.getString("IdBill"), userID, rs.getFloat("Total"),
                            rs.getString("Date"), rs.getInt("IDStatusBill"), rs.getInt("BillNum")));
                }
            }
        } catch (Exception ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean checkUserHasBill(String userID) throws SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdBill FROM tblBills WHERE UserID = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            }
        } catch (Exception ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static String getLastBillIsNotPaid(String userID) throws SQLException {
        String result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdBill FROM tblBills WHERE UserID = ? AND StatusBillCode = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setInt(2, 0);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("IdBill");
                }
            }
        } catch (SQLException | NamingException ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static int getNumBill(String userID) throws SQLException {
        int result = 1;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdBill FROM tblBills WHERE UserID = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, userID);
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

    public static BillDTO createNewBillForUser(String userID, int billNum) throws SQLException {
        BillDTO result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "INSERT INTO tblBills(IdBill, UserID, Total, Date, StatusBillCode) "
                        + " VALUES(?,?,?,?,?) ";
                ps = cnn.prepareStatement(sql);
                String idBill = billNum + "-" + userID;
                ps.setString(1, idBill);
                ps.setString(2, userID);
                float total = 0;
                ps.setFloat(3, total);
                Date date = new Date();
                SimpleDateFormat df
                        = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                ps.setString(4, df.format(date));
                int idStatusBill = 0;
                ps.setInt(5, idStatusBill);
                ps.executeUpdate();
                result = new BillDTO(idBill, userID, total, date.toString(), idStatusBill, billNum);
            }
        } catch (Exception ex) {
            log.error("Error at createNewBillForUser(String userID, int billNum): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at createNewBillForUser(String userID, int billNum): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static float getTotalOfBill(String billID) throws SQLException {
        float result = 0;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT Total FROM tblBills WHERE IdBill = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, billID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getFloat("Total");
                }
            }
        } catch (SQLException | NamingException ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean updateBillTotal(String idBill, float totalBill) throws SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "UPDATE tblBills SET Total = ?, Date = ? "
                        + " WHERE IdBill = ?";
                ps = cnn.prepareStatement(sql);
                ps.setFloat(1, totalBill);
                Date date = new Date();
                SimpleDateFormat df
                        = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                ps.setString(2, df.format(date));
                ps.setString(3, idBill);
                ps.executeUpdate();
                result = true;
            }
        } catch (Exception ex) {
            log.error("Error at updateBillTotal(String idBill, float totalBill): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at updateBillTotal(String idBill, float totalBill): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean updateBillDetail(BillDTO dto) throws SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "UPDATE tblBills SET Total = ?, Date = ?, IDStatusBill = ?"
                        + " WHERE IdBill = ?";
                ps = cnn.prepareStatement(sql);
                ps.setFloat(1, dto.getTotal());
                long millis = System.currentTimeMillis();
                Date date = new java.sql.Date(millis);
                ps.setString(2, date.toString());
                ps.setInt(3, dto.getStatusBillCode());
                ps.setString(4, dto.getIdBill());
                ps.executeUpdate();
                result = true;
            }
        } catch (Exception ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static BillDTO getBillDetailByBillID(String billID) throws SQLException {
        BillDTO result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT UserID, Total, Date, IDStatusBill, BillNum FROM tblBills WHERE IdBill = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, billID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = new BillDTO(billID, rs.getString("UserID"), rs.getFloat("Total"),
                            rs.getString("Date"), rs.getInt("IDStatusBill"), rs.getInt("BillNum"));
                }
            }
        } catch (Exception ex) {

        } finally {
            closeConnection();
        }
        return result;
    }
}
