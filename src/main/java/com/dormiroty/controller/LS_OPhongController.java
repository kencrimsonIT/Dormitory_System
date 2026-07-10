package com.dormiroty.controller;

import java.util.List;

import com.dormiroty.entity.LS_OPhong;
import com.dormiroty.service.LS_OPhongService;
import com.dormiroty.service.impl.LS_OPhongServiceImpl;

public class LS_OPhongController {

    private LS_OPhongService service;

    public LS_OPhongController() {
        service = new LS_OPhongServiceImpl();
    }

    public List<LS_OPhong> getAllLS_OPhong() {
        return service.getAll();
    }

    public LS_OPhong getLS_OPhongById(String maLS) {
        return service.getById(maLS);
    }

    public boolean addLS_OPhong(LS_OPhong ls) {
        return service.create(ls);
    }

    public boolean updateLS_OPhong(LS_OPhong ls) {
        return service.update(ls);
    }

    public boolean deleteLS_OPhong(String maLS) {
        return service.delete(maLS);
    }

    public List<LS_OPhong> getLS_OPhongByMSSV(String mssv) {
        return service.getByMSSV(mssv);
    }
}