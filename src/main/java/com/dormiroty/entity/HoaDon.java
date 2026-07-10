package com.dormiroty.entity;

import java.math.BigDecimal;

public class HoaDon {
    private String maHD;
    private String maHopDong;
    private String maNV;
    private String mssv;
    private BigDecimal soTienTra;
    private String hinhThuc;

    public HoaDon() {}

    public String getMaHD() { return maHD; }
    public void setMaHD(String maHD) { this.maHD = maHD; }

    public String getMaHopDong() { return maHopDong; }
    public void setMaHopDong(String maHopDong) { this.maHopDong = maHopDong; }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public String getMssv() { return mssv; }
    public void setMssv(String mssv) { this.mssv = mssv; }

    public BigDecimal getSoTienTra() { return soTienTra; }
    public void setSoTienTra(BigDecimal soTienTra) { this.soTienTra = soTienTra; }

    public String getHinhThuc() { return hinhThuc; }
    public void setHinhThuc(String hinhThuc) { this.hinhThuc = hinhThuc; }
}