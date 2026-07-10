package com.dormiroty.view;

import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private JTextField txtQueQuan;
    private JTextField txtNgaySinh;
    private JTextField txtNganhHoc;
    private JTextField txtNam;
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

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public SinhVienPanel() {
        System.out.println("SinhVienPanel constructor");
        controller = new SinhVienController();
        setLayout(new GridLayout(15, 2, 5, 5));

        txtMSSV = new JTextField();
        txtHoTen = new JTextField();
        txtQueQuan = new JTextField();
        txtNgaySinh = new JTextField();
        txtNganhHoc = new JTextField();
        txtNam = new JTextField();
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

        add(new JLabel("Ngày sinh (yyyy-MM-dd HH:mm)"));
        add(txtNgaySinh);

        add(new JLabel("Quê quán"));
        add(txtQueQuan);

        add(new JLabel("Ngành học"));
        add(txtNganhHoc);

        add(new JLabel("Sinh viên năm thứ"));
        add(txtNam);

        add(new JLabel("Email"));
        add(txtEmail);

        add(new JLabel("SĐT"));
        add(txtSDT);

        add(new JLabel("Mã DCS"));
        add(txtMaDCS);
        
        add(new JLabel("Tìm theo tên"));
        add(txtTimKiem);
        
        add(btnThem);
        add(btnSua);

        add(btnXoa);
        add(btnTim);
        String[] columns = {
                "MSSV",
                "Họ tên",
                "Giới tính",
                "Ngày sinh",
                "Quê quán",
                "Ngành học",
                "Năm",
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
                txtQueQuan.setText(model.getValueAt(row, 4).toString());
                txtNganhHoc.setText(model.getValueAt(row, 5).toString());
                txtNam.setText(model.getValueAt(row, 6).toString());
                txtEmail.setText(model.getValueAt(row, 7).toString());
                txtSDT.setText(model.getValueAt(row, 8).toString());

                Object maDCS = model.getValueAt(row, 9);
                txtMaDCS.setText(maDCS == null ? "" : maDCS.toString());
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        btnThem.addActionListener(e -> {
            try {
                SinhVien sv = new SinhVien();
                sv.setMssv(txtMSSV.getText().trim());
                sv.setHoTen(txtHoTen.getText().trim());
                sv.setGioiTinh(cboGioiTinh.getSelectedItem().toString());

                if (!txtNgaySinh.getText().trim().isEmpty()) {
                    sv.setNgaySinh(LocalDateTime.parse(txtNgaySinh.getText().trim(), formatter));
                }

                sv.setQueQuan(txtQueQuan.getText().trim());
                sv.setNganhHoc(txtNganhHoc.getText().trim());
                sv.setNam(Integer.parseInt(txtNam.getText().trim()));
                sv.setEmail(txtEmail.getText().trim());
                sv.setSdt(txtSDT.getText().trim());
                sv.setMaDCS(txtMaDCS.getText().trim().isEmpty() ? null : txtMaDCS.getText().trim());

                boolean result = controller.addSinhVien(sv);

                if (result) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Thêm thành công!");
                    loadTable();
                    clearFields();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Thêm thất bại! Kiểm tra Console.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, "Lỗi dữ liệu: " + ex.getMessage());
            }
        });
        btnSua.addActionListener(e -> {
            try {
                SinhVien sv = new SinhVien();

                sv.setMssv(txtMSSV.getText().trim());
                sv.setHoTen(txtHoTen.getText().trim());
                sv.setGioiTinh(cboGioiTinh.getSelectedItem().toString());

                if (!txtNgaySinh.getText().trim().isEmpty()) {
                    sv.setNgaySinh(LocalDateTime.parse(txtNgaySinh.getText().trim(), formatter));
                }

                sv.setQueQuan(txtQueQuan.getText().trim());
                sv.setNganhHoc(txtNganhHoc.getText().trim());
                sv.setNam(Integer.parseInt(txtNam.getText().trim()));
                sv.setEmail(txtEmail.getText().trim());
                sv.setSdt(txtSDT.getText().trim());
                sv.setMaDCS(txtMaDCS.getText().trim());

                if (controller.updateSinhVien(sv)) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                    loadTable();
                    clearFields();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        btnXoa.addActionListener(e -> {

            String mssv = txtMSSV.getText().trim();

            if (mssv.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Chọn sinh viên cần xóa!");
                return;
            }

            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc muốn xóa?",
                    "Xác nhận",
                    javax.swing.JOptionPane.YES_NO_OPTION);

            if (confirm == javax.swing.JOptionPane.YES_OPTION) {

                if (controller.deleteSinhVien(mssv)) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    loadTable();
                    clearFields();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Xóa thất bại!");
                }

            }

        });

        btnTim.addActionListener(e -> {

            model.setRowCount(0);

            List<SinhVien> list = controller.findByName(txtTimKiem.getText().trim());

            for (SinhVien sv : list) {

                String ngaySinh = "";

                if (sv.getNgaySinh() != null) {
                    ngaySinh = sv.getNgaySinh().format(formatter);
                }

                model.addRow(new Object[]{
                        sv.getMssv(),
                        sv.getHoTen(),
                        sv.getGioiTinh(),
                        ngaySinh,
                        sv.getQueQuan(),
                        sv.getNganhHoc(),
                        sv.getNam(),
                        sv.getEmail(),
                        sv.getSdt(),
                        sv.getMaDCS()
                });
            }

        });

        loadTable();
     
    }

    private void clearFields() {
        txtMSSV.setText("");
        txtHoTen.setText("");
        txtNgaySinh.setText("");
        txtQueQuan.setText("");
        txtNganhHoc.setText("");
        txtNam.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        txtMaDCS.setText("");
    }

    private void loadTable() {
        System.out.println("loadTable duoc goi");
        model.setRowCount(0);

        List<SinhVien> list = controller.getAllSinhVien();

        System.out.println("So sinh vien = " + list.size());

        for (SinhVien sv : list) {
            String ngaySinhStr = sv.getNgaySinh() != null ? sv.getNgaySinh().format(formatter) : "";

            model.addRow(new Object[] {
                    sv.getMssv(),
                    sv.getHoTen(),
                    sv.getGioiTinh(),
                    ngaySinhStr,
                    sv.getQueQuan(),
                    sv.getNganhHoc(),
                    sv.getNam(),
                    sv.getEmail(),
                    sv.getSdt(),
                    sv.getMaDCS()
            });
        }
    }
}