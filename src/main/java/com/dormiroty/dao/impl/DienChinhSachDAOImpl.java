package com.dormiroty.dao.impl;

import com.dormiroty.dao.DienChinhSachDAO;
import com.dormiroty.database.DBConnect;
import com.dormiroty.entity.DienChinhSach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DienChinhSachDAOImpl implements DienChinhSachDAO {

    private DienChinhSach mapResultSetToEntity(ResultSet rs) throws SQLException {
        DienChinhSach dcs = new DienChinhSach();
        dcs.setMaDCS(rs.getString("MaDCS").trim());
        dcs.setTenDCS(rs.getString("TenDCS"));
        dcs.setMucNienGiam(rs.getString("MucNienGiam"));
        return dcs;
    }

    @Override
    public List<DienChinhSach> findAll() {
        List<DienChinhSach> list = new ArrayList<>();
        String sql = "SELECT * FROM DienChinhSach";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public DienChinhSach findById(String maDien) {
        String sql = "SELECT * FROM DienChinhSach WHERE MaDCS = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maDien);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToEntity(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(DienChinhSach dcs) {
        String sql = "INSERT INTO DienChinhSach(MaDCS, TenDCS, MucNienGiam) VALUES (?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dcs.getMaDCS());
            ps.setString(2, dcs.getTenDCS());
            ps.setString(3, dcs.getMucNienGiam());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(DienChinhSach dcs) {
        String sql = "UPDATE DienChinhSach SET TenDCS=?, MucNienGiam=? WHERE MaDCS=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dcs.getTenDCS());
            ps.setString(2, dcs.getMucNienGiam());
            ps.setString(3, dcs.getMaDCS());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String maDien) {
        String sql = "DELETE FROM DienChinhSach WHERE MaDCS=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maDien);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}