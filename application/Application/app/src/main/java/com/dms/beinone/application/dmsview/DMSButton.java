package com.dms.beinone.application.dmsview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.Button;

import com.dms.beinone.application.DensityConverter;
import com.dms.beinone.application.R;

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

    private boolean hasImage;

    public DMSButton(Context context) {
        super(context);
    }

    public DMSButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DMSButton);

        // get style
        int style = a.getInt(R.styleable.DMSButton_style, STYLE_NORMAL);

        a = context.obtainStyledAttributes(attrs,
                new int[] { android.R.attr.textSize, android.R.attr.drawableRight });

        // get text size
        int textSize = a.getDimensionPixelSize(0, (int) DensityConverter.dpToPx(context, 14));

        // get drawable on right
        Drawable drawableRight = a.getDrawable(1);
        if (drawableRight != null) hasImage = true;

        init(context, style, textSize);
    }

    public DMSButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a =
                context.obtainStyledAttributes(attrs, R.styleable.DMSButton, defStyleAttr, 0);
        int style = a.getInt(R.styleable.DMSButton_style, STYLE_NORMAL);

        a = context.obtainStyledAttributes(attrs,
                new int[] { android.R.attr.textSize, android.R.attr.drawableRight }, defStyleAttr, 0);
        int textSize = a.getDimensionPixelSize(0, (int) DensityConverter.dpToPx(context, 14));
        Drawable drawableRight = a.getDrawable(1);
        if (drawableRight != null) hasImage = true;

        init(context, style, textSize);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setBackground(mOnTouchBackground);
                setTextColor(mOnTouchTextColor);

                if (hasImage) {
                    // get drawable having drawableRight attribute (drawableRight is in index 2)
                    // and clear color filter
                    getCompoundDrawables()[2].clearColorFilter();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Rect rect = new Rect(getLeft(), getTop(), getRight(), getBottom());

                if (!rect.contains(getLeft() + (int) event.getX(), getTop() + (int) event.getY())) {
                    setBackground(mNormalBackground);
                    setTextColor(mNormalTextColor);

                    if (hasImage) {
                        getCompoundDrawables()[2].setColorFilter(
                                mNormalTextColor, PorterDuff.Mode.MULTIPLY);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                setBackground(mNormalBackground);
                setTextColor(mNormalTextColor);

                if (hasImage) {
                    getCompoundDrawables()[2].setColorFilter(
                            mNormalTextColor, PorterDuff.Mode.MULTIPLY);
                }
                break;
            default: break;
        }

        return true;
    }

    /**
     * 속성 초기화
     */
    private void init(Context context, int style, int textSize) {
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

        if (hasImage) {
            getCompoundDrawables()[2].setColorFilter(mNormalTextColor, PorterDuff.Mode.MULTIPLY);
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
