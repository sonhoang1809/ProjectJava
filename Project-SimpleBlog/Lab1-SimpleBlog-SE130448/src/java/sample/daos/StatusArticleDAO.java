/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import static sample.daos.DAO.cnn;
import static sample.daos.DAO.log;
import sample.dtos.StatusArticleDTO;
import sample.mails.SendMailSSL;
import sample.utils.DBUtils;

/**
 *
 * @author sonho
 */
public class StatusArticleDAO extends DAO {

    public static List<StatusArticleDTO> getAllListStatusArticle() throws NamingException, SQLException {
        List<StatusArticleDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT StatusID, StatusDetail FROM tblStatusArticle";
                ps = cnn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new StatusArticleDTO(rs.getInt("StatusID"), rs.getString("StatusDetail")));
                }
            }
        } catch (SQLException ex) {
            log.error("Error at getAllListStatusArticle(): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getAllListStatusArticle(): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }
}
