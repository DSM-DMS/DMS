package com.dms.beinone.application.views.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.dms.beinone.application.R;
import com.dms.beinone.application.utils.DensityConverter;

import static android.R.attr.drawableRight;
import static android.R.attr.paddingBottom;
import static android.R.attr.paddingLeft;
import static android.R.attr.paddingRight;
import static android.R.attr.paddingTop;
import static android.R.attr.textSize;

/**
 * Created by BeINone on 2017-01-11.
 */

public class DMSButton extends AppCompatButton {

    private static final int STYLE_RECTANGLE = 0;
    private static final int STYLE_ROUND = 1;

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
        int style = a.getInt(R.styleable.DMSButton_dmsbtn_style, STYLE_RECTANGLE);

        int defBackgroundColor = ContextCompat.getColor(context, android.R.color.white);
        int defStrokeColor = ContextCompat.getColor(context, R.color.colorPrimary);

        // get background color and stroke color
        int backgroundColor =
                a.getColor(R.styleable.DMSButton_dmsbtn_backgroundColor, defBackgroundColor);
        int strokeColor =
                a.getColor(R.styleable.DMSButton_dmsbtn_strokeColor, defStrokeColor);
        int touchBackgroundColor =
                a.getColor(R.styleable.DMSButton_dmsbtn_touchBackgroundColor, defStrokeColor);
        int touchStrokeColor =
                a.getColor(R.styleable.DMSButton_dmsbtn_touchStrokeColor, defStrokeColor);

        // get text color
        int textColor = a.getColor(R.styleable.DMSButton_dmsbtn_textColor, strokeColor);
        int touchTextColor = a.getColor(R.styleable.DMSButton_dmsbtn_touchTextColor, backgroundColor);

        a = context.obtainStyledAttributes(attrs, new int[]{textSize});

        // get text size
        int textSize = a.getDimensionPixelSize(0, (int) DensityConverter.dpToPx(context, 14));

        a = context.obtainStyledAttributes(attrs, new int[]{drawableRight});

        // get drawable on right
        Drawable drawableRight = a.getDrawable(0);
        if (drawableRight != null) hasImage = true;

        int defPaddingHorizontal = 0;
        int defPaddingVertical = 0;
        if (style == STYLE_RECTANGLE) {
            defPaddingHorizontal = (int) DensityConverter.dpToPx(context, 16);
            defPaddingVertical = (int) DensityConverter.dpToPx(context, 10);
        } else {
            defPaddingHorizontal = (int) DensityConverter.dpToPx(context, 8);
            defPaddingVertical = (int) DensityConverter.dpToPx(context, 4);
        }

        // get padding
        a = context.obtainStyledAttributes(attrs, new int[]{paddingLeft, paddingTop, paddingRight, paddingBottom});
        int paddingLeft = a.getDimensionPixelOffset(0, defPaddingHorizontal);
        int paddingTop = a.getDimensionPixelOffset(1, defPaddingVertical);
        int paddingRight = a.getDimensionPixelOffset(3, defPaddingHorizontal);
        int paddingBottom = a.getDimensionPixelOffset(2, defPaddingVertical);

        init(context, style, backgroundColor, strokeColor, touchBackgroundColor,
                touchStrokeColor, textColor, touchTextColor, textSize,
                new int[]{paddingLeft, paddingTop, paddingBottom, paddingRight});
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return mGestureDetector.onTouchEvent(event);
//    }

    private void init(Context context, int style, int backgroundColor, int strokeColor,
                      int touchBackgroundColor, int touchStrokeColor, int textColor,
                      int touchTextColor, int textSize, int[] padding) {

        if (style == STYLE_RECTANGLE) {
            mNormalBackground = ContextCompat.getDrawable(context, R.drawable.dmsbtn);
            mOnTouchBackground = ContextCompat.getDrawable(context, R.drawable.dmsbtn);
        } else if (style == STYLE_ROUND) {
            mNormalBackground = ContextCompat.getDrawable(context, R.drawable.dmsbtn_round);
            mOnTouchBackground = ContextCompat.getDrawable(context, R.drawable.dmsbtn_round);
        }

        // background color and stroke color
        ((GradientDrawable) mNormalBackground).setColor(backgroundColor);
        ((GradientDrawable) mNormalBackground).setStroke(
                (int) DensityConverter.dpToPx(context, 1), strokeColor);

        // background color and stroke color on button touch
        ((GradientDrawable) mOnTouchBackground).setColor(touchBackgroundColor);
        ((GradientDrawable) mOnTouchBackground).setStroke(
                (int) DensityConverter.dpToPx(context, 1), touchStrokeColor);

        // text color
        mNormalTextColor = textColor;

        // text color on button touch
        mOnTouchTextColor = touchTextColor;

        setBackground(mNormalBackground);
        setTextColor(mNormalTextColor);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, DensityConverter.pxTodp(context, textSize));
        if (hasImage) {
            // sets color of image if exists
            getCompoundDrawables()[2].setColorFilter(mNormalTextColor, PorterDuff.Mode.MULTIPLY);
        }

        setPadding(padding[0], padding[1], padding[2], padding[3]);

        setMinWidth(0);
        setMinimumWidth(0);
        setMinHeight(0);
        setMinimumHeight(0);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
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

                        if (!rect.contains(getLeft() + (int) event.getX(),
                                getTop() + (int) event.getY())) {

                            setBackground(mNormalBackground);
                            setTextColor(mNormalTextColor);

                            if (hasImage) {
                                getCompoundDrawables()[2].setColorFilter(
                                        mNormalTextColor, PorterDuff.Mode.MULTIPLY);
                            }
                        }

                        v.invalidate();
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        setBackground(mNormalBackground);
                        setTextColor(mNormalTextColor);

                        if (hasImage) {
                            getCompoundDrawables()[2].setColorFilter(
                                    mNormalTextColor, PorterDuff.Mode.MULTIPLY);
                        }

                        break;

                    default:
                        break;
                }

                return false;
            }
        });
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (!enabled) {
            setBackgroundColor(Color.GRAY);
        }
    }
}
