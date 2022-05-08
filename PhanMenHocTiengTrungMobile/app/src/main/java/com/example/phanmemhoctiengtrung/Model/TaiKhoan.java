package com.example.phanmemhoctiengtrung.Model;

import java.util.List;

public class TaiKhoan {
    private String id;
    private String email, matkhau, hoten, ngaysinh, sdt, hinhanh;
    private int level;
    private boolean isAdmin;
    List<BaiHoc> baiDaHoc;
    List<BaiHoc> baiKiemTra;

    public TaiKhoan(String id, String email, String matkhau, String hoten, String ngaysinh, String sdt, String hinhanh) {
        this.id = id;
        this.email = email;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.hinhanh = hinhanh;
        this.level = 0;

    }

    public TaiKhoan() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<BaiHoc> getBaiDaHoc() {
        return baiDaHoc;
    }

    public void setBaiDaHoc(List<BaiHoc> baiDaHoc) {
        this.baiDaHoc = baiDaHoc;
    }

    public List<BaiHoc> getBaiKiemTra() {
        return baiKiemTra;
    }

    public void setBaiKiemTra(List<BaiHoc> baiKiemTra) {
        this.baiKiemTra = baiKiemTra;
    }

}
