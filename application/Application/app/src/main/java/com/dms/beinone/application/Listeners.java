package com.dms.beinone.application;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by BeINone on 2017-01-23.
 */

public class Listeners {

    /**
     * returns OnTouchListener that change color of TextView on touch View.
     * @param context
     * @param textView TextView to change text color
     * @return
     */
    public static View.OnTouchListener changeTextColorOnTouchListener(
            final Context context, final TextView textView) {

        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        textView.setTextColor(
                                ContextCompat.getColor(context, R.color.colorPrimary));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Rect rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        if (!rect.contains(
                                v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            textView.setTextColor(Color.BLACK);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        textView.setTextColor(Color.BLACK);
                        break;
                    default: break;
                }

                return false;
            }
        };
    }

}
