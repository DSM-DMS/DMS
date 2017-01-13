package com.dms.beinone.application;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by BeINone on 2017-01-11.
 */

public class DMSButton extends Button {

    private Context mContext;

    public DMSButton(Context context) {
        super(context);
        mContext = context;
    }

    public DMSButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public DMSButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Drawable onTouchBackground =
                ContextCompat.getDrawable(mContext, R.drawable.dms_button_touch);
        Drawable defaultBackground =
                ContextCompat.getDrawable(mContext, R.drawable.dms_button);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setBackground(onTouchBackground);
                break;
            case MotionEvent.ACTION_UP:
                setBackground(defaultBackground);
                break;
            default: break;
        }

        return super.onTouchEvent(event);
    }

}
