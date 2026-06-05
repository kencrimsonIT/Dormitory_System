package com.dormiroty.view;

import java.awt.GridLayout;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.dormiroty.controller.LoaiPhongController;
import com.dormiroty.entity.LoaiPhong;

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

        setLayout(new GridLayout(8, 2, 5, 5));

        txtMaLoaiPhong = new JTextField();
        txtTenLoaiPhong = new JTextField();
        txtSucChua = new JTextField();
        txtDonGia = new JTextField();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        add(new JLabel("Mã loại phòng"));
        add(txtMaLoaiPhong);

        add(new JLabel("Tên loại phòng"));
        add(txtTenLoaiPhong);

        add(new JLabel("Sức chứa"));
        add(txtSucChua);

        add(new JLabel("Đơn giá"));
        add(txtDonGia);

        add(btnThem);
        add(btnSua);

        add(btnXoa);
        add(new JLabel(""));

        String[] columns = {
                "Mã loại phòng",
                "Tên loại phòng",
                "Sức chứa",
                "Đơn giá"
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