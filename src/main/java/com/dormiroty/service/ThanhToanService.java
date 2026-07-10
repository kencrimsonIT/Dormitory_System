package com.dormiroty.service;
import com.dormiroty.entity.ThanhToan;
import java.util.List;

public interface ThanhToanService {
    List<ThanhToan> getAll();
    ThanhToan getById(String maHD);
    boolean create(ThanhToan tt);
    boolean update(ThanhToan tt);
    boolean delete(String maHD);
}