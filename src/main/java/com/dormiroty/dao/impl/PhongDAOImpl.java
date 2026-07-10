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

    private Phong mapResultSetToEntity(ResultSet rs) throws SQLException {
        Phong p = new Phong();
        p.setMaPhong(rs.getString("MaPhong").trim());
        p.setMaLoaiPhong(rs.getString("MaLoaiPhong").trim());
        p.setMaToaNha(rs.getString("MaToaNha").trim());
        p.setSoChoTrong(rs.getInt("SoChoTrong"));
        p.setTrangThai(rs.getString("TrangThai").trim());
        return p;
    }

    @Override
    public List<Phong> findAll() {
        List<Phong> list = new ArrayList<>();
        String sql = "SELECT * FROM Phong";
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
    public Phong findById(String maPhong) {
        String sql = "SELECT * FROM Phong WHERE MaPhong = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maPhong);
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
    public List<Phong> findAvailableRooms() {
        List<Phong> list = new ArrayList<>();
        // Tìm phòng còn chỗ trống
        String sql = "SELECT * FROM Phong WHERE SoChoTrong > 0 AND TrangThai != N'Đang sửa'";
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
    public boolean save(Phong phong) {
        String sql = "INSERT INTO Phong(MaPhong, MaLoaiPhong, MaToaNha, SoChoTrong, TrangThai) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, phong.getMaPhong());
            ps.setString(2, phong.getMaLoaiPhong());
            ps.setString(3, phong.getMaToaNha());
            ps.setInt(4, phong.getSoChoTrong());
            ps.setString(5, phong.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Phong phong) {
        String sql = "UPDATE Phong SET MaLoaiPhong=?, MaToaNha=?, SoChoTrong=?, TrangThai=? WHERE MaPhong=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, phong.getMaLoaiPhong());
            ps.setString(2, phong.getMaToaNha());
            ps.setInt(3, phong.getSoChoTrong());
            ps.setString(4, phong.getTrangThai());
            ps.setString(5, phong.getMaPhong());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String maPhong) {
        String sql = "DELETE FROM Phong WHERE MaPhong=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maPhong);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Phong> findByToaNha(String maToaNha) {
        List<Phong> list = new ArrayList<>();
        String sql = "SELECT * FROM Phong WHERE MaToaNha = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maToaNha);
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