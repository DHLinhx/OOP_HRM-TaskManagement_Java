# BÀI VĂN THUYẾT TRÌNH VIDEO BÁO CÁO PROJECT OOP & CHI TIẾT TRUY VẤN
**Đề tài:** Hệ thống Quản lý Nhân sự & Vận hành Doanh nghiệp (HRM & Task Management System)  
**Môn học:** Lập trình Hướng đối tượng (OOP) | **Nhóm:** 9  
**Người thực hiện:** Đặng Hoàng Linh | **MSSV:** 25162073  
**Phân hệ phụ trách:** Tổ chức, Nhân sự & Nhóm làm việc (`NhanSuService.java`)

---

## PHẦN I: BÀI VĂN THUYẾT TRÌNH VIDEO BÁO CÁO TỔNG QUAN PROJECT OOP

### 1. Mở đầu & Giới thiệu đề tài Hệ thống MIS

> *(Kịch bản nói / Thuyết minh mở đầu)*
> 
> "Em xin kính chào quý thầy cô và các bạn!
> Em tên là **Đặng Hoàng Linh**, mã số sinh viên **25162073**. Hôm nay, em xin đại diện cho **Nhóm 9** trình bày video báo cáo dự án môn **Lập trình Hướng đối tượng (OOP)** với đề tài: **Hệ thống Quản lý Nhân sự & Vận hành Công việc (HRM & Task Management System)**.
> 
> Đề tài của nhóm chúng em thuộc nhóm **Hệ thống Thông tin Quản lý (Management Information System - MIS)**. Về mặt bản chất, một hệ thống MIS không đơn thuần là phần mềm lưu trữ dữ liệu, mà là sự kết hợp chặt chẽ giữa con người, quy trình và dữ liệu nhằm cung cấp thông tin kịp thời, chính xác cho các cấp quản trị ra quyết định:
> - **Ở cấp tác nghiệp (Operational Level):** Hệ thống giúp số hóa hồ sơ nhân sự, tự động hóa quy trình chấm công theo ca, tính toán ngày công và giờ làm thêm (OT) minh bạch.
> - **Ở cấp chiến thuật (Tactical Level):** Hỗ trợ Trưởng phòng và Quản lý HR nắm bắt cơ cấu tổ chức, theo dõi tình trạng nhân sự đa nhiệm giữa các dự án, phát hiện vi phạm chuyên cần và chủ động cảnh báo các hợp đồng sắp hết hạn để tái ký đúng hạn.
> - **Ở cấp chiến lược (Strategic Level):** Giúp Ban Giám đốc kiểm soát tổng quỹ lương, cân đối chi phí nhân sự theo từng chức danh và đo lường hiệu suất làm việc (KPI) đa chiều để khen thưởng đúng người, đúng việc.
> 
> Đề tài được nhóm định vị phục vụ nhu cầu quản lý nội bộ của các doanh nghiệp vừa và nhỏ (SME), được hiện thực hóa và thực thi xử lý nghiệp vụ tối ưu trên nền tảng Console/Terminal bằng ngôn ngữ Java."

---

### 2. Khảo sát hiện trạng thực tế & Các bước phân tích thiết kế sơ đồ ERD

