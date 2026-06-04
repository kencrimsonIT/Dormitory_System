package com.dormiroty.dao;

import com.dormiroty.entity.DienChinhSach;

import java.util.List;

public interface DienChinhSachDAO {
    List<DienChinhSach> findAll();
    DienChinhSach findById(String maDCS);
    boolean save(DienChinhSach dcs);
    boolean update(DienChinhSach dcs);
    boolean delete(String maDCS);
}
