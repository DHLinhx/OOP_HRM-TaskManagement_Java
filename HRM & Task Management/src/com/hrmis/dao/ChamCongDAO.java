package com.hrmis.dao;

import com.hrmis.data.DatabaseMock;
import com.hrmis.enums.TrangThaiCong;
import com.hrmis.models.ChamCong;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChamCongDAO implements IDAO<ChamCong, Integer> {

    @Override
    public List<ChamCong> getAll() {
        return DatabaseMock.dsChamCong;
    }

    @Override
    public Optional<ChamCong> getById(Integer id) {
        return DatabaseMock.dsChamCong.stream()
                .filter(cc -> cc.getMaChamCong() == id)
                .findFirst();
    }

    public List<ChamCong> getByNhanVienVaThang(int maNV, int thang, int nam) {
        return DatabaseMock.dsChamCong.stream()
                .filter(cc -> cc.getMaNhanVien() == maNV
                        && cc.getNgayLamViec().getMonthValue() == thang
                        && cc.getNgayLamViec().getYear() == nam)
                .collect(Collectors.toList());
    }

    public long countDiTre(int maNV, int thang, int nam) {
        return DatabaseMock.dsChamCong.stream()
                .filter(cc -> cc.getMaNhanVien() == maNV
                        && cc.getNgayLamViec().getMonthValue() == thang
                        && cc.getNgayLamViec().getYear() == nam
                        && cc.getTrangThaiCong() == TrangThaiCong.DI_TRE)
                .count();
    }

    public double sumGioOT(int maNV, int thang, int nam) {
        return DatabaseMock.dsChamCong.stream()
                .filter(cc -> cc.getMaNhanVien() == maNV
                        && cc.getNgayLamViec().getMonthValue() == thang
                        && cc.getNgayLamViec().getYear() == nam)
                .mapToDouble(ChamCong::getSoGioLamThem)
                .sum();
    }

    public boolean isChuyenCan100PhanTram(int maNV, int thang, int nam) {
        long tong = DatabaseMock.dsChamCong.stream()
                .filter(cc -> cc.getMaNhanVien() == maNV
                        && cc.getNgayLamViec().getMonthValue() == thang
                        && cc.getNgayLamViec().getYear() == nam)
                .count();
        long dungGio = DatabaseMock.dsChamCong.stream()
                .filter(cc -> cc.getMaNhanVien() == maNV
                        && cc.getNgayLamViec().getMonthValue() == thang
                        && cc.getNgayLamViec().getYear() == nam
                        && cc.getTrangThaiCong() == TrangThaiCong.DUNG_GIO)
                .count();
        return tong > 0 && tong == dungGio;
    }

    @Override
    public void add(ChamCong entity) {
        DatabaseMock.dsChamCong.add(entity);
    }

    @Override
    public void update(ChamCong entity) {
        for (int i = 0; i < DatabaseMock.dsChamCong.size(); i++) {
            if (DatabaseMock.dsChamCong.get(i).getMaChamCong() == entity.getMaChamCong()) {
                DatabaseMock.dsChamCong.set(i, entity);
                return;
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        return DatabaseMock.dsChamCong.removeIf(cc -> cc.getMaChamCong() == id);
    }
}
