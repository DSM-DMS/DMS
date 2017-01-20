package com.dms.beinone.application;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

import java.util.List;

/**
 * Created by BeINone on 2017-01-19.
 */

public class FAQAdapter extends ExpandableRecyclerAdapter<
        FAQTitle, FAQContent,FAQAdapter.FAQTitleVH, FAQAdapter.FAQContentVH> {

    private Context mContext;

    public FAQAdapter(Context context, @NonNull List<FAQTitle> parentItemList) {
        super(parentItemList);
        mContext = context;
    }

    @NonNull
    @Override
    public FAQTitleVH onCreateParentViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.viewholder_faq_title, parent, false);
        return new FAQTitleVH(view);
    }

    @NonNull
    @Override
    public FAQContentVH onCreateChildViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.viewholder_faq_content, parent, false);
        return new FAQContentVH(view);
    }

    @Override
    public void onBindParentViewHolder(@NonNull FAQTitleVH parentViewHolder, int parentPosition, @NonNull FAQTitle parent) {
        parentViewHolder.bind(parent.getTitle());
    }

    @Override
    public void onBindChildViewHolder(@NonNull FAQContentVH childViewHolder, int parentPosition, int childPosition, @NonNull FAQContent child) {
        childViewHolder.bind(child.getContent());
    }

    /**
     * View Holder for FAQ Title
     */
    public class FAQTitleVH extends ParentViewHolder {

        private TextView mTitleTV;
        private ImageButton mExpandBtn;

        public FAQTitleVH(@NonNull View itemView) {
            super(itemView);

            mTitleTV = (TextView) itemView.findViewById(R.id.tv_faq_title);
            mExpandBtn = (ImageButton) itemView.findViewById(R.id.btn_faq_expand);

            // changes color of expand button on touch row
            itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            mExpandBtn.setColorFilter(
                                    ContextCompat.getColor(mContext, R.color.colorPrimary));
                            break;
                        case MotionEvent.ACTION_MOVE:
                            Rect rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                            if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                                mExpandBtn.setColorFilter(Color.BLACK);
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                            mExpandBtn.setColorFilter(Color.BLACK);
                            break;
                        default: break;
                    }

                    return false;
                }
            });
        }

        @Override
        public void setExpanded(boolean expanded) {
            super.setExpanded(expanded);

            if (expanded) {
                mExpandBtn.setImageResource(R.drawable.ic_expand_less_black_24dp);
                mTitleTV.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
            } else {
                mExpandBtn.setImageResource(R.drawable.ic_expand_more_black_24dp);
                mTitleTV.setTextColor(Color.BLACK);
            }
        }

        public void bind(String title) {
            mTitleTV.setText(title);
        }

    }

    /**
     * View Holder for FAQ Content
     */
    public class FAQContentVH extends ChildViewHolder {

        private TextView mContentTV;

        public FAQContentVH(@NonNull View itemView) {
            super(itemView);
            mContentTV = (TextView) itemView.findViewById(R.id.tv_faq_content);
        }

        public void bind(String content) {
            mContentTV.setText(content);
        }

    }

}
