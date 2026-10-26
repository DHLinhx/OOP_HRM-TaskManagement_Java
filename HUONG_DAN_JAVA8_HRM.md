# Áp dụng Java 8 / Lambdas & Streams vào Hệ Thống Quản Trị Nhân Sự (HRM & Task Management)

---

## 1. Mục tiêu

Dự án kết hợp chặt chẽ giữa **Lập trình Hướng đối tượng (OOP)** và **Lập trình hàm (Functional Programming)** trong Java:
* **Về mặt OOP:** Giữ vững 4 trụ cột cơ bản (*Tính đóng gói - Encapsulation, Tính trừu tượng - Abstraction, Tính kế thừa - Inheritance, Tính đa hình - Polymorphism*) và mô hình kiến trúc phân tầng chuẩn mực (**Service Layer $\to$ DAO Layer $\to$ Model/Entity Layer**).
* **Về mặt Functional Programming (Java 8+):** Áp dụng toàn diện biểu thức Lambda và Stream API để thay thế các cấu trúc vòng lặp `for`/`while` và `if-else` lồng nhau phức tạp. Sử dụng functional programming cho các tác vụ: lọc hồ sơ (*filter*), ánh xạ dữ liệu (*map*), sắp xếp (*sorted*), tính tổng lương/thưởng (*sum*), tính trung bình (*average*), gom nhóm báo cáo (*groupingBy*), và truyền chiến lược tính toán linh hoạt qua Functional Interfaces.
* **Nguyên tắc bất biến (Immutability & No Side-Effects):** Toàn bộ các thao tác xử lý trên Stream chỉ thực hiện truy vấn và chuyển đổi dữ liệu hiển thị, tuyệt đối không làm thay đổi trạng thái gốc của kho dữ liệu bên dưới (`DatabaseMock`).

---

## 2. Functional Interface là gì?

**Functional Interface** là một interface trong Java chỉ chứa **duy nhất một phương thức trừu tượng (Single Abstract Method - SAM)** (không tính các phương thức công khai mặc định của lớp `Object`). Interface này có thể chứa thêm các phương thức `default` hoặc `static`. 

Annotation `@FunctionalInterface` được đặt phía trên interface nhằm thông báo cho Java Compiler kiểm tra cú pháp, ngăn ngừa việc vô tình khai báo thêm phương thức trừu tượng thứ hai. Biểu thức Lambda không tồn tại kiểu độc lập; kiểu đích (Target Type) của một Lambda luôn là một Functional Interface.

### Bảng các Functional Interface chuẩn của Java áp dụng trong dự án HRM:

| Kiểu Functional Interface | Cú pháp phương thức trừu tượng | Vai trò thực tế trong dự án HRM |
|---|---|---|
| `Predicate<T>` | `boolean test(T t)` | Điều kiện lọc nhân viên (theo phòng ban, đang làm việc, sắp hết hạn hợp đồng, chuyên cần 100%, KPI xuất sắc) |
| `Consumer<T>` | `void accept(T t)` | Tiếp nhận và xuất thông tin ra màn hình Console (`System.out::print`) hoặc ghi log |
| `Supplier<T>` | `T get()` | Cung cấp ngày kiểm tra hệ thống, ngày chốt lương cố định hoặc khởi tạo cấu trúc dữ liệu (`ArrayList::new`) |
| `Function<T, R>` | `R apply(T t)` | Ánh xạ đối tượng hoặc chuyển đổi: Mã NV $\to$ Họ tên, Mã PB $\to$ Tên phòng ban, Mã CV $\to$ Tên chức vụ |
| `BiFunction<T, U, R>` | `R apply(T t, U u)` | Tính toán kết hợp giữa 2 tham số: (Bản ghi chấm công + Khung giờ ca làm) $\to$ Số giờ làm thêm (OT) |
| `ToDoubleFunction<T>` | `double applyAsDouble(T t)` | Trích xuất trường số thực trong `mapToDouble()` (lương thực lãnh, số tiền giảm trừ, giờ OT) giúp giảm boxing |
| `SalaryCalculator` | `double calculate(...)` | Functional Interface tự định nghĩa đại diện cho chiến lược (Strategy) tính toán kết toán lương |

