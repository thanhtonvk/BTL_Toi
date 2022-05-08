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

public class KiemTraActivity extends AppCompatActivity {
    List<ChiTietBaiHoc> chiTietBaiHocList;
    int index = 0;
    Button btn_back, btn_sound;
    ImageView img_anh;
    VideoView videoView;
    TextView tv_vitricau;
    ChiTietBaiHocDBContext ctbhDBcontext;
    MediaPlayer mediaPlayer;
    Button btn_da1, btn_da2, btn_da3, btn_da4;
    int diem = 0;
    TaiKhoanDBContext dbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiem_tra);
        chiTietBaiHocList = Common.chiTietBaiHocList;
        anhXa();
        loadBaiHoc();
        onClick();
    }

    private void anhXa() {
        btn_back = findViewById(R.id.btn_back);
        btn_sound = findViewById(R.id.btn_sound);
        img_anh = findViewById(R.id.img_hinhanh);
        videoView = findViewById(R.id.video_view);
        tv_vitricau = findViewById(R.id.tv_vitricau);
        ctbhDBcontext = new ChiTietBaiHocDBContext(KiemTraActivity.this);
        mediaPlayer = new MediaPlayer();
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        btn_da1 = findViewById(R.id.btn_da1);
        btn_da2 = findViewById(R.id.btn_da2);
        btn_da3 = findViewById(R.id.btn_da3);
        btn_da4 = findViewById(R.id.btn_da4);
        dbContext = new TaiKhoanDBContext();
    }

    private void onClick() {
        btn_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_da1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkDA(btn_da1.getText().toString())) {
                    diem++;
                    Toast.makeText(getApplicationContext(), "Chính xác", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Sai rồi!!", Toast.LENGTH_SHORT).show();
                }
                index++;
                if (index == chiTietBaiHocList.size()) {
                    if (Common.taiKhoan.getBaiKiemTra() != null) {
                        Common.baiHoc.setDiem(diem);
                        Common.taiKhoan.getBaiKiemTra().add(Common.baiHoc);
                    } else {
                        List<BaiHoc> baiHocList = new ArrayList<>();
                        Common.baiHoc.setDiem(diem);
                        baiHocList.add(Common.baiHoc);
                        Common.taiKhoan.setBaiKiemTra(baiHocList);
                    }
                    dbContext.suaTaiKhoan(Common.taiKhoan, KiemTraActivity.this);
                    finish();
                    Toast.makeText(getApplicationContext(), "Hoàn thành", Toast.LENGTH_SHORT).show();
                } else {
                    loadBaiHoc();
                }
            }
        });
        btn_da2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkDA(btn_da2.getText().toString())) {
                    diem++;
                    Toast.makeText(getApplicationContext(), "Chính xác", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Sai rồi!!", Toast.LENGTH_SHORT).show();
                }
                index++;
                if (index == chiTietBaiHocList.size()) {
                    if (Common.taiKhoan.getBaiKiemTra() != null) {
                        Common.baiHoc.setDiem(diem);
                        Common.taiKhoan.getBaiKiemTra().add(Common.baiHoc);
                    } else {
                        List<BaiHoc> baiHocList = new ArrayList<>();
                        Common.baiHoc.setDiem(diem);
                        baiHocList.add(Common.baiHoc);
                        Common.taiKhoan.setBaiKiemTra(baiHocList);
                    }
                    dbContext.suaTaiKhoan(Common.taiKhoan, KiemTraActivity.this);
                    finish();
                    Toast.makeText(getApplicationContext(), "Hoàn thành", Toast.LENGTH_SHORT).show();
                } else {
                    loadBaiHoc();
                }
            }
        });
        btn_da3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkDA(btn_da3.getText().toString())) {
                    diem++;
                    Toast.makeText(getApplicationContext(), "Chính xác", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Sai rồi!!", Toast.LENGTH_SHORT).show();
                }
                index++;
                if (index == chiTietBaiHocList.size()) {
                    if (Common.taiKhoan.getBaiKiemTra() != null) {
                        Common.baiHoc.setDiem(diem);
                        Common.taiKhoan.getBaiKiemTra().add(Common.baiHoc);
                    } else {
                        List<BaiHoc> baiHocList = new ArrayList<>();
                        Common.baiHoc.setDiem(diem);
                        baiHocList.add(Common.baiHoc);
                        Common.taiKhoan.setBaiKiemTra(baiHocList);
                    }
                    dbContext.suaTaiKhoan(Common.taiKhoan, KiemTraActivity.this);
                    finish();
                    Toast.makeText(getApplicationContext(), "Hoàn thành", Toast.LENGTH_SHORT).show();
                } else {
                    loadBaiHoc();
                }
            }
        });
        btn_da4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkDA(btn_da4.getText().toString())) {
                    diem++;
                    Toast.makeText(getApplicationContext(), "Chính xác", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Sai rồi!!", Toast.LENGTH_SHORT).show();
                }
                index++;
                if (index == chiTietBaiHocList.size()) {
                    if (Common.taiKhoan.getBaiKiemTra() != null) {
                        Common.baiHoc.setDiem(diem);
                        checkTonTai();
                    } else {
                        List<BaiHoc> baiHocList = new ArrayList<>();
                        Common.baiHoc.setDiem(diem);
                        baiHocList.add(Common.baiHoc);
                        Common.taiKhoan.setBaiKiemTra(baiHocList);
                    }
                    dbContext.suaTaiKhoan(Common.taiKhoan, KiemTraActivity.this);
                    finish();
                    Toast.makeText(getApplicationContext(), "Hoàn thành", Toast.LENGTH_SHORT).show();
                } else {
                    loadBaiHoc();
                }
            }
        });
    }

    public void checkTonTai() {

        for (int i = 0; i < Common.taiKhoan.getBaiKiemTra().size(); i++) {
            BaiHoc baiHoc = Common.taiKhoan.getBaiDaHoc().get(i);
            if (baiHoc.getId().equals(Common.baiHoc.getId())) {
                Common.taiKhoan.getBaiKiemTra().remove(i);
                Common.taiKhoan.getBaiKiemTra().add(Common.baiHoc);
            }
        }

    }
    private boolean checkDA(String da) {
        ChiTietBaiHoc chiTietBaiHoc = chiTietBaiHocList.get(index);
        if (chiTietBaiHoc.getDapan().equalsIgnoreCase(da)) {
            return true;
        } else return false;
    }

    private void loadBaiHoc() {
        ChiTietBaiHoc chiTietBaiHoc = chiTietBaiHocList.get(index);
        tv_vitricau.setText("Điểm: " + diem);
        btn_da1.setText(chiTietBaiHoc.getA());
        btn_da2.setText(chiTietBaiHoc.getB());
        btn_da3.setText(chiTietBaiHoc.getC());
        btn_da4.setText(chiTietBaiHoc.getD());
        if (chiTietBaiHoc.getTuonghinh() != null) {
            //set am thanh va hinh anh
            videoView.setVisibility(View.INVISIBLE);
            btn_sound.setVisibility(View.VISIBLE);
            img_anh.setVisibility(View.VISIBLE);

            ctbhDBcontext.storageReference.child(chiTietBaiHoc.getId()).child("Image").child(chiTietBaiHoc.getTuonghinh()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(KiemTraActivity.this).load(uri).into(img_anh);
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
            mediaPlayer.setDataSource(KiemTraActivity.this, uri);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}