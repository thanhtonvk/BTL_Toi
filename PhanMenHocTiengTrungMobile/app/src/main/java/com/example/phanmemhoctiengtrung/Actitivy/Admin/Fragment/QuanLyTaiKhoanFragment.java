package com.example.phanmemhoctiengtrung.Actitivy.Admin.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.example.phanmemhoctiengtrung.Actitivy.Admin.Adapter.TaiKhoanAdapter;
import com.example.phanmemhoctiengtrung.FirebaseContext.TaiKhoanDBContext;
import com.example.phanmemhoctiengtrung.Model.TaiKhoan;
import com.example.phanmemhoctiengtrung.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class QuanLyTaiKhoanFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly_tai_khoan, container, false);
    }

    ListView lv_taikhoan;
    AutoCompleteTextView edt_timkiem;
    TaiKhoanDBContext dbContext;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv_taikhoan = view.findViewById(R.id.lv_taikhoan);
        edt_timkiem = view.findViewById(R.id.edt_timkiem);
        dbContext = new TaiKhoanDBContext();
        taiKhoanList = new ArrayList<>();
        loadTaiKhoan();
    }
    List<TaiKhoan> taiKhoanList;
    private void loadTaiKhoan(){
        dbContext.reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                taiKhoanList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()
                     ) {
                    TaiKhoan taiKhoan= dataSnapshot.getValue(TaiKhoan.class);
                    taiKhoanList.add(taiKhoan);
                }
                if(getContext()!=null){
                    TaiKhoanAdapter adapter = new TaiKhoanAdapter(getContext(),taiKhoanList);
                    lv_taikhoan.setAdapter(adapter);
                    edt_timkiem.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}