package com.dormiroty.service;

import com.dormiroty.entity.Phong;

import java.util.List;

public interface PhongService {
    List<Phong> getAll();
    Phong getById(String maPhong);
    List<Phong> getAvailableRooms();
    boolean create(Phong phong);
    boolean update(Phong phong);
    boolean delete(String maPhong);
    List<Phong> findByToaNha(String maToaNha);
}
