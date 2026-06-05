package com.dormiroty.controller;

import java.util.List;
import com.dormiroty.entity.SinhVien;
import com.dormiroty.service.SinhVienService;
import com.dormiroty.service.impl.SinhVienServiceImpl;

public class SinhVienController {

    private SinhVienService service;

    public SinhVienController() {
        service = new SinhVienServiceImpl();
    }

    public List<SinhVien> getAllSinhVien() {
        return service.getAll();
    }

    public SinhVien getSinhVienById(int mssv) {
        return service.getById(mssv);
    }

    public boolean addSinhVien(SinhVien sv) {
        return service.create(sv);
    }

    public boolean updateSinhVien(SinhVien sv) {
        return service.update(sv);
    }

    public boolean deleteSinhVien(int mssv) {
        return service.delete(mssv);
    }

    public List<SinhVien> findByName(String name) {
        return service.findByName(name);
    }

    public int countTotalSinhVien() {
        return service.countTotalSinhVien();
    }
}