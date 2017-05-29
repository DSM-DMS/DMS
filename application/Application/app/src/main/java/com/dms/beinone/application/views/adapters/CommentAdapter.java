package com.dms.beinone.application.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dms.beinone.application.R;
import com.dms.beinone.application.models.Comment;

import java.util.List;

/**
 * Created by BeINone on 2017-01-25.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<Comment> mCommentList;

    public CommentAdapter(List<Comment> commentList) {
        mCommentList = commentList;
    }

    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_comment, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentAdapter.ViewHolder holder, int position) {
        holder.bind(mCommentList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mWriterTV;
        private TextView mDateTV;
        private TextView mContentTV;

        public ViewHolder(View itemView) {
            super(itemView);

            mWriterTV = (TextView) itemView.findViewById(R.id.tv_comment_writer);
            mDateTV = (TextView) itemView.findViewById(R.id.tv_comment_date);
            mContentTV = (TextView) itemView.findViewById(R.id.tv_comment_content);
        }

        public void bind(Comment comment) {
            mWriterTV.setText(comment.getWriter());
            mDateTV.setText(comment.getCommentDate());
            mContentTV.setText(comment.getContent());
        }
    }

    public void add(Comment comment) {
        mCommentList.add(comment);
        notifyDataSetChanged();
    }

}
