package com.dormiroty.service.impl;

import com.dormiroty.dao.SinhVienDAO;
import com.dormiroty.dao.impl.SinhVienDAOImpl;
import com.dormiroty.entity.SinhVien;
import com.dormiroty.service.SinhVienService;

import java.util.List;

public class SinhVienServiceImpl implements SinhVienService {
    private SinhVienDAO sinhVienDAO = new SinhVienDAOImpl();

    @Override
    public List<SinhVien> getAll() {
        return sinhVienDAO.findAll();
    }

    @Override
    public SinhVien getById(int mssv) {
        return sinhVienDAO.findById(mssv);
    }

    @Override
    public boolean create(SinhVien sv) {
        // Có thể thêm logic kiểm tra trùng lặp email, sđt ở đây trước khi save
        return sinhVienDAO.save(sv);
    }

    @Override
    public boolean update(SinhVien sv) {
        return sinhVienDAO.update(sv);
    }

    @Override
    public boolean delete(int mssv) {
        return sinhVienDAO.delete(mssv);
    }

    @Override
    public List<SinhVien> findByName(String name) {
        return sinhVienDAO.findByName(name);
    }
    @Override
    public int countTotalSinhVien() {
        return sinhVienDAO.countTotalSinhVien();
    }
}
