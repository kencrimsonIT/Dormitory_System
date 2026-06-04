package com.dormiroty.dao;

import com.dormiroty.entity.ViPham;

import java.util.List;

public interface ViPhamDAO {
    List<ViPham> findAll();
    ViPham findById(String maLoaiViPham);
    boolean save(ViPham viPham);
    boolean update(ViPham viPham);
    boolean delete(String maLoaiViPham);
}
