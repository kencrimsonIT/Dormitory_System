package com.dormiroty.dao.impl;

import com.dormiroty.dao.ThanhToanDAO;
import com.dormiroty.database.DBConnect;
import com.dormiroty.entity.ThanhToan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ThanhToanDAOImpl implements ThanhToanDAO {

    private ThanhToan mapResultSetToEntity(ResultSet rs) throws SQLException {
        ThanhToan tt = new ThanhToan();
        tt.setMaHD(rs.getString("MaHD").trim());

        Timestamp ts = rs.getTimestamp("NgayThanhToan");
        if (ts != null) {
            tt.setNgayThanhToan(ts.toLocalDateTime());
        }
        return tt;
    }

    @Override
    public List<ThanhToan> findAll() {
        List<ThanhToan> list = new ArrayList<>();
        String sql = "SELECT * FROM ThanhToan";
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
    public ThanhToan findById(String maHD) {
        String sql = "SELECT * FROM ThanhToan WHERE MaHD = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maHD);
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
    public boolean save(ThanhToan tt) {
        String sql = "INSERT INTO ThanhToan(MaHD, NgayThanhToan) VALUES (?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tt.getMaHD());
            ps.setTimestamp(2, tt.getNgayThanhToan() != null ? Timestamp.valueOf(tt.getNgayThanhToan()) : null);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ThanhToan tt) {
        String sql = "UPDATE ThanhToan SET NgayThanhToan=? WHERE MaHD=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, tt.getNgayThanhToan() != null ? Timestamp.valueOf(tt.getNgayThanhToan()) : null);
            ps.setString(2, tt.getMaHD());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String maHD) {
        String sql = "DELETE FROM ThanhToan WHERE MaHD=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maHD);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}