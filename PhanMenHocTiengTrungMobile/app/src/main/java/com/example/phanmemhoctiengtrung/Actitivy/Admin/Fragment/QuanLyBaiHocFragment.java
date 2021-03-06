package com.example.phanmemhoctiengtrung.Actitivy.Admin.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.phanmemhoctiengtrung.Actitivy.Admin.Adapter.BaiHocAdapter;
import com.example.phanmemhoctiengtrung.Actitivy.Admin.QuanLyChiTietBaiHocActivity;
import com.example.phanmemhoctiengtrung.Actitivy.Common;
import com.example.phanmemhoctiengtrung.FirebaseContext.BaiHocDBContext;
import com.example.phanmemhoctiengtrung.Model.BaiHoc;
import com.example.phanmemhoctiengtrung.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuanLyBaiHocFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly_bai_hoc, container, false);
    }

    ListView lv_baihoc;
    BaiHocDBContext dbContext;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv_baihoc = view.findViewById(R.id.lv_baihoc);
        dbContext = new BaiHocDBContext();
        //updateBaiHoc();
        setImages();
        loadDSBaiHoc();
        onClick();
    }

    List<BaiHoc> baiHocList;
    List<Integer> hinhAnhs;

    private void onClick() {
        lv_baihoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Common.baiHoc = baiHocList.get(i);
                startActivity(new Intent(getContext(), QuanLyChiTietBaiHocActivity.class));
            }
        });
    }

    private void setImages() {
        hinhAnhs = new ArrayList<>();
        //set hinh anh cho bai hoc
        hinhAnhs.add(R.drawable.ic_bai1);
        hinhAnhs.add(R.drawable.ic_bai2);
        hinhAnhs.add(R.drawable.ic_bai3);
        hinhAnhs.add(R.drawable.ic_bai4);
        hinhAnhs.add(R.drawable.ic_bai5);
        hinhAnhs.add(R.drawable.ic_bai6);
        hinhAnhs.add(R.drawable.ic_bai7);
        hinhAnhs.add(R.drawable.ic_bai8);
        hinhAnhs.add(R.drawable.ic_bai9);
        hinhAnhs.add(R.drawable.ic_bai10);
    }

    public void updateBaiHoc() {
        dbContext.capNhatBaiHoc(new BaiHoc("1", "T??m hi???u b???n th??n"), getContext());
        dbContext.capNhatBaiHoc(new BaiHoc("2", "C??u h???i ngh??? nghi???p"), getContext());
        dbContext.capNhatBaiHoc(new BaiHoc("3", "Qu???c t???ch"), getContext());
        dbContext.capNhatBaiHoc(new BaiHoc("4", "Th??nh vi??n trong gia ????nh"), getContext());
        dbContext.capNhatBaiHoc(new BaiHoc("5", "Kh??? n??ng ng??n ng???"), getContext());
        dbContext.capNhatBaiHoc(new BaiHoc("6", "?????m s??? 1-5"), getContext());
        dbContext.capNhatBaiHoc(new BaiHoc("7", "?????m s??? t??? 6-10"), getContext());
        dbContext.capNhatBaiHoc(new BaiHoc("8", "Ng??y th??ng trong n??m"), getContext());
        dbContext.capNhatBaiHoc(new BaiHoc("9", "Th???i gian"), getContext());
        dbContext.capNhatBaiHoc(new BaiHoc("10", "Cu???c s???ng h??ng ng??y"), getContext());
    }

    private void loadDSBaiHoc() {
        dbContext.reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                baiHocList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    BaiHoc baiHoc = dataSnapshot.getValue(BaiHoc.class);
                    baiHocList.add(baiHoc);
                }
                if (getContext() != null) {
                    BaiHocAdapter adapter = new BaiHocAdapter(getContext(), baiHocList, hinhAnhs);
                    lv_baihoc.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}