package com.dms.beinone.application;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewParent;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

/**
 * Created by BeINone on 2017-01-15.
 */

public class DMSRadioButton extends ToggleButton {

    private static final int STYLE_NORMAL = 0;
    private static final int STYLE_REVERSE = 1;

    private int mNormalTextColor;
    private int mSelectedTextColor;

    public DMSRadioButton(Context context) {
        super(context);
    }

    public DMSRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DMSButton);
        int style = a.getInt(R.styleable.DMSButton_style, STYLE_NORMAL);
        a = context.obtainStyledAttributes(attrs, new int[] { android.R.attr.textSize });
        int textSize = a.getDimensionPixelSize(0, (int) DensityConverter.dpToPx(context, 14));

        init(context, style, textSize);
    }

    public DMSRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DMSButton, defStyleAttr, 0);
        int style = a.getInt(R.styleable.DMSButton_style, STYLE_NORMAL);
        a = context.obtainStyledAttributes(attrs, new int[] { android.R.attr.textSize }, defStyleAttr, 0);
        int textSize = a.getDimensionPixelSize(0, (int) DensityConverter.dpToPx(context, 14));

        init(context, style, textSize);
    }

    /**
     * Initializes settings for radio button.
     */
    private void init(Context context, int style, int textSize) {
        if (style == STYLE_NORMAL) {
            setBackground(ContextCompat.getDrawable(context, R.drawable.dmsradio));
            mNormalTextColor = ContextCompat.getColor(context, R.color.colorPrimary);
            mSelectedTextColor = ContextCompat.getColor(context, android.R.color.white);
        } else if (style == STYLE_REVERSE) {
            setBackground(ContextCompat.getDrawable(context, R.drawable.dmsradio));
            mNormalTextColor = ContextCompat.getColor(context, android.R.color.white);
            mSelectedTextColor = ContextCompat.getColor(context, R.color.colorPrimary);
        }

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewParent parent = getParent();
                while (!(parent instanceof DMSRadioGroup)) {
                    parent = parent.getParent();
                }

                // 버튼이 선택되었음을 RadioGroup에 알림
                ((DMSRadioGroup) parent).check(view.getId());
            }
        });

        setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // RadioGroup에 의해 버튼이 선택되면 텍스트 색을 바꿈.
                if (b) {
                    setTextColor(mSelectedTextColor);
                } else {
                    setTextColor(mNormalTextColor);
                }
            }
        });

        setTextSize(TypedValue.COMPLEX_UNIT_SP, DensityConverter.pxTodp(context, textSize));
        setTextColor(mNormalTextColor);
        setMinWidth(0);
        setMinimumWidth(0);
        setMinHeight(0);
        setMinimumHeight(0);
    }

}
