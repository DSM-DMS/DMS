package com.dms.beinone.application;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by BeINone on 2017-01-18.
 */

public class NoticeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_IMPORTANT = 0;
    private static final int TYPE_COMMON = 1;

    private List<Notice> mNoticeList;
    private int mNumImportantItems;

    public NoticeAdapter(List<Notice> noticeList, int numImportantItems) {
        mNoticeList = noticeList;
        mNumImportantItems = numImportantItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
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
        }

    }

}
