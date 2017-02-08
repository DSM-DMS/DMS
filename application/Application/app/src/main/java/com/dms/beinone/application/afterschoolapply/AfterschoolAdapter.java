package com.dms.beinone.application.afterschoolapply;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dms.beinone.application.R;

import java.util.List;

/**
 * Created by BeINone on 2017-01-31.
 */

public class AfterschoolAdapter extends RecyclerView.Adapter<AfterschoolAdapter.ViewHolder> {

    private List<Afterschool> mAfterschoolList;

    public AfterschoolAdapter(List<Afterschool> afterschoolList) {
        mAfterschoolList = afterschoolList;
    }

    @Override
    public AfterschoolAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_afterschool, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AfterschoolAdapter.ViewHolder holder, int position) {
        holder.bind(mAfterschoolList.get(position));
    }

    @Override
    public int getItemCount() {
        return mAfterschoolList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTV;
        private TextView mInstructorTV;
        private TextView mPlaceTV;
        private TextView mDayTV;
        private TextView mTargetTV;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleTV = (TextView) itemView.findViewById(R.id.tv_afterschool_title);
            mInstructorTV = (TextView) itemView.findViewById(R.id.tv_afterschool_instructor);
            mPlaceTV = (TextView) itemView.findViewById(R.id.tv_afterschool_place);
            mDayTV = (TextView) itemView.findViewById(R.id.tv_afterschool_day);
            mTargetTV = (TextView) itemView.findViewById(R.id.tv_afterschool_target);
        }

        public void bind(Afterschool afterschool) {
            mTitleTV.setText(afterschool.getTitle());
            mInstructorTV.setText(afterschool.getInstructor());
            mPlaceTV.setText(afterschool.getPlace());
            mDayTV.setText(afterschool.getDay());
            mTargetTV.setText(afterschool.getTarget());
        }
    }

}
