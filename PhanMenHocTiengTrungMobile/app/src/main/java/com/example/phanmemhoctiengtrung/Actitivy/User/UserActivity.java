package com.example.phanmemhoctiengtrung.Actitivy.User;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.phanmemhoctiengtrung.Actitivy.User.Adapter.UserAdapter;
import com.example.phanmemhoctiengtrung.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserActivity extends AppCompatActivity {

    BottomNavigationView bottomNavBar;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        anhXa();
        setViewPager();
    }

    private void anhXa() {
        bottomNavBar = findViewById(R.id.bottom_bar);
        viewPager = findViewById(R.id.viewpager);
    }

    private void setViewPager() {
        UserAdapter adapter = new UserAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavBar.getMenu().findItem(R.id.item_hocbai).setChecked(true);
                        break;
                    case 1:
                        bottomNavBar.getMenu().findItem(R.id.item_lambai).setChecked(true);
                        break;
                    case 2:
                        bottomNavBar.getMenu().findItem(R.id.item_person).setChecked(true);
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
                    case R.id.item_hocbai:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.item_lambai:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.item_person:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }
}