> *(Kịch bản nói / Thuyết minh về Khảo sát thực tế & ERD)*
> 
> "Kính thưa thầy cô, để dự án không mang tính lý thuyết suông mà giải quyết được bài toán thực tiễn, trước khi bắt tay vào thiết kế dữ liệu, nhóm chúng em **đã tiến hành khảo sát hiện trạng thực tế** tại các mô hình doanh nghiệp vừa và nhỏ (SME) và chuỗi dịch vụ quy mô từ 30 đến 100 nhân sự.
> 
> Qua quá trình khảo sát và phỏng vấn trực tiếp cán bộ Nhân sự, nhóm nhận diện được **4 khó khăn cốt lõi (Pain Points)** mà các doanh nghiệp đang gặp phải khi quản lý thủ công:
> 1. **Dữ liệu phân tán, cát cứ dữ liệu:** Thông tin nhân viên nằm ở file hồ sơ riêng, máy chấm công xuất file excel riêng, dữ liệu dự án nằm trên Zalo/Trello, còn công thức tính lương lại nằm ở một bảng tính khác. Khi cần tra cứu thông tin tổng thể của một nhân viên, người quản lý mất rất nhiều thời gian đối chiếu.
> 2. **Rủi ro pháp lý về hợp đồng lao động:** Doanh nghiệp thường xuyên 'bỏ quên' ngày hết hạn hợp đồng thử việc hoặc hợp đồng xác định thời hạn của nhân viên, dẫn đến việc chuyển đổi hợp đồng chậm trễ, tiềm ẩn rủi ro tranh chấp lao động theo luật.
> 3. **Tính toán chấm công và lương thủ công tốn ngày công:** Vào mỗi kỳ lương cuối tháng, HR mất từ 3 đến 5 ngày công để cộng trừ thủ công số giờ làm thêm (OT), đếm số lần đi trễ để phạt, và tính các khoản trích bảo hiểm (10.5%). Quá trình này rất dễ xảy ra sai sót và nhầm lẫn công thức.
> 4. **Đánh giá hiệu suất nhân viên mang tính cảm tính:** Cuối quý, việc bình xét KPI thường thiếu căn cứ minh bạch, không có sự liên kết giữa điểm số chuyên môn với tỷ lệ chuyên cần và mức độ đóng góp trong các dự án.
> 
> Xuất phát từ 4 bài toán thực tế đó, nhóm đã tiến hành **Quy trình phân tích thiết kế dữ liệu gồm 4 bước bài bản** để xây dựng nên sơ đồ quan hệ thực thể (ERD):
> 
> - **Bước 1 – Phân cụm nghiệp vụ và Nhận diện 14 thực thể:** Nhóm chia nhỏ bài toán thành 6 cụm nghiệp vụ chặt chẽ:
>   * *Cụm Cơ cấu & Tổ chức:* gồm `PHONG_BAN`, `CHUC_VU`, `NHAN_VIEN`.
>   * *Cụm Nhóm làm việc & Dự án:* gồm `NHOM_NHAN_VIEN`, `THANH_VIEN_NHOM`.
>   * *Cụm Hợp đồng pháp lý:* gồm `HOP_DONG`, `DIEU_KHOAN_HOP_DONG`.
>   * *Cụm Bảng lương & Hạch toán:* gồm `BANG_LUONG`, `CHI_TIET_BANG_LUONG`.
>   * *Cụm Đánh giá hiệu suất:* gồm `DANH_GIA_HIEU_SUAT`, `CHI_TIET_DANH_GIA`.
>   * *Cụm Vận hành Chấm công & Nghỉ phép:* gồm `CA_LAM_VIEC`, `CHAM_CONG`, `DON_XIN_NGHI`.
> 
> - **Bước 2 – Xác định thuộc tính và Ràng buộc toàn vẹn (PK, FK, UK):** Mỗi thực thể đều có khóa chính (PK) định danh. Đặc biệt, nhóm thiết lập các ràng buộc duy nhất phức hợp (Composite Unique Keys) sát thực tế: Mỗi nhân viên chỉ có đúng một bảng lương trong một kỳ tháng/năm (`ma_nhan_vien`, `ky_thang`, `ky_nam`); mỗi nhân viên chỉ có một bản đánh giá trong cùng kỳ (`ma_nhan_vien`, `ky_danh_gia`, `nam`); và một lượt chấm công duy nhất cho mỗi ca trong ngày (`ma_nhan_vien`, `ma_ca`, `ngay_lam_viec`).
> 
> - **Bước 3 – Xử lý các bài toán quan hệ đặc biệt:**
>   * *Quan hệ tự tham chiếu (Recursive / Self-referencing):* Bảng `NHAN_VIEN` có khóa ngoại `ma_quan_ly` trỏ lại chính nó để mô hình hóa cây phân cấp quản lý. Nhân sự cấp cao nhất sẽ có giá trị `ma_quan_ly = null`.
>   * *Xử lý phụ thuộc vòng lặp (Circular Dependency):* `NHAN_VIEN` thuộc về `PHONG_BAN`, nhưng `PHONG_BAN` lại có Trưởng phòng là một `NHAN_VIEN`. Nhóm đã xử lý bằng cách cho phép `ma_truong_phong` trong `PHONG_BAN` nhận giá trị Nullable, giúp giải quyết triệt để vấn đề bế tắc khi khởi tạo dữ liệu ban đầu.
>   * *Chuẩn hóa quan hệ Nhiều – Nhiều (M-N):* Một nhân viên có thể tham gia nhiều nhóm dự án và một nhóm có nhiều nhân viên tham gia. Nhóm đã chuẩn hóa bằng bảng trung gian `THANH_VIEN_NHOM` với khóa chính phức hợp `(ma_nhom, ma_nhan_vien)` và lưu thêm ngày tham gia cùng vai trò cụ thể.
> 
> - **Bước 4 – Chuẩn hóa cơ sở dữ liệu đạt chuẩn 3NF (Third Normal Form):** Nhóm tách riêng các khoản mục lương thành bảng `CHI_TIET_BANG_LUONG` và các tiêu chí KPI thành bảng `CHI_TIET_DANH_GIA`. Thiết kế này loại bỏ hoàn toàn các thuộc tính đa trị, tránh dư thừa dữ liệu và cho phép hệ thống mở rộng linh hoạt không giới hạn các khoản phụ cấp hoặc tiêu chí đánh giá mới."

---

