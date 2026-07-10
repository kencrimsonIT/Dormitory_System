package com.dormiroty.view.panels;

import com.dormiroty.controller.DienChinhSachController;
import com.dormiroty.entity.DienChinhSach;
import com.dormiroty.view.utils.ThemeUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DienChinhSachPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private DienChinhSachController controller;

    private JTextField txtMaDCS;
    private JTextField txtTenDCS;
    private JTextField txtMucNienGiam;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;

    private JTable table;
    private DefaultTableModel model;

    public DienChinhSachPanel() {

        controller = new DienChinhSachController();

        setLayout(new BorderLayout(10, 10));
        setBackground(ThemeUtils.BG_PANEL);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formPanel = ThemeUtils.createFormPanel("Thông tin diện chính sách", 2);

        txtMaDCS = ThemeUtils.createTextField(15);
        txtTenDCS = ThemeUtils.createTextField(15);
        txtMucNienGiam = ThemeUtils.createTextField(15);

        btnThem = ThemeUtils.createSuccessButton("Thêm");
        btnSua = ThemeUtils.createPrimaryButton("Sửa");
        btnXoa = ThemeUtils.createDangerButton("Xóa");
        JPanel btnPanel = ThemeUtils.createButtonPanel(btnThem, btnSua, btnXoa);

        addFormRow(formPanel, 0, 0, "Mã diện", txtMaDCS);
        addFormRow(formPanel, 0, 1, "Tên diện", txtTenDCS);
        addFormRow(formPanel, 1, 0, "Mức niên giảm", txtMucNienGiam);

        String[] columns = {
                "Mã diện",
                "Tên diện",
                "Mức niên giảm"
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
                txtMaDCS.setText(model.getValueAt(row, 0).toString());
                txtTenDCS.setText(model.getValueAt(row, 1).toString());
                txtMucNienGiam.setText(model.getValueAt(row, 2).toString());
            }
        });

        btnThem.addActionListener(e -> {

            DienChinhSach dcs = new DienChinhSach();

            dcs.setMaDCS(txtMaDCS.getText());
            dcs.setTenDCS(txtTenDCS.getText());
            dcs.setMucNienGiam(txtMucNienGiam.getText());

            if (controller.addDienChinhSach(dcs)) {

                JOptionPane.showMessageDialog(this,
                        "Thêm thành công");

                loadTable();
            }
        });

        btnSua.addActionListener(e -> {

            DienChinhSach dcs = new DienChinhSach();

            dcs.setMaDCS(txtMaDCS.getText());
            dcs.setTenDCS(txtTenDCS.getText());
            dcs.setMucNienGiam(txtMucNienGiam.getText());

            if (controller.updateDienChinhSach(dcs)) {

                JOptionPane.showMessageDialog(this,
                        "Sửa thành công");

                loadTable();
            }
        });

        btnXoa.addActionListener(e -> {

            if (controller.deleteDienChinhSach(txtMaDCS.getText())) {

                JOptionPane.showMessageDialog(this,
                        "Xóa thành công");

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

        List<DienChinhSach> list =
                controller.getAllDienChinhSach();

        for (DienChinhSach dcs : list) {

            model.addRow(new Object[]{
                    dcs.getMaDCS(),
                    dcs.getTenDCS(),
                    dcs.getMucNienGiam()
            });
        }
    }
}