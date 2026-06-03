package com.dormiroty.DTO.response;

import java.math.BigDecimal;

public record LoaiPhongResponse(
    String maLoaiPhong,
    String tenLoaiPhong,
    short sucChua,
    BigDecimal donGia
) {}
