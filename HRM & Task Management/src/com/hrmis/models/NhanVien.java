package com.hrmis.models;

import com.hrmis.enums.GioiTinh;
import com.hrmis.enums.TrangThaiLamViec;
import java.time.LocalDate;
public class NhanVien {
    private Integer maNhanVien;
    private String Ho;
    private String Ten;
    private String Email;
    private Integer SDT;
    private LocalDate NgaySinh;
    private GioiTinh gioiTinh;
    private LocalDate ngayVaoLam;
    private TrangThaiLamViec trangThaiLamViec

    public NhanVien(Integer maNhanVien, String Ho,String Ten,String Email,Integer SDT,LocalDate NgaySinh,
                    GioiTinh gioiTinh,LocalDate ngayVaoLam,TrangThaiLamViec trangThaiLamViec){
        this.maNhanVien=maNhanVien;
        this.Ho=Ho;
        this.Ten=Ten;
        this.Email=Email;
        this.SDT=SDT;
        this.NgaySinh=NgaySinh;
        this.gioiTinh=gioiTinh;
        this.ngayVaoLam=ngayVaoLam;
        this.trangThaiLamViec=trangThaiLamViec;
    }
    public Integer getmaNhanVien(){return maNhanVien;}
    public void setmaNhanVien(Integer maNhanVien){this.maNhanVien=maNhanVien;}
    public String getHo(){return Ho;}
    public void setHo(String Ho){this.Ho=Ho;}
    public String getTen(){return Ten;}
    public void setTen(String Ten){this.Ten=Ten;}
    public String getEmail(){return Email;}
    public void setEmail(String Email){this.Email=Email;}
    public Integer getSDT(){return SDT;}
    public void setSDT(Integer SDT){this.SDT=SDT;}
    public LocalDate getNgaySinh(){return NgaySinh;}
    public void setNgaySinh(LocalDate NgaySinh){this.NgaySinh=NgaySinh;}
    public GioiTinh getgioiTinh(){return gioiTinh;}
    public void setGioiTinh(GioiTinh gioiTinh){this.gioiTinh=gioiTinh;}
    public LocalDate getngayVaoLam(){return ngayVaoLam;}
    public void setngayVaoLam(LocalDate ngayVaoLam){this.ngayVaoLam=ngayVaoLam;}
    public TrangThaiLamViec getTrangThaiLamViec(){return TrangThaiLamViec;}
    public void settrangThaiLamViec(TrangThaiLamViec trangThaiLamViec){this.trangThaiLamViec=trangThaiLamViec;}

    @Override 
    public String toString(){
        return("MaNV: "+maNhanVien+", Ho&TenNV: "+ Ho +" "+Ten +", Ngay Sinh: "+NgaySinh+
               ", Gioi Tinh:"+gioiTinh+", Email: "+ Email +", SDT: "+SDT+", ngay vao lam: "+ngayVaoLam+", trang thai lam viec: "+trangThaiLamViec);
    }
}
