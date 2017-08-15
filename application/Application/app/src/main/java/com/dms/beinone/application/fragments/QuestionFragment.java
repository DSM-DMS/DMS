package com.dms.beinone.application.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dms.beinone.application.models.AfterSchool;
import com.dms.beinone.application.models.AfterSchoolClass;
import com.dms.beinone.application.R;
import com.dms.beinone.application.views.adapters.QuestionAdapter;


/**
 * Created by root1 on 2017. 8. 8..
 */

public class QuestionFragment extends Fragment {


    AfterSchoolClass data;
    int position;

    @SuppressLint("ValidFragment")
    public QuestionFragment(AfterSchoolClass data, int position){
        this.data = data;
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_afterschool_question, container, false);

        TextView question_name_text = (TextView) view.findViewById(R.id.question_name_text);
        RecyclerView choose_answer_recyclerview = (RecyclerView) view.findViewById(R.id.choose_answer_recyclerview);

        question_name_text.setText(data.getQuestionName());

        choose_answer_recyclerview.setLayoutManager(new LinearLayoutManager(container.getContext()));
        Log.d("xxxFragment", "onCreateView: " + position);
        choose_answer_recyclerview.setAdapter(new QuestionAdapter(getContext(), data.getAnswerList(), position));

        return view;
    }
}

