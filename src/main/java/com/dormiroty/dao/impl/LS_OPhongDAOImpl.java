package com.dormiroty.dao.impl;

import com.dormiroty.dao.LS_OPhongDAO;
import com.dormiroty.database.DBConnect;
import com.dormiroty.entity.LS_OPhong;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LS_OPhongDAOImpl implements LS_OPhongDAO {

    private LS_OPhong mapResultSetToEntity(ResultSet rs) throws SQLException {
        LS_OPhong ls = new LS_OPhong();
        ls.setMaLS(rs.getString("MaLS").trim());
        ls.setMssv(rs.getString("MSSV").trim());
        ls.setMaPhong(rs.getString("MaPhong").trim());

        Timestamp tsVaoO = rs.getTimestamp("NgayVaoO");
        if (tsVaoO != null) {
            ls.setNgayVaoO(tsVaoO.toLocalDateTime());
        }

        Timestamp tsChuyenDi = rs.getTimestamp("NgayChuyenDi");
        if (tsChuyenDi != null) {
            ls.setNgayChuyenDi(tsChuyenDi.toLocalDateTime());
        }

        ls.setTrangThaiOPhong(rs.getString("TrangThai_OPhong"));
        return ls;
    }

    @Override
    public List<LS_OPhong> findAll() {
        List<LS_OPhong> list = new ArrayList<>();
        String sql = "SELECT * FROM LS_OPhong";
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
    public LS_OPhong findById(String maLS) {
        String sql = "SELECT * FROM LS_OPhong WHERE MaLS = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maLS);
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
    public boolean save(LS_OPhong ls) {
        String sql = "INSERT INTO LS_OPhong(MaLS, MSSV, MaPhong, NgayVaoO, NgayChuyenDi, TrangThai_OPhong) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ls.getMaLS());
            ps.setString(2, ls.getMssv());
            ps.setString(3, ls.getMaPhong());
            ps.setTimestamp(4, ls.getNgayVaoO() != null ? Timestamp.valueOf(ls.getNgayVaoO()) : null);
            ps.setTimestamp(5, ls.getNgayChuyenDi() != null ? Timestamp.valueOf(ls.getNgayChuyenDi()) : null);
            ps.setString(6, ls.getTrangThaiOPhong());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(LS_OPhong ls) {
        String sql = "UPDATE LS_OPhong SET MSSV=?, MaPhong=?, NgayVaoO=?, NgayChuyenDi=?, TrangThai_OPhong=? WHERE MaLS=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ls.getMssv());
            ps.setString(2, ls.getMaPhong());
            ps.setTimestamp(3, ls.getNgayVaoO() != null ? Timestamp.valueOf(ls.getNgayVaoO()) : null);
            ps.setTimestamp(4, ls.getNgayChuyenDi() != null ? Timestamp.valueOf(ls.getNgayChuyenDi()) : null);
            ps.setString(5, ls.getTrangThaiOPhong());
            ps.setString(6, ls.getMaLS());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String maLS) {
        String sql = "DELETE FROM LS_OPhong WHERE MaLS=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maLS);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<LS_OPhong> findByMSSV(String mssv) {
        List<LS_OPhong> list = new ArrayList<>();
        String sql = "SELECT * FROM LS_OPhong WHERE MSSV = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mssv);
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