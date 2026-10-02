package com.hrmis.services;

import com.hrmis.data.DatabaseMock;
import com.hrmis.enums.*;
import com.hrmis.models.*;

import java.text.NumberFormat;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TaiChinhService {

    private static final NumberFormat TIEN = NumberFormat.getInstance(Locale.forLanguageTag("vi-VN"));
    private static final String LINE = "-".repeat(78);
    private static String tien(double v) {
        return TIEN.format(Math.round(v));
    }
    private static String chuanHoa(String s) {
        if (s == null) return "";
        String r = Normalizer.normalize(s, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .replace('đ', 'd').replace('Đ', 'D');
        return r.toLowerCase();
    }

    /** Model NhanVien của nhóm tách họ đệm (getHo) và tên (getTen). */
    private static String hoTen(NhanVien nv) {
        return nv.getHo() + " " + nv.getTen();
    }

    private static NhanVien timNhanVien(int maNV) {
        for (NhanVien nv : DatabaseMock.dsNhanVien) {
            if (nv.getMaNhanVien() == maNV) return nv;
        }
        return null;
    }

    private static String tenNV(int maNV) {
        NhanVien nv = timNhanVien(maNV);
        return nv == null ? "(không rõ #" + maNV + ")" : hoTen(nv);
    }

    private static String tenPhongBan(int maPB) {
        for (PhongBan pb : DatabaseMock.dsPhongBan) {
            if (pb.getmaPhongBan() == maPB) return pb.gettenPhongBan();
        }
        return "(không rõ)";
    }

    private static String tenChucVu(int maCV) {
        for (ChucVu cv : DatabaseMock.dsChucVu) {
            if (cv.getmaChucVu() == maCV) return cv.gettenChucVu();
        }
        return "(không rõ)";
    }

    private static List<ChiTietBangLuong> chiTietCua(BangLuong bl) {
        return DatabaseMock.dsChiTietBangLuong.stream()
                .filter(ct -> ct.getMaBangLuong() == bl.getMaBangLuong())
                .collect(Collectors.toList());
    }

    private static List<BangLuong> bangLuongTheoKy(int thang, int nam) {
        return DatabaseMock.dsBangLuong.stream()
                .filter(bl -> bl.getKyThang() == thang && bl.getKyNam() == nam)
                .collect(Collectors.toList());
    }

    private static void tieuDe(String s) {
        System.out.println();
        System.out.println("=".repeat(78));
        System.out.println(s);
        System.out.println("=".repeat(78));
    }

    private static boolean khongCoDuLieu(Collection<?> c, String thongBao) {
        if (c.isEmpty()) {
            System.out.println(thongBao);
            return true;
        }
        return false;
    }

 
    // CÂU 1: In phiếu lương cá nhân (Payslip)
    
    public void inPhieuLuong(int maNV, int thang, int nam) {
        NhanVien nv = timNhanVien(maNV);
        if (nv == null) {
            System.out.println("Không tìm thấy nhân viên có mã " + maNV);
            return;
        }
        BangLuong bl = DatabaseMock.dsBangLuong.stream()
                .filter(b -> b.getMaNhanVien() == maNV && b.getKyThang() == thang && b.getKyNam() == nam)
                .findFirst().orElse(null);
        if (bl == null) {
            System.out.printf("Nhân viên %s chưa có bảng lương kỳ %02d/%d.%n", hoTen(nv), thang, nam);
            return;
        }

        List<ChiTietBangLuong> ct = chiTietCua(bl);
        tieuDe(String.format("PHIẾU LƯƠNG THÁNG %02d/%d", thang, nam));
        System.out.printf("Nhân viên : %s (Mã %d)%n", hoTen(nv), nv.getMaNhanVien());
        System.out.printf("Phòng ban : %s%n", tenPhongBan(nv.getMaPhongBan()));
        System.out.printf("Chức vụ   : %s%n", tenChucVu(nv.getMaChucVu()));
        System.out.println(LINE);

        if (ct.isEmpty()) {
            System.out.println("(Bảng lương này chưa có dòng chi tiết hạch toán)");
        } else {
            double luongCoBan = 0;
            List<ChiTietBangLuong> khoanCong = new ArrayList<>();
            List<ChiTietBangLuong> khoanTru = new ArrayList<>();
            for (ChiTietBangLuong c : ct) {
                if (c.getLoaiKhoanMuc() == LoaiKhoanMuc.TRU) {
                    khoanTru.add(c);
                } else if (chuanHoa(c.getTenKhoanMuc()).contains("luong co ban")) {
                    luongCoBan += c.getSoTien();
                } else {
                    khoanCong.add(c);
                }
            }
            System.out.printf("%-40s %20s%n", "Lương cơ bản", tien(luongCoBan));
            System.out.println("\nCác khoản cộng (phụ cấp, thưởng, OT):");
            if (khoanCong.isEmpty()) System.out.println("  (không có)");
            for (ChiTietBangLuong c : khoanCong) {
                System.out.printf("  + %-37s %20s   %s%n", c.getTenKhoanMuc(), tien(c.getSoTien()),
                        c.getGhiChu() == null ? "" : "(" + c.getGhiChu() + ")");
            }
            System.out.println("\nCác khoản trừ (BHXH, thuế, phạt trễ):");
            if (khoanTru.isEmpty()) System.out.println("  (không có)");
            for (ChiTietBangLuong c : khoanTru) {
                System.out.printf("  - %-37s %20s   %s%n", c.getTenKhoanMuc(), tien(c.getSoTien()),
                        c.getGhiChu() == null ? "" : "(" + c.getGhiChu() + ")");
            }
        }

        System.out.println(LINE);
        System.out.printf("%-40s %20s%n", "Tổng thu nhập", tien(bl.getTongThuNhap()));
        System.out.printf("%-40s %20s%n", "Tổng khấu trừ", tien(bl.getTongKhauTru()));
        System.out.printf("%-40s %20s VND%n", "THỰC LÃNH", tien(bl.getThucLanh()));
        System.out.printf("Trạng thái chi trả: %s%s%n", bl.getTrangThaiChiTra(),
                bl.getNgayChiTra() == null ? "" : " (ngày " + bl.getNgayChiTra() + ")");

        // Cảnh báo nếu chi tiết không khớp với tổng đã lưu
        if (!ct.isEmpty()) {
            double cong = ct.stream().filter(c -> c.getLoaiKhoanMuc() == LoaiKhoanMuc.CONG)
                    .mapToDouble(ChiTietBangLuong::getSoTien).sum();
            double tru = ct.stream().filter(c -> c.getLoaiKhoanMuc() == LoaiKhoanMuc.TRU)
                    .mapToDouble(ChiTietBangLuong::getSoTien).sum();
            if (Math.abs(cong - bl.getTongThuNhap()) > 1 || Math.abs(tru - bl.getTongKhauTru()) > 1) {
                System.out.println("[Cảnh báo] Tổng các dòng chi tiết không khớp với tổng thu nhập/khấu trừ đã lưu.");
            }
        }
    }

   
    // CÂU 2: Bảng tổng hợp chi phí lương tháng
   
    public void bangTongHopLuongThang(int thang, int nam) {
        tieuDe(String.format("BẢNG TỔNG HỢP LƯƠNG THÁNG %02d/%d", thang, nam));
        List<BangLuong> ds = bangLuongTheoKy(thang, nam);
        if (khongCoDuLieu(ds, "Chưa có bảng lương nào trong kỳ này.")) return;

        System.out.printf("%-6s %-22s %15s %15s %15s%n", "Mã NV", "Tên NV", "Tổng thu nhập", "Tổng khấu trừ", "Thực lãnh");
        System.out.println(LINE);
        double tThuNhap = 0, tKhauTru = 0, tThucLanh = 0;
        for (BangLuong bl : ds) {
            System.out.printf("%-6d %-22s %15s %15s %15s%n", bl.getMaNhanVien(), tenNV(bl.getMaNhanVien()),
                    tien(bl.getTongThuNhap()), tien(bl.getTongKhauTru()), tien(bl.getThucLanh()));
            tThuNhap += bl.getTongThuNhap();
            tKhauTru += bl.getTongKhauTru();
            tThucLanh += bl.getThucLanh();
        }
        System.out.println(LINE);
        System.out.printf("%-29s %15s %15s %15s%n", "TỔNG CỘNG (" + ds.size() + " nhân viên)",
                tien(tThuNhap), tien(tKhauTru), tien(tThucLanh));
    }

   
    // CÂU 3: Thống kê quỹ lương theo phòng ban
   
    public void quyLuongTheoPhongBan(int thang, int nam) {
        tieuDe(String.format("QUỸ LƯƠNG THỰC LÃNH THEO PHÒNG BAN - THÁNG %02d/%d", thang, nam));
        List<BangLuong> ds = bangLuongTheoKy(thang, nam);
        if (khongCoDuLieu(ds, "Chưa có bảng lương nào trong kỳ này.")) return;

        Map<Integer, Double> quy = new LinkedHashMap<>();
        Map<Integer, Integer> soNV = new HashMap<>();
        for (PhongBan pb : DatabaseMock.dsPhongBan) quy.put(pb.getmaPhongBan(), 0.0);

        for (BangLuong bl : ds) {
            NhanVien nv = timNhanVien(bl.getMaNhanVien());
            if (nv == null) continue;
            quy.merge(nv.getMaPhongBan(), bl.getThucLanh(), Double::sum);
            soNV.merge(nv.getMaPhongBan(), 1, Integer::sum);
        }

        System.out.printf("%-6s %-24s %10s %22s%n", "Mã PB", "Phòng ban", "Số NV", "Quỹ lương thực lãnh");
        System.out.println(LINE);
        double tong = 0;
        for (Map.Entry<Integer, Double> e : quy.entrySet()) {
            System.out.printf("%-6d %-24s %10d %22s%n", e.getKey(), tenPhongBan(e.getKey()),
                    soNV.getOrDefault(e.getKey(), 0), tien(e.getValue()));
            tong += e.getValue();
        }
        System.out.println(LINE);
        System.out.printf("%-31s %10s %22s%n", "TỔNG CÔNG TY", "", tien(tong));
    }

   
    // CÂU 4: Top 3 thu nhập thực lãnh cao nhất
    
    public void topThuNhapCaoNhat(int thang, int nam) {
        tieuDe(String.format("TOP 3 THU NHẬP THỰC LÃNH CAO NHẤT - THÁNG %02d/%d", thang, nam));
        List<BangLuong> ds = bangLuongTheoKy(thang, nam);
        if (khongCoDuLieu(ds, "Chưa có bảng lương nào trong kỳ này.")) return;

        List<BangLuong> top = ds.stream()
                .sorted(Comparator.comparingDouble(BangLuong::getThucLanh).reversed())
                .limit(3).collect(Collectors.toList());

        System.out.printf("%-5s %-6s %-22s %-24s %15s%n", "Hạng", "Mã NV", "Tên NV", "Chức vụ", "Thực lãnh");
        System.out.println(LINE);
        int hang = 1;
        for (BangLuong bl : top) {
            NhanVien nv = timNhanVien(bl.getMaNhanVien());
            System.out.printf("%-5d %-6d %-22s %-24s %15s%n", hang++, bl.getMaNhanVien(), tenNV(bl.getMaNhanVien()),
                    nv == null ? "" : tenChucVu(nv.getMaChucVu()), tien(bl.getThucLanh()));
        }
    }

   
    // CÂU 5: Chi tiết khấu trừ bảo hiểm (BHXH, BHYT, BHTN)
   
    public void thongKeBaoHiem(int thang, int nam) {
        tieuDe(String.format("THỐNG KÊ BẢO HIỂM BẮT BUỘC ĐÃ TRÍCH - THÁNG %02d/%d", thang, nam));
        List<BangLuong> ds = bangLuongTheoKy(thang, nam);
        if (khongCoDuLieu(ds, "Chưa có bảng lương nào trong kỳ này.")) return;

        System.out.printf("%-6s %-22s %12s %12s %12s %13s%n", "Mã NV", "Tên NV", "BHXH", "BHYT", "BHTN", "Tổng");
        System.out.println(LINE);
        double sXH = 0, sYT = 0, sTN = 0;
        for (BangLuong bl : ds) {
            double xh = 0, yt = 0, tn = 0;
            for (ChiTietBangLuong c : chiTietCua(bl)) {
                if (c.getLoaiKhoanMuc() != LoaiKhoanMuc.TRU) continue;
                String ten = chuanHoa(c.getTenKhoanMuc());
                if (!ten.contains("bao hiem")) continue;
                if (ten.contains("that nghiep")) tn += c.getSoTien();
                else if (ten.contains("y te")) yt += c.getSoTien();
                else xh += c.getSoTien(); // "bao hiem xa hoi" (kể cả dòng gộp 10.5%)
            }
            System.out.printf("%-6d %-22s %12s %12s %12s %13s%n", bl.getMaNhanVien(), tenNV(bl.getMaNhanVien()),
                    tien(xh), tien(yt), tien(tn), tien(xh + yt + tn));
            sXH += xh; sYT += yt; sTN += tn;
        }
        System.out.println(LINE);
        System.out.printf("%-29s %12s %12s %12s %13s%n", "TỔNG CỘNG", tien(sXH), tien(sYT), tien(sTN), tien(sXH + sYT + sTN));
    }

   
    // CÂU 6: Báo cáo thưởng và phụ cấp
   
    public void baoCaoThuongPhuCap(int thang, int nam) {
        tieuDe(String.format("BÁO CÁO THƯỞNG & PHỤ CẤP - THÁNG %02d/%d", thang, nam));
        List<BangLuong> ds = bangLuongTheoKy(thang, nam);
        if (khongCoDuLieu(ds, "Chưa có bảng lương nào trong kỳ này.")) return;

        System.out.printf("%-6s %-20s %-26s %13s  %s%n", "Mã NV", "Tên NV", "Khoản mục", "Số tiền", "Lý do chi");
        System.out.println(LINE);
        double tong = 0;
        int dem = 0;
        for (BangLuong bl : ds) {
            for (ChiTietBangLuong c : chiTietCua(bl)) {
                if (c.getLoaiKhoanMuc() != LoaiKhoanMuc.CONG) continue;
                String ten = chuanHoa(c.getTenKhoanMuc());
                if (!ten.contains("phu cap") && !ten.contains("thuong")) continue;
                System.out.printf("%-6d %-20s %-26s %13s  %s%n", bl.getMaNhanVien(), tenNV(bl.getMaNhanVien()),
                        c.getTenKhoanMuc(), tien(c.getSoTien()), c.getGhiChu() == null ? "" : c.getGhiChu());
                tong += c.getSoTien();
                dem++;
            }
        }
        if (dem == 0) {
            System.out.println("Không có khoản thưởng/phụ cấp nào trong kỳ này.");
            return;
        }
        System.out.println(LINE);
        System.out.printf("TỔNG %d khoản: %s VND%n", dem, tien(tong));
    }

   
    // CÂU 7: Bản in phiếu đánh giá KPI chi tiết
    
    public void inPhieuKPI(int maDanhGia) {
        DanhGiaHieuSuat dg = DatabaseMock.dsDanhGiaHieuSuat.stream()
                .filter(d -> d.getMaDanhGia() == maDanhGia).findFirst().orElse(null);
        if (dg == null) {
            System.out.println("Không tìm thấy phiếu đánh giá có mã " + maDanhGia);
            return;
        }
        NhanVien nv = timNhanVien(dg.getMaNhanVien());

        tieuDe("PHIẾU ĐÁNH GIÁ HIỆU SUẤT (KPI) - Mã " + dg.getMaDanhGia());
        System.out.printf("Nhân viên được đánh giá: %s (Mã %d)%n", tenNV(dg.getMaNhanVien()), dg.getMaNhanVien());
        if (nv != null) {
            System.out.printf("Phòng ban / Chức vụ    : %s / %s%n", tenPhongBan(nv.getMaPhongBan()), tenChucVu(nv.getMaChucVu()));
        }
        System.out.printf("Người đánh giá         : %s (Mã %d)%n", tenNV(dg.getNguoiDanhGia()), dg.getNguoiDanhGia());
        System.out.printf("Kỳ đánh giá            : %s / %d%n", dg.getKyDanhGia(), dg.getNam());
        System.out.printf("Điểm tổng              : %.2f%n", dg.getDiemTrungBinh());
        System.out.printf("Xếp loại               : %s%n", dg.getXepLoai());
        System.out.println(LINE);

        List<ChiTietDanhGia> ct = DatabaseMock.dsChiTietDanhGia.stream()
                .filter(c -> c.getMaDanhGia() == maDanhGia).collect(Collectors.toList());
        if (khongCoDuLieu(ct, "(Phiếu này chưa có chi tiết từng tiêu chí)")) return;

        System.out.printf("%-30s %9s %8s  %s%n", "Tiêu chí", "Trọng số", "Điểm", "Nhận xét");
        System.out.println(LINE);
        for (ChiTietDanhGia c : ct) {
            System.out.printf("%-30s %9.2f %8.2f  %s%n", c.getTieuChiDanhGia(), c.getTrongSo(), c.getDiemSo(),
                    c.getNhanXetChiTiet() == null ? "" : c.getNhanXetChiTiet());
        }
    }

   
    // CÂU 8: Xếp hạng hiệu suất nhân viên theo xếp loại trong một kỳ
   
    public void xepHangHieuSuat(String kyDanhGia, int nam) {
        tieuDe(String.format("XẾP HẠNG HIỆU SUẤT - %s / %d", kyDanhGia, nam));
        String kyChuan = chuanHoa(kyDanhGia).trim();
        List<DanhGiaHieuSuat> ds = DatabaseMock.dsDanhGiaHieuSuat.stream()
                .filter(d -> d.getNam() == nam && chuanHoa(d.getKyDanhGia()).trim().equals(kyChuan))
                .collect(Collectors.toList());
        if (khongCoDuLieu(ds, "Chưa có đánh giá nào trong kỳ này.")) return;

        for (XepLoai xl : XepLoai.values()) {
            List<DanhGiaHieuSuat> nhom = ds.stream().filter(d -> d.getXepLoai() == xl)
                    .sorted(Comparator.comparingDouble(DanhGiaHieuSuat::getDiemTrungBinh).reversed())
                    .collect(Collectors.toList());
            System.out.printf("%n>> %s (%d nhân viên)%n", xl, nhom.size());
            if (nhom.isEmpty()) {
                System.out.println("   (không có)");
                continue;
            }
            for (DanhGiaHieuSuat d : nhom) {
                System.out.printf("   %-6d %-22s %-24s %6.2f%n", d.getMaNhanVien(), tenNV(d.getMaNhanVien()),
                        tenChucVuCuaNV(d.getMaNhanVien()), d.getDiemTrungBinh());
            }
        }
    }

    private String tenChucVuCuaNV(int maNV) {
        NhanVien nv = timNhanVien(maNV);
        return nv == null ? "" : tenChucVu(nv.getMaChucVu());
    }

   
    // CÂU 9: Nhân sự xuất sắc + không nghỉ ngày nào trong quý
    // Quy ước: "không nghỉ ngày nào" = không có đơn nghỉ ĐÃ DUYỆT giao với quý
    // và không có bản ghi chấm công VẮNG MẶT (có phép / không phép) trong quý.
   
    public void timNhanSuXuatSac(int quy, int nam) {
        tieuDe(String.format("NHÂN SỰ XUẤT SẮC & KHÔNG NGHỈ NGÀY NÀO - QUÝ %d/%d", quy, nam));
        if (quy < 1 || quy > 4) {
            System.out.println("Quý phải từ 1 đến 4.");
            return;
        }
        LocalDate dau = LocalDate.of(nam, (quy - 1) * 3 + 1, 1);
        LocalDate cuoi = dau.plusMonths(3).minusDays(1);
        String kyChuan = chuanHoa("Quy " + quy);

        List<DanhGiaHieuSuat> xuatSac = DatabaseMock.dsDanhGiaHieuSuat.stream()
                .filter(d -> d.getNam() == nam && chuanHoa(d.getKyDanhGia()).trim().equals(kyChuan)
                        && d.getXepLoai() == XepLoai.XUAT_SAC)
                .collect(Collectors.toList());

        int dem = 0;
        for (DanhGiaHieuSuat d : xuatSac) {
            int maNV = d.getMaNhanVien();
            boolean coNghiPhep = DatabaseMock.dsDonXinNghi.stream().anyMatch(n ->
                    n.getMaNhanVien() == maNV && n.getTrangThaiDuyet() == TrangThaiDuyet.DA_DUYET
                            && !n.getNgayBatDau().isAfter(cuoi) && !n.getNgayKetThuc().isBefore(dau));
            boolean coVang = DatabaseMock.dsChamCong.stream().anyMatch(c ->
                    c.getMaNhanVien() == maNV && !c.getNgayLamViec().isBefore(dau) && !c.getNgayLamViec().isAfter(cuoi)
                            && (c.getTrangThaiCong() == TrangThaiCong.VANG_MAT_CO_PHEP
                            || c.getTrangThaiCong() == TrangThaiCong.VANG_MAT_KHONG_PHEP));
            if (coNghiPhep || coVang) continue;
            if (dem == 0) {
                System.out.printf("%-6s %-22s %-24s %8s%n", "Mã NV", "Tên NV", "Chức vụ", "Điểm");
                System.out.println(LINE);
            }
            System.out.printf("%-6d %-22s %-24s %8.2f%n", maNV, tenNV(maNV), tenChucVuCuaNV(maNV), d.getDiemTrungBinh());
            dem++;
        }
        if (dem == 0) System.out.println("Không có nhân viên nào thỏa cả hai điều kiện.");
        else System.out.println("\nTìm thấy " + dem + " nhân viên.");
    }

    
    // CÂU 10: Thu nhập trung bình theo từng chức vụ
    // (trung bình của "tổng thu nhập" trên toàn bộ các bảng lương đã có)
  
    public void thuNhapTrungBinhTheoChucVu() {
        tieuDe("THU NHẬP TRUNG BÌNH THEO CHỨC VỤ (toàn bộ bảng lương)");
        if (khongCoDuLieu(DatabaseMock.dsBangLuong, "Chưa có bảng lương nào.")) return;

        Map<Integer, List<BangLuong>> theoChucVu = new LinkedHashMap<>();
        for (BangLuong bl : DatabaseMock.dsBangLuong) {
            NhanVien nv = timNhanVien(bl.getMaNhanVien());
            if (nv == null) continue;
            theoChucVu.computeIfAbsent(nv.getMaChucVu(), k -> new ArrayList<>()).add(bl);
        }

        List<Map.Entry<Integer, List<BangLuong>>> ds = new ArrayList<>(theoChucVu.entrySet());
        ds.sort((a, b) -> Double.compare(tb(b.getValue()), tb(a.getValue())));

        System.out.printf("%-26s %10s %16s%n", "Chức vụ", "Số bảng lương", "TB thu nhập");
        System.out.println(LINE);
        for (Map.Entry<Integer, List<BangLuong>> e : ds) {
            System.out.printf("%-26s %10d %16s%n", tenChucVu(e.getKey()), e.getValue().size(), tien(tb(e.getValue())));
        }
    }

    private static double tb(List<BangLuong> ds) {
        return ds.stream().mapToDouble(BangLuong::getTongThuNhap).average().orElse(0);
    }
}