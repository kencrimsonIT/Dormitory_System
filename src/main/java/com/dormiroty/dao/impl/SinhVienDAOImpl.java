package com.dormiroty.dao.impl;

import com.dormiroty.dao.SinhVienDAO;
import com.dormiroty.database.DBConnect;
import com.dormiroty.entity.SinhVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SinhVienDAOImpl implements SinhVienDAO {

    @Override
    public List<SinhVien> findAll() {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM SinhVien";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SinhVien sv = new SinhVien();
                sv.setMSSV(rs.getInt("MSSV"));
                sv.setHoTen(rs.getString("HoTen"));
                sv.setGioiTinh(rs.getString("GioiTinh"));

                Timestamp ts = rs.getTimestamp("NgaySinh");
                if (ts != null) {
                    sv.setNgaySinh(ts.toLocalDateTime());
                }

                sv.setNganhHoc(rs.getString("NganhHoc"));
                sv.setEmail(rs.getString("Email").trim());
                sv.setSDT(rs.getString("SĐT").trim());
                sv.setMaDCS(rs.getString("MaDCS") != null ? rs.getString("MaDCS").trim() : null);
                list.add(sv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public SinhVien findById(int mssv) {
        String sql = "SELECT * FROM SinhVien WHERE MSSV = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, mssv);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    SinhVien sv = new SinhVien();
                    sv.setMSSV(rs.getInt("MSSV"));
                    sv.setHoTen(rs.getString("HoTen"));
                    sv.setGioiTinh(rs.getString("GioiTinh"));

                    Timestamp ts = rs.getTimestamp("NgaySinh");
                    if (ts != null) {
                        sv.setNgaySinh(ts.toLocalDateTime());
                    }

                    sv.setNganhHoc(rs.getString("NganhHoc"));
                    sv.setEmail(rs.getString("Email").trim());
                    sv.setSDT(rs.getString("SĐT").trim());
                    sv.setMaDCS(rs.getString("MaDCS") != null ? rs.getString("MaDCS").trim() : null);
                    return sv;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(SinhVien sv) {
        String sql = "INSERT INTO SinhVien(MSSV, HoTen, GioiTinh, NgaySinh, NganhHoc, Email, SĐT, MADCS) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sv.getMSSV());
            ps.setString(2, sv.getHoTen());
            ps.setString(3, sv.getGioiTinh());

            ps.setTimestamp(4, Timestamp.valueOf(sv.getNgaySinh()));

            ps.setString(5, sv.getNganhHoc());
            ps.setString(6, sv.getEmail());
            ps.setString(7, sv.getSDT());
            ps.setString(8, sv.getMaDCS());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(SinhVien sv) {
        String sql = "UPDATE SinhVien SET HoTen=?, GioiTinh=?, NgaySinh=?, NganhHoc=?, Email=?, SĐT=? MaDCS=? WHERE MSSV=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sv.getHoTen());
            ps.setString(2, sv.getGioiTinh());

            ps.setTimestamp(3, Timestamp.valueOf(sv.getNgaySinh()));

            ps.setString(4, sv.getNganhHoc());
            ps.setString(5, sv.getEmail());
            ps.setString(6, sv.getSDT());
            ps.setString(7, sv.getMaDCS());
            ps.setInt(7, sv.getMSSV());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int mssv) {
        String sql = "DELETE FROM SinhVien WHERE MSSV=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, mssv);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public List<SinhVien> findByName(String name) {
        List<SinhVien> list = new ArrayList<>();
        // Dùng LIKE để tìm kiếm gần đúng
        String sql = "SELECT * FROM SinhVien WHERE HoTen LIKE ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            // Thêm dấu % vào 2 đầu để tìm chuỗi chứa từ khóa
            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SinhVien sv = new SinhVien();
                    sv.setMSSV(rs.getInt("MSSV"));
                    sv.setHoTen(rs.getString("HoTen"));
                    sv.setGioiTinh(rs.getString("GioiTinh"));
                    java.sql.Timestamp ts = rs.getTimestamp("NgaySinh");
                    if (ts != null) {
                        sv.setNgaySinh(ts.toLocalDateTime());
                    }
                    sv.setNganhHoc(rs.getString("NganhHoc"));
                    sv.setEmail(rs.getString("Email").trim());
                    sv.setSDT(rs.getString("SĐT").trim());
                    sv.setMaDCS(rs.getString("MaDCS") != null ? rs.getString("MaDCS").trim() : null);
                    list.add(sv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public int countTotalSinhVien() {
        String sql = "SELECT COUNT(*) FROM SinhVien";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}