package com.dormiroty.service;

import com.dormiroty.entity.NhanVien;
import java.util.List;

public interface NhanVienService {
    List<NhanVien> getAll();
    NhanVien getById(String maNV);
    boolean create(NhanVien nv);
    boolean update(NhanVien nv);
    boolean delete(String maNV);
}