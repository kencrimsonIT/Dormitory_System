package com.dormiroty.dao;

import com.dormiroty.entity.SinhVien;

import java.util.List;

public interface SinhVienDAO {
    List<SinhVien> findAll();
    SinhVien findById(int mssv);
    boolean save(SinhVien sv);
    boolean update(SinhVien sv);
    boolean delete(int mssv);
}
