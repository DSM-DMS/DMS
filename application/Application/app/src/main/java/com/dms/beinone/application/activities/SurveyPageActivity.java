package com.dms.beinone.application.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dms.beinone.application.R;
import com.dms.beinone.application.models.Survey;
import com.dms.beinone.application.views.adapters.SurveyViewPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dsm2017 on 2017-11-10.
 */

public class SurveyPageActivity extends AppCompatActivity{

    private ViewPager survey_viewpager = (ViewPager) findViewById(R.id.survey_question_view_pager);
    private ImageButton survey_next_button = (ImageButton) findViewById(R.id.survey_next_button_after);
    private ImageButton survey_after_button = (ImageButton) findViewById(R.id.survey_back_button_after);
    private TextView detail_text;
    private String explain_text; // 설명 글
    public ArrayList<String> answerList;
    private String title;
    private String date;

    public static int SurveyCurrentItem[];

    @Override
    public void onCreate(Bundle SavedInstanceState) {

        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_survey_page);

        detail_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(getApplicationContext(), R.style.AlertDialog);
                Dialog dialog = alertDialog.
                        setTitle(explain_text)
                        .setMessage(R.string.default_intro2)
                        .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).create();

                dialog.show();
            }
        });

        date = "2017-11-12";
        title = "모범학생";
        answerList = new ArrayList<String>();
        answerList.add("이성현");
        answerList.add("윤정현");
        answerList.add("이병찬");
        answerList.add("이민서");
        answerList.add("신동현");

        ArrayList<Survey> surveyArrayList = new ArrayList<>();
        surveyArrayList.add(new Survey(date, title, answerList));
        survey_viewpager.setAdapter(new SurveyViewPagerAdapter(getApplicationContext(), surveyArrayList, getSupportFragmentManager()));

         survey_next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                survey_viewpager.setCurrentItem(survey_viewpager.getCurrentItem() + 1, true);
            }
        });

        survey_after_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                survey_viewpager.setCurrentItem(survey_viewpager.getCurrentItem() - 1 , true);
            }
        });

        final int size = surveyArrayList.size();
        final LinearLayout view = (LinearLayout) findViewById(R.id.survey_current_view_count);
        survey_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("xxx", "onPageSelected: " + position);
                //setNextButtonText(position, size);
                setViewCount(view, size, position);
                setBack_button(position,0);
                setNext_button(position,size);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setViewCount(LinearLayout view, int count, int selectNum){
        view.removeAllViews();
        for(int i = 0;i < count;i++){
            View countView = new View(getApplicationContext());
            if(i == selectNum){
                countView.setBackground(getResources().getDrawable(R.drawable.count_view_shape_selected));
            }else{
                countView.setBackground(getResources().getDrawable(R.drawable.count_view_shape));
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 30);
            layoutParams.setMargins(8,0,8,0);
            view.addView(countView, layoutParams);
        }
    }


    private void setNext_button(int currentCount, int maxCount) {

        detail_text =(TextView)findViewById(R.id.detail_view);

        if(currentCount + 1 == maxCount) {

            survey_next_button.setVisibility(View.GONE);
            detail_text.setText("제출");
        } else {

            survey_next_button.setVisibility(View.VISIBLE);
        }
    }

    private void setBack_button(int currentCount, int minCount) {

        if (currentCount == minCount) {

            survey_after_button.setVisibility(View.GONE);
        } else {

            survey_after_button.setVisibility(View.VISIBLE);
        }
    }
}
