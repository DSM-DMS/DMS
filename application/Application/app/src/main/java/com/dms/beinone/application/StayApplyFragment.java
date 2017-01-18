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
     * Initializes the selected week text view and the radio button and the apply button
     * @param rootView view to find child views
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

        // 초기에 금요귀가 버튼 자동 선택
        fridayGoRB.setChecked(true);

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

    /**
     * Sets selected week on selectedweek textview
     * @param date Date that selected
     */
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
     * Inserts or updates selected stay state value on database
     * @param stayState Stay state to insert or update on database
     */
    private void apply(int stayState) {

    }

}
