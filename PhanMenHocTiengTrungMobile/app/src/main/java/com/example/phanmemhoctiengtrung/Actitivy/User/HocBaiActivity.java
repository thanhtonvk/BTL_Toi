package com.example.phanmemhoctiengtrung.Actitivy.User;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.phanmemhoctiengtrung.Actitivy.Common;
import com.example.phanmemhoctiengtrung.FirebaseContext.ChiTietBaiHocDBContext;
import com.example.phanmemhoctiengtrung.FirebaseContext.TaiKhoanDBContext;
import com.example.phanmemhoctiengtrung.Model.BaiHoc;
import com.example.phanmemhoctiengtrung.Model.ChiTietBaiHoc;
import com.example.phanmemhoctiengtrung.R;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HocBaiActivity extends AppCompatActivity {

    List<ChiTietBaiHoc> chiTietBaiHocList;
    int index = 0;
    Button btn_back, btn_sound, btn_next;
    ImageView img_anh;
    VideoView videoView;
    TextView tv_nghiatv, tv_vitricau;
    ChiTietBaiHocDBContext ctbhDBcontext;
    MediaPlayer mediaPlayer;
    TaiKhoanDBContext taiKhoanDBContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc_bai);
        chiTietBaiHocList = Common.chiTietBaiHocList;
        anhXa();
        loadBaiHoc();
        onClick();
    }

    private void anhXa() {
        btn_back = findViewById(R.id.btn_back);
        btn_sound = findViewById(R.id.btn_sound);
        btn_next = findViewById(R.id.btn_next);
        img_anh = findViewById(R.id.img_hinhanh);
        videoView = findViewById(R.id.video_view);
        tv_nghiatv = findViewById(R.id.tv_nghiatv);
        tv_vitricau = findViewById(R.id.tv_vitricau);
        ctbhDBcontext = new ChiTietBaiHocDBContext(HocBaiActivity.this);
        mediaPlayer = new MediaPlayer();
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        taiKhoanDBContext = new TaiKhoanDBContext();
    }

    private void onClick() {
        btn_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                if (index == chiTietBaiHocList.size()) {

                    if (Common.taiKhoan.getBaiDaHoc() != null) {
                        checkTonTai();
                        Common.taiKhoan.setBaiDaHoc(Common.taiKhoan.getBaiDaHoc());
                    } else {
                        List<BaiHoc> baiHocList = new ArrayList<>();
                        baiHocList.add(Common.baiHoc);
                        Common.taiKhoan.setBaiDaHoc(baiHocList);
                    }
                    Common.taiKhoan.setLevel(Common.taiKhoan.getLevel() + 1);
                    taiKhoanDBContext.suaTaiKhoan(Common.taiKhoan, HocBaiActivity.this);
                    Toast.makeText(getApplicationContext(), "Hoàn thành", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    loadBaiHoc();
                }

            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void checkTonTai() {

        for (int i = 0; i < Common.taiKhoan.getBaiDaHoc().size(); i++) {
            BaiHoc baiHoc = Common.taiKhoan.getBaiDaHoc().get(i);
            if (baiHoc.getId().equals(Common.baiHoc.getId())) {
                Common.taiKhoan.getBaiDaHoc().remove(i);
                Common.taiKhoan.getBaiDaHoc().add(Common.baiHoc);
            }
        }

    }

    private void loadBaiHoc() {
        ChiTietBaiHoc chiTietBaiHoc = chiTietBaiHocList.get(index);
        tv_vitricau.setText((index + 1) + "/5");
        tv_nghiatv.setText(chiTietBaiHoc.getNghiatiengviet());
        if (chiTietBaiHoc.getTuonghinh() != null) {
            //set am thanh va hinh anh
            videoView.setVisibility(View.INVISIBLE);
            btn_sound.setVisibility(View.VISIBLE);
            img_anh.setVisibility(View.VISIBLE);

            ctbhDBcontext.storageReference.child(chiTietBaiHoc.getId()).child("Image").child(chiTietBaiHoc.getTuonghinh()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(HocBaiActivity.this).load(uri).into(img_anh);
                }
            });

            if (chiTietBaiHoc.getAmthanh() != null) {
                ctbhDBcontext.storageReference.child(chiTietBaiHoc.getId()).child("Recorder").child(chiTietBaiHoc.getAmthanh()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        prepareAudio(uri);
                    }
                });
            }
        } else {
            //set video
            img_anh.setVisibility(View.INVISIBLE);
            btn_sound.setVisibility(View.INVISIBLE);
            videoView.setVisibility(View.VISIBLE);
            if (chiTietBaiHoc.getVideo() != null) {
                ctbhDBcontext.storageReference.child(chiTietBaiHoc.getId()).child("Video").child(chiTietBaiHoc.getVideo()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        videoView.setVideoURI(uri);
                        videoView.start();
                    }
                });
            }
        }
    }

    private void prepareAudio(Uri uri) {
        try {
            mediaPlayer.setDataSource(HocBaiActivity.this, uri);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}