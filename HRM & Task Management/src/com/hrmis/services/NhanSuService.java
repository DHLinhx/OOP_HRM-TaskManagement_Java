package com.hrmis.services;

import com.hrmis.dao.*;
import com.hrmis.models.NhanVien;
import com.hrmis.models.NhomNhanVien;
import com.hrmis.models.ThanhVienNhom;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NhanSuService {

    // Khai báo các đối tượng DAO theo mô hình phân tầng
    private final NhanVienDAO nhanVienDAO = new NhanVienDAO();
    private final PhongBanDAO phongBanDAO = new PhongBanDAO();
    private final ChucVuDAO chucVuDAO = new ChucVuDAO();
    private final NhomNhanVienDAO nhomNhanVienDAO = new NhomNhanVienDAO();
    private final ThanhVienNhomDAO thanhVienNhomDAO = new ThanhVienNhomDAO();

    private String getTenPhongBan(int maPhongBan) {
        return phongBanDAO.getTenPhongBan(maPhongBan);
    }

    private String getTenChucVu(int maChucVu) {
        return chucVuDAO.getTenChucVu(maChucVu);
    }

    private String getTenNhanVien(int maNhanVien) {
        return nhanVienDAO.getById(maNhanVien)
                .map(NhanVien::HoTenNV)
                .orElse("Không xác định");
    }

    private String getTenNhom(int maNhom) {
        return nhomNhanVienDAO.getTenNhom(maNhom);
    }

    //=============================================
    // Câu 11: Danh sách toàn bộ nhân viên
    //=============================================
    public void DanhSachNhanVien() {
        String formatHeader = "%-8s %-22s %-12s %-22s %-28s %-18s%n";
        System.out.printf(formatHeader, "Ma NV", "Ho va Ten", "Gioi Tinh", "Phong Ban", "Chuc Vu", "Nguoi Quan Ly");
        System.out.println("-".repeat(110));

        nhanVienDAO.getAll().forEach(nv -> {
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
    // Câu 12: Cơ cấu tổ chức phòng ban
    //=============================================
    public void danhSachPhongBan() {
        System.out.println("\n=== Cơ cấu tổ chức phòng ban ===");
        System.out.printf("%-20s %-25s %-10s%n", "Phong ban", "Truong phong", "So NV");
        System.out.println("-".repeat(60));

        phongBanDAO.getAll().forEach(pb -> {
            String truongPhong = pb.getmaTruongPhong() != null
                    ? getTenNhanVien(pb.getmaTruongPhong())
                    : "Chua co";

            long soNhanVien = nhanVienDAO.getByPhongBan(pb.getmaPhongBan()).size();

            System.out.printf("%-20s %-25s %-10d%n",
                    pb.gettenPhongBan(),
                    truongPhong,
                    soNhanVien);
        });
    }

    //=============================================
    // Câu 13: Tra cứu nhân viên theo phòng ban
    //=============================================
    public void traCuuNhanVienTheoPhongBan(String maHoacTenPhongBan) {
        if (maHoacTenPhongBan == null || maHoacTenPhongBan.trim().isEmpty()) {
            System.out.println("Nhap ma hoac ten phong ban");
            return;
        }

        String keyword = maHoacTenPhongBan.trim().toLowerCase();
        List<NhanVien> dsKetQua = nhanVienDAO.getAll().stream()
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
    // Câu 14: Top 3 nhân viên thâm niên
    //=============================================
    public void top3NhanVienThamNien() {
        System.out.println("\n=== Top 3 nhan vien co ngay vao lam som nhat ===");

        nhanVienDAO.getTopThamNien(3).forEach(nv -> System.out.printf("- %s | %s | %s%n",
                nv.getMaNhanVien(),
                nv.HoTenNV(),
                nv.getNgayVaoLam()));
    }

    //=============================================
    // Câu 15: Cây phân cấp quản lý
    //=============================================
    public void danhSachCapDuoiCuaQuanLy(int maNhanVienQuanLy) {
        System.out.println("\n=== Danh sach cap duoi cua quan ly " + maNhanVienQuanLy + " ===");

        List<NhanVien> dsCapDuoi = nhanVienDAO.getCapDuoi(maNhanVienQuanLy);

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
    // Câu 16: Chi tiết một nhóm làm việc
    //=============================================
    public void chiTietNhomNhanVien(int maNhom) {
        System.out.println("\n=== Chi tiet nhom lam viec ===");

        NhomNhanVien nhom = nhomNhanVienDAO.getById(maNhom).orElse(null);

        if (nhom == null) {
            System.out.println("Khong tim thay nhom.");
            return;
        }

        String truongNhom = nhom.getmaTruongNhom() != null ? getTenNhanVien(nhom.getmaTruongNhom()) : "Chưa có";
        System.out.println("Ma nhom: " + nhom.getmaNhom());
        System.out.println("Ten nhom: " + nhom.gettenNhom());
        System.out.println("Truong nhom: " + truongNhom);
        System.out.println("Ngay thanh lap: " + nhom.getngayThanhLap());
        System.out.println("Mo ta: " + nhom.toString());

        System.out.println("\nDanh sach thanh vien:");
        thanhVienNhomDAO.getByNhom(maNhom).forEach(tv -> {
            NhanVien nv = nhanVienDAO.getById(tv.getnhomNhanVien()).orElse(null);
            if (nv != null) {
                System.out.printf("- %s | %s | %s%n",
                        nv.getMaNhanVien(),
                        nv.HoTenNV(),
                        tv.getVaiTro());
            }
        });
    }

    //=============================================
    // Câu 17: Thống kê nhân sự đa nhiệm
    //=============================================
    public void thongKeNhanVienDaNhiemNhieuNhom() {
        System.out.println("\n=== Danh sach nhan vien tham gia tu 2 nhom tro len ===");

        Map<Integer, List<ThanhVienNhom>> dsTheoNhanVien = thanhVienNhomDAO.getAll().stream()
                .collect(Collectors.groupingBy(ThanhVienNhom::getnhomNhanVien));

        dsTheoNhanVien.entrySet().stream()
                .filter(entry -> entry.getValue().size() >= 2)
                .forEach(entry -> {
                    NhanVien nv = nhanVienDAO.getById(entry.getKey()).orElse(null);

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
    // Câu 18: Phân bổ nhân sự theo chức danh
    //=============================================
    public void thongKeNhanSuTheoChucVu() {
        System.out.println("\n=== Thong ke nhan su theo chuc vu ===");

        chucVuDAO.getAll().forEach(cv -> {
            long soLuong = nhanVienDAO.getByChucVu(cv.getmaChucVu()).size();
            if (soLuong > 0) {
                System.out.printf("- %s: %d%n", cv.gettenChucVu(), soLuong);
            }
        });
    }

    //=============================================
    // Câu 19: Nhân viên chưa vào nhóm
    //=============================================
    public void danhSachNhanVienChuaVaoNhom() {
        System.out.println("\n=== Danh sach nhan vien chua tham gia nhom nao ===");

        List<NhanVien> dsChuaVaoNhom = nhanVienDAO.getAll().stream()
                .filter(nv -> thanhVienNhomDAO.getByNhanVien(nv.getMaNhanVien()).isEmpty())
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
    // Câu 20: Tìm kiếm nhân sự đa tiêu chí
    //=============================================
    public void timNhanSuTheoTuKhoa(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Nhap tu khoa tim kiem.");
            return;
        }

        List<NhanVien> dsKetQua = nhanVienDAO.search(keyword);

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
