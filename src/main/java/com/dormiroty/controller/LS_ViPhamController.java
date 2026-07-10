package com.dormiroty.controller;

import com.dormiroty.entity.LS_ViPham;
import com.dormiroty.service.LS_ViPhamService;
import com.dormiroty.service.impl.LS_ViPhamServiceImpl;

import java.util.List;

public class LS_ViPhamController {
    private LS_ViPhamService service = new LS_ViPhamServiceImpl();
    public List<LS_ViPham> getAllLSViPham() {
        return service.getAll();
    }
    public LS_ViPham getLSViPhamById(String maLSViPham) {
        return service.getById(maLSViPham);
    }
    public boolean addLSViPham(LS_ViPham ls) {
        return service.create(ls);
    }
    public boolean updateLSViPham(LS_ViPham ls) {
        return service.update(ls);
    }
    public boolean deleteLSViPham(String maLSViPham) {
        return service.delete(maLSViPham);
    }
    public List<LS_ViPham> getLSViPhamByMSSV(String mssv) {
        return service.getByMSSV(mssv);
    }
}