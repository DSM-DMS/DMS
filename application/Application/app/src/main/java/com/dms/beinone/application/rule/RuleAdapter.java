package com.dms.beinone.application.rule;

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
import com.dms.beinone.application.R;

import java.util.List;

/**
 * Created by BeINone on 2017-01-20.
 */

public class RuleAdapter extends ExpandableRecyclerAdapter<
        RuleTitle, RuleContent, RuleAdapter.RuleTitleVH, RuleAdapter.RuleContentVH> {

    private Context mContext;

    public RuleAdapter(Context context, @NonNull List<RuleTitle> parentList) {
        super(parentList);
        mContext = context;
    }

    @NonNull
    @Override
    public RuleTitleVH onCreateParentViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.viewholder_rule_title, parent, false);
        return new RuleTitleVH(view);
    }

    @NonNull
    @Override
    public RuleContentVH onCreateChildViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.viewholder_rule_content, parent, false);
        return new RuleContentVH(view);
    }

    @Override
    public void onBindParentViewHolder(@NonNull RuleTitleVH parentViewHolder, int parentPosition, @NonNull RuleTitle parent) {
        parentViewHolder.bind(parent.getTitle());
    }

    @Override
    public void onBindChildViewHolder(@NonNull RuleContentVH childViewHolder, int parentPosition, int childPosition, @NonNull RuleContent child) {
        childViewHolder.bind(child.getContent());
    }

    /**
     * ViewHolder for Rule Title
     */
    public class RuleTitleVH extends ParentViewHolder<RuleTitle, RuleContent> {

        private TextView mTitleTV;
        private ImageButton mExpandBtn;

        public RuleTitleVH(View itemView) {
            super(itemView);

            mTitleTV = (TextView) itemView.findViewById(R.id.tv_rule_title);
            mExpandBtn = (ImageButton) itemView.findViewById(R.id.btn_rule_expand);

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

                    return true;
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
     * ViewHolder for Rule Content
     */
    public class RuleContentVH extends ChildViewHolder<RuleContent> {

        private TextView mContentTV;

        public RuleContentVH(View itemView) {
            super(itemView);
            mContentTV = (TextView) itemView.findViewById(R.id.tv_rule_content);
        }

        public void bind(String content) {
            mContentTV.setText(content);
        }

    }

}
