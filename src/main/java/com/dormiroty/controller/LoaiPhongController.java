package com.dormiroty.controller;

import java.util.List;

import com.dormiroty.entity.LoaiPhong;
import com.dormiroty.service.LoaiPhongService;
import com.dormiroty.service.impl.LoaiPhongServiceImpl;

public class LoaiPhongController {

    private LoaiPhongService service;

    public LoaiPhongController() {
        service = new LoaiPhongServiceImpl();
    }

    public List<LoaiPhong> getAllLoaiPhong() {
        return service.getAll();
    }

    public LoaiPhong getLoaiPhongById(String maLoaiPhong) {
        return service.getById(maLoaiPhong);
    }

    public boolean addLoaiPhong(LoaiPhong lp) {
        return service.create(lp);
    }

    public boolean updateLoaiPhong(LoaiPhong lp) {
        return service.update(lp);
    }

    public boolean deleteLoaiPhong(String maLoaiPhong) {
        return service.delete(maLoaiPhong);
    }
}
