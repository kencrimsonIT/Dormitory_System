package com.dormiroty.view.panels;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.dormiroty.controller.LS_OPhongController;
import com.dormiroty.entity.LS_OPhong;
import com.dormiroty.view.utils.ThemeUtils;


public class LS_OPhongPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private LS_OPhongController controller;


    private JTextField txtMaLS;
    private JTextField txtMSSV;
    private JTextField txtMaPhong;
    private JTextField txtNgayVaoO;
    private JTextField txtNgayChuyenDi;
    private JTextField txtTrangThai;
    private JTextField txtTimKiem;


    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnTim;


    private JTable table;
    private DefaultTableModel model;


    private DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");



    public LS_OPhongPanel() {


        controller = new LS_OPhongController();


        setLayout(new BorderLayout(10, 10));
        setBackground(ThemeUtils.BG_PANEL);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formPanel = ThemeUtils.createFormPanel("Lịch sử ở phòng", 2);

        txtMaLS = ThemeUtils.createTextField(15);
        txtMSSV = ThemeUtils.createTextField(15);
        txtMaPhong = ThemeUtils.createTextField(15);
        txtNgayVaoO = ThemeUtils.createTextField(15);
        txtNgayChuyenDi = ThemeUtils.createTextField(15);
        txtTrangThai = ThemeUtils.createTextField(15);

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

        addFormRow(formPanel, 0, 0, "Mã Lịch Sử", txtMaLS);
        addFormRow(formPanel, 0, 1, "MSSV", txtMSSV);
        addFormRow(formPanel, 1, 0, "Mã Phòng", txtMaPhong);
        addFormRow(formPanel, 1, 1, "Ngày Vào Ở", txtNgayVaoO);
        addFormRow(formPanel, 2, 0, "Ngày Chuyển Đi", txtNgayChuyenDi);
        addFormRow(formPanel, 2, 1, "Trạng Thái", txtTrangThai);



        String[] columns = {

                "Mã LS",
                "MSSV",
                "Mã Phòng",
                "Ngày Vào Ở",
                "Ngày Chuyển Đi",
                "Trạng Thái"

        };


        model = new DefaultTableModel(columns,0);

        table = new JTable(model);



        table.getSelectionModel()
        .addListSelectionListener(e -> {


            int row = table.getSelectedRow();


            if(row >= 0){


                txtMaLS.setText(
                        model.getValueAt(row,0).toString()
                );


                txtMSSV.setText(
                        model.getValueAt(row,1).toString()
                );


                txtMaPhong.setText(
                        model.getValueAt(row,2).toString()
                );


                txtNgayVaoO.setText(
                        model.getValueAt(row,3).toString()
                );


                txtNgayChuyenDi.setText(
                        model.getValueAt(row,4).toString()
                );


                txtTrangThai.setText(
                        model.getValueAt(row,5).toString()
                );

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



        // ================= THÊM =================

        btnThem.addActionListener(e -> {


            try {


                LS_OPhong ls = getDataFromForm();


                if(controller.addLS_OPhong(ls)){


                    JOptionPane.showMessageDialog(
                            this,
                            "Thêm thành công!"
                    );


                    loadTable();
                    clearFields();


                }else{


                    JOptionPane.showMessageDialog(
                            this,
                            "Thêm thất bại!"
                    );

                }



            }catch(Exception ex){

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );

            }


        });





        // ================= SỬA =================

        btnSua.addActionListener(e -> {


            try {


                LS_OPhong ls = getDataFromForm();


                if(controller.updateLS_OPhong(ls)){


                    JOptionPane.showMessageDialog(
                            this,
                            "Cập nhật thành công!"
                    );


                    loadTable();
                    clearFields();

                }



            }catch(Exception ex){

                ex.printStackTrace();

            }


        });






        // ================= XÓA =================

        btnXoa.addActionListener(e -> {


            String maLS =
                    txtMaLS.getText().trim();



            if(maLS.isEmpty()){

                JOptionPane.showMessageDialog(
                        this,
                        "Hãy chọn lịch sử cần xóa!"
                );

                return;

            }



            int confirm =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Bạn có chắc muốn xóa?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );



            if(confirm == JOptionPane.YES_OPTION){


                if(controller.deleteLS_OPhong(maLS)){


                    JOptionPane.showMessageDialog(
                            this,
                            "Xóa thành công!"
                    );


                    loadTable();
                    clearFields();

                }


            }


        });






        // ================= TÌM =================

        btnTim.addActionListener(e -> {


            model.setRowCount(0);



            List<LS_OPhong> list =
                    controller.getLS_OPhongByMSSV(
                            txtTimKiem.getText().trim()
                    );



            for(LS_OPhong ls:list){

                addRow(ls);

            }


        });




        loadTable();

    }







    private LS_OPhong getDataFromForm(){


        LS_OPhong ls = new LS_OPhong();


        ls.setMaLS(
                txtMaLS.getText().trim()
        );


        ls.setMssv(
                txtMSSV.getText().trim()
        );


        ls.setMaPhong(
                txtMaPhong.getText().trim()
        );


        ls.setTrangThaiOPhong(
                txtTrangThai.getText().trim()
        );



        if(!txtNgayVaoO.getText().trim().isEmpty()){


            ls.setNgayVaoO(
                    LocalDateTime.parse(
                            txtNgayVaoO.getText().trim(),
                            formatter
                    )
            );

        }



        if(!txtNgayChuyenDi.getText().trim().isEmpty()){


            ls.setNgayChuyenDi(
                    LocalDateTime.parse(
                            txtNgayChuyenDi.getText().trim(),
                            formatter
                    )
            );

        }



        return ls;

    }    private void addFormRow(JPanel panel, int row, int col, String label, JComponent field) {
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

    private void loadTable(){


        model.setRowCount(0);



        List<LS_OPhong> list =
                controller.getAllLS_OPhong();



        for(LS_OPhong ls:list){


            addRow(ls);


        }


    }







    private void addRow(LS_OPhong ls){


        model.addRow(new Object[]{


                ls.getMaLS(),

                ls.getMssv(),

                ls.getMaPhong(),

                ls.getNgayVaoO(),

                ls.getNgayChuyenDi(),

                ls.getTrangThaiOPhong()


        });


    }







    private void clearFields(){


        txtMaLS.setText("");

        txtMSSV.setText("");

        txtMaPhong.setText("");

        txtNgayVaoO.setText("");

        txtNgayChuyenDi.setText("");

        txtTrangThai.setText("");

        table.clearSelection();

    }

}