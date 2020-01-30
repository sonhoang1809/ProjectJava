/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.dtos.UserDTO;
import sample.utils.DBUtils;

/**
 *
 * @author sonho
 */
public class UserDAO extends DAO {

    public static boolean updateUser(UserDTO dto) throws SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String url = "UPDATE tblUsers SET UserName = ?, Email = ?, Password = ?, RoleID = ? WHERE UserID = ? ";
                ps = cnn.prepareStatement(url);
                ps.setString(1, dto.getUserName());
                ps.setString(2, dto.getEmail());
                ps.setString(3, dto.getPassword());
                ps.setString(4, dto.getRoleID());
                ps.setString(5, dto.getUserID());
                ps.executeUpdate();
                result = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {

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
                String url = "SELECT UserName,RoleID, Email FROM tblUsers WHERE userID = ? AND password = ? AND Invalid = ?";
                ps = cnn.prepareStatement(url);
                ps.setString(1, userID);
                ps.setString(2, pass);
                ps.setBoolean(3, false);
                rs = ps.executeQuery();
                if (rs.next()) {
                    if (rs.getString("Email") == null) {
                        result = new UserDTO(rs.getString("UserName"), rs.getString("RoleID"));
                        //    result = new UserDTO(userID, rs.getString("UserName"), pass, rs.getString("RoleID"));
                    } else {
                        result = new UserDTO(userID, rs.getString("UserName"), rs.getString("Email"), pass, rs.getString("RoleID"));
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<UserDTO> getAllListUser() throws SQLException {
        List<UserDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String url = "SELECT UserID,UserName,Email, Password,RoleID FROM tblUsers WHERE Invalid = ?";
                ps = cnn.prepareStatement(url);
                ps.setBoolean(1, false);
                rs = ps.executeQuery();

                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new UserDTO(rs.getString("UserID"),
                            rs.getString("UserName"), rs.getString("Email"), rs.getString("Password"), rs.getString("RoleID")));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<UserDTO> searchByLikeName(String search) throws SQLException {
        List<UserDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String url = "SELECT UserID, UserName, Email, Password,RoleID FROM tblUsers WHERE userName LIKE ? AND Invalid = ?";
                ps = cnn.prepareStatement(url);
                ps.setString(1, "%" + search + "%");
                ps.setBoolean(2, false);
                rs = ps.executeQuery();
                result = new ArrayList<>();
                while (rs.next()) {
                    result.add(new UserDTO(rs.getString("UserID"),
                            rs.getString("UserName"), rs.getString("Email"), rs.getString("Password"), rs.getString("RoleID")));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean checkValidUserID(String userID) throws SQLException {
        boolean result = true;
        try {
            cnn = DBUtils.getConnection();

            if (cnn != null) {
                String url = "SELECT UserID FROM tblUsers Where UserID = ?";
                ps = cnn.prepareStatement(url);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = false;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean insertAUser(UserDTO dto) throws SQLException, SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();

            if (cnn != null) {
                String url = "INSERT INTO tblUsers (UserID, UserName, Email, Password, RoleID, Invalid) "
                        + " VALUES (?,?,?,?,?,?)";
                ps = cnn.prepareStatement(url);
                ps.setString(1, dto.getUserID());
                ps.setString(2, dto.getUserName());
                ps.setString(3, dto.getEmail());
                ps.setString(4, dto.getPassword());
                ps.setString(5, dto.getRoleID());
                ps.setBoolean(6, false);
                ps.executeUpdate();
                result = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean deleteAUser(String userID) throws SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null && userID != null) {
                String sql = "UPDATE tblUsers SET Invalid = ? WHERE userID = ?";
                ps = cnn.prepareStatement(sql);
                ps.setBoolean(1, true);
                ps.setString(2, userID);
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
