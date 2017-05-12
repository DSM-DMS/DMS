package com.dms.beinone.application.dmsview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dms.beinone.application.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by BeINone on 2017-03-10.
 */

public class SlimDatePicker extends LinearLayout {

    // main view
    private View mView;

    private ImageButton mPrevIB;
    private ImageButton mNextIB;
    private TextView mDateTV;

    private Calendar mCalendar;

    private OnDateChangedListener mOnDateChangedListener;

    private int mTextColor;
    private int mButtonColor;
    private int mBackgroundColor;

    public SlimDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a =
                context.getTheme().obtainStyledAttributes(attrs, R.styleable.SlimDatePicker, 0, 0);

        try {
            int defContentColor = ContextCompat.getColor(context, R.color.colorPrimary);
            mTextColor = a.getColor(R.styleable.SlimDatePicker_sdp_textColor, defContentColor);
            mButtonColor = a.getColor(R.styleable.SlimDatePicker_sdp_buttonColor, defContentColor);
            mBackgroundColor = a.getColor(R.styleable.SlimDatePicker_sdp_backgroundColor, Color.WHITE);
        } finally {
            a.recycle();
        }

        init();
    }

    public SlimDatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.view_slimdatepicker, this, true);

        mPrevIB = (ImageButton) mView.findViewById(R.id.ib_slimdatepicker_prev);
        mNextIB = (ImageButton) mView.findViewById(R.id.ib_slimdatepicker_next);
        mDateTV = (TextView) mView.findViewById(R.id.tv_slimdatepicker_date);
        mCalendar = Calendar.getInstance();

        this.setBackgroundColor(mBackgroundColor);
        mDateTV.setTextColor(mTextColor);
        mPrevIB.setColorFilter(mButtonColor);
        mNextIB.setColorFilter(mButtonColor);

        mPrevIB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                prevDay();
            }
        });

        mNextIB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                nextDay();
            }
        });

        // initialize the date text view
        setDateTV(getDateString());
    }

    private void nextDay() {
        mCalendar.add(Calendar.DATE, 1);
        setDateTV(getDateString());
        if (mOnDateChangedListener != null) {
            mOnDateChangedListener.onDateChanged(mCalendar.getTime());
        }
    }

    private void prevDay() {
        mCalendar.add(Calendar.DATE, -1);
        setDateTV(getDateString());
        if (mOnDateChangedListener != null) {
            mOnDateChangedListener.onDateChanged(mCalendar.getTime());
        }
    }

    private void setDateTV(String date) {
        mDateTV.setText(date);
    }

    private String getDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA);
        return sdf.format(mCalendar.getTime());
    }

    public Calendar getDate() {
        return mCalendar;
    }

    public void setOnDateChangedListener(@Nullable OnDateChangedListener l) {
        mOnDateChangedListener = l;
    }

    public interface OnDateChangedListener {
        void onDateChanged(Date date);
    }

}
