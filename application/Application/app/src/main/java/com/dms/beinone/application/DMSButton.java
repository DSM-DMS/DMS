package com.dms.beinone.application;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by BeINone on 2017-01-11.
 */

public class DMSButton extends Button {

    private static final int STYLE_NORMAL = 0;
    private static final int STYLE_REVERSE = 1;
    private static final int STYLE_ROUND = 2;
    private static final int STYLE_BOTTOM = 3;

    private Drawable mNormalBackground;
    private Drawable mOnTouchBackground;
    private int mNormalTextColor;
    private int mOnTouchTextColor;
    private int mPrimaryColor;

    public DMSButton(Context context) {
        super(context);
    }

    public DMSButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DMSButton);
        int style = a.getInt(R.styleable.DMSButton_style, STYLE_NORMAL);
        a = context.obtainStyledAttributes(attrs, new int[] { android.R.attr.textSize });
        int textSize = a.getDimensionPixelSize(0, (int) DensityConverter.dpToPx(context, 14));
        init(context, style, textSize);
    }

    public DMSButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a =
                context.obtainStyledAttributes(attrs, R.styleable.DMSButton, defStyleAttr, 0);
        int style = a.getInt(R.styleable.DMSButton_style, STYLE_NORMAL);
        a = context.obtainStyledAttributes(attrs, new int[] { android.R.attr.textSize }, defStyleAttr, 0);
        int textSize = a.getDimensionPixelSize(0, (int) DensityConverter.dpToPx(context, 14));
        init(context, style, textSize);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setBackground(mOnTouchBackground);
                setTextColor(mOnTouchTextColor);
                break;
            case MotionEvent.ACTION_UP:
                setBackground(mNormalBackground);
                setTextColor(mNormalTextColor);
                break;
            default: break;
        }



        return super.onTouchEvent(event);
    }

    /**
     * Initializes settings for button.
     */
    private void init(Context context, int style, int textSize) {
        mPrimaryColor = ContextCompat.getColor(context, R.color.colorPrimary);

        if (style == STYLE_NORMAL) {
            mNormalBackground = ContextCompat.getDrawable(context, R.drawable.dmsbtn);
            mOnTouchBackground = ContextCompat.getDrawable(context, R.drawable.dmsbtn_touch);
            mNormalTextColor = ContextCompat.getColor(context, R.color.colorPrimary);
            mOnTouchTextColor = ContextCompat.getColor(context, android.R.color.white);
        } else if (style == STYLE_REVERSE) {
            mNormalBackground = ContextCompat.getDrawable(context, R.drawable.dmsbtn_reverse);
            mOnTouchBackground =
                    ContextCompat.getDrawable(context, R.drawable.dmsbtn_reverse_touch);
            mNormalTextColor = ContextCompat.getColor(context, android.R.color.white);
            mOnTouchTextColor = ContextCompat.getColor(context, R.color.colorPrimary);
        } else if (style == STYLE_ROUND) {
            mNormalBackground = ContextCompat.getDrawable(context, R.drawable.dmsbtn_round);
            mOnTouchBackground = ContextCompat.getDrawable(context, R.drawable.dmsbtn_round_touch);
            mNormalTextColor = ContextCompat.getColor(context, R.color.colorPrimary);
            mOnTouchTextColor = ContextCompat.getColor(context, android.R.color.white);
        } else if (style == STYLE_BOTTOM) {
            mNormalBackground = ContextCompat.getDrawable(context, R.drawable.dmsbtn_bottom);
            mOnTouchBackground = ContextCompat.getDrawable(context, R.drawable.dmsbtn_bottom_touch);
            mNormalTextColor = ContextCompat.getColor(context, android.R.color.white);
            mOnTouchTextColor = ContextCompat.getColor(context, android.R.color.white);
        }
        setBackground(mNormalBackground);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, DensityConverter.pxTodp(context, textSize));
        setTextColor(mNormalTextColor);
        setMinWidth(0);
        setMinimumWidth(0);
        setMinHeight(0);
        setMinimumHeight(0);
    }

}
