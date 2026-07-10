package com.dormiroty.dao.impl;

import com.dormiroty.dao.HoaDonDAO;
import com.dormiroty.database.DBConnect;
import com.dormiroty.entity.HoaDon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAOImpl implements HoaDonDAO {

    private HoaDon mapResultSetToEntity(ResultSet rs) throws SQLException {
        HoaDon hd = new HoaDon();
        hd.setMaHD(rs.getString("MaHD").trim());
        hd.setMaHopDong(rs.getString("MaHopDong").trim());
        hd.setMaNV(rs.getString("MaNV").trim());
        hd.setMssv(rs.getString("MSSV").trim()); // MSSV là String
        hd.setSoTienTra(rs.getBigDecimal("SoTienTra"));
        hd.setHinhThuc(rs.getString("HinhThuc"));
        return hd;
    }

    @Override
    public List<HoaDon> findAll() {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapResultSetToEntity(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public HoaDon findById(String maHD) {
        String sql = "SELECT * FROM HoaDon WHERE MaHD = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maHD);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapResultSetToEntity(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public boolean save(HoaDon hd) {
        String sql = "INSERT INTO HoaDon(MaHD, MaHopDong, MaNV, MSSV, SoTienTra, HinhThuc) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hd.getMaHD());
            ps.setString(2, hd.getMaHopDong());
            ps.setString(3, hd.getMaNV());
            ps.setString(4, hd.getMssv());
            ps.setBigDecimal(5, hd.getSoTienTra());
            ps.setString(6, hd.getHinhThuc());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean update(HoaDon hd) {
        String sql = "UPDATE HoaDon SET MaHopDong=?, MaNV=?, MSSV=?, SoTienTra=?, HinhThuc=? WHERE MaHD=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hd.getMaHopDong());
            ps.setString(2, hd.getMaNV());
            ps.setString(3, hd.getMssv());
            ps.setBigDecimal(4, hd.getSoTienTra());
            ps.setString(5, hd.getHinhThuc());
            ps.setString(6, hd.getMaHD());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean delete(String maHD) {
        String sql = "DELETE FROM HoaDon WHERE MaHD=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maHD);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public List<HoaDon> findByMSSV(String mssv) {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon WHERE MSSV = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mssv);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public List<HoaDon> findByMaHopDong(String maHopDong) {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon WHERE MaHopDong = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maHopDong);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}