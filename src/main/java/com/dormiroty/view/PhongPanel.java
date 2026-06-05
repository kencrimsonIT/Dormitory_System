package com.dormiroty.view;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
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
    private JTextField txtTinhTrang;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;

    private JTable table;
    private DefaultTableModel model;

    public PhongPanel() {

        controller = new PhongController();

        setLayout(new GridLayout(8, 2, 5, 5));

        txtMaPhong = new JTextField();
        txtMaLoaiPhong = new JTextField();
        txtMaToaNha = new JTextField();
        txtTinhTrang = new JTextField();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        add(new JLabel("Mã phòng"));
        add(txtMaPhong);

        add(new JLabel("Mã loại phòng"));
        add(txtMaLoaiPhong);

        add(new JLabel("Mã tòa nhà"));
        add(txtMaToaNha);

        add(new JLabel("Tình trạng"));
        add(txtTinhTrang);

        add(btnThem);
        add(btnSua);

        add(btnXoa);
        add(new JLabel(""));

        String[] columns = {
                "Mã phòng",
                "Mã loại phòng",
                "Mã tòa nhà",
                "Tình trạng"
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
        	    txtTinhTrang.setText(model.getValueAt(row, 3).toString());
        	}
  

        	});

        	btnThem.addActionListener(e -> {

   
        	Phong p = new Phong();

        	p.setMaPhong(txtMaPhong.getText());
        	p.setMaLoaiPhong(txtMaLoaiPhong.getText());
        	p.setMaToaNha(txtMaToaNha.getText());
        	p.setTinhTrang(txtTinhTrang.getText());

        	if (controller.addPhong(p)) {

        	    loadTable();

        	    txtMaPhong.setText("");
        	    txtMaLoaiPhong.setText("");
        	    txtMaToaNha.setText("");
        	    txtTinhTrang.setText("");
        	}
   
        	});

        	btnSua.addActionListener(e -> {
        		System.out.println("Da bam nut Sua");
        	Phong p = new Phong();

        	p.setMaPhong(txtMaPhong.getText());
        	p.setMaLoaiPhong(txtMaLoaiPhong.getText());
        	p.setMaToaNha(txtMaToaNha.getText());
        	p.setTinhTrang(txtTinhTrang.getText());
        	boolean result = controller.updatePhong(p);
        	System.out.println("Ket qua update = " + result);
        	if (result) {
        	    loadTable();
        	}
    

        	});

        	btnXoa.addActionListener(e -> {

   
        	String maPhong = txtMaPhong.getText();

        	if (controller.deletePhong(maPhong)) {

        	    loadTable();

        	    txtMaPhong.setText("");
        	    txtMaLoaiPhong.setText("");
        	    txtMaToaNha.setText("");
        	    txtTinhTrang.setText("");
        	}

        	});

    }

    private void loadTable() {

        model.setRowCount(0);

        List<Phong> list = controller.getAllPhong();

        for (Phong p : list) {

            model.addRow(new Object[] {
                    p.getMaPhong(),
                    p.getMaLoaiPhong(),
                    p.getMaToaNha(),
                    p.getTinhTrang()
            });
        }
    }
}