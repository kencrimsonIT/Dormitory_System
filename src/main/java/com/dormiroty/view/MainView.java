package com.dormiroty.view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainView extends JFrame {
    private static final long serialVersionUID = 1L;

    public MainView() {
        setTitle("Quản lý KTX");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Sinh viên", new SinhVienPanel());
        tabs.addTab("Nhân viên", new NhanVienPanel());
        tabs.addTab("Phòng", new PhongPanel());
        tabs.addTab("Loại phòng", new LoaiPhongPanel());
        tabs.addTab("Hợp đồng", new HopDongPanel());
        tabs.addTab("Vi phạm", new ViPhamPanel());
        tabs.addTab("Tòa nhà", new ToaNhaPanel());
        tabs.addTab("Diện chính sách", new DienChinhSachPanel());

        add(tabs);
    }

    public static void main(String[] args) {
        new MainView().setVisible(true);
    }
}

