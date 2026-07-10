package com.dormiroty.view;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.dormiroty.controller.PhongController;
import com.dormiroty.entity.Phong;

public class PhongPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private PhongController controller;

    private JTextField txtMaPhong;
    private JTextField txtMaLoaiPhong;
    private JTextField txtMaToaNha;
    private JTextField txtSoChoTrong;
    private JTextField txtTrangThai;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;

    private JTable table;
    private DefaultTableModel model;

    public PhongPanel() {

        controller = new PhongController();
        setLayout(new GridLayout(10, 2, 5, 5));

        txtMaPhong = new JTextField();
        txtMaLoaiPhong = new JTextField();
        txtMaToaNha = new JTextField();
        txtSoChoTrong = new JTextField();
        txtTrangThai = new JTextField();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        add(new JLabel("Mã phòng"));
        add(txtMaPhong);

        add(new JLabel("Mã loại phòng"));
        add(txtMaLoaiPhong);

        add(new JLabel("Mã tòa nhà"));
        add(txtMaToaNha);

        add(new JLabel("Số chỗ trống"));
        add(txtSoChoTrong);

        add(new JLabel("Trạng thái"));
        add(txtTrangThai);

        add(btnThem);
        add(btnSua);

        add(btnXoa);
        add(new JLabel(""));

        String[] columns = {
                "Mã phòng",
                "Mã loại phòng",
                "Mã tòa nhà",
                "Số chỗ trống",
                "Trạng thái"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        loadTable();

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                txtMaPhong.setText(model.getValueAt(row, 0).toString());
                txtMaLoaiPhong.setText(model.getValueAt(row, 1).toString());
                txtMaToaNha.setText(model.getValueAt(row, 2).toString());
                txtSoChoTrong.setText(model.getValueAt(row, 3).toString());
                txtTrangThai.setText(model.getValueAt(row, 4).toString());
            }
        });

        btnThem.addActionListener(e -> {
            try {
                Phong p = new Phong();
                p.setMaPhong(txtMaPhong.getText());
                p.setMaLoaiPhong(txtMaLoaiPhong.getText());
                p.setMaToaNha(txtMaToaNha.getText());
                p.setSoChoTrong(Integer.parseInt(txtSoChoTrong.getText())); // THÊM MỚI (Ép kiểu sang int)
                p.setTrangThai(txtTrangThai.getText());

                if (controller.addPhong(p)) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    loadTable();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Số chỗ trống phải là một số nguyên hợp lệ!");
            }
        });

        btnSua.addActionListener(e -> {
            try {
                Phong p = new Phong();
                p.setMaPhong(txtMaPhong.getText());
                p.setMaLoaiPhong(txtMaLoaiPhong.getText());
                p.setMaToaNha(txtMaToaNha.getText());
                p.setSoChoTrong(Integer.parseInt(txtSoChoTrong.getText()));
                p.setTrangThai(txtTrangThai.getText());

                if (controller.updatePhong(p)) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thất bại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Số chỗ trống phải là một số nguyên hợp lệ!");
            }
        });

        btnXoa.addActionListener(e -> {
            String maPhong = txtMaPhong.getText();
            if (controller.deletePhong(maPhong)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                loadTable();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
            }
        });
    }
    private void clearFields() {
        txtMaPhong.setText("");
        txtMaLoaiPhong.setText("");
        txtMaToaNha.setText("");
        txtSoChoTrong.setText("");
        txtTrangThai.setText("");
    }

    private void loadTable() {
        model.setRowCount(0);
        List<Phong> list = controller.getAllPhong();

        for (Phong p : list) {
            model.addRow(new Object[] {
                    p.getMaPhong(),
                    p.getMaLoaiPhong(),
                    p.getMaToaNha(),
                    p.getSoChoTrong(),
                    p.getTrangThai()
            });
        }
    }
}