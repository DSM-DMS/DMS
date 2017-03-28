package com.dms.beinone.application.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dms.beinone.application.R;
import com.dms.beinone.application.meal.Meal;

/**
 * Created by BeINone on 2017-03-12.
 */

public class HomeMealFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 3;
    private static final int POS_BREAKFAST = 0;
    private static final int POS_LUNCH = 1;
    private static final int POS_DINNER = 2;
    private static final String[] PAGE_TITLES = {"아침", "점심", "저녁"};

    private HomeMealFragment[] mHomeMealFragments;

    public HomeMealFragmentPagerAdapter(FragmentManager fm, Context context, Meal meal) {
        super(fm);
        mHomeMealFragments = new HomeMealFragment[PAGE_COUNT];

        if (meal == null) {
            String noMealString = context.getString(R.string.meal_failure);
            for (int index = 0; index < PAGE_COUNT; index++) {
                mHomeMealFragments[index] = HomeMealFragment.newInstance(context, noMealString);
            }
        } else {
            for (int index = 0; index < PAGE_COUNT; index++) {
                mHomeMealFragments[index] = HomeMealFragment.newInstance(context, meal.get(index));
            }
        }
    }

    public HomeMealFragmentPagerAdapter(FragmentManager fm, HomeMealFragment[] homeMealFragments) {
        super(fm);
        mHomeMealFragments = homeMealFragments;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return mHomeMealFragments[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return PAGE_TITLES[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setData(Meal meal) {
        for (int index = 0; index < mHomeMealFragments.length; index++) {
            mHomeMealFragments[index].setMealString(meal.get(index));
        }
    }

    public void changeItems(HomeMealFragment[] homeMealFragments) {
        mHomeMealFragments = homeMealFragments;
        notifyDataSetChanged();
    }

}
