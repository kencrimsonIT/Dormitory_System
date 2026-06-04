package com.dormiroty.dao;

import com.dormiroty.entity.ToaNha;

import java.util.List;

public interface ToaNhaDAO {
    List<ToaNha> findAll();
    ToaNha findById(String maToaNha);
    boolean save(ToaNha toaNha);
    boolean update(ToaNha toaNha);
    boolean delete(String maToaNha);
}
