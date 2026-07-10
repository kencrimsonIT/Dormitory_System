package com.dormiroty.service.impl;

import com.dormiroty.dao.NhanVienDAO;
import com.dormiroty.dao.impl.NhanVienDAOImpl;
import com.dormiroty.entity.NhanVien;
import com.dormiroty.service.NhanVienService;

import java.util.List;

public class NhanVienServiceImpl implements NhanVienService {

    private NhanVienDAO nhanVienDAO = new NhanVienDAOImpl();

    @Override
    public List<NhanVien> getAll() {
        return nhanVienDAO.findAll();
    }

    @Override
    public NhanVien getById(String maNV) {
        if (maNV == null || maNV.trim().isEmpty()) {
            return null;
        }
        return nhanVienDAO.findById(maNV);
    }

    @Override
    public boolean create(NhanVien nv) {
        if (nhanVienDAO.findById(nv.getMaNV()) != null) {
            System.out.println("Lỗi: Mã nhân viên đã tồn tại!");
            return false;
        }
        if (!"Nam".equals(nv.getGioiTinh()) && !"Nữ".equals(nv.getGioiTinh())) {
            System.out.println("Lỗi: Giới tính nhân viên phải là 'Nam' hoặc 'Nữ'!");
            return false;
        }
        return nhanVienDAO.save(nv);
    }

    @Override
    public boolean update(NhanVien nv) {
        if (nhanVienDAO.findById(nv.getMaNV()) == null) {
            System.out.println("Lỗi: Nhân viên không tồn tại để cập nhật!");
            return false;
        }
        return nhanVienDAO.update(nv);
    }

    @Override
    public boolean delete(String maNV) {
        return nhanVienDAO.delete(maNV);
    }
}