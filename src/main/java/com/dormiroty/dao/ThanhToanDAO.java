package com.dormiroty.dao;

import com.dormiroty.entity.ThanhToan;
import java.util.List;

public interface ThanhToanDAO {
    List<ThanhToan> findAll();
    ThanhToan findById(String maHD);
    boolean save(ThanhToan tt);
    boolean update(ThanhToan tt);
    boolean delete(String maHD);
}