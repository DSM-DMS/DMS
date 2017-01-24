package com.dms.beinone.application.notice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dms.beinone.application.Listeners;
import com.dms.beinone.application.R;

import java.util.List;

/**
 * Created by BeINone on 2017-01-18.
 */

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    private static final int TYPE_IMPORTANT = 0;
    private static final int TYPE_COMMON = 1;

    private Context mContext;
    private List<Notice> mNoticeList;
    private int mNumImportantItems;

    public NoticeAdapter(Context context, List<Notice> noticeList, int numImportantItems) {
        mContext = context;
        mNoticeList = noticeList;
        mNumImportantItems = numImportantItems;
    }

    @Override
    public NoticeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_IMPORTANT) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.viewholder_notice_important, parent, false);
            return new ViewHolder(view);
        } else if (viewType == TYPE_COMMON) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.viewholder_notice_common, parent, false);
            return new ViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(NoticeAdapter.ViewHolder holder, int position) {
        Notice notice = mNoticeList.get(position);

        holder.bind(notice.getTitle(), notice.getWriter(), notice.getDate());
    }

    @Override
    public int getItemCount() {
        return mNoticeList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mNumImportantItems) return TYPE_IMPORTANT;
        else return TYPE_COMMON;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTV;
        private TextView mWriterTV;
        private TextView mDateTV;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleTV = (TextView) itemView.findViewById(R.id.tv_notice_title);
            mWriterTV = (TextView) itemView.findViewById(R.id.tv_notice_writer);
            mDateTV = (TextView) itemView.findViewById(R.id.tv_notice_date);

            itemView.setOnTouchListener(Listeners.changeTextColorOnTouchListener(mContext, mTitleTV));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewArticle(mNoticeList.get(getAdapterPosition()));
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
         * @param notice Notice object that contains information of article
         */
        private void viewArticle(Notice notice) {
            Intent intent = new Intent(mContext, NoticeArticleActivity.class);
            intent.putExtra(mContext.getString(R.string.EXTRA_NOTICE), notice);
            mContext.startActivity(intent);
        }

    }

}
