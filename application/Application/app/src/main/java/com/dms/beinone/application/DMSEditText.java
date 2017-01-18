package com.dms.beinone.application;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by BeINone on 2017-01-17.
 */

public class DMSEditText extends EditText {

    public DMSEditText(Context context) {
        super(context);
    }

    public DMSEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DMSEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    /**
     * 속성 초기화
     */
    private void init(Context context) {
        setBackground(ContextCompat.getDrawable(context, R.drawable.dmset));

        int padding = (int) DensityConverter.dpToPx(context, 8);
        setPadding(padding, padding, padding, padding);
    }

}
