package com.dms.beinone.application.views.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.dms.beinone.application.R;

/**
 * Created by BeINone on 2017-01-20.
 */

public class DMSImageButton extends AppCompatImageButton {

    private static final int STYLE_RECTANGLE = 0;
    private static final int STYLE_OVAL = 1;

    private Drawable mNormalBackground;
    private Drawable mOnTouchBackground;
    private int mNormalImageColor;
    private int mOnTouchImageColor;

    public DMSImageButton(Context context) {
        super(context);
    }

    public DMSImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DMSImageButton);
        int style = a.getInt(R.styleable.DMSImageButton_ib_style, STYLE_RECTANGLE);

        init(context, style);
    }

    public DMSImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DMSImageButton, defStyleAttr, 0);
        int style = a.getInt(R.styleable.DMSImageButton_ib_style, STYLE_RECTANGLE);

        init(context, style);
    }

    /**
     * 속성 초기화, 터치 이벤트 설정
     */
    private void init(final Context context, int style) {
        if (style == STYLE_RECTANGLE) {
            mNormalBackground = ContextCompat.getDrawable(context, R.drawable.dmsib_rectangle);
            mOnTouchBackground = ContextCompat.getDrawable(context, R.drawable.dmsib_rectangle_touch);
            mNormalImageColor = ContextCompat.getColor(context, android.R.color.white);
            mOnTouchImageColor = ContextCompat.getColor(context, R.color.colorPrimary);
        } else if (style == STYLE_OVAL) {
            mNormalBackground = ContextCompat.getDrawable(context, R.drawable.dmsib_oval);
            mOnTouchBackground =
                    ContextCompat.getDrawable(context, R.drawable.dmsib_oval_touch);
            mNormalImageColor = ContextCompat.getColor(context, R.color.colorPrimary);
            mOnTouchImageColor = ContextCompat.getColor(context, android.R.color.white);
        }

        setBackground(mNormalBackground);
        setColorFilter(mNormalImageColor);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        setBackground(mOnTouchBackground);
                        setColorFilter(mOnTouchImageColor);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        Rect rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        if (!rect.contains(v.getLeft() + (int) event.getX(),
                                v.getTop() + (int) event.getY())) {

                            setBackground(mNormalBackground);
                            setColorFilter(mNormalImageColor);
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        setBackground(mNormalBackground);
                        setColorFilter(mNormalImageColor);

                        break;

                    default: break;
                }

                return true;
            }
        });
    }

}
