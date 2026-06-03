package com.dormiroty.DTO.response;

import java.time.LocalDateTime;

public record ViPhamResponse(
    String maNV,
    int mssv,
    String tenLoaiViPham,
    String maLoaiViPham,
    LocalDateTime ngayViPham,
    String hinhThucXuLi
) {}
