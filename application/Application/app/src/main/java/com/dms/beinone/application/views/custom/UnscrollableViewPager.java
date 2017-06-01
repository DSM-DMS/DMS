package com.dms.beinone.application.views.custom;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by BeINone on 2017-06-01.
 */

public class UnscrollableViewPager extends ViewPager {

    public UnscrollableViewPager(Context context) {
        super(context);
    }

    public UnscrollableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean canScrollHorizontally(int direction) {
        return false;
    }
}
