/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.SQLException;
import sample.dtos.PropertyDTO;
import sample.utils.DBUtils;

/**
 *
 * @author sonho
 */
public class PropertyDAO extends DAO {

    public static PropertyDTO getPropertyByIdHouse(String idHouse) throws SQLException {
        PropertyDTO result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdHouse, MediaRoom, FamilyRoom, GymRoom, Library,"
                        + " Pool, TV, Kitchen,LivingRoom,Garden FROM tblProperties WHERE IdHouse = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, idHouse);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = new PropertyDTO(rs.getString("IdHouse"), rs.getBoolean("MediaRoom"),
                            rs.getBoolean("FamilyRoom"), rs.getBoolean("GymRoom"), rs.getBoolean("Library"),
                            rs.getBoolean("Pool"), rs.getBoolean("TV"),
                            rs.getBoolean("Kitchen"), rs.getBoolean("LivingRoom"), rs.getBoolean("Garden"));
                }
            }

        } catch (Exception ex) {
        } finally {
            closeConnection();
        }
        return result;
    }
}
