package com.dormiroty.dao.impl;

import com.dormiroty.dao.LoaiPhongDAO;
import com.dormiroty.database.DBConnect;
import com.dormiroty.entity.LoaiPhong;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoaiPhongDAOImpl implements LoaiPhongDAO {
    @Override
    public List<LoaiPhong> findAll() {
        List<LoaiPhong> list = new ArrayList<>();
        String sql = "SELECT * FROM LOAIPHONG";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LoaiPhong lp = new LoaiPhong();
                lp.setMaLoaiPhong(rs.getString("MALOAIPHONG").trim());
                lp.setTenLoaiPhong(rs.getString("TENLOAIPHONG"));
                lp.setSucChua(rs.getShort("SUCCHUA"));
                lp.setDonGia(rs.getBigDecimal("DONGIA"));
                list.add(lp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public LoaiPhong findById(String maLoaiPhong) {
        String sql = "SELECT * FROM LOAIPHONG WHERE MALOAIPHONG = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maLoaiPhong);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    LoaiPhong lp = new LoaiPhong();
                    lp.setMaLoaiPhong(rs.getString("MALOAIPHONG").trim());
                    lp.setTenLoaiPhong(rs.getString("TENLOAIPHONG"));
                    lp.setSucChua(rs.getShort("SUCCHUA"));
                    lp.setDonGia(rs.getBigDecimal("DONGIA"));
                    return lp;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(LoaiPhong lp) {
        String sql = "INSERT INTO LOAIPHONG(MALOAIPHONG, TENLOAIPHONG, SUCCHUA, DONGIA) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, lp.getMaLoaiPhong());
            ps.setString(2, lp.getTenLoaiPhong());
            ps.setShort(3, lp.getSucChua());
            ps.setBigDecimal(4, lp.getDonGia());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(LoaiPhong lp) {
        String sql = "UPDATE LOAIPHONG SET TENLOAIPHONG=?, SUCCHUA=?, DONGIA=? WHERE MALOAIPHONG=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, lp.getTenLoaiPhong());
            ps.setShort(2, lp.getSucChua());
            ps.setBigDecimal(3, lp.getDonGia());
            ps.setString(4, lp.getMaLoaiPhong());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String maLoaiPhong) {
        String sql = "DELETE FROM LOAIPHONG WHERE MALOAIPHONG=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maLoaiPhong);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}