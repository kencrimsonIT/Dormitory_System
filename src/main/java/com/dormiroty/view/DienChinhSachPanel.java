package com.dormiroty.view;

import com.dormiroty.controller.DienChinhSachController;
import com.dormiroty.entity.DienChinhSach;

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

        setLayout(new GridLayout(8, 2, 5, 5));

        txtMaDCS = new JTextField();
        txtTenDCS = new JTextField();
        txtMucNienGiam = new JTextField();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        add(new JLabel("Mã diện"));
        add(txtMaDCS);

        add(new JLabel("Tên diện"));
        add(txtTenDCS);

        add(new JLabel("Mức niên giảm"));
        add(txtMucNienGiam);

        add(btnThem);
        add(btnSua);

        add(btnXoa);
        add(new JLabel(""));

        String[] columns = {
                "Mã diện",
                "Tên diện",
                "Mức niên giảm"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

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