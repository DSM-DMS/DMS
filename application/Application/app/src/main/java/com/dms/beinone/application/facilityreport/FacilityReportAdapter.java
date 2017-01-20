package com.dms.beinone.application.facilityreport;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dms.beinone.application.R;

import java.util.List;

/**
 * Created by BeINone on 2017-01-20.
 */

public class FacilityReportAdapter extends RecyclerView.Adapter<FacilityReportAdapter.ViewHolder> {

    private List<FacilityReport> mFacilityReportList;

    public FacilityReportAdapter(List<FacilityReport> facilityReportList) {
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

    }

    @Override
    public int getItemCount() {
        return mFacilityReportList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTV;
        private TextView mWriterTV;
        private TextView mDateTV;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleTV = (TextView) itemView.findViewById(R.id.tv_facilityreport_title);
            mWriterTV = (TextView) itemView.findViewById(R.id.tv_facilityreport_writer);
            mDateTV = (TextView) itemView.findViewById(R.id.tv_facilityreport_date);
        }

        public void bind() {

        }

    }

}
