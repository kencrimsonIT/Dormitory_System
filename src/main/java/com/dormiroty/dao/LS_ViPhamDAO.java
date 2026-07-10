package com.dormiroty.dao;

import com.dormiroty.entity.LS_ViPham;
import java.util.List;

public interface LS_ViPhamDAO {
    List<LS_ViPham> findAll();
    LS_ViPham findById(String maLSViPham);
    boolean save(LS_ViPham ls);
    boolean update(LS_ViPham ls);
    boolean delete(String maLSViPham);
    List<LS_ViPham> findByMSSV(String mssv);
}