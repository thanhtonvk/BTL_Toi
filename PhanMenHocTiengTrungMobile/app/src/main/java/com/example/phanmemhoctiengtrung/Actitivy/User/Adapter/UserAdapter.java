package com.example.phanmemhoctiengtrung.Actitivy.User.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.phanmemhoctiengtrung.Actitivy.User.Fragment.BaiKiemTraFragment;
import com.example.phanmemhoctiengtrung.Actitivy.User.Fragment.CaNhanFragment;
import com.example.phanmemhoctiengtrung.Actitivy.User.Fragment.HocBaiFragment;

public class UserAdapter  extends FragmentStatePagerAdapter {
    public UserAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HocBaiFragment();
            case 1:
                return new BaiKiemTraFragment();
            case 2:
                return new CaNhanFragment();
            default:
                return new HocBaiFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
