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

public class RecommendFragment extends Fragment {

    private TextView mContentTV;
    private EditText mContentET;
    private TextView mTargetTV;
    private EditText mTargetET;
    private OnApplyBtnClickListener mOnApplyBtnClickListener;

    public static RecommendFragment newInstance(OnApplyBtnClickListener onApplyBtnClickListener) {
        RecommendFragment fragment = new RecommendFragment();
        fragment.mOnApplyBtnClickListener = onApplyBtnClickListener;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rewardscoreapply_recommend, container, false);
        init(view);

        return view;
    }

    /**
     * 초기화, 내용 및 추천인 EditText 포커스 이벤트 설정, 신청 버튼 클릭 이벤트 설정
     *
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        mContentTV = (TextView) rootView.findViewById(R.id.tv_recommend_content);

        mContentET = (DMSEditText) rootView.findViewById(R.id.et_recommend_content);
        mContentET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mContentTV.setTextColor(
                            ContextCompat.getColor(getContext(), R.color.colorPrimary));
                } else {
                    EditTextManager.hideKeyboard(getContext(), (EditText) v);
                    mContentTV.setTextColor(
                            ContextCompat.getColor(getContext(), android.R.color.primary_text_light));
                }
            }
        });

        mTargetTV = (TextView) rootView.findViewById(R.id.tv_recommend_target);

        mTargetET = (DMSEditText) rootView.findViewById(R.id.et_recommend_target);
        mTargetET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mTargetTV.setTextColor(
                            ContextCompat.getColor(getContext(), R.color.colorPrimary));
                } else {
                    EditTextManager.hideKeyboard(getContext(), (EditText) v);
                    mTargetTV.setTextColor(
                            ContextCompat.getColor(getContext(), android.R.color.primary_text_light));
                }
            }
        });

        SharedPreferences prefs = getContext()
                .getSharedPreferences(getString(R.string.PREFS_ACCOUNT), Context.MODE_PRIVATE);
        final String id = prefs.getString(getString(R.string.PREFS_ACCOUNT_ID), "");

        DMSButton applyBtn = (DMSButton) getActivity().findViewById(R.id.btn_rewardscoreapply_apply);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mContentET.getText().toString().trim();
                String target = mTargetET.getText().toString().trim();
                if (content.length() == 0) {
                    Toast.makeText(getContext(), R.string.rewardscoreapply_nocontent, Toast.LENGTH_SHORT)
                            .show();
                } else if (target.length() == 0) {
                    Toast.makeText(getContext(), R.string.rewardscoreapply_norecommendee, Toast.LENGTH_SHORT)
                            .show();
                } else {
                    mOnApplyBtnClickListener.onApplyBtnClick(target, content);
                }
            }
        });
    }

    private void clearView() {
        mTargetET.setText("");
        mContentET.setText("");
    }

    public interface OnApplyBtnClickListener {
        void onApplyBtnClick(String target, String content);
    }
}
