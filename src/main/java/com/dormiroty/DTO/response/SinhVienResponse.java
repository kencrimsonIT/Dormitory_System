package com.dormiroty.DTO.response;

import java.time.LocalDateTime;

public record SinhVienResponse(
    int mssv,
    String hoTen,
    String gioiTinh,
    LocalDateTime ngaySinh,
    String nganhHoc,
    String email,
    String sdt
) {}
