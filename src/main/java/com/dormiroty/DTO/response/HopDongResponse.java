package com.dormiroty.DTO.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record HopDongResponse(
    String maHopDong,
    int mssv,
    String maNV,
    BigDecimal tienCoc,
    LocalDateTime ngayHetHan,
    LocalDateTime ngayLap,
    String maPhong
) {}
