package com.dormiroty.view.panels;

import com.dormiroty.controller.ToaNhaController;
import com.dormiroty.entity.ToaNha;
import com.dormiroty.view.utils.ThemeUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ToaNhaPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private ToaNhaController controller;

    private JTextField txtMaToaNha;
    private JTextField txtTenToaNha;
    private JTextField txtLoaiToaNha;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;

    private JTable table;
    private DefaultTableModel model;

    public ToaNhaPanel() {

        controller = new ToaNhaController();

        setLayout(new BorderLayout(10, 10));
        setBackground(ThemeUtils.BG_PANEL);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formPanel = ThemeUtils.createFormPanel("Thông tin tòa nhà", 2);

        txtMaToaNha = ThemeUtils.createTextField(15);
        txtTenToaNha = ThemeUtils.createTextField(15);
        txtLoaiToaNha = ThemeUtils.createTextField(15);

        btnThem = ThemeUtils.createSuccessButton("Thêm");
        btnSua = ThemeUtils.createPrimaryButton("Sửa");
        btnXoa = ThemeUtils.createDangerButton("Xóa");
        JPanel btnPanel = ThemeUtils.createButtonPanel(btnThem, btnSua, btnXoa);

        addFormRow(formPanel, 0, 0, "Mã tòa nhà", txtMaToaNha);
        addFormRow(formPanel, 0, 1, "Tên tòa nhà", txtTenToaNha);
        addFormRow(formPanel, 1, 0, "Loại tòa nhà", txtLoaiToaNha);

        String[] columns = {
                "Mã tòa nhà",
                "Tên tòa nhà",
                "Loại tòa nhà"
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
                txtMaToaNha.setText(model.getValueAt(row, 0).toString());
                txtTenToaNha.setText(model.getValueAt(row, 1).toString());
                txtLoaiToaNha.setText(model.getValueAt(row, 2).toString());
            }
        });

        btnThem.addActionListener(e -> {

            ToaNha tn = new ToaNha();

            tn.setMaToaNha(txtMaToaNha.getText());
            tn.setTenToaNha(txtTenToaNha.getText());
            tn.setLoaiToaNha(txtLoaiToaNha.getText());

            if (controller.addToaNha(tn)) {
                loadTable();
            }
        });

        btnSua.addActionListener(e -> {

            ToaNha tn = new ToaNha();

            tn.setMaToaNha(txtMaToaNha.getText());
            tn.setTenToaNha(txtTenToaNha.getText());
            tn.setLoaiToaNha(txtLoaiToaNha.getText());

            if (controller.updateToaNha(tn)) {
                loadTable();
            }
        });

        btnXoa.addActionListener(e -> {

            String maToaNha = txtMaToaNha.getText();

            if (controller.deleteToaNha(maToaNha)) {
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

        List<ToaNha> list = controller.getAllToaNha();

        for (ToaNha tn : list) {

            model.addRow(new Object[]{
                    tn.getMaToaNha(),
                    tn.getTenToaNha(),
                    tn.getLoaiToaNha()
            });
        }
    }
}