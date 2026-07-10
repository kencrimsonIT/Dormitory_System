package com.dormiroty.service.impl;

import com.dormiroty.dao.ThanhToanDAO;
import com.dormiroty.dao.impl.ThanhToanDAOImpl;
import com.dormiroty.entity.ThanhToan;
import com.dormiroty.service.ThanhToanService;

import java.time.LocalDateTime;
import java.util.List;

public class ThanhToanServiceImpl implements ThanhToanService {
    private ThanhToanDAO dao = new ThanhToanDAOImpl();

    @Override
    public List<ThanhToan> getAll() { return dao.findAll(); }

    @Override
    public ThanhToan getById(String maHD) { return dao.findById(maHD); }

    @Override
    public boolean create(ThanhToan tt) {
        if (dao.findById(tt.getMaHD()) != null) return false;

        // Tự động gán giờ thanh toán nếu chưa có
        if (tt.getNgayThanhToan() == null) {
            tt.setNgayThanhToan(LocalDateTime.now());
        }
        return dao.save(tt);
    }

    @Override
    public boolean update(ThanhToan tt) { return dao.update(tt); }

    @Override
    public boolean delete(String maHD) { return dao.delete(maHD); }
}