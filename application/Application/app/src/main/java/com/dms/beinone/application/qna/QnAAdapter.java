package com.dms.beinone.application.qna;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.Listeners;
import com.dms.beinone.application.R;

import java.util.List;

/**
 * Created by BeINone on 2017-01-23.
 */

public class QnAAdapter extends RecyclerView.Adapter<QnAAdapter.ViewHolder> {

    private Context mContext;
    private List<QnA> mQnAList;

    public QnAAdapter(Context context, List<QnA> qnaList) {
        mContext = context;
        mQnAList = qnaList;
    }

    @Override
    public QnAAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_qna, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QnAAdapter.ViewHolder holder, int position) {
        QnA qna = mQnAList.get(position);

        holder.bind(qna.getTitle(), qna.getQuestioner(), qna.getQuestionDate(), qna.isPrivacy());
    }

    @Override
    public int getItemCount() {
        return mQnAList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTV;
        private TextView mQuestionerTV;
        private TextView mQuestionDateTV;
        private ImageView mPrivacyIV;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleTV = (TextView) itemView.findViewById(R.id.tv_qna_title);
            mQuestionerTV = (TextView) itemView.findViewById(R.id.tv_qna_questioner);
            mQuestionDateTV = (TextView) itemView.findViewById(R.id.tv_qna_questiondate);
            mPrivacyIV = (ImageView) itemView.findViewById(R.id.iv_privacy);

            itemView.setOnTouchListener(Listeners.changeTextColorOnTouchListener(mContext, mTitleTV));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    QnA qna = mQnAList.get(getAdapterPosition());

                    if (qna.isPrivacy()) {
                        Toast.makeText(mContext, R.string.qna_private, Toast.LENGTH_SHORT).show();
                    } else {
                        viewArticle(qna);
                    }
                }
            });
        }

        public void bind(String title, String questioner, String questionDate, boolean privacy) {
            mTitleTV.setText(title);
            mQuestionerTV.setText(questioner);
            mQuestionDateTV.setText(questionDate);

            if (privacy) {
                mPrivacyIV.setImageResource(R.drawable.ic_lock_white_24dp);
                mPrivacyIV.setColorFilter(ContextCompat.getColor(mContext, R.color.colorAccent));
            } else {
                mPrivacyIV.setImageResource(R.drawable.ic_lock_open_white_24dp);
                mPrivacyIV.setColorFilter(ContextCompat.getColor(mContext, android.R.color.darker_gray));
            }
        }

        /**
         * start a new activity to display article
         * @param qna QnA object that contains information of article
         */
        private void viewArticle(QnA qna) {
            Intent intent = new Intent(mContext, QnAArticleActivity.class);
            intent.putExtra(mContext.getString(R.string.EXTRA_QNA), qna);
            mContext.startActivity(intent);
        }

    }

}
