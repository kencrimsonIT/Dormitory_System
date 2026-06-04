package com.dormiroty;

import com.dormiroty.service.ToaNhaService;
import com.dormiroty.service.impl.ToaNhaServiceImpl;

public class MainTest {
    public static void main(String[] args) {
        ToaNhaService toaNhaService = new ToaNhaServiceImpl();
        // Thử in ra danh sách tòa nhà xem có bị lỗi SQL không
        toaNhaService.getAll().forEach(tn -> {
            System.out.println("Tên tòa nhà: " + tn.getTenToaNha());
        });
    }
}
