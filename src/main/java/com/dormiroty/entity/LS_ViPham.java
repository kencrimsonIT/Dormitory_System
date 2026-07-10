package com.dormiroty.entity;

import java.time.LocalDateTime;

public class LS_ViPham {
    private String maLSViPham;
    private String maLoaiViPham;
    private String mssv;
    private LocalDateTime ngayViPham;
    private String maNV;
    private String hinhThucXuLi;

    public LS_ViPham() {}

    public String getMaLSViPham() { return maLSViPham; }
    public void setMaLSViPham(String maLSViPham) { this.maLSViPham = maLSViPham; }

    public String getMaLoaiViPham() { return maLoaiViPham; }
    public void setMaLoaiViPham(String maLoaiViPham) { this.maLoaiViPham = maLoaiViPham; }

    public String getMssv() { return mssv; }
    public void setMssv(String mssv) { this.mssv = mssv; }

    public LocalDateTime getNgayViPham() { return ngayViPham; }
    public void setNgayViPham(LocalDateTime ngayViPham) { this.ngayViPham = ngayViPham; }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public String getHinhThucXuLi() { return hinhThucXuLi; }
    public void setHinhThucXuLi(String hinhThucXuLi) { this.hinhThucXuLi = hinhThucXuLi; }
}