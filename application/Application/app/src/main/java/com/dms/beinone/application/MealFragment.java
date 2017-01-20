package com.dms.beinone.application;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.samsistemas.calendarview.widget.CalendarView;

import java.util.Date;

/**
 * Created by BeINone on 2017-01-18.
 */

public class MealFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(R.string.nav_meal);
        View view = inflater.inflate(R.layout.fragment_meal, container, false);

        init(view);

        return view;
    }

    /**
     * 초기화, 더보기 버튼 터치 이벤트 및 클릭 이벤트 설정, 달력 날짜 클릭 이벤트 설정
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        ImageButton breakfastExpandBtn = (ImageButton) rootView.findViewById(R.id.btn_meal_expandbreakfast);
        ImageButton lunchExpandBtn = (ImageButton) rootView.findViewById(R.id.btn_meal_expandlunch);
        ImageButton dinnerExpandBtn = (ImageButton) rootView.findViewById(R.id.btn_meal_expanddinner);

        breakfastExpandBtn.setOnTouchListener(Listeners.getExpandBtnOnTouchListener(getContext()));
        lunchExpandBtn.setOnTouchListener(Listeners.getExpandBtnOnTouchListener(getContext()));
        dinnerExpandBtn.setOnTouchListener(Listeners.getExpandBtnOnTouchListener(getContext()));

        breakfastExpandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        lunchExpandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dinnerExpandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CalendarView calendarView = (CalendarView) rootView.findViewById(R.id.calendar_meal);
        calendarView.setOnDateClickListener(new CalendarView.OnDateClickListener() {
            @Override
            public void onDateClick(@NonNull Date selectedDate) {

            }
        });
    }

}
