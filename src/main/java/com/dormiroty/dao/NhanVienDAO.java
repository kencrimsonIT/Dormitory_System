package com.dormiroty.dao;

import com.dormiroty.entity.NhanVien;

import java.util.List;

public interface NhanVienDAO {
    List<NhanVien> findAll();
    NhanVien findById(String maNV);
    boolean save(NhanVien nv);
    boolean update(NhanVien nv);
    boolean delete(String maNV);
}
