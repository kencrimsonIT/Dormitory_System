package com.dormiroty.service;

import com.dormiroty.entity.LS_OPhong;
import java.util.List;

public interface LS_OPhongService {
    List<LS_OPhong> getAll();
    LS_OPhong getById(String maLS);
    boolean create(LS_OPhong ls);
    boolean update(LS_OPhong ls);
    boolean delete(String maLS);
    List<LS_OPhong> getByMSSV(String mssv);
}