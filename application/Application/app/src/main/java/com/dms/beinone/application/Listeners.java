package com.dms.beinone.application;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by BeINone on 2017-01-19.
 */

public class Listeners {

    /**
     * Returns OnTouchListener that changed color when touch expand button.
     * @param context
     * @return OnTouchListener
     */
    public static View.OnTouchListener getExpandBtnOnTouchListener(final Context context) {
        View.OnTouchListener expandBtnOnTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ((ImageButton) v).setColorFilter(
                                ContextCompat.getColor(context, R.color.colorPrimary));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Rect rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            ((ImageButton) v).setColorFilter(Color.BLACK);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        ((ImageButton) v).setColorFilter(Color.BLACK);
                        break;
                    default: break;
                }

                return false;
            }
        };

        return expandBtnOnTouchListener;
    }

}