package com.dormiroty.entity;

public class DienChinhSach {
    private String MaDCS;
    private String TenDCS;
    private String MucNienGiam;

    public DienChinhSach() {
    }

    public DienChinhSach(String maDCS, String tenDCS, String mucNienGiam) {
        MaDCS = maDCS;
        TenDCS = tenDCS;
        MucNienGiam = mucNienGiam;
    }

    public String getMaDCS() {
        return MaDCS;
    }

    public void setMaDCS(String maDCS) {
        MaDCS = maDCS;
    }

    public String getTenDCS() {
        return TenDCS;
    }

    public void setTenDCS(String tenDCS) {
        TenDCS = tenDCS;
    }

    public String getMucNienGiam() {
        return MucNienGiam;
    }

    public void setMucNienGiam(String mucNienGiam) {
        MucNienGiam = mucNienGiam;
    }
}
