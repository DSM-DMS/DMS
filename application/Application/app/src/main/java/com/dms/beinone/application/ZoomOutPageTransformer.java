package com.dms.beinone.application;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by BeINone on 2017-05-18.
 */

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        ViewPager parent = (ViewPager) view.getParent();
        // calculate padding
        position -= parent.getPaddingRight() / (float) pageWidth;

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setScaleY(MIN_SCALE);
            view.setAlpha(0.5f);
        } else if (position <= 1) { // [-1,1]
            float scaleFactor = 1 - Math.abs(position) * (1 - MIN_SCALE);
            view.setScaleY(scaleFactor);
            view.setAlpha(MIN_ALPHA +
                    (scaleFactor - MIN_SCALE) /
                            (1 - MIN_SCALE) * (1 - MIN_ALPHA));
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setScaleY(MIN_SCALE);
            view.setAlpha(0.5f);
        }
    }
}
