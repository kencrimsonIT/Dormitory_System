package com.dormiroty.entity;

import java.time.LocalDateTime;

public class SinhVien {
    private int MSSV;
    private String HoTen;
    private String GioiTinh;
    private LocalDateTime NgaySinh;
    private String NganhHoc;
    private String Email;
    private String SDT;
    private String MaDCS;

    public SinhVien() {
    }

    public SinhVien(int MSSV, String hoTen, String gioiTinh, LocalDateTime ngaySinh, String nganhHoc, String email, String SDT, String maDCS) {
        this.MSSV = MSSV;
        HoTen = hoTen;
        GioiTinh = gioiTinh;
        NgaySinh = ngaySinh;
        NganhHoc = nganhHoc;
        Email = email;
        this.SDT = SDT;
        MaDCS = maDCS;
    }

    public int getMSSV() {
        return MSSV;
    }

    public void setMSSV(int MSSV) {
        this.MSSV = MSSV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public LocalDateTime getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(LocalDateTime ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getNganhHoc() {
        return NganhHoc;
    }

    public void setNganhHoc(String nganhHoc) {
        NganhHoc = nganhHoc;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMaDCS() { return MaDCS; }
    public void setMaDCS(String maDCS) { MaDCS = maDCS; }
}
