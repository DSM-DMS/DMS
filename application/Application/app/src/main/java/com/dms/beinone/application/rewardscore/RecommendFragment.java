package com.dms.beinone.application.rewardscore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.dmsview.DMSButton;
import com.dms.beinone.application.dmsview.DMSEditText;
import com.dms.beinone.application.R;

/**
 * Created by BeINone on 2017-01-17.
 */

public class RecommendFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);

        init(view);

        return view;
    }

    /**
     * 초기화, 내용 및 추천인 EditText 포커스 이벤트 설정, 신청 버튼 클릭 이벤트 설정
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        final TextView contentTV = (TextView) rootView.findViewById(R.id.tv_recommend_content);

        final DMSEditText contentET = (DMSEditText) rootView.findViewById(R.id.et_recommend_content);
        contentET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    contentTV.setTextColor(
                            ContextCompat.getColor(getContext(), R.color.colorPrimary));
                } else {
                    contentTV.setTextColor(
                            ContextCompat.getColor(getContext(), android.R.color.primary_text_light));
                }
            }
        });

        final TextView recommendeeTV = (TextView) rootView.findViewById(R.id.tv_recommend_recommendee);

        final DMSEditText recommendeeET = (DMSEditText) rootView.findViewById(R.id.et_recommend_recommendee);
        recommendeeET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    recommendeeTV.setTextColor(
                            ContextCompat.getColor(getContext(), R.color.colorPrimary));
                } else {
                    recommendeeTV.setTextColor(
                            ContextCompat.getColor(getContext(), android.R.color.primary_text_light));
                }
            }
        });

        DMSButton applyBtn = (DMSButton) rootView.findViewById(R.id.btn_recommend_apply);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = contentET.getText().toString().trim();
                String recommendee = recommendeeET.getText().toString().trim();
                if (content.length() == 0) {
                    Toast.makeText(getContext(), R.string.rewardscoreapply_nocontent, Toast.LENGTH_SHORT)
                            .show();
                } else if (recommendee.length() == 0) {
                    Toast.makeText(getContext(), R.string.rewardscoreapply_norecommendee, Toast.LENGTH_SHORT)
                            .show();
                } else {
                    apply(content, recommendee);
                }
            }
        });
    }

    /**
     * 데이터베이스에 상점추천 정보 기록
     * @param content 추천하는 이유
     * @param recommendee 추천인
     */
    private void apply(String content, String recommendee) {

    }

}
