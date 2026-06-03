package com.dormiroty.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HopDong {
    private String MaHopDong;
    private int MSSV;
    private String MaNV;
    private BigDecimal TienCoc;
    private LocalDateTime NgayHetHan;
    private LocalDateTime NgayLap;
    private String MaPhong;

    public HopDong() {
    }

    public HopDong(String maHopDong, int MSSV, String maNV, BigDecimal tienCoc, LocalDateTime ngayHetHan, LocalDateTime ngayLap, String maPhong) {
        MaHopDong = maHopDong;
        this.MSSV = MSSV;
        MaNV = maNV;
        TienCoc = tienCoc;
        NgayHetHan = ngayHetHan;
        NgayLap = ngayLap;
        MaPhong = maPhong;
    }

    public String getMaHopDong() {
        return MaHopDong;
    }

    public void setMaHopDong(String maHopDong) {
        MaHopDong = maHopDong;
    }

    public int getMSSV() {
        return MSSV;
    }

    public void setMSSV(int MSSV) {
        this.MSSV = MSSV;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public BigDecimal getTienCoc() {
        return TienCoc;
    }

    public void setTienCoc(BigDecimal tienCoc) {
        TienCoc = tienCoc;
    }

    public LocalDateTime getNgayHetHan() {
        return NgayHetHan;
    }

    public void setNgayHetHan(LocalDateTime ngayHetHan) {
        NgayHetHan = ngayHetHan;
    }

    public LocalDateTime getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(LocalDateTime ngayLap) {
        NgayLap = ngayLap;
    }

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String maPhong) {
        MaPhong = maPhong;
    }
}
