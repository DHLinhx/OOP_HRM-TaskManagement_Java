package com.hrmis.models;

public class DieuKhoanHopDong {
    // 1. Khai báo các thuộc tính (khớp chuẩn ERD)
    private int maDieuKhoan;
    private int maHopDong;
    private int soThuTuDieu;
    private String tieuDeDieuKhoan;
    private String noiDungDieuKhoan;
    private String loaiDieuKhoan;

    // 2. Constructor mặc định (không tham số)
    public DieuKhoanHopDong() {
    }

    // 3. Constructor đầy đủ tham số (khớp với DatabaseMock)
    public DieuKhoanHopDong(int maDieuKhoan, int maHopDong, int soThuTuDieu,
            String tieuDeDieuKhoan, String noiDungDieuKhoan, String loaiDieuKhoan) {
        this.maDieuKhoan = maDieuKhoan;
        this.maHopDong = maHopDong;
        this.soThuTuDieu = soThuTuDieu;
        this.tieuDeDieuKhoan = tieuDeDieuKhoan;
        this.noiDungDieuKhoan = noiDungDieuKhoan;
        this.loaiDieuKhoan = loaiDieuKhoan;
    }

    // 4. Các phương thức Getter và Setter
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

    // 5. Ghi đè phương thức toString
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
