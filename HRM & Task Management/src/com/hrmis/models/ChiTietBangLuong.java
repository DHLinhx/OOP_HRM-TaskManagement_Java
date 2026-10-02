package com.hrmis.models;

import com.hrmis.enums.*;

public class ChiTietBangLuong {
    private int maChiTietLuong;
    private int maBangLuong;
    private String tenKhoanMuc;
    private LoaiKhoanMuc loaiKhoanMuc;
    private double soTien;
    private String ghiChu;

    public ChiTietBangLuong(int maChiTietLuong, int maBangLuong, String tenKhoanMuc, LoaiKhoanMuc loaiKhoanMuc, double soTien, String ghiChu) {
        this.maChiTietLuong = maChiTietLuong;
        this.maBangLuong = maBangLuong;
        this.tenKhoanMuc = tenKhoanMuc;
        this.loaiKhoanMuc = loaiKhoanMuc;
        this.soTien = soTien;
        this.ghiChu = ghiChu;
    }

    public int getMaChiTietLuong() { return maChiTietLuong; }
    public void setMaChiTietLuong(int maChiTietLuong) { this.maChiTietLuong = maChiTietLuong; }

    public int getMaBangLuong() { return maBangLuong; }
    public void setMaBangLuong(int maBangLuong) { this.maBangLuong = maBangLuong; }

    public String getTenKhoanMuc() { return tenKhoanMuc; }
    public void setTenKhoanMuc(String tenKhoanMuc) { this.tenKhoanMuc = tenKhoanMuc; }

    public LoaiKhoanMuc getLoaiKhoanMuc() { return loaiKhoanMuc; }
    public void setLoaiKhoanMuc(LoaiKhoanMuc loaiKhoanMuc) { this.loaiKhoanMuc = loaiKhoanMuc; }

    public double getSoTien() { return soTien; }
    public void setSoTien(double soTien) { this.soTien = soTien; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
}
