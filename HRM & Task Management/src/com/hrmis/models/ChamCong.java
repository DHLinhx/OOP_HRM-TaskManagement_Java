package com.hrmis.models;

import com.hrmis.enums.TrangThaiCong;
import java.time.LocalDate;
import java.time.LocalTime;

public class ChamCong {
    private int maChamCong;
    private int maNhanVien;
    private int maCa;
    private LocalDate ngayLamViec;
    private LocalTime gioVaoThucTe;
    private LocalTime gioRaThucTe;
    private double soGioLamThem;
    private TrangThaiCong trangThaiCong;

    public ChamCong() {
    }

    public ChamCong(int maChamCong, int maNhanVien, int maCa, LocalDate ngayLamViec,
            LocalTime gioVaoThucTe, LocalTime gioRaThucTe, double soGioLamThem, TrangThaiCong trangThaiCong) {
        this.maChamCong = maChamCong;
        this.maNhanVien = maNhanVien;
        this.maCa = maCa;
        this.ngayLamViec = ngayLamViec;
        this.gioVaoThucTe = gioVaoThucTe;
        this.gioRaThucTe = gioRaThucTe;
        this.soGioLamThem = soGioLamThem;
        this.trangThaiCong = trangThaiCong;
    }

    public int getMaChamCong() {
        return maChamCong;
    }

    public void setMaChamCong(int maChamCong) {
        this.maChamCong = maChamCong;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getMaCa() {
        return maCa;
    }

    public void setMaCa(int maCa) {
        this.maCa = maCa;
    }

    public LocalDate getNgayLamViec() {
        return ngayLamViec;
    }

    public void setNgayLamViec(LocalDate ngayLamViec) {
        this.ngayLamViec = ngayLamViec;
    }

    public LocalTime getGioVaoThucTe() {
        return gioVaoThucTe;
    }

    public void setGioVaoThucTe(LocalTime gioVaoThucTe) {
        this.gioVaoThucTe = gioVaoThucTe;
    }

    public LocalTime getGioRaThucTe() {
        return gioRaThucTe;
    }

    public void setGioRaThucTe(LocalTime gioRaThucTe) {
        this.gioRaThucTe = gioRaThucTe;
    }

    public double getSoGioLamThem() {
        return soGioLamThem;
    }

    public void setSoGioLamThem(double soGioLamThem) {
        this.soGioLamThem = soGioLamThem;
    }

    public TrangThaiCong getTrangThaiCong() {
        return trangThaiCong;
    }

    public void setTrangThaiCong(TrangThaiCong trangThaiCong) {
        this.trangThaiCong = trangThaiCong;
    }

    @Override
    public String toString() {
        return "ChamCong{" +
                "maChamCong=" + maChamCong +
                ", maNhanVien=" + maNhanVien +
                ", maCa=" + maCa +
                ", ngayLamViec=" + ngayLamViec +
                ", gioVaoThucTe=" + gioVaoThucTe +
                ", gioRaThucTe=" + gioRaThucTe +
                ", soGioLamThem=" + soGioLamThem +
                ", trangThaiCong=" + trangThaiCong +
                '}';
    }
}
