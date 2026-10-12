package com.hrmis.services;

import com.hrmis.data.DatabaseMock;
import com.hrmis.enums.LoaiHopDong;
import com.hrmis.enums.TrangThaiCong;
import com.hrmis.enums.TrangThaiDuyet;
import com.hrmis.enums.TrangThaiHopDong;
import com.hrmis.models.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VanHanhService {

    // =========================================================================
    // CÂU 1: Bản in hợp đồng chi tiết
    // =========================================================================
    public void inHopDongChiTiet(int maHopDong) {
        for (HopDong hd : DatabaseMock.dsHopDong) {
            if (hd.getMaHopDong() == maHopDong) {
                // Lấy thông tin nhân viên ký kết
                String tenNV = "Mã " + hd.getMaNhanVien();
                for (NhanVien nv : DatabaseMock.dsNhanVien) {
                    if (nv.getMaNhanVien() == hd.getMaNhanVien()) {
                        tenNV = nv.HoTenNV() + " (Mã " + nv.getMaNhanVien() + ")";
                        break;
                    }
                }

                System.out.println("==============================================================================");
                System.out.println("CHI TIẾT HỢP ĐỒNG LAO ĐỘNG: " + hd.getSoHopDong());
                System.out.println("==============================================================================");
                System.out.printf("Nhân viên ký kết : %s%n", tenNV);
                System.out.printf("Loại hợp đồng    : %s%n", hd.getLoaiHopDong());
                System.out.printf("Thời hạn         : %s -> %s%n",
                        hd.getNgayHieuLuc(),
                        hd.getNgayHetHan() != null ? hd.getNgayHetHan() : "Vô thời hạn");
                System.out.printf("Lương thỏa thuận : %,.0f VNĐ%n", hd.getLuongThoaThuan());
                System.out.printf("Trạng thái       : %s%n", hd.getTrangThai());
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("CÁC ĐIỀU KHOẢN ĐIỀU KIỆN KÈM THEO:");

                boolean coDieuKhoan = false;
                for (DieuKhoanHopDong dk : DatabaseMock.dsDieuKhoanHopDong) {
                    if (dk.getMaHopDong() == maHopDong) {
                        coDieuKhoan = true;
                        System.out.printf("  [Điều %d] %-25s (%s)%n", dk.getSoThuTuDieu(), dk.getTieuDeDieuKhoan(),
                                dk.getLoaiDieuKhoan());
                        System.out.printf("          -> %s%n", dk.getNoiDungDieuKhoan());
                    }
                }
                if (!coDieuKhoan) {
                    System.out.println("  (Hợp đồng không có điều khoản phụ kèm theo)");
                }
                return;
            }
        }

        System.out.println("Không tìm thấy hợp đồng có mã: " + maHopDong);
    }

    // =========================================================================
    // CÂU 2: Cảnh báo hợp đồng sắp hết hạn trong vòng 30 ngày
    // =========================================================================
    public void canhBaoHopDongSapHetHan(LocalDate mocThoiGian) {
        System.out.println("==============================================================================");
        System.out.println("CẢNH BÁO HỢP ĐỒNG SẮP HẾT HẠN (30 NGÀY TỪ " + mocThoiGian + ")");
        System.out.println("==============================================================================");
        System.out.printf("%-14s %-8s %-22s %-18s %s%n", "Số HĐ", "Mã NV", "Họ và tên", "Loại hợp đồng",
                "Ngày hết hạn");
        System.out.println("------------------------------------------------------------------------------");

        LocalDate moc30NgaySau = mocThoiGian.plusDays(30);
        boolean timThay = false;

        for (HopDong hd : DatabaseMock.dsHopDong) {
            if (hd.getNgayHetHan() != null) {
                LocalDate ngayHetHan = hd.getNgayHetHan();
                if (!ngayHetHan.isBefore(mocThoiGian) && !ngayHetHan.isAfter(moc30NgaySau)) {
                    timThay = true;
                    String hoTen = "";
                    for (NhanVien nv : DatabaseMock.dsNhanVien) {
                        if (nv.getMaNhanVien() == hd.getMaNhanVien()) {
                            hoTen = nv.HoTenNV();
                            break;
                        }
                    }
                    System.out.printf("%-14s %-8d %-22s %-18s %s%n",
                            hd.getSoHopDong(), hd.getMaNhanVien(), hoTen, hd.getLoaiHopDong(), ngayHetHan);
                }
            }
        }

        if (!timThay) {
            System.out.println("-> Không có hợp đồng nào sắp hết hạn trong 30 ngày tới.");
        }
    }

    // =========================================================================
    // CÂU 3: Bảng chấm công cá nhân theo tháng/năm
    // =========================================================================
    public void inBangChamCongCaNhan(int maNhanVien, int thang, int nam) {
        String hoTen = "Mã " + maNhanVien;
        for (NhanVien nv : DatabaseMock.dsNhanVien) {
            if (nv.getMaNhanVien() == maNhanVien) {
                hoTen = nv.HoTenNV();
                break;
            }
        }

        System.out.println("==============================================================================");
        System.out.printf("BẢNG CHẤM CÔNG NHÂN VIÊN: %s (Mã %d) - THÁNG %02d/%d%n", hoTen, maNhanVien, thang, nam);
        System.out.println("==============================================================================");
        System.out.printf("%-12s %-18s %-10s %-10s %-12s %s%n", "Ngày", "Ca làm việc", "Giờ vào", "Giờ ra",
                "Số giờ làm", "Trạng thái");
        System.out.println("------------------------------------------------------------------------------");

        boolean timThay = false;
        for (ChamCong cc : DatabaseMock.dsChamCong) {
            if (cc.getMaNhanVien() == maNhanVien
                    && cc.getNgayLamViec().getMonthValue() == thang
                    && cc.getNgayLamViec().getYear() == nam) {

                timThay = true;
                String tenCa = "Ca " + cc.getMaCa();
                for (CaLamViec ca : DatabaseMock.dsCaLamViec) {
                    if (ca.getMaCa() == cc.getMaCa()) {
                        tenCa = ca.getTenCa();
                        break;
                    }
                }

                double soGioLam = 0.0;
                if (cc.getGioVaoThucTe() != null && cc.getGioRaThucTe() != null) {
                    long soPhut = Duration.between(cc.getGioVaoThucTe(), cc.getGioRaThucTe()).toMinutes();
                    soGioLam = soPhut / 60.0;
                }

                System.out.printf("%-12s %-18s %-10s %-10s %-12s %s%n",
                        cc.getNgayLamViec(),
                        tenCa,
                        cc.getGioVaoThucTe(),
                        cc.getGioRaThucTe(),
                        String.format("%.1f giờ", soGioLam),
                        cc.getTrangThaiCong());
            }
        }

        if (!timThay) {
            System.out.println("-> Không tìm thấy dữ liệu chấm công trong tháng này.");
        }
    }

    // =========================================================================
    // CÂU 4: Báo cáo đi trễ trong tháng (sắp xếp giảm dần theo số lần vi phạm)
    // =========================================================================
    static class ThongKeDiTre {
        int maNV;
        String hoTen;
        int soLan;

        public ThongKeDiTre(int maNV, String hoTen, int soLan) {
            this.maNV = maNV;
            this.hoTen = hoTen;
            this.soLan = soLan;
        }
    }

    public void baocaoDiTre(int thang, int nam) {
        System.out.println("==============================================================================");
        System.out.printf("BÁO CÁO NHÂN VIÊN ĐI TRỄ - THÁNG %02d/%d%n", thang, nam);
        System.out.println("==============================================================================");
        System.out.printf("%-6s %-8s %-25s %s%n", "Hạng", "Mã NV", "Họ và tên", "Số lần đi trễ");
        System.out.println("------------------------------------------------------------------------------");

        List<ThongKeDiTre> danhSach = new ArrayList<>();
        for (NhanVien nv : DatabaseMock.dsNhanVien) {
            int soLanTre = 0;
            for (ChamCong cc : DatabaseMock.dsChamCong) {
                if (cc.getMaNhanVien() == nv.getMaNhanVien()
                        && cc.getNgayLamViec().getMonthValue() == thang
                        && cc.getNgayLamViec().getYear() == nam
                        && cc.getTrangThaiCong() == TrangThaiCong.DI_TRE) {
                    soLanTre++;
                }
            }
            if (soLanTre > 0) {
                danhSach.add(new ThongKeDiTre(nv.getMaNhanVien(), nv.HoTenNV(), soLanTre));
            }
        }

        if (danhSach.isEmpty()) {
            System.out.println("-> Tuyệt vời! Không có nhân viên nào đi trễ trong tháng này.");
            return;
        }

        danhSach.sort((a, b) -> Integer.compare(b.soLan, a.soLan));
        int rank = 1;
        for (ThongKeDiTre tk : danhSach) {
            System.out.printf("%-6d %-8d %-25s %d lần%n", rank++, tk.maNV, tk.hoTen, tk.soLan);
        }
    }

    // =========================================================================
    // CÂU 5: Báo cáo nhân viên làm thêm giờ (OT) trong tháng
    // =========================================================================
    static class ThongKeOT {
        int maNV;
        String hoTen;
        double soGio;

        public ThongKeOT(int maNV, String hoTen, double soGio) {
            this.maNV = maNV;
            this.hoTen = hoTen;
            this.soGio = soGio;
        }
    }

    public void baocaoOT(int thang, int nam) {
        System.out.println("==============================================================================");
        System.out.printf("BÁO CÁO TOP NHÂN VIÊN LÀM THÊM GIỜ (OT) - THÁNG %02d/%d%n", thang, nam);
        System.out.println("==============================================================================");
        System.out.printf("%-6s %-8s %-25s %s%n", "Hạng", "Mã NV", "Họ và tên", "Tổng số giờ OT");
        System.out.println("------------------------------------------------------------------------------");

        List<ThongKeOT> danhSach = new ArrayList<>();
        for (NhanVien nv : DatabaseMock.dsNhanVien) {
            double soGio = 0;
            for (ChamCong cc : DatabaseMock.dsChamCong) {
                if (cc.getMaNhanVien() == nv.getMaNhanVien()
                        && cc.getNgayLamViec().getMonthValue() == thang
                        && cc.getNgayLamViec().getYear() == nam) {
                    soGio += cc.getSoGioLamThem();
                }
            }
            if (soGio > 0) {
                danhSach.add(new ThongKeOT(nv.getMaNhanVien(), nv.HoTenNV(), soGio));
            }
        }

        if (danhSach.isEmpty()) {
            System.out.println("-> Không có nhân viên nào làm thêm giờ trong tháng này.");
            return;
        }

        danhSach.sort((a, b) -> Double.compare(b.soGio, a.soGio));
        int rank = 1;
        for (ThongKeOT tk : danhSach) {
            System.out.printf("%-6d %-8d %-25s %.1f giờ%n", rank++, tk.maNV, tk.hoTen, tk.soGio);
        }
    }

    // =========================================================================
    // CÂU 6: Bảng theo dõi đơn nghỉ phép đang CHỜ DUYỆT
    // =========================================================================
    public void theoDoiDonNghiPhep() {
        System.out.println("==============================================================================");
        System.out.println("DANH SÁCH ĐƠN NGHỈ PHÉP ĐANG CHỜ DUYỆT");
        System.out.println("==============================================================================");
        System.out.printf("%-8s %-22s %-16s %-10s %-24s %s%n", "Mã đơn", "Người nộp", "Loại nghỉ", "Số ngày",
                "Thời gian", "Người duyệt");
        System.out.println("------------------------------------------------------------------------------");

        boolean timThay = false;
        for (DonXinNghi don : DatabaseMock.dsDonXinNghi) {
            if (don.getTrangThaiDuyet() == TrangThaiDuyet.CHO_DUYET) {
                timThay = true;
                String tenNguoiNop = "Mã " + don.getMaNhanVien();
                for (NhanVien nv : DatabaseMock.dsNhanVien) {
                    if (nv.getMaNhanVien() == don.getMaNhanVien()) {
                        tenNguoiNop = nv.HoTenNV();
                        break;
                    }
                }

                String tenNguoiDuyet = "Chưa chỉ định";
                if (don.getNguoiDuyet() != null) {
                    for (NhanVien nv : DatabaseMock.dsNhanVien) {
                        if (nv.getMaNhanVien() == don.getNguoiDuyet()) {
                            tenNguoiDuyet = nv.HoTenNV();
                            break;
                        }
                    }
                }

                long soNgayNghi = don.getNgayKetThuc().toEpochDay() - don.getNgayBatDau().toEpochDay() + 1;
                String thoiGian = don.getNgayBatDau() + " -> " + don.getNgayKetThuc();

                System.out.printf("%-8d %-22s %-16s %-10s %-24s %s%n",
                        don.getMaDonNghi(), tenNguoiNop, don.getLoaiNghi(), soNgayNghi + " ngày", thoiGian,
                        tenNguoiDuyet);
            }
        }

        if (!timThay) {
            System.out.println("-> Hiện không có đơn xin nghỉ phép nào đang chờ duyệt.");
        }
    }

    // =========================================================================
    // CÂU 7: Lịch sử nghỉ phép của một nhân viên
    // =========================================================================
    public void lsNghi(int maNV) {
        String hoTen = "Không xác định";
        boolean timThayNV = false;
        for (NhanVien nv : DatabaseMock.dsNhanVien) {
            if (nv.getMaNhanVien() == maNV) {
                hoTen = nv.HoTenNV();
                timThayNV = true;
                break;
            }
        }
        if (!timThayNV) {
            System.out.println("Không tìm thấy nhân viên có mã: " + maNV);
            return;
        }

        System.out.println("==============================================================================");
        System.out.printf("LỊCH SỬ NGHỈ PHÉP: %s (Mã %d)%n", hoTen, maNV);
        System.out.println("==============================================================================");
        System.out.printf("%-8s %-16s %-10s %-25s %-14s %s%n", "Mã đơn", "Loại nghỉ", "Số ngày", "Thời gian",
                "Trạng thái", "Lý do");
        System.out.println("------------------------------------------------------------------------------");

        boolean coDon = false;
        for (DonXinNghi don : DatabaseMock.dsDonXinNghi) {
            if (don.getMaNhanVien() == maNV) {
                coDon = true;
                long soNgayNghi = don.getNgayKetThuc().toEpochDay() - don.getNgayBatDau().toEpochDay() + 1;
                String thoiGian = don.getNgayBatDau() + " -> " + don.getNgayKetThuc();

                System.out.printf("%-8d %-16s %-10s %-25s %-14s %s%n",
                        don.getMaDonNghi(), don.getLoaiNghi(), soNgayNghi + " ngày", thoiGian, don.getTrangThaiDuyet(),
                        don.getLyDo());
            }
        }
        if (!coDon) {
            System.out.println("-> Nhân viên chưa có đơn xin nghỉ phép nào.");
        }
    }

    // =========================================================================
    // CÂU 8: Thống kê nhân viên đạt chuyên cần 100% trong tháng
    // =========================================================================
    public void thongkeChuyenCan(int thang, int nam) {
        System.out.println("==============================================================================");
        System.out.printf("DANH SÁCH NHÂN VIÊN ĐẠT CHUYÊN CẦN 100%% - THÁNG %02d/%d%n", thang, nam);
        System.out.println("==============================================================================");
        System.out.printf("%-8s %-25s %-18s %s%n", "Mã NV", "Họ và tên", "Số ngày công", "Tỷ lệ đúng giờ");
        System.out.println("------------------------------------------------------------------------------");

        boolean timThay = false;
        for (NhanVien nv : DatabaseMock.dsNhanVien) {
            int tongcong = 0;
            int dungGio = 0;
            for (ChamCong cc : DatabaseMock.dsChamCong) {
                if (cc.getMaNhanVien() == nv.getMaNhanVien()
                        && cc.getNgayLamViec().getMonthValue() == thang
                        && cc.getNgayLamViec().getYear() == nam) {
                    tongcong++;
                    if (cc.getTrangThaiCong() == TrangThaiCong.DUNG_GIO) {
                        dungGio++;
                    }
                }
            }
            if (tongcong == dungGio && dungGio > 0) {
                timThay = true;
                System.out.printf("%-8d %-25s %-18s %s%n",
                        nv.getMaNhanVien(),
                        nv.HoTenNV(),
                        dungGio + "/" + tongcong + " ngày",
                        "100%");
            }
        }
        if (!timThay) {
            System.out.println("-> Không có nhân viên nào đạt chuyên cần 100% trong tháng.");
        }
    }

    // =========================================================================
    // CÂU 9: Thống kê số lượng nhân viên theo từng loại hợp đồng lao động
    // =========================================================================
    public void thongkeHopDong() {
        System.out.println("==============================================================================");
        System.out.println("THỐNG KÊ NHÂN SỰ THEO TỪNG LOẠI HỢP ĐỒNG LAO ĐỘNG");
        System.out.println("==============================================================================");

        for (LoaiHopDong lhd : LoaiHopDong.values()) {
            int count = 0;
            for (HopDong hd : DatabaseMock.dsHopDong) {
                if (hd.getLoaiHopDong() == lhd && hd.getTrangThai() != TrangThaiHopDong.DA_HET_HAN) {
                    count++;
                }
            }

            System.out.printf("%n>> LOẠI HỢP ĐỒNG: %s (%d nhân viên)%n", lhd, count);
            if (count > 0) {
                System.out.printf("   %-8s %-22s %-15s %s%n", "Mã NV", "Họ và tên", "Số hợp đồng", "Trạng thái HĐ");
                System.out.println("   --------------------------------------------------------------------------");
                for (HopDong hd : DatabaseMock.dsHopDong) {
                    if (hd.getLoaiHopDong() == lhd && hd.getTrangThai() != TrangThaiHopDong.DA_HET_HAN) {
                        for (NhanVien nv : DatabaseMock.dsNhanVien) {
                            if (nv.getMaNhanVien() == hd.getMaNhanVien()) {
                                System.out.printf("   %-8d %-22s %-15s %s%n",
                                        nv.getMaNhanVien(),
                                        nv.HoTenNV(),
                                        hd.getSoHopDong(),
                                        hd.getTrangThai());
                            }
                        }
                    }
                }
            }
        }
    }

    // =========================================================================
    // CÂU 10: Lọc các điều khoản pháp lý (Bảo mật hoặc Kỷ luật) trên hệ thống
    // =========================================================================
    public void locDieuKhoanPhapLy() {
        System.out.println("==============================================================================");
        System.out.println("DANH SÁCH ĐIỀU KHOẢN PHÁP LÝ (BẢO MẬT & KỶ LUẬT)");
        System.out.println("==============================================================================");

        boolean timThay = false;
        for (DieuKhoanHopDong dk : DatabaseMock.dsDieuKhoanHopDong) {
            String loai = dk.getLoaiDieuKhoan();
            if (loai != null && (loai.equalsIgnoreCase("Bao mat") || loai.equalsIgnoreCase("Ky luat"))) {
                timThay = true;

                String soHD = "Mã HĐ: " + dk.getMaHopDong();
                for (HopDong hd : DatabaseMock.dsHopDong) {
                    if (hd.getMaHopDong() == dk.getMaHopDong()) {
                        soHD = hd.getSoHopDong();
                        break;
                    }
                }

                System.out.printf("[%s] - Điều %d (%s): %s%n",
                        dk.getLoaiDieuKhoan().toUpperCase(),
                        dk.getSoThuTuDieu(),
                        soHD,
                        dk.getTieuDeDieuKhoan());
                System.out.printf("   -> Nội dung: %s%n%n", dk.getNoiDungDieuKhoan());
            }
        }

        if (!timThay) {
            System.out.println("-> Không tìm thấy điều khoản bảo mật hoặc kỷ luật nào.");
        }
    }

}
