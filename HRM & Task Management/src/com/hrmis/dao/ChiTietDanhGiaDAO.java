package com.hrmis.dao;

import com.hrmis.data.DatabaseMock;
import com.hrmis.models.ChiTietDanhGia;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChiTietDanhGiaDAO implements IDAO<ChiTietDanhGia, Integer> {

    @Override
    public List<ChiTietDanhGia> getAll() {
        return DatabaseMock.dsChiTietDanhGia;
    }

    @Override
    public Optional<ChiTietDanhGia> getById(Integer id) {
        return DatabaseMock.dsChiTietDanhGia.stream()
                .filter(ct -> ct.getMaChiTietDanhGia() == id)
                .findFirst();
    }

    public List<ChiTietDanhGia> getByDanhGia(int maDanhGia) {
        return DatabaseMock.dsChiTietDanhGia.stream()
                .filter(ct -> ct.getMaDanhGia() == maDanhGia)
                .collect(Collectors.toList());
    }

    @Override
    public void add(ChiTietDanhGia entity) {
        DatabaseMock.dsChiTietDanhGia.add(entity);
    }

    @Override
    public void update(ChiTietDanhGia entity) {
        for (int i = 0; i < DatabaseMock.dsChiTietDanhGia.size(); i++) {
            if (DatabaseMock.dsChiTietDanhGia.get(i).getMaChiTietDanhGia() == entity.getMaChiTietDanhGia()) {
                DatabaseMock.dsChiTietDanhGia.set(i, entity);
                return;
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        return DatabaseMock.dsChiTietDanhGia.removeIf(ct -> ct.getMaChiTietDanhGia() == id);
    }
}
