package com.dormiroty.DTO.request;

import java.time.LocalDateTime;

public record SinhVienRequest(
    int mssv,
    String hoTen,
    String gioiTinh,
    LocalDateTime ngaySinh,
    String nganhHoc,
    String email,
    String sdt
) {}
