package com.dormiroty.dao.impl;

import com.dormiroty.dao.LS_ViPhamDAO;
import com.dormiroty.database.DBConnect;
import com.dormiroty.entity.LS_ViPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LS_ViPhamDAOImpl implements LS_ViPhamDAO {

    private LS_ViPham mapResultSetToEntity(ResultSet rs) throws SQLException {
        LS_ViPham ls = new LS_ViPham();
        ls.setMaLSViPham(rs.getString("MaLSViPham").trim());
        ls.setMaLoaiViPham(rs.getString("MaLoaiViPham").trim());
        ls.setMssv(rs.getString("MSSV").trim()); // MSSV là String theo Entity mới

        Timestamp ts = rs.getTimestamp("NgayViPham");
        if (ts != null) {
            ls.setNgayViPham(ts.toLocalDateTime());
        }

        ls.setMaNV(rs.getString("MaNV").trim());
        ls.setHinhThucXuLi(rs.getString("HinhThucXuLi"));
        return ls;
    }

    @Override
    public List<LS_ViPham> findAll() {
        List<LS_ViPham> list = new ArrayList<>();
        String sql = "SELECT * FROM LS_ViPham";
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
    public LS_ViPham findById(String maLSViPham) {
        String sql = "SELECT * FROM LS_ViPham WHERE MaLSViPham = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maLSViPham);
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
    public boolean save(LS_ViPham ls) {
        String sql = "INSERT INTO LS_ViPham(MaLSViPham, MaLoaiViPham, MSSV, NgayViPham, MaNV, HinhThucXuLi) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ls.getMaLSViPham());
            ps.setString(2, ls.getMaLoaiViPham());
            ps.setString(3, ls.getMssv());
            ps.setTimestamp(4, ls.getNgayViPham() != null ? Timestamp.valueOf(ls.getNgayViPham()) : null);
            ps.setString(5, ls.getMaNV());
            ps.setString(6, ls.getHinhThucXuLi());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(LS_ViPham ls) {
        String sql = "UPDATE LS_ViPham SET MaLoaiViPham=?, MSSV=?, NgayViPham=?, MaNV=?, HinhThucXuLi=? WHERE MaLSViPham=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ls.getMaLoaiViPham());
            ps.setString(2, ls.getMssv());
            ps.setTimestamp(3, ls.getNgayViPham() != null ? Timestamp.valueOf(ls.getNgayViPham()) : null);
            ps.setString(4, ls.getMaNV());
            ps.setString(5, ls.getHinhThucXuLi());
            ps.setString(6, ls.getMaLSViPham());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String maLSViPham) {
        String sql = "DELETE FROM LS_ViPham WHERE MaLSViPham=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maLSViPham);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<LS_ViPham> findByMSSV(String mssv) {
        List<LS_ViPham> list = new ArrayList<>();
        String sql = "SELECT * FROM LS_ViPham WHERE MSSV = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mssv);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}