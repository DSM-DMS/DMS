package com.dms.beinone.application.views.custom;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by BeINone on 2017-07-13.
 */

public class SelectableButton extends AppCompatButton implements View.OnClickListener {

    private OnSelectedListener mOnSelectionChangedListener;

    public SelectableButton(Context context) {
        super(context);
        setOnClickListener(this);
    }

    public SelectableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    public SelectableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (mOnSelectionChangedListener != null && selected) {
            mOnSelectionChangedListener.onSelected(this);
        }
    }

    @Override
    public void onClick(View v) {
        setSelected(!this.isSelected());
    }

    public void setOnSelectedListener(OnSelectedListener listener) {
        mOnSelectionChangedListener = listener;
    }

    public void removeOnSelectedListener() {
        mOnSelectionChangedListener = null;
    }

    public interface OnSelectedListener {
        void onSelected(View v);
    }
}
