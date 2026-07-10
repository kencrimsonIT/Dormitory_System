package com.dormiroty.service;
import com.dormiroty.entity.HoaDon;
import java.util.List;

public interface HoaDonService {
    List<HoaDon> getAll();
    HoaDon getById(String maHD);
    boolean create(HoaDon hd);
    boolean update(HoaDon hd);
    boolean delete(String maHD);
    List<HoaDon> getByMSSV(String mssv);
    List<HoaDon> getByMaHopDong(String maHopDong);
}