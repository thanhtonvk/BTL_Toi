package com.example.phanmemhoctiengtrung.Actitivy.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.phanmemhoctiengtrung.Actitivy.Admin.Adapter.AdminAdapter;
import com.example.phanmemhoctiengtrung.Actitivy.User.Adapter.UserAdapter;
import com.example.phanmemhoctiengtrung.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminActivity extends AppCompatActivity {

    ViewPager viewPager;
    BottomNavigationView bottomNavBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        anhXa();
        setViewPager();
    }
    private void anhXa() {
        bottomNavBar = findViewById(R.id.bottom_bar);
        viewPager = findViewById(R.id.viewpager);
    }
    private void setViewPager() {
        AdminAdapter adapter = new AdminAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavBar.getMenu().findItem(R.id.item_baihoc).setChecked(true);
                        break;
                    case 1:
                        bottomNavBar.getMenu().findItem(R.id.item_taikhoan).setChecked(true);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_baihoc:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.item_taikhoan:
                        viewPager.setCurrentItem(1);
                        break;
                }
                return true;
            }
        });
    }
}