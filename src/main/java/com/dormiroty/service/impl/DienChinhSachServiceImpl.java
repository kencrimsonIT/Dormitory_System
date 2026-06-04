package com.dormiroty.service.impl;

import com.dormiroty.dao.DienChinhSachDAO;
import com.dormiroty.dao.impl.DienChinhSachDAOImpl;
import com.dormiroty.entity.DienChinhSach;
import com.dormiroty.service.DienChinhSachService;

import java.util.List;

public class DienChinhSachServiceImpl implements DienChinhSachService {

    private DienChinhSachDAO dienChinhSachDAO = new DienChinhSachDAOImpl();

    @Override
    public List<DienChinhSach> getAll() {
        return dienChinhSachDAO.findAll();
    }

    @Override
    public DienChinhSach getById(String maDien) {
        return dienChinhSachDAO.findById(maDien);
    }

    @Override
    public boolean create(DienChinhSach dcs) {
        return dienChinhSachDAO.save(dcs);
    }

    @Override
    public boolean update(DienChinhSach dcs) {
        return dienChinhSachDAO.update(dcs);
    }

    @Override
    public boolean delete(String maDien) {
        return dienChinhSachDAO.delete(maDien);
    }
}
