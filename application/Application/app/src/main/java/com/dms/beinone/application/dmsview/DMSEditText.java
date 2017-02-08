package com.dms.beinone.application.dmsview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.EditText;

import com.dms.beinone.application.DensityConverter;
import com.dms.beinone.application.R;

/**
 * Created by BeINone on 2017-01-17.
 */

public class DMSEditText extends EditText {

    private static final int STYLE_NORMAL = 0;
    private static final int STYLE_LOGIN = 1;

    private Drawable mNormalBackground;
    private Drawable mOnFocusBackground;

    public DMSEditText(Context context) {
        super(context);
    }

    public DMSEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DMSEditText);
        int style = a.getInt(R.styleable.DMSImageButton_ib_style, STYLE_NORMAL);

        init(context, style);
    }

    public DMSEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DMSEditText, defStyleAttr, 0);
        int style = a.getInt(R.styleable.DMSImageButton_ib_style, STYLE_NORMAL);

        init(context, style);
    }

    /**
     * 속성 초기화
     */
    private void init(Context context, int style) {
        if (style == STYLE_NORMAL) {
            mNormalBackground = ContextCompat.getDrawable(context, R.drawable.dmset);
            mOnFocusBackground = ContextCompat.getDrawable(context, R.drawable.dmset_focus);
        } else if (style == STYLE_LOGIN) {
            mNormalBackground = ContextCompat.getDrawable(context, R.drawable.dmset_login);
            mOnFocusBackground = ContextCompat.getDrawable(context, R.drawable.dmset_login_focus);
            setTextColor(Color.WHITE);
        }

        setBackground(mNormalBackground);

        int padding = (int) DensityConverter.dpToPx(context, 8);
        setPadding(padding, padding, padding, padding);
    }

}
