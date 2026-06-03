package com.dormiroty.entity;

import java.time.LocalDateTime;

public class ViPham {
    private String MaNV;
    private int MSSV;
    private String TenLoaiViPham;
    private String MaLoaiViPham;
    private LocalDateTime NgayviPham;
    private String HinhThucXuLi;

    public ViPham() {
    }

    public ViPham(String maNV, int MSSV, String tenLoaiViPham, String maLoaiViPham, LocalDateTime ngayviPham, String hinhThucXuLi) {
        MaNV = maNV;
        this.MSSV = MSSV;
        TenLoaiViPham = tenLoaiViPham;
        MaLoaiViPham = maLoaiViPham;
        NgayviPham = ngayviPham;
        HinhThucXuLi = hinhThucXuLi;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public int getMSSV() {
        return MSSV;
    }

    public void setMSSV(int MSSV) {
        this.MSSV = MSSV;
    }

    public String getTenLoaiViPham() {
        return TenLoaiViPham;
    }

    public void setTenLoaiViPham(String tenLoaiViPham) {
        TenLoaiViPham = tenLoaiViPham;
    }

    public String getMaLoaiViPham() {
        return MaLoaiViPham;
    }

    public void setMaLoaiViPham(String maLoaiViPham) {
        MaLoaiViPham = maLoaiViPham;
    }

    public LocalDateTime getNgayviPham() {
        return NgayviPham;
    }

    public void setNgayviPham(LocalDateTime ngayviPham) {
        NgayviPham = ngayviPham;
    }

    public String getHinhThucXuLi() {
        return HinhThucXuLi;
    }

    public void setHinhThucXuLi(String hinhThucXuLi) {
        HinhThucXuLi = hinhThucXuLi;
    }
}
