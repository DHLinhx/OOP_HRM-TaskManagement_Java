package com.hrmis.models;

public class PhongBan {
    private Integer maPhongBan;
    private String tenPhongBan;
    private Integer maTruongPhong;
    private String ViTri;

    public PhongBan(Integer maPhongBan,String tenPhongBan,Integer maTruongPhong,String ViTri){
        this.maPhongBan=maPhongBan;
        this.tenPhongBan=tenPhongBan;
        this.maTruongPhong=maTruongPhong;
        this.ViTri=ViTri;
    }
    public Integer getmaPhongBan(){return maPhongBan;}
    public void setmaPhongBan(Integer maPhongBan){this.maPhongBan=maPhongBan;}
    public String gettenPhongBan(){return tenPhongBan;}
    public void settenPhongBan(String tenPhongBan){this.tenPhongBan=tenPhongBan;}
    public Integer getmaTruongPhong(){return maTruongPhong;}
    public void setmaTruongPhong(Integer maTruongPhong){this.maTruongPhong=maTruongPhong;}
    public String getViTri(){return ViTri;}
    public void setViTri(String ViTri){this.ViTri=ViTri;}

    @Override 
    public String toString(){
        return("ma Phong Ban: "+maPhongBan+" ,ten Phong ban: "+tenPhongBan+" ,ma Truong Phong: "+maTruongPhong+", Vi Tri: "+ViTri);
    }
}
