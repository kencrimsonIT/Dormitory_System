package com.dormiroty.view.panels;

import com.dormiroty.controller.NhanVienController;
import com.dormiroty.entity.NhanVien;
import com.dormiroty.view.utils.ThemeUtils;

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
        setLayout(new BorderLayout(10, 10));
        setBackground(ThemeUtils.BG_PANEL);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formPanel = ThemeUtils.createFormPanel("Thông tin nhân viên", 2);

        txtMaNV = ThemeUtils.createTextField(15);
        txtHoTen = ThemeUtils.createTextField(15);
        cbGioiTinh = ThemeUtils.createComboBox(new String[]{"Nam", "Nữ"});
        txtSDT = ThemeUtils.createTextField(15);
        txtEmail = ThemeUtils.createTextField(15);
        txtChucVu = ThemeUtils.createTextField(15);
        txtMaToaNha = ThemeUtils.createTextField(15);

        addFormRow(formPanel, 0, 0, "Mã nhân viên", txtMaNV);
        addFormRow(formPanel, 0, 1, "Họ tên", txtHoTen);
        addFormRow(formPanel, 1, 0, "Giới tính", cbGioiTinh);
        addFormRow(formPanel, 1, 1, "SĐT", txtSDT);
        addFormRow(formPanel, 2, 0, "Email", txtEmail);
        addFormRow(formPanel, 2, 1, "Chức vụ", txtChucVu);
        addFormRow(formPanel, 3, 0, "Mã tòa nhà", txtMaToaNha);

        btnThem = ThemeUtils.createSuccessButton("Thêm");
        btnSua = ThemeUtils.createPrimaryButton("Sửa");
        btnXoa = ThemeUtils.createDangerButton("Xóa");
        JPanel btnPanel = ThemeUtils.createButtonPanel(btnThem, btnSua, btnXoa);
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
        ThemeUtils.styleTable(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(ThemeUtils.BORDER_COLOR, 1, true));

        JPanel northPanel = new JPanel(new BorderLayout(10, 10));
        northPanel.setOpaque(false);
        northPanel.add(formPanel, BorderLayout.NORTH);
        northPanel.add(btnPanel, BorderLayout.SOUTH);

        add(northPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

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