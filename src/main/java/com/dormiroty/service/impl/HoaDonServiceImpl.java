package com.dormiroty.service.impl;

import com.dormiroty.dao.HoaDonDAO;
import com.dormiroty.dao.impl.HoaDonDAOImpl;
import com.dormiroty.entity.HoaDon;
import com.dormiroty.service.HoaDonService;

import java.math.BigDecimal;
import java.util.List;

public class HoaDonServiceImpl implements HoaDonService {
    private HoaDonDAO dao = new HoaDonDAOImpl();

    @Override
    public List<HoaDon> getAll() { return dao.findAll(); }

    @Override
    public HoaDon getById(String maHD) { return dao.findById(maHD); }

    @Override
    public boolean create(HoaDon hd) {
        if (dao.findById(hd.getMaHD()) != null) {
            System.out.println("Lỗi: Mã Hóa Đơn này đã tồn tại!");
            return false;
        }
        if (!"Tiền mặt".equals(hd.getHinhThuc()) && !"Chuyển khoản".equals(hd.getHinhThuc())) {
            System.out.println("Lỗi: Hình thức thanh toán phải là 'Tiền mặt' hoặc 'Chuyển khoản'");
            return false;
        }

        if (hd.getSoTienTra().compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("Lỗi: Số tiền trả không được âm!");
            return false;
        }

        return dao.save(hd);
    }

    @Override
    public boolean update(HoaDon hd) {
        if (!"Tiền mặt".equals(hd.getHinhThuc()) && !"Chuyển khoản".equals(hd.getHinhThuc())) {
            return false;
        }
        return dao.update(hd);
    }

    @Override
    public boolean delete(String maHD) {
        return dao.delete(maHD);
    }

    @Override
    public List<HoaDon> getByMSSV(String mssv) { return dao.findByMSSV(mssv); }

    @Override
    public List<HoaDon> getByMaHopDong(String maHopDong) { return dao.findByMaHopDong(maHopDong); }
}