# HRM & Task Management System


## Giới thiệu đề tài

- Mục tiêu hệ thống:
• Tự động hóa quá trình lưu trữ hồ sơ, quản trị mô hình nhân sự và tổ chức phòng ban/nhóm làm việc.   
• Số hóa quy trình chấm công theo ca, tính lương tự động với các khoản phụ cấp và khấu trừ chi tiết theo luật lao động.   
• Hỗ trợ ban giám đốc và bộ phận HR đưa ra quyết định nhân sự thông qua báo cáo đánh giá hiệu suất (KPI) và quản lý hợp đồng.   
- Phạm vi hệ thống: Quản lý nội bộ dành cho doanh nghiệp vừa và nhỏ (SME), thực thi xử lý nghiệp vụ trên nền tảng Console/Terminal.
```Mermaid
erDiagram
    %% --- CỤM CƠ CẤU VÀ TỔ CHỨC ---
    PHONG_BAN ||--o{ NHAN_VIEN : "[1:N] thuoc ve"
    CHUC_VU ||--o{ NHAN_VIEN : "[1:N] dam nhiem"
    NHAN_VIEN ||--o{ NHAN_VIEN : "[1:N] quan ly truc tiep"
    NHAN_VIEN o|--o{ PHONG_BAN : "[0..1:N] lam truong phong (nullable tranh circular)"

    %% --- CỤM NHÓM NHÂN VIÊN ---
    NHAN_VIEN ||--o{ NHOM_NHAN_VIEN : "[1:N] lam truong nhom"
    NHOM_NHAN_VIEN ||--o{ THANH_VIEN_NHOM : "[1:N] bao gom"
    NHAN_VIEN ||--o{ THANH_VIEN_NHOM : "[1:N] tham gia"

    %% --- CỤM HỢP ĐỒNG ---
    NHAN_VIEN ||--o{ HOP_DONG : "[1:N] ky ket"
    HOP_DONG ||--o{ DIEU_KHOAN_HOP_DONG : "[1:N] gom cac"

    %% --- CỤM BẢNG LƯƠNG ---
    NHAN_VIEN ||--o{ BANG_LUONG : "[1:N] nhan luong"
    BANG_LUONG ||--o{ CHI_TIET_BANG_LUONG : "[1:N] hach toan chi tiet"

    %% --- CỤM ĐÁNH GIÁ HIỆU SUẤT ---
    NHAN_VIEN ||--o{ DANH_GIA_HIEU_SUAT : "[1:N] nhan danh gia"
    NHAN_VIEN ||--o{ DANH_GIA_HIEU_SUAT : "[1:N] thuc hien danh gia"
    DANH_GIA_HIEU_SUAT ||--o{ CHI_TIET_DANH_GIA : "[1:N] gom tieu chi"

    %% --- CỤM NGHỈ PHÉP & CHẤM CÔNG ---
    NHAN_VIEN ||--o{ DON_XIN_NGHI : "[1:N] tao don"
    NHAN_VIEN ||--o{ DON_XIN_NGHI : "[1:N] duyet don"
    CA_LAM_VIEC ||--o{ CHAM_CONG : "[1:N] ap dung cho"
    NHAN_VIEN ||--o{ CHAM_CONG : "[1:N] cham cong"

    PHONG_BAN {
        int ma_phong_ban PK
        varchar ten_phong_ban
        int ma_truong_phong FK "Nullable - tránh circular FK"
        varchar dia_diem
    }

    CHUC_VU {
        int ma_chuc_vu PK
        varchar ten_chuc_vu
    }

    NHAN_VIEN {
        int ma_nhan_vien PK
        varchar ho_dem
        varchar ten
        varchar email UK
        varchar so_dien_thoai
        date ngay_sinh
        varchar gioi_tinh
        date ngay_vao_lam
        varchar trang_thai_lam_viec
        int ma_phong_ban FK
        int ma_chuc_vu FK
        int ma_quan_ly FK "Nullable"
    }

    NHOM_NHAN_VIEN {
        int ma_nhom PK
        varchar ten_nhom
        int ma_truong_nhom FK
        date ngay_thanh_lap
        varchar mo_ta
    }

    THANH_VIEN_NHOM {
        int ma_nhom PK, FK
        int ma_nhan_vien PK, FK
        date ngay_tham_gia
        varchar vai_tro_trong_nhom
    }

    HOP_DONG {
        int ma_hop_dong PK
        int ma_nhan_vien FK
        varchar so_hop_dong UK
        varchar loai_hop_dong
        date ngay_hieu_luc
        date ngay_het_han
        decimal luong_thoa_thuan
        varchar trang_thai
    }

    DIEU_KHOAN_HOP_DONG {
        int ma_dieu_khoan PK
        int ma_hop_dong FK
        int so_thu_tu_dieu
        varchar tieu_de_dieu_khoan
        text noi_dung_dieu_khoan
        varchar loai_dieu_khoan
    }

    BANG_LUONG {
        int ma_bang_luong PK
        int ma_nhan_vien FK "UK (ma_nhan_vien, ky_thang, ky_nam)"
        int ky_thang "UK composite"
        int ky_nam "UK composite"
        decimal tong_thu_nhap
        decimal tong_khau_tru
        decimal thuc_lanh
        varchar trang_thai_chi_tra
        date ngay_chi_tra
    }

    CHI_TIET_BANG_LUONG {
        int ma_chi_tiet_luong PK
        int ma_bang_luong FK
        varchar ten_khoan_muc
        varchar loai_khoan_muc
        decimal so_tien
        text ghi_chu
    }

    DANH_GIA_HIEU_SUAT {
        int ma_danh_gia PK
        int ma_nhan_vien FK "UK (ma_nhan_vien, ky_danh_gia, nam)"
        int nguoi_danh_gia FK
        varchar ky_danh_gia "UK composite"
        int nam "UK composite"
        decimal diem_trung_binh
        varchar xep_loai
    }

    CHI_TIET_DANH_GIA {
        int ma_chi_tiet_danh_gia PK
        int ma_danh_gia FK
        varchar tieu_chi_danh_gia
        decimal trong_so
        decimal diem_so
        text nhan_xet_chi_tiet
    }

    CA_LAM_VIEC {
        int ma_ca PK
        varchar ten_ca
        time gio_bat_dau
        time gio_ket_thuc
    }

    CHAM_CONG {
        int ma_cham_cong PK
        int ma_nhan_vien FK "UK (ma_nhan_vien, ma_ca, ngay_lam_viec)"
        int ma_ca FK "UK composite"
        date ngay_lam_viec "UK composite"
        time gio_vao_thuc_te
        time gio_ra_thuc_te
        decimal so_gio_lam_them
        varchar trang_thai_cong
    }

    DON_XIN_NGHI {
        int ma_don_nghi PK
        int ma_nhan_vien FK
        varchar loai_nghi
        date ngay_bat_dau
        date ngay_ket_thuc
        varchar ly_do
        varchar trang_thai_duyet
        int nguoi_duyet FK "Nullable - khi don chua duyet"
    }
```


