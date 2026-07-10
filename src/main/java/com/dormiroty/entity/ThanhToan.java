package com.dormiroty.entity;

import java.time.LocalDateTime;

public class ThanhToan {
    private String maHD;
    private LocalDateTime ngayThanhToan;

    public ThanhToan() {}

    public String getMaHD() { return maHD; }
    public void setMaHD(String maHD) { this.maHD = maHD; }

    public LocalDateTime getNgayThanhToan() { return ngayThanhToan; }
    public void setNgayThanhToan(LocalDateTime ngayThanhToan) { this.ngayThanhToan = ngayThanhToan; }
}