> *Ghi chú:* Toàn bộ các interface chuẩn trên đều nằm sẵn trong package `java.util.function` từ Java 8, lập trình viên không cần tự khai báo lại.

---

## 3. Predicate: Lọc và kết hợp điều kiện (`and`, `or`, `negate`)

Lớp vị từ `Predicate<T>` đóng vai trò là "bộ lọc dữ liệu" (Filter) nhận vào một đối tượng kiểu `T` và trả về kết quả `boolean`.

### Xây dựng tập các vị từ chuẩn trong `NhanVienPredicates.java`:

```java
package com.hrmis.predicates;

import com.hrmis.models.NhanVien;
import com.hrmis.enums.TrangThaiLamViec;
import java.util.function.Predicate;

public class NhanVienPredicates {
    // Nhân viên đang làm việc bình thường
    public static final Predicate<NhanVien> DANG_LAM_VIEC = 
            nv -> nv.getTrangThaiLamViec() == TrangThaiLamViec.DANG_LAM_VIEC;

    // Nhân viên đã nghỉ việc (phủ định của đang làm việc)
    public static final Predicate<NhanVien> DA_NGHI_VIEC = DANG_LAM_VIEC.negate();

    // Vị từ động: Lọc nhân viên thuộc một phòng ban cụ thể
    public static Predicate<NhanVien> thuocPhongBan(int maPhongBan) {
        return nv -> nv.getMaPhongBan() == maPhongBan;
    }

    // Vị từ động: Lọc nhân viên là cấp dưới của một quản lý
    public static Predicate<NhanVien> capDuoiCua(int maQuanLy) {
        return nv -> nv.getMaQuanLy() != null && nv.getMaQuanLy() == maQuanLy;
    }
}
```

### Ứng dụng kết hợp điều kiện trong Service:

```java
// Lọc các nhân viên ĐANG LÀM VIỆC VÀ THUỘC PHÒNG BAN SỐ 1 (Phòng CNTT)
long soLuongNV = nhanVienDAO.getAll().stream()
    .filter(NhanVienPredicates.DANG_LAM_VIEC.and(NhanVienPredicates.thuocPhongBan(1)))
    .count();
```

* **Cơ chế thực thi:** 
  * `and()`: Yêu cầu cả 2 điều kiện đều phải trả về `true` (có cơ chế Short-circuit: nếu vế đầu `false` thì không kiểm tra vế sau).
  * `or()`: Thỏa mãn ít nhất 1 trong 2 điều kiện.
  * `negate()`: Đảo ngược giá trị logic (`true` $\to$ `false`).

---

## 4. Consumer: Tiếp nhận và xuất dữ liệu (`System.out::println`)

`Consumer<T>` đại diện cho một tác vụ tiêu thụ dữ liệu đầu vào kiểu `T` và không trả về bất kỳ kết quả nào (`void`).

```java
// Khai báo Consumer định dạng và in dòng nhân viên ra console
Consumer<NhanVien> inDongNhanVien = nv -> System.out.printf(
    "%-8d %-22s %-12s %s%n", 
    nv.getMaNhanVien(), nv.HoTenNV(), nv.getGioiTinh(), nv.getNgayVaoLam()
);

// Duyệt và tiêu thụ dữ liệu
nhanVienDAO.getAll().forEach(inDongNhanVien);
```

