package com.dormiroty.entity;

public class NhanVien {
    private String maNV;
    private String hoTenNV;
    private String gioiTinh;
    private String sdt;
    private String email;
    private String chucVu;
    private String maToaNha;

    public NhanVien() {}

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public String getHoTenNV() { return hoTenNV; }
    public void setHoTenNV(String hoTenNV) { this.hoTenNV = hoTenNV; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getChucVu() { return chucVu; }
    public void setChucVu(String chucVu) { this.chucVu = chucVu; }

    public String getMaToaNha() { return maToaNha; }
    public void setMaToaNha(String maToaNha) { this.maToaNha = maToaNha; }
}