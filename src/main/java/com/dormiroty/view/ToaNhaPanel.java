package com.dormiroty.view;

import com.dormiroty.controller.ToaNhaController;
import com.dormiroty.entity.ToaNha;

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

        setLayout(new GridLayout(8, 2, 5, 5));

        txtMaToaNha = new JTextField();
        txtTenToaNha = new JTextField();
        txtLoaiToaNha = new JTextField();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        add(new JLabel("Mã tòa nhà"));
        add(txtMaToaNha);

        add(new JLabel("Tên tòa nhà"));
        add(txtTenToaNha);

        add(new JLabel("Loại tòa nhà"));
        add(txtLoaiToaNha);

        add(btnThem);
        add(btnSua);

        add(btnXoa);
        add(new JLabel(""));

        String[] columns = {
                "Mã tòa nhà",
                "Tên tòa nhà",
                "Loại tòa nhà"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        loadTable();

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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