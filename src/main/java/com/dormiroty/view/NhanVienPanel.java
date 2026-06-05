package com.dormiroty.view;

import com.dormiroty.controller.NhanVienController;
import com.dormiroty.entity.NhanVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class NhanVienPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private NhanVienController controller;

    private JTextField txtMaNV;
    private JTextField txtHoTen;
    private JTextField txtSDT;
    private JTextField txtEmail;
    private JTextField txtChucVu;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;

    private JTable table;
    private DefaultTableModel model;

    public NhanVienPanel() {

        controller = new NhanVienController();

        setLayout(new GridLayout(10, 2, 5, 5));

        txtMaNV = new JTextField();
        txtHoTen = new JTextField();
        txtSDT = new JTextField();
        txtEmail = new JTextField();
        txtChucVu = new JTextField();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        add(new JLabel("Mã nhân viên"));
        add(txtMaNV);

        add(new JLabel("Họ tên"));
        add(txtHoTen);

        add(new JLabel("SĐT"));
        add(txtSDT);

        add(new JLabel("Email"));
        add(txtEmail);

        add(new JLabel("Chức vụ"));
        add(txtChucVu);

        add(btnThem);
        add(btnSua);

        add(btnXoa);
        add(new JLabel(""));

        String[] columns = {
                "Mã NV",
                "Họ tên",
                "SĐT",
                "Email",
                "Chức vụ"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        loadTable();

        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if (row >= 0) {
                txtMaNV.setText(model.getValueAt(row, 0).toString());
                txtHoTen.setText(model.getValueAt(row, 1).toString());
                txtSDT.setText(model.getValueAt(row, 2).toString());
                txtEmail.setText(model.getValueAt(row, 3).toString());
                txtChucVu.setText(model.getValueAt(row, 4).toString());
            }
        });

        btnThem.addActionListener(e -> {

            NhanVien nv = new NhanVien();

            nv.setMaNV(txtMaNV.getText());
            nv.setHoTenNV(txtHoTen.getText());
            nv.setSDT(txtSDT.getText());
            nv.setEmail(txtEmail.getText());
            nv.setChucVu(txtChucVu.getText());

            if (controller.addNhanVien(nv)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                loadTable();
            }
        });

        btnSua.addActionListener(e -> {

            NhanVien nv = new NhanVien();

            nv.setMaNV(txtMaNV.getText());
            nv.setHoTenNV(txtHoTen.getText());
            nv.setSDT(txtSDT.getText());
            nv.setEmail(txtEmail.getText());
            nv.setChucVu(txtChucVu.getText());

            if (controller.updateNhanVien(nv)) {
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                loadTable();
            }
        });

        btnXoa.addActionListener(e -> {

            if (controller.deleteNhanVien(txtMaNV.getText())) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                loadTable();
            }
        });
    }

    private void loadTable() {

        model.setRowCount(0);

        List<NhanVien> list = controller.getAllNhanVien();

        for (NhanVien nv : list) {

            model.addRow(new Object[] {
                    nv.getMaNV(),
                    nv.getHoTenNV(),
                    nv.getSDT(),
                    nv.getEmail(),
                    nv.getChucVu()
            });
        }
    }
}