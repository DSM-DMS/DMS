package com.dms.beinone.application;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by BeINone on 2017-01-14.
 */

public class DensityConverter {

    public static float pxTodp(Context context, float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px / scaledDensity;
    }

    public static float dpToPx(Context context, float dp) {
        Resources r = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

}
