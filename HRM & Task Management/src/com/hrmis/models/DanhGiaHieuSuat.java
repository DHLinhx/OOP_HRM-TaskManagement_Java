package com.hrmis.models;

import com.hrmis.enums.*;

public class DanhGiaHieuSuat {
    private int maDanhGia;
    private int maNhanVien;
    private int nguoiDanhGia;
    private String kyDanhGia;
    private int nam;
    private double diemTrungBinh;
    private XepLoai xepLoai;

    public DanhGiaHieuSuat(int maDanhGia, int maNhanVien, int nguoiDanhGia, String kyDanhGia, int nam, double diemTrungBinh, XepLoai xepLoai) {
        this.maDanhGia = maDanhGia;
        this.maNhanVien = maNhanVien;
        this.nguoiDanhGia = nguoiDanhGia;
        this.kyDanhGia = kyDanhGia;
        this.nam = nam;
        this.diemTrungBinh = diemTrungBinh;
        this.xepLoai = xepLoai;
    }

    public int getMaDanhGia() { return maDanhGia; }
    public void setMaDanhGia(int maDanhGia) { this.maDanhGia = maDanhGia; }

    public int getMaNhanVien() { return maNhanVien; }
    public void setMaNhanVien(int maNhanVien) { this.maNhanVien = maNhanVien; }

    public int getNguoiDanhGia() { return nguoiDanhGia; }
    public void setNguoiDanhGia(int nguoiDanhGia) { this.nguoiDanhGia = nguoiDanhGia; }

    public String getKyDanhGia() { return kyDanhGia; }
    public void setKyDanhGia(String kyDanhGia) { this.kyDanhGia = kyDanhGia; }

    public int getNam() { return nam; }
    public void setNam(int nam) { this.nam = nam; }

    public double getDiemTrungBinh() { return diemTrungBinh; }
    public void setDiemTrungBinh(double diemTrungBinh) { this.diemTrungBinh = diemTrungBinh; }

    public XepLoai getXepLoai() { return xepLoai; }
    public void setXepLoai(XepLoai xepLoai) { this.xepLoai = xepLoai; }
}
