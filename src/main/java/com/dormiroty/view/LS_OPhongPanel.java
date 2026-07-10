package com.dormiroty.view;

import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.dormiroty.controller.LS_OPhongController;
import com.dormiroty.entity.LS_OPhong;


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


        setLayout(new GridLayout(14,2,5,5));


        txtMaLS = new JTextField();
        txtMSSV = new JTextField();
        txtMaPhong = new JTextField();
        txtNgayVaoO = new JTextField();
        txtNgayChuyenDi = new JTextField();
        txtTrangThai = new JTextField();
        txtTimKiem = new JTextField();



        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnTim = new JButton("Tìm");



        add(new JLabel("Mã Lịch Sử"));
        add(txtMaLS);


        add(new JLabel("MSSV"));
        add(txtMSSV);


        add(new JLabel("Mã Phòng"));
        add(txtMaPhong);


        add(new JLabel("Ngày Vào Ở (yyyy-MM-dd HH:mm)"));
        add(txtNgayVaoO);


        add(new JLabel("Ngày Chuyển Đi (yyyy-MM-dd HH:mm)"));
        add(txtNgayChuyenDi);


        add(new JLabel("Trạng Thái Ở Phòng"));
        add(txtTrangThai);


        add(new JLabel("Tìm theo MSSV"));
        add(txtTimKiem);



        add(btnThem);
        add(btnSua);

        add(btnXoa);
        add(btnTim);



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



        add(new JScrollPane(table));




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