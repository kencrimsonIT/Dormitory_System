package com.dormiroty.view;

import com.dormiroty.controller.HopDongController;
import com.dormiroty.entity.HopDong;

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
        setLayout(new GridLayout(14, 2, 5, 5));

        txtMaHopDong = new JTextField();
        txtMSSV = new JTextField();
        txtMaNV = new JTextField();
        txtTienCoc = new JTextField();
        txtNgayLap = new JTextField();
        txtNgayVaoO = new JTextField();
        txtNgayHetHan = new JTextField();
        txtMaPhong = new JTextField();
        txtTrangThaiHD = new JTextField();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        add(new JLabel("Mã hợp đồng"));
        add(txtMaHopDong);

        add(new JLabel("MSSV"));
        add(txtMSSV);

        add(new JLabel("Mã nhân viên"));
        add(txtMaNV);

        add(new JLabel("Tiền cọc"));
        add(txtTienCoc);

        add(new JLabel("Ngày lập (yyyy-MM-dd HH:mm)"));
        add(txtNgayLap);

        add(new JLabel("Ngày vào ở (yyyy-MM-dd HH:mm)"));
        add(txtNgayVaoO);

        add(new JLabel("Ngày hết hạn (yyyy-MM-dd HH:mm)"));
        add(txtNgayHetHan);

        add(new JLabel("Mã phòng"));
        add(txtMaPhong);

        add(new JLabel("Trạng thái hợp đồng"));
        add(txtTrangThaiHD);

        add(btnThem);
        add(btnSua);

        add(btnXoa);
        add(new JLabel());

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

        add(new JScrollPane(table));

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