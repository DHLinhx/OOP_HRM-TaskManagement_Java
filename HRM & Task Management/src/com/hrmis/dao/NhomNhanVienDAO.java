package com.hrmis.dao;

import com.hrmis.data.DatabaseMock;
import com.hrmis.models.NhomNhanVien;

import java.util.List; 
import java.util.Optional;

public class NhomNhanVienDAO implements IDAO<NhomNhanVien, Integer> {

    @Override
    public List<NhomNhanVien> getAll() {
        return DatabaseMock.dsNhomNhanVien;
    }

    @Override
    public Optional<NhomNhanVien> getById(Integer id) {
        return DatabaseMock.dsNhomNhanVien.stream()
                .filter(nhom -> nhom.getmaNhom().equals(id))
                .findFirst();
    }

    public String getTenNhom(int maNhom) {
        return getById(maNhom)
                .map(NhomNhanVien::gettenNhom)
                .orElse("Nhóm " + maNhom);
    }

    @Override
    public void add(NhomNhanVien entity) {
        DatabaseMock.dsNhomNhanVien.add(entity);
    }

    @Override
    public void update(NhomNhanVien entity) {
        for (int i = 0; i < DatabaseMock.dsNhomNhanVien.size(); i++) {
            if (DatabaseMock.dsNhomNhanVien.get(i).getmaNhom().equals(entity.getmaNhom())) {
                DatabaseMock.dsNhomNhanVien.set(i, entity);
                return;
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        return DatabaseMock.dsNhomNhanVien.removeIf(nhom -> nhom.getmaNhom().equals(id));
    }
}
