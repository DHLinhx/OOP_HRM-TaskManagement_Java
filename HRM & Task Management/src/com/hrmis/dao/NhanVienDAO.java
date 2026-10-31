package com.hrmis.dao;

import com.hrmis.data.DatabaseMock;
import com.hrmis.models.NhanVien;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NhanVienDAO implements IDAO<NhanVien, Integer> {

    @Override
    public List<NhanVien> getAll() {
        return DatabaseMock.dsNhanVien;
    }

    @Override
    public Optional<NhanVien> getById(Integer id) {
        return DatabaseMock.dsNhanVien.stream()
                .filter(nv -> nv.getMaNhanVien() == id)
                .findFirst();
    }

    public List<NhanVien> getByPhongBan(int maPB) {
        return DatabaseMock.dsNhanVien.stream()
                .filter(nv -> nv.getMaPhongBan() == maPB)
                .collect(Collectors.toList());
    }

    public List<NhanVien> getByChucVu(int maCV) {
        return DatabaseMock.dsNhanVien.stream()
                .filter(nv -> nv.getMaChucVu() == maCV)
                .collect(Collectors.toList());
    }

    public List<NhanVien> getCapDuoi(int maQL) {
        return DatabaseMock.dsNhanVien.stream()
                .filter(nv -> nv.getMaQuanLy() != null && nv.getMaQuanLy() == maQL)
                .collect(Collectors.toList());
    }

    public List<NhanVien> getTopThamNien(int limit) {
        return DatabaseMock.dsNhanVien.stream()
                .sorted(Comparator.comparing(NhanVien::getNgayVaoLam))
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<NhanVien> search(String keyword) {
        String kw = keyword.toLowerCase().trim();
        return DatabaseMock.dsNhanVien.stream()
                .filter(nv -> nv.HoTenNV().toLowerCase().contains(kw)
                        || nv.getSoDienThoai().contains(kw)
                        || nv.getEmail().toLowerCase().contains(kw))
                .collect(Collectors.toList());
    }

    @Override
    public void add(NhanVien entity) {
        DatabaseMock.dsNhanVien.add(entity);
    }

    @Override
    public void update(NhanVien entity) {
        for (int i = 0; i < DatabaseMock.dsNhanVien.size(); i++) {
            if (DatabaseMock.dsNhanVien.get(i).getMaNhanVien() == entity.getMaNhanVien()) {
                DatabaseMock.dsNhanVien.set(i, entity);
                return;
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        return DatabaseMock.dsNhanVien.removeIf(nv -> nv.getMaNhanVien() == id);
    }
}
