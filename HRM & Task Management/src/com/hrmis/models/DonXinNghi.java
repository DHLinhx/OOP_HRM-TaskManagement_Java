package com.hrmis.models;

import com.hrmis.enums.LoaiNghi;
import com.hrmis.enums.TrangThaiDuyet;
import java.time.LocalDate;

public class DonXinNghi {
    private int maDonNghi;
    private int maNhanVien;
    private LoaiNghi loaiNghi;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private String lyDo;
    private TrangThaiDuyet trangThaiDuyet;
    private Integer nguoiDuyet;

    public DonXinNghi() {
    }

    public DonXinNghi(int maDonNghi, int maNhanVien, LoaiNghi loaiNghi, LocalDate ngayBatDau,
            LocalDate ngayKetThuc, String lyDo, TrangThaiDuyet trangThaiDuyet, Integer nguoiDuyet) {
        this.maDonNghi = maDonNghi;
        this.maNhanVien = maNhanVien;
        this.loaiNghi = loaiNghi;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.lyDo = lyDo;
        this.trangThaiDuyet = trangThaiDuyet;
        this.nguoiDuyet = nguoiDuyet;
    }

    public int getMaDonNghi() {
        return maDonNghi;
    }

    public void setMaDonNghi(int maDonNghi) {
        this.maDonNghi = maDonNghi;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public LoaiNghi getLoaiNghi() {
        return loaiNghi;
    }

    public void setLoaiNghi(LoaiNghi loaiNghi) {
        this.loaiNghi = loaiNghi;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public TrangThaiDuyet getTrangThaiDuyet() {
        return trangThaiDuyet;
    }

    public void setTrangThaiDuyet(TrangThaiDuyet trangThaiDuyet) {
        this.trangThaiDuyet = trangThaiDuyet;
    }

    public Integer getNguoiDuyet() {
        return nguoiDuyet;
    }

    public void setNguoiDuyet(Integer nguoiDuyet) {
        this.nguoiDuyet = nguoiDuyet;
    }

    @Override
    public String toString() {
        return "DonXinNghi{" +
                "maDonNghi=" + maDonNghi +
                ", maNhanVien=" + maNhanVien +
                ", loaiNghi=" + loaiNghi +
                ", ngayBatDau=" + ngayBatDau +
                ", ngayKetThuc=" + ngayKetThuc +
                ", lyDo='" + lyDo + '\'' +
                ", trangThaiDuyet=" + trangThaiDuyet +
                ", nguoiDuyet=" + nguoiDuyet +
                '}';
    }
}
