package com.hrmis.models;

import java.time.LocalDate;
public class ThanhVienNhom {
    private Integer maNhom;
    private NhanVien maNhanVien;
    private LocalDate ngayThamGia;
    private String VaiTro;

    public ThanhVienNhom(Integer maNhom,NhanVien maNhanVien,LocalDate ngayThamGia, String VaiTro){
        this.maNhom=maNhom;
        this.maNhanVien=maNhanVien;
        this.ngayThamGia=ngayThamGia;
        this.VaiTro=VaiTro;
    }
    public Integer getmaNhom(){return maNhom;}
    public void setmaNhom(Integer maNhom){this.maNhom=maNhom;}
    public NhanVien getnhomNhanVien(){return maNhanVien;}
    public void setnhomNhanVien(NhanVien nhomNhanVien){this.maNhanVien=nhomNhanVien;}
    public LocalDate getngayThamGia(){return ngayThamGia;}
    public void setngayThamGia(LocalDate ngayThamGia){this.ngayThamGia=ngayThamGia;}
    public String getVaiTro(){return VaiTro;}
    public void setVaiTro(String VaiTro){this.VaiTro=VaiTro;}

    @Override 
    public String toString(){
        return("Thanh vien nhom{ ma nhom "+maNhom + " vai tro: "+VaiTro);
    }
}