### 3. Cấu trúc Project & Kiến trúc phân tầng (Layered Architecture & DAO Pattern)

> *(Kịch bản nói / Thuyết minh về Cấu trúc Project)*
> 
> "Về mặt kỹ thuật lập trình, dự án được xây dựng theo chuẩn **Kiến trúc phân tầng (Layered Architecture)** kết hợp mẫu thiết kế **DAO Pattern (Data Access Object)** nhằm đảm bảo tính phân tách trách nhiệm (Separation of Concerns) và khả năng mở rộng lâu dài.
> 
> Toàn bộ mã nguồn nằm trong gói `src/com/hrmis/` được tổ chức thành **5 thư mục chức năng chuyên biệt** và lớp thực thi chính:
> 
> 1. **Thư mục `enums/` (10 Enums):** Định nghĩa các kiểu liệt kê trạng thái chuẩn hóa như `GioiTinh`, `TrangThaiLamViec`, `LoaiHopDong`, `TrangThaiCong`, `LoaiNghi`, `TrangThaiDuyet`, `LoaiKhoanMuc`, `XepLoai`, `TrangThaiHopDong`, `TrangThaiChiTra`. Việc sử dụng Enum loại bỏ hoàn toàn các chuỗi gõ tay tự do (Magic Strings), đảm bảo an toàn kiểu dữ liệu (Type-Safety) trong toàn bộ chương trình.
> 2. **Thư mục `models/` (14 Classes):** Gồm 14 lớp POJO ánh xạ trực tiếp từ 14 bảng trong sơ đồ ERD. Mỗi lớp đều áp dụng tính đóng gói (Encapsulation) cao với thuộc tính `private`, constructors, getters/setters và các phương thức tiện ích hỗ trợ hiển thị.
> 3. **Thư mục `data/` (`DatabaseMock.java`):** Đóng vai trò là CSDL bộ nhớ trong (In-Memory Database), khai báo các `static List` và khởi tạo sẵn bộ dữ liệu mẫu phong phú với 10 nhân sự có đầy đủ cấp bậc lãnh đạo, chức vụ, phòng ban, nhóm dự án, hợp đồng, chấm công có đi trễ và làm thêm giờ OT, cùng các phiếu đánh giá KPI thực tế.
> 4. **Thư mục `dao/` (Tầng Data Access Object):** Đây là điểm nâng cấp kiến trúc rất quan trọng của nhóm. Nhóm xây dựng giao diện gốc `IDAO<T, ID>` định nghĩa các thao tác CRUD cơ sở và 14 lớp Concrete DAO (như `NhanVienDAO`, `PhongBanDAO`, `HopDongDAO`,...). Toàn bộ việc đọc/ghi dữ liệu từ `DatabaseMock` đều thông qua DAO. Khi dự án kết nối với CSDL thật như MySQL hay PostgreSQL qua JDBC, chúng em chỉ cần thay đổi bên trong các lớp DAO mà không phải sửa đổi bất kỳ dòng code nào ở tầng Service.
> 5. **Thư mục `services/` (Tầng Business Logic):** Trọng tâm xử lý logic nghiệp vụ, gồm 3 dịch vụ tương ứng với sự phân công của 3 thành viên:
>    - `NhanSuService.java`: Phụ trách 10 nghiệp vụ về Tổ chức, Nhân sự & Nhóm làm việc.
>    - `VanHanhService.java`: Phụ trách 10 nghiệp vụ về Hợp đồng, Chấm công & Nghỉ phép.
>    - `TaiChinhService.java`: Phụ trách 10 nghiệp vụ về Bảng lương, Bảo hiểm & Đánh giá KPI.
>    Tầng này ứng dụng triệt để **Java Streams API** và biểu thức Lambda để xử lý dữ liệu với tốc độ cao và cú pháp rõ ràng.
> 6. **File `Main.java` (Tầng Trình diễn / Presentation Layer):** Là điểm khởi chạy của ứng dụng, cung cấp Menu Console tương tác từ câu 1 đến câu 30, xử lý nhập liệu an toàn với khối `try-catch` và điều phối gọi đến các Service tương ứng."

---

### 4. Các bước thực hiện để hoàn thành Project (Quy trình 6 bước)

