package com.example.phanmemhoctiengtrung.Actitivy.Admin.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.phanmemhoctiengtrung.FirebaseContext.ChiTietBaiHocDBContext;
import com.example.phanmemhoctiengtrung.Model.ChiTietBaiHoc;
import com.example.phanmemhoctiengtrung.R;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class CTBaiHocAdapter extends ArrayAdapter<ChiTietBaiHoc> {
    Context context;
    List<ChiTietBaiHoc>chiTietBaiHocList;
    ChiTietBaiHocDBContext dbContext;
    public CTBaiHocAdapter(@NonNull Context context, List<ChiTietBaiHoc>chiTietBaiHocList) {
        super(context, 0,chiTietBaiHocList);
        this.context = context;
        this.chiTietBaiHocList = chiTietBaiHocList;
        dbContext= new ChiTietBaiHocDBContext(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView ==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_ctbaihoc,parent,false);
        }
        ChiTietBaiHoc chiTietBaiHoc = chiTietBaiHocList.get(position);
        TextView tv_nghia = convertView.findViewById(R.id.tv_nghiatv);
        ImageView img = convertView.findViewById(R.id.hinhanh);
        tv_nghia.setText(chiTietBaiHoc.getNghiatiengviet());
        if(chiTietBaiHoc.getTuonghinh()!=null){
            if(context!=null){
                dbContext.storageReference.child(chiTietBaiHoc.getId()).child("Image").child(chiTietBaiHoc.getTuonghinh()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(context).load(uri).into(img);
                    }
                });
            }

        }
        return convertView;
    }
}
