package com.hrmis;

import java.time.LocalDate;

import com.hrmis.services.NhanSuService;
import com.hrmis.services.TaiChinhService;
import com.hrmis.services.VanHanhService;

public class Main {
    public static void main(String[] args) {
        // DUONG
        // 1. Khởi tạo Service Vận hành
        VanHanhService vanHanhService = new VanHanhService();

        // 2. Chạy thử Câu 1 với mã hợp đồng số 3 (hoặc số 1, 2)
        System.out.println(">>> ĐANG CHẠY THỬ CÂU 1:");
        vanHanhService.inHopDongChiTiet(3);
        System.out.println("\n>>> ĐANG CHẠY THỬ CÂU 2:");
        vanHanhService.canhBaoHopDongSapHetHan(LocalDate.now());
        System.out.println("\n>>> ĐANG CHẠY THỬ CÂU 3:");
        vanHanhService.inBangChamCongCaNhan(2, 3, 2024);
        System.out.println("\n>>> ĐANG CHẠY THỬ CÂU 4:");
        vanHanhService.baocaoDiTre(3, 2024);
        System.out.println("\n>>> ĐANG CHẠY THỬ CÂU 5:");
        vanHanhService.baocaoOT(3, 2024);
        System.out.println("\n>>> ĐANG CHẠY THỬ CÂU 6:");
        vanHanhService.theoDoiDonNghiPhep();
        System.out.println("\n>>> ĐANG CHẠY THỬ CÂU 7:");
        vanHanhService.lsNghi(2);
        System.out.println("\n>>> ĐANG CHẠY THỬ CÂU 8:");
        vanHanhService.thongkeChuyenCan(3, 2024);

        /*
         * // LINH
         * NhanSuService nhanSuService = new NhanSuService();
         * nhanSuService.DanhSachNhanVien();
         * nhanSuService.danhSachPhongBan();
         * nhanSuService.traCuuNhanVienTheoPhongBan("Phong CNTT");
         * nhanSuService.top3NhanVienThamNien();
         * nhanSuService.danhSachCapDuoiCuaQuanLy(1);
         * nhanSuService.chiTietNhomNhanVien(1);
         * nhanSuService.thongKeNhanVienDaNhiemNhieuNhom();
         * nhanSuService.thongKeNhanSuTheoChucVu();
         * nhanSuService.danhSachNhanVienChuaVaoNhom();
         * nhanSuService.timNhanSuTheoTuKhoa("linh");
         * 
         * // HAO
         * TaiChinhService taiChinhService = new TaiChinhService();
         * taiChinhService.inPhieuLuong(2, 3, 2024); // Câu 1
         * taiChinhService.bangTongHopLuongThang(3, 2024); // Câu 2
         * taiChinhService.quyLuongTheoPhongBan(3, 2024); // Câu 3
         * taiChinhService.topThuNhapCaoNhat(3, 2024); // Câu 4
         * taiChinhService.thongKeBaoHiem(3, 2024); // Câu 5
         * taiChinhService.baoCaoThuongPhuCap(3, 2024); // Câu 6
         * taiChinhService.inPhieuKPI(1); // Câu 7
         * taiChinhService.xepHangHieuSuat("Quy 1", 2024); // Câu 8
         * taiChinhService.timNhanSuXuatSac(1, 2024); // Câu 9
         * taiChinhService.thuNhapTrungBinhTheoChucVu(); // Câu 10
         */
    }
}
