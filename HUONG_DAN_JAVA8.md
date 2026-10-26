# Áp dụng Java 8 vào bài thư viện số

## 1. Mục tiêu

Giữ abstraction, inheritance, polymorphism và interface của bài OOP. Dùng functional programming cho lọc, ánh xạ, tính tổng, thống kê và truyền hành vi. Các bước thay đổi kho/phiếu vẫn thực hiện rõ ràng, tuần tự trong borrow, renew, returnItem.

## 2. Functional interface là gì?

Là interface có đúng một phương thức abstract (không tính các phương thức tương ứng public của Object). Có thể chứa default/static method. Annotation `@FunctionalInterface` giúp compiler phát hiện khai báo sai. Lambda không có kiểu độc lập; kiểu đích của nó là một functional interface.

| Kiểu | Hàm chính | Vai trò trong project |
|---|---|---|
| Predicate<T> | boolean test(T t) | Điều kiện lọc tài liệu/phiếu |
| Consumer<T> | void accept(T t) | Xuất log ra console hoặc lưu vào List |
| Supplier<T> | T get() | Cung cấp ngày hệ thống hoặc ngày cố định |
| Function<T,R> | R apply(T t) | Tài liệu → tiêu đề, phiếu → dòng báo cáo |
| BiFunction<T,U,R> | R apply(T t,U u) | Phiếu + ngày → phí tạm tính |
| ToLongFunction<T> | long applyAsLong(T t) | Đối số của mapToLong, giảm boxing |
| FeeCalculator | long calculate(Loan,LocalDate) | Interface tự định nghĩa cho quy tắc phí |

Các interface chuẩn nằm trong `java.util.function`. Chúng đã là functional interface; không cần tự khai báo lại Predicate, Consumer, Supplier, Function.

## 3. Predicate: lọc và kết hợp điều kiện

`LoanPredicates.java`:

```java
public static final Predicate<Loan> ACTIVE = Loan::isActive;
public static final Predicate<Loan> RETURNED = ACTIVE.negate();

public static Predicate<Loan> ofUser(String userId) {
    return loan -> loan.getUser().getId().equals(userId);
}
```

Trong borrow:

```java
long active = loans.stream()
    .filter(LoanPredicates.ACTIVE.and(LoanPredicates.ofUser(userId)))
    .count();
```

Thực thi: tạo stream từ lịch sử → giữ phiếu ACTIVE và đúng người → đếm. Phiếu đã trả không làm mất hạn mức mượn.
`and` yêu cầu cả hai đúng; `or` yêu cầu ít nhất một; `negate` đảo điều kiện. and/or có short-circuit.

Trong App:

```java
Predicate<LibraryItem> available = LibraryItem::isAvailableForBorrow;
Predicate<LibraryItem> renewable = item -> item instanceof Renewable
        && ((Renewable) item).canRenew();
lib.findItems(available.and(renewable));
```

Đây chỉ là lọc khả năng của tài liệu, chưa phải kiểm tra tất cả điều kiện mượn của một người dùng. Quyền truy cập và hạn mức vẫn kiểm tra trong borrow.

## 4. Consumer: truyền nơi nhận kết quả

```java
Consumer<String> console = System.out::println;
```

Service nhận Consumer qua constructor và gọi:

```java
output.accept("[BORROW-OK] " + loan);
```

Bộ kiểm tra thay console bằng List:

```java
List<String> log = new ArrayList<>();
LibraryService lib = new LibraryService(
        log::add, () -> d0, FeeCalculator.standard());
```

Cùng nghiệp vụ nhưng có thể in ra màn hình hoặc thu thập log để kiểm tra. Không cần sửa borrow/returnItem.

## 5. Supplier: cung cấp ngày một cách chủ động

```java
Supplier<LocalDate> demoDate = () -> LocalDate.of(2025, 12, 1);
```

Overload borrow không nhận ngày sử dụng:

```java
return borrow(userId, itemId, today.get());
```

