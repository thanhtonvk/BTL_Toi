package com.example.phanmemhoctiengtrung.Model;

public class ChiTietBaiHoc {
    private String id, tuonghinh, nghiatiengviet;
    private String amthanh;
    private String video;
    private String a, b, c, d, dapan;



    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public ChiTietBaiHoc(String id, String tuonghinh, String nghiatiengviet, String amthanh, String video, String a, String b, String c, String d, String dapan) {
        this.id = id;
        this.tuonghinh = tuonghinh;

        this.nghiatiengviet = nghiatiengviet;
        this.amthanh = amthanh;
        this.video = video;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.dapan = dapan;
    }


    public ChiTietBaiHoc(String id, String a, String b, String c, String d, String dapan) {
        this.id = id;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.dapan = dapan;
    }

    public ChiTietBaiHoc() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTuonghinh() {
        return tuonghinh;
    }

    public void setTuonghinh(String tuonghinh) {
        this.tuonghinh = tuonghinh;
    }


    public String getNghiatiengviet() {
        return nghiatiengviet;
    }

    public void setNghiatiengviet(String nghiatiengviet) {
        this.nghiatiengviet = nghiatiengviet;
    }

    public String getAmthanh() {
        return amthanh;
    }

    public void setAmthanh(String amthanh) {
        this.amthanh = amthanh;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getDapan() {
        return dapan;
    }

    public void setDapan(String dapan) {
        this.dapan = dapan;
    }
}
