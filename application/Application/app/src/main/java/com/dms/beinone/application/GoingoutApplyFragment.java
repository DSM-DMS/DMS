package com.dms.beinone.application;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by BeINone on 2017-01-16.
 */

public class GoingoutApplyFragment extends Fragment {

    private static final int SATURDAY = 1;
    private static final int SUNDAY = 2;
    private static final int BOTH = 3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(R.string.nav_goingoutapply);
        View view = inflater.inflate(R.layout.fragment_goingoutapply, container, false);

        final DMSToggleButton saturdayToggle =
                (DMSToggleButton) view.findViewById(R.id.toggle_goingoutapply_saturday);
        final DMSToggleButton sundayToggle =
                (DMSToggleButton) view.findViewById(R.id.toggle_goingoutapply_sunday);

        DMSButton applyBtn = (DMSButton) view.findViewById(R.id.btn_goingoutapply_apply);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (saturdayToggle.isChecked()) apply(SATURDAY);
                else if (sundayToggle.isChecked()) apply(SUNDAY);
                else if (saturdayToggle.isChecked() && sundayToggle.isChecked()) apply(BOTH);
            }
        });

        return view;
    }

    private void apply(int day) {

    }

}
