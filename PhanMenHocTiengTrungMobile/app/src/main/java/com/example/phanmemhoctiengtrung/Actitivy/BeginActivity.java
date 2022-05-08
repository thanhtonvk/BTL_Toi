package com.example.phanmemhoctiengtrung.Actitivy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phanmemhoctiengtrung.R;

public class BeginActivity extends AppCompatActivity {

    Button btn_dangnhap, btn_dangky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        anhXa();
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DangNhapActivity.class));
            }
        });
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DangKyActivity.class));
            }
        });
    }

    private void anhXa() {
        btn_dangky = findViewById(R.id.btn_dangky);
        btn_dangnhap = findViewById(R.id.btn_dangnhap);
    }
}