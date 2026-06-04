package com.dormiroty.dao;

import com.dormiroty.entity.LoaiPhong;

import java.util.List;

public interface LoaiPhongDAO {
    List<LoaiPhong> findAll();
    LoaiPhong findById(String maLoaiPhong);
    boolean save(LoaiPhong loaiPhong);
    boolean update(LoaiPhong loaiPhong);
    boolean delete(String maLoaiPhong);
}
