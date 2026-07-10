package com.dormiroty.service.impl;

import com.dormiroty.dao.HopDongDAO;
import com.dormiroty.dao.PhongDAO;
import com.dormiroty.dao.SinhVienDAO;
import com.dormiroty.dao.LoaiPhongDAO;
import com.dormiroty.dao.impl.HopDongDAOImpl;
import com.dormiroty.dao.impl.PhongDAOImpl;
import com.dormiroty.dao.impl.SinhVienDAOImpl;
import com.dormiroty.dao.impl.LoaiPhongDAOImpl;
import com.dormiroty.entity.HopDong;
import com.dormiroty.entity.LoaiPhong;
import com.dormiroty.entity.Phong;
import com.dormiroty.entity.SinhVien;
import com.dormiroty.service.HopDongService;

import java.util.List;

public class HopDongServiceImpl implements HopDongService {
    private HopDongDAO hopDongDAO = new HopDongDAOImpl();
    private SinhVienDAO sinhVienDAO = new SinhVienDAOImpl();
    private PhongDAO phongDAO = new PhongDAOImpl();
    private LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAOImpl();

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
        Phong phong = phongDAO.findById(hd.getMaPhong());
        if (phong == null) {
            System.out.println("Lỗi: Phòng không tồn tại!");
            return false;
        }
        if (phong.getSoChoTrong() <= 0) {
            System.out.println("Lỗi Nghiệp Vụ: Phòng " + hd.getMaPhong() + " đã hết chỗ. Không thể tạo thêm hợp đồng!");
            return false;
        }
        if (hd.getNgayLap() == null) {
            hd.setNgayLap(java.time.LocalDateTime.now());
        }
        boolean isSaved = hopDongDAO.save(hd);
        if (isSaved) {
            int soChoMoi = phong.getSoChoTrong() - 1;
            phong.setSoChoTrong(soChoMoi);

            if (soChoMoi == 0) {
                phong.setTrangThai("Đã đầy");
                System.out.println("Thông báo: Phòng " + hd.getMaPhong() + " đã chuyển sang trạng thái 'Đã đầy'.");
            }
            phongDAO.update(phong);
        }

        return isSaved;
    }

    @Override
    public boolean update(HopDong hopDong) {
        return hopDongDAO.update(hopDong);
    }

    @Override
    public boolean delete(String maHopDong) {
        return hopDongDAO.delete(maHopDong);
    }

    @Override
    public double calculateTotalTienCoc() {
        return hopDongDAO.calculateTotalTienCoc();
    }
}
