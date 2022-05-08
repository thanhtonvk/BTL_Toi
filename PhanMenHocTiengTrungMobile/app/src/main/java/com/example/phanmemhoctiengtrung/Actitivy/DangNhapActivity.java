package com.example.phanmemhoctiengtrung.Actitivy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.phanmemhoctiengtrung.Actitivy.Admin.AdminActivity;
import com.example.phanmemhoctiengtrung.Actitivy.User.UserActivity;
import com.example.phanmemhoctiengtrung.FirebaseContext.TaiKhoanDBContext;
import com.example.phanmemhoctiengtrung.Model.TaiKhoan;
import com.example.phanmemhoctiengtrung.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class DangNhapActivity extends AppCompatActivity {

    Button btn_dangnhap;
    TaiKhoanDBContext dbContext;
    EditText edt_email,edt_matkhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        anhXa();
        dbContext = new TaiKhoanDBContext();
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edt_matkhau.getText().toString().equals("")&&!edt_email.getText().toString().equals("")){
                 dangNhap(edt_email.getText().toString(),edt_matkhau.getText().toString());
                }else{
                    Common.dialog(DangNhapActivity.this,"Email hoặc mật khẩu không được để trống");
                }
            }
        });
    }

    private void anhXa() {
        btn_dangnhap = findViewById(R.id.btn_dangnhap);
        edt_email = findViewById(R.id.edt_email);
        edt_matkhau = findViewById(R.id.edt_matkhau);

    }
    public void dangNhap(String email, String matkhau) {
        ProgressDialog dialog = new ProgressDialog(DangNhapActivity.this);
        dialog.setTitle("Đang đăng nhập...");
        dialog.show();
        dbContext.auth.signInWithEmailAndPassword(email, matkhau).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    dbContext.reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()
                                 ) {
                                TaiKhoan taiKhoan = dataSnapshot.getValue(TaiKhoan.class);
                                if(dbContext.auth.getCurrentUser().getUid().equals(taiKhoan.getId())){
                                    Common.taiKhoan = taiKhoan;
                                    if(taiKhoan.isAdmin()){
                                        startActivity(new Intent(DangNhapActivity.this, AdminActivity.class));
                                        finish();
                                    }
                                   else{
                                        startActivity(new Intent(DangNhapActivity.this,UserActivity.class));
                                        finish();
                                        //tài khoản bình thường
                                    }
                                }
                                dialog.dismiss();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(),"Tài khoản hoặc mật khẩu không chính xác",Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });
    }
}