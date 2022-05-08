package com.example.phanmemhoctiengtrung.Actitivy.Admin.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.phanmemhoctiengtrung.Actitivy.Admin.Fragment.QuanLyBaiHocFragment;
import com.example.phanmemhoctiengtrung.Actitivy.Admin.Fragment.QuanLyTaiKhoanFragment;
import com.example.phanmemhoctiengtrung.Actitivy.User.Fragment.BaiKiemTraFragment;
import com.example.phanmemhoctiengtrung.Actitivy.User.Fragment.CaNhanFragment;
import com.example.phanmemhoctiengtrung.Actitivy.User.Fragment.HocBaiFragment;

public class AdminAdapter extends FragmentStatePagerAdapter {
    public AdminAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new QuanLyBaiHocFragment();
            case 1:
                return new QuanLyTaiKhoanFragment();
            default:
                return new QuanLyBaiHocFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
