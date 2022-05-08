package com.example.phanmemhoctiengtrung.Actitivy.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.phanmemhoctiengtrung.Actitivy.Admin.Adapter.CTBaiHocAdapter;
import com.example.phanmemhoctiengtrung.Actitivy.Common;
import com.example.phanmemhoctiengtrung.FirebaseContext.BaiHocDBContext;
import com.example.phanmemhoctiengtrung.Model.ChiTietBaiHoc;
import com.example.phanmemhoctiengtrung.R;

import java.util.ArrayList;

public class QuanLyChiTietBaiHocActivity extends AppCompatActivity {

    ListView lv_ctbaihoc;
    Button btn_them;
    CTBaiHocAdapter adapter;
    BaiHocDBContext dbContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_chi_tiet_bai_hoc);
        anhXa();
        dbContext= new BaiHocDBContext();
        if (Common.baiHoc.getChiTietBaiHocList()== null) {
            Common.chiTietBaiHocList = new ArrayList<>();
        } else {
            Common.chiTietBaiHocList = Common.baiHoc.getChiTietBaiHocList();
            adapter = new CTBaiHocAdapter(getApplicationContext(),Common.baiHoc.getChiTietBaiHocList());
            lv_ctbaihoc.setAdapter(adapter);
        }


        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),UpdateCTBaiHocActivity.class));
            }
        });
        lv_ctbaihoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialog(i);
                return false;
            }
        });
        lv_ctbaihoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Common.chiTietBaiHoc = Common.chiTietBaiHocList.get(i);
                startActivity(new Intent(getApplicationContext(),UpdateCTBaiHocActivity.class));
            }
        });
    }
    public void dialog(int postion){
        AlertDialog.Builder builder = new AlertDialog.Builder(QuanLyChiTietBaiHocActivity.this);
        builder.setTitle("Bạn có muốn xóa không? ");
        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Common.chiTietBaiHocList.remove(postion);
                Common.baiHoc.setChiTietBaiHocList(Common.chiTietBaiHocList);
                dbContext.capNhatBaiHoc(Common.baiHoc,QuanLyChiTietBaiHocActivity.this);
                dialogInterface.dismiss();
                adapter = new CTBaiHocAdapter(QuanLyChiTietBaiHocActivity.this,Common.baiHoc.getChiTietBaiHocList());
                lv_ctbaihoc.setAdapter(adapter);
            }
        });
        builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    private void anhXa(){
        lv_ctbaihoc = findViewById(R.id.lv_ctbaihoc);
        btn_them= findViewById(R.id.btn_them);
    }
}