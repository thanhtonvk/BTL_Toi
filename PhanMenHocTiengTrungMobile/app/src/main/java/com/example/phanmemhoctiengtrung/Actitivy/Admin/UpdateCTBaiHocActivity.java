package com.example.phanmemhoctiengtrung.Actitivy.Admin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.phanmemhoctiengtrung.Actitivy.Common;
import com.example.phanmemhoctiengtrung.FirebaseContext.BaiHocDBContext;
import com.example.phanmemhoctiengtrung.FirebaseContext.ChiTietBaiHocDBContext;
import com.example.phanmemhoctiengtrung.Model.ChiTietBaiHoc;
import com.example.phanmemhoctiengtrung.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;

public class UpdateCTBaiHocActivity extends AppCompatActivity {

    ImageView img_tuonghinh;
    Button btn_hinhanh, btn_video, btn_amthanh, btn_capnhat;
    EditText edt_da1, edt_da2, edt_da3, edt_da4, edt_nghiaTV;
    Spinner sp_dapan;
    String[] listDA = {"A", "B", "C", "D"};
    ChiTietBaiHocDBContext ctbhDBcontext;
    public static final int REQUEST_IMAGE = 4564, REQUEST_RECORDER = 56564564, REQUEST_VIDEO = 456456;
    ChiTietBaiHoc chiTietBaiHoc = new ChiTietBaiHoc();
    Random random;
    BaiHocDBContext dbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ctbai_hoc);
        anhXa();
        if(Common.chiTietBaiHoc!=null){
            loadUpdate();
        }
        random = new Random();
        chiTietBaiHoc.setId(random.nextInt() + "");
        ctbhDBcontext = new ChiTietBaiHocDBContext(UpdateCTBaiHocActivity.this);
        dbContext = new BaiHocDBContext();
        ArrayAdapter adapter = new ArrayAdapter(UpdateCTBaiHocActivity.this, android.R.layout.simple_spinner_dropdown_item, listDA);
        sp_dapan.setAdapter(adapter);
        onClick();
    }

    private void loadUpdate() {

        if (Common.chiTietBaiHoc.getVideo() == null) {
            btn_hinhanh.setText(Common.chiTietBaiHoc.getTuonghinh());
            btn_amthanh.setText(Common.chiTietBaiHoc.getAmthanh());

        } else {
            btn_video.setText(Common.chiTietBaiHoc.getVideo());
        }
        edt_da1.setText(Common.chiTietBaiHoc.getA());
        edt_da2.setText(Common.chiTietBaiHoc.getB());
        edt_da3.setText(Common.chiTietBaiHoc.getC());
        edt_da4.setText(Common.chiTietBaiHoc.getD());
        edt_nghiaTV.setText(Common.chiTietBaiHoc.getNghiatiengviet());

    }

    private void anhXa() {
        img_tuonghinh = findViewById(R.id.img_tuonghinh);
        btn_hinhanh = findViewById(R.id.btn_themtuonghinh);
        btn_video = findViewById(R.id.btn_themvideo);
        btn_amthanh = findViewById(R.id.btn_themamthanh);
        btn_capnhat = findViewById(R.id.btn_capnhat);
        edt_da1 = findViewById(R.id.edt_da1);
        edt_da2 = findViewById(R.id.edt_da2);
        edt_da3 = findViewById(R.id.edt_da3);
        edt_da4 = findViewById(R.id.edt_da4);
        sp_dapan = findViewById(R.id.sp_dapan);
        edt_nghiaTV = findViewById(R.id.edt_nghiaTV);

    }

    private void onClick() {
        btn_hinhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(UpdateCTBaiHocActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_IMAGE);
            }
        });
        btn_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(UpdateCTBaiHocActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_VIDEO);
            }
        });
        btn_amthanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(UpdateCTBaiHocActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_RECORDER);
            }
        });
        sp_dapan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    chiTietBaiHoc.setDapan(edt_da1.getText().toString());
                }
                if(i==1){
                    chiTietBaiHoc.setDapan(edt_da2.getText().toString());
                }
                if(i==2){
                    chiTietBaiHoc.setDapan(edt_da3.getText().toString());
                }
                if(i==3){
                    chiTietBaiHoc.setDapan(edt_da4.getText().toString());
                }
              

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                chiTietBaiHoc.setDapan(edt_da1.getText().toString());
            }
        });
        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chiTietBaiHoc.setId(random.nextInt() + "");
                chiTietBaiHoc.setA(edt_da1.getText().toString());
                chiTietBaiHoc.setB(edt_da2.getText().toString());
                chiTietBaiHoc.setC(edt_da3.getText().toString());
                chiTietBaiHoc.setD(edt_da4.getText().toString());
                chiTietBaiHoc.setNghiatiengviet(edt_nghiaTV.getText().toString());

                if (uriVideo != null) {
                    String video = uriVideo.getPath().split("/")[uriVideo.getPath().split("/").length - 1];
                    chiTietBaiHoc.setVideo(video);
                    ctbhDBcontext.uploadVideo(uriVideo, chiTietBaiHoc);
                } else {
                    String image = uriImage.getPath().split("/")[uriImage.getPath().split("/").length - 1];
                    chiTietBaiHoc.setTuonghinh(image);
                    if(uriRecorder!=null){
                        String sound = uriRecorder.getPath().split("/")[uriRecorder.getPath().split("/").length - 1];
                        chiTietBaiHoc.setAmthanh(sound);
                        ctbhDBcontext.uploadHinhAnh(uriImage, chiTietBaiHoc);
                        ctbhDBcontext.uploadRecorder(uriRecorder, chiTietBaiHoc);
                    }else{
                        Toast.makeText(getApplicationContext(),"Thiếu âm thanh",Toast.LENGTH_LONG).show();
                    }

                }
                Common.chiTietBaiHocList.add(chiTietBaiHoc);
                Common.baiHoc.setChiTietBaiHocList(Common.chiTietBaiHocList);
                dbContext.capNhatBaiHoc(Common.baiHoc, UpdateCTBaiHocActivity.this);
                Common.chiTietBaiHoc=null;
                finish();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == REQUEST_IMAGE) {
                //mở hình ảnh
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_IMAGE);
            }
            if (requestCode == REQUEST_VIDEO) {
                //mở video
                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.addCategory(Intent.CATEGORY_OPENABLE);
                chooseFile.setType("video/*");
                startActivityForResult(
                        Intent.createChooser(chooseFile, "Choose a file"),
                        REQUEST_VIDEO
                );
            }
            if (requestCode == REQUEST_RECORDER) {
                //mở âm thanh
                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.addCategory(Intent.CATEGORY_OPENABLE);
                chooseFile.setType("audio/*");
                startActivityForResult(
                        Intent.createChooser(chooseFile, "Choose a file"),
                        REQUEST_RECORDER
                );
            }
        }

    }

    Uri uriImage;
    Uri uriVideo;
    Uri uriRecorder;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null) {
            uriImage = data.getData();
            InputStream inputStream = null;
            try {
                inputStream = getContentResolver().openInputStream(uriImage);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img_tuonghinh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        if (requestCode == REQUEST_VIDEO && resultCode == RESULT_OK && data != null) {
            uriVideo = data.getData();
            btn_video.setText(uriVideo.getPath());

        }
        if (requestCode == REQUEST_RECORDER && resultCode == RESULT_OK && data != null) {
            uriRecorder = data.getData();
            btn_amthanh.setText(uriRecorder.getPath());

        }
    }
}