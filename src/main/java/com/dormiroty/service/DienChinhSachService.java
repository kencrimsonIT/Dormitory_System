package com.dormiroty.service;

import com.dormiroty.entity.DienChinhSach;

import java.util.List;

public interface DienChinhSachService {
    List<DienChinhSach> getAll();
    DienChinhSach getById(String maDCS);
    boolean create(DienChinhSach dcs);
    boolean update(DienChinhSach dcs);
    boolean delete(String maDCS);
}
