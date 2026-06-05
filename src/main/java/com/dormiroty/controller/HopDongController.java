package com.dormiroty.controller;

import com.dormiroty.entity.HopDong;
import com.dormiroty.service.HopDongService;
import com.dormiroty.service.impl.HopDongServiceImpl;

import java.util.List;

public class HopDongController {

    private HopDongService service = new HopDongServiceImpl();

    public List<HopDong> getAllHopDong() {
        return service.getAll();
    }

    public boolean addHopDong(HopDong hd) {
        return service.createContract(hd);
    }

    public boolean updateHopDong(HopDong hd) {
        return service.update(hd);
    }

    public boolean deleteHopDong(String maHopDong) {
        return service.delete(maHopDong);
    }

    public double getTongTienCoc() {
        return service.calculateTotalTienCoc();
    }
}