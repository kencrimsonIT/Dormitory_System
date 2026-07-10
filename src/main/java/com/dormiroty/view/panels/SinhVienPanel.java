package com.dormiroty.view.panels;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.dormiroty.controller.SinhVienController;
import com.dormiroty.entity.SinhVien;
import com.dormiroty.view.utils.ThemeUtils;

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
        controller = new SinhVienController();
        setLayout(new BorderLayout(10, 10));
        setBackground(ThemeUtils.BG_PANEL);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formPanel = ThemeUtils.createFormPanel("Thông tin sinh viên", 2);

        txtMSSV     = ThemeUtils.createTextField(15);
        txtHoTen    = ThemeUtils.createTextField(15);
        txtQueQuan  = ThemeUtils.createTextField(15);
        txtNgaySinh = ThemeUtils.createTextField(15);
        txtNganhHoc = ThemeUtils.createTextField(15);
        txtNam      = ThemeUtils.createTextField(15);
        txtEmail    = ThemeUtils.createTextField(15);
        txtSDT     = ThemeUtils.createTextField(15);
        txtMaDCS    = ThemeUtils.createTextField(15);

        cboGioiTinh = ThemeUtils.createComboBox(new String[]{"Nam", "Nữ"});

        addFormRow(formPanel, 0, 0, "MSSV", txtMSSV);
        addFormRow(formPanel, 0, 1, "Họ tên", txtHoTen);
        addFormRow(formPanel, 1, 0, "Giới tính", cboGioiTinh);
        addFormRow(formPanel, 1, 1, "Ngày sinh (yyyy-MM-dd HH:mm)", txtNgaySinh);
        addFormRow(formPanel, 2, 0, "Quê quán", txtQueQuan);
        addFormRow(formPanel, 2, 1, "Ngành học", txtNganhHoc);
        addFormRow(formPanel, 3, 0, "Năm thứ", txtNam);
        addFormRow(formPanel, 3, 1, "Email", txtEmail);
        addFormRow(formPanel, 4, 0, "SĐT", txtSDT);
        addFormRow(formPanel, 4, 1, "Mã DCS", txtMaDCS);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        searchPanel.setOpaque(false);
        JLabel lblTim = ThemeUtils.createLabel("Tìm theo tên:");
        txtTimKiem = ThemeUtils.createTextField(20);
        searchPanel.add(lblTim);
        searchPanel.add(txtTimKiem);

        btnThem = ThemeUtils.createSuccessButton("Thêm");
        btnSua  = ThemeUtils.createPrimaryButton("Sửa");
        btnXoa  = ThemeUtils.createDangerButton("Xóa");
        btnTim  = ThemeUtils.createWarningButton("Tìm");
        JPanel btnPanel = ThemeUtils.createButtonPanel(btnThem, btnSua, btnXoa, btnTim);
        String[] columns = {
                "MSSV", "Họ tên", "Giới tính", "Ngày sinh",
                "Quê quán", "Ngành học", "Năm", "Email", "SĐT", "Mã DCS"
        };
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        ThemeUtils.styleTable(table);

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
        scrollPane.setBorder(BorderFactory.createLineBorder(ThemeUtils.BORDER_COLOR, 1, true));

        JPanel northPanel = new JPanel(new BorderLayout(10, 10));
        northPanel.setOpaque(false);
        northPanel.add(formPanel, BorderLayout.NORTH);
        northPanel.add(searchPanel, BorderLayout.CENTER);
        northPanel.add(btnPanel, BorderLayout.SOUTH);

        add(northPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

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
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                    loadTable();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại! Kiểm tra Console.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi dữ liệu: " + ex.getMessage());
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
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                    loadTable();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        btnXoa.addActionListener(e -> {

            String mssv = txtMSSV.getText().trim();

            if (mssv.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Chọn sinh viên cần xóa!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (controller.deleteSinhVien(mssv)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    loadTable();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!");
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

    private void addFormRow(JPanel panel, int row, int col, String label, JComponent field) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel lbl = ThemeUtils.createLabel(label);
        gbc.gridx = col * 2;
        gbc.gridy = row;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lbl, gbc);
        gbc.gridx = col * 2 + 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(field, gbc);
    }

    private void loadTable() {
        model.setRowCount(0);
        List<SinhVien> list = controller.getAllSinhVien();
        for (SinhVien sv : list) {
            String ngaySinhStr = sv.getNgaySinh() != null ? sv.getNgaySinh().format(formatter) : "";
            model.addRow(new Object[]{
                    sv.getMssv(), sv.getHoTen(), sv.getGioiTinh(), ngaySinhStr,
                    sv.getQueQuan(), sv.getNganhHoc(), sv.getNam(),
                    sv.getEmail(), sv.getSdt(), sv.getMaDCS()
            });
        }
    }
}