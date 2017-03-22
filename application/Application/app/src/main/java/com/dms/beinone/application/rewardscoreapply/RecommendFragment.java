package com.dms.beinone.application.rewardscoreapply;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import com.dms.beinone.application.dmsview.DMSButton;
import com.dms.beinone.application.dmsview.DMSEditText;
import com.dms.beinone.application.utils.EditTextUtils;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Request;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BeINone on 2017-01-17.
 */

public class RecommendFragment extends Fragment {

    private TextView mContentTV;
    private EditText mContentET;
    private TextView mRecommendeeTV;
    private EditText mRecommendeeET;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rewardscoreapply_recommend, container, false);

        init(view);

        return view;
    }

    /**
     * 초기화, 내용 및 추천인 EditText 포커스 이벤트 설정, 신청 버튼 클릭 이벤트 설정
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
                    EditTextUtils.hideKeyboard(getContext(), (EditText) v);
                    mContentTV.setTextColor(
                            ContextCompat.getColor(getContext(), android.R.color.primary_text_light));
                }
            }
        });

        mRecommendeeTV = (TextView) rootView.findViewById(R.id.tv_recommend_recommendee);

        mRecommendeeET = (DMSEditText) rootView.findViewById(R.id.et_recommend_recommendee);
        mRecommendeeET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mRecommendeeTV.setTextColor(
                            ContextCompat.getColor(getContext(), R.color.colorPrimary));
                } else {
                    EditTextUtils.hideKeyboard(getContext(), (EditText) v);
                    mRecommendeeTV.setTextColor(
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
                String recommendee = mRecommendeeET.getText().toString().trim();
                if (content.length() == 0) {
                    Toast.makeText(getContext(), R.string.rewardscoreapply_nocontent, Toast.LENGTH_SHORT)
                            .show();
                } else if (recommendee.length() == 0) {
                    Toast.makeText(getContext(), R.string.rewardscoreapply_norecommendee, Toast.LENGTH_SHORT)
                            .show();
                } else {
                    new ApplyRecommendTask().execute(recommendee, content);
                }
            }
        });
    }

    private void clearView() {
        mRecommendeeET.setText("");
        mContentET.setText("");
    }

    private class ApplyRecommendTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            int code = 0;

            try {
                code = applyRecommend(params[0], params[1]);
            } catch (IOException e) {
                return -1;
            } catch (JSONException e) {
                return -1;
            }

            return code;
        }

        @Override
        protected void onPostExecute(Integer code) {
            super.onPostExecute(code);

            if (code == 201) {
                // succeed
                Toast.makeText(getContext(), R.string.rewardscoreapply_success, Toast.LENGTH_SHORT)
                        .show();
                clearView();
            } else if (code == 400) {
                // failed
                Toast.makeText(getContext(), R.string.rewardscoreapply_failure, Toast.LENGTH_SHORT)
                        .show();
            } else {
                // error
                Toast.makeText(getContext(), R.string.rewardscoreapply_error, Toast.LENGTH_SHORT)
                        .show();
            }
        }

        private int applyRecommend(String recommendee, String content)
                throws IOException, JSONException {

            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("target", recommendee);
            requestParams.put("content", content);

            Response response = HttpBox.post(getContext(), "/apply/merit", Request.TYPE_POST)
                    .putBodyData(requestParams)
                    .push();

            return response.getCode();
        }
    }

}
