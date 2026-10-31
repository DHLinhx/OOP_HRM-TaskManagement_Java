package com.hrmis;

import java.time.LocalDate;
import java.util.Scanner;

import com.hrmis.services.NhanSuService;
import com.hrmis.services.TaiChinhService;
import com.hrmis.services.VanHanhService;

public class Main {
    private static final VanHanhService VAN_HANH_SERVICE = new VanHanhService();
    private static final NhanSuService NHAN_SU_SERVICE = new NhanSuService();
    private static final TaiChinhService TAI_CHINH_SERVICE = new TaiChinhService();

    private static void printMenu() {
        System.out.println("\n" + "=".repeat(110));
        System.out.println("                 HỆ THỐNG THÔNG TIN QUẢN TRỊ NHÂN SỰ & CÔNG VIỆC (HRMIS)");
        System.out.println("=".repeat(110));
        System.out.printf(" %-36s %-36s %-36s%n",
                "[ PHÂN HỆ 1: LĨNH ]", "[ PHÂN HỆ 2: DƯƠNG ]", "[ PHÂN HỆ 3: HẠO ]");
        System.out.println("-".repeat(110));
        System.out.printf(" %-36s %-36s %-36s%n", " 1. Danh sách toàn bộ nhân sự", "11. In chi tiết hợp đồng", "21. In phiếu lương (Payslip)");
        System.out.printf(" %-36s %-36s %-36s%n", " 2. Cơ cấu tổ chức phòng ban", "12. Cảnh báo HĐ sắp hết hạn", "22. Bảng tổng hợp lương tháng");
        System.out.printf(" %-36s %-36s %-36s%n", " 3. Tra cứu theo phòng ban (*)", "13. Bảng chấm công cá nhân", "23. Quỹ lương theo phòng ban");
        System.out.printf(" %-36s %-36s %-36s%n", " 4. Top 3 nhân viên thâm niên", "14. Báo cáo xếp hạng đi trễ", "24. Top 3 thực lãnh cao nhất");
        System.out.printf(" %-36s %-36s %-36s%n", " 5. Cây phân cấp quản lý", "15. Báo cáo làm thêm giờ (OT)", "25. Thống kê bảo hiểm bắt buộc");
        System.out.printf(" %-36s %-36s %-36s%n", " 6. Chi tiết nhóm làm việc", "16. Theo dõi đơn nghỉ phép", "26. Báo cáo thưởng & phụ cấp");
        System.out.printf(" %-36s %-36s %-36s%n", " 7. Thống kê nhân sự đa nhiệm", "17. Lịch sử xin nghỉ phép", "27. In phiếu đánh giá KPI");
        System.out.printf(" %-36s %-36s %-36s%n", " 8. Phân bổ theo chức vụ", "18. Thống kê chuyên cần 100%", "28. Xếp hạng hiệu suất");
        System.out.printf(" %-36s %-36s %-36s%n", " 9. NV chưa tham gia nhóm", "19. Thống kê loại hợp đồng", "29. Tìm nhân sự xuất sắc");
        System.out.printf(" %-36s %-36s %-36s%n", "10. Tìm kiếm nhân sự (*)", "20. Lọc điều khoản pháp lý", "30. Lương TB theo chức vụ");
        System.out.println("-".repeat(110));
        System.out.printf(" %-36s %-36s %-36s%n",
                "[91] Demo 10 câu của LĨNH", "[92] Demo 10 câu của DƯƠNG", "[93] Demo 10 câu của HẠO");
        System.out.println("  [0] Thoát chương trình         | (*) Có nhập từ bàn phím");
        System.out.println("=".repeat(110));
    }

    private static void pause(Scanner scanner) {
        System.out.print("\n>>> Nhấn phím [ENTER] để quay lại menu...");
        scanner.nextLine();
    }

    private static int promptInt(Scanner scanner, String message, int defaultValue) {
        System.out.printf(" %s [Mặc định: %d]: ", message, defaultValue);
        String line = scanner.nextLine().trim();
        if (line.isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.printf("   (Nhập không hợp lệ, tự động áp dụng mặc định: %d)%n", defaultValue);
            return defaultValue;
        }
    }

