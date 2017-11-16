package com.dms.beinone.application.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dms.beinone.application.R;
import com.dms.beinone.application.models.DormitoryNotice;
import com.dms.beinone.application.models.Survey;
import com.dms.beinone.application.models.SurveyList;
import com.dms.beinone.application.views.adapters.SurveyAdapter;

import java.util.ArrayList;

public class SurveyListActivity extends AppCompatActivity {

        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;
        ImageButton back_button;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_survey_list);

                TextView titleTV = (TextView) findViewById(R.id.tv_toolbar_title);
                titleTV.setText("설문조사");
                back_button = (ImageButton) findViewById(R.id.ib_toolbar_back);
                back_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                finish();
                        }
                });


                final ArrayList<SurveyList> item = new ArrayList<>();
                item.add(new SurveyList("사감부", "연장신청에 관하여"));
                item.add(new SurveyList("사감부", "연장신청에 관하여"));
                item.add(new SurveyList("사감부", "연장신청에 관하여"));
                item.add(new SurveyList("사감부", "연장신청에 관하여"));
                item.add(new SurveyList("사감부", "연장신청에 관하여"));
                item.add(new SurveyList("사감부", "연장신청에 관하여"));
                item.add(new SurveyList("사감부", "연장신청에 관하여"));


                recyclerView = (RecyclerView) findViewById(R.id.dormitory_survey_recycler);
                layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(new SurveyAdapter(this, item));
        }
}
