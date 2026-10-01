package com.hrmis.models;

import java.time.LocalDate;
public class ThanhVienNhom {
    private Integer maNhom;
    private NhomNhanVien nhomNhanVien;
    private NhanVien maNhanVien;
    private LocalDate ngayThamGia;
    private String VaiTro;

    public ThanhVienNhom(Integer maNhom,NhomNhanVien nhomNhanVien,NhanVien maNhanVien,LocalDate ngayThamGia, String VaiTro){
        this.maNhom=maNhom;
        this.nhomNhanVien=nhomNhanVien;
        this.maNhanVien=maNhanVien;
        this.VaiTro=VaiTro;
    }
    public Integer getmaNhom(){return maNhom;}
    public void setmaNhom(Integer maNhom){this.maNhom=maNhom;}
    public NhomNhanVien getnhomNhanVien(){return nhomNhanVien;}
    public void setnhomNhanVien(NhomNhanVien nhomNhanVien){this.nhomNhanVien=nhomNhanVien;}
    public NhanVien getmaNhanVien(){return maNhanVien;}
    public void setmaNhanVien(NhanVien maNhanVien){this.maNhanVien=maNhanVien;}
    public LocalDate getngayThamGia(){return ngayThamGia;}
    public void setngayThamGia(LocalDate ngayThamGia){this.ngayThamGia=ngayThamGia;}
    public String getVaiTro(){return VaiTro;}
    public void setVaiTro(String VaiTro){this.VaiTro=VaiTro;}

    @Override 
    public String toString(){
        return("Thanh vien nhom{ ma nhom "+maNhom + " vai tro: "+VaiTro);
    }
}
