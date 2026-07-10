package com.dormiroty.service.impl;

import com.dormiroty.dao.LS_OPhongDAO;
import com.dormiroty.dao.impl.LS_OPhongDAOImpl;
import com.dormiroty.entity.LS_OPhong;
import com.dormiroty.service.LS_OPhongService;

import java.util.List;

public class LS_OPhongServiceImpl implements LS_OPhongService {

    private LS_OPhongDAO dao = new LS_OPhongDAOImpl();

    @Override
    public List<LS_OPhong> getAll() {
        return dao.findAll();
    }

    @Override
    public LS_OPhong getById(String maLS) {
        if (maLS == null || maLS.trim().isEmpty()) {
            return null;
        }
        return dao.findById(maLS);
    }

    @Override
    public boolean create(LS_OPhong ls) {
        // Kiểm tra trùng mã
        if (dao.findById(ls.getMaLS()) != null) {
            System.out.println("Lỗi: Mã lịch sử ở phòng này đã tồn tại!");
            return false;
        }

        // Nghiệp vụ (Check constraint từ Database): Ngày chuyển đi (nếu có) không được nhỏ hơn ngày vào ở
        if (ls.getNgayChuyenDi() != null && ls.getNgayVaoO() != null) {
            if (ls.getNgayChuyenDi().isBefore(ls.getNgayVaoO())) {
                System.out.println("Lỗi Nghiệp vụ: Ngày chuyển đi không được nhỏ hơn ngày vào ở!");
                return false;
            }
        }

        return dao.save(ls);
    }

    @Override
    public boolean update(LS_OPhong ls) {
        if (dao.findById(ls.getMaLS()) == null) {
            System.out.println("Lỗi: Không tìm thấy lịch sử để cập nhật!");
            return false;
        }

        // Nghiệp vụ check logic ngày tháng tương tự hàm create
        if (ls.getNgayChuyenDi() != null && ls.getNgayVaoO() != null) {
            if (ls.getNgayChuyenDi().isBefore(ls.getNgayVaoO())) {
                System.out.println("Lỗi Nghiệp vụ: Ngày chuyển đi không được nhỏ hơn ngày vào ở!");
                return false;
            }
        }

        return dao.update(ls);
    }

    @Override
    public boolean delete(String maLS) {
        return dao.delete(maLS);
    }

    @Override
    public List<LS_OPhong> getByMSSV(String mssv) {
        return dao.findByMSSV(mssv);
    }
}