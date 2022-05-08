package com.example.phanmemhoctiengtrung.Actitivy;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phanmemhoctiengtrung.FirebaseContext.TaiKhoanDBContext;
import com.example.phanmemhoctiengtrung.Model.TaiKhoan;
import com.example.phanmemhoctiengtrung.R;

public class DangKyActivity extends AppCompatActivity {
    EditText edt_emai, edt_matkhau, edt_hoten;
    TextView tv_ngaysinh;
    Button btn_dangky;
    TaiKhoanDBContext dbContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        initView();
        onClick();
        dbContext = new TaiKhoanDBContext();

    }

    private void initView() {
        edt_emai = findViewById(R.id.edt_email);
        edt_matkhau = findViewById(R.id.edt_matkhau);
        edt_hoten = findViewById(R.id.edt_hoten);
        tv_ngaysinh = findViewById(R.id.edt_ngaysinh);
        btn_dangky = findViewById(R.id.btn_dangky);
    }

    private void onClick() {
        tv_ngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogPickDate();
            }
        });
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edt_emai.getText().toString().equals("")&&!edt_matkhau.getText().toString().equals("")){
                    TaiKhoan taiKhoan = new TaiKhoan();
                    taiKhoan.setEmail(edt_emai.getText().toString());
                    taiKhoan.setMatkhau(edt_matkhau.getText().toString());
                    taiKhoan.setHoten(edt_hoten.getText().toString());
                    taiKhoan.setNgaysinh(tv_ngaysinh.getText().toString());
                    taiKhoan.setAdmin(false);
                    dbContext.dangKy(taiKhoan,DangKyActivity.this);
                }
                else{
                    Common.dialog(DangKyActivity.this,"Email hoặc mật khẩu không được để trống");
                }
            }
        });
    }

    //thông báo
    private void dialogNoti(){

    }
    private void dialogPickDate() {
        int selectedYear = 2021;
        int selectedMonth = 12;
        int selectedDay = 1;
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                tv_ngaysinh.setText(i2 + "/" + i1 + "/" + i);
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(DangKyActivity.this,
                android.R.style.Theme_Holo_Dialog_NoActionBar,
                dateSetListener, selectedYear, selectedMonth, selectedDay);
        datePickerDialog.show();
    }
}