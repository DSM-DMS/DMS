package com.dms.beinone.application.meal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by BeINone on 2017-05-16.
 */

public class MealPagerAdapter extends FragmentStatePagerAdapter {

    public static final int MAX_PAGE = 5;
    public static final int MIDDLE_INDEX = MAX_PAGE / 2;

    private MealCardFragment currentFragment;
    private MealCardFragment prevFragment, nextFragment;
    private Calendar mCalendar;

    public MealPagerAdapter(FragmentManager fm) {
        super(fm);
        init();
    }

    public void onPrev(int currentItemPosition) {
        mCalendar.add(Calendar.DATE, -1);
        currentFragment = prevFragment;
        Calendar cal = (Calendar) mCalendar.clone();
        cal.add(Calendar.DATE, -1);
        prevFragment = MealCardFragment.newInstance(cal.getTime());

    }

    public void onNext(int currentItemPosition) {
        mCalendar.add(Calendar.DATE, 1);
        currentFragment = nextFragment;
        Calendar cal = (Calendar) mCalendar.clone();
        cal.add(Calendar.DATE, 1);
        nextFragment = MealCardFragment.newInstance(cal.getTime());
    }

    @Override
    public Fragment getItem(int position) {
        return currentFragment;
    }

    @Override
    public int getCount() {
        return MAX_PAGE;
    }

    private void init() {
        Calendar cal = Calendar.getInstance();
        currentFragment = MealCardFragment.newInstance(cal.getTime());
        cal.add(Calendar.DATE, -1);
        prevFragment = MealCardFragment.newInstance(cal.getTime());
        cal.add(Calendar.DATE, 2);
        nextFragment = MealCardFragment.newInstance(cal.getTime());
        mCalendar = Calendar.getInstance();
    }
}
