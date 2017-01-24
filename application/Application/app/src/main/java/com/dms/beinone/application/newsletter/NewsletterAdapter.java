package com.dms.beinone.application.newsletter;

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
 * Created by BeINone on 2017-01-24.
 */

public class NewsletterAdapter extends RecyclerView.Adapter<NewsletterAdapter.ViewHolder> {

    private Context mContext;
    private List<Newsletter> mNewsletterList;

    public NewsletterAdapter(Context context, List<Newsletter> newsletterList) {
        mContext = context;
        mNewsletterList = newsletterList;
    }

    @Override
    public NewsletterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.viewholder_newsletter, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsletterAdapter.ViewHolder holder, int position) {
        Newsletter newsletter = mNewsletterList.get(position);

        holder.bind(newsletter.getTitle(), newsletter.getWriter(), newsletter.getDate());
    }

    @Override
    public int getItemCount() {
        return mNewsletterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTV;
        private TextView mWriterTV;
        private TextView mDateTV;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleTV = (TextView) itemView.findViewById(R.id.tv_newsletter_title);
            mWriterTV = (TextView) itemView.findViewById(R.id.tv_newsletter_writer);
            mDateTV = (TextView) itemView.findViewById(R.id.tv_newsletter_date);

            itemView.setOnTouchListener(Listeners.changeTextColorOnTouchListener(mContext, mTitleTV));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewArticle(mNewsletterList.get(getAdapterPosition()));
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
         * @param newsletter Newsletter object that contains information of article
         */
        private void viewArticle(Newsletter newsletter) {
            Intent intent = new Intent(mContext, NewsletterArticleActivity.class);
            intent.putExtra(mContext.getString(R.string.EXTRA_NEWSLETTER), newsletter);
            mContext.startActivity(intent);
        }

    }

}
