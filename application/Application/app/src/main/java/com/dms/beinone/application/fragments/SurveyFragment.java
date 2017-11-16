package com.dms.beinone.application.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dms.beinone.application.R;
import com.dms.beinone.application.views.adapters.SurveyRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by dsm2017 on 2017-11-12.
 */

public class SurveyFragment extends Fragment {

    RecyclerView.LayoutManager layoutManager;
    RecyclerView surveyRecyclerView;
    ArrayList<String> questionList;
    int index = 1;

    public SurveyFragment (ArrayList<String> questionList) {

        this.questionList = questionList;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        surveyRecyclerView = (RecyclerView)container.findViewById(R.id.choose_answer_recyclerview);

        View view = layoutInflater.inflate(R.layout.fragment_survey, container, false);

        layoutManager = new LinearLayoutManager(getContext());
        surveyRecyclerView.setLayoutManager(layoutManager);
        surveyRecyclerView.setAdapter(new SurveyRecyclerAdapter(questionList));
        return view;
    }

    private void setNumber () {


    }
}
