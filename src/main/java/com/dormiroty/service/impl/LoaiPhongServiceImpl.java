package com.dormiroty.service.impl;

import com.dormiroty.dao.LoaiPhongDAO;
import com.dormiroty.dao.impl.LoaiPhongDAOImpl;
import com.dormiroty.entity.LoaiPhong;
import com.dormiroty.service.LoaiPhongService;
import java.util.List;
import java.math.BigDecimal;

public class LoaiPhongServiceImpl implements LoaiPhongService {
    private LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAOImpl();

    @Override
    public List<LoaiPhong> getAll() { return loaiPhongDAO.findAll(); }

    @Override
    public LoaiPhong getById(String maLoaiPhong) { return loaiPhongDAO.findById(maLoaiPhong); }

    @Override
    public boolean create(LoaiPhong lp) {
        if (loaiPhongDAO.findById(lp.getMaLoaiPhong()) != null) return false;
        if (lp.getSucChua() < 1 || lp.getSucChua() > 12) {
            System.out.println("Lỗi: Sức chứa phải từ 1 đến 12!");
            return false;
        }
        if (lp.getDonGia().compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Lỗi: Đơn giá phải lớn hơn 0!");
            return false;
        }
        return loaiPhongDAO.save(lp);
    }

    @Override
    public boolean update(LoaiPhong lp) {
        if (lp.getSucChua() < 1 || lp.getSucChua() > 12) return false;
        if (lp.getDonGia().compareTo(BigDecimal.ZERO) <= 0) return false;
        return loaiPhongDAO.update(lp);
    }

    @Override
    public boolean delete(String maLoaiPhong) { return loaiPhongDAO.delete(maLoaiPhong); }
}