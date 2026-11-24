package com.example.mid_exam_mahmoud_jourani;

import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ModeActivity extends AppCompatActivity {

    private static final String[] TITLES = {"First", "Second", "Third"};
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        viewPager.setAdapter(new PagerAdapter(this));
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(TITLES[position])).attach();

        findViewById(R.id.btnSetMode).setOnClickListener(this::showModeMenu);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (viewPager.getCurrentItem() != 0) {
                    viewPager.setCurrentItem(0);
                } else {
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });
    }

    private void showModeMenu(View anchor) {
        PopupMenu popup = new PopupMenu(this, anchor);
        popup.getMenu().add(0, 1, 0, "Light");
        popup.getMenu().add(0, 2, 1, "Night");
        popup.getMenu().add(0, 3, 2, "System");
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case 1:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    break;
                case 2:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    break;
                case 3:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                    break;
            }
            return true;
        });
        popup.show();
    }

    private static class PagerAdapter extends FragmentStateAdapter {
        PagerAdapter(FragmentActivity activity) {
            super(activity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return TabFragment.newInstance(TITLES[position]);
        }

        @Override
        public int getItemCount() {
            return TITLES.length;
        }
    }
}