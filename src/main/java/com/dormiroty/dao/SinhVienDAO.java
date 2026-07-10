package com.dormiroty.dao;

import com.dormiroty.entity.SinhVien;

import java.util.List;

public interface SinhVienDAO {
    List<SinhVien> findAll();
    SinhVien findById(String mssv);
    boolean save(SinhVien sv);
    boolean update(SinhVien sv);
    boolean delete(String mssv);
    List<SinhVien> findByName(String name);
    int countTotalSinhVien();
}
