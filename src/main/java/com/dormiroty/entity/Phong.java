package com.dormiroty.entity;

public class Phong {
    private String MaPhong;
    private String MaLoaiPhong;
    private String MaToaNha;
    private String TinhTrang;

    public Phong() {
    }

    public Phong(String maPhong, String maLoaiPhong, String maToaNha, String tinhTrang) {
        MaPhong = maPhong;
        MaLoaiPhong = maLoaiPhong;
        MaToaNha = maToaNha;
        TinhTrang = tinhTrang;
    }

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String maPhong) {
        MaPhong = maPhong;
    }

    public String getMaLoaiPhong() {
        return MaLoaiPhong;
    }

    public void setMaLoaiPhong(String maLoaiPhong) {
        MaLoaiPhong = maLoaiPhong;
    }

    public String getMaToaNha() {
        return MaToaNha;
    }

    public void setMaToaNha(String maToaNha) {
        MaToaNha = maToaNha;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        TinhTrang = tinhTrang;
    }
}