## Cài đặt các lớp

```Bash
src/
└── com/hrmis/
    ├── enums/
    │   ├── GioiTinh.java
    │   ├── TrangThaiLamViec.java
    │   ├── LoaiHopDong.java
    │   ├── LoaiKhoanMuc.java
    │   ├── TrangThaiDuyet.java
    │   ├── XepLoai.java
    │   ├── TrangThaiHopDong.java
    │   ├── TrangThaiCong.java
    │   ├── LoaiNghi.java
    │   └── TrangThaiChiTra.java
    ├── models/
    │   ├── PhongBan.java
    │   ├── ChucVu.java
    │   ├── NhanVien.java
    │   ├── NhomNhanVien.java
    │   ├── ThanhVienNhom.java
    │   ├── HopDong.java
    │   ├── DieuKhoanHopDong.java
    │   ├── CaLamViec.java
    │   ├── ChamCong.java
    │   ├── DonXinNghi.java
    │   ├── BangLuong.java
    │   ├── ChiTietBangLuong.java
    │   ├── DanhGiaHieuSuat.java
    │   └── ChiTietDanhGia.java
    ├── data/
    │   └── DatabaseMock.java
    ├── services/
    │   ├── NhanSuService.java
    │   ├── VanHanhService.java
    │   └── TaiChinhService.java
    └── Main.java
```


## Các enum

```Java
package com.hrmis.enums;

public enum TrangThaiChiTra {
    CHO_DUYET, DA_DUYET, DA_THANH_TOAN
}
```

```Java
package com.hrmis.enums;

public enum LoaiNghi {
    NGHI_PHEP_NAM, NGHI_OM, NGHI_THAI_SAN, NGHI_KHONG_LUONG, NGHI_KET_HON
}
```

```Java
package com.hrmis.enums;

public enum TrangThaiHopDong {
    DANG_HIEU_LUC, SAP_HET_HAN, DA_HET_HAN, DA_HUY
}
```

```Java
package com.hrmis.enums;

public enum TrangThaiCong {
    DUNG_GIO, DI_TRE, VE_SOM, VANG_MAT_CO_PHEP, VANG_MAT_KHONG_PHEP
}
```

