package com.dormiroty.view;

import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.dormiroty.controller.LS_ViPhamController;
import com.dormiroty.entity.LS_ViPham;

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

        setLayout(new GridLayout(12,2,5,5));

        txtMaLS = new JTextField();
        txtMaLoaiVP = new JTextField();
        txtMSSV = new JTextField();
        txtNgayVP = new JTextField();
        txtMaNV = new JTextField();
        txtTimKiem = new JTextField();

        cboHinhThuc = new JComboBox<>(new String[]{
                "Cảnh cáo",
                "Khiển trách",
                "Phạt tiền",
                "Đình chỉ"
        });

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnTim = new JButton("Tìm");

        add(new JLabel("Mã LS Vi Phạm"));
        add(txtMaLS);

        add(new JLabel("Mã Loại Vi Phạm"));
        add(txtMaLoaiVP);

        add(new JLabel("MSSV"));
        add(txtMSSV);

        add(new JLabel("Ngày Vi Phạm (yyyy-MM-dd HH:mm)"));
        add(txtNgayVP);

        add(new JLabel("Mã Nhân Viên"));
        add(txtMaNV);

        add(new JLabel("Hình Thức Xử Lý"));
        add(cboHinhThuc);

        add(new JLabel("Tìm theo MSSV"));
        add(txtTimKiem);

        add(btnThem);
        add(btnSua);

        add(btnXoa);
        add(btnTim);

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

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
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