package com.dormiroty.service.impl;

import com.dormiroty.dao.LoaiPhongDAO;
import com.dormiroty.dao.impl.LoaiPhongDAOImpl;
import com.dormiroty.entity.LoaiPhong;
import com.dormiroty.service.LoaiPhongService;

import java.util.List;

public class LoaiPhongServiceImpl implements LoaiPhongService {
    private LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAOImpl();

    @Override
    public List<LoaiPhong> getAll() {
        return loaiPhongDAO.findAll();
    }

    @Override
    public LoaiPhong getById(String maLoaiPhong) {
        return loaiPhongDAO.findById(maLoaiPhong);
    }

    @Override
    public boolean create(LoaiPhong lp) {
        if (loaiPhongDAO.findById(lp.getMaLoaiPhong()) != null) {
            System.out.println("Lỗi: Mã loại phòng này đã tồn tại!");
            return false;
        }
        return loaiPhongDAO.save(lp);
    }

    @Override
    public boolean update(LoaiPhong lp) {
        return loaiPhongDAO.update(lp);
    }

    @Override
    public boolean delete(String maLoaiPhong) {
        return loaiPhongDAO.delete(maLoaiPhong);
    }
}
