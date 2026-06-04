package com.dormiroty.service.impl;

import com.dormiroty.dao.ViPhamDAO;
import com.dormiroty.dao.impl.ViPhamDAOImpl;
import com.dormiroty.entity.ViPham;
import com.dormiroty.service.ViPhamService;

import java.util.List;

public class ViPhamServiceImpl implements ViPhamService {

    private ViPhamDAO viPhamDAO = new ViPhamDAOImpl();

    @Override
    public List<ViPham> getAll() {
        return viPhamDAO.findAll();
    }

    @Override
    public ViPham getById(String maLoaiViPham) {
        return viPhamDAO.findById(maLoaiViPham);
    }

    @Override
    public boolean create(ViPham vp) {
        return viPhamDAO.save(vp);
    }

    @Override
    public boolean update(ViPham vp) {
        return viPhamDAO.update(vp);
    }

    @Override
    public boolean delete(String maLoaiViPham) {
        return viPhamDAO.delete(maLoaiViPham);
    }
}