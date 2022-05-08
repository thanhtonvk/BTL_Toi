package com.example.phanmemhoctiengtrung;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phanmemhoctiengtrung.Actitivy.Admin.AdminActivity;
import com.example.phanmemhoctiengtrung.Actitivy.BeginActivity;
import com.example.phanmemhoctiengtrung.Actitivy.DangNhapActivity;
import com.example.phanmemhoctiengtrung.Actitivy.User.UserActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                finish();
                startActivity(new Intent(getApplicationContext(), BeginActivity.class));
            }
        };
        countDownTimer.start();
    }
}