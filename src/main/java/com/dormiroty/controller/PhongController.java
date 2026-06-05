package com.dormiroty.controller;

import java.util.List;

import com.dormiroty.entity.Phong;
import com.dormiroty.service.PhongService;
import com.dormiroty.service.impl.PhongServiceImpl;

public class PhongController {

    private PhongService service;

    public PhongController() {
        service = new PhongServiceImpl();
    }

    public List<Phong> getAllPhong() {
        return service.getAll();
    }

    public Phong getPhongById(String maPhong) {
        return service.getById(maPhong);
    }

    public boolean addPhong(Phong phong) {
        return service.create(phong);
    }

    public boolean updatePhong(Phong phong) {
        return service.update(phong);
    }

    public boolean deletePhong(String maPhong) {
        return service.delete(maPhong);
        
    }
    
}