Constructor mặc định dùng `LocalDate::now`; demo dùng ngày cố định. Nhờ đó bài chạy lần nào cũng cho hạn trả giống nhau, không phụ thuộc ngày máy tính. Supplier được gọi mỗi lần cần giá trị, không mặc nhiên cache kết quả.

## 6. Function và andThen: biến đổi dữ liệu

```java
Function<LibraryItem, String> describe = LibraryItem::info;
items.values().stream().map(describe).forEach(output);
```

`map` chuyển LibraryItem thành String; `forEach` đưa String đến Consumer.

```java
Function<Loan, String> format = Loan::toString;
Function<Loan, String> indent = format.andThen(text -> "  " + text);
```

`indent.apply(loan)` chạy `format` trước, sau đó thêm khoảng trắng. `andThen` tạo hàm mới; chưa tự chạy khi khai báo.

## 7. Interface tự định nghĩa FeeCalculator

```java
@FunctionalInterface
public interface FeeCalculator {
    long calculate(Loan loan, LocalDate returnDate);

    static FeeCalculator standard() {
        return (loan, date) -> loan.lateDays(date) * Math.max(
                loan.getUser().getLoanPolicy().lateFeePerDay(),
                loan.getItem().lateFeePerDayForThisItem());
    }
}
```

Đối tượng Loan giữ calculator nhận khi tạo. `calculateFee(date)` ủy quyền cho calculator. Quy tắc mặc định vẫn dùng đa hình của người dùng/tài liệu.

Có thể thay quy tắc bằng lambda:

```java
LibraryService freeLibrary = new LibraryService(
        System.out::println,
        LocalDate::now,
        (loan, date) -> 0L);
```

Đây là cách truyền một chiến lược tính phí qua functional interface. Bộ kiểm tra có xác minh tình huống này. Calculator nên thuần: không sửa kho, trạng thái phiếu, không phụ thuộc trạng thái thay đổi ngoài dự kiến. Khi trả, phí được lưu vào settledFee để báo cáo không tính lại lịch sử theo một lambda có trạng thái thay đổi.

## 8. Stream: tính tổng phí

```java
return loans.stream()
        .filter(LoanPredicates.RETURNED)
        .mapToLong(Loan::returnedFee)
        .sum();
```

`filter` giữ phiếu đã trả, `mapToLong` chuyển thành LongStream, `sum` cộng. Đối số Loan::returnedFee ở đây tương thích ToLongFunction<Loan>. Stream rỗng cho tổng 0.

Không nhầm `map` với `forEach`: map biến đổi và trả stream; forEach là thao tác kết thúc, trả void.

## 9. groupingBy và counting

```java
return loans.stream().collect(Collectors.groupingBy(
        loan -> loan.getUser().getUserType(),
        () -> new EnumMap<UserType, Long>(UserType.class),
        Collectors.counting()));
```

Ba đối số lần lượt là hàm xác định nhóm, Supplier tạo Map kết quả, và collector đếm trong nhóm. Kết quả demo: STUDENT=4, LECTURER=1. Đây là số lượt mượn thành công trong toàn lịch sử, bao gồm đã trả. Loại chưa có lượt mượn không có entry.

Thống kê tài liệu nhóm theo ID, tránh gộp nhầm hai tài liệu khác mã nhưng trùng tên. `mostBorrowedItems()` tìm max và trả tất cả tài liệu đồng hạng. Nếu chưa có giao dịch thì trả danh sách rỗng.

## 10. flatMap, distinct, sorted, joining

```java
return activeLoansByUser().values().stream()
        .flatMap(Collection::stream)
        .map(Loan::getItem)
        .map(LibraryItem::getTitle)
        .distinct()
        .sorted()
        .collect(Collectors.toList());
```

Ban đầu có nhiều List<Loan>, mỗi List của một người dùng. flatMap trải chúng thành một stream Loan; map lấy tiêu đề; distinct bỏ tiêu đề lặp; sorted sắp xếp; collect tạo List. Đây là danh sách tiêu đề, nên hai tài liệu cùng tiêu đề có thể gộp ở màn hình này; thống kê số lượt vẫn nhóm theo ID.