```Java
package com.hrmis.enums;

public enum GioiTinh {
    NAM, NU
}
```

```Java
package com.hrmis.enums;

public enum TrangThaiLamViec {
    DANG_LAM_VIEC, DANG_NGHI_PHEP, DA_NGHI_VIEC
}
```

```Java
package com.hrmis.enums;

public enum LoaiHopDong {
    THU_VIEC, XAC_DINH_THOI_HAN, VO_THOI_HAN
}
```

```Java
package com.hrmis.enums;

public enum LoaiKhoanMuc {
    CONG, TRU
}
```

```Java
package com.hrmis.enums;

public enum TrangThaiDuyet {
    CHO_DUYET, DA_DUYET, TU_CHOI
}
```

```Java
package com.hrmis.enums;

public enum XepLoai {
    XUAT_SAC, TOT, TRUNG_BINH, YEU
}
```


## Câu truy vấn 


#### Người 1: Tổ chức, Nhân sự & Nhóm làm việc (10 câu)

Phụ trách các thực thể: NHAN_VIEN, PHONG_BAN, CHUC_VU, NHOM_NHAN_VIEN, THANH_VIEN_NHOM.

1. Danh sách nhân sự toàn diện: In bảng danh sách toàn bộ nhân viên gồm: Mã NV, Họ tên, Giới tính, Tên phòng ban, Tên chức vụ, Tên người quản lý trực tiếp.
1. Cơ cấu tổ chức phòng ban: In danh sách tất cả các phòng ban, kèm theo Họ tên của Trưởng phòng và tổng số lượng nhân viên hiện có trong từng phòng.
1. Tra cứu theo phòng ban: Nhập vào Mã hoặc Tên phòng ban, in ra danh sách toàn bộ nhân viên thuộc phòng ban đó.
1. Nhân sự thâm niên: Tìm và in ra top 5 nhân viên có ngày vào làm (ngay_vao_lam) sớm nhất công ty.
1. Cây phân cấp quản lý: Nhập ma_nhan_vien của một quản lý, in ra danh sách toàn bộ cấp dưới do người đó trực tiếp phụ trách.
1. Chi tiết một nhóm làm việc: Nhập ma_nhom, in thông tin nhóm, thông tin Trưởng nhóm và danh sách các thành viên cùng vai trò cụ thể trong nhóm.
1. Thống kê nhân sự đa nhiệm: Liệt kê các nhân viên đang tham gia từ 2 nhóm làm việc trở lên kèm tên các nhóm họ trực thuộc.
1. Phân bổ nhân sự theo chức danh: Thống kê số lượng nhân viên theo từng chức vụ (Ví dụ: Chuyên viên: 10, Thực tập sinh: 4, Trưởng nhóm: 3...).
1. Nhân viên chưa vào nhóm: Lọc danh sách các nhân viên chưa tham gia bất kỳ nhóm làm việc (NHOM_NHAN_VIEN) nào.
1. Tìm kiếm nhân sự đa tiêu chí: Tìm kiếm nhân viên gần đúng theo họ tên, số điện thoại hoặc email và in thông tin tóm tắt ra màn hình.

#### Người 2: Hợp đồng, Chấm công & Nghỉ phép (10 câu)

Phụ trách các thực thể: HOP_DONG, DIEU_KHOAN_HOP_DONG, CA_LAM_VIEC, CHAM_CONG, DON_XIN_NGHI.

1. Bản in hợp đồng chi tiết: Nhập ma_hop_dong, in thông tin chung của hợp đồng (Nhân viên ký, thời hạn, mức lương) kèm toàn bộ danh sách các điều khoản điều kiện được đánh số thứ tự (Điều 1, Điều 2...).
1. Cảnh báo hợp đồng sắp hết hạn: Lọc ra tất cả các hợp đồng lao động sẽ hết hạn trong vòng 30 ngày tới kể từ một mốc thời gian cho trước.
1. Bảng chấm công cá nhân: Nhập ma_nhan_vien và tháng/năm, in ra nhật ký chấm công chi tiết từng ngày (Ngày, Ca, Giờ vào, Giờ ra, Số giờ làm, Trạng thái).
1. Báo cáo đi trễ / về sớm: Thống kê danh sách nhân viên đi làm muộn hơn giờ bắt đầu của ca trong một tháng cụ thể, sắp xếp giảm dần theo số lần vi phạm.
1. Tổng hợp giờ làm thêm (OT): Tính tổng số giờ làm thêm ngoài giờ của từng nhân viên trong tháng và in ra bảng xếp hạng top 5 người OT nhiều nhất.
1. Bảng theo dõi đơn nghỉ phép: In danh sách các đơn xin nghỉ phép đang ở trạng thái Cho_duyet, kèm tên người nộp đơn, loại nghỉ, số ngày xin nghỉ và người có thẩm quyền duyệt.
1. Lịch sử nghỉ phép cá nhân: Nhập ma_nhan_vien, in ra lịch sử tất cả các lần nghỉ phép (ốm, phép năm, việc riêng) và trạng thái duyệt của từng đơn.
1. Thống kê chuyên cần: Tìm ra các nhân viên có tỷ lệ đi làm đầy đủ, đúng giờ 100% trong tháng (không đi muộn, không nghỉ không phép).
1. Thống kê loại hợp đồng: Thống kê số lượng nhân viên đang áp dụng từng loại hợp đồng lao động (Thử việc, Hợp đồng 1 năm, Hợp đồng vô thời hạn).
1. Lọc điều khoản pháp lý: In ra tất cả các điều khoản hợp đồng thuộc loại Bảo mật (NDA) hoặc Kỷ luật trên toàn bộ hệ thống.

