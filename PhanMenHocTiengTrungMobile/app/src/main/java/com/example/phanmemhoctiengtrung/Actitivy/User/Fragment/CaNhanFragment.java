package com.example.phanmemhoctiengtrung.Actitivy.User.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.phanmemhoctiengtrung.Actitivy.BeginActivity;
import com.example.phanmemhoctiengtrung.Actitivy.Common;
import com.example.phanmemhoctiengtrung.Actitivy.User.BaiDaHocActivity;
import com.example.phanmemhoctiengtrung.Actitivy.User.BaiDaKiemTraActivity;
import com.example.phanmemhoctiengtrung.Actitivy.User.ThongTinCaNhanActivity;
import com.example.phanmemhoctiengtrung.FirebaseContext.TaiKhoanDBContext;
import com.example.phanmemhoctiengtrung.R;
import com.google.android.gms.tasks.OnSuccessListener;

public class CaNhanFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ca_nhan, container, false);
    }

    TextView tv_hoten;
    TextView tv_level;
    ImageView img_avatar;
    TaiKhoanDBContext dbContext;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbContext = new TaiKhoanDBContext();
        tv_hoten = view.findViewById(R.id.tv_hoten);
        tv_level = view.findViewById(R.id.tv_level);
        img_avatar = view.findViewById(R.id.img_avatar);
        tv_hoten.setText(Common.taiKhoan.getHoten());
        tv_level.setText("Level " + Common.taiKhoan.getLevel());
        if (Common.taiKhoan.getHinhanh() != null) {
            if (!Common.taiKhoan.getHinhanh().equals("")) {
                dbContext.storageReference.child(Common.taiKhoan.getId()).child(Common.taiKhoan.getHinhanh()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(getContext()).load(uri).into(img_avatar);
                    }
                });
            }
        }

        view.findViewById(R.id.layout_infomation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ThongTinCaNhanActivity.class));
            }
        });
        view.findViewById(R.id.btn_baidahoc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), BaiDaHocActivity.class));
            }
        });
        view.findViewById(R.id.btn_baikiemtra).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), BaiDaKiemTraActivity.class));
            }
        });
        view.findViewById(R.id.btn_dangxuat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbContext.auth.signOut();
                getActivity().finish();
                startActivity(new Intent(getContext(), BeginActivity.class));
            }
        });
    }
}