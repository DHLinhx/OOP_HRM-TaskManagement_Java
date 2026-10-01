package com.hrmis.models;

import java.time.LocalDate;
public class NhomNhanVien {
    private Integer maNhom;
    private String tenNhom;
    private Integer maTruongNhom;
    private LocalDate ngayThanhLap;
    private String MoTa;

    public NhomNhanVien(Integer maNhom,String tenNhom, Integer maTruongNhom,LocalDate ngayThanhLap, String MoTa){
        this.maNhom=maNhom;
        this.tenNhom=tenNhom;
        this.maTruongNhom=maTruongNhom;
        this.ngayThanhLap=ngayThanhLap;
        this.MoTa=MoTa;
    }
    public Integer getmaNhom(){return maNhom;}
    public void setmaNhom(Integer maNhom){this.maNhom=maNhom;}
    public String gettenNhom(){return tenNhom;}
    public void settenNhom(String tenNhom){this.tenNhom=tenNhom;}
    public Integer getmaTruongNhom(){return maTruongNhom;}
    public void setmaTruongNhom(Integer maTruongNhom){this.maTruongNhom=maTruongNhom;}
    public LocalDate getngayThanhLap(){return ngayThanhLap;}
    public void setngayThanhLap(LocalDate ngayThanhLap){this.ngayThanhLap=ngayThanhLap;}

    @Override 
    public String toString(){
        return("ma Nhom: "+maNhom+", ten nhom: "+tenNhom+", ma truong phong: "+maTruongNhom+", ngay thanh lap: "+ngayThanhLap+", mo ta: "+MoTa);
    }
}
