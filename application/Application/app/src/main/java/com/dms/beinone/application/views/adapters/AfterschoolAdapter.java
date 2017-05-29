package com.dms.beinone.application.views.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dms.beinone.application.R;
import com.dms.beinone.application.dialogs.ApplyAfterschoolDialogFragment;
import com.dms.beinone.application.models.Afterschool;

import java.util.List;

/**
 * Created by BeINone on 2017-01-31.
 */

public class AfterschoolAdapter extends RecyclerView.Adapter<AfterschoolAdapter.ViewHolder> {

    private Context mContext;
    private List<Afterschool> mAfterschools;

    public AfterschoolAdapter(Context context, List<Afterschool> afterschools) {
        mContext = context;
        mAfterschools = afterschools;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_afterschool, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Afterschool afterschool = mAfterschools.get(position);

        holder.bind(afterschool);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplyAfterschoolDialogFragment.newInstance(mContext, afterschool.getNo());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAfterschools.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTV;
        private TextView mInstructorTV;
        private TextView mPlaceTV;
        private TextView mMondayTV;
        private TextView mTuesdayTV;
        private TextView mWednesdayTV;
        private TextView mSaturdayTV;
        private TextView mFirstGradeTV;
        private TextView mSecondGradeTV;
        private TextView mThridGradeTV;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleTV = (TextView) itemView.findViewById(R.id.tv_afterschool_title);
            mInstructorTV = (TextView) itemView.findViewById(R.id.tv_afterschool_instructor);
            mPlaceTV = (TextView) itemView.findViewById(R.id.tv_afterschool_place);
            mMondayTV = (TextView) itemView.findViewById(R.id.tv_afterschool_monday);
            mTuesdayTV = (TextView) itemView.findViewById(R.id.tv_afterschool_tuesday);
            mWednesdayTV = (TextView) itemView.findViewById(R.id.tv_afterschool_wednesday);
            mSaturdayTV = (TextView) itemView.findViewById(R.id.tv_afterschool_saturday);
            mFirstGradeTV = (TextView) itemView.findViewById(R.id.tv_afterschool_grade1);
            mSecondGradeTV = (TextView) itemView.findViewById(R.id.tv_afterschool_grade2);
            mThridGradeTV = (TextView) itemView.findViewById(R.id.tv_afterschool_grade3);
        }

        public void bind(final Afterschool afterschool) {
            mTitleTV.setText(afterschool.getTitle());
            mInstructorTV.setText(afterschool.getInstructor());
            mPlaceTV.setText(afterschool.getPlace());
            if (afterschool.isOnMonday()) mMondayTV.setTextColor(Color.BLACK);
            if (afterschool.isOnTuesday()) mTuesdayTV.setTextColor(Color.BLACK);
            if (afterschool.isOnWednesday()) mWednesdayTV.setTextColor(Color.BLACK);
            if (afterschool.isOnSaturday()) mSaturdayTV.setTextColor(Color.BLACK);
        }

//        private String getAfterschoolDayString(Afterschool afterschool) {
//            boolean isFirstDay = true;
//            String afterschoolDayString = "";
//
//            if (afterschool.isOnMonday()) {
//                afterschoolDayString = "월";
//                isFirstDay = false;
//            }
//
//            if (afterschool.isOnTuesday()) {
//                if (isFirstDay) {
//                    afterschoolDayString = "화";
//                    isFirstDay = false;
//                } else {
//                    afterschoolDayString += ", 화";
//                }
//            }
//
//            if (afterschool.isOnWednesday()) {
//                if (isFirstDay) {
//                    afterschoolDayString = "수";
//                    isFirstDay = false;
//                } else {
//                    afterschoolDayString += ", 수";
//                }
//            }
//
//            if (afterschool.isOnSaturday()) {
//                if (isFirstDay) {
//                    afterschoolDayString = "토";
//                } else {
//                    afterschoolDayString += ", 토";
//                }
//            }
//
//            return afterschoolDayString;
//        }
    }
}