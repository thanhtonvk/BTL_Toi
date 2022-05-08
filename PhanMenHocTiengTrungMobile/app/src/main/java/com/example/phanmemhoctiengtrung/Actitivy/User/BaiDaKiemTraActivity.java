package com.example.phanmemhoctiengtrung.Actitivy.User;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phanmemhoctiengtrung.Actitivy.Admin.Adapter.BaiHocAdapter;
import com.example.phanmemhoctiengtrung.Actitivy.Common;
import com.example.phanmemhoctiengtrung.R;

import java.util.ArrayList;
import java.util.List;

public class BaiDaKiemTraActivity extends AppCompatActivity {
    BaiHocAdapter baiHocAdapter;
    ListView lv_baidahoc;
    List<Integer> hinhAnhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_da_kiem_tra);
        setImages();
        lv_baidahoc = findViewById(R.id.lv_baidahoc);
        baiHocAdapter = new BaiHocAdapter(BaiDaKiemTraActivity.this, Common.taiKhoan.getBaiDaHoc(), hinhAnhs);
        lv_baidahoc.setAdapter(baiHocAdapter);
    }

    private void setImages() {
        hinhAnhs = new ArrayList<>();
        //set hinh anh cho bai hoc
        hinhAnhs.add(R.drawable.ic_bai1);
        hinhAnhs.add(R.drawable.ic_bai2);
        hinhAnhs.add(R.drawable.ic_bai3);
        hinhAnhs.add(R.drawable.ic_bai4);
        hinhAnhs.add(R.drawable.ic_bai5);
        hinhAnhs.add(R.drawable.ic_bai6);
        hinhAnhs.add(R.drawable.ic_bai7);
        hinhAnhs.add(R.drawable.ic_bai8);
        hinhAnhs.add(R.drawable.ic_bai9);
        hinhAnhs.add(R.drawable.ic_bai10);
    }
}