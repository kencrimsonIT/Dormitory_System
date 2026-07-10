package com.dormiroty.dao.impl;

import com.dormiroty.dao.ToaNhaDAO;
import com.dormiroty.database.DBConnect;
import com.dormiroty.entity.ToaNha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToaNhaDAOImpl implements ToaNhaDAO {

    private ToaNha mapResultSetToEntity(ResultSet rs) throws SQLException {
        ToaNha tn = new ToaNha();
        tn.setMaToaNha(rs.getString("MaToaNha").trim());
        tn.setTenToaNha(rs.getString("TenToaNha"));
        tn.setLoaiToaNha(rs.getString("LoaiToaNha").trim());
        tn.setSoTang(rs.getInt("SoTang"));
        tn.setDiaChi(rs.getString("DiaChi"));
        return tn;
    }

    @Override
    public List<ToaNha> findAll() {
        List<ToaNha> list = new ArrayList<>();
        String sql = "SELECT * FROM ToaNha";
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
    public ToaNha findById(String maToaNha) {
        String sql = "SELECT * FROM ToaNha WHERE MaToaNha = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maToaNha);
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
    public boolean save(ToaNha tn) {
        String sql = "INSERT INTO ToaNha(MaToaNha, TenToaNha, LoaiToaNha, SoTang, DiaChi) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tn.getMaToaNha());
            ps.setString(2, tn.getTenToaNha());
            ps.setString(3, tn.getLoaiToaNha());
            ps.setInt(4, tn.getSoTang());
            ps.setString(5, tn.getDiaChi());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ToaNha tn) {
        String sql = "UPDATE ToaNha SET TenToaNha=?, LoaiToaNha=?, SoTang=?, DiaChi=? WHERE MaToaNha=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tn.getTenToaNha());
            ps.setString(2, tn.getLoaiToaNha());
            ps.setInt(3, tn.getSoTang());
            ps.setString(4, tn.getDiaChi());
            ps.setString(5, tn.getMaToaNha());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String maToaNha) {
        String sql = "DELETE FROM ToaNha WHERE MaToaNha=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maToaNha);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}