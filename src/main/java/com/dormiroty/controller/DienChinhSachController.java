package com.dormiroty.controller;

import com.dormiroty.entity.DienChinhSach;
import com.dormiroty.service.DienChinhSachService;
import com.dormiroty.service.impl.DienChinhSachServiceImpl;

import java.util.List;

public class DienChinhSachController {

    private DienChinhSachService service = new DienChinhSachServiceImpl();

    public List<DienChinhSach> getAllDienChinhSach() {
        return service.getAll();
    }

    public boolean addDienChinhSach(DienChinhSach dcs) {
        return service.create(dcs);
    }

    public boolean updateDienChinhSach(DienChinhSach dcs) {
        return service.update(dcs);
    }

    public boolean deleteDienChinhSach(String maDCS) {
        return service.delete(maDCS);
    }
}