package com.dormiroty.view;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.dormiroty.controller.SinhVienController;
import com.dormiroty.entity.SinhVien;

public class SinhVienPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private SinhVienController controller;

    private JTextField txtMSSV;
    private JTextField txtHoTen;
    private JTextField txtNgaySinh;
    private JTextField txtNganhHoc;
    private JTextField txtEmail;
    private JTextField txtSDT;
    private JTextField txtMaDCS;
    private JTextField txtTimKiem;

    private JComboBox<String> cboGioiTinh;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnTim;

    private JTable table;
    private DefaultTableModel model;

    public SinhVienPanel() {
    	System.out.println("SinhVienPanel constructor");
        controller = new SinhVienController();

        setLayout(new GridLayout(11, 2, 5, 5));

        txtMSSV = new JTextField();
        txtHoTen = new JTextField();
        txtNgaySinh = new JTextField();
        txtNganhHoc = new JTextField();
        txtEmail = new JTextField();
        txtSDT = new JTextField();
        txtMaDCS = new JTextField();
        txtTimKiem = new JTextField();

        cboGioiTinh = new JComboBox<>(new String[] { "Nam", "Nữ" });

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnTim = new JButton("Tìm");

        add(new JLabel("MSSV"));
        add(txtMSSV);

        add(new JLabel("Họ tên"));
        add(txtHoTen);

        add(new JLabel("Giới tính"));
        add(cboGioiTinh);

        add(new JLabel("Ngày sinh"));
        add(txtNgaySinh);

        add(new JLabel("Ngành học"));
        add(txtNganhHoc);

        add(new JLabel("Email"));
        add(txtEmail);

        add(new JLabel("SĐT"));
        add(txtSDT);

        add(new JLabel("Mã DCS"));
        add(txtMaDCS);

        add(btnThem);
        add(btnSua);

        add(btnXoa);
        add(btnTim);

        String[] columns = {
                "MSSV",
                "Họ tên",
                "Giới tính",
                "Ngày sinh",
                "Ngành học",
                "Email",
                "SĐT",
                "Mã DCS"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();

            if (row >= 0) {
                txtMSSV.setText(model.getValueAt(row, 0).toString());
                txtHoTen.setText(model.getValueAt(row, 1).toString());
                cboGioiTinh.setSelectedItem(model.getValueAt(row, 2).toString());
                txtNgaySinh.setText(model.getValueAt(row, 3).toString());
                txtNganhHoc.setText(model.getValueAt(row, 4).toString());
                txtEmail.setText(model.getValueAt(row, 5).toString());
                txtSDT.setText(model.getValueAt(row, 6).toString());

                Object maDCS = model.getValueAt(row, 7);
                txtMaDCS.setText(maDCS == null ? "" : maDCS.toString());
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        btnThem.addActionListener(e -> {
            try {
                SinhVien sv = new SinhVien();

                sv.setMSSV(Integer.parseInt(txtMSSV.getText().trim()));
                sv.setHoTen(txtHoTen.getText().trim());
                sv.setGioiTinh(cboGioiTinh.getSelectedItem().toString());

                sv.setNgaySinh(
                        java.time.LocalDateTime.parse(txtNgaySinh.getText().trim())
                );

                sv.setNganhHoc(txtNganhHoc.getText().trim());
                sv.setEmail(txtEmail.getText().trim());
                sv.setSDT(txtSDT.getText().trim());
                sv.setMaDCS(txtMaDCS.getText().trim());

                System.out.println("========== DU LIEU THEM ==========");
                System.out.println("MSSV = " + sv.getMSSV());
                System.out.println("HoTen = " + sv.getHoTen());
                System.out.println("GioiTinh = " + sv.getGioiTinh());
                System.out.println("NgaySinh = " + sv.getNgaySinh());
                System.out.println("NganhHoc = " + sv.getNganhHoc());
                System.out.println("Email = " + sv.getEmail());
                System.out.println("SDT = " + sv.getSDT());
                System.out.println("MaDCS = [" + sv.getMaDCS() + "]");
                System.out.println("=================================");

                boolean result = controller.addSinhVien(sv);

                if (result) {
                    javax.swing.JOptionPane.showMessageDialog(
                            this,
                            "Thêm thành công!"
                    );

                    loadTable();

                    txtMSSV.setText("");
                    txtHoTen.setText("");
                    txtNgaySinh.setText("");
                    txtNganhHoc.setText("");
                    txtEmail.setText("");
                    txtSDT.setText("");
                    txtMaDCS.setText("");

                } else {
                    javax.swing.JOptionPane.showMessageDialog(
                            this,
                            "Thêm thất bại! Kiểm tra Console."
                    );
                }

            } catch (Exception ex) {
                ex.printStackTrace();

                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Lỗi dữ liệu: " + ex.getMessage()
                );
            }
        });
        loadTable();
    }
    private void loadTable() {
    	 System.out.println("loadTable duoc goi");
        model.setRowCount(0);

        List<SinhVien> list = controller.getAllSinhVien();

        System.out.println("So sinh vien = " + list.size());

        for (SinhVien sv : list) {

            System.out.println(sv.getHoTen());

            model.addRow(new Object[] {
                    sv.getMSSV(),
                    sv.getHoTen(),
                    sv.getGioiTinh(),
                    sv.getNgaySinh(),
                    sv.getNganhHoc(),
                    sv.getEmail(),
                    sv.getSDT(),
                    sv.getMaDCS()
            });
        }
    }
    
}