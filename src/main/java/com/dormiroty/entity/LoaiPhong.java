package com.dormiroty.entity;

import java.math.BigDecimal;

public class LoaiPhong {
    private String MaLoaiPhong;
    private String TenLoaiPhong;
    private short SucChua;
    private BigDecimal DonGia;

    public LoaiPhong() {
    }

    public LoaiPhong(String maLoaiPhong, String tenLoaiPhong, short sucChua, BigDecimal donGia) {
        MaLoaiPhong = maLoaiPhong;
        TenLoaiPhong = tenLoaiPhong;
        SucChua = sucChua;
        DonGia = donGia;
    }

    public String getMaLoaiPhong() {
        return MaLoaiPhong;
    }

    public void setMaLoaiPhong(String maLoaiPhong) {
        MaLoaiPhong = maLoaiPhong;
    }

    public String getTenLoaiPhong() {
        return TenLoaiPhong;
    }

    public void setTenLoaiPhong(String tenLoaiPhong) {
        TenLoaiPhong = tenLoaiPhong;
    }

    public short getSucChua() {
        return SucChua;
    }

    public void setSucChua(short sucChua) {
        SucChua = sucChua;
    }

    public BigDecimal getDonGia() {
        return DonGia;
    }

    public void setDonGia(BigDecimal donGia) {
        DonGia = donGia;
    }
}
