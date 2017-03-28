package com.dms.beinone.application.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by BeINone on 2017-03-12.
 */

public class HomeMealFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    private static final int PAGE_COUNT = 3;
    private static final int POS_BREAKFAST = 0;
    private static final int POS_LUNCH = 1;
    private static final int POS_DINNER = 2;
    private static final String[] PAGE_TITLES = {"아침", "점심", "저녁"};

    private HomeMealFragment[] mHomeMealFragments;

    public HomeMealFragmentStatePagerAdapter(FragmentManager fm, HomeMealFragment[] homeMealFragments) {
        super(fm);
        mHomeMealFragments = homeMealFragments;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("testLog", "getItem: " + position);
        return mHomeMealFragments[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return PAGE_TITLES[position];
    }

    public void changeItems(HomeMealFragment[] homeMealFragments) {
        mHomeMealFragments = homeMealFragments;
        notifyDataSetChanged();
    }

}
