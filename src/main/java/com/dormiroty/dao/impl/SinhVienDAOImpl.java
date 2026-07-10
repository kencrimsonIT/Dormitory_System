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

    private SinhVien mapResultSetToEntity(ResultSet rs) throws SQLException {
        SinhVien sv = new SinhVien();
        sv.setMssv(rs.getString("MSSV").trim());
        sv.setHoTen(rs.getString("HoTen"));

        Timestamp ts = rs.getTimestamp("NgaySinh");
        if (ts != null) {
            sv.setNgaySinh(ts.toLocalDateTime());
        }

        sv.setQueQuan(rs.getString("QueQuan"));
        sv.setGioiTinh(rs.getString("GioiTinh"));
        sv.setEmail(rs.getString("Email_SV").trim());
        sv.setSdt(rs.getString("SDT").trim());
        sv.setNganhHoc(rs.getString("NganhHoc"));
        sv.setNam(rs.getInt("Nam"));
        sv.setMaDCS(rs.getString("MaDCS") != null ? rs.getString("MaDCS").trim() : null);
        return sv;
    }

    @Override
    public List<SinhVien> findAll() {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM SinhVien";
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
    public SinhVien findById(String mssv) {
        String sql = "SELECT * FROM SinhVien WHERE MSSV = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mssv);
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
    public boolean save(SinhVien sv) {
        String sql = "INSERT INTO SinhVien(MSSV, HoTen, NgaySinh, QueQuan, GioiTinh, Email_SV, SDT, NganhHoc, Nam, MaDCS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sv.getMssv());
            ps.setString(2, sv.getHoTen());
            ps.setTimestamp(3, sv.getNgaySinh() != null ? Timestamp.valueOf(sv.getNgaySinh()) : null);
            ps.setString(4, sv.getQueQuan());
            ps.setString(5, sv.getGioiTinh());
            ps.setString(6, sv.getEmail());
            ps.setString(7, sv.getSdt());
            ps.setString(8, sv.getNganhHoc());
            ps.setInt(9, sv.getNam());
            ps.setString(10, sv.getMaDCS());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(SinhVien sv) {
        String sql = "UPDATE SinhVien SET HoTen=?, NgaySinh=?, QueQuan=?, GioiTinh=?, Email_SV=?, SDT=?, NganhHoc=?, Nam=?, MaDCS=? WHERE MSSV=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sv.getHoTen());
            ps.setTimestamp(2, sv.getNgaySinh() != null ? Timestamp.valueOf(sv.getNgaySinh()) : null);
            ps.setString(3, sv.getQueQuan());
            ps.setString(4, sv.getGioiTinh());
            ps.setString(5, sv.getEmail());
            ps.setString(6, sv.getSdt());
            ps.setString(7, sv.getNganhHoc());
            ps.setInt(8, sv.getNam());
            ps.setString(9, sv.getMaDCS());
            ps.setString(10, sv.getMssv());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String mssv) {
        String sql = "DELETE FROM SinhVien WHERE MSSV=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mssv);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<SinhVien> findByName(String name) {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM SinhVien WHERE HoTen LIKE ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
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