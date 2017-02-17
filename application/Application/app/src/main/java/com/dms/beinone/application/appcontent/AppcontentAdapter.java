package com.dms.beinone.application.appcontent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dms.beinone.application.Listeners;
import com.dms.beinone.application.R;

import java.util.List;

/**
 * Created by BeINone on 2017-01-26.
 */

public class AppcontentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    private Context mContext;
    private int mCategory;
    private List<Appcontent> mAppcontentList;

    private RecyclerView mRecyclerView;

    public AppcontentAdapter(Context context, int category, List<Appcontent> appcontentList) {
        mContext = context;
        mCategory = category;
        mAppcontentList = appcontentList;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
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
                    .inflate(R.layout.viewholder_appcontent_footer, parent, false);
            return new FooterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_ITEM) {
            Appcontent appcontent = mAppcontentList.get(position);

            ((ItemViewHolder) holder)
                    .bind(appcontent.getTitle(), appcontent.getWriter(), appcontent.getDate());
        }
    }

    @Override
    public int getItemCount() {
        // + 1 is for bottom button
        return mAppcontentList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mAppcontentList.size()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void addAll(List<Appcontent> appcontentList) {
        mAppcontentList.addAll(appcontentList);
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
                    viewArticle(mAppcontentList.get(getAdapterPosition()));
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
         * @param appcontent Appcontent object that contains information of article
         */
        private void viewArticle(Appcontent appcontent) {
            Intent intent = new Intent(mContext, AppcontentArticleActivity.class);
            intent.putExtra(mContext.getString(R.string.EXTRA_APPCONTENT), appcontent);
            mContext.startActivity(intent);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        private Button mMoreBtn;

        public FooterViewHolder(View itemView) {
            super(itemView);

            mMoreBtn = (Button) itemView.findViewById(R.id.btn_appcontent_more);
            mMoreBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("log", "clicked");
                    new LoadAppcontentListTask(mContext, mCategory, mRecyclerView)
                            .execute(AppcontentFragment.page++);
                }
            });
        }
    }

}
