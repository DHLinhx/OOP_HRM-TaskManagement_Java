package com.hrmis.services;

import com.hrmis.data.DatabaseMock;
import com.hrmis.models.*;

import java.time.Duration;
import java.time.LocalDate;

public class VanHanhService {

    // =========================================================================
    // CÂU 1: Bản in hợp đồng chi tiết
    // =========================================================================
    public void inHopDongChiTiet(int maHopDong) {
        // 1. Duyệt trực tiếp qua từng HopDong (không cần ép kiểu)
        for (HopDong hd : DatabaseMock.dsHopDong) {
            if (hd.getMaHopDong() == maHopDong) {
                System.out.println("--- THÔNG TIN HỢP ĐỒNG ---");
                System.out.println("Số hợp đồng: " + hd.getSoHopDong());
                System.out.println("Mã nhân viên: " + hd.getMaNhanVien());
                System.out.println("Loại hợp đồng: " + hd.getLoaiHopDong());
                System.out.printf("Lương thỏa thuận: %,.0f VNĐ\n", hd.getLuongThoaThuan());
                System.out.println("Trạng thái: " + hd.getTrangThai());

                // 2. Duyệt trực tiếp qua từng DieuKhoanHopDong
                System.out.println("--- CÁC ĐIỀU KHOẢN KÈM THEO ---");
                for (DieuKhoanHopDong dk : DatabaseMock.dsDieuKhoanHopDong) {
                    if (dk.getMaHopDong() == maHopDong) {
                        System.out.println("Điều " + dk.getSoThuTuDieu() + ": " + dk.getTieuDeDieuKhoan() + " ("
                                + dk.getLoaiDieuKhoan() + ")");
                        System.out.println("  -> " + dk.getNoiDungDieuKhoan());
                    }
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
        System.out.println("=== DANH SÁCH HỢP ĐỒNG SẮP HẾT HẠN TRONG 30 NGÀY ===");

        // Mốc thời gian 30 ngày sau
        LocalDate moc30NgaySau = mocThoiGian.plusDays(30);

        for (HopDong hd : DatabaseMock.dsHopDong) {
            // Chỉ xét hợp đồng có ngày hết hạn
            if (hd.getNgayHetHan() != null) {
                LocalDate ngayHetHan = hd.getNgayHetHan();

                // Kiểm tra nếu ngày hết hạn nằm từ mocThoiGian đến 30 ngày sau
                if (!ngayHetHan.isBefore(mocThoiGian) && !ngayHetHan.isAfter(moc30NgaySau)) {
                    System.out.println("Số HĐ: " + hd.getSoHopDong()
                            + " | Mã NV: " + hd.getMaNhanVien()
                            + " | Loại: " + hd.getLoaiHopDong()
                            + " | Ngày hết hạn: " + ngayHetHan);
                }
            }
        }
    }

    // =========================================================================
    // CÂU 3: Bảng chấm công cá nhân theo tháng/năm
    // =========================================================================
    public void inBangChamCongCaNhan(int maNhanVien, int thang, int nam) {
        System.out.println("=== BẢNG CHẤM CÔNG NHÂN VIÊN " + maNhanVien + " (THÁNG " + thang + "/" + nam + ") ===");
        boolean timThay = false;

        for (ChamCong cc : DatabaseMock.dsChamCong) {
            // Lọc đúng mã nhân viên, tháng và năm
            if (cc.getMaNhanVien() == maNhanVien
                    && cc.getNgayLamViec().getMonthValue() == thang
                    && cc.getNgayLamViec().getYear() == nam) {

                timThay = true;

                // 1. Tìm tên ca làm việc tương ứng từ maCa
                String tenCa = "Ca " + cc.getMaCa();
                for (CaLamViec ca : DatabaseMock.dsCaLamViec) {
                    if (ca.getMaCa() == cc.getMaCa()) {
                        tenCa = ca.getTenCa();
                        break;
                    }
                }

                // 2. Tính số giờ làm thực tế từ giờ vào và giờ ra
                double soGioLam = 0.0;
                if (cc.getGioVaoThucTe() != null && cc.getGioRaThucTe() != null) {
                    long soPhut = Duration.between(cc.getGioVaoThucTe(), cc.getGioRaThucTe()).toMinutes();
                    soGioLam = soPhut / 60.0;
                }

                // 3. In ra từng ngày chấm công theo yêu cầu đề bài
                System.out.println("- Ngày: " + cc.getNgayLamViec()
                        + " | Ca: " + tenCa
                        + " | Vào: " + cc.getGioVaoThucTe()
                        + " | Ra: " + cc.getGioRaThucTe()
                        + " | Số giờ làm: " + String.format("%.1f giờ", soGioLam)
                        + " | Trạng thái: " + cc.getTrangThaiCong());
            }
        }

        if (!timThay) {
            System.out.println("-> Không tìm thấy dữ liệu chấm công của nhân viên " + maNhanVien + " trong tháng "
                    + thang + "/" + nam);
        }
    }

}