> *(Kịch bản nói / Thuyết minh về Quy trình làm ra Project)*
> 
> "Để hoàn thiện dự án một cách khoa học, nhóm em đã tiến hành qua **6 bước tuần tự**:
> - **Bước 1:** Khảo sát nghiệp vụ thực tế, xác định phạm vi MIS và chốt danh sách 30 câu truy vấn chia đều cho 3 thành viên.
> - **Bước 2:** Thiết kế sơ đồ quan hệ thực thể ERD đạt chuẩn 3NF và giải quyết các ràng buộc toàn vẹn dữ liệu.
> - **Bước 3:** Khởi tạo kiến trúc mã nguồn, định nghĩa 10 Enums chuẩn hóa và lập trình 14 lớp Model thực thể.
> - **Bước 4:** Xây dựng tầng truy cập dữ liệu với Generic `IDAO` và các lớp Concrete DAO, kèm theo lớp `DatabaseMock` chứa dữ liệu giả lập có chiều sâu logic.
> - **Bước 5:** Lập trình tầng nghiệp vụ tại `NhanSuService`, `VanHanhService` và `TaiChinhService` ứng dụng Java Stream API (`filter`, `map`, `sorted`, `groupingBy`, `count`, `joining`).
> - **Bước 6:** Tích hợp vào menu điều khiển tại `Main.java`, chạy kiểm thử toàn bộ 30 kịch bản và định dạng xuất kết quả dạng bảng biểu căn lề ngay ngắn trên màn hình Console."

---

### 5. Lời dẫn chuyển sang phần chạy Main và Demo truy vấn cá nhân

> *(Kịch bản nói / Thuyết minh chuyển tiếp)*
> 
> "Sau đây, em xin phép khởi chạy chương trình `Main.java` để trực tiếp thuyết minh và chứng minh kết quả thực thi của **10 câu truy vấn thuộc Phân hệ Tổ chức, Nhân sự & Nhóm làm việc** do em trực tiếp phụ trách, được cài đặt trong lớp `NhanSuService.java`, thao tác trên 5 thực thể: `NHAN_VIEN`, `PHONG_BAN`, `CHUC_VU`, `NHOM_NHAN_VIEN` và `THANH_VIEN_NHOM`!"

---

## PHẦN II: GIẢI THÍCH CHI TIẾT & SÂU SẮC TỪNG LỆNH TRUY VẤN TRONG `NhanSuService.java`

Phần này dùng để bạn cầm theo khi quay video, thuyết trình chi tiết từng dòng code và tự tin trả lời mọi câu hỏi vấn đáp nâng cao từ giảng viên.

---

### A. Kiến trúc phân tầng & Tầng DAO trong phân hệ Nhân sự

Tại đầu lớp `NhanSuService.java`, các đối tượng DAO được khai báo độc lập:
```java
private final NhanVienDAO nhanVienDAO = new NhanVienDAO();
private final PhongBanDAO phongBanDAO = new PhongBanDAO();
private final ChucVuDAO chucVuDAO = new ChucVuDAO();
private final NhomNhanVienDAO nhomNhanVienDAO = new NhomNhanVienDAO();
private final ThanhVienNhomDAO thanhVienNhomDAO = new ThanhVienNhomDAO();
```
* **Mục đích:** Tách biệt hoàn toàn tầng Service khỏi dữ liệu lưu trữ. Service không cần quan tâm dữ liệu nằm trong RAM hay trong Database, mà chỉ làm việc với các phương thức của DAO (`getAll()`, `getById()`, `getByPhongBan()`).

---

### B. Nhóm 4 phương thức bổ trợ (Private Helper Methods) & Kỹ thuật JOIN trong bộ nhớ

Trong CSDL quan hệ, ta dùng câu lệnh `JOIN` để lấy tên từ bảng danh mục thông qua khóa ngoại (FK). Trong Java OOP, ta xây dựng 4 phương thức `private helper` để ánh xạ từ ID sang Tên:

1. **`getTenPhongBan(int maPhongBan)`:**
   - *Cơ chế:* Gọi `phongBanDAO.getTenPhongBan(maPhongBan)`.
   - *Bên trong DAO:* Sử dụng Stream lọc `dsPhongBan` theo `maPhongBan`, trích xuất tên phòng ban qua `.map(PhongBan::gettenPhongBan)`.
   - *Xử lý an toàn:* Sử dụng `.findFirst().orElse("Chưa phân bổ")` để tránh lỗi `NullPointerException` nếu mã phòng ban không tồn tại.

2. **`getTenChucVu(int maChucVu)`:**
   - *Cơ chế:* Gọi `chucVuDAO.getTenChucVu(maChucVu)`.
   - *Xử lý an toàn:* Trả về `"Chưa có"` nếu không tìm thấy chức vụ khớp.

3. **`getTenNhanVien(int maNhanVien)`:**
   - *Cơ chế:* Gọi `nhanVienDAO.getById(maNhanVien).map(NhanVien::HoTenNV).orElse("Không xác định")`.
   - *Ý nghĩa:* Dùng phương thức tiện ích `HoTenNV()` kết hợp `Optional` để lấy đầy đủ họ tên của nhân viên một cách an toàn.

4. **`getTenNhom(int maNhom)`:**
   - *Cơ chế:* Gọi `nhomNhanVienDAO.getTenNhom(maNhom)`.
   - *Xử lý an toàn:* Trả về `"Không xác định"` nếu mã nhóm không tồn tại.

---

