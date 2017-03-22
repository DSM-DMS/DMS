package com.dms.beinone.application.facilityreport;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.dms.beinone.application.utils.EditTextUtils;
import com.dms.beinone.application.R;

/**
 * Created by BeINone on 2017-03-06.
 */

public class FacilityReportWriteFragment extends Fragment {

    private TextView mTitleTV;
    private EditText mTitleET;
    private TextView mRoomTV;
    private EditText mRoomET;
    private TextView mContentTV;
    private TextView mContentET;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facilityreport_write, container, false);
        init(view);

        return view;
    }

    private void init(View rootView) {
        mTitleTV = (TextView) rootView.findViewById(R.id.tv_facilityreport_write_title);
        mTitleET = (EditText) rootView.findViewById(R.id.et_facilityreport_write_title);
        mTitleET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mTitleTV.setTextColor(
                            ContextCompat.getColor(getContext(), R.color.colorPrimary));
                } else {
                    mTitleTV.setTextColor(
                            ContextCompat.getColor(getContext(), android.R.color.primary_text_light));
                    // hide the soft keyboard when touch outside
                    EditTextUtils.hideKeyboard(getContext(), (EditText) v);
                }
            }
        });

        mRoomTV = (TextView) rootView.findViewById(R.id.tv_facilityreport_write_room);
        mRoomET = (EditText) rootView.findViewById(R.id.et_facilityreport_write_room);
        mRoomET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mRoomTV.setTextColor(
                            ContextCompat.getColor(getContext(), R.color.colorPrimary));
                } else {
                    mRoomTV.setTextColor(
                            ContextCompat.getColor(getContext(), android.R.color.primary_text_light));
                    // hide the soft keyboard when touch outside
                    EditTextUtils.hideKeyboard(getContext(), (EditText) v);
                }
            }
        });

        mContentTV = (TextView) rootView.findViewById(R.id.tv_facilityreport_write_content);
        mContentET = (EditText) rootView.findViewById(R.id.et_facilityreport_write_content);
        mContentET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mContentTV.setTextColor(
                            ContextCompat.getColor(getContext(), R.color.colorPrimary));
                } else {
                    mContentTV.setTextColor(
                            ContextCompat.getColor(getContext(), android.R.color.primary_text_light));
                    // hide the soft keyboard when touch outside
                    EditTextUtils.hideKeyboard(getContext(), (EditText) v);
                }
            }
        });
    }

    public String getTitle() {
        return mTitleET.getText().toString().trim();
    }

    public String getRoom() {
        return mRoomET.getText().toString().trim();
    }

    public String getContent() {
        return mContentET.getText().toString().trim();
    }

}
