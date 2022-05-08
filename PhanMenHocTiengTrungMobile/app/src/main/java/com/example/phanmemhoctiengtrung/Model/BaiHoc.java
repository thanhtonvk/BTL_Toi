package com.example.phanmemhoctiengtrung.Model;

import java.util.List;

public class BaiHoc {
    private String id,chude,idYoutube;
    private List<ChiTietBaiHoc> chiTietBaiHocList;
    private int diem;

    public BaiHoc(String id, String chude) {
        this.id = id;
        this.chude = chude;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public BaiHoc(String id,  String chude, String idYoutube, List<ChiTietBaiHoc> chiTietBaiHocList) {
        this.id = id;

        this.chude = chude;
        this.idYoutube = idYoutube;
        this.chiTietBaiHocList = chiTietBaiHocList;
        diem = 0;
    }
    public BaiHoc(){

    }

    public String getIdYoutube() {
        return idYoutube;
    }

    public void setIdYoutube(String idYoutube) {
        this.idYoutube = idYoutube;
    }

    public List<ChiTietBaiHoc> getChiTietBaiHocList() {
        return chiTietBaiHocList;
    }

    public void setChiTietBaiHocList(List<ChiTietBaiHoc> chiTietBaiHocList) {
        this.chiTietBaiHocList = chiTietBaiHocList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getChude() {
        return chude;
    }

    public void setChude(String chude) {
        this.chude = chude;
    }
}
