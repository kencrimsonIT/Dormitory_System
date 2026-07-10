package com.dormiroty.entity;

import java.time.LocalDateTime;

public class LS_OPhong {
    private String maLS;
    private String mssv;
    private String maPhong;
    private LocalDateTime ngayVaoO;
    private LocalDateTime ngayChuyenDi;
    private String trangThaiOPhong;

    public LS_OPhong() {}

    public String getMaLS() { return maLS; }
    public void setMaLS(String maLS) { this.maLS = maLS; }

    public String getMssv() { return mssv; }
    public void setMssv(String mssv) { this.mssv = mssv; }

    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }

    public LocalDateTime getNgayVaoO() { return ngayVaoO; }
    public void setNgayVaoO(LocalDateTime ngayVaoO) { this.ngayVaoO = ngayVaoO; }

    public LocalDateTime getNgayChuyenDi() { return ngayChuyenDi; }
    public void setNgayChuyenDi(LocalDateTime ngayChuyenDi) { this.ngayChuyenDi = ngayChuyenDi; }

    public String getTrangThaiOPhong() { return trangThaiOPhong; }
    public void setTrangThaiOPhong(String trangThaiOPhong) { this.trangThaiOPhong = trangThaiOPhong; }
}