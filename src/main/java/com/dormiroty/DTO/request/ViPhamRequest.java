package com.dormiroty.DTO.request;

import java.time.LocalDateTime;

public record ViPhamRequest(
    String maNV,
    int mssv,
    String tenLoaiViPham,
    String maLoaiViPham,
    LocalDateTime ngayViPham,
    String hinhThucXuLi
) {}
