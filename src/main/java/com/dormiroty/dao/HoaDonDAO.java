package com.dormiroty.dao;

import com.dormiroty.entity.HoaDon;
import java.util.List;

public interface HoaDonDAO {
    List<HoaDon> findAll();
    HoaDon findById(String maHD);
    boolean save(HoaDon hd);
    boolean update(HoaDon hd);
    boolean delete(String maHD);
    List<HoaDon> findByMSSV(String mssv);
    List<HoaDon> findByMaHopDong(String maHopDong);
}