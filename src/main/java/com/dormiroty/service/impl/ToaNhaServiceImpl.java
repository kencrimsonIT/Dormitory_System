package com.dormiroty.service.impl;

import com.dormiroty.dao.ToaNhaDAO;
import com.dormiroty.dao.impl.ToaNhaDAOImpl;
import com.dormiroty.entity.ToaNha;
import com.dormiroty.service.ToaNhaService;
import java.util.List;

public class ToaNhaServiceImpl implements ToaNhaService {
    private ToaNhaDAO toaNhaDAO = new ToaNhaDAOImpl();

    @Override
    public List<ToaNha> getAll() { return toaNhaDAO.findAll(); }

    @Override
    public ToaNha getById(String maToaNha) { return toaNhaDAO.findById(maToaNha); }

    @Override
    public boolean create(ToaNha tn) {
        if (toaNhaDAO.findById(tn.getMaToaNha()) != null) return false;
        if (tn.getSoTang() < 1 || tn.getSoTang() > 10) {
            System.out.println("Lỗi: Số tầng phải từ 1 đến 10!");
            return false;
        }
        return toaNhaDAO.save(tn);
    }

    @Override
    public boolean update(ToaNha tn) {
        if (tn.getSoTang() < 1 || tn.getSoTang() > 10) {
            System.out.println("Lỗi: Số tầng phải từ 1 đến 10!");
            return false;
        }
        return toaNhaDAO.update(tn);
    }

    @Override
    public boolean delete(String maToaNha) { return toaNhaDAO.delete(maToaNha); }
}