### C. Phân tích chi tiết 10 câu truy vấn nghiệp vụ của `NhanSuService`

---

#### CÂU 1 (Menu 11): `DanhSachNhanVien()` – In danh sách nhân sự toàn diện

* **Mục đích:** Xuất danh sách toàn bộ nhân viên trong công ty gồm: Mã NV, Họ tên, Giới tính, Phòng ban, Chức vụ và Người quản lý trực tiếp.
* **Luồng xử lý kỹ thuật:**
  1. Định nghĩa chuỗi format in bảng: `String formatHeader = "%-8s %-22s %-12s %-22s %-28s %-18s%n";`
  2. Lấy toàn bộ danh sách nhân viên qua `nhanVienDAO.getAll()`.
  3. Lặp qua từng nhân viên bằng `.forEach(nv -> { ... })`.
  4. Lấy tên phòng ban và chức vụ qua các hàm helper.
  5. **Xử lý đặc biệt cho Người quản lý trực tiếp:** Kiểm tra nếu `nv.getMaQuanLy() == null` thì gán nhãn `"Cấp cao nhất"` (đây là vị trí Giám đốc điều hành). Nếu có `maQuanLy`, gọi `getTenNhanVien(nv.getMaQuanLy())`.
* **Kết quả in màn hình Console thực tế:**
```text
Ma NV    Ho va Ten              Gioi Tinh    Phong Ban              Chuc Vu                      Nguoi Quan Ly     
--------------------------------------------------------------------------------------------------------------
1        Nguyen Van An          NAM          Phong CNTT             Giam doc Ky thuat            Cap cao nhat      
3        Le Hoang Cuong         NAM          Phong Kinh doanh       Truong phong Kinh doanh      Nguyen Van An     
5        Vu Thi Hoa             NU           Phong Nhan su          Truong phong Nhan su         Nguyen Van An     
2        Tran Thi Binh          NU           Phong CNTT             Lap trinh vien Senior        Nguyen Van An     
4        Pham Minh Duc          NAM          Phong CNTT             Lap trinh vien Junior        Tran Thi Binh     
6        Dang Quoc Khoa         NAM          Phong CNTT             Thuc tap sinh                Tran Thi Binh     
7        Ngo Thanh Giang        NU           Phong CNTT             Lap trinh vien Senior        Tran Thi Binh     
8        Bui Van Hieu           NAM          Phong Kinh doanh       Chuyen vien Sales            Le Hoang Cuong    
9        Dinh Thi Linh          NU           Phong Nhan su          Chuyen vien Nhan su          Vu Thi Hoa        
10       Hoang Anh Tuan         NAM          Phong Kinh doanh       Chuyen vien Sales            Le Hoang Cuong    
```
* **Câu hỏi phụ giảng viên hay hỏi:**
  > *Hỏi:* Tại sao lại cần kiểm tra `nv.getMaQuanLy() != null`?
  > *Đáp:* Dạ thưa thầy/cô, `maQuanLy` trong lớp `NhanVien` có kiểu `Integer` (đối tượng bao bọc - Wrapper Class) để có thể nhận giá trị `null` cho nhân sự cấp cao nhất. Nếu không kiểm tra `null` mà trực tiếp unbox sang kiểu nguyên thủy `int`, chương trình sẽ bắn lỗi `NullPointerException`.

---

#### CÂU 2 (Menu 12): `danhSachPhongBan()` – Cơ cấu tổ chức phòng ban

* **Mục đích:** Thống kê tất cả các phòng ban, kèm Họ tên Trưởng phòng và Tổng số lượng nhân viên hiện có trong từng phòng.
* **Luồng xử lý kỹ thuật:**
  1. Duyệt danh sách phòng ban: `phongBanDAO.getAll().forEach(pb -> { ... })`.
  2. Xác định Trưởng phòng: Nếu `pb.getmaTruongPhong() != null`, gọi `getTenNhanVien(pb.getmaTruongPhong())`, ngược lại hiển thị `"Chưa có"`.
  3. Đếm số lượng nhân viên: Gọi `nhanVienDAO.getByPhongBan(pb.getmaPhongBan()).size()` (hoặc dùng `.stream().filter(...).count()`).
  4. In kết quả theo bảng căn lề.
* **Kết quả in màn hình Console thực tế:**
```text
=== Cơ cấu tổ chức phòng ban ===
Phong ban            Truong phong              So NV     
------------------------------------------------------------
Phong CNTT           Nguyen Van An             5         
Phong Kinh doanh     Le Hoang Cuong            3         
Phong Nhan su        Vu Thi Hoa                2         
```
* **Câu hỏi phụ giảng viên hay hỏi:**
  > *Hỏi:* Thao tác đếm nhân viên này tương đương câu lệnh SQL nào?
  > *Đáp:* Dạ thưa thầy/cô, thao tác này tương đương với câu lệnh:
  > `SELECT ten_phong_ban, COUNT(ma_nhan_vien) FROM PHONG_BAN pb LEFT JOIN NHAN_VIEN nv ON pb.ma_phong_ban = nv.ma_phong_ban GROUP BY pb.ma_phong_ban;`

