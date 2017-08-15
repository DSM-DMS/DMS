package com.dms.beinone.application.activities;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dms.beinone.application.fragments.QuestionFragment;
import com.dms.beinone.application.models.AfterSchoolClass;

import java.util.ArrayList;
import com.dms.beinone.application.R;

public class AfterSchoolActivity extends AppCompatActivity {

    private ViewPager question_view_pager_yjh;
    private Button next_button;
    private ImageButton back_button;

    private int size;

    public static int [] currentSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_school);

        TextView titleTV = (TextView) findViewById(R.id.tv_toolbar_title);
        titleTV.setText("방과후 신청");
        ArrayList<AfterSchoolClass> tempData = new ArrayList<>();
        ArrayList<String> dataArray = new ArrayList<>();
        dataArray.add("야구");
        dataArray.add("배드민턴");
        dataArray.add("발야구");
        dataArray.add("족구");
        dataArray.add("피구");

        ArrayList<String> dataArray1 = new ArrayList<>();
        dataArray1.add("검도");
        dataArray1.add("댄스부");
        dataArray1.add("자습");
        dataArray1.add("c언어");
        dataArray1.add("자바");

        ArrayList<String> dataArray2 = new ArrayList<>();
        dataArray2.add("자전거");
        dataArray2.add("배구");
        dataArray2.add("배드민턴");
        dataArray2.add("축구");


        tempData.add(new AfterSchoolClass("월요일", dataArray));
        tempData.add(new AfterSchoolClass("화요일", dataArray1));
        tempData.add(new AfterSchoolClass("토요일", dataArray2));

        size = tempData.size();

        currentSet = new int[size];

        question_view_pager_yjh=(ViewPager)findViewById(R.id.question_view_pager);
        question_view_pager_yjh.setAdapter(new QuestionPagerAdapter(getSupportFragmentManager(), tempData));

        final LinearLayout view = (LinearLayout) findViewById(R.id.current_view_count);

        next_button=(Button)findViewById(R.id.next_button);

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question_view_pager_yjh.setCurrentItem(question_view_pager_yjh.getCurrentItem() + 1, true);
            }
        });

        question_view_pager_yjh.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("xxx", "onPageSelected: " + position);
                setNextButtonText(position, size);
                setViewCount(view, size, position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        setViewCount(view, size, 0);
        back_button=(ImageButton)findViewById(R.id.ib_toolbar_back);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50, 50);
            layoutParams.setMargins(8,0,8,0);
            view.addView(countView, layoutParams);
        }
    }

    public class QuestionPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<AfterSchoolClass> data;

        public QuestionPagerAdapter(FragmentManager fm, ArrayList<AfterSchoolClass> data){
            super(fm);
            this.data = data;
        }

        @Override
        public Fragment getItem(int position) {
            Log.d("xxx Main", "getItem: " + position);
            return new QuestionFragment(data.get(position), position);
        }

        @Override
        public int getCount() {
            return data.size();
        }
    }

    private void setNextButtonText(int currentCount, int maxCount) {
        if(currentCount + 1 == maxCount){
            next_button.setText("제출하기");
        }else{
            next_button.setText("다음 문항");
        }
    }


}
