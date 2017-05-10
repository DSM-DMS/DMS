package com.dms.beinone.application.afterschoolapply;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        private Button mApplyBtn;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleTV = (TextView) itemView.findViewById(R.id.tv_afterschool_title);
            mInstructorTV = (TextView) itemView.findViewById(R.id.tv_afterschool_instructor);
            mPlaceTV = (TextView) itemView.findViewById(R.id.tv_afterschool_place);
            mDayTV = (TextView) itemView.findViewById(R.id.tv_afterschool_day);
            mTargetTV = (TextView) itemView.findViewById(R.id.tv_afterschool_target);
            mApplyBtn = (Button) itemView.findViewById(R.id.btn_afterschoolapply_apply);

            mApplyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void bind(Afterschool afterschool) {
            mTitleTV.setText(afterschool.getTitle());
            mInstructorTV.setText(afterschool.getInstructor());
            mPlaceTV.setText(afterschool.getPlace());
            mDayTV.setText(getAfterschoolDayString(afterschool));
            mTargetTV.setText(String.valueOf(afterschool.getTarget()));
        }

        private String getAfterschoolDayString(Afterschool afterschool) {
            boolean isFirstDay = true;
            String afterschoolDayString = "";

            if (afterschool.isOnMonday()) {
                afterschoolDayString = "월";
                isFirstDay = false;
            }

            if (afterschool.isOnTuesday()) {
                if (isFirstDay) {
                    afterschoolDayString = "화";
                    isFirstDay = false;
                } else {
                    afterschoolDayString += ", 화";
                }
            }

            if (afterschool.isOnWednesday()) {
                if (isFirstDay) {
                    afterschoolDayString = "수";
                    isFirstDay = false;
                } else {
                    afterschoolDayString += ", 수";
                }
            }

            if (afterschool.isOnSaturday()) {
                if (isFirstDay) {
                    afterschoolDayString = "토";
                } else {
                    afterschoolDayString += ", 토";
                }
            }

            return afterschoolDayString;
        }
    }

}