---

#### CÂU 3 (Menu 13): `traCuuNhanVienTheoPhongBan(String keyword)` – Tra cứu theo phòng ban

* **Mục đích:** Người dùng nhập Mã hoặc Tên phòng ban (ví dụ: `"Phong CNTT"` hoặc `"1"`), hệ thống lọc và in ra danh sách nhân viên thuộc phòng ban đó.
* **Luồng xử lý kỹ thuật:**
  1. **Validation:** Kiểm tra nếu chuỗi tìm kiếm bị rỗng (`keyword.trim().isEmpty()`) thì in thông báo nhắc nhở và thoát hàm.
  2. Chuẩn hóa từ khóa tìm kiếm về chữ thường: `String key = keyword.trim().toLowerCase();`
  3. Sử dụng Stream với bộ lọc đa điều kiện:
     ```java
     .filter(nv -> String.valueOf(nv.getMaPhongBan()).equalsIgnoreCase(key) 
                || getTenPhongBan(nv.getMaPhongBan()).toLowerCase().contains(key))
     ```
     Điều này cho phép người dùng nhập bằng **Mã số** (ví dụ "1") hay nhập bằng **Tên phòng** (ví dụ "cntt", "kinh doanh") hệ thống đều nhận diện chính xác.
  4. Thu thập kết quả vào `List<NhanVien>` bằng `.collect(Collectors.toList())`. Kiểm tra nếu danh sách rỗng thì thông báo không tìm thấy.
* **Kết quả in màn hình Console thực tế (khi tra cứu "Phong CNTT"):**
```text
=== Danh sách nhân viên thuộc: Phong CNTT ===
Ma NV    Ho va Ten              Chuc vu                 
------------------------------------------------------------
1        Nguyen Van An          Giam doc Ky thuat       
2        Tran Thi Binh          Lap trinh vien Senior   
4        Pham Minh Duc          Lap trinh vien Junior   
6        Dang Quoc Khoa         Thuc tap sinh           
7        Ngo Thanh Giang        Lap trinh vien Senior   
```

---

#### CÂU 4 (Menu 14): `top3NhanVienThamNien()` – Nhân sự thâm niên cao nhất

* **Mục đích:** Tìm và in ra các nhân viên có ngày vào làm (`ngay_vao_lam`) sớm nhất công ty.
* **Luồng xử lý kỹ thuật:**
  1. Khởi tạo Stream trên danh sách nhân viên: `nhanVienDAO.getAll().stream()`.
  2. Sắp xếp tăng dần theo thời gian vào làm:
     ```java
     .sorted(Comparator.comparing(NhanVien::getNgayVaoLam))
     ```
     Thuộc tính `ngayVaoLam` có kiểu `LocalDate`, nên việc sắp xếp này sẽ đưa ngày xa nhất trong quá khứ (vào làm sớm nhất) lên đầu danh sách.
  3. Dùng toán tử trung gian `.limit(3)` để cắt lấy đúng 3 nhân viên đầu tiên.
  4. Duyệt `.forEach()` để in kết quả ra bảng.
* **Kết quả in màn hình Console thực tế:**
```text
=== Top 3 nhân viên thâm niên nhất công ty ===
Ma NV    Ho va Ten              Ngay vao lam 
---------------------------------------------
3        Le Hoang Cuong         2017-03-01   
1        Nguyen Van An          2018-01-10   
2        Tran Thi Binh          2019-05-20   
```
* **Lưu ý quan trọng khi bảo vệ:**
  > Trong đề bài mô tả có ghi "Top 5", nhưng trong mã nguồn của bạn đang viết hàm `top3NhanVienThamNien()` với `.limit(3)`. Nếu thầy/cô yêu cầu hiển thị Top 5, bạn chỉ cần giải thích: *"Dạ thưa thầy/cô, để màn hình Console hiển thị gọn gàng em đang để limit là 3, khi cần chuyển sang Top 5 ta chỉ việc đổi tham số thành `.limit(5)` là chương trình tự động lấy 5 nhân sự thâm niên nhất."*

---

#### CÂU 5 (Menu 15): `danhSachCapDuoiCuaQuanLy(int maQuanLy)` – Cây phân cấp quản lý

