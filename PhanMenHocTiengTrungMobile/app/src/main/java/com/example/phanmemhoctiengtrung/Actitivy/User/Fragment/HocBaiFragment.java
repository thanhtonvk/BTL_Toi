package com.example.phanmemhoctiengtrung.Actitivy.User.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.phanmemhoctiengtrung.Actitivy.Common;
import com.example.phanmemhoctiengtrung.Actitivy.User.HocBaiActivity;
import com.example.phanmemhoctiengtrung.FirebaseContext.BaiHocDBContext;
import com.example.phanmemhoctiengtrung.Model.BaiHoc;
import com.example.phanmemhoctiengtrung.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HocBaiFragment extends Fragment {

    ImageView btn_bai0, btn_bai1, btn_bai2, btn_bai3, btn_bai4, btn_bai5, btn_bai6, btn_bai7, btn_bai8, btn_bai9;
    BaiHocDBContext dbContext;
    List<BaiHoc> baiHocList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hoc_bai, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXa(view);
        loadDSBaiHoc();
        onClick();

    }

    private void anhXa(View view) {
        btn_bai0 = view.findViewById(R.id.btn_bai0);
        btn_bai1 = view.findViewById(R.id.btn_bai1);
        btn_bai2 = view.findViewById(R.id.btn_bai2);
        btn_bai3 = view.findViewById(R.id.btn_bai3);
        btn_bai4 = view.findViewById(R.id.btn_bai4);
        btn_bai5 = view.findViewById(R.id.btn_bai5);
        btn_bai6 = view.findViewById(R.id.btn_bai6);
        btn_bai7 = view.findViewById(R.id.btn_bai7);
        btn_bai8 = view.findViewById(R.id.btn_bai8);
        btn_bai9 = view.findViewById(R.id.btn_bai9);
        dbContext = new BaiHocDBContext();
    }

    private void onClick() {
        btn_bai0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.baiHoc = baiHocList.get(0);
                Common.chiTietBaiHocList = baiHocList.get(0).getChiTietBaiHocList();
                startActivity(new Intent(getContext(), HocBaiActivity.class));
            }
        });
        btn_bai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.baiHoc = baiHocList.get(1);
                Common.chiTietBaiHocList = baiHocList.get(1).getChiTietBaiHocList();
            }
        });
        btn_bai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.baiHoc = baiHocList.get(2);
                Common.chiTietBaiHocList = baiHocList.get(2).getChiTietBaiHocList();
            }
        });
        btn_bai3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.baiHoc = baiHocList.get(3);
                Common.chiTietBaiHocList = baiHocList.get(3).getChiTietBaiHocList();
            }
        });
        btn_bai4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.baiHoc = baiHocList.get(4);
                Common.chiTietBaiHocList = baiHocList.get(4).getChiTietBaiHocList();
            }
        });
        btn_bai5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.baiHoc = baiHocList.get(5);
                Common.chiTietBaiHocList = baiHocList.get(5).getChiTietBaiHocList();
            }
        });
        btn_bai6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.baiHoc = baiHocList.get(6);
                Common.chiTietBaiHocList = baiHocList.get(6).getChiTietBaiHocList();
            }
        });
        btn_bai7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.baiHoc = baiHocList.get(7);
                Common.chiTietBaiHocList = baiHocList.get(7).getChiTietBaiHocList();
            }
        });
        btn_bai8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.baiHoc = baiHocList.get(8);
                Common.chiTietBaiHocList = baiHocList.get(8).getChiTietBaiHocList();
            }
        });
        btn_bai9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.baiHoc = baiHocList.get(9);
                Common.chiTietBaiHocList = baiHocList.get(9).getChiTietBaiHocList();
            }
        });
    }

    private void loadDSBaiHoc() {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Đang tải dữ liệu");
        progressDialog.show();
        dbContext.reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                baiHocList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    BaiHoc baiHoc = dataSnapshot.getValue(BaiHoc.class);
                    baiHocList.add(baiHoc);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}