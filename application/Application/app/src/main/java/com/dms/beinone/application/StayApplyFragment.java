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
import java.util.Date;
import java.util.Locale;

/**
 * Created by BeINone on 2017-01-14.
 */

public class StayApplyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.nav_stayapply);
        View view = inflater.inflate(R.layout.fragment_stayapply, container, false);

        final TextView selectedWeekTV = (TextView) view.findViewById(R.id.tv_stayapply_selectedweek);

        final CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendar_stayapply);
        calendarView.setOnDateLongClickListener(null);
        calendarView.setOnDateClickListener(new CalendarView.OnDateClickListener() {
            @Override
            public void onDateClick(@NonNull Date date) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 M월 W주", Locale.KOREA);
                selectedWeekTV.setText(sdf.format(date));
            }
        });

        DMSRadioButton fridayGoRadio =
                (DMSRadioButton) view.findViewById(R.id.radio_stayapply_fridaygo);
        DMSRadioButton saturdayGoRadio =
                (DMSRadioButton) view.findViewById(R.id.radio_stayapply_saturdaygo);
        DMSRadioButton saturdayComeRadio =
                (DMSRadioButton) view.findViewById(R.id.radio_stayapply_saturdaycome);
        DMSRadioButton stayRadio = (DMSRadioButton) view.findViewById(R.id.radio_stayapply_stay);



        return view;
    }

}
