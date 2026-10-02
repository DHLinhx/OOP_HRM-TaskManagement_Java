package com.hrmis.models;

public class ChiTietDanhGia {
    private int maChiTietDanhGia;
    private int maDanhGia;
    private String tieuChiDanhGia;
    private double trongSo;
    private double diemSo;
    private String nhanXetChiTiet;

    public ChiTietDanhGia(int maChiTietDanhGia, int maDanhGia, String tieuChiDanhGia, double trongSo, double diemSo, String nhanXetChiTiet) {
        this.maChiTietDanhGia = maChiTietDanhGia;
        this.maDanhGia = maDanhGia;
        this.tieuChiDanhGia = tieuChiDanhGia;
        this.trongSo = trongSo;
        this.diemSo = diemSo;
        this.nhanXetChiTiet = nhanXetChiTiet;
    }

    public int getMaChiTietDanhGia() { return maChiTietDanhGia; }
    public void setMaChiTietDanhGia(int maChiTietDanhGia) { this.maChiTietDanhGia = maChiTietDanhGia; }

    public int getMaDanhGia() { return maDanhGia; }
    public void setMaDanhGia(int maDanhGia) { this.maDanhGia = maDanhGia; }

    public String getTieuChiDanhGia() { return tieuChiDanhGia; }
    public void setTieuChiDanhGia(String tieuChiDanhGia) { this.tieuChiDanhGia = tieuChiDanhGia; }

    public double getTrongSo() { return trongSo; }
    public void setTrongSo(double trongSo) { this.trongSo = trongSo; }

    public double getDiemSo() { return diemSo; }
    public void setDiemSo(double diemSo) { this.diemSo = diemSo; }

    public String getNhanXetChiTiet() { return nhanXetChiTiet; }
    public void setNhanXetChiTiet(String nhanXetChiTiet) { this.nhanXetChiTiet = nhanXetChiTiet; }
}
