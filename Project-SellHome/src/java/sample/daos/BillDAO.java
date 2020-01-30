/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.Date;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.dtos.BillDTO;
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
        } catch (ClassNotFoundException | SQLException ex) {
            
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
        } catch (ClassNotFoundException | SQLException ex) {

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
                String sql = "SELECT IdBill, IDStatusBill FROM tblBills WHERE UserID = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getInt("IDStatusBill") == 0) {
                        result = rs.getString("IdBill");
                        break;
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static int getBillNumLast(String userID) throws SQLException {
        int result = 0;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT BillNum FROM tblBills WHERE UserID = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int billNum = rs.getInt("BillNum");
                    if (result < billNum) {
                        result = billNum;
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
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
                String sql = "INSERT INTO tblBills(IdBill, UserID, Total, Date, IDStatusBill, BillNum)"
                        + " VALUES(?,?,?,?,?,?) ";
                ps = cnn.prepareStatement(sql);
                String idBill = billNum + "-" + userID;
                ps.setString(1, idBill);
                ps.setString(2, userID);
                float total = 0;
                ps.setFloat(3, total);
                //DateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:MI:SS");
                long millis = System.currentTimeMillis();
                Date date = new java.sql.Date(millis);
                ps.setString(4, date.toString());
                int idStatusBill = 0;
                ps.setInt(5, idStatusBill);
                ps.setInt(6, billNum);
                ps.executeUpdate();
                result = new BillDTO(idBill, userID, total, date.toString(), idStatusBill, billNum);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
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
                ps.setInt(3, dto.getIdStatusBill());
                ps.setString(4, dto.getIdBill());
                ps.executeUpdate();
                result = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {

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
        } catch (ClassNotFoundException | SQLException ex) {

        } finally {
            closeConnection();
        }
        return result;
    }
}
