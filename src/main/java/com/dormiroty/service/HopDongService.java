package com.dormiroty.service;

import com.dormiroty.entity.HopDong;

import java.util.List;

public interface HopDongService {
    List<HopDong> getAll();
    HopDong getById(String maHopDong);
    boolean createContract(HopDong hopDong);
    boolean update(HopDong hopDong);
    boolean delete(String maHopDong);
    double calculateTotalTienCoc();
}
