package com.example.phanmemhoctiengtrung.Actitivy.Admin.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.phanmemhoctiengtrung.FirebaseContext.TaiKhoanDBContext;
import com.example.phanmemhoctiengtrung.Model.TaiKhoan;
import com.example.phanmemhoctiengtrung.R;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class TaiKhoanAdapter extends ArrayAdapter<TaiKhoan> {
    Context context;
    List<TaiKhoan>taiKhoanList;
    TaiKhoanDBContext dbContext;
    public TaiKhoanAdapter(@NonNull Context context,List<TaiKhoan>taiKhoanList) {
        super(context, 0, taiKhoanList);
        this.context = context;
        this.taiKhoanList = taiKhoanList;
        dbContext = new TaiKhoanDBContext();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_taikhoan,parent,false);
        }
        TextView tv_email = convertView.findViewById(R.id.tv_email);
        TextView tv_hoten = convertView.findViewById(R.id.tv_hoten);
        ImageView img_avatar = convertView.findViewById(R.id.img_avatar);
        TaiKhoan taiKhoan = taiKhoanList.get(position);
        tv_email.setText(taiKhoan.getEmail());
        tv_hoten.setText(taiKhoan.getHoten());
        if(taiKhoan.getHinhanh()!=null){
            if(!taiKhoan.getHinhanh().equals("")){
                dbContext.storageReference.child(taiKhoan.getId()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(context).load(uri).into(img_avatar);
                    }
                });
            }
        }
        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            List<TaiKhoan>suggestions = new ArrayList<>();
            if(charSequence==null||charSequence.length()==0){
                suggestions.addAll(taiKhoanList);
            }else{
                String filter = charSequence.toString().toLowerCase().trim();
                for (TaiKhoan item:taiKhoanList
                     ) {
                    if(item.getHoten().toLowerCase().contains(filter)||
                    item.getEmail().toLowerCase().contains(filter)||
                    item.getNgaysinh().toLowerCase().contains(filter)||
                    item.getSdt().toLowerCase().contains(filter)){
                        suggestions.add(item);
                    }
                }
            }
            results.values = suggestions;
            results.count = suggestions.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            List<TaiKhoan> filtered = (ArrayList<TaiKhoan>) filterResults.values;
            notifyDataSetChanged();
            clear();
            if(filtered!=null){
                for (int i = 0, l = filtered.size(); i < l; i++)
                    add((TaiKhoan) filtered.get(i));
            }

            notifyDataSetInvalidated();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((TaiKhoan)resultValue).getHoten();
        }
    };
}
