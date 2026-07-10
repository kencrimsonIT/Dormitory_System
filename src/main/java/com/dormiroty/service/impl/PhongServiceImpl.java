package com.dormiroty.service.impl;

import com.dormiroty.dao.PhongDAO;
import com.dormiroty.dao.impl.PhongDAOImpl;
import com.dormiroty.entity.Phong;
import com.dormiroty.service.PhongService;

import java.util.List;

public class PhongServiceImpl implements PhongService {
    private PhongDAO phongDAO = new PhongDAOImpl();

    @Override
    public List<Phong> getAll() { return phongDAO.findAll(); }

    @Override
    public Phong getById(String maPhong) { return phongDAO.findById(maPhong); }

    @Override
    public List<Phong> getAvailableRooms() { return phongDAO.findAvailableRooms(); }

    @Override
    public boolean create(Phong phong) {
        if (phongDAO.findById(phong.getMaPhong()) != null) {
            System.out.println("Lỗi: Mã phòng đã tồn tại!");
            return false;
        }
        if (phong.getSoChoTrong() < 0) {
            System.out.println("Lỗi: Số chỗ trống không được bé hơn 0!");
            return false;
        }
        return phongDAO.save(phong);
    }

    @Override
    public boolean update(Phong phong) {
        if (phong.getSoChoTrong() < 0) {
            System.out.println("Lỗi: Số chỗ trống không được bé hơn 0!");
            return false;
        }
        return phongDAO.update(phong);
    }

    @Override
    public boolean delete(String maPhong) { return phongDAO.delete(maPhong); }

    @Override
    public List<Phong> findByToaNha(String maToaNha) { return phongDAO.findByToaNha(maToaNha); }
}