package com.dms.beinone.application.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dms.beinone.application.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by dsm2017 on 2017-11-13.
 */

public class SurveyRecyclerAdapter extends RecyclerView.Adapter<SurveyRecyclerAdapter.ViewHolder> {

    ArrayList<String> answerList;

    public SurveyRecyclerAdapter (ArrayList<String> answerList) {

        this.answerList = answerList;
    }

    @Override
    public void onBindViewHolder(ViewHolder v, int position) {

        v.tbQuestionList.setText(answerList.get(position));
        v.rbAnswerCheck.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_sruvey_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RadioButton rbAnswerCheck;
        TextView tbQuestionList;

        public ViewHolder(View view) {
            super(view);
            rbAnswerCheck = (RadioButton) view.findViewById(R.id.item_survey_page_rb);
            tbQuestionList = (TextView) view.findViewById(R.id.tv_question_list);
        }
    }
}
