package com.dormiroty.service;

import com.dormiroty.entity.ToaNha;

import java.util.List;

public interface ToaNhaService {
    List<ToaNha> getAll();
    ToaNha getById(String maToaNha);
    boolean create(ToaNha tn);
    boolean update(ToaNha tn);
    boolean delete(String maToaNha);
}
