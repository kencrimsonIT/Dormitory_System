package com.dormiroty.dao.impl;

import com.dormiroty.dao.DienChinhSachDAO;
import com.dormiroty.database.DBConnect;
import com.dormiroty.entity.DienChinhSach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DienChinhSachDAOImpl implements DienChinhSachDAO {

    @Override
    public List<DienChinhSach> findAll() {
        List<DienChinhSach> list = new ArrayList<>();
        String sql = "SELECT * FROM DIENCHINHSACH";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DienChinhSach dcs = new DienChinhSach();
                dcs.setMaDCS(rs.getString("MADIEN").trim());
                dcs.setTenDCS(rs.getString("TENDIEN"));
                dcs.setMucNienGiam(rs.getString("MUCNIENGIAM"));
                list.add(dcs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public DienChinhSach findById(String maDien) {
        String sql = "SELECT * FROM DIENCHINHSACH WHERE MADIEN = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maDien);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    DienChinhSach dcs = new DienChinhSach();
                    dcs.setMaDCS(rs.getString("MADIEN").trim());
                    dcs.setTenDCS(rs.getString("TENDIEN"));
                    dcs.setMucNienGiam(rs.getString("MUCNIENGIAM"));
                    return dcs;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(DienChinhSach dcs) {
        String sql = "INSERT INTO DIENCHINHSACH(MADIEN, TENDIEN, MUCNIENGIAM) VALUES (?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dcs.getMaDCS());
            ps.setString(2, dcs.getTenDCS());
            ps.setString(3, dcs.getMucNienGiam());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(DienChinhSach dcs) {
        String sql = "UPDATE DIENCHINHSACH SET TENDIEN=?, MUCNIENGIAM=? WHERE MADIEN=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dcs.getTenDCS());
            ps.setString(2, dcs.getMucNienGiam());
            ps.setString(3, dcs.getMaDCS());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String maDien) {
        String sql = "DELETE FROM DIENCHINHSACH WHERE MADIEN=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maDien);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}