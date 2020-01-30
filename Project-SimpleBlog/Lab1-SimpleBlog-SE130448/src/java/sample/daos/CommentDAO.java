/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import static sample.daos.DAO.log;
import sample.dtos.CommentDTO;
import sample.mails.SendMailSSL;
import sample.utils.DBUtils;

/**
 *
 * @author sonho
 */
public class CommentDAO extends DAO {

    public static int getNextNumberCommentArticleOfUser(String articleID, String email) throws NamingException, SQLException {
        int result = 1;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT CommentID FROM tblComments "
                        + " WHERE EmailUserComment = ? AND ArticleID = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, articleID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = result + 1;
                }
            }
        } catch (SQLException ex) {
            log.error("Error at getNextNumberCommentArticleOfUser(String articleID, String email): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getNextNumberCommentArticleOfUser(String articleID, String email): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean createAComment(CommentDTO dto) {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "INSERT INTO tblComments(CommentID, CommentContent, EmailUserComment, Date, ArticleID, Author)"
                        + " VALUES(?,?,?,?,?,?)";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, dto.getCommentID());
                ps.setString(2, dto.getContent());
                ps.setString(3, dto.getEmail());
                ps.setString(4, dto.getDateComment());
                ps.setString(5, dto.getArticleID());
                ps.setString(6, dto.getAuthor());
                ps.executeUpdate();
                result = true;
            }
        } catch (SQLException | NamingException ex) {
            log.error("Error at createAComment(CommentDTO dto): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at createAComment(CommentDTO dto): " + ex.toString(), "Error!!");
        }
        return result;
    }

    public static List<CommentDTO> getListCommentByArticleID(String id) throws SQLException, NamingException {
        List<CommentDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT CommentID, CommentContent, EmailUserComment, Date, Author"
                        + " FROM tblComments "
                        + " WHERE ArticleID = ?"
                        + " ORDER BY Date DESC";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new CommentDTO(rs.getString("CommentID"), rs.getString("CommentContent"),
                            rs.getString("EmailUserComment"), rs.getString("Date"), id, rs.getString("Author")));
                }
            }
        } catch (SQLException ex) {
            log.error("Error at getListCommentByArticleID(String id): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getListCommentByArticleID(String id): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }
}
