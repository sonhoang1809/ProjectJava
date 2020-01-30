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
import sample.dtos.ArticleDTO;
import sample.mails.SendMailSSL;
import sample.utils.DBUtils;

/**
 *
 * @author sonho
 */
public class ArticleDAO extends DAO {

    public static boolean deleteArticle(String articleID) throws NamingException, SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "UPDATE tblArticles SET StatusID = ? WHERE ArticleID = ?";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, 2);
                ps.setString(2, articleID);
                ps.executeUpdate();
                result = true;
            }
        } catch (SQLException ex) {
            log.error("Error at deleteArticle(String articleID): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at deleteArticle(String articleID): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean activeArticle(String articleID) throws NamingException, SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "UPDATE tblArticles SET StatusID = ? WHERE ArticleID = ?";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, 1);
                ps.setString(2, articleID);
                ps.executeUpdate();
                result = true;
            }
        } catch (SQLException ex) {
            log.error("Error at activeArticle(String articleID): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at activeArticle(String articleID): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean insertArticle(ArticleDTO dto) throws NamingException, SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "INSERT INTO tblArticles(ArticleID, Title, ShortDescription, Img, PostContent, Author, PostingDate, Email, StatusID)"
                        + " VALUES(?,?,?,?,?,?,?,?,?)";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, dto.getArticleID());
                ps.setString(2, dto.getTitle());
                ps.setString(3, dto.getShortDescription());
                ps.setString(4, dto.getImg());
                ps.setString(5, dto.getPostContent());
                ps.setString(6, dto.getAuthor());
                ps.setString(7, dto.getPostingDate());
                ps.setString(8, dto.getEmail());
                ps.setInt(9, 0);
                ps.executeUpdate();
                result = true;
            }
        } catch (SQLException ex) {
            log.error("Error at insertArticle(ArticleDTO dto): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at insertArticle(ArticleDTO dto): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static int getNextNumberArticleOfUser(String email) throws NamingException, SQLException {
        int result = 1;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT ArticleID FROM tblArticles  "
                        + " WHERE Email = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = result + 1;
                }
            }
        } catch (SQLException ex) {
            log.error("Error at getNextNumberArticleOfUser(String email): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getNextNumberArticleOfUser(String email): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static int getNumberArticle() throws NamingException, SQLException {
        int result = 0;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT ArticleID FROM tblArticles";
                ps = cnn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = result + 1;
                }
            }
        } catch (SQLException ex) {
            log.error("Error at getNumberArticle(): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getNumberArticle(): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static int getNumberArticle(int statusArticleID, String searchTitle) throws NamingException, SQLException {
        int result = 0;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT ArticleID FROM tblArticles  "
                        + " WHERE StatusID = ? AND Title LIKE ?";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, statusArticleID);
                ps.setString(2, "%" + searchTitle + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = result + 1;
                }
            }
        } catch (SQLException ex) {
            log.error("Error at getNumberArticle(int statusArticleID, String searchTitle): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getNumberArticle(int statusArticleID, String searchTitle): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static int getNumberArticle(String searchTitle) throws NamingException, SQLException {
        int result = 0;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT ArticleID FROM tblArticles  "
                        + " WHERE Title LIKE ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, "%" + searchTitle + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = result + 1;
                }
            }
        } catch (SQLException ex) {
            log.error("Error at getNumberArticle(String searchTitle): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getNumberArticle(String searchTitle): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static int getNumberArticle(int statusArticleID) throws NamingException, SQLException {
        int result = 0;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT ArticleID FROM tblArticles  "
                        + " WHERE StatusID = ?";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, statusArticleID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = result + 1;
                }
            }
        } catch (SQLException ex) {
            log.error("Error at getNumberArticle(int statusArticleID): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getNumberArticle(int statusArticleID): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static ArticleDTO getArticleByArticleID(String ID) throws SQLException, NamingException {
        ArticleDTO result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT Title, ShortDescription, Img, PostContent, Author, PostingDate, Email, StatusID"
                        + " FROM tblArticles "
                        + " WHERE ArticleID = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, ID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = new ArticleDTO(ID, rs.getString("Title"), rs.getString("ShortDescription"), rs.getString("Img"),
                            rs.getString("PostContent"), rs.getString("Author"),
                            rs.getString("PostingDate"), rs.getString("Email"), rs.getInt("StatusID"));
                }
            }
        } catch (SQLException ex) {
            log.error("Error at getArticleByArticleID(String ID): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getArticleByArticleID(String ID): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<ArticleDTO> getListArticleByEmail(String email) throws SQLException, NamingException {
        List<ArticleDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT ArticleID, Title, ShortDescription, Img, Author,PostingDate, Email, StatusID"
                        + " FROM tblArticles "
                        + " WHERE Email LIKE ?"
                        + " ORDER BY PostingDate ";
                ps = cnn.prepareStatement(sql);

                ps.setString(1, email);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new ArticleDTO(rs.getString("ArticleID"),
                            rs.getString("Title"), rs.getString("ShortDescription"), rs.getString("Img"), rs.getString("Author"),
                            rs.getString("PostingDate"), rs.getString("Email"), rs.getInt("StatusID")));
                }
            }
        } catch (SQLException ex) {
            log.error("Error at getListArticleByEmail(String email): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getListArticleByEmail(String email): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<ArticleDTO> getListArticleByPage(int page) throws SQLException, NamingException {
        List<ArticleDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String url = "SELECT ArticleID, Title, ShortDescription, Img, Author,PostingDate, Email, StatusID"
                        + " FROM tblArticles "
                        + " ORDER BY PostingDate "
                        + " OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
                ps = cnn.prepareStatement(url);
                int get = (page - 1) * 5;
                ps.setInt(1, get);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new ArticleDTO(rs.getString("ArticleID"),
                            rs.getString("Title"), rs.getString("ShortDescription"), rs.getString("Img"), rs.getString("Author"),
                            rs.getString("PostingDate"), rs.getString("Email"), rs.getInt("StatusID")));
                }
            }
        } catch (SQLException ex) {
            log.error("Error at getListArticleByPage(int page): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getListArticleByPage(int page): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<ArticleDTO> getListArticleByPage(int page, String searchTitle) throws SQLException, NamingException {
        List<ArticleDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String url = "SELECT ArticleID, Title, ShortDescription, Img, Author,PostingDate, Email, StatusID"
                        + " FROM tblArticles "
                        + " WHERE StatusID = ? AND Title LIKE ?"
                        + " ORDER BY PostingDate "
                        + " OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
                ps = cnn.prepareStatement(url);
                int get = (page - 1) * 5;
                ps.setString(1, "1");
                ps.setString(2, "%" + searchTitle + "%");
                ps.setInt(3, get);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new ArticleDTO(rs.getString("ArticleID"),
                            rs.getString("Title"), rs.getString("ShortDescription"), rs.getString("Img"), rs.getString("Author"),
                            rs.getString("PostingDate"), rs.getString("Email"), rs.getInt("StatusID")));
                }
            }
        } catch (SQLException ex) {
            log.error("Error at getListArticleByPage(int page, String searchTitle): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getListArticleByPage(int page, String searchTitle): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<ArticleDTO> getListArticleByPage(int page, int statusArticleID) throws SQLException, NamingException {
        List<ArticleDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String url = "SELECT ArticleID, Title, ShortDescription, Img, Author,PostingDate, Email, StatusID"
                        + " FROM tblArticles "
                        + " WHERE StatusID = ?"
                        + " ORDER BY PostingDate "
                        + " OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
                ps = cnn.prepareStatement(url);
                int get = (page - 1) * 5;
                ps.setInt(1, statusArticleID);
                ps.setInt(2, get);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new ArticleDTO(rs.getString("ArticleID"),
                            rs.getString("Title"), rs.getString("ShortDescription"), rs.getString("Img"), rs.getString("Author"),
                            rs.getString("PostingDate"), rs.getString("Email"), rs.getInt("StatusID")));
                }
            }
        } catch (SQLException ex) {
            log.error("Error at getListArticleByPage(int page, int statusArticleID): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getListArticleByPage(int page, int statusArticleID): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<ArticleDTO> getListArticleByPage(int page, String searchTitle, int statusArticleID) throws SQLException, NamingException {
        List<ArticleDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String url = "SELECT ArticleID, Title, ShortDescription, Img, Author,PostingDate, Email, StatusID"
                        + " FROM tblArticles "
                        + " WHERE StatusID = ? AND Title LIKE ?"
                        + " ORDER BY PostingDate "
                        + " OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
                ps = cnn.prepareStatement(url);
                int get = (page - 1) * 5;
                ps.setInt(1, statusArticleID);
                ps.setString(2, "%" + searchTitle + "%");
                ps.setInt(3, get);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new ArticleDTO(rs.getString("ArticleID"),
                            rs.getString("Title"), rs.getString("ShortDescription"), rs.getString("Img"), rs.getString("Author"),
                            rs.getString("PostingDate"), rs.getString("Email"), rs.getInt("StatusID")));
                }
            }
        } catch (SQLException ex) {
            log.error("Error at getListArticleByPage(int page, String searchTitle, int statusArticleID): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getListArticleByPage(int page, String searchTitle, int statusArticleID): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

}
