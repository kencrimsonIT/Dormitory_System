package com.dormiroty.view;

import javax.swing.JFrame;

public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;

    public MainView() {
        setTitle("Quản lý KTX");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new SinhVienPanel());
    }
    public static void main(String[] args) {
        new MainView().setVisible(true);
    }
}
