package com.example.phanmemhoctiengtrung.FirebaseContext;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.phanmemhoctiengtrung.MainActivity;
import com.example.phanmemhoctiengtrung.Model.TaiKhoan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class TaiKhoanDBContext {
    public FirebaseAuth auth;
    public FirebaseDatabase database;
    public DatabaseReference reference;
    public FirebaseStorage storage;
    public StorageReference storageReference;
    public TaiKhoanDBContext() {
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("TaiKhoan");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("Avatar");
    }

    public void uploadAnh(Uri uri,TaiKhoan taiKhoan,Context context){
        storageReference = storageReference.child(taiKhoan.getId()).child(uri.getLastPathSegment());
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String path = uri.getPath().split("/")[uri.getPath().split("/").length-1];
                taiKhoan.setHinhanh(path);
                suaTaiKhoan(taiKhoan,context);
            }
        });
    }
    public void dangKy(TaiKhoan taiKhoan, Context context) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setTitle("Đang đăng ký...");
        dialog.show();
        auth.createUserWithEmailAndPassword(taiKhoan.getEmail(), taiKhoan.getMatkhau()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    taiKhoan.setId(firebaseUser.getUid());
                    reference.child(taiKhoan.getId()+"").setValue(taiKhoan).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }
                    });

                } else {
                    Toast.makeText(context, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            }
        });
    }


    public void xoaTaiKhoan(String id,Context context){
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setTitle("Đang đăng nhập...");
        dialog.show();
        reference.child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    dialog.dismiss();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }else{
                    dialog.dismiss();
                    Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void suaTaiKhoan(TaiKhoan taiKhoan,Context context){
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setTitle("Đang cập nhật thông tin...");
        dialog.show();
        reference.child(taiKhoan.getId()).setValue(taiKhoan).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    dialog.dismiss();
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }else{
                    dialog.dismiss();
                    Toast.makeText(context, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
