package com.dormiroty.service;

import com.dormiroty.entity.LoaiPhong;

import java.util.List;

public interface LoaiPhongService {
    List<LoaiPhong> getAll();
    LoaiPhong getById(String maLoaiPhong);
    boolean create(LoaiPhong lp);
    boolean update(LoaiPhong lp);
    boolean delete(String maLoaiPhong);

}
