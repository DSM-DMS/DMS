package com.dms.beinone.application.appcontent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dms.beinone.application.Listeners;
import com.dms.beinone.application.OnMoreBtnClickListener;
import com.dms.beinone.application.R;

import java.util.List;

import static com.dms.beinone.application.utils.RecyclerViewUtils.TYPE_FOOTER;
import static com.dms.beinone.application.utils.RecyclerViewUtils.TYPE_ITEM;

/**
 * Created by BeINone on 2017-01-26.
 */

public class AppcontentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private int mCategory;
    private List<Appcontent> mAppcontents;
    private OnMoreBtnClickListener mOnMoreBtnClickListener;

    public AppcontentAdapter(Context context, int category, List<Appcontent> appcontents, OnMoreBtnClickListener onMoreBtnClickListener) {
        mContext = context;
        mCategory = category;
        mAppcontents = appcontents;
        mOnMoreBtnClickListener = onMoreBtnClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            // item view
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.viewholder_appcontent_item, parent, false);
            return new ItemViewHolder(view);
        } else {
            // footer view
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.viewholder_footer_more, parent, false);
            return new FooterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_ITEM) {
            Appcontent appcontent = mAppcontents.get(position);
            ((ItemViewHolder) holder).bind(appcontent.getTitle(), appcontent.getWriter(), appcontent.getDate());
        }
    }

    @Override
    public int getItemCount() {
        // + 1 is for bottom button
        return mAppcontents.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mAppcontents.size()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void addAll(List<Appcontent> appcontents) {
        mAppcontents.addAll(appcontents);
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTV;
        private TextView mWriterTV;
        private TextView mDateTV;

        public ItemViewHolder(View itemView) {
            super(itemView);

            mTitleTV = (TextView) itemView.findViewById(R.id.tv_appcontent_title);
            mWriterTV = (TextView) itemView.findViewById(R.id.tv_appcontent_writer);
            mDateTV = (TextView) itemView.findViewById(R.id.tv_appcontent_date);

            itemView.setOnTouchListener(Listeners.changeTextColorOnTouchListener(mContext, mTitleTV));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewArticle(mAppcontents.get(getAdapterPosition()));
                }
            });
        }

        public void bind(String title, String writer, String date) {
            mTitleTV.setText(title);
            mWriterTV.setText(writer);
            mDateTV.setText(date);
        }

        /**
         * start a new activity to display article
         *
         * @param appcontent Appcontent object that contains information of article
         */
        private void viewArticle(Appcontent appcontent) {
            Intent intent = new Intent(mContext, AppcontentArticleActivity.class);
            intent.putExtra(mContext.getString(R.string.EXTRA_APPCONTENT), appcontent);
            mContext.startActivity(intent);
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {

        private Button mMoreBtn;

        public FooterViewHolder(View itemView) {
            super(itemView);

            mMoreBtn = (Button) itemView.findViewById(R.id.btn_footer_more);
            mMoreBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnMoreBtnClickListener.onMoreBtnClick();
                }
            });
        }
    }
}
