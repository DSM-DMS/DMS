package com.dms.beinone.application.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dms.beinone.application.R;
import com.dms.beinone.application.activities.LoginActivity;

/**
 * Created by BeINone on 2017-05-31.
 */

public class MyPageFragment extends Fragment {

    private TextView mStayStatusTV;
    private TextView mExtensionStatusTV;
    private TextView mMeritTV;
    private TextView mDemeritTV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);

        mStayStatusTV = (TextView) view.findViewById(R.id.tv_my_page_stay_status);
        mExtensionStatusTV = (TextView) view.findViewById(R.id.tv_my_page_extension_status);
        mMeritTV = (TextView) view.findViewById(R.id.tv_my_page_merit);
        mDemeritTV = (TextView) view.findViewById(R.id.tv_my_page_demerit);

        initMenuArrowColor(view);

        View qnaListMenu = view.findViewById(R.id.layout_my_page_qna_list);
        qnaListMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        View reportFacilityMenu = view.findViewById(R.id.layout_my_page_report_facility);
        reportFacilityMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        View changePasswordMenu = view.findViewById(R.id.layout_my_page_change_password);
        changePasswordMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        View logoutMenu = view.findViewById(R.id.layout_my_page_logout);
        logoutMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button loginBtn = (Button) view.findViewById(R.id.btn_my_page_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });

        return view;
    }

    private void initMenuArrowColor(View rootView) {
        ImageView arrowIV1 = (ImageView) rootView.findViewById(R.id.iv_my_page_arrow1);
        ImageView arrowIV2 = (ImageView) rootView.findViewById(R.id.iv_my_page_arrow2);
        ImageView arrowIV3 = (ImageView) rootView.findViewById(R.id.iv_my_page_arrow3);
        ImageView arrowIV4 = (ImageView) rootView.findViewById(R.id.iv_my_page_arrow4);

        arrowIV1.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        arrowIV2.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        arrowIV3.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        arrowIV4.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
    }
}