#### Người 3: Bảng lương, Hạch toán & Đánh giá hiệu suất (10 câu)

Phụ trách các thực thể: BANG_LUONG, CHI_TIET_BANG_LUONG, DANH_GIA_HIEU_SUAT, CHI_TIET_DANH_GIA.

1. In phiếu lương cá nhân (Payslip): Nhập ma_nhan_vien và kỳ lương, in ra một phiếu lương hoàn chỉnh dạng bảng: Lương cơ bản, danh sách các khoản cộng (phụ cấp, thưởng, OT), danh sách các khoản trừ (BHXH, thuế, trễ) và số tiền Thực lãnh.
1. Bảng tổng hợp chi phí lương tháng: In bảng lương tổng của toàn công ty theo tháng gồm: Mã NV, Tên NV, Tổng thu nhập, Tổng khấu trừ, Thực lãnh.
1. Thống kê quỹ lương theo phòng ban: Tính tổng số tiền lương thực lãnh mà công ty phải chi trả cho từng phòng ban trong một tháng cụ thể.
1. Top thu nhập cao nhất: Lọc danh sách top 3 nhân viên có thu nhập thực lãnh cao nhất trong một kỳ lương được chọn.
1. Chi tiết khấu trừ bảo hiểm: Thống kê tổng số tiền các khoản bảo hiểm bắt buộc (BHXH, BHYT, BHTN) đã trích từ lương nhân viên trong tháng.
1. Báo cáo thưởng và phụ cấp: Liệt kê toàn bộ các khoản phụ cấp và tiền thưởng đã phát trong tháng, kèm theo tên nhân viên nhận và lý do chi.
1. Bản in phiếu đánh giá KPI chi tiết: Nhập ma_danh_gia, in thông tin nhân viên được đánh giá, người đánh giá, điểm tổng, xếp loại và chi tiết điểm từng tiêu chí kèm nhận xét.
1. Xếp hạng hiệu suất nhân viên: Thống kê danh sách nhân viên theo xếp loại đánh giá (Xuat_sac, Tot, Trung_binh, Yeu) trong một kỳ đánh giá cụ thể.
1. Tìm kiếm nhân sự xuất sắc: Lọc ra các nhân viên vừa đạt xếp loại đánh giá Xuat_sac vừa có số ngày công chuẩn không nghỉ ngày nào trong cùng một quý.
1. Tổng hợp chi phí vận hành nhân sự: Tính tổng thu nhập trung bình (Average Salary) của toàn bộ nhân viên theo từng chức vụ công việc.

## Database

```Java
package com.hrmis.data;

import com.hrmis.enums.*;
import com.hrmis.models.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DatabaseMock {
    public static List dsPhongBan = new ArrayList<>();
    public static List dsChucVu = new ArrayList<>();
    public static List dsNhanVien = new ArrayList<>();
    public static List dsNhomNhanVien = new ArrayList<>();
    public static List dsThanhVienNhom = new ArrayList<>();
    public static List dsHopDong = new ArrayList<>();
    public static List dsDieuKhoanHopDong = new ArrayList<>();
    public static List dsCaLamViec = new ArrayList<>();
    public static List dsChamCong = new ArrayList<>();
    public static List dsDonXinNghi = new ArrayList<>();
    public static List dsBangLuong = new ArrayList<>();
    public static List dsChiTietBangLuong = new ArrayList<>();
    public static List dsDanhGiaHieuSuat = new ArrayList<>();
    public static List dsChiTietDanhGia = new ArrayList<>();

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
```
