package com.dormiroty.service.impl;

import com.dormiroty.dao.HopDongDAO;
import com.dormiroty.dao.PhongDAO;
import com.dormiroty.dao.SinhVienDAO;
import com.dormiroty.dao.impl.HopDongDAOImpl;
import com.dormiroty.dao.impl.PhongDAOImpl;
import com.dormiroty.dao.impl.SinhVienDAOImpl;
import com.dormiroty.entity.HopDong;
import com.dormiroty.entity.Phong;
import com.dormiroty.entity.SinhVien;
import com.dormiroty.service.HopDongService;

import java.util.List;

public class HopDongServiceImpl implements HopDongService {
    private HopDongDAO hopDongDAO = new HopDongDAOImpl();
    private SinhVienDAO sinhVienDAO = new SinhVienDAOImpl();
    private PhongDAO phongDAO = new PhongDAOImpl();

    @Override
    public List<HopDong> getAll() {
        return hopDongDAO.findAll();
    }

    @Override
    public HopDong getById(String maHopDong) {
        return hopDongDAO.findById(maHopDong);
    }

    @Override
    public boolean createContract(HopDong hd) {
        // 1. Kiểm tra Sinh viên có tồn tại không
        SinhVien sv = sinhVienDAO.findById(hd.getMSSV());
        if (sv == null) {
            System.out.println("Lỗi: Sinh viên không tồn tại trong hệ thống!");
            return false;
        }

        // 2. Kiểm tra Phòng có tồn tại không
        Phong phong = phongDAO.findById(hd.getMaPhong());
        if (phong == null) {
            System.out.println("Lỗi: Phòng không tồn tại!");
            return false;
        }

        // 3. Kiểm tra tình trạng phòng có cho phép xếp thêm người không
        if (!phong.getTinhTrang().equalsIgnoreCase("Còn trống")) {
            System.out.println("Lỗi: Phòng này đã đầy hoặc đang sửa chữa!");
            return false;
        }

        // Nếu qua hết các bài kiểm tra -> Cho phép lưu hợp đồng mới
        return hopDongDAO.save(hd);
    }

    @Override
    public boolean update(HopDong hopDong) {
        return hopDongDAO.update(hopDong);
    }

    @Override
    public boolean delete(String maHopDong) {
        return hopDongDAO.delete(maHopDong);
    }
}
