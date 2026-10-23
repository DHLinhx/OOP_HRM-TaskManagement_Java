package com.hrmis.dao;

import com.hrmis.data.DatabaseMock;
import com.hrmis.enums.LoaiHopDong;
import com.hrmis.models.HopDong;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HopDongDAO implements IDAO<HopDong, Integer> {

    @Override
    public List<HopDong> getAll() {
        return DatabaseMock.dsHopDong;
    }

    @Override
    public Optional<HopDong> getById(Integer id) {
        return DatabaseMock.dsHopDong.stream()
                .filter(hd -> hd.getMaHopDong() == id)
                .findFirst();
    }

    public Optional<HopDong> getByNhanVien(int maNV) {
        return DatabaseMock.dsHopDong.stream()
                .filter(hd -> hd.getMaNhanVien() == maNV)
                .findFirst();
    }

    public List<HopDong> getSapHetHan(LocalDate mocThoiGian, int soNgay) {
        LocalDate mocGioiHan = mocThoiGian.plusDays(soNgay);
        return DatabaseMock.dsHopDong.stream()
                .filter(hd -> hd.getNgayHetHan() != null
                        && !hd.getNgayHetHan().isBefore(mocThoiGian)
                        && !hd.getNgayHetHan().isAfter(mocGioiHan))
                .collect(Collectors.toList());
    }

    public List<HopDong> getByLoai(LoaiHopDong loai) {
        return DatabaseMock.dsHopDong.stream()
                .filter(hd -> hd.getLoaiHopDong() == loai)
                .collect(Collectors.toList());
    }

    @Override
    public void add(HopDong entity) {
        DatabaseMock.dsHopDong.add(entity);
    }

    @Override
    public void update(HopDong entity) {
        for (int i = 0; i < DatabaseMock.dsHopDong.size(); i++) {
            if (DatabaseMock.dsHopDong.get(i).getMaHopDong() == entity.getMaHopDong()) {
                DatabaseMock.dsHopDong.set(i, entity);
                return;
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        return DatabaseMock.dsHopDong.removeIf(hd -> hd.getMaHopDong() == id);
    }
}
