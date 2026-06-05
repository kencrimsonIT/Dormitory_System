package com.dormiroty.controller;

import com.dormiroty.entity.NhanVien;
import com.dormiroty.service.NhanVienService;
import com.dormiroty.service.impl.NhanVienServiceImpl;

import java.util.List;

public class NhanVienController {

    private NhanVienService service = new NhanVienServiceImpl();

    public List<NhanVien> getAllNhanVien() {
        return service.getAll();
    }

    public boolean addNhanVien(NhanVien nv) {
        return service.create(nv);
    }

    public boolean updateNhanVien(NhanVien nv) {
        return service.update(nv);
    }

    public boolean deleteNhanVien(String maNV) {
        return service.delete(maNV);
    }
}