package com.dms.beinone.application.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.dms.beinone.application.R;
import com.dms.beinone.application.views.adapters.MainPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_main);
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout_main);
        tabLayout.setupWithViewPager(viewPager);
        for (int index = 0; index < tabLayout.getTabCount(); index++) {
            tabLayout.getTabAt(index).setIcon(MainPagerAdapter.mIconResIds[index]);
        }
    }
}
