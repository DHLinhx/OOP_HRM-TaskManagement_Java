package com.hrmis.dao;

import com.hrmis.data.DatabaseMock;
import com.hrmis.enums.TrangThaiDuyet;
import com.hrmis.models.DonXinNghi;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DonXinNghiDAO implements IDAO<DonXinNghi, Integer> {

    @Override
    public List<DonXinNghi> getAll() {
        return DatabaseMock.dsDonXinNghi;
    }

    @Override
    public Optional<DonXinNghi> getById(Integer id) {
        return DatabaseMock.dsDonXinNghi.stream()
                .filter(dn -> dn.getMaDonNghi() == id)
                .findFirst();
    }

    public List<DonXinNghi> getChoDuyet() {
        return DatabaseMock.dsDonXinNghi.stream()
                .filter(dn -> dn.getTrangThaiDuyet() == TrangThaiDuyet.CHO_DUYET)
                .collect(Collectors.toList());
    }

    public List<DonXinNghi> getByNhanVien(int maNV) {
        return DatabaseMock.dsDonXinNghi.stream()
                .filter(dn -> dn.getMaNhanVien() == maNV)
                .collect(Collectors.toList());
    }

    @Override
    public void add(DonXinNghi entity) {
        DatabaseMock.dsDonXinNghi.add(entity);
    }

    @Override
    public void update(DonXinNghi entity) {
        for (int i = 0; i < DatabaseMock.dsDonXinNghi.size(); i++) {
            if (DatabaseMock.dsDonXinNghi.get(i).getMaDonNghi() == entity.getMaDonNghi()) {
                DatabaseMock.dsDonXinNghi.set(i, entity);
                return;
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        return DatabaseMock.dsDonXinNghi.removeIf(dn -> dn.getMaDonNghi() == id);
    }
}
