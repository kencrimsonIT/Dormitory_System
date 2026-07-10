package com.dormiroty.view;

import com.dormiroty.controller.LS_ViPhamController; // Thay đổi Controller tương ứng
import com.dormiroty.entity.LS_ViPham; // Sử dụng Entity Lịch sử vi phạm

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
        setLayout(new GridLayout(14, 2, 5, 5));

        txtMaLSViPham = new JTextField();
        txtMaLoaiViPham = new JTextField();
        txtMSSV = new JTextField();
        txtNgayViPham = new JTextField();
        txtMaNV = new JTextField();
        txtHinhThucXuLi = new JTextField();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        add(new JLabel("Mã lịch sử VP"));
        add(txtMaLSViPham);

        add(new JLabel("Mã loại vi phạm"));
        add(txtMaLoaiViPham);

        add(new JLabel("MSSV"));
        add(txtMSSV);

        add(new JLabel("Ngày vi phạm (yyyy-MM-dd HH:mm)"));
        add(txtNgayViPham);

        add(new JLabel("Mã NV lập biên bản"));
        add(txtMaNV);

        add(new JLabel("Hình thức xử lý"));
        add(txtHinhThucXuLi);

        add(btnThem);
        add(btnSua);

        add(btnXoa);
        add(new JLabel());
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

        add(new JScrollPane(table));

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