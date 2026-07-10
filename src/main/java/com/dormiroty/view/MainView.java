package com.dormiroty.view;

import com.dormiroty.view.panels.*;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private static final long serialVersionUID = 1L;

    public MainView() {
        setTitle("Hệ Thống Quản Lý Ký Túc Xá");
        setSize(1400, 800);
        setMinimumSize(new Dimension(1200, 700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Segoe UI", Font.BOLD, 15));

        tabs.addTab("Sinh viên", new SinhVienPanel());
        tabs.addTab("Nhân viên", new NhanVienPanel());
        tabs.addTab("Phòng", new PhongPanel());
        tabs.addTab("Loại phòng", new LoaiPhongPanel());
        tabs.addTab("Hợp đồng", new HopDongPanel());
        tabs.addTab("Hóa đơn", new HoaDonPanel());
        tabs.addTab("Vi phạm", new ViPhamPanel());
        tabs.addTab("Lịch sử vi phạm", new LS_ViPhamPanel());
        tabs.addTab("Lịch sử ở phòng", new LS_OPhongPanel());
        tabs.addTab("Tòa nhà", new ToaNhaPanel());
        tabs.addTab("Điện chính sách", new DienChinhSachPanel());

        add(tabs);
    }

    public static void main(String[] args) {
        try {
            // Set up FlatLaf modern look-and-feel
            UIManager.put("TabbedPane.selectedBackground", new Color(0x1976D2));
            UIManager.put("TabbedPane.selectedForeground", Color.WHITE);
            UIManager.put("TabbedPane.font", new Font("Segoe UI", Font.BOLD, 14));
            UIManager.put("TabbedPane.tabHeight", 40);
            UIManager.put("TabbedPane.tabInsets", new Insets(8, 18, 8, 18));
            UIManager.put("TabbedPane.contentAreaColor", new Color(0xF5F5F5));
            UIManager.put("ScrollPane.smoothScrolling", true);

            FlatLightLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
            // Fallback to system L&F
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        SwingUtilities.invokeLater(() -> new MainView().setVisible(true));
    }
}
