package com.example.phanmemhoctiengtrung.Actitivy.User;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.example.phanmemhoctiengtrung.Actitivy.Common;
import com.example.phanmemhoctiengtrung.FirebaseContext.TaiKhoanDBContext;
import com.example.phanmemhoctiengtrung.R;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ThongTinCaNhanActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);
        onClick();
        anhXa();
        loadData();
    }

    EditText edt_hoten, edt_ngaysinh, edt_sdt;
    ImageView img_avatar;
    TaiKhoanDBContext dbContext;

    private void loadData() {
        edt_hoten.setText(Common.taiKhoan.getHoten());
        edt_ngaysinh.setText(Common.taiKhoan.getNgaysinh());
        edt_sdt.setText(Common.taiKhoan.getSdt());
        if (Common.taiKhoan.getHinhanh() != null) {
            if (!Common.taiKhoan.getHinhanh().equals("")) {
                dbContext.storageReference.child(Common.taiKhoan.getId()).child(Common.taiKhoan.getHinhanh()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(ThongTinCaNhanActivity.this).load(uri).into(img_avatar);
                    }
                });
            }
        }

    }

    private void anhXa() {
        edt_hoten = findViewById(R.id.edt_hoten);
        edt_ngaysinh = findViewById(R.id.edt_ngaysinh);
        edt_sdt = findViewById(R.id.edt_sdt);
        img_avatar = findViewById(R.id.img_avatar);
        dbContext = new TaiKhoanDBContext();
    }

    int REQUEST_IMAGE = 123;

    private void onClick() {
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.layout_avatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(ThongTinCaNhanActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_IMAGE);

            }
        });
        findViewById(R.id.btn_luu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (uriImage != null) {
                    Common.taiKhoan.setSdt(edt_sdt.getText().toString());
                    Common.taiKhoan.setHoten(edt_hoten.getText().toString());
                    Common.taiKhoan.setNgaysinh(edt_ngaysinh.getText().toString());
                    dbContext.uploadAnh(uriImage, Common.taiKhoan, ThongTinCaNhanActivity.this);
                    Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Common.taiKhoan.setSdt(edt_sdt.getText().toString());
                    Common.taiKhoan.setHoten(edt_hoten.getText().toString());
                    Common.taiKhoan.setNgaysinh(edt_ngaysinh.getText().toString());
                    dbContext.suaTaiKhoan(Common.taiKhoan, ThongTinCaNhanActivity.this);
                    Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_SHORT).show();
                }

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
        }
    }

    Uri uriImage;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null) {
            uriImage = data.getData();
            InputStream inputStream = null;
            try {
                inputStream = getContentResolver().openInputStream(uriImage);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img_avatar.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}