`Collectors.joining(", ")` nối stream String thành dòng văn bản. Không dùng `Stream.toList()` vì không có trong Java 8.

## 11. Optional và exception supplier

```java
public Optional<Loan> findLoan(String id) {
    return loans.stream()
            .filter(loan -> loan.getLoanId().equals(id))
            .findFirst();
}
```

Nếu tìm không thấy trả Optional.empty(). Với chức năng bắt buộc có phiếu:

```java
return findLoan(id).orElseThrow(() ->
        new IllegalArgumentException("Khong tim thay phieu: " + id));
```

Lambda là Supplier tạo exception, chỉ gọi khi Optional rỗng. Demo dùng map/ifPresent/orElse để hiển thị kết quả tra cứu. Không gọi get() khi chưa chắc có giá trị. `orElse(value)` đánh giá value ngay; `orElseGet(supplier)` tạo giá trị mặc định khi rỗng.

## 12. BiFunction: phí tạm tính

```java
BiFunction<Loan, LocalDate, Long> estimate = Loan::calculateFee;
return loans.stream()
        .filter(LoanPredicates.overdueOn(date))
        .mapToLong(loan -> estimate.apply(loan, date))
        .sum();
```

Một method reference đến instance method qua tên lớp có thể nhận đối tượng làm tham số đầu. Với Loan::calculateFee, hai đầu vào là Loan và LocalDate. Giá trị long được boxing thành Long theo BiFunction, rồi unboxing trong mapToLong. Nếu ưu tiên tránh boxing, dùng ToLongBiFunction<Loan,LocalDate> và applyAsLong.

Ngày 16/12/2025, các phiếu chưa trả:

| Phiếu | Trễ | Phí/ngày | Tạm tính |
|---|---:|---:|---:|
| L0003: giảng viên mượn luận văn | 1 | 5000 | 5000 |
| L0004: sinh viên mượn sách giấy | 8 | 3000 | 24000 |
| L0005: sinh viên mượn tạp chí | 8 | 2000 | 16000 |

Tổng tạm tính = 45000. Tổng đã chốt = 15000. Hàm tạm tính không đánh dấu đã trả, không thay đổi kho hoặc phí đã chốt. Ngày dùng cho báo cáo là thời điểm hiện tại/cuối demo, không phải tái dựng lịch sử trạng thái quá khứ.

## 13. Thực thi lazy và method reference

Các thao tác filter/map/sorted là intermediate operation; pipeline chỉ được thực hiện khi có terminal operation như collect, count, sum, forEach, findFirst. Mỗi stream chỉ dùng một lần, muốn chạy lại phải tạo stream mới từ collection.

| Lambda | Method reference |
|---|---|
| loan -> loan.isActive() | Loan::isActive |
| item -> item.info() | LibraryItem::info |
| text -> System.out.println(text) | System.out::println |
| () -> LocalDate.now() | LocalDate::now |
| () -> new LinkedHashMap<>() | LinkedHashMap::new |

## 14. Java 17 sang Java 8

- LoanPolicy từ record thành final class với private final fields.
- instanceof pattern matching thành instanceof + cast tường minh.
- String.isBlank thành trim().isEmpty(). Đây là kiểm tra trắng đơn giản; không có cùng ngữ nghĩa Unicode whitespace đầy đủ với isBlank.
- Giữ LocalDate, Optional, Stream, java.util.function vì đã có trong Java 8.
- Không thêm Stream.toList(), List.of(), var hoặc switch expression.

## 15. OOP và functional programming phối hợp

LibraryItem/LibraryUser vẫn là abstract class, lớp con override hành vi. Borrowable có ba phương thức abstract nên không phải functional interface. Renewable, AccessControlled có một phương thức abstract, nhưng vẫn được triển khai bởi lớp tài liệu để thể hiện khả năng. FeeCalculator có annotation và được truyền bằng lambda vì cần thay đổi hành vi tính phí.

Không đưa cập nhật kho vào filter/map/peek. Không dùng parallelStream cho mượn/trả: collections và số lượng trong bài chưa được đồng bộ. Lambda trong Supplier/Consumer giúp test mà không cần thư viện mock.
