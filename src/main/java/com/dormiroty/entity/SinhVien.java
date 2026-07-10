package com.dormiroty.entity;

import java.time.LocalDateTime;

public class SinhVien {
    private String mssv;
    private String hoTen;
    private LocalDateTime ngaySinh;
    private String queQuan;
    private String gioiTinh;
    private String email;
    private String sdt;
    private String nganhHoc;
    private int nam;
    private String maDCS;

    public SinhVien() {}

    // Getters and Setters
    public String getMssv() { return mssv; }
    public void setMssv(String mssv) { this.mssv = mssv; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public LocalDateTime getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(LocalDateTime ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getQueQuan() { return queQuan; }
    public void setQueQuan(String queQuan) { this.queQuan = queQuan; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }

    public String getNganhHoc() { return nganhHoc; }
    public void setNganhHoc(String nganhHoc) { this.nganhHoc = nganhHoc; }

    public int getNam() { return nam; }
    public void setNam(int nam) { this.nam = nam; }

    public String getMaDCS() { return maDCS; }
    public void setMaDCS(String maDCS) { this.maDCS = maDCS; }
}