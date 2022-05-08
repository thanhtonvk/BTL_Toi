package com.example.phanmemhoctiengtrung.Actitivy.Admin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.phanmemhoctiengtrung.Model.BaiHoc;
import com.example.phanmemhoctiengtrung.R;

import java.util.List;

public class BaiHocAdapter extends ArrayAdapter<BaiHoc> {

    List<BaiHoc>baiHocList;
    List<Integer>hinhAnhs;
    Context context;
    public BaiHocAdapter(@NonNull Context context,List<BaiHoc>baiHocList,List<Integer>hinhAnhs) {
        super(context, 0,baiHocList);
        this.baiHocList= baiHocList;
        this.context =context;
        this.hinhAnhs = hinhAnhs;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_baihoc,parent,false);
        }
        ImageView img_avatar = convertView.findViewById(R.id.img_avatar);
        TextView tv_tenbai = convertView.findViewById(R.id.tv_tenbai);
        int res = hinhAnhs.get(position);
        BaiHoc baiHoc= baiHocList.get(position);
        tv_tenbai.setText(baiHoc.getChude());
        img_avatar.setImageResource(res);

        return convertView;
    }
}
