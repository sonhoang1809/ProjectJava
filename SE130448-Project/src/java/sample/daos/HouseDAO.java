/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.dtos.HouseDTO;
import sample.utils.DBUtils;

/**
 *
 * @author sonho
 */
public class HouseDAO extends DAO {

    public static boolean checkHouseIsSold(String idHouse) throws SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT StatusCode FROM tblHouses WHERE IdHouse = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, idHouse);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int n = rs.getInt("StatusCode");
                    if (n == 0) {
                        result = true;
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;

    }

    public static String getIdHouse() throws SQLException {
        String result = null;
        int n = 0;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdHouse FROM tblHouses";
                ps = cnn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    n++;
                }
                n++;
                result = "h" + n;
            }
        } catch (ClassNotFoundException | SQLException ex) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean deleteAHouse(String idHouse) throws SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "UPDATE tblHouses SET IsDelete = ? WHERE IdHouse = ?";
                ps = cnn.prepareStatement(sql);
                ps.setBoolean(1, true);
                ps.setString(2, idHouse);
                ps.executeUpdate();
                result = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean updateHouse(HouseDTO dto) throws SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "UPDATE tblHouses SET PicHouse = ?, Description = ?, Furniture = ?"
                        + " , TypeID = ?, Price = ?, StatusCode = ? WHERE IdHouse = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, dto.getPicHouse());
                ps.setString(2, dto.getDescription());
                ps.setString(3, dto.getFurniture().toString());
                ps.setString(4, dto.getTypeId());
                ps.setFloat(5, dto.getPrice());
                ps.setInt(6, dto.getStatusCode());
                ps.setString(7, dto.getIdHouse());
                ps.executeUpdate();
                result = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean updateHouseStatus(HouseDTO dto) throws SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "UPDATE tblHouses SET StatusCode = ? WHERE IdHouse = ?";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, dto.getStatusCode());
                ps.setString(2, dto.getIdHouse());
                ps.executeUpdate();
                result = true;
            }

        } catch (Exception ex) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public static HouseDTO getPicHouseAndDescriptionById(String idHouse) throws SQLException {
        HouseDTO result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT PicHouse, Description"
                        + " FROM tblHouses WHERE IdHouse = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, idHouse);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = new HouseDTO(idHouse, rs.getString("PicHouse"), rs.getString("Description"));
                }
            }

        } catch (Exception ex) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<HouseDTO> getListHouseRelated(HouseDTO dto, float priceLower, float priceHigher) throws SQLException {
        List<HouseDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdHouse, PicHouse, Description, Furniture, TypeID,"
                        + " Price, IdWay, StatusCode FROM tblHouses WHERE TypeID = ? AND Price BETWEEN ? AND ? AND IdWay = ? AND IsDelete = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, dto.getTypeId());
                ps.setFloat(2, priceHigher);
                ps.setFloat(3, priceLower);
                ps.setString(4, dto.getIdWay());
                ps.setBoolean(5, false);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null && !rs.getString("IdHouse").equalsIgnoreCase(dto.getIdHouse())) {
                        result = new ArrayList<>();
                    }
                    if (!rs.getString("IdHouse").equalsIgnoreCase(dto.getIdHouse())) {
                        result.add(new HouseDTO(rs.getString("IdHouse"), rs.getString("PicHouse"),
                                rs.getString("Description"), rs.getString("Furniture"), rs.getString("TypeID"),
                                rs.getFloat("Price"), rs.getString("IdWay"),
                                rs.getInt("StatusCode")));
                    }
                }
            }

        } catch (Exception ex) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public static HouseDTO getHouseDTOByHouseID(String houseId) throws SQLException {
        HouseDTO result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdHouse, PicHouse, Description, Furniture, TypeID,"
                        + " Price, IdWay, StatusCode FROM tblHouses WHERE IdHouse = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, houseId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = new HouseDTO(rs.getString("IdHouse"), rs.getString("PicHouse"),
                            rs.getString("Description"), rs.getString("Furniture"), rs.getString("TypeID"),
                            rs.getFloat("Price"), rs.getString("IdWay"),
                            rs.getInt("StatusCode"));
                }
            }

        } catch (Exception ex) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<HouseDTO> getListHouseByIdWayAndTypeId(String idWay, String typeId, float priceLower, float priceHigher) throws SQLException {
        List<HouseDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdHouse, PicHouse, Description, Furniture, TypeID,"
                        + " Price, IdWay, StatusCode FROM tblHouses WHERE TypeID = ? AND Price BETWEEN ? AND ? AND IdWay = ? AND IsDelete = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, typeId);
                ps.setFloat(2, priceHigher);
                ps.setFloat(3, priceLower);
                ps.setString(4, idWay);
                ps.setBoolean(5, false);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new HouseDTO(rs.getString("IdHouse"), rs.getString("PicHouse"),
                            rs.getString("Description"), rs.getString("Furniture"), rs.getString("TypeID"),
                            rs.getFloat("Price"), rs.getString("IdWay"),
                            rs.getInt("StatusCode")));
                }
            }

        } catch (Exception ex) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<HouseDTO> getListHouseByTypeIdAndListWayId(float priceLower, float priceHigher,
            String typeId, List<String> listIdWay) throws SQLException {
        List<HouseDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdHouse, PicHouse, Description, Furniture, TypeID,"
                        + " Price, IdWay, StatusCode FROM tblHouses WHERE TypeID = ? AND Price BETWEEN ? AND ? AND IdWay = ? AND IsDelete = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, typeId);
                ps.setFloat(2, priceHigher);
                ps.setFloat(3, priceLower);
                ps.setBoolean(5, false);
                for (String x : listIdWay) {
                    ps.setString(4, x);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        if (result == null) {
                            result = new ArrayList<>();
                        }
                        result.add(new HouseDTO(rs.getString("IdHouse"), rs.getString("PicHouse"),
                                rs.getString("Description"), rs.getString("Furniture"), rs.getString("TypeID"),
                                rs.getFloat("Price"), rs.getString("IdWay"),
                                rs.getInt("StatusCode")));
                    }
                }
            }

        } catch (Exception ex) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<HouseDTO> getListHouseByTypeId(String typeId, float priceLower, float priceHigher) throws SQLException {
        List<HouseDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdHouse, PicHouse, Description, Furniture, TypeID,"
                        + " Price, IdWay, StatusCode FROM tblHouses WHERE TypeID = ? AND Price BETWEEN ? AND ? AND IsDelete = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, typeId);
                ps.setFloat(2, priceHigher);
                ps.setFloat(3, priceLower);
                ps.setBoolean(4, false);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new HouseDTO(rs.getString("IdHouse"), rs.getString("PicHouse"),
                            rs.getString("Description"), rs.getString("Furniture"), rs.getString("TypeID"),
                            rs.getFloat("Price"), rs.getString("IdWay"),
                            rs.getInt("StatusCode")));
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<HouseDTO> getListHouseByIdWay(String idWay, float priceLower, float priceHigher) throws SQLException {
        List<HouseDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdHouse, PicHouse, Description, Furniture, TypeID, Price, IdWay,"
                        + " StatusCode FROM tblHouses WHERE IdWay = ? AND Price BETWEEN ? AND ? AND IsDelete = ?";
                ps = cnn.prepareStatement(sql);
                ps.setString(1, idWay);
                ps.setFloat(2, priceHigher);
                ps.setFloat(3, priceLower);
                ps.setBoolean(4, false);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new HouseDTO(rs.getString("IdHouse"), rs.getString("PicHouse"),
                            rs.getString("Description"), rs.getString("Furniture"), rs.getString("TypeID"),
                            rs.getFloat("Price"), rs.getString("IdWay"),
                            rs.getInt("StatusCode")));
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<HouseDTO> getListHouseWithListWay(List<String> listWayId, float priceLower, float priceHigher) throws SQLException {
        List<HouseDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdHouse, PicHouse, Description, Furniture, TypeID, Price, IdWay,"
                        + " StatusCode FROM tblHouses WHERE IdWay = ? AND Price BETWEEN ? AND ? AND IsDelete = ?";
                ps = cnn.prepareStatement(sql);
                ps.setFloat(2, priceHigher);
                ps.setFloat(3, priceLower);
                ps.setBoolean(4, false);
                for (String x : listWayId) {
                    ps.setString(1, x);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        if (result == null) {
                            result = new ArrayList<>();
                        }
                        result.add(new HouseDTO(rs.getString("IdHouse"), rs.getString("PicHouse"),
                                rs.getString("Description"), rs.getString("Furniture"), rs.getString("TypeID"),
                                rs.getFloat("Price"), rs.getString("IdWay"),
                                rs.getInt("StatusCode")));
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<HouseDTO> getListHouseWithPriceBetween(float priceLower, float priceHigher) throws SQLException {
        List<HouseDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdHouse, PicHouse, Description, Furniture, TypeID, Price, IdWay, StatusCode FROM tblHouses WHERE Price"
                        + " BETWEEN ? AND ? AND IsDelete = ?";
                ps = cnn.prepareStatement(sql);
                ps.setFloat(1, priceHigher);
                ps.setFloat(2, priceLower);
                ps.setBoolean(3, false);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new HouseDTO(rs.getString("IdHouse"), rs.getString("PicHouse"),
                            rs.getString("Description"), rs.getString("Furniture"), rs.getString("TypeID"),
                            rs.getFloat("Price"), rs.getString("IdWay"),
                            rs.getInt("StatusCode")));
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static List<HouseDTO> getListAllHouse() throws SQLException {
        List<HouseDTO> result = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT IdHouse, PicHouse, Description, Furniture, TypeID, Price, IdWay, StatusCode FROM tblHouses WHERE IsDelete = ?";
                ps = cnn.prepareStatement(sql);
                ps.setBoolean(1, false);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new HouseDTO(rs.getString("IdHouse"), rs.getString("PicHouse"),
                            rs.getString("Description"), rs.getString("Furniture"), rs.getString("TypeID"),
                            rs.getFloat("Price"), rs.getString("IdWay"),
                            rs.getInt("StatusCode")));
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean insertAHouse(HouseDTO dto) throws SQLException, SQLException {
        boolean result = false;
        try {
            cnn = DBUtils.getConnection();
            cnn.setAutoCommit(false);
            if (cnn != null) {
                String url = "INSERT INTO tblHouses (IdHouse, PicHouse, Description,Furniture,TypeID, Price, IdWay, StatusCode, IsDelete) "
                        + " VALUES (?,?,?,?,?,?,?,?,?)";
                ps = cnn.prepareStatement(url);
                ps.setString(1, dto.getIdHouse());
                ps.setString(2, dto.getPicHouse());
                ps.setString(3, dto.getDescription());
                ps.setString(4, dto.getFurniture().toString());
                ps.setString(5, dto.getTypeId());
                ps.setFloat(6, dto.getPrice());
                ps.setString(7, dto.getIdWay());
                ps.setInt(8, dto.getStatusCode());
                ps.setBoolean(9, false);
                ps.executeUpdate();
                result = true;
                cnn.commit();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            cnn.rollback();
        } finally {
            closeConnection();
        }
        return result;
    }

}
