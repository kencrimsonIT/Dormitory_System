package com.dormiroty.dao.impl;

import com.dormiroty.dao.ViPhamDAO;
import com.dormiroty.database.DBConnect;
import com.dormiroty.entity.ViPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ViPhamDAOImpl implements ViPhamDAO {

    @Override
    public List<ViPham> findAll() {
        List<ViPham> list = new ArrayList<>();
        String sql = "SELECT * FROM ViPham";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ViPham vp = new ViPham();
                vp.setMaLoaiViPham(rs.getString("MaLoaiViPham").trim());
                vp.setTenLoaiViPham(rs.getString("TenLoaiViPham"));
                list.add(vp);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public ViPham findById(String maLoaiViPham) {
        String sql = "SELECT * FROM ViPham WHERE MaLoaiViPham = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maLoaiViPham);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ViPham vp = new ViPham();
                    vp.setMaLoaiViPham(rs.getString("MaLoaiViPham").trim());
                    vp.setTenLoaiViPham(rs.getString("TenLoaiViPham"));
                    return vp; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(ViPham vp) {
        String sql = "INSERT INTO ViPham(MaLoaiViPham, TenLoaiViPham) VALUES (?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vp.getMaLoaiViPham());
            ps.setString(2, vp.getTenLoaiViPham());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean update(ViPham vp) {
        // Chỉ cập nhật TenLoaiViPham dựa trên MaLoaiViPham
        String sql = "UPDATE ViPham SET TenLoaiViPham=? WHERE MaLoaiViPham=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vp.getTenLoaiViPham());
            ps.setString(2, vp.getMaLoaiViPham());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String maLoaiViPham) {
        String sql = "DELETE FROM ViPham WHERE MaLoaiViPham=?";
        try (Connection conn = DBConnect.getConnection();
             // Đã sửa lại lỗi thiếu chữ 'conn.' ở đây
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maLoaiViPham);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}