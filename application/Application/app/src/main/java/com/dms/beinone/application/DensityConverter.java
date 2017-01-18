package com.dms.beinone.application;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by BeINone on 2017-01-14.
 */

public class DensityConverter {

    /**
     * pixel 단위의 값을 dp 단위의 값으로 변환
     * @param context Context
     * @param px dp 단위로 변환할 pixel 값
     * @return 변환된 dp 단위의 값
     */
    public static float pxTodp(Context context, float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px / scaledDensity;
    }

    /**
     * dp 단위의 값을 pixel 단위의 값으로 변환
     * @param context Context
     * @param dp pixel 단위로 변환할 dp 값
     * @return 변환된 pixel 단위의 값
     */
    public static float dpToPx(Context context, float dp) {
        Resources r = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

}