### Tách biệt nơi nhận kết quả giữa Môi trường Thực tế và Môi trường Test:
Bằng cách truyền `Consumer<String>` vào tầng Service, ta có thể dễ dàng chuyển đổi đầu ra:
```java
// Trong Console thực tế:
Consumer<String> outputConsole = System.out::println;

// Trong Unit Test: Thu thập log vào một danh sách để kiểm chứng kết quả mà không in rác console
List<String> logs = new ArrayList<>();
Consumer<String> outputTest = logs::add;
```

---

## 5. Supplier: Cung cấp ngày tháng và dữ liệu chủ động

`Supplier<T>` không nhận tham số đầu vào nào nhưng trả về một đối tượng kiểu `T` (`T get()`). Trong hệ thống HRM, `Supplier` giải quyết bài toán mốc thời gian kiểm thử:

```java
// Supplier cung cấp ngày kiểm tra cố định để kết quả báo cáo luôn nhất quán:
Supplier<LocalDate> ngayKiemTraDemo = () -> LocalDate.of(2024, 3, 31);

// Khi chạy thật dùng thời gian máy tính hiện tại:
Supplier<LocalDate> ngayHienTai = LocalDate::now;
```

### Ứng dụng trong Câu 12 (Cảnh báo hợp đồng sắp hết hạn trong 30 ngày):
```java
public void canhBaoHopDongSapHetHan(Supplier<LocalDate> dateSupplier) {
    LocalDate mocNgay = dateSupplier.get();
    hopDongDAO.getSapHetHan(mocNgay, 30).forEach(hd -> {
        long soNgay = ChronoUnit.DAYS.between(mocNgay, hd.getNgayKetThuc());
        System.out.printf("Hợp đồng #%d hết hạn sau %d ngày.%n", hd.getMaHopDong(), soNgay);
    });
}
```
Nhờ `Supplier`, khi báo cáo trước Giảng viên, bài chạy luôn hiển thị danh sách hợp đồng mẫu hợp lệ mà không phụ thuộc vào việc bạn chạy vào năm 2024, 2025 hay 2026.

---

## 6. Function và andThen: Biến đổi dữ liệu (Mapping Pipeline)

`Function<T, R>` nhận đầu vào kiểu `T` và biến đổi thành kết quả kiểu `R`.

```java
// 1. Function trích xuất họ tên từ đối tượng NhanVien
Function<NhanVien, String> layHoTen = NhanVien::HoTenNV;

// 2. Function định dạng chuỗi: thêm ký tự đánh dấu đầu dòng
Function<String, String> themDauDong = text -> "  -> " + text;

// 3. Kết hợp 2 hàm bằng andThen: NhanVien -> HoTen -> Chuỗi định dạng
Function<NhanVien, String> taoDongBaoCao = layHoTen.andThen(themDauDong);

// Sử dụng trong luồng Stream:
nhanVienDAO.getAll().stream()
    .map(taoDongBaoCao)
    .forEach(System.out::println);
```

* `andThen()` tạo ra một hàm mới: hàm thứ nhất chạy trước, lấy kết quả truyền vào hàm thứ hai.
* Thao tác `map()` trong Stream áp dụng `Function` để chuyển luồng từ kiểu `Stream<NhanVien>` sang `Stream<String>`.

---

## 7. Interface tự định nghĩa `@FunctionalInterface`: `SalaryCalculator`

Ngoài các interface chuẩn, dự án tự xây dựng Functional Interface đại diện cho **Chiến lược tính lương (Strategy Pattern)**:

```java
package com.hrmis.services;

import java.time.LocalDate;

@FunctionalInterface
public interface SalaryCalculator {
    // Phương thức SAM duy nhất
    double calculateNetSalary(double baseSalary, double allowance, double overtimePay, double deduction);

    // Chiến lược chuẩn mực theo quy định Luật Lao động
    static SalaryCalculator standard() {
        return (base, allowance, ot, deduction) -> {
            double gross = base + allowance + ot;
            double bhxh = base * 0.105; // 10.5% bảo hiểm bắt buộc
            return gross - bhxh - deduction;
        };
    }

    // Chiến lược tính lương thời vụ / thử việc (không trừ bảo hiểm)
    static SalaryCalculator probation() {
        return (base, allowance, ot, deduction) -> (base + allowance + ot) - deduction;
    }
}
```

