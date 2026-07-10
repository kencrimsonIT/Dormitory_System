package com.dormiroty.entity;

public class Phong {
    private String maPhong;
    private String maLoaiPhong;
    private String maToaNha;
    private int soChoTrong;
    private String trangThai;

    public Phong() {}

    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }

    public String getMaLoaiPhong() { return maLoaiPhong; }
    public void setMaLoaiPhong(String maLoaiPhong) { this.maLoaiPhong = maLoaiPhong; }

    public String getMaToaNha() { return maToaNha; }
    public void setMaToaNha(String maToaNha) { this.maToaNha = maToaNha; }

    public int getSoChoTrong() { return soChoTrong; }
    public void setSoChoTrong(int soChoTrong) { this.soChoTrong = soChoTrong; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}