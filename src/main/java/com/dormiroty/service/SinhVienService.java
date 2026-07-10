package com.dormiroty.service;

import com.dormiroty.entity.SinhVien;

import java.util.List;

public interface SinhVienService {
    List<SinhVien> getAll();
    SinhVien getById(String mssv);
    boolean create(SinhVien sv);
    boolean update(SinhVien sv);
    boolean delete(String mssv);
    List<SinhVien> findByName(String name);
    int countTotalSinhVien();
}
