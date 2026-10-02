package com.hrmis.models;

import com.hrmis.enums.*;
import java.time.LocalDate;

public class BangLuong {
    private int maBangLuong;
    private int maNhanVien;
    private int kyThang;
    private int kyNam;
    private double tongThuNhap;
    private double tongKhauTru;
    private double thucLanh;
    private TrangThaiChiTra trangThaiChiTra;
    private LocalDate ngayChiTra;

    public BangLuong(int maBangLuong, int maNhanVien, int kyThang, int kyNam, double tongThuNhap, double tongKhauTru, double thucLanh, TrangThaiChiTra trangThaiChiTra, LocalDate ngayChiTra) {
        this.maBangLuong = maBangLuong;
        this.maNhanVien = maNhanVien;
        this.kyThang = kyThang;
        this.kyNam = kyNam;
        this.tongThuNhap = tongThuNhap;
        this.tongKhauTru = tongKhauTru;
        this.thucLanh = thucLanh;
        this.trangThaiChiTra = trangThaiChiTra;
        this.ngayChiTra = ngayChiTra;
    }

    public int getMaBangLuong() { return maBangLuong; }
    public void setMaBangLuong(int maBangLuong) { this.maBangLuong = maBangLuong; }

    public int getMaNhanVien() { return maNhanVien; }
    public void setMaNhanVien(int maNhanVien) { this.maNhanVien = maNhanVien; }

    public int getKyThang() { return kyThang; }
    public void setKyThang(int kyThang) { this.kyThang = kyThang; }

    public int getKyNam() { return kyNam; }
    public void setKyNam(int kyNam) { this.kyNam = kyNam; }

    public double getTongThuNhap() { return tongThuNhap; }
    public void setTongThuNhap(double tongThuNhap) { this.tongThuNhap = tongThuNhap; }

    public double getTongKhauTru() { return tongKhauTru; }
    public void setTongKhauTru(double tongKhauTru) { this.tongKhauTru = tongKhauTru; }

    public double getThucLanh() { return thucLanh; }
    public void setThucLanh(double thucLanh) { this.thucLanh = thucLanh; }

    public TrangThaiChiTra getTrangThaiChiTra() { return trangThaiChiTra; }
    public void setTrangThaiChiTra(TrangThaiChiTra trangThaiChiTra) { this.trangThaiChiTra = trangThaiChiTra; }

    public LocalDate getNgayChiTra() { return ngayChiTra; }
    public void setNgayChiTra(LocalDate ngayChiTra) { this.ngayChiTra = ngayChiTra; }
}
