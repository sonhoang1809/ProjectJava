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
import sample.dtos.CategoryDTO;
import sample.dtos.FoodDTO;
import sample.mails.SendMailSSL;
import sample.supports.Support;
import static sample.utils.DBUtils.getConnection;

/**
 *
 * @author sonho
 */
public class FoodDAO extends DAO {
    
    

    public static FoodDTO getFoodDTO(String idFood) throws SQLException {
        FoodDTO result = null;
        try {
            cnn = getConnection();
            if (cnn != null) {
                String sql = "SELECT ImgFood, NameFood, Description, Quantity, Price, CategoryID, CreateDate, StatusCode"
                        + " FROM tblFoods"
                        + " WHERE IdFood = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, idFood);
                rs = ps.executeQuery();
                if (rs.next()) {

                    result = new FoodDTO(idFood, rs.getString("ImgFood"),
                            rs.getString("NameFood"), rs.getString("Description"),
                            rs.getInt("Quantity"), rs.getFloat("Price"),
                            rs.getString("CategoryID"), rs.getString("CreateDate"), rs.getInt("StatusCode"));
                }
            }
        } catch (SQLException | NamingException ex) {
            log.error("Error at getListFood(float priceLower, float priceHigher, int statusCode): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getListFood(float priceLower, float priceHigher, int statusCode): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<FoodDTO> getListFood(float priceLower, float priceHigher, int statusCode, int pageShow) throws SQLException {
        List<FoodDTO> result = null;
        try {
            cnn = getConnection();
            if (cnn != null) {
                String sql = "SELECT IdFood, ImgFood, NameFood, Description, Quantity, Price, CategoryID, CreateDate, StatusCode"
                        + " FROM tblFoods"
                        + " WHERE StatusCode = ? AND Quantity > 0 AND Price BETWEEN ? AND ? "
                        + " ORDER BY CreateDate"
                        + " OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, statusCode);
                ps.setFloat(2, priceHigher);
                ps.setFloat(3, priceLower);
                int get = (pageShow - 1) * 20;
                ps.setInt(4, get);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new FoodDTO(rs.getString("IdFood"), rs.getString("ImgFood"),
                            rs.getString("NameFood"), rs.getString("Description"),
                            rs.getInt("Quantity"), rs.getFloat("Price"),
                            rs.getString("CategoryID"), rs.getString("CreateDate"), statusCode));
                }
            }
        } catch (SQLException | NamingException ex) {
            log.error("Error at getListFood(float priceLower, float priceHigher, int statusCode): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getListFood(float priceLower, float priceHigher, int statusCode): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static int getNumberFood(float priceLower, float priceHigher, int statusCode) throws NamingException, SQLException {
        int result = 0;
        try {
            cnn = getConnection();
            if (cnn != null) {
                String sql = "SELECT IdFood FROM tblFoods"
                        + " WHERE StatusCode = ?"
                        + " AND Price BETWEEN ? AND ?";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, statusCode);
                ps.setFloat(2, priceHigher);
                ps.setFloat(3, priceLower);
                rs = ps.executeQuery();
                while (rs.next()) {
                    result++;
                }
            }
        } catch (SQLException ex) {
            log.error("Error at getNumberFood(float priceLower, float priceHigher, int statusCode): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getNumberFood(float priceLower, float priceHigher, int statusCode): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<FoodDTO> getListFood(float priceLower, float priceHigher, int statusCode, int pageShow, String searchName) throws SQLException {
        List<FoodDTO> result = null;
        try {
            cnn = getConnection();
            if (cnn != null) {
                String sql = "SELECT IdFood, ImgFood, NameFood, Description, Quantity, Price, CategoryID, CreateDate, StatusCode"
                        + " FROM tblFoods"
                        + " WHERE StatusCode = ? AND Quantity > 0 AND Price BETWEEN ? AND ? AND NameFood LIKE ?"
                        + " ORDER BY CreateDate"
                        + " OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, statusCode);
                ps.setFloat(2, priceHigher);
                ps.setFloat(3, priceLower);
                ps.setString(4, "%" + searchName + "%");
                int get = (pageShow - 1) * 20;
                ps.setInt(5, get);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new FoodDTO(rs.getString("IdFood"), rs.getString("ImgFood"),
                            rs.getString("NameFood"), rs.getString("Description"),
                            rs.getInt("Quantity"), rs.getFloat("Price"),
                            rs.getString("CategoryID"), rs.getString("CreateDate"), statusCode));
                }
            }
        } catch (SQLException | NamingException ex) {
            log.error("Error at getListFood(float priceLower, float priceHigher, int statusCode): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getListFood(float priceLower, float priceHigher, int statusCode): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static int getNumberFood(float priceLower, float priceHigher, int statusCode, String searchName) throws NamingException, SQLException {
        int result = 0;
        try {
            cnn = getConnection();
            if (cnn != null) {
                String sql = "SELECT IdFood FROM tblFoods"
                        + " WHERE StatusCode = ?"
                        + " AND Price BETWEEN ? AND ?"
                        + " AND NameFood LIKE ?";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, statusCode);
                ps.setFloat(2, priceHigher);
                ps.setFloat(3, priceLower);
                ps.setString(4, "%" + searchName + "%");
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

    public static List<FoodDTO> getListFood(float priceLower, float priceHigher, int statusCode, int pageShow, String categoryID, String searchName) throws SQLException {
        List<FoodDTO> result = null;
        try {
            cnn = getConnection();
            if (cnn != null) {
                String sql = "SELECT IdFood, ImgFood, NameFood, Description, Quantity, Price, CategoryID, CreateDate, StatusCode"
                        + " FROM tblFoods"
                        + " WHERE StatusCode = ? AND Quantity > 0 AND Price BETWEEN ? AND ? AND NameFood LIKE ? AND CategoryID = ?"
                        + " ORDER BY CreateDate"
                        + " OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, statusCode);
                ps.setFloat(2, priceHigher);
                ps.setFloat(3, priceLower);
                ps.setString(4, "%" + searchName + "%");
                ps.setString(5, categoryID);
                int get = (pageShow - 1) * 20;
                ps.setInt(6, get);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new FoodDTO(rs.getString("IdFood"), rs.getString("ImgFood"),
                            rs.getString("NameFood"), rs.getString("Description"),
                            rs.getInt("Quantity"), rs.getFloat("Price"),
                            rs.getString("CategoryID"), rs.getString("CreateDate"), statusCode));
                }
            }
        } catch (SQLException | NamingException ex) {
            log.error("Error at getListFood(float priceLower, float priceHigher, int statusCode): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getListFood(float priceLower, float priceHigher, int statusCode): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static int getNumberFood(float priceLower, float priceHigher, int statusCode, String categoryID, String searchName) throws NamingException, SQLException {
        int result = 0;
        try {
            cnn = getConnection();
            if (cnn != null) {
                String sql = "SELECT IdFood FROM tblFoods"
                        + " WHERE StatusCode = ?"
                        + " AND Price BETWEEN ? AND ?"
                        + " AND NameFood LIKE ? AND CategoryID = ?";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, statusCode);
                ps.setFloat(2, priceHigher);
                ps.setFloat(3, priceLower);
                ps.setString(4, "%" + searchName + "%");
                ps.setString(5, categoryID);
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

    public static List<FoodDTO> getListFood(float priceLower, float priceHigher, int statusCode, int pageShow, List<CategoryDTO> listCategory) throws SQLException {
        List<FoodDTO> result = null;
        try {
            cnn = getConnection();
            if (cnn != null) {

                String sql = "SELECT IdFood, ImgFood, NameFood, Description, Quantity, Price, CategoryID, CreateDate, StatusCode"
                        + " FROM tblFoods"
                        + " WHERE StatusCode = ? AND Quantity > 0 AND Price BETWEEN ? AND ? AND " + Support.getSQL(listCategory)
                        + " ORDER BY CreateDate"
                        + " OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, statusCode);
                ps.setFloat(2, priceHigher);
                ps.setFloat(3, priceLower);
                for (int i = 4; i < listCategory.size() + 4; i++) {
                    ps.setString(i, listCategory.get(i - 4).getCategoryID());
                }
                int get = (pageShow - 1) * 20;
                ps.setInt(4 + listCategory.size(), get);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new FoodDTO(rs.getString("IdFood"), rs.getString("ImgFood"),
                            rs.getString("NameFood"), rs.getString("Description"),
                            rs.getInt("Quantity"), rs.getFloat("Price"),
                            rs.getString("CategoryID"), rs.getString("CreateDate"), statusCode));
                }
            }
        } catch (Exception ex) {
            log.error("Error at getListFood(float priceLower, float priceHigher, int statusCode, int pageShow, List<CategoryDTO> listCategory): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getListFood(float priceLower, float priceHigher, int statusCode, int pageShow, List<CategoryDTO> listCategory): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static int getNumberFood(float priceLower, float priceHigher, int statusCode, List<CategoryDTO> listCategory) throws NamingException, SQLException {
        int result = 0;
        try {
            cnn = getConnection();
            if (cnn != null) {
                String sql = "SELECT IdFood FROM tblFoods"
                        + " WHERE StatusCode = ?"
                        + " AND Price BETWEEN ? AND ?"
                        + " AND " + Support.getSQL(listCategory);
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, statusCode);
                ps.setFloat(2, priceHigher);
                ps.setFloat(3, priceLower);
                for (int i = 4; i < listCategory.size() + 4; i++) {
                    ps.setString(i, listCategory.get(i - 4).getCategoryID());
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = result + 1;
                }
            }
        } catch (SQLException ex) {
            log.error("Error at getNumberFood(float priceLower, float priceHigher, int statusCode, List<CategoryDTO> listCategory): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getNumberFood(float priceLower, float priceHigher, int statusCode, List<CategoryDTO> listCategory): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<FoodDTO> getListFood(float priceLower, float priceHigher, String categoryID, int statusCode, int pageShow) throws SQLException {
        List<FoodDTO> result = null;
        try {
            cnn = getConnection();
            if (cnn != null) {

                String sql = "SELECT IdFood, ImgFood, NameFood, Description, Quantity, Price, CategoryID, CreateDate, StatusCode"
                        + " FROM tblFoods"
                        + " WHERE StatusCode = ? AND Quantity > 0 AND Price BETWEEN ? AND ? AND CategoryID = ? "
                        + " ORDER BY CreateDate"
                        + " OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, statusCode);
                ps.setFloat(2, priceHigher);
                ps.setFloat(3, priceLower);
                ps.setString(4, categoryID);
                int get = (pageShow - 1) * 20;
                ps.setInt(5, get);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new FoodDTO(rs.getString("IdFood"), rs.getString("ImgFood"),
                            rs.getString("NameFood"), rs.getString("Description"),
                            rs.getInt("Quantity"), rs.getFloat("Price"),
                            rs.getString("CategoryID"), rs.getString("CreateDate"), statusCode));
                }
            }
        } catch (Exception ex) {
            log.error("Error at getListFood(float priceLower, float priceHigher, int statusCode, int pageShow, List<CategoryDTO> listCategory): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getListFood(float priceLower, float priceHigher, int statusCode, int pageShow, List<CategoryDTO> listCategory): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static int getNumberFood(float priceLower, float priceHigher, String categoryID, int statusCode) throws SQLException {
        int result = 0;
        try {
            cnn = getConnection();
            if (cnn != null) {
                String sql = "SELECT IdFood FROM tblFoods"
                        + " WHERE StatusCode = ?"
                        + " AND Price BETWEEN ? AND ?"
                        + " AND CategoryID = ? ";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, statusCode);
                ps.setFloat(2, priceHigher);
                ps.setFloat(3, priceLower);
                ps.setString(4, categoryID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = result + 1;
                }
            }
        } catch (SQLException | NamingException ex) {
            log.error("Error at getNumberFood(float priceLower, float priceHigher, int statusCode, List<CategoryDTO> listCategory): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getNumberFood(float priceLower, float priceHigher, int statusCode, List<CategoryDTO> listCategory): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<FoodDTO> getListFood(float priceLower, float priceHigher, int statusCode, int pageShow, List<CategoryDTO> listCategory, String searchName) throws SQLException {
        List<FoodDTO> result = null;
        try {
            cnn = getConnection();
            if (cnn != null) {

                String sql = "SELECT IdFood, ImgFood, NameFood, Description, Quantity, Price, CategoryID, CreateDate, StatusCode"
                        + " FROM tblFoods"
                        + " WHERE StatusCode = ? AND Quantity > 0 AND Price BETWEEN ? AND ? AND NameFood LIKE ? AND " + Support.getSQL(listCategory)
                        + " ORDER BY CreateDate"
                        + " OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, statusCode);
                ps.setFloat(2, priceHigher);
                ps.setFloat(3, priceLower);
                ps.setString(4, "%" + searchName + "%");
                for (int i = 5; i < listCategory.size() + 5; i++) {
                    ps.setString(i, listCategory.get(i - 5).getCategoryID());
                }
                int get = (pageShow - 1) * 20;
                ps.setInt(5 + listCategory.size(), get);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new FoodDTO(rs.getString("IdFood"), rs.getString("ImgFood"),
                            rs.getString("NameFood"), rs.getString("Description"),
                            rs.getInt("Quantity"), rs.getFloat("Price"),
                            rs.getString("CategoryID"), rs.getString("CreateDate"), statusCode));
                }
            }
        } catch (SQLException | NamingException ex) {
            log.error("Error at getListFood(float priceLower, float priceHigher, int statusCode, int pageShow, List<CategoryDTO> listCategory): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getListFood(float priceLower, float priceHigher, int statusCode, int pageShow, List<CategoryDTO> listCategory): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static int getNumberFood(float priceLower, float priceHigher, int statusCode, List<CategoryDTO> listCategory, String searchName) throws NamingException, SQLException {
        int result = 0;
        try {
            cnn = getConnection();
            if (cnn != null) {
                String sql = "SELECT IdFood FROM tblFoods"
                        + " WHERE StatusCode = ?"
                        + " AND Price BETWEEN ? AND ? AND NameFood LIKE ?"
                        + " AND " + Support.getSQL(listCategory);
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, statusCode);
                ps.setFloat(2, priceHigher);
                ps.setFloat(3, priceLower);
                ps.setString(4, "%" + searchName + "%");
                for (int i = 5; i < listCategory.size() + 5; i++) {
                    ps.setString(i, listCategory.get(i - 5).getCategoryID());
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    result = result + 1;
                }
            }
        } catch (SQLException ex) {
            log.error("Error at getNumberFood(float priceLower, float priceHigher, int statusCode, List<CategoryDTO> listCategory): " + ex.toString());
            SendMailSSL.sendToAdmin("Error at getNumberFood(float priceLower, float priceHigher, int statusCode, List<CategoryDTO> listCategory): " + ex.toString(), "Error!!");
        } finally {
            closeConnection();
        }
        return result;
    }

    public static int getNumberArticle(String searchTitle) throws NamingException, SQLException {
        int result = 0;
        try {
            cnn = getConnection();
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
            cnn = getConnection();
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

}
