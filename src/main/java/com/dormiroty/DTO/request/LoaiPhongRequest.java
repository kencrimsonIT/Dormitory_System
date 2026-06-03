package com.dormiroty.DTO.request;

import java.math.BigDecimal;

public record LoaiPhongRequest(
    String maLoaiPhong,
    String tenLoaiPhong,
    short sucChua,
    BigDecimal donGia
) {}
