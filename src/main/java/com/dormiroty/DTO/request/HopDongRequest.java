package com.dormiroty.DTO.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record HopDongRequest(
    String maHopDong,
    int mssv,
    String maNV,
    BigDecimal tienCoc,
    LocalDateTime ngayHetHan,
    LocalDateTime ngayLap,
    String maPhong
) {}
