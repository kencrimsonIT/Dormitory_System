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
        return nhanVienDAO.findById(maNV);
    }

    @Override
    public boolean create(NhanVien nv) {
        return nhanVienDAO.save(nv);
    }

    @Override
    public boolean update(NhanVien nv) {
        return nhanVienDAO.update(nv);
    }

    @Override
    public boolean delete(String maNV) {
        return nhanVienDAO.delete(maNV);
    }
}
