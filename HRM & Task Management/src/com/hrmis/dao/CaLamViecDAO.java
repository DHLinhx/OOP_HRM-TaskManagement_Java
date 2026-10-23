package com.hrmis.dao;

import com.hrmis.data.DatabaseMock;
import com.hrmis.models.CaLamViec;

import java.util.List;
import java.util.Optional;

public class CaLamViecDAO implements IDAO<CaLamViec, Integer> {

    @Override
    public List<CaLamViec> getAll() {
        return DatabaseMock.dsCaLamViec;
    }

    @Override
    public Optional<CaLamViec> getById(Integer id) {
        return DatabaseMock.dsCaLamViec.stream()
                .filter(ca -> ca.getMaCa() == id)
                .findFirst();
    }

    public String getTenCa(int maCa) {
        return getById(maCa)
                .map(CaLamViec::getTenCa)
                .orElse("Ca " + maCa);
    }

    @Override
    public void add(CaLamViec entity) {
        DatabaseMock.dsCaLamViec.add(entity);
    }

    @Override
    public void update(CaLamViec entity) {
        for (int i = 0; i < DatabaseMock.dsCaLamViec.size(); i++) {
            if (DatabaseMock.dsCaLamViec.get(i).getMaCa() == entity.getMaCa()) {
                DatabaseMock.dsCaLamViec.set(i, entity);
                return;
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        return DatabaseMock.dsCaLamViec.removeIf(ca -> ca.getMaCa() == id);
    }
}
