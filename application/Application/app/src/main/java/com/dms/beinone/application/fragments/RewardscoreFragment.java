package com.dms.beinone.application.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.beinone.application.views.custom.DMSButton;
import com.dms.beinone.application.views.custom.DMSEditText;
import com.dms.beinone.application.managers.EditTextManager;

/**
 * Created by BeINone on 2017-01-17.
 */

public class RewardscoreFragment extends Fragment {

    private EditText mContentET;
    private TextView mContentTV;
    private OnApplyBtnClickListener mOnApplyBtnClickListener;

    public static RewardscoreFragment newInstance(OnApplyBtnClickListener onApplyBtnClickListener) {
        RewardscoreFragment fragment = new RewardscoreFragment();
        fragment.mOnApplyBtnClickListener = onApplyBtnClickListener;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rewardscore_rewardscore, container, false);
        init(view);

        return view;
    }

    /**
     * Initializes the edit text
     *
     * @param rootView view to find child views
     */
    private void init(View rootView) {
        mContentTV = (TextView) rootView.findViewById(R.id.tv_rewardscore_content);

        mContentET = (DMSEditText) rootView.findViewById(R.id.et_rewardscore_content);
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
                    EditTextManager.hideKeyboard(getContext(), (EditText) v);
                }
            }
        });

        SharedPreferences prefs =
                getContext().getSharedPreferences(getString(R.string.PREFS_ACCOUNT), Context.MODE_PRIVATE);
        final String id = prefs.getString(getString(R.string.PREFS_ACCOUNT_ID), "");

        DMSButton applyBtn = (DMSButton) getActivity().findViewById(R.id.btn_rewardscore_apply);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mContentET.getText().toString().trim();
                if (content.length() == 0) {
                    Toast.makeText(getContext(), R.string.rewardscore_nocontent, Toast.LENGTH_SHORT)
                            .show();
                } else {
                    mOnApplyBtnClickListener.onApplyBtnClick(content);
                }
            }
        });
    }

    private void clearView() {
        mContentET.setText("");
    }

    public interface OnApplyBtnClickListener {
        void onApplyBtnClick(String content);
    }
}
