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
                vp.setMaNV(rs.getString("MaNV").trim());
                vp.setMSSV(rs.getInt("MSSV")); // Đã sửa thành getMSSV
                vp.setTenLoaiViPham(rs.getString("TenLoaiViPham"));
                vp.setMaLoaiViPham(rs.getString("MaLoaiViPham").trim());

                Timestamp ts = rs.getTimestamp("NgayViPham");
                if (ts != null) {
                    vp.setNgayviPham(ts.toLocalDateTime());
                }

                vp.setHinhThucXuLi(rs.getString("HinhThucXuLi"));
                list.add(vp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                    vp.setMaNV(rs.getString("MaNV").trim());
                    vp.setMSSV(rs.getInt("MSSV"));
                    vp.setTenLoaiViPham(rs.getString("TenLoaiViPham"));
                    vp.setMaLoaiViPham(rs.getString("MaLoaiViPham").trim());

                    Timestamp ts = rs.getTimestamp("NgayViPham");
                    if (ts != null) {
                        vp.setNgayviPham(ts.toLocalDateTime());
                    }

                    vp.setHinhThucXuLi(rs.getString("HinhThucXuLi"));
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
        String sql = "INSERT INTO ViPham(MaNV, MSSV, TenLoaiViPham, MaLoaiViPham, NgayViPham, HinhThucXuLi) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vp.getMaNV());
            ps.setInt(2, vp.getMSSV());
            ps.setString(3, vp.getTenLoaiViPham());
            ps.setString(4, vp.getMaLoaiViPham());

            if (vp.getNgayviPham() != null) {
                ps.setTimestamp(5, Timestamp.valueOf(vp.getNgayviPham()));
            } else {
                ps.setNull(5, java.sql.Types.TIMESTAMP);
            }

            ps.setString(6, vp.getHinhThucXuLi());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ViPham vp) {
        String sql = "UPDATE ViPham SET MaNV=?, MSSV=?, TenLoaiViPham=?, NgayViPham=?, HinhThucXuLi=? WHERE MaLoaiViPham=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vp.getMaNV());
            ps.setInt(2, vp.getMSSV());
            ps.setString(3, vp.getTenLoaiViPham());

            if (vp.getNgayviPham() != null) {
                ps.setTimestamp(4, Timestamp.valueOf(vp.getNgayviPham()));
            } else {
                ps.setNull(4, java.sql.Types.TIMESTAMP);
            }

            ps.setString(5, vp.getHinhThucXuLi());
            ps.setString(6, vp.getMaLoaiViPham());
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