package com.dms.beinone.application.views.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

/**
 * Created by BeINone on 2017-05-29.
 */

public class ExpandableLayout extends LinearLayout {

    public static final long ANIMATION_DURATION = 200L;

    private View mSelectedParentView;
    private View mSelectedChildView;

    public ExpandableLayout(Context context) {
        super(context);
    }

    public ExpandableLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void addView(final View parentView, final View childView) {
        addView(parentView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        parentView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                if (parentView.isSelected()) {
                    collapse(childView);
                    parentView.setSelected(false);
                    mSelectedParentView = null;
                    mSelectedChildView = null;
                } else {
                    expand(childView);
                    parentView.setSelected(true);
                    if (mSelectedParentView != null) {
                        collapse(mSelectedChildView);
                        mSelectedParentView.setSelected(false);
                    }
                    mSelectedParentView = parentView;
                    mSelectedChildView = childView;
                }
            }
        });
        childView.setVisibility(View.GONE);
        addView(childView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void addView(final View parentView,final View childView,boolean check){
        addView(parentView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        parentView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parentView.isSelected()) {
                    collapse(childView);
                    parentView.setSelected(false);
                    mSelectedParentView = null;
                    mSelectedChildView = null;
                } else {
                    expand(childView);
                    parentView.setSelected(true);
                    if (mSelectedParentView != null) {
                        collapse(mSelectedChildView);
                        mSelectedParentView.setSelected(false);
                    }
                    mSelectedParentView = parentView;
                    mSelectedChildView = childView;
                }
            }
        });
        childView.setVisibility(View.GONE);
        addView(childView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        expand(childView);
        parentView.setSelected(true);
        if (mSelectedParentView != null) {
            collapse(mSelectedChildView);
            mSelectedParentView.setSelected(false);
        }
        mSelectedParentView = parentView;
        mSelectedChildView = childView;
    }

    private void expand(final View v) {
        v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration(ANIMATION_DURATION);
        v.startAnimation(a);
    }

    private void collapse(final View v) {
        v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else{
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration(ANIMATION_DURATION);
        v.startAnimation(a);
    }
}
