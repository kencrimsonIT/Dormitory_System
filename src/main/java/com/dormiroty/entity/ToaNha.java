package com.dormiroty.entity;

public class ToaNha {
    private String MaToaNha;
    private String TenToaNha;
    private String LoaiToaNha;

    public ToaNha() {
    }

    public ToaNha(String maToaNha, String tenToaNha, String loaiToaNha) {
        MaToaNha = maToaNha;
        TenToaNha = tenToaNha;
        LoaiToaNha = loaiToaNha;
    }

    public String getMaToaNha() {
        return MaToaNha;
    }

    public void setMaToaNha(String maToaNha) {
        MaToaNha = maToaNha;
    }

    public String getTenToaNha() {
        return TenToaNha;
    }

    public void setTenToaNha(String tenToaNha) {
        TenToaNha = tenToaNha;
    }

    public String getLoaiToaNha() {
        return LoaiToaNha;
    }

    public void setLoaiToaNha(String loaiToaNha) {
        LoaiToaNha = loaiToaNha;
    }
}
