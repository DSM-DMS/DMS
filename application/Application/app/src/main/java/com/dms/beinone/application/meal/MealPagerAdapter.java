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

    public static final int MAX_PAGE = 1000;
    public static final int MIDDLE_INDEX = MAX_PAGE / 2;
    public static final int NUM_READY_PAGE = 3;

    private MealCardFragment[] mFragments;
    private Calendar mCalendar;

    public MealPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new MealCardFragment[MAX_PAGE];
        init();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }

    public void onPrev(int currentItemPosition) {
        if (mFragments[currentItemPosition - NUM_READY_PAGE] == null) {
            if (currentItemPosition >= NUM_READY_PAGE) {
                Calendar cal = (Calendar) mCalendar.clone();
                cal.add(Calendar.DATE, -NUM_READY_PAGE);
                Log.d("testLog", "mCalendar: " + mCalendar.get(Calendar.DATE) + ", cal: " + cal.get(Calendar.DATE));
                mFragments[currentItemPosition - NUM_READY_PAGE] = MealCardFragment.newInstance(cal.getTime());
            }
        }

        mCalendar.add(Calendar.DATE, -1);
    }

    public void onNext(int currentItemPosition) {
        if (mFragments[currentItemPosition + NUM_READY_PAGE] == null) {
            if (currentItemPosition <= MAX_PAGE - NUM_READY_PAGE) {
                Calendar cal = (Calendar) mCalendar.clone();
                cal.add(Calendar.DATE, NUM_READY_PAGE);
                Log.d("testLog", "mCalendar: " + mCalendar.get(Calendar.DATE) + ", cal: " + cal.get(Calendar.DATE));
                mFragments[currentItemPosition + NUM_READY_PAGE] = MealCardFragment.newInstance(cal.getTime());
            }
        }

        mCalendar.add(Calendar.DATE, 1);
    }

    private void init() {
        Calendar cal = Calendar.getInstance();
        mFragments[MIDDLE_INDEX] = MealCardFragment.newInstance(cal.getTime());
        cal.add(Calendar.DATE, -1);
        mFragments[MIDDLE_INDEX - 1] = MealCardFragment.newInstance(cal.getTime());
        cal.add(Calendar.DATE, -1);
        mFragments[MIDDLE_INDEX - 2] = MealCardFragment.newInstance(cal.getTime());
        cal.add(Calendar.DATE, -1);
        mFragments[MIDDLE_INDEX - 3] = MealCardFragment.newInstance(cal.getTime());
        cal.add(Calendar.DATE, NUM_READY_PAGE + 1);
        mFragments[MIDDLE_INDEX + 1] = MealCardFragment.newInstance(cal.getTime());
        cal.add(Calendar.DATE, 1);
        mFragments[MIDDLE_INDEX + 2] = MealCardFragment.newInstance(cal.getTime());
        cal.add(Calendar.DATE, 1);
        mFragments[MIDDLE_INDEX + 3] = MealCardFragment.newInstance(cal.getTime());

        mCalendar = Calendar.getInstance();
    }
}
