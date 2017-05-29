package com.dms.beinone.application.views.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.dms.beinone.application.R;
import com.dms.beinone.application.fragments.ApplyListFragment;
import com.dms.beinone.application.fragments.MealFragment;

/**
 * Created by BeINone on 2017-05-16.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private static final int COUNT = 4;
    private static final int POS_MEAL = 0;
    private static final int POS_APPLY = 1;
    private static final int POS_NOTICE = 2;
    private static final int POS_MYPAGE = 3;

    public static final int[] mIconResIds =
            {R.drawable.ic_meal, R.drawable.ic_apply,R.drawable.ic_warning, R.drawable.ic_account};

    private Context mContext;

    public MainPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case POS_MEAL:
                return new MealFragment();
            case POS_APPLY:
                return new ApplyListFragment();
            default:
                return new ApplyListFragment();
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        Drawable image = ContextCompat.getDrawable(mContext, mIconResIds[position]);
//        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
//        SpannableString sb = new SpannableString(" ");
//        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
//        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return sb;
//    }
}
