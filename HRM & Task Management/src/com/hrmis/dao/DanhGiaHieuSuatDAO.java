package com.hrmis.dao;

import com.hrmis.data.DatabaseMock;
import com.hrmis.enums.XepLoai;
import com.hrmis.models.DanhGiaHieuSuat;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DanhGiaHieuSuatDAO implements IDAO<DanhGiaHieuSuat, Integer> {

    @Override
    public List<DanhGiaHieuSuat> getAll() {
        return DatabaseMock.dsDanhGiaHieuSuat;
    }

    @Override
    public Optional<DanhGiaHieuSuat> getById(Integer id) {
        return DatabaseMock.dsDanhGiaHieuSuat.stream()
                .filter(dg -> dg.getMaDanhGia() == id)
                .findFirst();
    }

    public List<DanhGiaHieuSuat> getByNam(int nam) {
        return DatabaseMock.dsDanhGiaHieuSuat.stream()
                .filter(dg -> dg.getNam() == nam)
                .collect(Collectors.toList());
    }

    public List<DanhGiaHieuSuat> getByXepLoai(int nam, XepLoai xl) {
        return DatabaseMock.dsDanhGiaHieuSuat.stream()
                .filter(dg -> dg.getNam() == nam && dg.getXepLoai() == xl)
                .sorted(Comparator.comparingDouble(DanhGiaHieuSuat::getDiemTrungBinh).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void add(DanhGiaHieuSuat entity) {
        DatabaseMock.dsDanhGiaHieuSuat.add(entity);
    }

    @Override
    public void update(DanhGiaHieuSuat entity) {
        for (int i = 0; i < DatabaseMock.dsDanhGiaHieuSuat.size(); i++) {
            if (DatabaseMock.dsDanhGiaHieuSuat.get(i).getMaDanhGia() == entity.getMaDanhGia()) {
                DatabaseMock.dsDanhGiaHieuSuat.set(i, entity);
                return;
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        return DatabaseMock.dsDanhGiaHieuSuat.removeIf(dg -> dg.getMaDanhGia() == id);
    }
}