Nhờ thiết kế này, phương thức kết toán lương có thể nhận linh hoạt các biểu thức Lambda khác nhau tùy từng trường hợp mà không cần viết lại toàn bộ class Service.

---

## 8. Stream: Tính tổng chi phí lương và giờ làm thêm

Stream API cung cấp các luồng số chuyên biệt (`DoubleStream`, `LongStream`, `IntStream`) giúp loại bỏ chi phí đóng gói/mở gói (Boxing/Unboxing):

### Tính tổng chi phí tiền lương toàn công ty trong tháng (Câu 22):
```java
List<BangLuong> dsBangLuongThang = bangLuongDAO.getByKy(thang, nam);

double tongChiPhiLuong = dsBangLuongThang.stream()
        .mapToDouble(BangLuong::getLuongThucLanh) // Chuyển sang DoubleStream
        .sum();                                  // Tính tổng cộng dồn
```

### Tính tổng số giờ làm thêm (OT) của nhân viên (Câu 15):
```java
double tongGioOT = chamCongDAO.getAll().stream()
        .filter(cc -> cc.getMaNhanVien() == maNV && cc.getSoGioLamThem() > 0)
        .mapToDouble(ChamCong::getSoGioLamThem)
        .sum();
```
* **Lưu ý kỹ thuật:** Nếu Stream rỗng (không có bản ghi nào), hàm `sum()` trả về an toàn giá trị `0.0` mà không gây lỗi `NullPointerException`.

---

## 9. Thống kê nâng cao: `groupingBy`, `counting` và `summingDouble`

Lớp tiện ích `Collectors` hỗ trợ gom nhóm và kết hợp dữ liệu nhiều chiều:

### Thống kê nhân sự đa nhiệm tham gia $\ge 2$ nhóm dự án (Câu 7):
```java
thanhVienNhomDAO.getAll().stream()
    .collect(Collectors.groupingBy(ThanhVienNhom::getnhomNhanVien)) // Gom nhóm theo mã NV
    .entrySet().stream()
    .filter(entry -> entry.getValue().size() >= 2)                  // Lọc người >= 2 nhóm
    .forEach(entry -> {
        String tenNV = getTenNhanVien(entry.getKey());
        int soNhom = entry.getValue().size();
        System.out.printf("- %s tham gia %d nhóm dự án.%n", tenNV, soNhom);
    });
```

### Báo cáo xếp hạng nhân viên đi trễ (Câu 14):
```java
Map<Integer, Long> mapDiTre = chamCongDAO.getAll().stream()
    .filter(cc -> cc.getTrangThaiCong() == TrangThaiCong.DI_TRE)
    .collect(Collectors.groupingBy(ChamCong::getMaNhanVien, Collectors.counting()));
```

### Tổng hợp chi phí các loại phụ cấp và thưởng đã chi (Câu 26):
```java
Map<String, Double> tongTheoKhoanMuc = chiTietBangLuongDAO.getAll().stream()
    .filter(ct -> ct.getLoaiKhoanMuc() == LoaiKhoanMuc.CONG)
    .collect(Collectors.groupingBy(
        ChiTietBangLuong::getTenKhoanMuc,
        Collectors.summingDouble(ChiTietBangLuong::getSoTien)
    ));
```

---

## 10. `sorted`, `limit`, `distinct` và `Collectors.joining`

### Trích xuất Top 3 nhân viên thâm niên cao nhất (Câu 4):
```java
nhanVienDAO.getAll().stream()
    .sorted(Comparator.comparing(NhanVien::getNgayVaoLam)) // Sắp xếp tăng dần theo ngày vào làm
    .limit(3)                                              // Trích xuất đúng 3 phần tử đầu
    .forEach(nv -> System.out.println(nv.HoTenNV() + " - " + nv.getNgayVaoLam()));
```

