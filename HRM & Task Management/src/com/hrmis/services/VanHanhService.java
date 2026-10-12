package com.hrmis.services;

import com.hrmis.data.DatabaseMock;
import com.hrmis.enums.TrangThaiCong;
import com.hrmis.enums.TrangThaiDuyet;
import com.hrmis.models.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // =========================================================================
    // CÂU 4: Báo cáo đi trễ trong tháng (sắp xếp giảm dần theo số lần vi phạm)
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
        System.out.println("=== BÁO CÁO NHÂN VIÊN ĐI TRỄ (THÁNG " + thang + "/" + nam + ") ===");
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
                String hoTen = nv.getHo() + " " + nv.getTen();
                danhSach.add(new ThongKeDiTre(nv.getMaNhanVien(), hoTen, soLanTre));
            }
        }

        if (danhSach.isEmpty()) {
            System.out.println("-> Tuyệt vời! Không có nhân viên nào đi trễ trong tháng " + thang + "/" + nam);
            return;
        }
        danhSach.sort((a, b) -> Integer.compare(b.soLan, a.soLan));
        for (ThongKeDiTre tk : danhSach) {
            System.out.println("Mã NV: " + tk.maNV
                    + " | Họ tên: " + tk.hoTen
                    + " | Số lần đi trễ: " + tk.soLan);
        }
    }

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
        System.out.println("=== BÁO CÁO NHÂN VIÊN LÀM THÊM GIỜ (THÁNG " + thang + "/" + nam + ") ===");
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
                String hoTen = nv.getHo() + " " + nv.getTen();
                danhSach.add(new ThongKeOT(nv.getMaNhanVien(), hoTen, soGio));
            }
        }

        if (danhSach.isEmpty()) {
            System.out.println("-> Không có nhân viên nào làm thêm giờ trong tháng " + thang + "/" + nam);
            return;
        }
        danhSach.sort((a, b) -> Double.compare(b.soGio, a.soGio));
        for (ThongKeOT tk : danhSach) {
            System.out.println("Mã NV: " + tk.maNV
                    + " | Họ tên: " + tk.hoTen
                    + " | Số giờ OT: " + tk.soGio);
        }
    }

    // =========================================================================
    // CÂU 6: Bảng theo dõi đơn nghỉ phép đang CHỜ DUYỆT
    // =========================================================================
    public void theoDoiDonNghiPhep() {
        System.out.println("=== DANH SÁCH ĐƠN NGHỈ PHÉP ĐANG CHỜ DUYỆT ===");
        boolean timThay = false;

        for (DonXinNghi don : DatabaseMock.dsDonXinNghi) {
            if (don.getTrangThaiDuyet() == TrangThaiDuyet.CHO_DUYET) {
                timThay = true;

                String tenNguoiNop = "Mã NV: " + don.getMaNhanVien();
                for (NhanVien nv : DatabaseMock.dsNhanVien) {
                    if (nv.getMaNhanVien() == don.getMaNhanVien()) {
                        tenNguoiNop = nv.getHo() + " " + nv.getTen();
                        break;
                    }
                }

                String tenNguoiDuyet = "Chưa chỉ định";
                if (don.getNguoiDuyet() != null) {
                    for (NhanVien nv : DatabaseMock.dsNhanVien) {
                        if (nv.getMaNhanVien() == don.getNguoiDuyet()) {
                            tenNguoiDuyet = nv.getHo() + " " + nv.getTen();
                            break;
                        }
                    }
                }

                long soNgayNghi = don.getNgayKetThuc().toEpochDay() - don.getNgayBatDau().toEpochDay() + 1;

                System.out.println("- Mã đơn: " + don.getMaDonNghi()
                        + " | Người nộp: " + tenNguoiNop
                        + " | Loại nghỉ: " + don.getLoaiNghi()
                        + " | Số ngày: " + soNgayNghi + " ngày (" + don.getNgayBatDau() + " -> " + don.getNgayKetThuc()
                        + ")"
                        + " | Người duyệt: " + tenNguoiDuyet);
            }
        }

        if (!timThay) {
            System.out.println("-> Hiện không có đơn xin nghỉ phép nào đang chờ duyệt.");
        }
    }

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
        System.out.println("=== LỊCH SỬ NGHỈ PHÉP: " + hoTen + " (Mã NV: " + maNV + ") ===");
        boolean coDon = false;

        for (DonXinNghi don : DatabaseMock.dsDonXinNghi) {
            if (don.getMaNhanVien() == maNV) {
                coDon = true;
                long soNgayNghi = don.getNgayKetThuc().toEpochDay() - don.getNgayBatDau().toEpochDay() + 1;
                System.out.println("- Mã đơn: " + don.getMaDonNghi()
                        + " | Nhân Viên: " + hoTen
                        + " | Loại nghỉ: " + don.getLoaiNghi()
                        + " | Ngày nghỉ: " + don.getNgayBatDau() + " -> " + don.getNgayKetThuc()
                        + " | Số ngày: " + soNgayNghi + " ngày"
                        + " | Lý do: " + don.getLyDo()
                        + " | Trạng thái: " + don.getTrangThaiDuyet());
            }
        }
        if (!coDon) {
            System.out.println("-> Nhân viên " + hoTen + " chưa có đơn xin nghỉ phép nào.");
        }
    }

    // CÂU 8: Thống kê nhân viên đạt chuyên cần 100% trong tháng
    // =========================================================================
    public void thongkeChuyenCan(int thang, int nam) {
        System.out.println("=== THỐNG KÊ NHÂN VIÊN ĐẠT CHUYÊN CẦN 100% (THÁNG " + thang + "/" + nam + ") ===");
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
                System.out.println("Mã NV: " + nv.getMaNhanVien()
                        + " | Họ tên: " + nv.getHo() + " " + nv.getTen()
                        + " | Số ngày: " + dungGio);
            }
        }
    }
    
}
