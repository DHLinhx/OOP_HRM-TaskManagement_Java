# HRM & TASK MANAGEMENT SYSTEM - PHIÊN BẢN JAVA (JDK 21)

> Hệ thống Thông tin Quản trị Nhân sự và Vận hành Công việc doanh nghiệp, được xây dựng hoàn toàn bằng ngôn ngữ **Java 21**, áp dụng triệt để kiến trúc phân tầng Hướng đối tượng (OOP) 3 lớp và sức mạnh của **Java Lambdas & Stream API** để xử lý 30 câu truy vấn dữ liệu phức tạp.

---

##  Mục lục
- [Giới thiệu](#-giới-thiệu)
- [Tính năng chính](#-tính-năng-chính)
- [Áp dụng lập trình hướng đối tượng (OOP)](#-áp-dụng-lập-trình-hướng-đối-tượng-oop)
- [Kiến trúc & Sơ đồ lớp (Class Diagram)](#-kiến-trúc--sơ-đồ-lớp-class-diagram)
- [Yêu cầu hệ thống & Công nghệ sử dụng](#-yêu-cầu-hệ-thống--công-nghệ-sử-dụng)
- [Hướng dẫn cài đặt & Chạy chương trình](#-hướng-dẫn-cài-đặt--chạy-chương-trình)
- [Tác giả](#-tác-giả)

---

##  Giới thiệu

Trong các doanh nghiệp hiện đại, nguồn nhân lực là tài sản cốt lõi quyết định năng lực cạnh tranh. Tuy nhiên, công tác quản trị nhân sự truyền thống thông qua sổ sách hoặc các bảng tính rời rạc (Excel) bộc lộ nhiều điểm nghẽn nghiêm trọng:
* Dữ liệu chấm công thủ công phân tán, dễ sai sót và khó kiểm soát giờ làm việc thực tế.
* Hợp đồng lao động hết hạn không được cảnh báo kịp thời, dẫn tới rủi ro pháp lý cho doanh nghiệp.
* Quy trình tính lương phức tạp (kết hợp lương cơ bản, phụ cấp trách nhiệm, thưởng làm thêm ngoài giờ OT và các khoản trích nộp bảo hiểm bắt buộc BHXH 10.5%).
* Đánh giá hiệu suất nhân viên (KPI) thiếu minh bạch và không có trọng số đo lường định lượng.

**Hệ thống Quản trị Nhân sự và Công việc (HRM & Task Management System - Java Edition)** được xây dựng nhằm giải quyết triệt để các bài toán trên. Ứng dụng thiết lập cơ sở dữ liệu mô phỏng trong bộ nhớ gồm 14 thực thể và 10 danh mục Enumeration đạt dạng chuẩn 3NF/BCNF, chia thành 3 phân hệ nghiệp vụ độc lập nhưng liên kết chặt chẽ, đồng thời hiện thực hóa 30 chức năng truy vấn báo cáo chuyên sâu bằng công nghệ **Java Lambdas & Stream API (JDK 21)**.

---
## Hướng dẫn chạy code

##  Tính năng chính

Hệ thống cung cấp đầy đủ 30 chức năng nghiệp vụ được phân rã thành 3 phân hệ chuyên trách trong Java:

### 1. Phân hệ Quản trị Nhân sự & Tổ chức (Thành viên 1: Đặng Hoàng Lĩnh)
- **Hồ sơ nhân viên 360° (Câu 1):** Quản lý tập trung thông tin cá nhân, liên lạc, chức danh và phòng ban.
- **Sơ đồ cơ cấu tổ chức (Câu 2):** Thống kê quy mô nhân sự trực thuộc từng phòng ban và nhận diện chức vụ trưởng phòng.
- **Tra cứu đa dạng (Câu 3):** Tra cứu nhân sự theo mã hoặc từ khóa tên phòng ban (không phân biệt hoa/thường).
- **Vinh danh thâm niên (Câu 4):** Tự động sắp xếp và trích xuất Top 3 nhân viên có ngày vào làm việc sớm nhất.
- **Cây phân cấp quản trị (Câu 5):** Truy vết chính xác quan hệ cấp trên – cấp dưới trực tiếp thông qua quan hệ tự tham chiếu (`maQuanLy`).
- **Quản lý ban dự án (Câu 6):** Hiển thị chi tiết nhóm dự án, danh sách thành viên, ngày tham gia và vai trò.
- **Thống kê nhân sự đa nhiệm (Câu 7):** Gom nhóm bằng Stream để phát hiện các nhân sự tham gia từ 2 nhóm dự án trở lên.
- **Cân đối vị trí nghề nghiệp (Câu 8):** Đếm số lượng nhân sự nắm giữ từng vị trí chức vụ trong công ty.
- **Quản lý tài nguyên nhàn rỗi (Câu 9):** Lọc danh sách nhân viên mới hoặc chưa tham gia bất kỳ nhóm dự án nào.
- **Tìm kiếm thông minh (Câu 10):** 1 từ khóa duy nhất tìm kiếm đồng thời theo Họ tên, Số điện thoại hoặc Email.

### 2. Phân hệ Vận hành & Quản lý Chấm công (Thành viên 2: Nguyễn Sinh Dương)
- **Hợp đồng & Pháp lý (Câu 11):** Bản in chi tiết hợp đồng lao động và danh mục điều khoản pháp lý đính kèm.
- **Cảnh báo đáo hạn tự động (Câu 12):** Tự động rà soát và cảnh báo hợp đồng sắp hết hạn trong vòng 30 ngày bằng `ChronoUnit.DAYS`.
- **Lịch sử chấm công tháng (Câu 13):** Bảng quẹt thẻ chi tiết từng ngày công: giờ vào, giờ ra, trạng thái công và số giờ OT.
- **Xếp hạng kỷ luật (Câu 14):** Gom nhóm và xếp hạng Top nhân viên vi phạm đi trễ (`DI_TRE`) nhiều nhất trong tháng.
- **Ghi nhận cống hiến OT (Câu 15):** Thống kê Top nhân viên làm thêm giờ nhiều nhất trong tháng làm căn cứ phụ cấp.
- **Quy trình phê duyệt nghỉ phép (Câu 16):** Lọc danh sách các đơn xin nghỉ phép đang ở trạng thái `CHO_DUYET`.
- **Hồ sơ nghỉ phép cá nhân (Câu 17):** Truy xuất toàn bộ lịch sử nghỉ phép trong năm của một nhân sự.
- **Tôn vinh chuyên cần 100% (Câu 18):** Tìm kiếm các nhân viên đi làm đầy đủ, không trễ/vắng trong tháng bằng `allMatch()`.
- **Thống kê loại hợp đồng (Câu 19):** Phân tích tỷ trọng cơ cấu hợp đồng lao động theo danh mục Enum `LoaiHopDong`.
- **Thanh tra pháp chế (Câu 20):** Lọc toàn bộ các điều khoản thuộc nhóm Bảo mật thông tin hoặc Kỷ luật lao động.

### 3. Phân hệ Quản lý Tài chính, Tiền lương & KPI (Thành viên 3: Nguyễn Gia Hạo)
- **Phiếu lương minh bạch (Câu 21):** Xuất phiếu lương cá nhân (Payslip) gồm đầy đủ các dòng hạch toán cộng (Gross) và trừ (Khấu trừ).
- **Tổng ngân sách lương tháng (Câu 22):** Tính toán tổng số tiền lương toàn công ty phải chi trả bằng `mapToDouble().sum()`.
- **Quỹ lương theo phòng ban (Câu 23):** Thống kê tổng chi phí lương thực lãnh đã giải ngân theo từng phòng ban.
- **Vinh danh thu nhập cao (Câu 24):** Sắp xếp giảm dần và lọc Top 3 nhân sự có thu nhập thực lãnh cao nhất.
- **Bóc tách bảo hiểm bắt buộc (Câu 25):** Thống kê chi tiết số tiền trích nộp các quỹ BHXH, BHYT, BHTN.
- **Báo cáo danh mục phụ cấp & thưởng (Câu 26):** Tổng hợp toàn bộ các khoản mục tiền thưởng và phụ cấp đã chi trả.
- **Phiếu KPI đa tiêu chí (Câu 27):** Bản in phiếu đánh giá hiệu suất gồm điểm tổng kết, xếp loại và chi tiết từng tiêu chí có trọng số.
- **Phân loại thi đua cuối năm (Câu 28):** Gom nhóm nhân sự theo 4 mức xếp loại: Xuất sắc, Tốt, Trung bình, Yếu.
- **Chiến sĩ thi đua toàn diện (Câu 29):** Lọc nhân sự đạt KPI Xuất Sắc VÀ không có bất kỳ ngày nghỉ phép nào trong năm.
- **Khảo sát đãi ngộ thị trường (Câu 30):** Tính thu nhập bình quân theo từng chức vụ bằng `mapToDouble().average()`.

---

## 💡 Áp dụng lập trình hướng đối tượng (OOP)

Ứng dụng Java tuân thủ nghiêm ngặt 4 nguyên lý OOP và các mẫu thiết kế kiến trúc chuẩn mực:

### 1. Đóng gói (Encapsulation)
- Toàn bộ 14 lớp Model trong package `com.hrmis.models` đều khai báo các thuộc tính ở phạm vi truy cập an toàn (`private`).
- Truy xuất và cập nhật giá trị thông qua các phương thức getter/setter hợp lệ theo chuẩn Java Beans (`getMaNhanVien()`, `setMaNhanVien()`, `HoTenNV()`).
- *Ví dụ:* Lớp `BangLuong` che giấu các trường tài chính (`tongThuNhap`, `tongKhauTru`, `thucLanh`). Dữ liệu chỉ được cập nhật thông qua quá trình tính toán nghiệp vụ tại tầng Service, ngăn chặn việc sửa đổi số tiền tùy tiện.

### 2. Trừu tượng (Abstraction)
- Định nghĩa giao diện tổng quát `IDAO<T, ID>` trong package `com.hrmis.dao`, trừu tượng hóa các hành vi CRUD cơ bản (`getAll()`, `getById()`, `add()`, `update()`, `delete()`).
- Tầng Service chỉ tương tác với các hành vi trừu tượng mà không cần quan tâm dữ liệu bên dưới được lưu trữ trong danh sách tĩnh (`DatabaseMock`) hay Cơ sở dữ liệu quan hệ (SQL Server/MySQL qua JDBC).
- Áp dụng các Functional Interface trừu tượng hóa hành vi tính toán như `SalaryCalculator`, `Predicate<T>`, `Function<T, R>`.

### 3. Kế thừa (Inheritance)
- Toàn bộ 14 lớp DAO cụ thể (`NhanVienDAO`, `PhongBanDAO`, `BangLuongDAO`...) đều cài đặt (implements) giao diện cơ sở `IDAO<T, ID>`.
- Đảm bảo tính nhất quán trong toàn bộ tầng truy xuất dữ liệu, giúp dễ dàng tái sử dụng và mở rộng hệ thống.

### 4. Đa hình (Polymorphism)
- **Ghi đè phương thức (Method Overriding):** 
  - Các lớp Model ghi đè phương thức `toString()` để phục vụ việc in vết và gỡ lỗi.
  - Các lớp DAO triển khai và ghi đè các hàm của `IDAO<T, ID>` theo logic xử lý riêng của từng thực thể.
- **Đa hình giao diện (Interface-based Polymorphism):** 
  - Khai báo biến dưới dạng kiểu interface: `IDAO<NhanVien, Integer> dao = new NhanVienDAO();`.
- **Đa hình tham số (Parametric Polymorphism / Generics):**
  - Giao diện `IDAO<T, ID>` hoạt động linh hoạt với mọi thực thể `T` và kiểu khóa chính `ID` khác nhau (`Integer`, `CompositeKey`).

### 5. Design Patterns đã áp dụng
* **Data Access Object (DAO) Pattern:** Đóng vai trò là tầng trung gian duy nhất giao tiếp với nguồn dữ liệu. Tách biệt hoàn toàn mã nguồn xử lý nghiệp vụ (`*Service`) khỏi mã nguồn truy xuất dữ liệu (`*DAO`), đạt chuẩn **Khớp nối lỏng lẻo (Loose Coupling)** và **Đơn trách nhiệm (Single Responsibility Principle)**.
* **Strategy Pattern:** Ứng dụng thông qua biểu thức Lambda và Functional Interface (`SalaryCalculator`, `Comparator.comparing()`) cho phép hoán đổi thuật toán tính lương hoặc tiêu chí sắp xếp linh hoạt ngay tại thời điểm chạy.
* **3-Layer Architecture Pattern:** Phân chia kiến trúc rõ ràng: *Presentation Layer (Main.java) -> Service Layer -> DAO Layer -> Mock Database*.

---

##  Kiến trúc & Sơ đồ lớp (Class Diagram)

### 1. Kiến trúc phân tầng Java
```
+--------------------------------------------------------------------------+
|                     TẦNG TRÌNH DIỄN (PRESENTATION)                       |
|                             com.hrmis.Main                               |
|        (Menu Console tương tác điều hướng 30 câu hỏi truy vấn)           |
+--------------------------------------------------------------------------+
                                     |
                                     v
+--------------------------------------------------------------------------+
|                     TẦNG DỊCH VỤ (SERVICE LAYER)                         |
|   - NhanSuService.java                  (Đặng Hoàng Lĩnh: Câu 1 -> 10)   |
|   - VanHanhService.java                 (Nguyễn Sinh Dương: Câu 11 -> 20)|
|   - TaiChinhService.java                (Nguyễn Gia Hạo: Câu 21 -> 30)   |
+--------------------------------------------------------------------------+
                                     |
                                     v
+--------------------------------------------------------------------------+
|                TẦNG TRUY XUẤT DỮ LIỆU (DAO LAYER - IDAO<T, ID>)          |
|    NhanVienDAO, PhongBanDAO, HopDongDAO, ChamCongDAO, BangLuongDAO...    |
+--------------------------------------------------------------------------+
                                     |
                                     v
+--------------------------------------------------------------------------+
|                     TẦNG LƯU TRỮ (DATABASE MOCK)                         |
|                       com.hrmis.data.DatabaseMock                        |
|            (14 danh sách List<T> thực thể dữ liệu mẫu chuẩn 3NF)         |
+--------------------------------------------------------------------------+
```

### 2. Sơ đồ lớp Mermaid (Java Edition)

```mermaid
classDiagram
    %% Giao diện cơ sở
    class IDAO~T, ID~ {
        <<interface>>
        +getAll() List~T~
        +getById(ID id) Optional~T~
        +add(T entity) void
        +update(T entity) void
        +delete(ID id) boolean
    }

    %% Các lớp DAO
    class NhanVienDAO {
        +getCapDuoi(int maQL) List~NhanVien~
        +getTopThamNien(int limit) List~NhanVien~
        +search(String kw) List~NhanVien~
    }
    class BangLuongDAO {
        +getByKy(int thang, int nam) List~BangLuong~
        +getByNhanVienVaKy(int maNV, int thang, int nam) Optional~BangLuong~
    }
    class ChamCongDAO {
        +getByNhanVienVaThang(int maNV, int thang, int nam) List~ChamCong~
        +isChuyenCan100PhanTram(int maNV, int thang, int nam) boolean
    }

    IDAO <|.. NhanVienDAO
    IDAO <|.. BangLuongDAO
    IDAO <|.. ChamCongDAO

    %% Các lớp Model
    class NhanVien {
        -int maNhanVien
        -String ho
        -String ten
        -LocalDate ngayVaoLam
        -int maPhongBan
        -int maChucVu
        -Integer maQuanLy
        +HoTenNV() String
        +getMaNhanVien() int
        +getNgayVaoLam() LocalDate
        +getMaQuanLy() Integer
    }

    class BangLuong {
        -int maBangLuong
        -int maNhanVien
        -int kyThang
        -int kyNam
        -double thucLanh
        +getLuongThucLanh() double
    }

    class ChamCong {
        -int maChamCong
        -int maNhanVien
        -LocalDate ngayChamCong
        -double soGioLamThem
        -TrangThaiCong trangThaiCong
        +getTrangThaiCong() TrangThaiCong
    }

    %% Các lớp Service
    class NhanSuService {
        -NhanVienDAO nhanVienDAO
        +DanhSachNhanVien() void
        +top3NhanVienThamNien() void
        +danhSachCapDuoiCuaQuanLy(int maQL) void
    }

    class VanHanhService {
        -ChamCongDAO chamCongDAO
        +bangChamCongCaNhan(int maNV, int thang, int nam) void
        +nhanVienChuyenCan(int thang, int nam) void
    }

    class TaiChinhService {
        -BangLuongDAO bangLuongDAO
        +inPhieuLuongChiTiet(int maNV, int thang, int nam) void
        +tongChiPhiLuongThang(int thang, int nam) void
    }

    %% Mối liên kết
    NhanSuService --> NhanVienDAO : gọi hàm
    VanHanhService --> ChamCongDAO : gọi hàm
    TaiChinhService --> BangLuongDAO : gọi hàm
    NhanVienDAO ..> NhanVien : quản lý
    BangLuongDAO ..> BangLuong : quản lý
    ChamCongDAO ..> ChamCong : quản lý
    NhanVien "1" <-- "N" BangLuong : sở hữu
    NhanVien "1" <-- "N" ChamCong : thực hiện
    NhanVien "1" <-- "0..N" NhanVien : quản lý cấp dưới
```

---

##  Yêu cầu hệ thống & Công nghệ sử dụng

### 1. Môi trường yêu cầu
- **JDK:** Java Development Kit (**JDK 17** hoặc **JDK 21 LTS**).
- **Hệ điều hành:** Microsoft Windows 10/11, macOS hoặc Linux.
- **RAM:** Tối thiểu 4GB RAM.

### 2. Công nghệ Java áp dụng
* **Java SE 21 (LTS).**
* **Java Stream API:** Tạo luồng dữ liệu khai báo, xử lý luồng số (`DoubleStream`), tránh lỗi Boxing/Unboxing.
* **Biểu thức Lambda (`->`):** Cung cấp các hàm ngắn gọn thay thế anonymous classes.
* **Tham chiếu phương thức (Method Reference `::`):** `NhanVien::HoTenNV`, `System.out::println`, `NhanVien::getNgayVaoLam`.
* **Gói thời gian hiện đại:** `java.time.LocalDate`, `LocalTime`, `ChronoUnit`.
* **Collectors nâng cao:** `Collectors.groupingBy()`, `summingDouble()`, `counting()`, `joining()`.
* **Xử lý Null an toàn:** `java.util.Optional` với `.map()`, `.orElse()`, `.orElseThrow()`.

---

## 🛠 Hướng dẫn cài đặt & Chạy chương trình

1. Mở cửa sổ dòng lệnh (Terminal / Command Prompt / PowerShell) và điều hướng vào thư mục dự án Java:
   ```bash
   cd "{folder code}"
   ```
2. Biên dịch toàn bộ mã nguồn Java sang thư mục `bin`:
   ```bash
   javac -encoding UTF-8 -d bin -sourcepath src src/com/hrmis/Main.java
   ```
3. Khởi chạy ứng dụng console:
   ```bash
   java -cp bin com.hrmis.Main
   ```
4. Khi menu console xuất hiện, nhập số thứ tự câu hỏi từ `1` đến `30` để xem kết quả truy vấn, hoặc nhập `0` để thoát.

---

##  Tác giả

Đồ án được thực hiện bởi nhóm sinh viên Khoa Công Nghệ Thông Tin dưới sự hướng dẫn khoa học của:
* **Giảng viên hướng dẫn:** Thầy Nguyễn Minh Đạo

### Thành viên nhóm thực hiện:
1. **Đặng Hoàng Lĩnh** – **MSSV: 25162073** (Phân hệ 1: Quản trị Nhân sự & Tổ chức — Câu 1 -> 10).
2. **Nguyễn Sinh Dương** – **MSSV: 25162019** (Phân hệ 2: Vận hành & Chấm công — Câu 11 -> 20).
3. **Nguyễn Gia Hạo** – **MSSV: 25162030** (Phân hệ 3: Tài chính, Tiền lương & KPI — Câu 21 -> 30).
