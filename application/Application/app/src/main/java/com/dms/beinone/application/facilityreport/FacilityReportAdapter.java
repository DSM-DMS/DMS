package com.dms.beinone.application.facilityreport;

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
 * Created by BeINone on 2017-01-20.
 */

public class FacilityReportAdapter extends RecyclerView.Adapter<FacilityReportAdapter.ViewHolder> {

    private Context mContext;
    private List<FacilityReport> mFacilityReportList;

    public FacilityReportAdapter(Context context, List<FacilityReport> facilityReportList) {
        mContext = context;
        mFacilityReportList = facilityReportList;
    }

    @Override
    public FacilityReportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_facilityreport, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FacilityReportAdapter.ViewHolder holder, int position) {
        FacilityReport facilityReport = mFacilityReportList.get(position);

        holder.bind(facilityReport.getTitle(), facilityReport.getWriter(), facilityReport.getWriteDate());
    }

    @Override
    public int getItemCount() {
        return mFacilityReportList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTV;
        private TextView mWriterTV;
        private TextView mWriteDateTV;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleTV = (TextView) itemView.findViewById(R.id.tv_facilityreport_title);
            mWriterTV = (TextView) itemView.findViewById(R.id.tv_facilityreport_writer);
            mWriteDateTV = (TextView) itemView.findViewById(R.id.tv_facilityreport_writedate);

            itemView.setOnTouchListener(Listeners.changeTextColorOnTouchListener(mContext, mTitleTV));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewArticle(mFacilityReportList.get(getAdapterPosition()).getNo());
                }
            });
        }

        public void bind(String title, String writer, String writeDate) {
            mTitleTV.setText(title);
            mWriterTV.setText(writer);
            mWriteDateTV.setText(writeDate);
        }

        /**
         * start a new activity to display article
         * @param no index of FacilityReport article
         */
        private void viewArticle(int no) {
            Intent intent = new Intent(mContext, FacilityReportArticleActivity.class);
            intent.putExtra(mContext.getString(R.string.EXTRA_NO), no);
            mContext.startActivity(intent);
        }

    }

}
