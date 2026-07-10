package com.dormiroty.view.panels;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.dormiroty.controller.HoaDonController;
import com.dormiroty.entity.HoaDon;
import com.dormiroty.view.utils.ThemeUtils;

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

        setLayout(new BorderLayout(10, 10));
        setBackground(ThemeUtils.BG_PANEL);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formPanel = ThemeUtils.createFormPanel("Thông tin hóa đơn", 2);

        txtMaHD = ThemeUtils.createTextField(15);
        txtMaHopDong = ThemeUtils.createTextField(15);
        txtMaNV = ThemeUtils.createTextField(15);
        txtMSSV = ThemeUtils.createTextField(15);
        txtSoTien = ThemeUtils.createTextField(15);
        cboHinhThuc = ThemeUtils.createComboBox(new String[]{"Tiền mặt", "Chuyển khoản"});

        addFormRow(formPanel, 0, 0, "Mã hóa đơn", txtMaHD);
        addFormRow(formPanel, 0, 1, "Mã hợp đồng", txtMaHopDong);
        addFormRow(formPanel, 1, 0, "Mã nhân viên", txtMaNV);
        addFormRow(formPanel, 1, 1, "MSSV", txtMSSV);
        addFormRow(formPanel, 2, 0, "Số tiền", txtSoTien);
        addFormRow(formPanel, 2, 1, "Hình thức", cboHinhThuc);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        searchPanel.setOpaque(false);
        JLabel lblTim = ThemeUtils.createLabel("Tìm theo MSSV:");
        txtTimKiem = ThemeUtils.createTextField(20);
        searchPanel.add(lblTim);
        searchPanel.add(txtTimKiem);

        btnThem = ThemeUtils.createSuccessButton("Thêm");
        btnSua = ThemeUtils.createPrimaryButton("Sửa");
        btnXoa = ThemeUtils.createDangerButton("Xóa");
        btnTim = ThemeUtils.createWarningButton("Tìm");
        JPanel btnPanel = ThemeUtils.createButtonPanel(btnThem, btnSua, btnXoa, btnTim);

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

        ThemeUtils.styleTable(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(ThemeUtils.BORDER_COLOR, 1, true));

        JPanel northPanel = new JPanel(new BorderLayout(10, 10));
        northPanel.setOpaque(false);
        northPanel.add(formPanel, BorderLayout.NORTH);
        northPanel.add(searchPanel, BorderLayout.CENTER);
        northPanel.add(btnPanel, BorderLayout.SOUTH);

        add(northPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

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