### Trích xuất Top 3 thu nhập thực lãnh cao nhất trong tháng (Câu 24):
```java
bangLuongDAO.getByKy(thang, nam).stream()
    .sorted((b1, b2) -> Double.compare(b2.getLuongThucLanh(), b1.getLuongThucLanh())) // Giảm dần
    .limit(3)
    .forEach(bl -> System.out.printf("TOP %s: %,.0f VNĐ%n", getTenNhanVien(bl.getMaNhanVien()), bl.getLuongThucLanh()));
```

### Nối chuỗi tên các nhóm dự án bằng `Collectors.joining` (Câu 7):
```java
String dsTenNhom = danhSachThanhVien.stream()
    .map(tv -> getTenNhom(tv.getmaNhom()))
    .collect(Collectors.joining(", ")); // Nối thành chuỗi: "Nhóm Core, Nhóm Mobile, Ban Sự Kiện"
```

---

## 11. `Optional` và Exception Supplier (`orElse`, `orElseThrow`, `map`)

`Optional<T>` là giải pháp an toàn trong Java 8 để phòng chống lỗi kinh điển `NullPointerException`:

### Phương thức tìm kiếm tại tầng DAO trả về `Optional`:
```java
public Optional<NhanVien> getById(Integer id) {
    return DatabaseMock.dsNhanVien.stream()
            .filter(nv -> nv.getMaNhanVien() == id)
            .findFirst();
}
```

### Ánh xạ an toàn không sợ Null (Helper method):
```java
private String getTenNhanVien(int maNhanVien) {
    return nhanVienDAO.getById(maNhanVien)
            .map(NhanVien::HoTenNV)               // Nếu có nhân viên thì lấy họ tên
            .orElse("Không xác định");            // Nếu không tìm thấy trả về chuỗi mặc định
}
```

### Bắt buộc phải có hợp đồng (ném ngoại lệ bằng Supplier):
```java
HopDong hd = hopDongDAO.getById(maHopDong)
        .orElseThrow(() -> new IllegalArgumentException("Lỗi: Không tìm thấy hợp đồng số " + maHopDong));
```

---

## 12. `BiFunction` & `BiPredicate`: Xử lý logic 2 tham số

Khi bài toán nghiệp vụ đòi hỏi kết hợp dữ liệu giữa hai thực thể:

```java
// BiFunction tính số tiền làm ngoài giờ (OT):
// Đầu vào: Đối tượng ChamCong và Mức lương cơ bản giờ của nhân viên
// Đầu ra: Số tiền làm thêm giờ (OT * lương giờ * 1.5)
BiFunction<ChamCong, Double, Double> tinhTienOT = (cong, luongGio) -> {
    if (cong.getSoGioLamThem() <= 0) return 0.0;
    return cong.getSoGioLamThem() * luongGio * 1.5;
};
```

---

## 13. Thực thi lười (Lazy Evaluation) và Bảng đối chiếu Method Reference

Một luồng Stream gồm 3 giai đoạn:
1. **Nguồn dữ liệu (Source):** `dsNhanVien.stream()`
2. **Thao tác trung gian (Intermediate Operations):** `filter()`, `map()`, `sorted()`. Các hàm này có tính chất **Lazy Evaluation** (thực thi lười), nghĩa là chúng chỉ được lập trình sẵn và sẽ KHÔNG BAO GIỜ CHẠY cho đến khi có một thao tác kết thúc được gọi.
3. **Thao tác kết thúc (Terminal Operation):** `forEach()`, `collect()`, `count()`, `sum()`, `findFirst()`. Kích hoạt luồng chạy và thu hoạch kết quả. Sau khi chạy, Stream bị đóng và không thể tái sử dụng.

### Bảng đối chiếu cú pháp Lambda vs Method Reference trong project:

