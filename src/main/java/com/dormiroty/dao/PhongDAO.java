package com.dormiroty.dao;

import com.dormiroty.entity.Phong;

import java.util.List;

public interface PhongDAO {
    List<Phong> findAll();
    Phong findById(String maPhong);
    List<Phong> findAvailableRooms();
    boolean save(Phong phong);
    boolean update(Phong phong);
    boolean delete(String maPhong);
}
