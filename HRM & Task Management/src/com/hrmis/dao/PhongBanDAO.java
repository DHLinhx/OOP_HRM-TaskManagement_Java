package com.hrmis.dao;

import com.hrmis.data.DatabaseMock;
import com.hrmis.models.PhongBan;

import java.util.List;
import java.util.Optional;

public class PhongBanDAO implements IDAO<PhongBan, Integer> {

    @Override
    public List<PhongBan> getAll() {
        return DatabaseMock.dsPhongBan;
    }

    @Override
    public Optional<PhongBan> getById(Integer id) {
        return DatabaseMock.dsPhongBan.stream()
                .filter(pb -> pb.getmaPhongBan().equals(id))
                .findFirst();
    }

    public String getTenPhongBan(int maPB) {
        return getById(maPB)
                .map(PhongBan::gettenPhongBan)
                .orElse("Phòng " + maPB);
    }

    @Override
    public void add(PhongBan entity) {
        DatabaseMock.dsPhongBan.add(entity);
    }

    @Override
    public void update(PhongBan entity) {
        for (int i = 0; i < DatabaseMock.dsPhongBan.size(); i++) {
            if (DatabaseMock.dsPhongBan.get(i).getmaPhongBan().equals(entity.getmaPhongBan())) {
                DatabaseMock.dsPhongBan.set(i, entity);
                return;
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        return DatabaseMock.dsPhongBan.removeIf(pb -> pb.getmaPhongBan().equals(id));
    }
}
