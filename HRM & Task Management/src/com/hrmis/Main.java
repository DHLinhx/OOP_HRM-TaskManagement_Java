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
        System.out.println("\n===================== MENU TRUY VẤN HRM =====================");
        System.out.println("1. In chi tiết hợp đồng");
        System.out.println("2. Cảnh báo hợp đồng sắp hết hạn");
        System.out.println("3. Bảng chấm công cá nhân");
        System.out.println("4. Báo cáo đi trễ");
        System.out.println("5. Báo cáo làm thêm giờ");
        System.out.println("6. Theo dõi đơn nghỉ phép");
        System.out.println("7. Lịch sử nghỉ của nhân viên");
        System.out.println("8. Thống kê chuyên cần");
        System.out.println("9. Thống kê hợp đồng");
        System.out.println("10. Lọc điều khoản pháp lý");
        System.out.println("11. Danh sách nhân viên");
        System.out.println("12. Danh sách phòng ban");
        System.out.println("13. Tra cứu nhân viên theo phòng ban");
        System.out.println("14. Top 3 nhân viên thâm niên");
        System.out.println("15. Danh sách cấp dưới của quản lý");
        System.out.println("16. Chi tiết nhóm nhân viên");
        System.out.println("17. Thống kê nhân viên đa nhiệm");
        System.out.println("18. Thống kê nhân sự theo chức vụ");
        System.out.println("19. Danh sách nhân viên chưa vào nhóm");
        System.out.println("20. Tìm nhân sự theo từ khóa");
        System.out.println("21. In phiếu lương cá nhân");
        System.out.println("22. Bảng tổng hợp lương tháng");
        System.out.println("23. Quỹ lương theo phòng ban");
        System.out.println("24. Top thu nhập thực lãnh cao nhất");
        System.out.println("25. Thống kê bảo hiểm");
        System.out.println("26. Báo cáo thưởng phụ cấp");
        System.out.println("27. In phiếu KPI");
        System.out.println("28. Xếp hạng hiệu suất");
        System.out.println("29. Tìm nhân sự xuất sắc");
        System.out.println("30. Thu nhập trung bình theo chức vụ");
        System.out.println("0. Thoát chương trình");
        System.out.println("===========================================================");
    }

    private static void executeChoice(int choice) {
        System.out.println("\n=== ĐANG THỰC THI: CÂU " + choice + " ===");

        switch (choice) {
            case 1:
                VAN_HANH_SERVICE.inHopDongChiTiet(3);
                break;
            case 2:
                VAN_HANH_SERVICE.canhBaoHopDongSapHetHan(LocalDate.now());
                break;
            case 3:
                VAN_HANH_SERVICE.inBangChamCongCaNhan(2, 3, 2024);
                break;
            case 4:
                VAN_HANH_SERVICE.baocaoDiTre(3, 2024);
                break;
            case 5:
                VAN_HANH_SERVICE.baocaoOT(3, 2024);
                break;
            case 6:
                VAN_HANH_SERVICE.theoDoiDonNghiPhep();
                break;
            case 7:
                VAN_HANH_SERVICE.lsNghi(2);
                break;
            case 8:
                VAN_HANH_SERVICE.thongkeChuyenCan(3, 2024);
                break;
            case 9:
                VAN_HANH_SERVICE.thongkeHopDong();
                break;
            case 10:
                VAN_HANH_SERVICE.locDieuKhoanPhapLy();
                break;
            case 11:
                NHAN_SU_SERVICE.DanhSachNhanVien();
                break;
            case 12:
                NHAN_SU_SERVICE.danhSachPhongBan();
                break;
            case 13:
                NHAN_SU_SERVICE.traCuuNhanVienTheoPhongBan("Phong CNTT");
                break;
            case 14:
                NHAN_SU_SERVICE.top3NhanVienThamNien();
                break;
            case 15:
                NHAN_SU_SERVICE.danhSachCapDuoiCuaQuanLy(1);
                break;
            case 16:
                NHAN_SU_SERVICE.chiTietNhomNhanVien(1);
                break;
            case 17:
                NHAN_SU_SERVICE.thongKeNhanVienDaNhiemNhieuNhom();
                break;
            case 18:
                NHAN_SU_SERVICE.thongKeNhanSuTheoChucVu();
                break;
            case 19:
                NHAN_SU_SERVICE.danhSachNhanVienChuaVaoNhom();
                break;
            case 20:
                NHAN_SU_SERVICE.timNhanSuTheoTuKhoa("linh");
                break;
            case 21:
                TAI_CHINH_SERVICE.inPhieuLuong(2, 3, 2024);
                break;
            case 22:
                TAI_CHINH_SERVICE.bangTongHopLuongThang(3, 2024);
                break;
            case 23:
                TAI_CHINH_SERVICE.quyLuongTheoPhongBan(3, 2024);
                break;
            case 24:
                TAI_CHINH_SERVICE.topThuNhapCaoNhat(3, 2024);
                break;
            case 25:
                TAI_CHINH_SERVICE.thongKeBaoHiem(3, 2024);
                break;
            case 26:
                TAI_CHINH_SERVICE.baoCaoThuongPhuCap(3, 2024);
                break;
            case 27:
                TAI_CHINH_SERVICE.inPhieuKPI(1);
                break;
            case 28:
                TAI_CHINH_SERVICE.xepHangHieuSuat("Quy 1", 2024);
                break;
            case 29:
                TAI_CHINH_SERVICE.timNhanSuXuatSac(1, 2024);
                break;
            case 30:
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
                    System.out.println("Chương trình kết thúc. Cảm ơn bạn!");
                    break;
                }

                executeChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập số từ 0 đến 30.");
            }
        }

        scanner.close();
    }
}
