package com.dms.beinone.application;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samsistemas.calendarview.widget.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by BeINone on 2017-01-14.
 */

public class StayApplyFragment extends Fragment {

    private static final int FRIDAY_GO = 1;
    private static final int SATURDAY_GO = 2;
    private static final int SATURDAY_COME = 3;
    private static final int STAY = 4;

    private TextView mSelectedWeekTV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.nav_stayapply);
        View view = inflater.inflate(R.layout.fragment_stayapply, container, false);

        init(view);

        return view;
    }

    /**
     * 초기화, 달력 날짜 클릭 이벤트 설정, 신청 버튼 클릭 이벤트 설정
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        mSelectedWeekTV = (TextView) rootView.findViewById(R.id.tv_stayapply_selectedweek);

        final CalendarView calendarView = (CalendarView) rootView.findViewById(R.id.calendar_stayapply);
        calendarView.setOnDateLongClickListener(null);
        calendarView.setOnDateClickListener(new CalendarView.OnDateClickListener() {
            @Override
            public void onDateClick(@NonNull Date date) {
                setSelectedWeekTV(date);
            }
        });

        setSelectedWeekTV(calendarView.getLastSelectedDay());

        final DMSRadioButton fridayGoRB =
                (DMSRadioButton) rootView.findViewById(R.id.rb_stayapply_fridaygo);
        final DMSRadioButton saturdayGoRB =
                (DMSRadioButton) rootView.findViewById(R.id.rb_stayapply_saturdaygo);
        final DMSRadioButton saturdayComeRB =
                (DMSRadioButton) rootView.findViewById(R.id.rb_stayapply_saturdaycome);
        final DMSRadioButton stayRB = (DMSRadioButton) rootView.findViewById(R.id.rb_stayapply_stay);

        DMSButton applyBtn = (DMSButton) rootView.findViewById(R.id.btn_stayapply_apply);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fridayGoRB.isChecked()) apply(FRIDAY_GO);
                else if (saturdayGoRB.isChecked()) apply(SATURDAY_GO);
                else if (saturdayComeRB.isChecked()) apply(SATURDAY_COME);
                else if (stayRB.isChecked()) apply(STAY);
            }
        });
    }

    private void setSelectedWeekTV(Date date) {
        // 토요일 기준으로 주 구분
        Calendar cal = Calendar.getInstance(Locale.KOREA);
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 M월 W주", Locale.KOREA);
        mSelectedWeekTV.setText(sdf.format(cal.getTime()));
    }

    private void setDefaultValueTV() {

    }

    private void setSelectedWeekValueTV() {

    }

    /**
     * 데이터베이스에 잔류 상태 정보 기록
     * @param stayState 잔류 상태
     */
    private void apply(int stayState) {

    }

}
