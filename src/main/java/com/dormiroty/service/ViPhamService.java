package com.dormiroty.service;

import com.dormiroty.entity.ViPham;

import java.util.List;

public interface ViPhamService {
    List<ViPham> getAll();
    ViPham getById(String maLoaiViPham);
    boolean create(ViPham vp);
    boolean update(ViPham vp);
    boolean delete(String maLoaiViPham);
}
