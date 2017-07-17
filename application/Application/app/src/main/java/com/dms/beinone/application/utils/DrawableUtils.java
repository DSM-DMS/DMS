package com.dms.beinone.application.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by BeINone on 2017-07-15.
 */

public class DrawableUtils {

    public static Drawable resizeWithPx(Context context, Drawable drawable, int px) {
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, px, px, false);
        return new BitmapDrawable(context.getResources(), resizedBitmap);
    }

    public static Drawable resizeWithDp(Context context, Drawable drawable, int dp) {
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        int size = (int) DensityConverter.dpToPx(context, dp);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, size, size, false);
        return new BitmapDrawable(context.getResources(), resizedBitmap);
    }
}
