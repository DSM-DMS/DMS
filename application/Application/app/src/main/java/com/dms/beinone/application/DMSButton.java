package com.dms.beinone.application;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
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

    private Drawable normalBackground;
    private Drawable onTouchBackground;
    private int primaryColor;

    public DMSButton(Context context) {
        super(context);
    }

    public DMSButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.DMSButton);
        int style = attributes.getInt(R.styleable.DMSButton_style, STYLE_NORMAL);
        init(context, style);
    }

    public DMSButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray attributes =
                context.obtainStyledAttributes(attrs, R.styleable.DMSButton, defStyleAttr, 0);
        int style = attributes.getInt(R.styleable.DMSButton_style, STYLE_NORMAL);
        init(context, style);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setBackground(onTouchBackground);
                if (getCurrentTextColor() == primaryColor) {
                    setTextColor(Color.WHITE);
                } else {
                    setTextColor(primaryColor);
                }
                break;
            case MotionEvent.ACTION_UP:
                setBackground(normalBackground);
                if (getCurrentTextColor() == primaryColor) {
                    setTextColor(Color.WHITE);
                } else {
                    setTextColor(primaryColor);
                }
                break;
            default: break;
        }

        return super.onTouchEvent(event);
    }

    /**
     * Initializes settings for button.
     */
    private void init(Context context, int style) {
        primaryColor = ContextCompat.getColor(context, R.color.colorPrimary);

        if (style == STYLE_NORMAL) {
            normalBackground = ContextCompat.getDrawable(context, R.drawable.dms_btn);
            onTouchBackground = ContextCompat.getDrawable(context, R.drawable.dms_btn_touch);
            setTextColor(primaryColor);
        } else {
            normalBackground = ContextCompat.getDrawable(context, R.drawable.dms_btn_reverse);
            onTouchBackground =
                    ContextCompat.getDrawable(context, R.drawable.dms_btn_reverse_touch);
            setTextColor(Color.WHITE);
        }
        setBackground(normalBackground);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.0f);
    }

}
