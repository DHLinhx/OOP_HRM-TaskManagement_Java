package com.hrmis.models;

public class DieuKhoanHopDong {

    private int maDieuKhoan;
    private int maHopDong;
    private int soThuTuDieu;
    private String tieuDeDieuKhoan;
    private String noiDungDieuKhoan;
    private String loaiDieuKhoan;

    public DieuKhoanHopDong() {
    }

    public DieuKhoanHopDong(int maDieuKhoan, int maHopDong, int soThuTuDieu,
            String tieuDeDieuKhoan, String noiDungDieuKhoan, String loaiDieuKhoan) {
        this.maDieuKhoan = maDieuKhoan;
        this.maHopDong = maHopDong;
        this.soThuTuDieu = soThuTuDieu;
        this.tieuDeDieuKhoan = tieuDeDieuKhoan;
        this.noiDungDieuKhoan = noiDungDieuKhoan;
        this.loaiDieuKhoan = loaiDieuKhoan;
    }

    public int getMaDieuKhoan() {
        return maDieuKhoan;
    }

    public void setMaDieuKhoan(int maDieuKhoan) {
        this.maDieuKhoan = maDieuKhoan;
    }

    public int getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(int maHopDong) {
        this.maHopDong = maHopDong;
    }

    public int getSoThuTuDieu() {
        return soThuTuDieu;
    }

    public void setSoThuTuDieu(int soThuTuDieu) {
        this.soThuTuDieu = soThuTuDieu;
    }

    public String getTieuDeDieuKhoan() {
        return tieuDeDieuKhoan;
    }

    public void setTieuDeDieuKhoan(String tieuDeDieuKhoan) {
        this.tieuDeDieuKhoan = tieuDeDieuKhoan;
    }

    public String getNoiDungDieuKhoan() {
        return noiDungDieuKhoan;
    }

    public void setNoiDungDieuKhoan(String noiDungDieuKhoan) {
        this.noiDungDieuKhoan = noiDungDieuKhoan;
    }

    public String getLoaiDieuKhoan() {
        return loaiDieuKhoan;
    }

    public void setLoaiDieuKhoan(String loaiDieuKhoan) {
        this.loaiDieuKhoan = loaiDieuKhoan;
    }

    @Override
    public String toString() {
        return "DieuKhoanHopDong{" +
                "maDieuKhoan=" + maDieuKhoan +
                ", maHopDong=" + maHopDong +
                ", soThuTuDieu=" + soThuTuDieu +
                ", tieuDeDieuKhoan='" + tieuDeDieuKhoan + '\'' +
                ", noiDungDieuKhoan='" + noiDungDieuKhoan + '\'' +
                ", loaiDieuKhoan='" + loaiDieuKhoan + '\'' +
                '}';
    }
}
