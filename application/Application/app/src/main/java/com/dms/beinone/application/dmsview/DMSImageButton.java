package com.dms.beinone.application.dmsview;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by BeINone on 2017-01-20.
 */

public class DMSImageButton extends ImageButton {

    public DMSImageButton(Context context) {
        super(context);
    }

    public DMSImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public DMSImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    /**
     * 속성 초기화, 터치 이벤트 설정
     */
    private void init(final Context context) {
//        setBackground(ContextCompat.getDrawable(context, R.drawable.dmsib));

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ((ImageButton) v).setColorFilter(
                                ContextCompat.getColor(context, android.R.color.white));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Rect rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            ((ImageButton) v).clearColorFilter();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        ((ImageButton) v).clearColorFilter();
                        break;
                    default: break;
                }

                return true;
            }
        });
//        setMinimumWidth(0);
//        setMinimumHeight(0);
    }

}