* **Mục đích:** Nhập mã của một người quản lý, in ra danh sách toàn bộ nhân viên cấp dưới do người đó trực tiếp phụ trách.
* **Luồng xử lý kỹ thuật:**
  1. Lọc trong danh sách nhân viên:
     ```java
     .filter(nv -> nv.getMaQuanLy() != null && nv.getMaQuanLy() == maQuanLy)
     ```
  2. Kiểm tra `nv.getMaQuanLy() != null` trước là điều kiện tiên quyết để tránh lỗi `NullPointerException`.
  3. Gom kết quả vào `List<NhanVien> dsCapDuoi`. Nếu rỗng thông báo *"Không có cấp dưới trực tiếp"*, ngược lại in ra danh sách kèm chức vụ.
* **Kết quả in màn hình Console thực tế (với Quản lý mã 1 - Nguyễn Văn An):**
```text
=== Danh sách cấp dưới của quản lý: Nguyen Van An (Mã: 1) ===
Ma NV    Ho va Ten              Chuc vu                 
------------------------------------------------------------
3        Le Hoang Cuong         Truong phong Kinh doanh 
5        Vu Thi Hoa             Truong phong Nhan su    
2        Tran Thi Binh          Lap trinh vien Senior   
```

---

#### CÂU 6 (Menu 16): `chiTietNhomNhanVien(int maNhom)` – Chi tiết một nhóm làm việc / dự án

* **Mục đích:** Nhập mã nhóm (ví dụ: `1` - Đội Dự án Mobile App), in thông tin chung của nhóm, tên Trưởng nhóm và danh sách các thành viên cùng vai trò cụ thể.
* **Luồng xử lý kỹ thuật:**
  1. Tìm nhóm theo mã: Gọi `nhomNhanVienDAO.getById(maNhom)`. Nếu không tồn tại thì báo lỗi và dừng hàm.
  2. Lấy tên Trưởng nhóm bằng `getTenNhanVien(nhom.getmaTruongNhom())`.
  3. **Truy vấn bảng trung gian `THANH_VIEN_NHOM`:**
     - Lọc các bản ghi thành viên có `tv.getmaNhom().equals(maNhom)`.
     - Với mỗi thành viên, truy xuất đối tượng `NhanVien` để lấy Họ tên và in kèm vai trò (`tv.getVaiTro()`) cùng ngày tham gia.
* **Kết quả in màn hình Console thực tế (với Nhóm 1):**
```text
=== Thông tin nhóm: Doi Du an Mobile App ===
Trưởng nhóm: Tran Thi Binh
Ngày thành lập: 2024-01-15
Mô tả: Lam ung dung Mobile
Danh sách thành viên:
- Tran Thi Binh | Vai trò: Tech Lead | Ngày tham gia: 2024-01-15
- Pham Minh Duc | Vai trò: Mobile Dev | Ngày tham gia: 2024-02-01
- Ngo Thanh Giang | Vai trò: Tester | Ngày tham gia: 2024-03-01
```

---

#### CÂU 7 (Menu 17): `thongKeNhanVienDaNhiemNhieuNhom()` – Thống kê nhân sự đa nhiệm

* **Mục đích:** Liệt kê các nhân viên đang tham gia từ 2 nhóm làm việc trở lên kèm tên các nhóm họ trực thuộc.
* **Luồng xử lý kỹ thuật (Truy vấn nâng cao với `groupingBy` và `joining`):**
  1. **Bước 1 (Gom nhóm theo Mã nhân viên):**
     ```java
     Map<Integer, List<ThanhVienNhom>> dsTheoNhanVien = thanhVienNhomDAO.getAll().stream()
         .collect(Collectors.groupingBy(ThanhVienNhom::getnhomNhanVien));
     ```
     Tạo ra một cấu trúc `Map` với Key là Mã nhân viên và Value là Danh sách các nhóm nhân viên đó tham gia.
  2. **Bước 2 (Lọc nhân sự đa nhiệm):**
     Lọc các Entry trong Map có số lượng phần tử $\ge 2$:
     ```java
     .filter(entry -> entry.getValue().size() >= 2)
     ```
  3. **Bước 3 (Nối chuỗi tên các nhóm):**
     Dùng Stream biến đổi danh sách mã nhóm thành chuỗi tên ngăn cách bằng dấu phẩy:
     ```java
     String tenCacNhom = entry.getValue().stream()
         .map(tv -> getTenNhom(tv.getmaNhom()))
         .collect(Collectors.joining(", "));
     ```
* **Kết quả in màn hình Console thực tế:**
```text
=== Danh sách nhân viên tham gia từ 2 nhóm trở lên ===
Ma NV    Ho va Ten              So nhom   Cac nhom tham gia                       
--------------------------------------------------------------------------------
2        Tran Thi Binh          2         Doi Du an Mobile App, Doi Nghien cuu AI 
```
* **Ý nghĩa quản trị MIS:** Giúp Ban Giám đốc nhận diện nhân sự chủ chốt (Key Person) đang gánh vác nhiều trọng trách dự án để có chế độ đãi ngộ hoặc cân đối tải công việc tránh kiệt sức (burnout).

---

