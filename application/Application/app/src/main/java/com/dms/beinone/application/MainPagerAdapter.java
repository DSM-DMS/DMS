package com.dms.beinone.application;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dms.beinone.application.meal.MealFragment;

/**
 * Created by BeINone on 2017-05-16.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private static final int COUNT = 4;
    private static final int POS_MEAL = 0;
    private static final int POS_APPLY = 1;
    private static final int POS_NOTICE = 2;
    private static final int POS_MYPAGE = 3;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case POS_MEAL:
                return new MealFragment();
        }

        return new MealFragment();
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
