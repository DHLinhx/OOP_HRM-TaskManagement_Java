package com.hrmis.data;

import com.hrmis.enums.*;
import com.hrmis.models.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DatabaseMock {
    public static List<PhongBan> dsPhongBan = new ArrayList<>();
    public static List<ChucVu> dsChucVu = new ArrayList<>();
    public static List<NhanVien> dsNhanVien = new ArrayList<>();
    public static List<NhomNhanVien> dsNhomNhanVien = new ArrayList<>();
    public static List<ThanhVienNhom> dsThanhVienNhom = new ArrayList<>();
    public static List<HopDong> dsHopDong = new ArrayList<>();
    public static List<DieuKhoanHopDong> dsDieuKhoanHopDong = new ArrayList<>();
    public static List<CaLamViec> dsCaLamViec = new ArrayList<>();
    public static List<ChamCong> dsChamCong = new ArrayList<>();
    public static List<DonXinNghi> dsDonXinNghi = new ArrayList<>();
    public static List<BangLuong> dsBangLuong = new ArrayList<>();
    public static List<ChiTietBangLuong> dsChiTietBangLuong = new ArrayList<>();
    public static List<DanhGiaHieuSuat> dsDanhGiaHieuSuat = new ArrayList<>();
    public static List<ChiTietDanhGia> dsChiTietDanhGia = new ArrayList<>();

    static {
        // ==========================================
        // 1. PHÒNG BAN & CHỨC VỤ
        // ==========================================
        dsChucVu.add(new ChucVu(1, "Giam doc Ky thuat"));
        dsChucVu.add(new ChucVu(2, "Truong phong Kinh doanh"));
        dsChucVu.add(new ChucVu(3, "Lap trinh vien Senior"));
        dsChucVu.add(new ChucVu(4, "Lap trinh vien Junior"));
        dsChucVu.add(new ChucVu(5, "Chuyen vien Nhan su"));
        dsChucVu.add(new ChucVu(6, "Thuc tap sinh"));
        dsChucVu.add(new ChucVu(7, "Truong phong Nhan su")); // Bổ sung để gán cho NV5
        dsChucVu.add(new ChucVu(8, "Chuyen vien Sales"));

        // Gán đúng mã trưởng phòng tương ứng với danh sách Nhân viên bên dưới
        dsPhongBan.add(new PhongBan(1, "Phong CNTT", 1, "Tang 4 - Toa A"));
        dsPhongBan.add(new PhongBan(2, "Phong Kinh doanh", 3, "Tang 3 - Toa A"));
        dsPhongBan.add(new PhongBan(3, "Phong Nhan su", 5, "Tang 2 - Toa B"));

        // ==========================================
        // 2. NHÂN VIÊN (Tổng: 10 người để test TOP 5)
        // ==========================================
        // Sếp lớn (Không có người quản lý = null)
        dsNhanVien.add(new NhanVien(1, "Nguyen Van", "An", "an.nguyen@company.com", "0901234567", 
                LocalDate.of(1988, 3, 15), GioiTinh.NAM, LocalDate.of(2018, 1, 10), TrangThaiLamViec.DANG_LAM_VIEC, 1, 1, null));
        dsNhanVien.add(new NhanVien(3, "Le Hoang", "Cuong", "cuong.le@company.com", "0987654321", 
                LocalDate.of(1985, 11, 5), GioiTinh.NAM, LocalDate.of(2017, 3, 1), TrangThaiLamViec.DANG_LAM_VIEC, 2, 2, 1));
        dsNhanVien.add(new NhanVien(5, "Vu Thi", "Hoa", "hoa.vu@company.com", "0977889900", 
                LocalDate.of(1990, 12, 30), GioiTinh.NU, LocalDate.of(2019, 10, 1), TrangThaiLamViec.DANG_LAM_VIEC, 3, 7, 1));
        
        // Nhân viên cấp dưới (Có ma_quan_ly trỏ về các sếp)
        dsNhanVien.add(new NhanVien(2, "Tran Thi", "Binh", "binh.tran@company.com", "0912345678", 
                LocalDate.of(1992, 7, 22), GioiTinh.NU, LocalDate.of(2019, 5, 20), TrangThaiLamViec.DANG_LAM_VIEC, 1, 3, 1));
        dsNhanVien.add(new NhanVien(4, "Pham Minh", "Duc", "duc.pham@company.com", "0933445566", 
                LocalDate.of(1996, 9, 12), GioiTinh.NAM, LocalDate.of(2021, 8, 15), TrangThaiLamViec.DANG_LAM_VIEC, 1, 4, 2));
        dsNhanVien.add(new NhanVien(6, "Dang Quoc", "Khoa", "khoa.dang@company.com", "0944556677", 
                LocalDate.of(2001, 2, 18), GioiTinh.NAM, LocalDate.of(2023, 6, 1), TrangThaiLamViec.DANG_LAM_VIEC, 1, 6, 2));
        dsNhanVien.add(new NhanVien(7, "Ngo Thanh", "Giang", "giang.ngo@company.com", "0955667788", 
                LocalDate.of(1995, 4, 10), GioiTinh.NU, LocalDate.of(2020, 2, 15), TrangThaiLamViec.DANG_LAM_VIEC, 1, 3, 2));
        dsNhanVien.add(new NhanVien(8, "Bui Van", "Hieu", "hieu.bui@company.com", "0966778899", 
                LocalDate.of(1997, 8, 25), GioiTinh.NAM, LocalDate.of(2022, 1, 10), TrangThaiLamViec.DANG_LAM_VIEC, 2, 8, 3));
        dsNhanVien.add(new NhanVien(9, "Dinh Thi", "Linh", "linh.dinh@company.com", "0999888777", 
                LocalDate.of(1998, 1, 5), GioiTinh.NU, LocalDate.of(2021, 11, 20), TrangThaiLamViec.DANG_LAM_VIEC, 3, 5, 5));
        dsNhanVien.add(new NhanVien(10, "Hoang Anh", "Tuan", "tuan.hoang@company.com", "0911223344", 
                LocalDate.of(1999, 5, 20), GioiTinh.NAM, LocalDate.of(2023, 1, 5), TrangThaiLamViec.DANG_LAM_VIEC, 2, 8, 3));

        // ==========================================
        // 3. NHÓM & THÀNH VIÊN NHÓM
        // ==========================================
        dsNhomNhanVien.add(new NhomNhanVien(1, "Doi Du an Mobile App", 2, LocalDate.of(2024, 1, 15), "Lam ung dung Mobile"));
        dsNhomNhanVien.add(new NhomNhanVien(2, "Doi Nghien cuu AI", 1, LocalDate.of(2023, 11, 1), "Nghien cuu mo hinh AI"));

        dsThanhVienNhom.add(new ThanhVienNhom(1, 1, 2, LocalDate.of(2024, 1, 15), "Tech Lead"));
        dsThanhVienNhom.add(new ThanhVienNhom(2, 1, 4, LocalDate.of(2024, 2, 1), "Mobile Dev"));
        dsThanhVienNhom.add(new ThanhVienNhom(3, 2, 1, LocalDate.of(2023, 11, 1), "Truong nhom"));
        dsThanhVienNhom.add(new ThanhVienNhom(4, 2, 2, LocalDate.of(2023, 11, 10), "Chuyen gia AI"));
        dsThanhVienNhom.add(new ThanhVienNhom(5, 1, 7, LocalDate.of(2024, 3, 1), "Tester")); // NV7 tham gia nhóm 1

        // ==========================================
        // 4. HỢP ĐỒNG & ĐIỀU KHOẢN (Đã sửa lỗi dùng Enum)
        // ==========================================
        // Hợp đồng cũ đã hết hạn
        dsHopDong.add(new HopDong(1, 2, "HD-2019-001", LoaiHopDong.THU_VIEC, LocalDate.of(2019, 5, 20), LocalDate.of(2019, 7, 20), 8000000, TrangThaiHopDong.DA_HET_HAN));
        // Các hợp đồng đang hiệu lực
        dsHopDong.add(new HopDong(2, 1, "HD-2018-005", LoaiHopDong.VO_THOI_HAN, LocalDate.of(2018, 1, 10), null, 45000000, TrangThaiHopDong.DANG_HIEU_LUC));
        dsHopDong.add(new HopDong(3, 2, "HD-2022-045", LoaiHopDong.XAC_DINH_THOI_HAN, LocalDate.of(2022, 5, 20), LocalDate.of(2025, 5, 20), 30000000, TrangThaiHopDong.DANG_HIEU_LUC));
        // Hợp đồng sắp hết hạn (Đáp ứng câu hỏi Cảnh báo)
        dsHopDong.add(new HopDong(4, 4, "HD-2023-088", LoaiHopDong.XAC_DINH_THOI_HAN, LocalDate.of(2023, 8, 15), LocalDate.now().plusDays(20), 16000000, TrangThaiHopDong.SAP_HET_HAN));
        dsHopDong.add(new HopDong(5, 6, "HD-2024-012", LoaiHopDong.THU_VIEC, LocalDate.of(2024, 3, 1), LocalDate.now().plusDays(10), 5000000, TrangThaiHopDong.SAP_HET_HAN));

        dsDieuKhoanHopDong.add(new DieuKhoanHopDong(1, 3, 1, "Thoi gian lam viec", "T2 - T6, 8h00 - 17h30", "Quy dinh"));
        dsDieuKhoanHopDong.add(new DieuKhoanHopDong(2, 3, 2, "Bao mat thong tin", "Khong tiet lo ma nguon ra ngoai", "Bảo mật (NDA)"));
        dsDieuKhoanHopDong.add(new DieuKhoanHopDong(3, 3, 3, "Ky luat cong ty", "Tuan thu dung noi quy lao dong", "Kỷ luật"));

        // ==========================================
        // 5. CA LÀM VIỆC & CHẤM CÔNG (Đã sửa lỗi dùng Enum & Thêm OT)
        // ==========================================
        dsCaLamViec.add(new CaLamViec(1, "Ca hanh chinh", LocalTime.of(8, 0), LocalTime.of(17, 30)));

        dsChamCong.add(new ChamCong(1, 1, 1, LocalDate.of(2024, 3, 1), LocalTime.of(7, 55), LocalTime.of(17, 35), 0.0, TrangThaiCong.DUNG_GIO));
        dsChamCong.add(new ChamCong(2, 1, 1, LocalDate.of(2024, 3, 2), LocalTime.of(7, 50), LocalTime.of(19, 30), 2.0, TrangThaiCong.DUNG_GIO)); // Có OT
        dsChamCong.add(new ChamCong(3, 2, 1, LocalDate.of(2024, 3, 1), LocalTime.of(8, 15), LocalTime.of(17, 30), 0.0, TrangThaiCong.DI_TRE));
        dsChamCong.add(new ChamCong(4, 2, 1, LocalDate.of(2024, 3, 2), LocalTime.of(8, 20), LocalTime.of(20, 30), 3.0, TrangThaiCong.DI_TRE)); // Có OT
        dsChamCong.add(new ChamCong(5, 4, 1, LocalDate.of(2024, 3, 2), LocalTime.of(7, 58), LocalTime.of(21, 30), 4.0, TrangThaiCong.DUNG_GIO)); // Có OT
        dsChamCong.add(new ChamCong(6, 7, 1, LocalDate.of(2024, 3, 2), LocalTime.of(7, 59), LocalTime.of(19, 00), 1.5, TrangThaiCong.DUNG_GIO)); // Thêm OT cho NV7
        dsChamCong.add(new ChamCong(7, 8, 1, LocalDate.of(2024, 3, 2), LocalTime.of(8, 00), LocalTime.of(18, 30), 1.0, TrangThaiCong.DUNG_GIO)); // Thêm OT cho NV8

        // ==========================================
        // 6. ĐƠN XIN NGHỈ (Đã sửa lỗi dùng Enum)
        // ==========================================
        dsDonXinNghi.add(new DonXinNghi(1, 2, LoaiNghi.NGHI_PHEP_NAM, LocalDate.of(2024, 3, 10), LocalDate.of(2024, 3, 11), "Viec gia dinh", TrangThaiDuyet.CHO_DUYET, 1));
        dsDonXinNghi.add(new DonXinNghi(2, 4, LoaiNghi.NGHI_OM, LocalDate.of(2024, 3, 5), LocalDate.of(2024, 3, 5), "Kham benh", TrangThaiDuyet.DA_DUYET, 2));

        // ==========================================
        // 7. BẢNG LƯƠNG & CHI TIẾT (Đã sửa lỗi dùng Enum)
        // ==========================================
        dsBangLuong.add(new BangLuong(1, 1, 3, 2024, 49000000, 4650000, 44350000, TrangThaiChiTra.DA_THANH_TOAN, LocalDate.of(2024, 4, 5)));
        dsBangLuong.add(new BangLuong(2, 2, 3, 2024, 33500000, 3200000, 30300000, TrangThaiChiTra.DA_THANH_TOAN, LocalDate.of(2024, 4, 5)));
        dsBangLuong.add(new BangLuong(3, 4, 3, 2024, 18200000, 1700000, 16500000, TrangThaiChiTra.CHO_DUYET, null));

        dsChiTietBangLuong.add(new ChiTietBangLuong(1, 2, "Luong co ban", LoaiKhoanMuc.CONG, 30000000, "22 ngay cong"));
        dsChiTietBangLuong.add(new ChiTietBangLuong(2, 2, "Tien OT lam them", LoaiKhoanMuc.CONG, 2000000, "3 gio OT"));
        dsChiTietBangLuong.add(new ChiTietBangLuong(3, 2, "Phu cap an trua", LoaiKhoanMuc.CONG, 1500000, "Thang 3"));
        dsChiTietBangLuong.add(new ChiTietBangLuong(4, 2, "Bao hiem xa hoi (10.5%)", LoaiKhoanMuc.TRU, 3150000, "Trich nop BH"));
        dsChiTietBangLuong.add(new ChiTietBangLuong(5, 2, "Phat di tre", LoaiKhoanMuc.TRU, 50000, "Di tre 2 lan"));

        // ==========================================
        // 8. ĐÁNH GIÁ HIỆU SUẤT (Bổ sung NV bị YẾU)
        // ==========================================
        dsDanhGiaHieuSuat.add(new DanhGiaHieuSuat(1, 1, 1, "Quy 1", 2024, 4.8, XepLoai.XUAT_SAC));
        dsDanhGiaHieuSuat.add(new DanhGiaHieuSuat(2, 2, 1, "Quy 1", 2024, 4.2, XepLoai.TOT));
        dsDanhGiaHieuSuat.add(new DanhGiaHieuSuat(3, 4, 2, "Quy 1", 2024, 3.1, XepLoai.TRUNG_BINH));
        dsDanhGiaHieuSuat.add(new DanhGiaHieuSuat(4, 6, 2, "Quy 1", 2024, 1.5, XepLoai.YEU)); // Bổ sung để test CÂU 8

        dsChiTietDanhGia.add(new ChiTietDanhGia(1, 1, "Ky nang quan ly du an", 0.5, 5.0, "Dung tien do vuot chi tieu"));
        dsChiTietDanhGia.add(new ChiTietDanhGia(2, 1, "Chuyen can dung gio", 0.5, 4.6, "Guong mau dung gio 100%"));
        dsChiTietDanhGia.add(new ChiTietDanhGia(3, 4, "Chuyen can dung gio", 0.5, 2.0, "Hay di tre, can cai thien"));
    }
}