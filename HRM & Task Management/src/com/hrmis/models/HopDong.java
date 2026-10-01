package com.hrmis.models;

import com.hrmis.enums.LoaiHopDong;
import com.hrmis.enums.TrangThaiHopDong;
import java.time.LocalDate;

public class HopDong {
    // 1. Khai báo các thuộc tính (khớp chuẩn ERD)
    private int maHopDong;
    private int maNhanVien;
    private String soHopDong;
    private LoaiHopDong loaiHopDong;
    private LocalDate ngayHieuLuc;
    private LocalDate ngayHetHan; // Có thể nhận null nếu là hợp đồng vô thời hạn
    private double luongThoaThuan;
    private TrangThaiHopDong trangThai;

    // 2. Constructor mặc định
    public HopDong() {
    }

    // 3. Constructor đầy đủ tham số (khớp với DatabaseMock)
    public HopDong(int maHopDong, int maNhanVien, String soHopDong, LoaiHopDong loaiHopDong,
            LocalDate ngayHieuLuc, LocalDate ngayHetHan, double luongThoaThuan, TrangThaiHopDong trangThai) {
        this.maHopDong = maHopDong;
        this.maNhanVien = maNhanVien;
        this.soHopDong = soHopDong;
        this.loaiHopDong = loaiHopDong;
        this.ngayHieuLuc = ngayHieuLuc;
        this.ngayHetHan = ngayHetHan;
        this.luongThoaThuan = luongThoaThuan;
        this.trangThai = trangThai;
    }

    // 4. Các phương thức Getter và Setter
    public int getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(int maHopDong) {
        this.maHopDong = maHopDong;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getSoHopDong() {
        return soHopDong;
    }

    public void setSoHopDong(String soHopDong) {
        this.soHopDong = soHopDong;
    }

    public LoaiHopDong getLoaiHopDong() {
        return loaiHopDong;
    }

    public void setLoaiHopDong(LoaiHopDong loaiHopDong) {
        this.loaiHopDong = loaiHopDong;
    }

    public LocalDate getNgayHieuLuc() {
        return ngayHieuLuc;
    }

    public void setNgayHieuLuc(LocalDate ngayHieuLuc) {
        this.ngayHieuLuc = ngayHieuLuc;
    }

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(LocalDate ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public double getLuongThoaThuan() {
        return luongThoaThuan;
    }

    public void setLuongThoaThuan(double luongThoaThuan) {
        this.luongThoaThuan = luongThoaThuan;
    }

    public TrangThaiHopDong getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThaiHopDong trangThai) {
        this.trangThai = trangThai;
    }

    // 5. Ghi đè phương thức toString
    @Override
    public String toString() {
        return "HopDong{" +
                "maHopDong=" + maHopDong +
                ", maNhanVien=" + maNhanVien +
                ", soHopDong='" + soHopDong + '\'' +
                ", loaiHopDong=" + loaiHopDong +
                ", ngayHieuLuc=" + ngayHieuLuc +
                ", ngayHetHan=" + ngayHetHan +
                ", luongThoaThuan=" + luongThoaThuan +
                ", trangThai=" + trangThai +
                '}';
    }
}
