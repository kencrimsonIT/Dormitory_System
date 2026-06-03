package com.dormiroty.dao.impl;

import com.dormiroty.dao.HopDongDAO;
import com.dormiroty.database.DBConnect;
import com.dormiroty.entity.HopDong;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HopDongDAOImpl implements HopDongDAO {

    @Override
    public List<HopDong> findAll() {
        List<HopDong> list = new ArrayList<>();
        String sql = "SELECT * FROM HopDong";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HopDong hd = new HopDong();
                hd.setMaHopDong(rs.getString("MaHopDong").trim());
                hd.setMSSV(rs.getInt("MSSV"));
                hd.setMaNV(rs.getString("MaNV").trim());

                hd.setTienCoc(rs.getBigDecimal("TienCoc"));

                Timestamp hanTs = rs.getTimestamp("NgayHetHan");
                if (hanTs != null) hd.setNgayHetHan(hanTs.toLocalDateTime());

                Timestamp lapTs = rs.getTimestamp("NgayLap");
                if (lapTs != null) hd.setNgayLap(lapTs.toLocalDateTime());

                hd.setMaPhong(rs.getString("MaPhong").trim());
                list.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public HopDong findById(String maHopDong) {
        String sql = "SELECT * FROM HopDong WHERE MaHopDong = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maHopDong);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    HopDong hd = new HopDong();
                    hd.setMaHopDong(rs.getString("MaHopDong").trim());
                    hd.setMSSV(rs.getInt("MSSV"));
                    hd.setMaNV(rs.getString("MaNV").trim());

                    hd.setTienCoc(rs.getBigDecimal("TienCoc"));

                    Timestamp hanTs = rs.getTimestamp("NgayHetHan");
                    if (hanTs != null) hd.setNgayHetHan(hanTs.toLocalDateTime());

                    Timestamp lapTs = rs.getTimestamp("NgayLap");
                    if (lapTs != null) hd.setNgayLap(lapTs.toLocalDateTime());

                    hd.setMaPhong(rs.getString("MaPhong").trim());
                    return hd;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(HopDong hd) {
        String sql = "INSERT INTO HopDong(MaHopDong, MSSV, MaNV, TienCoc, NgayHetHan, NgayLap, MaPhong) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hd.getMaHopDong());
            ps.setInt(2, hd.getMSSV()); // Đã sửa thành getMSSV
            ps.setString(3, hd.getMaNV());

            ps.setBigDecimal(4, hd.getTienCoc());

            ps.setTimestamp(5, Timestamp.valueOf(hd.getNgayHetHan()));
            ps.setTimestamp(6, Timestamp.valueOf(hd.getNgayLap()));

            ps.setString(7, hd.getMaPhong());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(HopDong hd) {
        String sql = "UPDATE HopDong SET MSSV=?, MaNV=?, TienCoc=?, NgayHetHan=?, NgayLap=?, MaPhong=? WHERE MaHopDong=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, hd.getMSSV());
            ps.setString(2, hd.getMaNV());

            ps.setBigDecimal(3, hd.getTienCoc());

            ps.setTimestamp(4, Timestamp.valueOf(hd.getNgayHetHan()));
            ps.setTimestamp(5, Timestamp.valueOf(hd.getNgayLap()));

            ps.setString(6, hd.getMaPhong());
            ps.setString(7, hd.getMaHopDong());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String maHopDong) {
        String sql = "DELETE FROM HopDong WHERE MaHopDong=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maHopDong);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}