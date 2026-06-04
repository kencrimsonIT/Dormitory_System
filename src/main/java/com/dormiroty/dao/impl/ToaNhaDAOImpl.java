package com.dormiroty.dao.impl;

import com.dormiroty.dao.NhanVienDAO;
import com.dormiroty.dao.ToaNhaDAO;
import com.dormiroty.database.DBConnect;
import com.dormiroty.entity.NhanVien;
import com.dormiroty.entity.ToaNha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToaNhaDAOImpl implements ToaNhaDAO {
    @Override
    public List<ToaNha> findAll() {
        List<ToaNha> list = new ArrayList<>();
        String sql = "SELECT * FROM TOANHA";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ToaNha tn = new ToaNha();
                tn.setMaToaNha(rs.getString("MATOANHA").trim());
                tn.setTenToaNha(rs.getString("TENTOANHA"));
                tn.setLoaiToaNha(rs.getString("LOAITOANHA").trim());
                list.add(tn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ToaNha findById(String maToaNha) {
        String sql = "SELECT * FROM TOANHA WHERE MATOANHA = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maToaNha);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ToaNha tn = new ToaNha();
                    tn.setMaToaNha(rs.getString("MATOANHA").trim());
                    tn.setTenToaNha(rs.getString("TENTOANHA"));
                    tn.setLoaiToaNha(rs.getString("LOAITOANHA").trim());
                    return tn;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(ToaNha toaNha) {
        String sql = "INSERT INTO TOANHA(MATOANHA, TENTOANHA, LOAITOANHA) VALUES (?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, toaNha.getMaToaNha());
            ps.setString(2, toaNha.getTenToaNha());
            ps.setString(3, toaNha.getLoaiToaNha());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ToaNha toaNha) {
        String sql = "UPDATE TOANHA SET TENTOANHA=?, LOAITOANHA=? WHERE MATOANHA=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, toaNha.getTenToaNha());
            ps.setString(2, toaNha.getLoaiToaNha());
            ps.setString(3, toaNha.getMaToaNha());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String maToaNha) {
        String sql = "DELETE FROM TOANHA WHERE MATOANHA=?";
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
