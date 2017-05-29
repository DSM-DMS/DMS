package com.dms.beinone.application.views.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.dms.beinone.application.R;
import com.dms.beinone.application.models.FAQContent;
import com.dms.beinone.application.models.FAQ;

import java.util.List;

/**
 * Created by BeINone on 2017-01-19.
 */

public class FAQAdapter extends ExpandableRecyclerAdapter<
        FAQ, FAQContent,FAQAdapter.FAQVH, FAQAdapter.FAQContentVH> {

    private Context mContext;

    public FAQAdapter(Context context, @NonNull List<FAQ> parentItemList) {
        super(parentItemList);
        mContext = context;
    }

    @NonNull
    @Override
    public FAQVH onCreateParentViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.viewholder_faq_title, parent, false);
        return new FAQVH(view);
    }

    @NonNull
    @Override
    public FAQContentVH onCreateChildViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.viewholder_faq_content, parent, false);
        return new FAQContentVH(view);
    }

    @Override
    public void onBindParentViewHolder(@NonNull FAQVH parentViewHolder, int parentPosition, @NonNull FAQ parent) {
        parentViewHolder.mTitleTV.setText(parent.getTitle());
    }

    @Override
    public void onBindChildViewHolder(@NonNull FAQContentVH childViewHolder, int parentPosition, int childPosition, @NonNull FAQContent child) {
        childViewHolder.mContentWV.loadData(child.getContent(), "text/html; charset=UTF-8", null);
    }

    /**
     * ViewHolder for FAQ Title
     */
    public class FAQVH extends ParentViewHolder<FAQ, FAQContent> {

        private TextView mTitleTV;
        private ImageButton mExpandBtn;

        public FAQVH(@NonNull View itemView) {
            super(itemView);

            mTitleTV = (TextView) itemView.findViewById(R.id.tv_faq_title);
            mExpandBtn = (ImageButton) itemView.findViewById(R.id.btn_faq_expand);

            // changes color of expand button on touch row
            View overlayView = itemView.findViewById(R.id.layout_faq_overlay);
            overlayView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            mExpandBtn.setBackground(
                                    ContextCompat.getDrawable(mContext, R.drawable.dmsib_oval_touch));
                            mExpandBtn.setColorFilter(
                                    ContextCompat.getColor(mContext, android.R.color.white));
                            break;

                        case MotionEvent.ACTION_MOVE:
                            Rect rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                            if (!rect.contains(v.getLeft() + (int) event.getX(),
                                    v.getTop() + (int) event.getY())) {

                                mExpandBtn.setBackground(
                                        ContextCompat.getDrawable(mContext, R.drawable.dmsib_oval));
                                mExpandBtn.setColorFilter(
                                        ContextCompat.getColor(mContext, R.color.colorPrimary));
                            }
                            break;

                        case MotionEvent.ACTION_UP:
                            mExpandBtn.setBackground(
                                    ContextCompat.getDrawable(mContext, R.drawable.dmsib_oval));
                            mExpandBtn.setColorFilter(
                                    ContextCompat.getColor(mContext, R.color.colorPrimary));
                            break;

                        default: break;
                    }

                    return false;
                }
            });

            overlayView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isExpanded()) {
                        collapseView();
                    } else {
                        expandView();
                    }
                }
            });
        }

        @Override
        public void setExpanded(boolean expanded) {
            super.setExpanded(expanded);

            if (expanded) {
                // changes image of button to less when view is expended
                mExpandBtn.setImageResource(R.drawable.ic_expand_less_white_18dp);
                mTitleTV.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
            } else {
                // changes image of button to more when view is collapsed
                mExpandBtn.setImageResource(R.drawable.ic_expand_more_white_18dp);
                mTitleTV.setTextColor(Color.BLACK);
            }
        }
    }

    /**
     * ViewHolder for FAQ Content
     */
    public class FAQContentVH extends ChildViewHolder<FAQContent> {

        private WebView mContentWV;

        public FAQContentVH(@NonNull final View itemView) {
            super(itemView);
            mContentWV = (WebView) itemView.findViewById(R.id.wv_faq_content);
            mContentWV.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    itemView.getLayoutParams().height = view.getLayoutParams().height;
                    itemView.requestLayout();
                }
            });
            mContentWV.setBackgroundColor(Color.TRANSPARENT);
        }
    }

}
