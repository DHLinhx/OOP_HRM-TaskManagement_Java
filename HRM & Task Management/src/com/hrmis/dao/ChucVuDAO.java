package com.hrmis.dao;

import com.hrmis.data.DatabaseMock;
import com.hrmis.models.ChucVu;

import java.util.List;
import java.util.Optional;

public class ChucVuDAO implements IDAO<ChucVu, Integer> { 

    @Override
    public List<ChucVu> getAll() {
        return DatabaseMock.dsChucVu;
    }

    @Override
    public Optional<ChucVu> getById(Integer id) {
        return DatabaseMock.dsChucVu.stream()
                .filter(cv -> cv.getmaChucVu().equals(id))
                .findFirst();
    }

    public String getTenChucVu(int maCV) {
        return getById(maCV)
                .map(ChucVu::gettenChucVu)
                .orElse("Chức vụ " + maCV);
    }

    @Override
    public void add(ChucVu entity) {
        DatabaseMock.dsChucVu.add(entity);
    }

    @Override
    public void update(ChucVu entity) {
        for (int i = 0; i < DatabaseMock.dsChucVu.size(); i++) {
            if (DatabaseMock.dsChucVu.get(i).getmaChucVu().equals(entity.getmaChucVu())) {
                DatabaseMock.dsChucVu.set(i, entity);
                return;
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        return DatabaseMock.dsChucVu.removeIf(cv -> cv.getmaChucVu().equals(id));
    }
}
