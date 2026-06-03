package com.dormiroty.dao;

import com.dormiroty.entity.HopDong;

import java.util.List;

public interface HopDongDAO {
    List<HopDong> findAll();
    HopDong findById(String maHopDong);
    boolean save(HopDong hopDong);
    boolean update(HopDong hopDong);
    boolean delete(String maHopDong);
}
