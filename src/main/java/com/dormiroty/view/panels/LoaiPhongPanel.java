package com.dormiroty.view.panels;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.dormiroty.controller.LoaiPhongController;
import com.dormiroty.entity.LoaiPhong;
import com.dormiroty.view.utils.ThemeUtils;

public class LoaiPhongPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private LoaiPhongController controller;

    private JTextField txtMaLoaiPhong;
    private JTextField txtTenLoaiPhong;
    private JTextField txtSucChua;
    private JTextField txtDonGia;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;

    private JTable table;
    private DefaultTableModel model;

    public LoaiPhongPanel() {

        controller = new LoaiPhongController();

        setLayout(new BorderLayout(10, 10));
        setBackground(ThemeUtils.BG_PANEL);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formPanel = ThemeUtils.createFormPanel("Thông tin loại phòng", 2);

        txtMaLoaiPhong = ThemeUtils.createTextField(15);
        txtTenLoaiPhong = ThemeUtils.createTextField(15);
        txtSucChua = ThemeUtils.createTextField(15);
        txtDonGia = ThemeUtils.createTextField(15);

        btnThem = ThemeUtils.createSuccessButton("Thêm");
        btnSua = ThemeUtils.createPrimaryButton("Sửa");
        btnXoa = ThemeUtils.createDangerButton("Xóa");
        JPanel btnPanel = ThemeUtils.createButtonPanel(btnThem, btnSua, btnXoa);

        addFormRow(formPanel, 0, 0, "Mã loại phòng", txtMaLoaiPhong);
        addFormRow(formPanel, 0, 1, "Tên loại phòng", txtTenLoaiPhong);
        addFormRow(formPanel, 1, 0, "Sức chứa", txtSucChua);
        addFormRow(formPanel, 1, 1, "Đơn giá", txtDonGia);

        String[] columns = {
                "Mã loại phòng",
                "Tên loại phòng",
                "Sức chứa",
                "Đơn giá"
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

                txtMaLoaiPhong.setText(model.getValueAt(row, 0).toString());
                txtTenLoaiPhong.setText(model.getValueAt(row, 1).toString());
                txtSucChua.setText(model.getValueAt(row, 2).toString());
                txtDonGia.setText(model.getValueAt(row, 3).toString());
            }
        });

        btnThem.addActionListener(e -> {

            try {

                LoaiPhong lp = new LoaiPhong();

                lp.setMaLoaiPhong(txtMaLoaiPhong.getText());
                lp.setTenLoaiPhong(txtTenLoaiPhong.getText());
                lp.setSucChua(Short.parseShort(txtSucChua.getText()));
                lp.setDonGia(new BigDecimal(txtDonGia.getText()));

                if (controller.addLoaiPhong(lp)) {
                    loadTable();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnSua.addActionListener(e -> {

            try {

                LoaiPhong lp = new LoaiPhong();

                lp.setMaLoaiPhong(txtMaLoaiPhong.getText());
                lp.setTenLoaiPhong(txtTenLoaiPhong.getText());
                lp.setSucChua(Short.parseShort(txtSucChua.getText()));
                lp.setDonGia(new BigDecimal(txtDonGia.getText()));

                if (controller.updateLoaiPhong(lp)) {
                    loadTable();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnXoa.addActionListener(e -> {

            String maLoaiPhong = txtMaLoaiPhong.getText();

            if (controller.deleteLoaiPhong(maLoaiPhong)) {
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

        List<LoaiPhong> list = controller.getAllLoaiPhong();

        for (LoaiPhong lp : list) {

            model.addRow(new Object[] {
                    lp.getMaLoaiPhong(),
                    lp.getTenLoaiPhong(),
                    lp.getSucChua(),
                    lp.getDonGia()
            });
        }
    }
}