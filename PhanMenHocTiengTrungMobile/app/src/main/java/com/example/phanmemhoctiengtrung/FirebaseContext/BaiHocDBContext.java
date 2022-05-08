package com.example.phanmemhoctiengtrung.FirebaseContext;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.phanmemhoctiengtrung.Actitivy.Common;
import com.example.phanmemhoctiengtrung.Model.BaiHoc;
import com.example.phanmemhoctiengtrung.Model.TaiKhoan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BaiHocDBContext {
    public FirebaseDatabase database;
    public DatabaseReference reference;

    public BaiHocDBContext() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("BaiHoc");
    }
    public void capNhatBaiHoc(BaiHoc baiHoc,Context context){
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setTitle("Vui lòng chờ...");
        dialog.show();
        reference.child(baiHoc.getId()+"").setValue(baiHoc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Common.dialog(context,"Thành công");
                    dialog.dismiss();
                }else{
                    Common.dialog(context,"Lỗi!!");
                    dialog.dismiss();
                }
            }
        });
    }
}
