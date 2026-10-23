package com.hrmis.dao;

import com.hrmis.data.DatabaseMock;
import com.hrmis.models.BangLuong;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BangLuongDAO implements IDAO<BangLuong, Integer> {

    @Override
    public List<BangLuong> getAll() {
        return DatabaseMock.dsBangLuong;
    }

    @Override
    public Optional<BangLuong> getById(Integer id) {
        return DatabaseMock.dsBangLuong.stream()
                .filter(bl -> bl.getMaBangLuong() == id)
                .findFirst();
    }

    public Optional<BangLuong> getByNhanVienVaKy(int maNV, int thang, int nam) {
        return DatabaseMock.dsBangLuong.stream()
                .filter(bl -> bl.getMaNhanVien() == maNV && bl.getKyThang() == thang && bl.getKyNam() == nam)
                .findFirst();
    }

    public List<BangLuong> getByKy(int thang, int nam) {
        return DatabaseMock.dsBangLuong.stream()
                .filter(bl -> bl.getKyThang() == thang && bl.getKyNam() == nam)
                .collect(Collectors.toList());
    }

    public List<BangLuong> getTopThuNhap(int thang, int nam, int limit) {
        return DatabaseMock.dsBangLuong.stream()
                .filter(bl -> bl.getKyThang() == thang && bl.getKyNam() == nam)
                .sorted(Comparator.comparingDouble(BangLuong::getThucLanh).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public void add(BangLuong entity) {
        DatabaseMock.dsBangLuong.add(entity);
    }

    @Override
    public void update(BangLuong entity) {
        for (int i = 0; i < DatabaseMock.dsBangLuong.size(); i++) {
            if (DatabaseMock.dsBangLuong.get(i).getMaBangLuong() == entity.getMaBangLuong()) {
                DatabaseMock.dsBangLuong.set(i, entity);
                return;
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        return DatabaseMock.dsBangLuong.removeIf(bl -> bl.getMaBangLuong() == id);
    }
}