| Biểu thức Lambda tương đương | Cú pháp Method Reference | Ý nghĩa nghiệp vụ trong HRM |
|---|---|---|
| `nv -> nv.HoTenNV()` | `NhanVien::HoTenNV` | Lấy họ tên nhân viên |
| `nv -> nv.getNgayVaoLam()` | `NhanVien::getNgayVaoLam` | Dùng trong Comparator sắp xếp thâm niên |
| `bl -> bl.getLuongThucLanh()` | `BangLuong::getLuongThucLanh` | Lấy số tiền thực lãnh trong `mapToDouble` |
| `str -> System.out.println(str)` | `System.out::println` | In dòng dữ liệu ra màn hình |
| `id -> phongBanDAO.getTenPhongBan(id)` | `phongBanDAO::getTenPhongBan` | Tham chiếu hàm tìm tên phòng ban qua mã |
| `() -> new ArrayList<>()` | `ArrayList::new` | Khởi tạo danh sách mới trong Map gom nhóm |

---

## 14. Tính tương thích giữa các phiên bản Java (Java 8 $\to$ Java 21)

* **Xử lý ngày tháng:** Dự án sử dụng `java.time.LocalDate` và `LocalTime` (đã có chuẩn từ Java 8), thay thế hoàn toàn lớp `java.util.Date` cũ vốn hay xảy ra lỗi lệch múi giờ và mutable.
* **Kiểm tra chuỗi rỗng:** Sử dụng `str == null || str.trim().isEmpty()` để tương thích 100% với môi trường Java 8 (không dùng `str.isBlank()` vốn chỉ có từ Java 11).
* **Thu hoạch danh sách Stream:** Sử dụng `.collect(Collectors.toList())` (chuẩn Java 8+) thay vì `.toList()` (mới xuất hiện từ Java 16), đảm bảo mã nguồn biên dịch mượt mà trên mọi phiên bản JDK từ 8, 11, 17 đến 21.

---

## 15. Phối hợp giữa OOP và Functional Programming trong kiến trúc hệ thống

Dự án HRM phân định rõ ranh giới trách nhiệm giữa hai trường phái lập trình:

```
+--------------------------------------------------------------------------+
|                     KIẾN TRÚC TỔNG THỂ (OOP)                             |
|  - Tính đóng gói (Encapsulation): private fields, getter/setter.          |
|  - Trừu tượng hóa & Kế thừa: Giao diện tổng quát IDAO<T, ID>.            |
|  - Kiến trúc phân tầng 3 lớp: Presentation -> Service -> DAO.           |
+--------------------------------------------------------------------------+
                                    |
                                    v (Ứng dụng bên trong các hàm xử lý)
+--------------------------------------------------------------------------+
|                  THUẬT TOÁN XỬ LÝ DỮ LIỆU (FUNCTIONAL)                   |
|  - Stream API & Lambdas: filter, map, sorted, groupingBy, sum, average.  |
|  - Hàm thuần khiết (Pure Functions): Không làm biến đổi Mock Database.    |
|  - Code ngắn gọn, trực quan, loại bỏ các vòng lặp for/if lồng nhau.      |
+--------------------------------------------------------------------------+
```

1. **OOP chịu trách nhiệm về Khung xương kiến trúc:** Thiết kế các Model bảo vệ toàn vẹn dữ liệu, giao diện `IDAO<T, ID>` chuẩn hóa các hành vi CRUD, và các Service phân định nghiệp vụ độc lập giữa 3 thành viên (Lĩnh, Dương, Hạo).
2. **Functional Programming chịu trách nhiệm về Thuật toán xử lý luồng:** Các câu truy vấn dữ liệu được viết theo phong cách khai báo (Declarative), giúp code ngắn gọn gấp 3 lần so với code Java truyền thống, dễ đọc, dễ bảo trì và dễ dàng đối sánh 1-1 với C# LINQ.
