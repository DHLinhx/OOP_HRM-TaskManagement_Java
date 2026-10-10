package com.hrmis.services;

import com.hrmis.data.DatabaseMock;
import com.hrmis.models.ChucVu;
import com.hrmis.models.NhanVien;
import com.hrmis.models.PhongBan;

public class NhanSuService {
    public void DanhSachNhanVien() {
        String formatHeader = "%-8s %-22s %-12s %-22s %-28s %-18s%n";
        System.out.printf(formatHeader, "Ma NV", "Ho va Ten", "Gioi Tinh", "Phong Ban", "Chuc Vu", "Nguoi Quan Ly");
        System.out.println("-".repeat(110));

        DatabaseMock.dsNhanVien.forEach(nv -> {
            String tenPhongBan = DatabaseMock.dsPhongBan.stream()
                    .filter(pb -> pb.getmaPhongBan() != null && pb.getmaPhongBan().equals(nv.getMaPhongBan()))
                    .map(PhongBan::gettenPhongBan)
                    .findFirst()
                    .orElse("Chua phan bo");

            String tenChucVu = DatabaseMock.dsChucVu.stream()
                    .filter(cv -> cv.getmaChucVu() != null && cv.getmaChucVu().equals(nv.getMaChucVu()))
                    .map(ChucVu::gettenChucVu)
                    .findFirst()
                    .orElse("Chua co");

            String tenQuanLy = "Cap cao nhat";
            if (nv.getMaQuanLy() != null) {
                tenQuanLy = DatabaseMock.dsNhanVien.stream()
                        .filter(ql -> ql.getMaNhanVien() == nv.getMaQuanLy())
                        .map(NhanVien::HoTenNV)
                        .findFirst()
                        .orElse("khong xac dinh");
            }

            System.out.printf(formatHeader,
                    nv.getMaNhanVien(),
                    nv.HoTenNV(),
                    nv.getGioiTinh(),
                    tenPhongBan,
                    tenChucVu,
                    tenQuanLy);
        });
    }
}
