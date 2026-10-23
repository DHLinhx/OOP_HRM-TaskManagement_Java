package com.hrmis.dao;

import com.hrmis.data.DatabaseMock;
import com.hrmis.models.ThanhVienNhom;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ThanhVienNhomDAO implements IDAO<ThanhVienNhom, String> {

    @Override
    public List<ThanhVienNhom> getAll() {
        return DatabaseMock.dsThanhVienNhom;
    }

    /**
     * Khóa phức hợp định dạng "maNhom_maNV"
     */
    @Override
    public Optional<ThanhVienNhom> getById(String compositeKey) {
        String[] parts = compositeKey.split("_");
        if (parts.length != 2) return Optional.empty();
        int maNhom = Integer.parseInt(parts[0]);
        int maNV = Integer.parseInt(parts[1]);
        return DatabaseMock.dsThanhVienNhom.stream()
                .filter(tv -> tv.getmaNhom() != null && tv.getmaNhom() == maNhom && tv.getnhomNhanVien() == maNV)
                .findFirst();
    }

    public List<ThanhVienNhom> getByNhom(int maNhom) {
        return DatabaseMock.dsThanhVienNhom.stream()
                .filter(tv -> tv.getmaNhom() != null && tv.getmaNhom() == maNhom)
                .collect(Collectors.toList());
    }

    public List<ThanhVienNhom> getByNhanVien(int maNV) {
        return DatabaseMock.dsThanhVienNhom.stream()
                .filter(tv -> tv.getnhomNhanVien() == maNV)
                .collect(Collectors.toList());
    }

    @Override
    public void add(ThanhVienNhom entity) {
        DatabaseMock.dsThanhVienNhom.add(entity);
    }

    @Override
    public void update(ThanhVienNhom entity) {
        for (int i = 0; i < DatabaseMock.dsThanhVienNhom.size(); i++) {
            ThanhVienNhom tv = DatabaseMock.dsThanhVienNhom.get(i);
            if (tv.getmaNhom() != null && tv.getmaNhom().equals(entity.getmaNhom()) && tv.getnhomNhanVien() == entity.getnhomNhanVien()) {
                DatabaseMock.dsThanhVienNhom.set(i, entity);
                return;
            }
        }
    }

    @Override
    public boolean delete(String compositeKey) {
        String[] parts = compositeKey.split("_");
        if (parts.length != 2) return false;
        int maNhom = Integer.parseInt(parts[0]);
        int maNV = Integer.parseInt(parts[1]);
        return DatabaseMock.dsThanhVienNhom.removeIf(tv -> tv.getmaNhom() != null && tv.getmaNhom() == maNhom && tv.getnhomNhanVien() == maNV);
    }
}
