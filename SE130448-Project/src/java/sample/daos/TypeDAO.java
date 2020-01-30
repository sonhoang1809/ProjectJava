/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.dtos.TypeDTO;
import sample.utils.DBUtils;

/**
 *
 * @author sonho
 */
public class TypeDAO extends DAO {

    public static List<TypeDTO> getAllListType() throws SQLException {
        List<TypeDTO> listType = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT TypeID, TypeName FROM tblTypes";
                ps = cnn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (listType == null) {
                        listType = new ArrayList<>();
                    }
                    listType.add(new TypeDTO(rs.getString("TypeID"), rs.getString("TypeName")));
                }
            }
        } catch (Exception ex) {

        } finally {
            closeConnection();
        }
        return listType;
    }

    public static String getTypeNameByTypeId(String typeId) throws SQLException {
        String result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT TypeName FROM tblTypes WHERE TypeID = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, typeId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("TypeName");
                }
            }
        } catch (Exception ex) {

        } finally {
            closeConnection();
        }
        return result;
    }
}
