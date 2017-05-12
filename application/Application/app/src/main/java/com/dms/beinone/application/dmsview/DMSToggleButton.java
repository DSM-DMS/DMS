package com.dms.beinone.application.dmsview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.dms.beinone.application.R;
import com.dms.beinone.application.utils.DensityConverter;

/**
 * Created by BeINone on 2017-01-16.
 */

public class DMSToggleButton extends ToggleButton {

    private int mNormalTextColor;
    private int mCheckedTextColor;

    public DMSToggleButton(Context context) {
        super(context);
    }

    public DMSToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, new int[] { android.R.attr.textSize });
        int textSize = a.getDimensionPixelSize(0, (int) DensityConverter.dpToPx(context, 14));

        init(context, textSize);
    }

    public DMSToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(
                attrs, new int[] { android.R.attr.textSize }, defStyleAttr, 0);
        int textSize = a.getDimensionPixelSize(0, (int) DensityConverter.dpToPx(context, 14));

        init(context, textSize);
    }

    /**
     * 속성 초기화, 클릭 이벤트 설정
     */
    private void init(Context context, int textSize) {
        setBackground(ContextCompat.getDrawable(context, R.drawable.dmstb));
        mNormalTextColor = ContextCompat.getColor(context, R.color.colorPrimary);
        mCheckedTextColor = ContextCompat.getColor(context, android.R.color.white);

        setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked()) {
                    setTextColor(mCheckedTextColor);
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
