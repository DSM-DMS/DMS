package com.dms.beinone.application.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dms.beinone.application.R;
import com.dms.beinone.application.activities.AfterSchoolActivity;
import com.dms.beinone.application.activities.SurveyPageActivity;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by dsm2017 on 2017-11-13.
 */

public class SurveyRecyclerAdapter extends RecyclerView.Adapter<SurveyRecyclerAdapter.ViewHolder> {

    ArrayList<String> answerList;
    private int listPosition;
    Context context;

    public SurveyRecyclerAdapter (Context context, ArrayList<String> answerList, int listPosition) {

        this.answerList = answerList;
        this.listPosition = listPosition;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(ViewHolder v, int position) {

        v.tbQuestionList.setText(answerList.get(position));
        v.rbAnswerCheck.setChecked(false);
        if(position == AfterSchoolActivity.currentSet[this.listPosition] - 1){
            v.rbAnswerCheck.setChecked(true);
        }else{
            v.rbAnswerCheck.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_sruvey_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v, viewType, listPosition);
        return viewHolder;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RadioButton rbAnswerCheck;
        TextView tbQuestionList;
        LinearLayout linearLayout;

        public ViewHolder(View view, final int position, final int listPostion) {
            super(view);
            rbAnswerCheck = (RadioButton) view.findViewById(R.id.item_survey_page_rb);
            tbQuestionList = (TextView) view.findViewById(R.id.tv_question_list);
            linearLayout = (LinearLayout) view.findViewById(R.id.survey_detail_relative);
            linearLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick (View view) {

                    Intent intent = new Intent(context, SurveyPageActivity.class);
                }
            });
            rbAnswerCheck.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick (View view) {

                    SurveyPageActivity.SurveyCurrentItem[listPosition] = position + 1;

                    notifyDataSetChanged();
                }
            });


        }
    }
}
