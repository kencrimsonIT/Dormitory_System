package com.dormiroty.controller;

import com.dormiroty.entity.ToaNha;
import com.dormiroty.service.ToaNhaService;
import com.dormiroty.service.impl.ToaNhaServiceImpl;

import java.util.List;

public class ToaNhaController {

    private ToaNhaService service = new ToaNhaServiceImpl();

    public List<ToaNha> getAllToaNha() {
        return service.getAll();
    }

    public boolean addToaNha(ToaNha tn) {
        return service.create(tn);
    }

    public boolean updateToaNha(ToaNha tn) {
        return service.update(tn);
    }

    public boolean deleteToaNha(String maToaNha) {
        return service.delete(maToaNha);
    }
}