package com.dormiroty.controller;

import java.util.List;

import com.dormiroty.entity.ThanhToan;
import com.dormiroty.service.ThanhToanService;
import com.dormiroty.service.impl.ThanhToanServiceImpl;

public class ThanhToanController {

    private ThanhToanService service;

    public ThanhToanController() {
        service = new ThanhToanServiceImpl();
    }

    public List<ThanhToan> getAllThanhToan() {
        return service.getAll();
    }

    public ThanhToan getThanhToanById(String maHD) {
        return service.getById(maHD);
    }

    public boolean addThanhToan(ThanhToan tt) {
        return service.create(tt);
    }

    public boolean updateThanhToan(ThanhToan tt) {
        return service.update(tt);
    }

    public boolean deleteThanhToan(String maHD) {
        return service.delete(maHD);
    }
}