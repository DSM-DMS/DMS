package com.dms.beinone.application.qna;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.Listeners;
import com.dms.beinone.application.OnMoreBtnClickListener;
import com.dms.beinone.application.R;

import java.util.List;

import static com.dms.beinone.application.utils.RecyclerViewUtils.TYPE_FOOTER;
import static com.dms.beinone.application.utils.RecyclerViewUtils.TYPE_ITEM;

/**
 * Created by BeINone on 2017-01-23.
 */

public class QnAAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<QnA> mQnAList;
    private OnMoreBtnClickListener mOnMoreBtnClickListener;

    public QnAAdapter(Context context, List<QnA> qnaList, OnMoreBtnClickListener onMoreBtnClickListener) {
        mContext = context;
        mQnAList = qnaList;
        mOnMoreBtnClickListener = onMoreBtnClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.viewholder_qna, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.viewholder_footer_more, parent, false);
            return new FooterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_ITEM) {
            QnA qna = mQnAList.get(position);
            ((ItemViewHolder) holder).bind(qna.getTitle(), qna.getWriter(), qna.getQuestionDate(), qna.isPrivacy());
        }
    }

    @Override
    public int getItemCount() {
        return mQnAList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mQnAList.size()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTV;
        private TextView mWriterTV;
        private TextView mQuestionDateTV;
        private ImageView mPrivacyIV;

        public ItemViewHolder(View itemView) {
            super(itemView);

            mTitleTV = (TextView) itemView.findViewById(R.id.tv_qna_title);
            mWriterTV = (TextView) itemView.findViewById(R.id.tv_qna_writer);
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
                        viewArticle(qna.getNo(), qna.getWriter());
                    }
                }
            });
        }

        public void bind(String title, String writer, String questionDate, boolean privacy) {
            mTitleTV.setText(title);
            mWriterTV.setText(writer);
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
         * @param no index of QnA article
         */
        private void viewArticle(int no, String writer) {
            Intent intent = new Intent(mContext, QnAArticleActivity.class);
            intent.putExtra(mContext.getString(R.string.EXTRA_NO), no);
            intent.putExtra(mContext.getString(R.string.EXTRA_WRITER), writer);
            mContext.startActivity(intent);
        }

    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

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
