/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.dtos.WayDTO;
import sample.utils.DBUtils;

/**
 *
 * @author sonho
 */
public class WayDAO extends DAO {
    
    

    public static boolean checkWayBelongToIdCity(String idWay, String idCity) throws SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT NameWay FROM tblWays WHERE IdWay = ? AND IdCity = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, idWay);
                ps.setString(2, idCity);
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

    public static String getWayNameByIdWay(String idWay) throws SQLException {
        String result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT NameWay FROM tblWays WHERE IdWay = ? ";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, idWay);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("NameWay");
                }
            }
        } catch (Exception ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static String getIdCityByIdWay(String idWay) throws SQLException {
        String result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdCity FROM tblWays WHERE IdWay = ? ";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, idWay);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("IdCity");
                }
            }
        } catch (Exception ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<String> getListWayIdByIdCity(String idCity) throws SQLException {
        List<String> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdWay, NameWay FROM tblWays WHERE IdCity = ? ";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, idCity);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(rs.getString("IdWay"));
                }
            }
        } catch (Exception ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<WayDTO> getListWayByIdCity(String idCity) throws SQLException {
        List<WayDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdWay, NameWay FROM tblWays WHERE IdCity = ? ";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, idCity);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new WayDTO(rs.getString("IdWay"), rs.getString("NameWay"), idCity));
                }
            }
        } catch (Exception ex) {

        } finally {
            closeConnection();
        }
        return result;
    }
     public static List<WayDTO> getAllListWay() throws SQLException {
        List<WayDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdWay, NameWay FROM tblWays";
                ps = cnn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new WayDTO(rs.getString("IdWay"), rs.getString("NameWay")));
                }
            }
        } catch (Exception ex) {

        } finally {
            closeConnection();
        }
        return result;
    }
}
