package com.dormiroty.service.impl;

import com.dormiroty.dao.LS_ViPhamDAO;
import com.dormiroty.dao.impl.LS_ViPhamDAOImpl;
import com.dormiroty.entity.LS_ViPham;
import com.dormiroty.service.LS_ViPhamService;

import java.time.LocalDateTime;
import java.util.List;

public class LS_ViPhamServiceImpl implements LS_ViPhamService {

    private LS_ViPhamDAO dao = new LS_ViPhamDAOImpl();

    @Override
    public List<LS_ViPham> getAll() {
        return dao.findAll();
    }

    @Override
    public LS_ViPham getById(String maLSViPham) {
        if (maLSViPham == null || maLSViPham.trim().isEmpty()) {
            return null;
        }
        return dao.findById(maLSViPham);
    }

    @Override
    public boolean create(LS_ViPham ls) {
        if (dao.findById(ls.getMaLSViPham()) != null) {
            System.out.println("Lỗi: Mã lịch sử vi phạm này đã tồn tại!");
            return false;
        }

        // Tự động gán thời gian hiện tại nếu giao diện không truyền xuống
        if (ls.getNgayViPham() == null) {
            ls.setNgayViPham(LocalDateTime.now());
        }

        return dao.save(ls);
    }

    @Override
    public boolean update(LS_ViPham ls) {
        if (dao.findById(ls.getMaLSViPham()) == null) {
            System.out.println("Lỗi: Không tìm thấy lịch sử vi phạm để cập nhật!");
            return false;
        }
        return dao.update(ls);
    }

    @Override
    public boolean delete(String maLSViPham) {
        return dao.delete(maLSViPham);
    }

    @Override
    public List<LS_ViPham> getByMSSV(String mssv) {
        return dao.findByMSSV(mssv);
    }
}