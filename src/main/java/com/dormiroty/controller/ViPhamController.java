package com.dormiroty.controller;

import com.dormiroty.entity.ViPham;
import com.dormiroty.service.ViPhamService;
import com.dormiroty.service.impl.ViPhamServiceImpl;

import java.util.List;

public class ViPhamController {

    private ViPhamService service = new ViPhamServiceImpl();

    public List<ViPham> getAllViPham() {
        return service.getAll();
    }

    public boolean addViPham(ViPham vp) {
        return service.create(vp);
    }

    public boolean updateViPham(ViPham vp) {
        return service.update(vp);
    }

    public boolean deleteViPham(String maLoaiViPham) {
        return service.delete(maLoaiViPham);
    }
}
