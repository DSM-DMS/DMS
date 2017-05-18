package com.dms.beinone.application.meal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;

import java.util.Calendar;

/**
 * Created by BeINone on 2017-05-16.
 */

public class MealPagerAdapter extends FragmentStatePagerAdapter {

    public static final int NUM_PAGES = 1000;
    public static final int START_POSITION = NUM_PAGES / 2;

    private SparseArray<MealCardFragment> mFragments;

    private final Calendar BASE_CAL;

    public MealPagerAdapter(FragmentManager fm) {
        super(fm);
        BASE_CAL = Calendar.getInstance();
        mFragments = new SparseArray<>();
    }

    @Override
    public Fragment getItem(int position) {
        MealCardFragment mealCardFragment = mFragments.get(position);
        if (mealCardFragment == null) {
            int howFarFromStart = position - START_POSITION;
            Calendar cal = (Calendar) BASE_CAL.clone();
            cal.add(Calendar.DATE, howFarFromStart);

            mealCardFragment = MealCardFragment.newInstance(cal.getTime());
            mFragments.put(position, mealCardFragment);
        }

        return mealCardFragment;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
