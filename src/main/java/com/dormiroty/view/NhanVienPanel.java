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
    private JComboBox<String> cbGioiTinh;
    private JTextField txtSDT;
    private JTextField txtEmail;
    private JTextField txtChucVu;
    private JTextField txtMaToaNha;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;

    private JTable table;
    private DefaultTableModel model;

    public NhanVienPanel() {

        controller = new NhanVienController();
        setLayout(new GridLayout(14, 2, 5, 5));

        txtMaNV = new JTextField();
        txtHoTen = new JTextField();
        cbGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});

        txtSDT = new JTextField();
        txtEmail = new JTextField();
        txtChucVu = new JTextField();
        txtMaToaNha = new JTextField();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        add(new JLabel("Mã nhân viên"));
        add(txtMaNV);

        add(new JLabel("Họ tên"));
        add(txtHoTen);

        add(new JLabel("Giới tính"));
        add(cbGioiTinh);

        add(new JLabel("SĐT"));
        add(txtSDT);

        add(new JLabel("Email"));
        add(txtEmail);

        add(new JLabel("Chức vụ"));
        add(txtChucVu);

        add(new JLabel("Mã tòa nhà"));
        add(txtMaToaNha);

        add(btnThem);
        add(btnSua);

        add(btnXoa);
        add(new JLabel(""));
        String[] columns = {
                "Mã NV",
                "Họ tên",
                "Giới tính",
                "SĐT",
                "Email",
                "Chức vụ",
                "Mã tòa nhà"
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
                String gioiTinh = model.getValueAt(row, 2).toString();
                cbGioiTinh.setSelectedItem(gioiTinh);

                txtSDT.setText(model.getValueAt(row, 3).toString());
                txtEmail.setText(model.getValueAt(row, 4).toString());
                txtChucVu.setText(model.getValueAt(row, 5).toString());
                txtMaToaNha.setText(model.getValueAt(row, 6) != null ? model.getValueAt(row, 6).toString() : ""); // THÊM MỚI
            }
        });

        btnThem.addActionListener(e -> {

            NhanVien nv = new NhanVien();

            nv.setMaNV(txtMaNV.getText());
            nv.setHoTenNV(txtHoTen.getText());
            nv.setGioiTinh(cbGioiTinh.getSelectedItem().toString()); // THÊM MỚI
            nv.setSdt(txtSDT.getText());
            nv.setEmail(txtEmail.getText());
            nv.setChucVu(txtChucVu.getText());
            nv.setMaToaNha(txtMaToaNha.getText());

            if (controller.addNhanVien(nv)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                loadTable();
            }
        });

        btnSua.addActionListener(e -> {

            NhanVien nv = new NhanVien();

            nv.setMaNV(txtMaNV.getText());
            nv.setHoTenNV(txtHoTen.getText());
            nv.setGioiTinh(cbGioiTinh.getSelectedItem().toString());
            nv.setSdt(txtSDT.getText());
            nv.setEmail(txtEmail.getText());
            nv.setChucVu(txtChucVu.getText());
            nv.setMaToaNha(txtMaToaNha.getText()); // THÊM MỚI

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
                    nv.getGioiTinh(),
                    nv.getSdt(),
                    nv.getEmail(),
                    nv.getChucVu(),
                    nv.getMaToaNha()
            });
        }
    }
}