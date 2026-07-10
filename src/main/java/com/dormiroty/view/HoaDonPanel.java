package com.dormiroty.view;

import java.awt.GridLayout;
import java.math.BigDecimal;
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

import com.dormiroty.controller.HoaDonController;
import com.dormiroty.entity.HoaDon;

public class HoaDonPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private HoaDonController controller;

    private JTextField txtMaHD;
    private JTextField txtMaHopDong;
    private JTextField txtMaNV;
    private JTextField txtMSSV;
    private JTextField txtSoTien;
    private JTextField txtTimKiem;

    private JComboBox<String> cboHinhThuc;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnTim;

    private JTable table;
    private DefaultTableModel model;

    public HoaDonPanel() {

        controller = new HoaDonController();

        setLayout(new GridLayout(12,2,5,5));

        txtMaHD = new JTextField();
        txtMaHopDong = new JTextField();
        txtMaNV = new JTextField();
        txtMSSV = new JTextField();
        txtSoTien = new JTextField();
        txtTimKiem = new JTextField();

        cboHinhThuc = new JComboBox<>(
                new String[]{
                        "Tiền mặt",
                        "Chuyển khoản"
                });

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnTim = new JButton("Tìm");

        add(new JLabel("Mã hóa đơn"));
        add(txtMaHD);

        add(new JLabel("Mã hợp đồng"));
        add(txtMaHopDong);

        add(new JLabel("Mã nhân viên"));
        add(txtMaNV);

        add(new JLabel("MSSV"));
        add(txtMSSV);

        add(new JLabel("Số tiền"));
        add(txtSoTien);

        add(new JLabel("Hình thức"));
        add(cboHinhThuc);

        add(new JLabel("Tìm theo MSSV"));
        add(txtTimKiem);

        add(btnThem);
        add(btnSua);

        add(btnXoa);
        add(btnTim);

        String[] columns = {
                "Mã HD",
                "Mã HĐ",
                "Mã NV",
                "MSSV",
                "Số tiền",
                "Hình thức"
        };

        model = new DefaultTableModel(columns,0);
        table = new JTable(model);

        table.getSelectionModel().addListSelectionListener(e->{

            int row = table.getSelectedRow();

            if(row>=0){

                txtMaHD.setText(model.getValueAt(row,0).toString());
                txtMaHopDong.setText(model.getValueAt(row,1).toString());
                txtMaNV.setText(model.getValueAt(row,2).toString());
                txtMSSV.setText(model.getValueAt(row,3).toString());
                txtSoTien.setText(model.getValueAt(row,4).toString());
                cboHinhThuc.setSelectedItem(model.getValueAt(row,5).toString());

            }

        });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        btnThem.addActionListener(e -> {
            try {
                HoaDon hd = new HoaDon();

                hd.setMaHD(txtMaHD.getText().trim());
                hd.setMaHopDong(txtMaHopDong.getText().trim());
                hd.setMaNV(txtMaNV.getText().trim());
                hd.setMssv(txtMSSV.getText().trim());
                hd.setSoTienTra(new BigDecimal(txtSoTien.getText().trim()));
                hd.setHinhThuc(cboHinhThuc.getSelectedItem().toString());

                if (controller.addHoaDon(hd)) {
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

                HoaDon hd = new HoaDon();

                hd.setMaHD(txtMaHD.getText().trim());
                hd.setMaHopDong(txtMaHopDong.getText().trim());
                hd.setMaNV(txtMaNV.getText().trim());
                hd.setMssv(txtMSSV.getText().trim());
                hd.setSoTienTra(new BigDecimal(txtSoTien.getText().trim()));
                hd.setHinhThuc(cboHinhThuc.getSelectedItem().toString());

                if (controller.updateHoaDon(hd)) {
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

            String maHD = txtMaHD.getText().trim();

            if (maHD.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Chọn hóa đơn cần xóa!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc muốn xóa?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {

                if (controller.deleteHoaDon(maHD)) {

                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    loadTable();
                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(this, "Xóa thất bại!");

                }

            }

        });

        btnTim.addActionListener(e -> {

            model.setRowCount(0);

            List<HoaDon> list =
                    controller.getHoaDonByMSSV(txtTimKiem.getText().trim());

            for (HoaDon hd : list) {

                model.addRow(new Object[]{
                        hd.getMaHD(),
                        hd.getMaHopDong(),
                        hd.getMaNV(),
                        hd.getMssv(),
                        hd.getSoTienTra(),
                        hd.getHinhThuc()
                });

            }

        });

        loadTable();

    }

    private void clearFields() {

        txtMaHD.setText("");
        txtMaHopDong.setText("");
        txtMaNV.setText("");
        txtMSSV.setText("");
        txtSoTien.setText("");
        txtTimKiem.setText("");

    }

    private void loadTable() {

        model.setRowCount(0);

        List<HoaDon> list = controller.getAllHoaDon();

        for (HoaDon hd : list) {

            model.addRow(new Object[]{
                    hd.getMaHD(),
                    hd.getMaHopDong(),
                    hd.getMaNV(),
                    hd.getMssv(),
                    hd.getSoTienTra(),
                    hd.getHinhThuc()
            });

        }

    }

}