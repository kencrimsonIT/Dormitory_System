package com.dormiroty.dao.impl;

import com.dormiroty.dao.NhanVienDAO;
import com.dormiroty.database.DBConnect;
import com.dormiroty.entity.NhanVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAOImpl implements NhanVienDAO {

    private NhanVien mapResultSetToEntity(ResultSet rs) throws SQLException {
        NhanVien nv = new NhanVien();
        nv.setMaNV(rs.getString("MaNV").trim());
        nv.setHoTenNV(rs.getString("HoTenNV"));
        nv.setGioiTinh(rs.getString("GioiTinh"));
        nv.setSdt(rs.getString("SDT").trim());
        nv.setEmail(rs.getString("Email_NV").trim());
        nv.setChucVu(rs.getString("ChucVu"));
        nv.setMaToaNha(rs.getString("MaToaNha") != null ? rs.getString("MaToaNha").trim() : null);
        return nv;
    }

    @Override
    public List<NhanVien> findAll() {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";
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
    public NhanVien findById(String maNV) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maNV);
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
    public boolean save(NhanVien nv) {
        String sql = "INSERT INTO NhanVien(MaNV, HoTenNV, GioiTinh, SDT, Email_NV, ChucVu, MaToaNha) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getHoTenNV());
            ps.setString(3, nv.getGioiTinh());
            ps.setString(4, nv.getSdt());
            ps.setString(5, nv.getEmail());
            ps.setString(6, nv.getChucVu());
            ps.setString(7, nv.getMaToaNha());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(NhanVien nv) {
        String sql = "UPDATE NhanVien SET HoTenNV=?, GioiTinh=?, SDT=?, Email_NV=?, ChucVu=?, MaToaNha=? WHERE MaNV=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nv.getHoTenNV());
            ps.setString(2, nv.getGioiTinh());
            ps.setString(3, nv.getSdt());
            ps.setString(4, nv.getEmail());
            ps.setString(5, nv.getChucVu());
            ps.setString(6, nv.getMaToaNha());
            ps.setString(7, nv.getMaNV());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String maNV) {
        String sql = "DELETE FROM NhanVien WHERE MaNV=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maNV);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}