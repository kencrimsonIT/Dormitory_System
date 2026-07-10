package com.dormiroty.view.panels;

import com.dormiroty.controller.HopDongController;
import com.dormiroty.entity.HopDong;
import com.dormiroty.view.utils.ThemeUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HopDongPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private HopDongController controller;

    private JTextField txtMaHopDong;
    private JTextField txtMSSV;
    private JTextField txtMaNV;
    private JTextField txtTienCoc;
    private JTextField txtNgayLap;
    private JTextField txtNgayVaoO;
    private JTextField txtNgayHetHan;
    private JTextField txtMaPhong;
    private JTextField txtTrangThaiHD;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;

    private JTable table;
    private DefaultTableModel model;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public HopDongPanel() {

        controller = new HopDongController();
        setLayout(new BorderLayout(10, 10));
        setBackground(ThemeUtils.BG_PANEL);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formPanel = ThemeUtils.createFormPanel("Thông tin hợp đồng", 2);

        txtMaHopDong = ThemeUtils.createTextField(15);
        txtMSSV = ThemeUtils.createTextField(15);
        txtMaNV = ThemeUtils.createTextField(15);
        txtTienCoc = ThemeUtils.createTextField(15);
        txtNgayLap = ThemeUtils.createTextField(15);
        txtNgayVaoO = ThemeUtils.createTextField(15);
        txtNgayHetHan = ThemeUtils.createTextField(15);
        txtMaPhong = ThemeUtils.createTextField(15);
        txtTrangThaiHD = ThemeUtils.createTextField(15);

        btnThem = ThemeUtils.createSuccessButton("Thêm");
        btnSua = ThemeUtils.createPrimaryButton("Sửa");
        btnXoa = ThemeUtils.createDangerButton("Xóa");
        JPanel btnPanel = ThemeUtils.createButtonPanel(btnThem, btnSua, btnXoa);

        addFormRow(formPanel, 0, 0, "Mã hợp đồng", txtMaHopDong);
        addFormRow(formPanel, 0, 1, "MSSV", txtMSSV);
        addFormRow(formPanel, 1, 0, "Mã nhân viên", txtMaNV);
        addFormRow(formPanel, 1, 1, "Tiền cọc", txtTienCoc);
        addFormRow(formPanel, 2, 0, "Ngày lập (yyyy-MM-dd HH:mm)", txtNgayLap);
        addFormRow(formPanel, 2, 1, "Ngày vào ở", txtNgayVaoO);
        addFormRow(formPanel, 3, 0, "Ngày hết hạn", txtNgayHetHan);
        addFormRow(formPanel, 3, 1, "Mã phòng", txtMaPhong);
        addFormRow(formPanel, 4, 0, "Trạng thái HD", txtTrangThaiHD);

        String[] columns = {
                "Mã HD",
                "MSSV",
                "Mã NV",
                "Tiền cọc",
                "Ngày lập",
                "Ngày vào ở",
                "Ngày hết hạn",
                "Mã phòng",
                "Trạng thái"
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
                txtMaHopDong.setText(model.getValueAt(row, 0).toString());
                txtMSSV.setText(model.getValueAt(row, 1).toString());
                txtMaNV.setText(model.getValueAt(row, 2).toString());
                txtTienCoc.setText(model.getValueAt(row, 3).toString());
                txtNgayLap.setText(model.getValueAt(row, 4).toString());
                txtNgayVaoO.setText(model.getValueAt(row, 5) != null ? model.getValueAt(row, 5).toString() : ""); // THÊM MỚI
                txtNgayHetHan.setText(model.getValueAt(row, 6).toString());
                txtMaPhong.setText(model.getValueAt(row, 7).toString());
                txtTrangThaiHD.setText(model.getValueAt(row, 8) != null ? model.getValueAt(row, 8).toString() : ""); // THÊM MỚI
            }
        });

        btnThem.addActionListener(e -> saveData(true));
        btnSua.addActionListener(e -> saveData(false));

        btnXoa.addActionListener(e -> {
            if (controller.deleteHopDong(txtMaHopDong.getText())) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                loadTable();
            }
        });
    }

    private void saveData(boolean insert) {
        try {
            HopDong hd = new HopDong();

            hd.setMaHopDong(txtMaHopDong.getText());
            hd.setMssv(txtMSSV.getText());

            hd.setMaNV(txtMaNV.getText());
            hd.setTienCoc(new BigDecimal(txtTienCoc.getText()));
            DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            if (!txtNgayLap.getText().trim().isEmpty()) {
                hd.setNgayLap(LocalDateTime.parse(txtNgayLap.getText().trim(), parseFormatter));
            }
            if (!txtNgayVaoO.getText().trim().isEmpty()) {
                hd.setNgayVaoO(LocalDateTime.parse(txtNgayVaoO.getText().trim(), parseFormatter));
            }
            if (!txtNgayHetHan.getText().trim().isEmpty()) {
                hd.setNgayHetHan(LocalDateTime.parse(txtNgayHetHan.getText().trim(), parseFormatter));
            }

            hd.setMaPhong(txtMaPhong.getText());
            hd.setTrangThaiHopDong(txtTrangThaiHD.getText());

            boolean result;
            if (insert) {
                result = controller.addHopDong(hd);
            } else {
                result = controller.updateHopDong(hd);
            }

            if (result) {
                JOptionPane.showMessageDialog(this, insert ? "Thêm hợp đồng thành công" : "Sửa hợp đồng thành công");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Phòng đã đầy hoặc dữ liệu không hợp lệ");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng: " + ex.getMessage());
        }
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
        List<HopDong> list = controller.getAllHopDong();

        for (HopDong hd : list) {
            String ngayLapStr = hd.getNgayLap() != null ? hd.getNgayLap().format(formatter) : "";
            String ngayVaoOStr = hd.getNgayVaoO() != null ? hd.getNgayVaoO().format(formatter) : "";
            String ngayHetHanStr = hd.getNgayHetHan() != null ? hd.getNgayHetHan().format(formatter) : "";

            model.addRow(new Object[]{
                    hd.getMaHopDong(),
                    hd.getMssv(),
                    hd.getMaNV(),
                    hd.getTienCoc(),
                    ngayLapStr,
                    ngayVaoOStr,
                    ngayHetHanStr,
                    hd.getMaPhong(),
                    hd.getTrangThaiHopDong()
            });
        }
    }
}