#### CÂU 8 (Menu 18): `thongKeNhanSuTheoChucVu()` – Phân bổ nhân sự theo chức vụ

* **Mục đích:** Thống kê số lượng nhân viên hiện có theo từng chức danh trong công ty.
* **Luồng xử lý kỹ thuật:**
  1. Duyệt qua từng chức vụ trong danh mục: `chucVuDAO.getAll().forEach(cv -> { ... })`.
  2. Với mỗi chức vụ `cv`, đếm số nhân viên tương ứng:
     ```java
     long soLuong = nhanVienDAO.getAll().stream()
         .filter(nv -> nv.getMaChucVu() == cv.getmaChucVu())
         .count();
     ```
  3. Chỉ in ra các chức vụ hiện có nhân sự đảm nhiệm (`if (soLuong > 0)`).
* **Kết quả in màn hình Console thực tế:**
```text
=== Thống kê số lượng nhân sự theo chức vụ ===
Chuc vu                        So luong  
----------------------------------------
Giam doc Ky thuat              1         
Truong phong Kinh doanh        1         
Lap trinh vien Senior          2         
Lap trinh vien Junior          1         
Chuyen vien Nhan su            1         
Thuc tap sinh                  1         
Truong phong Nhan su           1         
Chuyen vien Sales              2         
```

---

#### CÂU 9 (Menu 19): `danhSachNhanVienChuaVaoNhom()` – Nhân viên chưa vào nhóm nào

* **Mục đích:** Lọc danh sách các nhân viên chưa tham gia bất kỳ nhóm làm việc hoặc dự án nào.
* **Luồng xử lý kỹ thuật:**
  1. Duyệt danh sách nhân viên: `nhanVienDAO.getAll().stream()`.
  2. Sử dụng toán tử kiểm tra phủ định `.noneMatch()`:
     ```java
     .filter(nv -> thanhVienNhomDAO.getAll().stream()
         .noneMatch(tv -> tv.getnhomNhanVien() == nv.getMaNhanVien()))
     ```
  3. Thu thập kết quả và in ra Mã NV, Họ tên cùng Phòng ban.
* **Kết quả in màn hình Console thực tế:**
```text
=== Danh sách nhân viên chưa tham gia nhóm nào ===
Ma NV    Ho va Ten              Phong Ban             
------------------------------------------------------
3        Le Hoang Cuong         Phong Kinh doanh      
5        Vu Thi Hoa             Phong Nhan su         
6        Dang Quoc Khoa         Phong CNTT            
8        Bui Van Hieu           Phong Kinh doanh      
9        Dinh Thi Linh          Phong Nhan su         
10       Hoang Anh Tuan         Phong Kinh doanh      
```
* **Câu hỏi phụ giảng viên hay hỏi:**
  > *Hỏi:* Toán tử `.noneMatch()` ở đây hoạt động tương đương câu lệnh SQL nào?
  > *Đáp:* Dạ thưa thầy/cô, `.noneMatch()` hoạt động tương đương mệnh đề `NOT EXISTS` hoặc `NOT IN` trong SQL:
  > `SELECT * FROM NHAN_VIEN nv WHERE NOT EXISTS (SELECT 1 FROM THANH_VIEN_NHOM tv WHERE tv.ma_nhan_vien = nv.ma_nhan_vien);`
  > Nó sẽ trả về `true` khi không có bất kỳ bản ghi nào trong bảng thành viên nhóm trùng với mã của nhân viên đang xét.

---

#### CÂU 10 (Menu 20): `timNhanSuTheoTuKhoa(String keyword)` – Tìm kiếm nhân sự đa tiêu chí

* **Mục đích:** Tìm kiếm nhân viên gần đúng theo Họ tên, Số điện thoại hoặc Email và in thông tin tóm tắt ra màn hình.
* **Luồng xử lý kỹ thuật:**
  1. Kiểm tra tính hợp lệ của từ khóa (`null` hoặc rỗng).
  2. Chuẩn hóa từ khóa về chữ thường: `String key = keyword.trim().toLowerCase();`
  3. Áp dụng Stream lọc kết hợp 3 tiêu chí thông qua toán tử HOẶC (`||`):
     ```java
     .filter(nv -> nv.HoTenNV().toLowerCase().contains(key)
                || nv.getSoDienThoai().toLowerCase().contains(key)
                || nv.getEmail().toLowerCase().contains(key))
     ```
  4. Thu thập kết quả vào `List<NhanVien>` và in ra bảng thông tin chi tiết.
* **Kết quả in màn hình Console thực tế (với từ khóa `"linh"`):**
```text
=== Kết quả tìm kiếm với từ khóa: 'linh' ===
Ma NV    Ho va Ten              Email                          So DT       
---------------------------------------------------------------------------
9        Dinh Thi Linh          linh.dinh@company.com          0999888777  
```