    private static String promptString(Scanner scanner, String message, String defaultValue) {
        System.out.printf(" %s [Mặc định: \"%s\"]: ", message, defaultValue);
        String line = scanner.nextLine().trim();
        return line.isEmpty() ? defaultValue : line;
    }

    private static void executeChoice(int choice, Scanner scanner) {
        System.out.println("\n=== === CÂU " + choice + "=== ===");

        switch (choice) {
            // LĨNH (Câu 1 -> 10: Quản lý nhân sự, tổ chức phòng ban)
            case 1:
                NHAN_SU_SERVICE.DanhSachNhanVien();
                break;
            case 2:
                NHAN_SU_SERVICE.danhSachPhongBan();
                break;
            case 3:
                System.out.println("💡 Gợi ý phòng ban: [1] Phong CNTT | [2] Phong Kinh doanh | [3] Phong Nhan su");
                String phongBan = promptString(scanner, "Nhập mã hoặc tên phòng ban", "Phong CNTT");
                NHAN_SU_SERVICE.traCuuNhanVienTheoPhongBan(phongBan);
                break;
            case 4:
                NHAN_SU_SERVICE.top3NhanVienThamNien();
                break;
            case 5:
                System.out.println("💡 Gợi ý các quản lý hiện có trong công ty:");
                System.out.println("   • [Mã 1] Nguyen Van An   - GĐ Kỹ thuật (Quản lý 4 cấp dưới: 2, 3, 4, 5)");
                System.out.println("   • [Mã 2] Tran Thi Binh   - Senior Dev  (Quản lý 2 cấp dưới: 6, 7)");
                System.out.println("   • [Mã 3] Le Hoang Cuong  - TP Kinh doanh (Quản lý 2 cấp dưới: 8, 10)");
                System.out.println("   • [Mã 5] Vu Thi Hoa      - TP Nhân sự (Quản lý 1 cấp dưới: 9)");
                int maQL = promptInt(scanner, "Nhập mã người quản lý", 1);
                NHAN_SU_SERVICE.danhSachCapDuoiCuaQuanLy(maQL);
                break;
            case 6:
                System.out.println("💡 Gợi ý các nhóm dự án: [Mã 1] Doi Du an ERP | [Mã 2] Nhom An toan thong tin | [Mã 3] Doi Chuyen doi so");
                int maNhom = promptInt(scanner, "Nhập mã nhóm dự án", 1);
                NHAN_SU_SERVICE.chiTietNhomNhanVien(maNhom);
                break;
            case 7:
                NHAN_SU_SERVICE.thongKeNhanVienDaNhiemNhieuNhom();
                break;
            case 8:
                NHAN_SU_SERVICE.thongKeNhanSuTheoChucVu();
                break;
            case 9:
                NHAN_SU_SERVICE.danhSachNhanVienChuaVaoNhom();
                break;
            case 10:
                System.out.println("💡 Gợi ý từ khóa mẫu: \"linh\", \"an\", \"090\", \"company.com\"");
                String tuKhoa = promptString(scanner, "Nhập từ khóa tìm kiếm (tên, email, SĐT)", "linh");
                NHAN_SU_SERVICE.timNhanSuTheoTuKhoa(tuKhoa);
                break;

            // DƯƠNG (Câu 11 -> 20: Vận hành, hợp đồng, chấm công, nghỉ phép)
            case 11:
                System.out.println("💡 Gợi ý: Có 10 hợp đồng (Mã 1 -> 10). Mẫu mã 3 là Hợp đồng không xác định thời hạn của NV Cuong.");
                int maHD = promptInt(scanner, "Nhập mã hợp đồng lao động", 3);
                VAN_HANH_SERVICE.inHopDongChiTiet(maHD);
                break;
            case 12:
                VAN_HANH_SERVICE.canhBaoHopDongSapHetHan(LocalDate.now());
                break;
            case 13:
                int nvCong = promptInt(scanner, "Nhập mã nhân viên", 2);
                int thangCong = promptInt(scanner, "Nhập tháng", 3);
                int namCong = promptInt(scanner, "Nhập năm", 2024);
                VAN_HANH_SERVICE.inBangChamCongCaNhan(nvCong, thangCong, namCong);
                break;
            case 14:
                int thangTre = promptInt(scanner, "Nhập tháng báo cáo đi trễ", 3);
                int namTre = promptInt(scanner, "Nhập năm", 2024);
                VAN_HANH_SERVICE.baocaoDiTre(thangTre, namTre);
                break;
            case 15:
                int thangOT = promptInt(scanner, "Nhập tháng báo cáo làm thêm giờ (OT)", 3);
                int namOT = promptInt(scanner, "Nhập năm", 2024);
                VAN_HANH_SERVICE.baocaoOT(thangOT, namOT);
                break;
            case 16:
                VAN_HANH_SERVICE.theoDoiDonNghiPhep();
                break;
            case 17:
                int maNVNghi = promptInt(scanner, "Nhập mã nhân viên xem lịch sử nghỉ", 2);
                VAN_HANH_SERVICE.lsNghi(maNVNghi);
                break;
            case 18:
                int thangCC = promptInt(scanner, "Nhập tháng thống kê chuyên cần", 3);
                int namCC = promptInt(scanner, "Nhập năm", 2024);
                VAN_HANH_SERVICE.thongkeChuyenCan(thangCC, namCC);
                break;
            case 19:
                VAN_HANH_SERVICE.thongkeHopDong();
                break;
            case 20:
                VAN_HANH_SERVICE.locDieuKhoanPhapLy();
                break;

            // HẠO (Câu 21 -> 30: Tài chính tiền lương, khấu trừ, KPI)
            case 21:
                int nvLuong = promptInt(scanner, "Nhập mã nhân viên", 2);
                int thangLuong = promptInt(scanner, "Nhập kỳ lương tháng", 3);
                int namLuong = promptInt(scanner, "Nhập kỳ lương năm", 2024);
                TAI_CHINH_SERVICE.inPhieuLuong(nvLuong, thangLuong, namLuong);
                break;
            case 22:
                int thangTH = promptInt(scanner, "Nhập kỳ lương tháng", 3);
                int namTH = promptInt(scanner, "Nhập kỳ lương năm", 2024);
                TAI_CHINH_SERVICE.bangTongHopLuongThang(thangTH, namTH);
                break;
            case 23:
                int thangQL = promptInt(scanner, "Nhập kỳ lương tháng", 3);
                int namQL = promptInt(scanner, "Nhập kỳ lương năm", 2024);
                TAI_CHINH_SERVICE.quyLuongTheoPhongBan(thangQL, namQL);
                break;
            case 24:
                int thangTop = promptInt(scanner, "Nhập kỳ lương tháng", 3);
                int namTop = promptInt(scanner, "Nhập kỳ lương năm", 2024);
                TAI_CHINH_SERVICE.topThuNhapCaoNhat(thangTop, namTop);
                break;
            case 25:
                int thangBH = promptInt(scanner, "Nhập kỳ lương tháng", 3);
                int namBH = promptInt(scanner, "Nhập kỳ lương năm", 2024);
                TAI_CHINH_SERVICE.thongKeBaoHiem(thangBH, namBH);
                break;
            case 26:
                int thangPC = promptInt(scanner, "Nhập kỳ lương tháng", 3);
                int namPC = promptInt(scanner, "Nhập kỳ lương năm", 2024);
                TAI_CHINH_SERVICE.baoCaoThuongPhuCap(thangPC, namPC);
                break;
            case 27:
                System.out.println("💡 Gợi ý: Có 6 phiếu KPI (Mã 1 -> 6). Mẫu mã 1 là KPI Quý 1/2024 của NV Tran Thi Binh.");
                int maKPI = promptInt(scanner, "Nhập mã phiếu đánh giá KPI", 1);
                TAI_CHINH_SERVICE.inPhieuKPI(maKPI);
                break;
            case 28:
                String kyXH = promptString(scanner, "Nhập kỳ đánh giá (VD: Quy 1)", "Quy 1");
                int namXH = promptInt(scanner, "Nhập năm đánh giá", 2024);
                TAI_CHINH_SERVICE.xepHangHieuSuat(kyXH, namXH);
                break;
            case 29:
                int quyXS = promptInt(scanner, "Nhập quý (1 - 4)", 1);
                int namXS = promptInt(scanner, "Nhập năm", 2024);
                TAI_CHINH_SERVICE.timNhanSuXuatSac(quyXS, namXS);
                break;
            case 30:
                TAI_CHINH_SERVICE.thuNhapTrungBinhTheoChucVu();
                break;

            // PHÍM TẮT CHẠY DEMO TỰ ĐỘNG THEO PHÂN HỆ
            case 91:
                System.out.println("\n>>> [DEMO TỰ ĐỘNG] CHẠY TOÀN BỘ 10 CÂU CỦA LĨNH (1 -> 10) <<<");
                NHAN_SU_SERVICE.DanhSachNhanVien();
                NHAN_SU_SERVICE.danhSachPhongBan();
                NHAN_SU_SERVICE.traCuuNhanVienTheoPhongBan("Phong CNTT");
                NHAN_SU_SERVICE.top3NhanVienThamNien();
                NHAN_SU_SERVICE.danhSachCapDuoiCuaQuanLy(1);
                NHAN_SU_SERVICE.chiTietNhomNhanVien(1);
                NHAN_SU_SERVICE.thongKeNhanVienDaNhiemNhieuNhom();
                NHAN_SU_SERVICE.thongKeNhanSuTheoChucVu();
                NHAN_SU_SERVICE.danhSachNhanVienChuaVaoNhom();
                NHAN_SU_SERVICE.timNhanSuTheoTuKhoa("linh");
                break;

            case 92:
                System.out.println("\n>>> [DEMO TỰ ĐỘNG] CHẠY TOÀN BỘ 10 CÂU CỦA DƯƠNG (11 -> 20) <<<");
                VAN_HANH_SERVICE.inHopDongChiTiet(3);
                VAN_HANH_SERVICE.canhBaoHopDongSapHetHan(LocalDate.now());
                VAN_HANH_SERVICE.inBangChamCongCaNhan(2, 3, 2024);
                VAN_HANH_SERVICE.baocaoDiTre(3, 2024);
                VAN_HANH_SERVICE.baocaoOT(3, 2024);
                VAN_HANH_SERVICE.theoDoiDonNghiPhep();
                VAN_HANH_SERVICE.lsNghi(2);
                VAN_HANH_SERVICE.thongkeChuyenCan(3, 2024);
                VAN_HANH_SERVICE.thongkeHopDong();
                VAN_HANH_SERVICE.locDieuKhoanPhapLy();
                break;

            case 93:
                System.out.println("\n>>> [DEMO TỰ ĐỘNG] CHẠY TOÀN BỘ 10 CÂU CỦA HẠO (21 -> 30) <<<");
                TAI_CHINH_SERVICE.inPhieuLuong(2, 3, 2024);
                TAI_CHINH_SERVICE.bangTongHopLuongThang(3, 2024);
                TAI_CHINH_SERVICE.quyLuongTheoPhongBan(3, 2024);
                TAI_CHINH_SERVICE.topThuNhapCaoNhat(3, 2024);
                TAI_CHINH_SERVICE.thongKeBaoHiem(3, 2024);
                TAI_CHINH_SERVICE.baoCaoThuongPhuCap(3, 2024);
                TAI_CHINH_SERVICE.inPhieuKPI(1);
                TAI_CHINH_SERVICE.xepHangHieuSuat("Quy 1", 2024);
                TAI_CHINH_SERVICE.timNhanSuXuatSac(1, 2024);
                TAI_CHINH_SERVICE.thuNhapTrungBinhTheoChucVu();
                break;

            default:
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("Nhập lựa chọn của bạn (0 để thoát): ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Vui lòng nhập một lựa chọn hợp lệ.");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);
                if (choice == 0) {
                    System.out.println("\nChương trình kết thúc. Cảm ơn bạn!");
                    break;
                }

                executeChoice(choice, scanner);
                pause(scanner);
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập số hiển thị trên menu.");
            }
        }

        scanner.close();
    }
}
