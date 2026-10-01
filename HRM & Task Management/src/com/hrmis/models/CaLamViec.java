package com.hrmis.models;

import java.time.LocalTime;

public class CaLamViec {
    // 1. Khai báo các thuộc tính (Encapsulation: luôn để private)
    private int maCa;
    private String tenCa;
    private LocalTime gioBatDau;
    private LocalTime gioKetThuc;

    // 2. Constructor không tham số (No-args Constructor)
    public CaLamViec() {
    }

    // 3. Constructor đầy đủ tham số (khớp với DatabaseMock)
    public CaLamViec(int maCa, String tenCa, LocalTime gioBatDau, LocalTime gioKetThuc) {
        this.maCa = maCa;
        this.tenCa = tenCa;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
    }

    // 4. Các phương thức Getter và Setter
    public int getMaCa() {
        return maCa;
    }

    public void setMaCa(int maCa) {
        this.maCa = maCa;
    }

    public String getTenCa() {
        return tenCa;
    }

    public void setTenCa(String tenCa) {
        this.tenCa = tenCa;
    }

    public LocalTime getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(LocalTime gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public LocalTime getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(LocalTime gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    // 5. Ghi đè toString để thuận tiện khi in ấn/kiểm tra dữ liệu
    @Override
    public String toString() {
        return "CaLamViec{" +
                "maCa=" + maCa +
                ", tenCa='" + tenCa + '\'' +
                ", gioBatDau=" + gioBatDau +
                ", gioKetThuc=" + gioKetThuc +
                '}';
    }
}
