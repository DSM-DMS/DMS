package com.dms.beinone.application.dmsview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.EditText;

import com.dms.beinone.application.utils.DensityConverter;
import com.dms.beinone.application.R;

import static android.R.attr.textSize;

/**
 * Created by BeINone on 2017-01-17.
 */

public class DMSEditText extends EditText {

    private static final int STYLE_NORMAL = 0;

    private Drawable mNormalBackground;
    private Drawable mOnFocusBackground;
    private int mTextColor;

    public DMSEditText(Context context) {
        super(context);
    }

    public DMSEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DMSEditText);
        int style = a.getInt(R.styleable.DMSEditText_dmset_style, STYLE_NORMAL);

        int defBackgroundColor = ContextCompat.getColor(context, android.R.color.white);
        int defStrokeColor = ContextCompat.getColor(context, R.color.colorPrimary);
        int defTextColor = ContextCompat.getColor(context, android.R.color.black);

        // get background color and stroke color
        int backgroundColor =
                a.getColor(R.styleable.DMSEditText_dmset_backgroundColor, defBackgroundColor);
        int strokeColor =
                a.getColor(R.styleable.DMSEditText_dmset_strokeColor, defStrokeColor);
        int focusStrokeColor =
                a.getColor(R.styleable.DMSEditText_dmset_focusStrokeColor, defStrokeColor);

        // get text color
        int textColor = a.getColor(R.styleable.DMSEditText_dmset_textColor, defTextColor);

        a = context.obtainStyledAttributes(attrs, new int[] { textSize, android.R.attr.drawableRight });

        // get text size
        int textSize = a.getDimensionPixelSize(0, (int) DensityConverter.dpToPx(context, 14));

        init(context, style, backgroundColor, strokeColor, focusStrokeColor, textColor, textSize);
    }

    /**
     * 속성 초기화
     */
    private void init(Context context, int style, int backgroundColor, int strokeColor,
                      int focusStrokeColor, int textColor, int textSize) {

        if (style == STYLE_NORMAL) {
            mNormalBackground = ContextCompat.getDrawable(context, R.drawable.dmset);
            mOnFocusBackground = ContextCompat.getDrawable(context, R.drawable.dmset);
        }

        int padding = (int) DensityConverter.dpToPx(context, 8);
        setPadding(padding, padding, padding, padding);

        // background color and stroke color
        ((GradientDrawable) mNormalBackground).setColor(backgroundColor);
        ((GradientDrawable) mNormalBackground).setStroke(
                (int) DensityConverter.dpToPx(context, 1), strokeColor);

        // background color and stroke color on button touch
        ((GradientDrawable) mOnFocusBackground).setColor(backgroundColor);
        ((GradientDrawable) mOnFocusBackground).setStroke(
                (int) DensityConverter.dpToPx(context, 1), focusStrokeColor);

        // text color
        mTextColor = textColor;

        setBackground(mNormalBackground);
        setTextColor(mTextColor);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, DensityConverter.pxTodp(context, textSize));
    }

}
