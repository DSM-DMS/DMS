package com.dms.beinone.application.dmsview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.dms.beinone.application.DensityConverter;
import com.dms.beinone.application.R;

/**
 * Created by BeINone on 2017-01-18.
 */

public class DMSBadge extends TextView {

    public DMSBadge(Context context) {
        super(context);
    }

    public DMSBadge(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, new int[] { android.R.attr.textSize });
        int textSize = a.getDimensionPixelSize(0, (int) DensityConverter.dpToPx(context, 12));

        init(context, textSize);
    }

    public DMSBadge(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(
                attrs, new int[] { android.R.attr.textSize }, defStyleAttr, 0);
        int textSize = a.getDimensionPixelSize(0, (int) DensityConverter.dpToPx(context, 12));

        init(context, textSize);
    }

    private void init(Context context, int textSize) {
        setBackground(ContextCompat.getDrawable(context, R.drawable.dmsbadge));
        setTextSize(TypedValue.COMPLEX_UNIT_SP, DensityConverter.pxTodp(context, textSize));
    }

}
