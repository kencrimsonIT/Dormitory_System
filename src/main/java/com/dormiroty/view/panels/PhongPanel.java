package com.dormiroty.view.panels;

import java.awt.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.dormiroty.controller.PhongController;
import com.dormiroty.entity.Phong;
import com.dormiroty.view.utils.ThemeUtils;

public class PhongPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private PhongController controller;

    private JTextField txtMaPhong;
    private JTextField txtMaLoaiPhong;
    private JTextField txtMaToaNha;
    private JTextField txtSoChoTrong;
    private JTextField txtTrangThai;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;

    private JTable table;
    private DefaultTableModel model;

    public PhongPanel() {

        controller = new PhongController();
        setLayout(new BorderLayout(10, 10));
        setBackground(ThemeUtils.BG_PANEL);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formPanel = ThemeUtils.createFormPanel("Thông tin phòng", 2);

        txtMaPhong = ThemeUtils.createTextField(15);
        txtMaLoaiPhong = ThemeUtils.createTextField(15);
        txtMaToaNha = ThemeUtils.createTextField(15);
        txtSoChoTrong = ThemeUtils.createTextField(15);
        txtTrangThai = ThemeUtils.createTextField(15);

        btnThem = ThemeUtils.createSuccessButton("Thêm");
        btnSua = ThemeUtils.createPrimaryButton("Sửa");
        btnXoa = ThemeUtils.createDangerButton("Xóa");
        JPanel btnPanel = ThemeUtils.createButtonPanel(btnThem, btnSua, btnXoa);

        addFormRow(formPanel, 0, 0, "Mã phòng", txtMaPhong);
        addFormRow(formPanel, 0, 1, "Mã loại phòng", txtMaLoaiPhong);
        addFormRow(formPanel, 1, 0, "Mã tòa nhà", txtMaToaNha);
        addFormRow(formPanel, 1, 1, "Số chỗ trống", txtSoChoTrong);
        addFormRow(formPanel, 2, 0, "Trạng thái", txtTrangThai);

        String[] columns = {
                "Mã phòng",
                "Mã loại phòng",
                "Mã tòa nhà",
                "Số chỗ trống",
                "Trạng thái"
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
                txtMaPhong.setText(model.getValueAt(row, 0).toString());
                txtMaLoaiPhong.setText(model.getValueAt(row, 1).toString());
                txtMaToaNha.setText(model.getValueAt(row, 2).toString());
                txtSoChoTrong.setText(model.getValueAt(row, 3).toString());
                txtTrangThai.setText(model.getValueAt(row, 4).toString());
            }
        });

        btnThem.addActionListener(e -> {
            try {
                Phong p = new Phong();
                p.setMaPhong(txtMaPhong.getText());
                p.setMaLoaiPhong(txtMaLoaiPhong.getText());
                p.setMaToaNha(txtMaToaNha.getText());
                p.setSoChoTrong(Integer.parseInt(txtSoChoTrong.getText())); // THÊM MỚI (Ép kiểu sang int)
                p.setTrangThai(txtTrangThai.getText());

                if (controller.addPhong(p)) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    loadTable();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Số chỗ trống phải là một số nguyên hợp lệ!");
            }
        });

        btnSua.addActionListener(e -> {
            try {
                Phong p = new Phong();
                p.setMaPhong(txtMaPhong.getText());
                p.setMaLoaiPhong(txtMaLoaiPhong.getText());
                p.setMaToaNha(txtMaToaNha.getText());
                p.setSoChoTrong(Integer.parseInt(txtSoChoTrong.getText()));
                p.setTrangThai(txtTrangThai.getText());

                if (controller.updatePhong(p)) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thất bại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Số chỗ trống phải là một số nguyên hợp lệ!");
            }
        });

        btnXoa.addActionListener(e -> {
            String maPhong = txtMaPhong.getText();
            if (controller.deletePhong(maPhong)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                loadTable();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
            }
        });
    }
    private void clearFields() {
        txtMaPhong.setText("");
        txtMaLoaiPhong.setText("");
        txtMaToaNha.setText("");
        txtSoChoTrong.setText("");
        txtTrangThai.setText("");
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
        List<Phong> list = controller.getAllPhong();

        for (Phong p : list) {
            model.addRow(new Object[] {
                    p.getMaPhong(),
                    p.getMaLoaiPhong(),
                    p.getMaToaNha(),
                    p.getSoChoTrong(),
                    p.getTrangThai()
            });
        }
    }
}