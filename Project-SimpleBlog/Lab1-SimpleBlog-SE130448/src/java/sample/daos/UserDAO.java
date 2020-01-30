/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import static sample.daos.DAO.cnn;
import static sample.daos.DAO.log;
import sample.dtos.UserDTO;
import sample.mails.SendMailSSL;
import sample.utils.DBUtils;

/**
 *
 * @author sonho
 */
public class UserDAO extends DAO {

    public static UserDTO getUser(String email) throws SQLException, NamingException {
        UserDTO result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT Avatar, Name, RoleID FROM tblUsers WHERE Email = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = new UserDTO(email, rs.getString("Avatar"), rs.getString("Name"), rs.getString("RoleID"));
                }
            }
        } catch (SQLException ex) {
            log.error("Error at getUser(String email): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getUser(String email): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean activeAccountUser(String email) throws SQLException, NamingException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String url = "UPDATE tblUsers SET  StatusID = ? WHERE Email = ? ";
                ps = cnn.prepareStatement(url);
                ps.setString(1, "1");
                ps.setString(2, email);
                ps.executeUpdate();
                result = true;
            }
        } catch (SQLException ex) {
            log.error("Error at activeAccountUser(String email): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at activeAccountUser(String email): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean updateAvatar(String avatar, String email) throws NamingException, SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "UPDATE tblUsers SET Avatar = ? WHERE Email = ? ";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, avatar);
                ps.setString(2, email);
                ps.executeUpdate();
                result = true;
            }
        } catch (SQLException ex) {
            log.error("Error at updateAvatar(String avatar, String email): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at updateAvatar(String avatar, String email): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean updateUser(UserDTO dto) throws SQLException, NamingException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String url = "UPDATE tblUsers SET  Name = ?, Password = ?, RoleID = ?, StatusID = ?, Avatar = ? WHERE Email = ? ";
                ps = cnn.prepareStatement(url);
                ps.setString(1, dto.getName());
                ps.setString(2, dto.getPassword());
                ps.setString(3, dto.getRoleID());
                ps.setString(4, dto.getStatusID());
                ps.setString(5, dto.getAvatar());
                ps.setString(6, dto.getEmail());
                ps.executeUpdate();
                result = true;
            }
        } catch (SQLException ex) {
            log.error("Error at updateUser(UserDTO dto): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at updateUser(UserDTO dto): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static UserDTO checkLogin(String email, String pass) throws SQLException, NamingException {
        UserDTO result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String url = "SELECT Avatar, Name, RoleID, StatusID FROM tblUsers WHERE Email = ? AND Password = ?";
                ps = cnn.prepareStatement(url);
                ps.setString(1, email);
                ps.setString(2, pass);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = new UserDTO(email, rs.getString("Avatar"), rs.getString("Name"),
                            rs.getString("RoleID"), rs.getString("StatusID"));
                }
            }
        } catch (SQLException ex) {
            log.error("Error at checkLogin(String email, String pass): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at checkLogin(String email, String pass): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static String getAvatarUser(String email) throws SQLException, NamingException {
        String result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT Avatar FROM tblUsers WHERE Email = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("Avatar");
                }
            }
        } catch (SQLException ex) {
            log.error("Error at getAvatarUser(String email): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getAvatarUser(String email): " + ex.toString(), "Error!!");
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
                String url = "SELECT Avatar, Name, RoleID, StatusID FROM tblUsers WHERE Email = ?";
                ps = cnn.prepareStatement(url);

                ps.setString(1, email);
                rs = ps.executeQuery();

                if (rs.next()) {
                    result = new UserDTO(email, rs.getString("Avatar"), rs.getString("Name"),
                            rs.getString("RoleID"), rs.getString("StatusID"));
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

    public static boolean insertAUser(UserDTO dto) throws SQLException, SQLException, NamingException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String url = "INSERT INTO tblUsers (Email, Avatar, Name, Password, RoleID, StatusID)"
                        + " VALUES (?,?,?,?,?,?)";
                ps = cnn.prepareStatement(url);
                ps.setString(1, dto.getEmail());
                ps.setString(2, dto.getAvatar());
                ps.setString(3, dto.getName());
                ps.setString(4, dto.getPassword());
                ps.setString(5, dto.getRoleID());
                ps.setString(6, dto.getStatusID());
                ps.executeUpdate();
                result = true;
            }
        } catch (SQLException ex) {
            log.error("Error at insertAUser(UserDTO dto): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at insertAUser(UserDTO dto): " + ex.toString(), "Error!!");
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
