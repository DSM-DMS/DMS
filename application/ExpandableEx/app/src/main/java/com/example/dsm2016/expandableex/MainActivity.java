package com.example.dsm2016.expandableex;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    private static final int[] TAB_ICON = {
           R.drawable.ic_build_black_24dp,
           R.drawable.ic_description_black_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My title");
        viewPager=(ViewPager)findViewById(R.id.view_pager);
        setViewPager(viewPager);

        tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        setTabIcon();
    }

    private void setViewPager(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFrag(new FirstFragment());
        viewPagerAdapter.addFrag(new SecondFragment());
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void setTabIcon(){
        tabLayout.getTabAt(0).setIcon(TAB_ICON[0]);
        tabLayout.getTabAt(1).setIcon(TAB_ICON[1]);
    }


    private class ViewPagerAdapter extends FragmentPagerAdapter{

        private final List<Fragment> mFragments=new ArrayList<>();

        public  ViewPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }


        public void addFrag(Fragment fragment) {
            mFragments.add(fragment);
        }
    }
}
