package com.dms.beinone.application.views.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dms.beinone.application.R;
import com.dms.beinone.application.fragments.ApplyListFragment;
import com.dms.beinone.application.fragments.DormitoryListFragment;
import com.dms.beinone.application.fragments.MealFragment;
import com.dms.beinone.application.fragments.MyPageFragment;

/**
 * Created by BeINone on 2017-05-16.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private static final int COUNT = 4;
    private static final int POS_MEAL = 0;
    private static final int POS_APPLY = 1;
    private static final int POS_DORMITORY = 2;
    private static final int POS_MYPAGE = 3;

    public static final int[] mIconResIds =
            {R.drawable.ic_meal, R.drawable.ic_apply,R.drawable.ic_warning, R.drawable.ic_account};

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case POS_MEAL:
                return new MealFragment();
            case POS_APPLY:
                return new ApplyListFragment();
            case POS_DORMITORY:
                return new DormitoryListFragment();
            case POS_MYPAGE:
                return new MyPageFragment();
            default:
                return new ApplyListFragment();
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
