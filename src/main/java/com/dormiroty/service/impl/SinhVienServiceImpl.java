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
    public SinhVien getById(String mssv) {
        if (mssv == null || mssv.trim().isEmpty()) {
            return null;
        }
        return sinhVienDAO.findById(mssv);
    }

    @Override
    public boolean create(SinhVien sv) {
        // Nghiệp vụ: Kiểm tra trùng mã sinh viên trước khi thêm
        if (sinhVienDAO.findById(sv.getMssv()) != null) {
            System.out.println("Lỗi: MSSV đã tồn tại!");
            return false;
        }
        // Nghiệp vụ: Kiểm tra tính hợp lệ của giới tính (Database chỉ nhận 'Nam' hoặc 'Nữ')
        if (!"Nam".equals(sv.getGioiTinh()) && !"Nữ".equals(sv.getGioiTinh())) {
            System.out.println("Lỗi: Giới tính không hợp lệ (Phải là 'Nam' hoặc 'Nữ')!");
            return false;
        }
        return sinhVienDAO.save(sv);
    }

    @Override
    public boolean update(SinhVien sv) {
        if (sinhVienDAO.findById(sv.getMssv()) == null) {
            System.out.println("Lỗi: Sinh viên không tồn tại để cập nhật!");
            return false;
        }
        return sinhVienDAO.update(sv);
    }

    @Override
    public boolean delete(String mssv) {
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