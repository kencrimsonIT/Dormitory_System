package com.dormiroty.dao.impl;

import com.dormiroty.dao.PhongDAO;
import com.dormiroty.database.DBConnect;
import com.dormiroty.entity.Phong;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhongDAOImpl implements PhongDAO {
    @Override
    public List<Phong> findAll() {
        List<Phong> list = new ArrayList<>();
        String sql = "SELECT * FROM PHONG";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Phong p = new Phong();
                p.setMaPhong(rs.getString("MAPHONG").trim());
                p.setMaLoaiPhong(rs.getString("MALOAIPHONG").trim());
                p.setMaToaNha(rs.getString("MATOANHA").trim());
                p.setTinhTrang(rs.getString("TinhTrang").trim());
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Phong findById(String maPhong) {
        String sql = "SELECT * FROM PHONG WHERE MAPHONG = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maPhong);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Phong p = new Phong();
                    p.setMaPhong(rs.getString("MAPHONG").trim());
                    p.setMaLoaiPhong(rs.getString("MALOAIPHONG").trim());
                    p.setMaToaNha(rs.getString("MATOANHA").trim());
                    p.setTinhTrang(rs.getString("TinhTrang").trim());
                    return p;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Phong> findAvailableRooms() {
        List<Phong> list = new ArrayList<>();
        // Lấy những phòng có TinhTrang là 'Còn trống' theo đúng SQL của bạn
        String sql = "SELECT * FROM PHONG WHERE TinhTrang = N'Còn trống'";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Phong p = new Phong();
                p.setMaPhong(rs.getString("MAPHONG").trim());
                p.setMaLoaiPhong(rs.getString("MALOAIPHONG").trim());
                p.setMaToaNha(rs.getString("MATOANHA").trim());
                p.setTinhTrang(rs.getString("TinhTrang").trim());
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(Phong phong) {
        String sql = "INSERT INTO PHONG(MAPHONG, MALOAIPHONG, MATOANHA, TinhTrang) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, phong.getMaPhong());
            ps.setString(2, phong.getMaLoaiPhong());
            ps.setString(3, phong.getMaToaNha());
            ps.setString(4, phong.getTinhTrang());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Phong phong) {
        String sql = "UPDATE PHONG SET MALOAIPHONG=?, MATOANHA=?, TinhTrang=? WHERE MAPHONG=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, phong.getMaLoaiPhong());
            ps.setString(2, phong.getMaToaNha());
            ps.setString(3, phong.getTinhTrang());
            ps.setString(4, phong.getMaPhong());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String maPhong) {
        String sql = "DELETE FROM PHONG WHERE MAPHONG=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maPhong);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
