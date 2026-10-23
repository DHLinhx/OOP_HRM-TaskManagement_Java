package com.hrmis.dao;

import com.hrmis.data.DatabaseMock;
import com.hrmis.models.ChiTietBangLuong;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChiTietBangLuongDAO implements IDAO<ChiTietBangLuong, Integer> {

    @Override
    public List<ChiTietBangLuong> getAll() {
        return DatabaseMock.dsChiTietBangLuong;
    }

    @Override
    public Optional<ChiTietBangLuong> getById(Integer id) {
        return DatabaseMock.dsChiTietBangLuong.stream()
                .filter(ct -> ct.getMaChiTietLuong() == id)
                .findFirst();
    }

    public List<ChiTietBangLuong> getByBangLuong(int maBangLuong) {
        return DatabaseMock.dsChiTietBangLuong.stream()
                .filter(ct -> ct.getMaBangLuong() == maBangLuong)
                .collect(Collectors.toList());
    }

    @Override
    public void add(ChiTietBangLuong entity) {
        DatabaseMock.dsChiTietBangLuong.add(entity);
    }

    @Override
    public void update(ChiTietBangLuong entity) {
        for (int i = 0; i < DatabaseMock.dsChiTietBangLuong.size(); i++) {
            if (DatabaseMock.dsChiTietBangLuong.get(i).getMaChiTietLuong() == entity.getMaChiTietLuong()) {
                DatabaseMock.dsChiTietBangLuong.set(i, entity);
                return;
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        return DatabaseMock.dsChiTietBangLuong.removeIf(ct -> ct.getMaChiTietLuong() == id);
    }
}
