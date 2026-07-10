package com.dormiroty.service;

import com.dormiroty.entity.LS_ViPham;
import java.util.List;

public interface LS_ViPhamService {
    List<LS_ViPham> getAll();
    LS_ViPham getById(String maLSViPham);
    boolean create(LS_ViPham ls);
    boolean update(LS_ViPham ls);
    boolean delete(String maLSViPham);
    List<LS_ViPham> getByMSSV(String mssv);
}