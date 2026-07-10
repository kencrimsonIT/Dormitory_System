package com.dormiroty.view.panels;

import com.dormiroty.controller.LS_ViPhamController;
import com.dormiroty.entity.LS_ViPham;
import com.dormiroty.view.utils.ThemeUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ViPhamPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private LS_ViPhamController controller;

    private JTextField txtMaLSViPham;
    private JTextField txtMaLoaiViPham;
    private JTextField txtMSSV;
    private JTextField txtNgayViPham;
    private JTextField txtMaNV;
    private JTextField txtHinhThucXuLi;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;

    private JTable table;
    private DefaultTableModel model;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ViPhamPanel() {

        controller = new LS_ViPhamController();
        setLayout(new BorderLayout(10, 10));
        setBackground(ThemeUtils.BG_PANEL);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formPanel = ThemeUtils.createFormPanel("Thông tin vi phạm", 2);

        txtMaLSViPham = ThemeUtils.createTextField(15);
        txtMaLoaiViPham = ThemeUtils.createTextField(15);
        txtMSSV = ThemeUtils.createTextField(15);
        txtNgayViPham = ThemeUtils.createTextField(15);
        txtMaNV = ThemeUtils.createTextField(15);
        txtHinhThucXuLi = ThemeUtils.createTextField(15);

        btnThem = ThemeUtils.createSuccessButton("Thêm");
        btnSua = ThemeUtils.createPrimaryButton("Sửa");
        btnXoa = ThemeUtils.createDangerButton("Xóa");
        JPanel btnPanel = ThemeUtils.createButtonPanel(btnThem, btnSua, btnXoa);

        addFormRow(formPanel, 0, 0, "Mã lịch sử VP", txtMaLSViPham);
        addFormRow(formPanel, 0, 1, "Mã loại vi phạm", txtMaLoaiViPham);
        addFormRow(formPanel, 1, 0, "MSSV", txtMSSV);
        addFormRow(formPanel, 1, 1, "Ngày vi phạm", txtNgayViPham);
        addFormRow(formPanel, 2, 0, "Mã NV lập BB", txtMaNV);
        addFormRow(formPanel, 2, 1, "Hình thức xử lý", txtHinhThucXuLi);
        String[] columns = {
                "Mã LS VP",
                "Mã loại VP",
                "MSSV",
                "Ngày vi phạm",
                "Mã NV",
                "Hình thức xử lý"
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
                txtMaLSViPham.setText(model.getValueAt(row, 0).toString());
                txtMaLoaiViPham.setText(model.getValueAt(row, 1).toString());
                txtMSSV.setText(model.getValueAt(row, 2).toString());
                txtNgayViPham.setText(model.getValueAt(row, 3) != null ? model.getValueAt(row, 3).toString() : "");
                txtMaNV.setText(model.getValueAt(row, 4).toString());
                txtHinhThucXuLi.setText(model.getValueAt(row, 5).toString());
            }
        });

        btnThem.addActionListener(e -> saveData(true));
        btnSua.addActionListener(e -> saveData(false));

        btnXoa.addActionListener(e -> {
            if (controller.deleteLSViPham(txtMaLSViPham.getText().trim())) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                loadTable();
                clearFields();
            }
        });
    }

    private void saveData(boolean isInsert) {

        try {
            LS_ViPham ls = new LS_ViPham();

            ls.setMaLSViPham(txtMaLSViPham.getText().trim());
            ls.setMaLoaiViPham(txtMaLoaiViPham.getText().trim());
            ls.setMssv(txtMSSV.getText().trim());

            if (!txtNgayViPham.getText().trim().isEmpty()) {
                ls.setNgayViPham(
                        LocalDateTime.parse(
                                txtNgayViPham.getText().trim(),
                                formatter
                        )
                );
            } else {
                ls.setNgayViPham(LocalDateTime.now());
            }

            ls.setMaNV(txtMaNV.getText().trim());
            ls.setHinhThucXuLi(txtHinhThucXuLi.getText().trim());

            boolean result;

            if (isInsert) {
                result = controller.addLSViPham(ls);
            } else {
                result = controller.updateLSViPham(ls);
            }

            if (result) {
                JOptionPane.showMessageDialog(this,
                        isInsert ? "Thêm lịch sử vi phạm thành công" : "Sửa lịch sử vi phạm thành công");
                loadTable();
                if (isInsert) clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Thao tác thất bại! Kiểm tra lại mã ràng buộc.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi định dạng dữ liệu: " + ex.getMessage());
        }
    }

    private void clearFields() {
        txtMaLSViPham.setText("");
        txtMaLoaiViPham.setText("");
        txtMSSV.setText("");
        txtNgayViPham.setText("");
        txtMaNV.setText("");
        txtHinhThucXuLi.setText("");
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

        List<LS_ViPham> list = controller.getAllLSViPham();

        for (LS_ViPham ls : list) {
            String ngayVPStr = ls.getNgayViPham() != null ? ls.getNgayViPham().format(formatter) : "";

            model.addRow(new Object[]{
                    ls.getMaLSViPham(),
                    ls.getMaLoaiViPham(),
                    ls.getMssv(),
                    ngayVPStr,
                    ls.getMaNV(),
                    ls.getHinhThucXuLi()
            });
        }
    }
}