package com.hrmis.services;

import com.hrmis.data.DatabaseMock;
import com.hrmis.models.ChucVu;
import com.hrmis.models.NhanVien;
import com.hrmis.models.NhomNhanVien;
import com.hrmis.models.PhongBan;
import com.hrmis.models.ThanhVienNhom;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NhanSuService {
    private String getTenPhongBan(int maPhongBan) {
        return DatabaseMock.dsPhongBan.stream()
                .filter(pb -> pb.getmaPhongBan() != null && pb.getmaPhongBan().equals(maPhongBan))
                .map(PhongBan::gettenPhongBan)
                .findFirst()
                .orElse("Chua phan bo");
    }

    private String getTenChucVu(int maChucVu) {
        return DatabaseMock.dsChucVu.stream()
                .filter(cv -> cv.getmaChucVu() != null && cv.getmaChucVu().equals(maChucVu))
                .map(ChucVu::gettenChucVu)
                .findFirst()
                .orElse("Chua co");
    }

    private String getTenNhanVien(int maNhanVien) {
        return DatabaseMock.dsNhanVien.stream()
                .filter(nv -> nv.getMaNhanVien() == maNhanVien)
                .map(NhanVien::HoTenNV)
                .findFirst()
                .orElse("Khong xac dinh");
    }

    private String getTenNhom(int maNhom) {
        return DatabaseMock.dsNhomNhanVien.stream()
                .filter(nh -> nh.getmaNhom() != null && nh.getmaNhom().equals(maNhom))
                .map(NhomNhanVien::gettenNhom)
                .findFirst()
                .orElse("Khong xac dinh");
    }

    //=============================================
    //Câu 1
    //=============================================
    public void DanhSachNhanVien() {
        String formatHeader = "%-8s %-22s %-12s %-22s %-28s %-18s%n";
        System.out.printf(formatHeader, "Ma NV", "Ho va Ten", "Gioi Tinh", "Phong Ban", "Chuc Vu", "Nguoi Quan Ly");
        System.out.println("-".repeat(110));

        DatabaseMock.dsNhanVien.forEach(nv -> {
            String tenPhongBan = getTenPhongBan(nv.getMaPhongBan());
            String tenChucVu = getTenChucVu(nv.getMaChucVu());
            String tenQuanLy = "Cap cao nhat";
            if (nv.getMaQuanLy() != null) {
                tenQuanLy = getTenNhanVien(nv.getMaQuanLy());
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

    //=============================================
    //Câu 2: Cơ cấu tổ chức phòng ban
    //=============================================
    public void danhSachPhongBan() {
        System.out.println("\n=== Cơ cấu tổ chức phòng ban ===");
        System.out.printf("%-20s %-25s %-10s%n", "Phong ban", "Truong phong", "So NV");
        System.out.println("-".repeat(60));

        DatabaseMock.dsPhongBan.forEach(pb -> {
            String truongPhong = DatabaseMock.dsNhanVien.stream()
                    .filter(nv -> nv.getMaNhanVien() == pb.getmaTruongPhong())
                    .map(NhanVien::HoTenNV)
                    .findFirst()
                    .orElse("Chua co");

            long soNhanVien = DatabaseMock.dsNhanVien.stream()
                    .filter(nv -> nv.getMaPhongBan() == pb.getmaPhongBan())
                    .count();

            System.out.printf("%-20s %-25s %-10d%n",
                    pb.gettenPhongBan(),
                    truongPhong,
                    soNhanVien);
        });
    }

    //=============================================
    //Câu 3: Tra cứu nhân viên theo phòng ban
    //=============================================
    public void traCuuNhanVienTheoPhongBan(String maHoacTenPhongBan) {
        if (maHoacTenPhongBan == null || maHoacTenPhongBan.trim().isEmpty()) {
            System.out.println("Nhap ma hoac ten phong ban");
            return;
        }

        String keyword = maHoacTenPhongBan.trim().toLowerCase();
        List<NhanVien> dsKetQua = DatabaseMock.dsNhanVien.stream()
                .filter(nv -> String.valueOf(nv.getMaPhongBan()).equalsIgnoreCase(keyword)
                        || getTenPhongBan(nv.getMaPhongBan()).toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        System.out.println("\n=== Danh sach nhan vien thuoc phong ban ===");
        if (dsKetQua.isEmpty()) {
            System.out.println("Khong tim thay phong ban nao phu hop.");
            return;
        }

        dsKetQua.forEach(nv -> {
            System.out.printf("- %s | %s | %s | %s%n",
                    nv.getMaNhanVien(),
                    nv.HoTenNV(),
                    getTenChucVu(nv.getMaChucVu()),
                    getTenPhongBan(nv.getMaPhongBan()));
        });
    }

    //=============================================
    //Câu 4: Top 3 nhân viên thâm niên
    //=============================================
    public void top3NhanVienThamNien() {
        System.out.println("\n=== Top 3 nhan vien co ngay vao lam som nhat ===");

        DatabaseMock.dsNhanVien.stream()
                .sorted(Comparator.comparing(NhanVien::getNgayVaoLam))
                .limit(3)
                .forEach(nv -> System.out.printf("- %s | %s | %s%n",
                        nv.getMaNhanVien(),
                        nv.HoTenNV(),
                        nv.getNgayVaoLam()));
    }

    //=============================================
    //Câu 5: Cây phân cấp quản lý
    //=============================================
    public void danhSachCapDuoiCuaQuanLy(int maNhanVienQuanLy) {
        System.out.println("\n=== Danh sach cap duoi cua quan ly " + maNhanVienQuanLy + " ===");

        List<NhanVien> dsCapDuoi = DatabaseMock.dsNhanVien.stream()
                .filter(nv -> nv.getMaQuanLy() != null && nv.getMaQuanLy() == maNhanVienQuanLy)
                .collect(Collectors.toList());

        if (dsCapDuoi.isEmpty()) {
            System.out.println("Khong co cap duoi nao.");
            return;
        }

        dsCapDuoi.forEach(nv -> System.out.printf("- %s | %s | %s%n",
                nv.getMaNhanVien(),
                nv.HoTenNV(),
                getTenChucVu(nv.getMaChucVu())));
    }

    //=============================================
    //Câu 6: Chi tiết một nhóm làm việc
    //=============================================
    public void chiTietNhomNhanVien(int maNhom) {
        System.out.println("\n=== Chi tiet nhom lam viec ===");

        NhomNhanVien nhom = DatabaseMock.dsNhomNhanVien.stream()
                .filter(nh -> nh.getmaNhom() != null && nh.getmaNhom().equals(maNhom))
                .findFirst()
                .orElse(null);

        if (nhom == null) {
            System.out.println("Khong tim thay nhom.");
            return;
        }

        String truongNhom = getTenNhanVien(nhom.getmaTruongNhom());
        System.out.println("Ma nhom: " + nhom.getmaNhom());
        System.out.println("Ten nhom: " + nhom.gettenNhom());
        System.out.println("Truong nhom: " + truongNhom);
        System.out.println("Ngay thanh lap: " + nhom.getngayThanhLap());
        System.out.println("Mo ta: " + nhom.toString());

        System.out.println("\nDanh sach thanh vien:");
        DatabaseMock.dsThanhVienNhom.stream()
                .filter(tv -> tv.getmaNhom() != null && tv.getmaNhom().equals(maNhom))
                .forEach(tv -> {
                    NhanVien nv = DatabaseMock.dsNhanVien.stream()
                            .filter(n -> n.getMaNhanVien() == tv.getnhomNhanVien())
                            .findFirst()
                            .orElse(null);

                    if (nv != null) {
                        System.out.printf("- %s | %s | %s%n",
                                nv.getMaNhanVien(),
                                nv.HoTenNV(),
                                tv.getVaiTro());
                    }
                });
    }

    //=============================================
    //Câu 7: Thống kê nhân sự đa nhiệm
    //=============================================
    public void thongKeNhanVienDaNhiemNhieuNhom() {
        System.out.println("\n=== Danh sach nhan vien tham gia tu 2 nhom tro len ===");

        Map<Integer, List<ThanhVienNhom>> dsTheoNhanVien = DatabaseMock.dsThanhVienNhom.stream()
                .collect(Collectors.groupingBy(ThanhVienNhom::getnhomNhanVien));

        dsTheoNhanVien.entrySet().stream()
                .filter(entry -> entry.getValue().size() >= 2)
                .forEach(entry -> {
                    NhanVien nv = DatabaseMock.dsNhanVien.stream()
                            .filter(n -> n.getMaNhanVien() == entry.getKey())
                            .findFirst()
                            .orElse(null);

                    if (nv != null) {
                        String dsNhom = entry.getValue().stream()
                                .map(tv -> getTenNhom(tv.getmaNhom()))
                                .collect(Collectors.joining(", "));

                        System.out.printf("- %s | %s | %s%n",
                                nv.getMaNhanVien(),
                                nv.HoTenNV(),
                                dsNhom);
                    }
                });
    }

    //=============================================
    //Câu 8: Phân bổ nhân sự theo chức danh
    //=============================================
    public void thongKeNhanSuTheoChucVu() {
        System.out.println("\n=== Thong ke nhan su theo chuc vu ===");

        DatabaseMock.dsChucVu.forEach(cv -> {
            long soLuong = DatabaseMock.dsNhanVien.stream()
                    .filter(nv -> nv.getMaChucVu() == cv.getmaChucVu())
                    .count();

            if (soLuong > 0) {
                System.out.printf("- %s: %d%n", cv.gettenChucVu(), soLuong);
            }
        });
    }

    //=============================================
    //Câu 9: Nhân viên chưa vào nhóm
    //=============================================
    public void danhSachNhanVienChuaVaoNhom() {
        System.out.println("\n=== Danh sach nhan vien chua tham gia nhom nao ===");

        List<NhanVien> dsChuaVaoNhom = DatabaseMock.dsNhanVien.stream()
                .filter(nv -> DatabaseMock.dsThanhVienNhom.stream()
                        .noneMatch(tv -> tv.getnhomNhanVien() == nv.getMaNhanVien()))
                .collect(Collectors.toList());

        if (dsChuaVaoNhom.isEmpty()) {
            System.out.println("Tat ca nhan vien da tham gia nhom.");
            return;
        }

        dsChuaVaoNhom.forEach(nv -> System.out.printf("- %s | %s | %s%n",
                nv.getMaNhanVien(),
                nv.HoTenNV(),
                getTenPhongBan(nv.getMaPhongBan())));
    }

    //=============================================
    //Câu 10: Tìm kiếm nhân sự đa tiêu chí
    //=============================================
    public void timNhanSuTheoTuKhoa(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Nhap tu khoa tim kiem.");
            return;
        }

        String key = keyword.trim().toLowerCase();
        List<NhanVien> dsKetQua = DatabaseMock.dsNhanVien.stream()
                .filter(nv -> nv.HoTenNV().toLowerCase().contains(key)
                        || nv.getSoDienThoai().toLowerCase().contains(key)
                        || nv.getEmail().toLowerCase().contains(key))
                .collect(Collectors.toList());

        System.out.println("\n=== Ket qua tim kiem nhan su ===");
        if (dsKetQua.isEmpty()) {
            System.out.println("Khong tim thay ket qua nao.");
            return;
        }

        dsKetQua.forEach(nv -> System.out.printf("- %s | %s | %s | %s | %s%n",
                nv.getMaNhanVien(),
                nv.HoTenNV(),
                nv.getSoDienThoai(),
                nv.getEmail(),
                getTenPhongBan(nv.getMaPhongBan())));
    }
}
