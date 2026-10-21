package com.hrmis.services;

import com.hrmis.data.DatabaseMock;
import com.hrmis.enums.LoaiHopDong;
import com.hrmis.enums.TrangThaiCong;
import com.hrmis.enums.TrangThaiDuyet;
import com.hrmis.enums.TrangThaiHopDong;
import com.hrmis.models.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VanHanhService {

    // Helper: Tìm tên nhân viên theo mã (dùng Stream & Lambda)
    private String getTenNhanVien(int maNV) {
        return DatabaseMock.dsNhanVien.stream()
                .filter(nv -> nv.getMaNhanVien() == maNV)
                .map(NhanVien::HoTenNV)
                .findFirst()
                .orElse("Mã " + maNV);
    }

    // Helper: Tìm tên ca làm việc theo mã (dùng Stream & Lambda)
    private String getTenCa(int maCa) {
        return DatabaseMock.dsCaLamViec.stream()
                .filter(ca -> ca.getMaCa() == maCa)
                .map(CaLamViec::getTenCa)
                .findFirst()
                .orElse("Ca " + maCa);
    }

    // Helper: Tìm số hợp đồng theo mã hợp đồng (dùng Stream & Lambda)
    private String getSoHopDong(int maHopDong) {
        return DatabaseMock.dsHopDong.stream()
                .filter(hd -> hd.getMaHopDong() == maHopDong)
                .map(HopDong::getSoHopDong)
                .findFirst()
                .orElse("Mã HĐ: " + maHopDong);
    }

    // =========================================================================
    // CÂU 1: Bản in hợp đồng chi tiết
    // =========================================================================
    public void inHopDongChiTiet(int maHopDong) {
        DatabaseMock.dsHopDong.stream()
                .filter(hd -> hd.getMaHopDong() == maHopDong)
                .findFirst()
                .ifPresentOrElse(hd -> {
                    String tenNV = getTenNhanVien(hd.getMaNhanVien()) + " (Mã " + hd.getMaNhanVien() + ")";

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

                    List<DieuKhoanHopDong> dsDK = DatabaseMock.dsDieuKhoanHopDong.stream()
                            .filter(dk -> dk.getMaHopDong() == maHopDong)
                            .collect(Collectors.toList());

                    if (dsDK.isEmpty()) {
                        System.out.println("  (Hợp đồng không có điều khoản phụ kèm theo)");
                    } else {
                        dsDK.forEach(dk -> {
                            System.out.printf("  [Điều %d] %-25s (%s)%n", dk.getSoThuTuDieu(), dk.getTieuDeDieuKhoan(), dk.getLoaiDieuKhoan());
                            System.out.printf("          -> %s%n", dk.getNoiDungDieuKhoan());
                        });
                    }
                }, () -> System.out.println("Không tìm thấy hợp đồng có mã: " + maHopDong));
    }

    // =========================================================================
    // CÂU 2: Cảnh báo hợp đồng sắp hết hạn trong vòng 30 ngày
    // =========================================================================
    public void canhBaoHopDongSapHetHan(LocalDate mocThoiGian) {
        System.out.println("==============================================================================");
        System.out.println("CẢNH BÁO HỢP ĐỒNG SẮP HẾT HẠN (30 NGÀY TỪ " + mocThoiGian + ")");
        System.out.println("==============================================================================");
        System.out.printf("%-14s %-8s %-22s %-18s %s%n", "Số HĐ", "Mã NV", "Họ và tên", "Loại hợp đồng", "Ngày hết hạn");
        System.out.println("------------------------------------------------------------------------------");

        LocalDate moc30NgaySau = mocThoiGian.plusDays(30);

        List<HopDong> dsSapHetHan = DatabaseMock.dsHopDong.stream()
                .filter(hd -> hd.getNgayHetHan() != null
                        && !hd.getNgayHetHan().isBefore(mocThoiGian)
                        && !hd.getNgayHetHan().isAfter(moc30NgaySau))
                .collect(Collectors.toList());

        if (dsSapHetHan.isEmpty()) {
            System.out.println("-> Không có hợp đồng nào sắp hết hạn trong 30 ngày tới.");
            return;
        }

        dsSapHetHan.forEach(hd -> {
            String hoTen = getTenNhanVien(hd.getMaNhanVien());
            System.out.printf("%-14s %-8d %-22s %-18s %s%n",
                    hd.getSoHopDong(), hd.getMaNhanVien(), hoTen, hd.getLoaiHopDong(), hd.getNgayHetHan());
        });
    }

    // =========================================================================
    // CÂU 3: Bảng chấm công cá nhân theo tháng/năm
    // =========================================================================
    public void inBangChamCongCaNhan(int maNhanVien, int thang, int nam) {
        String hoTen = getTenNhanVien(maNhanVien);

        System.out.println("==============================================================================");
        System.out.printf("BẢNG CHẤM CÔNG NHÂN VIÊN: %s (Mã %d) - THÁNG %02d/%d%n", hoTen, maNhanVien, thang, nam);
        System.out.println("==============================================================================");
        System.out.printf("%-12s %-18s %-10s %-10s %-12s %s%n", "Ngày", "Ca làm việc", "Giờ vào", "Giờ ra", "Số giờ làm", "Trạng thái");
        System.out.println("------------------------------------------------------------------------------");

        List<ChamCong> dsCong = DatabaseMock.dsChamCong.stream()
                .filter(cc -> cc.getMaNhanVien() == maNhanVien
                        && cc.getNgayLamViec().getMonthValue() == thang
                        && cc.getNgayLamViec().getYear() == nam)
                .collect(Collectors.toList());

        if (dsCong.isEmpty()) {
            System.out.println("-> Không tìm thấy dữ liệu chấm công trong tháng này.");
            return;
        }

        dsCong.forEach(cc -> {
            String tenCa = getTenCa(cc.getMaCa());
            double soGioLam = (cc.getGioVaoThucTe() != null && cc.getGioRaThucTe() != null)
                    ? Duration.between(cc.getGioVaoThucTe(), cc.getGioRaThucTe()).toMinutes() / 60.0
                    : 0.0;

            System.out.printf("%-12s %-18s %-10s %-10s %-12s %s%n",
                    cc.getNgayLamViec(),
                    tenCa,
                    cc.getGioVaoThucTe(),
                    cc.getGioRaThucTe(),
                    String.format("%.1f giờ", soGioLam),
                    cc.getTrangThaiCong());
        });
    }

    // =========================================================================
    // CÂU 4: Báo cáo đi trễ trong tháng (sắp xếp giảm dần theo số lần vi phạm)
    // =========================================================================
    public static class ThongKeDiTre {
        public int maNV;
        public String hoTen;
        public int soLan;

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

        List<ThongKeDiTre> danhSach = DatabaseMock.dsNhanVien.stream()
                .map(nv -> {
                    long soLanTre = DatabaseMock.dsChamCong.stream()
                            .filter(cc -> cc.getMaNhanVien() == nv.getMaNhanVien()
                                    && cc.getNgayLamViec().getMonthValue() == thang
                                    && cc.getNgayLamViec().getYear() == nam
                                    && cc.getTrangThaiCong() == TrangThaiCong.DI_TRE)
                            .count();
                    return new ThongKeDiTre(nv.getMaNhanVien(), nv.HoTenNV(), (int) soLanTre);
                })
                .filter(tk -> tk.soLan > 0)
                .sorted((a, b) -> Integer.compare(b.soLan, a.soLan))
                .collect(Collectors.toList());

        if (danhSach.isEmpty()) {
            System.out.println("-> Tuyệt vời! Không có nhân viên nào đi trễ trong tháng này.");
            return;
        }

        int[] rank = {1};
        danhSach.forEach(tk -> System.out.printf("%-6d %-8d %-25s %d lần%n", rank[0]++, tk.maNV, tk.hoTen, tk.soLan));
    }

    // =========================================================================
    // CÂU 5: Báo cáo nhân viên làm thêm giờ (OT) trong tháng
    // =========================================================================
    public static class ThongKeOT {
        public int maNV;
        public String hoTen;
        public double soGio;

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

        List<ThongKeOT> danhSach = DatabaseMock.dsNhanVien.stream()
                .map(nv -> {
                    double soGio = DatabaseMock.dsChamCong.stream()
                            .filter(cc -> cc.getMaNhanVien() == nv.getMaNhanVien()
                                    && cc.getNgayLamViec().getMonthValue() == thang
                                    && cc.getNgayLamViec().getYear() == nam)
                            .mapToDouble(ChamCong::getSoGioLamThem)
                            .sum();
                    return new ThongKeOT(nv.getMaNhanVien(), nv.HoTenNV(), soGio);
                })
                .filter(tk -> tk.soGio > 0)
                .sorted((a, b) -> Double.compare(b.soGio, a.soGio))
                .collect(Collectors.toList());

        if (danhSach.isEmpty()) {
            System.out.println("-> Không có nhân viên nào làm thêm giờ trong tháng này.");
            return;
        }

        int[] rank = {1};
        danhSach.forEach(tk -> System.out.printf("%-6d %-8d %-25s %.1f giờ%n", rank[0]++, tk.maNV, tk.hoTen, tk.soGio));
    }

    // =========================================================================
    // CÂU 6: Bảng theo dõi đơn nghỉ phép đang CHỜ DUYỆT
    // =========================================================================
    public void theoDoiDonNghiPhep() {
        System.out.println("==============================================================================");
        System.out.println("DANH SÁCH ĐƠN NGHỈ PHÉP ĐANG CHỜ DUYỆT");
        System.out.println("==============================================================================");
        System.out.printf("%-8s %-22s %-16s %-10s %-24s %s%n", "Mã đơn", "Người nộp", "Loại nghỉ", "Số ngày", "Thời gian", "Người duyệt");
        System.out.println("------------------------------------------------------------------------------");

        List<DonXinNghi> dsChoDuyet = DatabaseMock.dsDonXinNghi.stream()
                .filter(don -> don.getTrangThaiDuyet() == TrangThaiDuyet.CHO_DUYET)
                .collect(Collectors.toList());

        if (dsChoDuyet.isEmpty()) {
            System.out.println("-> Hiện không có đơn xin nghỉ phép nào đang chờ duyệt.");
            return;
        }

        dsChoDuyet.forEach(don -> {
            String tenNguoiNop = getTenNhanVien(don.getMaNhanVien());
            String tenNguoiDuyet = don.getNguoiDuyet() != null ? getTenNhanVien(don.getNguoiDuyet()) : "Chưa chỉ định";
            long soNgayNghi = don.getNgayKetThuc().toEpochDay() - don.getNgayBatDau().toEpochDay() + 1;
            String thoiGian = don.getNgayBatDau() + " -> " + don.getNgayKetThuc();

            System.out.printf("%-8d %-22s %-16s %-10s %-24s %s%n",
                    don.getMaDonNghi(), tenNguoiNop, don.getLoaiNghi(), soNgayNghi + " ngày", thoiGian, tenNguoiDuyet);
        });
    }

    // =========================================================================
    // CÂU 7: Lịch sử nghỉ phép của một nhân viên
    // =========================================================================
    public void lsNghi(int maNV) {
        boolean timThayNV = DatabaseMock.dsNhanVien.stream().anyMatch(nv -> nv.getMaNhanVien() == maNV);
        if (!timThayNV) {
            System.out.println("Không tìm thấy nhân viên có mã: " + maNV);
            return;
        }

        String hoTen = getTenNhanVien(maNV);

        System.out.println("==============================================================================");
        System.out.printf("LỊCH SỬ NGHỈ PHÉP: %s (Mã %d)%n", hoTen, maNV);
        System.out.println("==============================================================================");
        System.out.printf("%-8s %-16s %-10s %-25s %-14s %s%n", "Mã đơn", "Loại nghỉ", "Số ngày", "Thời gian", "Trạng thái", "Lý do");
        System.out.println("------------------------------------------------------------------------------");

        List<DonXinNghi> dsNghi = DatabaseMock.dsDonXinNghi.stream()
                .filter(don -> don.getMaNhanVien() == maNV)
                .collect(Collectors.toList());

        if (dsNghi.isEmpty()) {
            System.out.println("-> Nhân viên chưa có đơn xin nghỉ phép nào.");
            return;
        }

        dsNghi.forEach(don -> {
            long soNgayNghi = don.getNgayKetThuc().toEpochDay() - don.getNgayBatDau().toEpochDay() + 1;
            String thoiGian = don.getNgayBatDau() + " -> " + don.getNgayKetThuc();

            System.out.printf("%-8d %-16s %-10s %-25s %-14s %s%n",
                    don.getMaDonNghi(), don.getLoaiNghi(), soNgayNghi + " ngày", thoiGian, don.getTrangThaiDuyet(), don.getLyDo());
        });
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

        List<NhanVien> dsChuyenCan = DatabaseMock.dsNhanVien.stream()
                .filter(nv -> {
                    long tongCong = DatabaseMock.dsChamCong.stream()
                            .filter(cc -> cc.getMaNhanVien() == nv.getMaNhanVien()
                                    && cc.getNgayLamViec().getMonthValue() == thang
                                    && cc.getNgayLamViec().getYear() == nam)
                            .count();
                    long dungGio = DatabaseMock.dsChamCong.stream()
                            .filter(cc -> cc.getMaNhanVien() == nv.getMaNhanVien()
                                    && cc.getNgayLamViec().getMonthValue() == thang
                                    && cc.getNgayLamViec().getYear() == nam
                                    && cc.getTrangThaiCong() == TrangThaiCong.DUNG_GIO)
                            .count();
                    return tongCong > 0 && tongCong == dungGio;
                })
                .collect(Collectors.toList());

        if (dsChuyenCan.isEmpty()) {
            System.out.println("-> Không có nhân viên nào đạt chuyên cần 100% trong tháng.");
            return;
        }

        dsChuyenCan.forEach(nv -> {
            long tongCong = DatabaseMock.dsChamCong.stream()
                    .filter(cc -> cc.getMaNhanVien() == nv.getMaNhanVien()
                            && cc.getNgayLamViec().getMonthValue() == thang
                            && cc.getNgayLamViec().getYear() == nam)
                    .count();
            System.out.printf("%-8d %-25s %-18s %s%n",
                    nv.getMaNhanVien(),
                    nv.HoTenNV(),
                    tongCong + "/" + tongCong + " ngày",
                    "100%");
        });
    }

    // =========================================================================
    // CÂU 9: Thống kê số lượng nhân viên theo từng loại hợp đồng lao động
    // =========================================================================
    public void thongkeHopDong() {
        System.out.println("==============================================================================");
        System.out.println("THỐNG KÊ NHÂN SỰ THEO TỪNG LOẠI HỢP ĐỒNG LAO ĐỘNG");
        System.out.println("==============================================================================");

        Arrays.stream(LoaiHopDong.values()).forEach(lhd -> {
            List<HopDong> dsHDTheoLoai = DatabaseMock.dsHopDong.stream()
                    .filter(hd -> hd.getLoaiHopDong() == lhd && hd.getTrangThai() != TrangThaiHopDong.DA_HET_HAN)
                    .collect(Collectors.toList());

            System.out.printf("%n>> LOẠI HỢP ĐỒNG: %s (%d nhân viên)%n", lhd, dsHDTheoLoai.size());
            if (!dsHDTheoLoai.isEmpty()) {
                System.out.printf("   %-8s %-22s %-15s %s%n", "Mã NV", "Họ và tên", "Số hợp đồng", "Trạng thái HĐ");
                System.out.println("   --------------------------------------------------------------------------");
                dsHDTheoLoai.forEach(hd -> {
                    String hoTen = getTenNhanVien(hd.getMaNhanVien());
                    System.out.printf("   %-8d %-22s %-15s %s%n",
                            hd.getMaNhanVien(),
                            hoTen,
                            hd.getSoHopDong(),
                            hd.getTrangThai());
                });
            }
        });
    }

    // =========================================================================
    // CÂU 10: Lọc các điều khoản pháp lý (Bảo mật hoặc Kỷ luật) trên hệ thống
    // =========================================================================
    public void locDieuKhoanPhapLy() {
        System.out.println("==============================================================================");
        System.out.println("DANH SÁCH ĐIỀU KHOẢN PHÁP LÝ (BẢO MẬT & KỶ LUẬT)");
        System.out.println("==============================================================================");

        List<DieuKhoanHopDong> dsDKPhapLy = DatabaseMock.dsDieuKhoanHopDong.stream()
                .filter(dk -> dk.getLoaiDieuKhoan() != null &&
                        (dk.getLoaiDieuKhoan().equalsIgnoreCase("Bao mat") || dk.getLoaiDieuKhoan().equalsIgnoreCase("Ky luat")))
                .collect(Collectors.toList());

        if (dsDKPhapLy.isEmpty()) {
            System.out.println("-> Không tìm thấy điều khoản bảo mật hoặc kỷ luật nào.");
            return;
        }

        dsDKPhapLy.forEach(dk -> {
            String soHD = getSoHopDong(dk.getMaHopDong());
            System.out.printf("[%s] - Điều %d (%s): %s%n",
                    dk.getLoaiDieuKhoan().toUpperCase(),
                    dk.getSoThuTuDieu(),
                    soHD,
                    dk.getTieuDeDieuKhoan());
            System.out.printf("   -> Nội dung: %s%n%n", dk.getNoiDungDieuKhoan());
        });
    }
}
