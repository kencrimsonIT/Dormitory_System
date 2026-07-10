package com.dormiroty.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HopDong {
    private String maHopDong;
    private String maNV;
    private String mssv;
    private String maPhong;
    private LocalDateTime ngayLap;
    private LocalDateTime ngayVaoO;
    private LocalDateTime ngayHetHan;
    private BigDecimal tienCoc;
    private String trangThaiHopDong;

    public HopDong() {}

    public String getMaHopDong() { return maHopDong; }
    public void setMaHopDong(String maHopDong) { this.maHopDong = maHopDong; }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public String getMssv() { return mssv; }
    public void setMssv(String mssv) { this.mssv = mssv; }

    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }

    public LocalDateTime getNgayLap() { return ngayLap; }
    public void setNgayLap(LocalDateTime ngayLap) { this.ngayLap = ngayLap; }

    public LocalDateTime getNgayVaoO() { return ngayVaoO; }
    public void setNgayVaoO(LocalDateTime ngayVaoO) { this.ngayVaoO = ngayVaoO; }

    public LocalDateTime getNgayHetHan() { return ngayHetHan; }
    public void setNgayHetHan(LocalDateTime ngayHetHan) { this.ngayHetHan = ngayHetHan; }

    public BigDecimal getTienCoc() { return tienCoc; }
    public void setTienCoc(BigDecimal tienCoc) { this.tienCoc = tienCoc; }

    public String getTrangThaiHopDong() { return trangThaiHopDong; }
    public void setTrangThaiHopDong(String trangThaiHopDong) { this.trangThaiHopDong = trangThaiHopDong; }
}