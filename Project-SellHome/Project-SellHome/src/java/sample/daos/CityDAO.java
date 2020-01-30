/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.dtos.CityDTO;
import sample.utils.DBUtils;

/**
 *
 * @author sonho
 */
public class CityDAO extends DAO {

    public static List<CityDTO> getAllListCity() throws SQLException {
        List<CityDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdCity, NameCity FROM tblCities";
                ps = cnn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new CityDTO(rs.getString("IdCity"), rs.getString("NameCity")));
                }
            }
        } catch (Exception ex) {
        } finally {
            closeConnection();
        }

        return result;
    }

    public static String getCityNameByCityId(String idCity) throws SQLException {
        String result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT NameCity FROM tblCities WHERE idCity = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, idCity);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("NameCity");
                }
            }
        } catch (Exception ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static String getIdCityByName(String name) throws ClassNotFoundException, SQLException {
        String idCity = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT idCity FROM tblCities WHERE NameCity = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, name);
                rs = ps.executeQuery();
                if (rs.next()) {
                    idCity = rs.getString("idCity");
                }
            }
        } catch (Exception ex) {

        } finally {
            closeConnection();
        }
        return idCity;
    }
}
