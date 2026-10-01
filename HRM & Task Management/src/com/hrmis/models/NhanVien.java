package com.hrmis.models;

import com.hrmis.enums.GioiTinh;
import com.hrmis.enums.TrangThaiLamViec;
import java.time.LocalDate;

public class NhanVien {
    private int maNhanVien;
    private String ho;
    private String ten;
    private String email;
    private String soDienThoai;
    private LocalDate ngaySinh;
    private GioiTinh gioiTinh;
    private LocalDate ngayVaoLam;
    private TrangThaiLamViec trangThaiLamViec;
    private int maPhongBan;
    private int maChucVu;
    private Integer maQuanLy; // Integer để có thể nhận null nếu là cấp cao nhất

    public NhanVien() {
    }

    public NhanVien(int maNhanVien, String ho, String ten, String email, String soDienThoai,
            LocalDate ngaySinh, GioiTinh gioiTinh, LocalDate ngayVaoLam,
            TrangThaiLamViec trangThaiLamViec, int maPhongBan, int maChucVu, Integer maQuanLy) {
        this.maNhanVien = maNhanVien;
        this.ho = ho;
        this.ten = ten;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.ngayVaoLam = ngayVaoLam;
        this.trangThaiLamViec = trangThaiLamViec;
        this.maPhongBan = maPhongBan;
        this.maChucVu = maChucVu;
        this.maQuanLy = maQuanLy;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public GioiTinh getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(GioiTinh gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public LocalDate getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(LocalDate ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public TrangThaiLamViec getTrangThaiLamViec() {
        return trangThaiLamViec;
    }

    public void setTrangThaiLamViec(TrangThaiLamViec trangThaiLamViec) {
        this.trangThaiLamViec = trangThaiLamViec;
    }

    public int getMaPhongBan() {
        return maPhongBan;
    }

    public void setMaPhongBan(int maPhongBan) {
        this.maPhongBan = maPhongBan;
    }

    public int getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(int maChucVu) {
        this.maChucVu = maChucVu;
    }

    public Integer getMaQuanLy() {
        return maQuanLy;
    }

    public void setMaQuanLy(Integer maQuanLy) {
        this.maQuanLy = maQuanLy;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNhanVien=" + maNhanVien +
                ", hoTen='" + ho + " " + ten + '\'' +
                ", email='" + email + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", gioiTinh=" + gioiTinh +
                ", trangThaiLamViec=" + trangThaiLamViec +
                '}';
    }
}
