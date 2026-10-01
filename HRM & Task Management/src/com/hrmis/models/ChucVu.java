package com.hrmis.models;

public class ChucVu {
    private Integer maChucVu;
    private String tenChucVu;
    public ChucVu(Integer maChucVu,String tenChucVu){
        this.maChucVu=maChucVu;
        this.tenChucVu=tenChucVu;
    }
    public Integer getmaChucVu(){return maChucVu;}
    public void setmaChucVu(Integer maChucVu){this.maChucVu=maChucVu;}
    public String gettenChucVu(){return tenChucVu;}
    public void settenChucVu(String tenChucVu){this.tenChucVu=tenChucVu;}

    @Override 
    public String toString(){
        return "Chuc Vu{maChucVu='"+maChucVu+"'ten Chuc Vu:'"+tenChucVu;
    }
}
