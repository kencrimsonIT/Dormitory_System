package com.dormiroty.view.panels;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.dormiroty.controller.LS_ViPhamController;
import com.dormiroty.entity.LS_ViPham;
import com.dormiroty.view.utils.ThemeUtils;

public class LS_ViPhamPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private LS_ViPhamController controller;

    private JTextField txtMaLS;
    private JTextField txtMaLoaiVP;
    private JTextField txtMSSV;
    private JTextField txtNgayVP;
    private JTextField txtMaNV;
    private JTextField txtTimKiem;

    private JComboBox<String> cboHinhThuc;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnTim;

    private JTable table;
    private DefaultTableModel model;

    private DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public LS_ViPhamPanel() {

        controller = new LS_ViPhamController();

        setLayout(new BorderLayout(10, 10));
        setBackground(ThemeUtils.BG_PANEL);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formPanel = ThemeUtils.createFormPanel("Lịch sử vi phạm", 2);

        txtMaLS = ThemeUtils.createTextField(15);
        txtMaLoaiVP = ThemeUtils.createTextField(15);
        txtMSSV = ThemeUtils.createTextField(15);
        txtNgayVP = ThemeUtils.createTextField(15);
        txtMaNV = ThemeUtils.createTextField(15);
        cboHinhThuc = ThemeUtils.createComboBox(new String[]{"Cảnh cáo", "Khiển trách", "Phạt tiền", "Đình chỉ"});

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        searchPanel.setOpaque(false);
        JLabel lblTim = ThemeUtils.createLabel("Tìm theo MSSV:");
        txtTimKiem = ThemeUtils.createTextField(20);
        searchPanel.add(lblTim);
        searchPanel.add(txtTimKiem);

        btnThem = ThemeUtils.createSuccessButton("Thêm");
        btnSua = ThemeUtils.createPrimaryButton("Sửa");
        btnXoa = ThemeUtils.createDangerButton("Xóa");
        btnTim = ThemeUtils.createWarningButton("Tìm");
        JPanel btnPanel = ThemeUtils.createButtonPanel(btnThem, btnSua, btnXoa, btnTim);

        addFormRow(formPanel, 0, 0, "Mã LS Vi Phạm", txtMaLS);
        addFormRow(formPanel, 0, 1, "Mã Loại Vi Phạm", txtMaLoaiVP);
        addFormRow(formPanel, 1, 0, "MSSV", txtMSSV);
        addFormRow(formPanel, 1, 1, "Ngày Vi Phạm", txtNgayVP);
        addFormRow(formPanel, 2, 0, "Mã Nhân Viên", txtMaNV);
        addFormRow(formPanel, 2, 1, "Hình Thức XL", cboHinhThuc);

        String[] columns = {
                "Mã LS",
                "Mã Loại VP",
                "MSSV",
                "Ngày VP",
                "Mã NV",
                "Hình Thức XL"
        };

        model = new DefaultTableModel(columns,0);
        table = new JTable(model);

        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if(row >= 0){

                txtMaLS.setText(model.getValueAt(row,0).toString());
                txtMaLoaiVP.setText(model.getValueAt(row,1).toString());
                txtMSSV.setText(model.getValueAt(row,2).toString());
                txtNgayVP.setText(model.getValueAt(row,3).toString());
                txtMaNV.setText(model.getValueAt(row,4).toString());
                cboHinhThuc.setSelectedItem(model.getValueAt(row,5).toString());

            }

        });

        ThemeUtils.styleTable(table);
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

                LS_ViPham ls = new LS_ViPham();

                ls.setMaLSViPham(txtMaLS.getText().trim());
                ls.setMaLoaiViPham(txtMaLoaiVP.getText().trim());
                ls.setMssv(txtMSSV.getText().trim());

                if (!txtNgayVP.getText().trim().isEmpty()) {
                    ls.setNgayViPham(LocalDateTime.parse(
                            txtNgayVP.getText().trim(), formatter));
                }

                ls.setMaNV(txtMaNV.getText().trim());
                ls.setHinhThucXuLi(cboHinhThuc.getSelectedItem().toString());

                if (controller.addLSViPham(ls)) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                    loadTable();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }

        });

        btnSua.addActionListener(e -> {

            try {

                LS_ViPham ls = new LS_ViPham();

                ls.setMaLSViPham(txtMaLS.getText().trim());
                ls.setMaLoaiViPham(txtMaLoaiVP.getText().trim());
                ls.setMssv(txtMSSV.getText().trim());

                if (!txtNgayVP.getText().trim().isEmpty()) {
                    ls.setNgayViPham(LocalDateTime.parse(
                            txtNgayVP.getText().trim(), formatter));
                }

                ls.setMaNV(txtMaNV.getText().trim());
                ls.setHinhThucXuLi(cboHinhThuc.getSelectedItem().toString());

                if (controller.updateLSViPham(ls)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                    loadTable();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        btnXoa.addActionListener(e -> {

            String maLS = txtMaLS.getText().trim();

            if (maLS.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Chọn lịch sử vi phạm cần xóa!");
                return;
            }

            int choose = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc muốn xóa?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION);

            if (choose == JOptionPane.YES_OPTION) {

                if (controller.deleteLSViPham(maLS)) {
                    JOptionPane.showMessageDialog(this,
                            "Xóa thành công!");
                    loadTable();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Xóa thất bại!");
                }

            }

        });

        btnTim.addActionListener(e -> {

            model.setRowCount(0);

            List<LS_ViPham> list =
                    controller.getLSViPhamByMSSV(
                            txtTimKiem.getText().trim());

            for (LS_ViPham ls : list) {

                String ngay = "";

                if (ls.getNgayViPham() != null) {
                    ngay = ls.getNgayViPham().format(formatter);
                }

                model.addRow(new Object[]{
                        ls.getMaLSViPham(),
                        ls.getMaLoaiViPham(),
                        ls.getMssv(),
                        ngay,
                        ls.getMaNV(),
                        ls.getHinhThucXuLi()
                });

            }

        });

        loadTable();
        
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

            String ngay = "";

            if (ls.getNgayViPham() != null) {
                ngay = ls.getNgayViPham().format(formatter);
            }

            model.addRow(new Object[] {
                    ls.getMaLSViPham(),
                    ls.getMaLoaiViPham(),
                    ls.getMssv(),
                    ngay,
                    ls.getMaNV(),
                    ls.getHinhThucXuLi()
            });
        }
    }


    private void clearFields() {

        txtMaLS.setText("");
        txtMaLoaiVP.setText("");
        txtMSSV.setText("");
        txtNgayVP.setText("");
        txtMaNV.setText("");

        cboHinhThuc.setSelectedIndex(0);

        table.clearSelection();
    }
}