package com.dormiroty.service;

import com.dormiroty.entity.SinhVien;

import java.util.List;

public interface SinhVienService {
    List<SinhVien> getAll();
    SinhVien getById(int mssv);
    boolean create(SinhVien sv);
    boolean update(SinhVien sv);
    boolean delete(int mssv);
    List<SinhVien> findByName(String name);
    int countTotalSinhVien();
}
