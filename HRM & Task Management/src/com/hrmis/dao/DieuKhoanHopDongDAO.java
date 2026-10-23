package com.hrmis.dao;

import com.hrmis.data.DatabaseMock;
import com.hrmis.models.DieuKhoanHopDong;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DieuKhoanHopDongDAO implements IDAO<DieuKhoanHopDong, Integer> {

    @Override
    public List<DieuKhoanHopDong> getAll() {
        return DatabaseMock.dsDieuKhoanHopDong;
    }

    @Override
    public Optional<DieuKhoanHopDong> getById(Integer id) {
        return DatabaseMock.dsDieuKhoanHopDong.stream()
                .filter(dk -> dk.getMaDieuKhoan() == id)
                .findFirst();
    }

    public List<DieuKhoanHopDong> getByHopDong(int maHopDong) {
        return DatabaseMock.dsDieuKhoanHopDong.stream()
                .filter(dk -> dk.getMaHopDong() == maHopDong)
                .collect(Collectors.toList());
    }

    public List<DieuKhoanHopDong> getByLoaiDieuKhoan(String... loais) {
        return DatabaseMock.dsDieuKhoanHopDong.stream()
                .filter(dk -> {
                    if (dk.getLoaiDieuKhoan() == null) return false;
                    for (String loai : loais) {
                        if (dk.getLoaiDieuKhoan().equalsIgnoreCase(loai)) return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void add(DieuKhoanHopDong entity) {
        DatabaseMock.dsDieuKhoanHopDong.add(entity);
    }

    @Override
    public void update(DieuKhoanHopDong entity) {
        for (int i = 0; i < DatabaseMock.dsDieuKhoanHopDong.size(); i++) {
            if (DatabaseMock.dsDieuKhoanHopDong.get(i).getMaDieuKhoan() == entity.getMaDieuKhoan()) {
                DatabaseMock.dsDieuKhoanHopDong.set(i, entity);
                return;
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        return DatabaseMock.dsDieuKhoanHopDong.removeIf(dk -> dk.getMaDieuKhoan() == id);
    }
}
