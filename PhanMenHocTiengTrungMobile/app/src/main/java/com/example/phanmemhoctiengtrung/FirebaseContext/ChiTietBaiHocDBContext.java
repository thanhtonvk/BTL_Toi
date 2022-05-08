package com.example.phanmemhoctiengtrung.FirebaseContext;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.phanmemhoctiengtrung.Model.ChiTietBaiHoc;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ChiTietBaiHocDBContext {
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;
    public StorageReference storageReference;
    public FirebaseStorage firebaseStorage;
    public StorageReference storageReference1;
    public FirebaseStorage firebaseStorage1;
    Context context;

    public ChiTietBaiHocDBContext(Context context) {
        this.context = context;
        firebaseStorage = FirebaseStorage.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage1 = FirebaseStorage.getInstance();
        databaseReference = firebaseDatabase.getReference("ChiTietBaiHoc");
        storageReference = firebaseStorage.getReference("File");
        storageReference1 = firebaseStorage1.getReference("File");
    }

    public void update(ChiTietBaiHoc chiTietBaiHoc) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setTitle("Vui lòng chờ...");
        dialog.show();
        databaseReference.child(chiTietBaiHoc.getId()).setValue(chiTietBaiHoc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "thất bại", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
    }

    public void delete(ChiTietBaiHoc chiTietBaiHoc) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.show();
        databaseReference.child(chiTietBaiHoc.getId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    dialog.dismiss();
                    Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
                } else {
                    dialog.dismiss();
                    Toast.makeText(context, "Thất bại, kiểm tra đường truyền", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void uploadHinhAnh(Uri uri, ChiTietBaiHoc chiTietBaiHoc) {
        storageReference = storageReference.child(chiTietBaiHoc.getId()).child("Image").child(uri.getPath().split("/")[uri.getPath().split("/").length - 1]);
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String path = uri.getPath().split("/")[uri.getPath().split("/").length-1];
                chiTietBaiHoc.setTuonghinh(path);

            }
        });
    }
    public void uploadVideo(Uri uri,ChiTietBaiHoc chiTietBaiHoc){
        storageReference = storageReference.child(chiTietBaiHoc.getId()).child("Video").child(uri.getPath().split("/")[uri.getPath().split("/").length - 1]);
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String path = uri.getPath().split("/")[uri.getPath().split("/").length-1];
                chiTietBaiHoc.setVideo(path);

            }
        });
    }
    public void uploadRecorder(Uri uri,ChiTietBaiHoc chiTietBaiHoc){
        storageReference1 = storageReference1.child(chiTietBaiHoc.getId()).child("Recorder").child(uri.getPath().split("/")[uri.getPath().split("/").length - 1]);
        storageReference1.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String path = uri.getPath().split("/")[uri.getPath().split("/").length-1];
                chiTietBaiHoc.setAmthanh(path);

            }
        });
    }
}
