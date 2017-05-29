package com.dms.beinone.application.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dms.beinone.application.Listeners;
import com.dms.beinone.application.OnMoreBtnClickListener;
import com.dms.beinone.application.R;
import com.dms.beinone.application.activities.FacilityReportArticleActivity;
import com.dms.beinone.application.models.FacilityReport;

import java.util.List;

import static com.dms.beinone.application.managers.RecyclerViewManager.TYPE_FOOTER;
import static com.dms.beinone.application.managers.RecyclerViewManager.TYPE_ITEM;

/**
 * Created by BeINone on 2017-01-20.
 */

public class FacilityReportAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<FacilityReport> mFacilityReportList;
    private OnMoreBtnClickListener mOnMoreBtnClickListener;

    public FacilityReportAdapter(Context context, List<FacilityReport> facilityReportList, OnMoreBtnClickListener onMoreBtnClickListener) {
        mContext = context;
        mFacilityReportList = facilityReportList;
        mOnMoreBtnClickListener = onMoreBtnClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.viewholder_facilityreport, parent, false);
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
            FacilityReport facilityReport = mFacilityReportList.get(position);
            ((ItemViewHolder) holder).bind(facilityReport.getTitle(), facilityReport.getWriter(), facilityReport.getWriteDate());
        }
    }

    @Override
    public int getItemCount() {
        return mFacilityReportList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mFacilityReportList.size()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTV;
        private TextView mWriterTV;
        private TextView mWriteDateTV;

        public ItemViewHolder(View itemView) {
            super(itemView);

            mTitleTV = (TextView) itemView.findViewById(R.id.tv_facilityreport_title);
            mWriterTV = (TextView) itemView.findViewById(R.id.tv_facilityreport_writer);
            mWriteDateTV = (TextView) itemView.findViewById(R.id.tv_facilityreport_writedate);

            itemView.setOnTouchListener(Listeners.changeTextColorOnTouchListener(mContext, mTitleTV));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FacilityReport facilityReport = mFacilityReportList.get(getAdapterPosition());
                    viewArticle(facilityReport);
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
         * @param facilityReport
         */
        private void viewArticle(FacilityReport facilityReport) {
            Intent intent = new Intent(mContext, FacilityReportArticleActivity.class);
            intent.putExtra(mContext.getString(R.string.EXTRA_FACILITYREPORT), facilityReport);
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
