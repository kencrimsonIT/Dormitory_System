package com.dormiroty.view;

import com.dormiroty.controller.ViPhamController;
import com.dormiroty.entity.ViPham;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ViPhamPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private ViPhamController controller;

    private JTextField txtMaNV;
    private JTextField txtMSSV;
    private JTextField txtTenLoaiViPham;
    private JTextField txtMaLoaiViPham;
    private JTextField txtNgayViPham;
    private JTextField txtHinhThucXuLi;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;

    private JTable table;
    private DefaultTableModel model;

    private final DateTimeFormatter formatter =
    		 DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public ViPhamPanel() {

        controller = new ViPhamController();

        setLayout(new GridLayout(12, 2, 5, 5));

        txtMaNV = new JTextField();
        txtMSSV = new JTextField();
        txtTenLoaiViPham = new JTextField();
        txtMaLoaiViPham = new JTextField();
        txtNgayViPham = new JTextField();
        txtHinhThucXuLi = new JTextField();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        add(new JLabel("Mã NV"));
        add(txtMaNV);

        add(new JLabel("MSSV"));
        add(txtMSSV);

        add(new JLabel("Tên loại vi phạm"));
        add(txtTenLoaiViPham);

        add(new JLabel("Mã loại vi phạm"));
        add(txtMaLoaiViPham);

        add(new JLabel("Ngày vi phạm (yyyy-MM-dd HH:mm:ss)"));
        add(txtNgayViPham);

        add(new JLabel("Hình thức xử lý"));
        add(txtHinhThucXuLi);

        add(btnThem);
        add(btnSua);

        add(btnXoa);
        add(new JLabel());

        String[] columns = {
                "Mã NV",
                "MSSV",
                "Tên loại VP",
                "Mã loại VP",
                "Ngày VP",
                "Hình thức xử lý"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        add(new JScrollPane(table));

        loadTable();

        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if (row >= 0) {
                txtMaNV.setText(model.getValueAt(row, 0).toString());
                txtMSSV.setText(model.getValueAt(row, 1).toString());
                txtTenLoaiViPham.setText(model.getValueAt(row, 2).toString());
                txtMaLoaiViPham.setText(model.getValueAt(row, 3).toString());
                txtNgayViPham.setText(model.getValueAt(row, 4).toString());
                txtHinhThucXuLi.setText(model.getValueAt(row, 5).toString());
            }
        });

        btnThem.addActionListener(e -> saveData(true));
        btnSua.addActionListener(e -> saveData(false));

        btnXoa.addActionListener(e -> {

            if (controller.deleteViPham(txtMaLoaiViPham.getText())) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                loadTable();
            }
        });
    }

    private void saveData(boolean isInsert) {

        try {

            ViPham vp = new ViPham();

            vp.setMaNV(txtMaNV.getText());
            vp.setMSSV(Integer.parseInt(txtMSSV.getText()));
            vp.setTenLoaiViPham(txtTenLoaiViPham.getText());
            vp.setMaLoaiViPham(txtMaLoaiViPham.getText());

            if (!txtNgayViPham.getText().trim().isEmpty()) {
                vp.setNgayviPham(
                        LocalDateTime.parse(
                                txtNgayViPham.getText(),
                                formatter
                        )
                );
            }

            vp.setHinhThucXuLi(txtHinhThucXuLi.getText());

            boolean result;

            if (isInsert) {
                result = controller.addViPham(vp);
            } else {
                result = controller.updateViPham(vp);
            }

            if (result) {
                JOptionPane.showMessageDialog(this,
                        isInsert ? "Thêm thành công" : "Sửa thành công");
                loadTable();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi dữ liệu: " + ex.getMessage());
        }
    }

    private void loadTable() {

        model.setRowCount(0);

        List<ViPham> list = controller.getAllViPham();

        for (ViPham vp : list) {

            model.addRow(new Object[]{
                    vp.getMaNV(),
                    vp.getMSSV(),
                    vp.getTenLoaiViPham(),
                    vp.getMaLoaiViPham(),
                    vp.getNgayviPham(),
                    vp.getHinhThucXuLi()
            });
        }
    }
}