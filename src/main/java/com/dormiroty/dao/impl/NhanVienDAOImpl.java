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
    @Override
    public List<NhanVien> findAll() {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NHANVIEN";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV").trim());
                nv.setHoTenNV(rs.getString("HoTenNV"));
                nv.setSDT(rs.getString("SĐT").trim()); // Nhớ check Entity xem có phải setSDT() không
                nv.setEmail(rs.getString("Email").trim());
                nv.setChucVu(rs.getString("ChucVu"));
                list.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public NhanVien findById(String maNV) {
        String sql = "SELECT * FROM NHANVIEN WHERE MaNV = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maNV);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNV(rs.getString("MaNV").trim());
                    nv.setHoTenNV(rs.getString("HoTenNV"));
                    nv.setSDT(rs.getString("SĐT").trim());
                    nv.setEmail(rs.getString("Email").trim());
                    nv.setChucVu(rs.getString("ChucVu"));
                    return nv;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(NhanVien nv) {
        String sql = "INSERT INTO NHANVIEN(MaNV, HoTenNV, SĐT, Email, ChucVu) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getHoTenNV());
            ps.setString(3, nv.getSDT());
            ps.setString(4, nv.getEmail());
            ps.setString(5, nv.getChucVu());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(NhanVien nv) {
        String sql = "UPDATE NHANVIEN SET HoTenNV=?, SĐT=?, Email=?, ChucVu=? WHERE MaNV=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nv.getHoTenNV());
            ps.setString(2, nv.getSDT());
            ps.setString(3, nv.getEmail());
            ps.setString(4, nv.getChucVu());
            ps.setString(5, nv.getMaNV());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String maNV) {
        String sql = "DELETE FROM NHANVIEN WHERE MaNV=?";
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
