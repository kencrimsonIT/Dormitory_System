package com.dormiroty.controller;

import java.util.List;

import com.dormiroty.entity.HoaDon;
import com.dormiroty.service.HoaDonService;
import com.dormiroty.service.impl.HoaDonServiceImpl;

public class HoaDonController {

    private HoaDonService service;

    public HoaDonController() {
        service = new HoaDonServiceImpl();
    }

    public List<HoaDon> getAllHoaDon() {
        return service.getAll();
    }

    public HoaDon getHoaDonById(String maHD) {
        return service.getById(maHD);
    }

    public boolean addHoaDon(HoaDon hd) {
        return service.create(hd);
    }

    public boolean updateHoaDon(HoaDon hd) {
        return service.update(hd);
    }

    public boolean deleteHoaDon(String maHD) {
        return service.delete(maHD);
    }

    public List<HoaDon> getHoaDonByMSSV(String mssv) {
        return service.getByMSSV(mssv);
    }

    public List<HoaDon> getHoaDonByMaHopDong(String maHopDong) {
        return service.getByMaHopDong(maHopDong);
    }
}