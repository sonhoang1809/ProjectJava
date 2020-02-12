/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.SQLException;
import javax.naming.NamingException;
import static sample.daos.DAO.closeConnection;
import static sample.daos.DAO.cnn;
import static sample.daos.DAO.log;
import static sample.daos.DAO.ps;
import static sample.daos.DAO.rs;
import sample.dtos.UserDTO;
import sample.mails.SendMailSSL;
import sample.utils.DBUtils;

/**
 *
 * @author sonho
 */
public class UserDAO extends DAO {

    public static UserDTO checkLoginByGoogle(String email, String pass) throws SQLException {
        UserDTO result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String url = "SELECT UserID, Avatar, Name, RoleID FROM tblUsers WHERE Email = ? AND Password = ?";
                ps = cnn.prepareStatement(url);
                ps.setString(1, email);
                ps.setString(2, pass);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = new UserDTO(rs.getString("UserID"), email, rs.getString("Avatar"), rs.getString("Name"),
                            rs.getString("RoleID"));
                }
            }
        } catch (SQLException | NamingException ex) {
            log.error("Error at checkLogin(String userID, String pass): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at checkLogin(String userID, String pass): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static UserDTO checkLogin(String userID, String pass) throws SQLException {
        UserDTO result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String url = "SELECT Email, Avatar, Name, RoleID FROM tblUsers WHERE UserID = ? AND Password = ?";
                ps = cnn.prepareStatement(url);
                ps.setString(1, userID);
                ps.setString(2, pass);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = new UserDTO(userID, rs.getString("Email"), rs.getString("Avatar"), rs.getString("Name"),
                            rs.getString("RoleID"));
                }
            }
        } catch (SQLException | NamingException ex) {
            log.error("Error at checkLogin(String userID, String pass): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at checkLogin(String userID, String pass): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static UserDTO getUserByEmail(String email) throws NamingException, SQLException {
        UserDTO result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String url = "SELECT UserID, Avatar, Name, RoleID FROM tblUsers WHERE Email = ?";
                ps = cnn.prepareStatement(url);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = new UserDTO(rs.getString("UserID"), email, rs.getString("Avatar"), rs.getString("Name"),
                            rs.getString("RoleID"));
                }
            }
        } catch (SQLException ex) {
            log.error("Error at getUserByEmail(String email): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getUserByEmail(String email): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean insertAUser(UserDTO dto) throws SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String url = "INSERT INTO tblUsers (UserID, Email, Avatar, Name, Password, RoleID)"
                        + " VALUES (?,?,?,?,?,?)";
                ps = cnn.prepareStatement(url);
                ps.setString(1, dto.getUserID());
                ps.setString(2, dto.getEmail());
                ps.setString(3, dto.getAvatar());
                ps.setString(4, dto.getName());
                ps.setString(5, dto.getPassword());
                ps.setString(6, dto.getRoleID());
                ps.executeUpdate();
                result = true;
            }
        } catch (SQLException | NamingException ex) {
            log.error("Error at insertAUser(UserDTO dto): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at insertAUser(UserDTO dto): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean checkExistUserID(String userID) throws NamingException, SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null && userID != null) {
                String sql = "SELECT Name FROM tblUsers WHERE UserID = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            }
        } catch (SQLException ex) {
            log.error("Error at checkExistUserID(String userID): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at checkExistUserID(String userID): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public static boolean checkExistEmail(String email) throws NamingException, SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null && email != null) {
                String sql = "SELECT Name FROM tblUsers WHERE Email = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            }
        } catch (SQLException ex) {
            log.error("Error at checkExistEmail(String email): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at checkExistEmail(String email): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }
}
