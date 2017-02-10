package com.dms.beinone.application.rewardscore;

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

import com.dms.beinone.application.EditTextUtils;
import com.dms.beinone.application.R;
import com.dms.beinone.application.dmsview.DMSButton;
import com.dms.beinone.application.dmsview.DMSEditText;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Commands;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by BeINone on 2017-01-17.
 */

public class RewardscoreFragment extends Fragment {

    private EditText mContentET;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rewardscoreapply_rewardscore, container, false);
        init(view);

        return view;
    }

    /**
     * Initializes the edit text
     *
     * @param rootView view to find child views
     */
    private void init(View rootView) {
        final TextView contentTV = (TextView) rootView.findViewById(R.id.tv_rewardscore_content);

        mContentET = (DMSEditText) rootView.findViewById(R.id.et_rewardscore_content);
        mContentET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        mContentET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // hide the soft keyboard when touch outside
                    EditTextUtils.hideKeyboard(getContext(), (EditText) v);
                }
            }
        });

        SharedPreferences prefs =
                getContext().getSharedPreferences(getString(R.string.PREFS_ACCOUNT), Context.MODE_PRIVATE);
        final String id = prefs.getString(getString(R.string.PREFS_ACCOUNT_ID), "");

        DMSButton applyBtn = (DMSButton) rootView.findViewById(R.id.btn_rewardscore_apply);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mContentET.getText().toString().trim();
                if (content.length() == 0) {
                    Toast.makeText(getContext(), R.string.rewardscoreapply_nocontent, Toast.LENGTH_SHORT)
                            .show();
                } else {
                    new ApplyRewardscoreTask().execute(id, content);
                }
            }
        });
    }

    private void clear() {
        mContentET.setText(null);
    }

    private class ApplyRewardscoreTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            int status = 0;

            try {
                status = applyRewardscore(params[0], params[1]);
            } catch (IOException e) {
                return -1;
            } catch (JSONException e) {
                return -1;
            }

            return status;
        }

        @Override
        protected void onPostExecute(Integer status) {
            super.onPostExecute(status);

            if (status > 0) {
                // success
                Toast.makeText(getContext(), R.string.rewardscoreapply_success, Toast.LENGTH_SHORT)
                        .show();
                clear();
            } else {
                // failure
                Toast.makeText(getContext(), R.string.rewardscoreapply_failure, Toast.LENGTH_SHORT)
                        .show();
            }
        }

        private int applyRewardscore(String id, String content)
                throws IOException, JSONException {

            JSONObject requestJSONObject = new JSONObject();
            requestJSONObject.put("id", id);
            requestJSONObject.put("content", content);
            Response response = HttpBox.post()
                    .setCommand(Commands.APPLY_MERIT)
                    .putBodyData(requestJSONObject)
                    .push();

            JSONObject responseJSONObject = response.getJsonObject();
            return responseJSONObject.getInt("status");
        }
    }

}
