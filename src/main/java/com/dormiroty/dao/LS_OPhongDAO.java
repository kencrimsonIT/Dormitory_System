package com.dormiroty.dao;

import com.dormiroty.entity.LS_OPhong;
import java.util.List;

public interface LS_OPhongDAO {
    List<LS_OPhong> findAll();
    LS_OPhong findById(String maLS);
    boolean save(LS_OPhong ls);
    boolean update(LS_OPhong ls);
    boolean delete(String maLS);
    List<LS_OPhong> findByMSSV(String mssv);
}