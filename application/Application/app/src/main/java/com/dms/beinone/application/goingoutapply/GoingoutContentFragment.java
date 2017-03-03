package com.dms.beinone.application.goingoutapply;

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
 * Created by BeINone on 2017-02-24.
 */

public class GoingoutContentFragment extends Fragment {

    private EditText mReasonET;
    private TextView mReasonTV;

    public static GoingoutContentFragment newInstance(Context context, boolean date) {
        Bundle args = new Bundle();
        args.putBoolean(context.getString(R.string.ARGS_DATE), date);

        GoingoutContentFragment fragment = new GoingoutContentFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goingoutapply_content, container, false);
        init(view);

        return view;
    }

    /**
     * Initializes the edit text
     *
     * @param rootView view to find child views
     */
    private void init(View rootView) {
        mReasonTV = (TextView) rootView.findViewById(R.id.tv_goingoutcontent_reason);

        mReasonET = (DMSEditText) rootView.findViewById(R.id.et_goingoutcontent_reason);
        mReasonET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mReasonTV.setTextColor(
                            ContextCompat.getColor(getContext(), R.color.colorPrimary));
                } else {
                    mReasonTV.setTextColor(
                            ContextCompat.getColor(getContext(), android.R.color.primary_text_light));
                    // hide the soft keyboard when touch outside
                    EditTextUtils.hideKeyboard(getContext(), (EditText) v);
                }
            }
        });

        SharedPreferences prefs =
                getContext().getSharedPreferences(getString(R.string.PREFS_ACCOUNT), Context.MODE_PRIVATE);
        final String id = prefs.getString(getString(R.string.PREFS_ACCOUNT_ID), "");

        DMSButton applyBtn = (DMSButton) rootView.findViewById(R.id.btn_goingoutcontent_apply);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reason = mReasonET.getText().toString().trim();
                if (reason.length() == 0) {
                    Toast.makeText(getContext(), R.string.rewardscoreapply_nocontent, Toast.LENGTH_SHORT)
                            .show();
                } else {
                    boolean date = getArguments().getBoolean(getString(R.string.ARGS_DATE));
                    new ApplyGoingoutTask().execute(id, date, reason);
                }
            }
        });
    }

    private void clearView() {
        mReasonET.setText("");
    }

    private class ApplyGoingoutTask extends AsyncTask<Object, Void, Integer> {

        @Override
        protected Integer doInBackground(Object... params) {
            int code = -1;

            try {
                String id = params[0].toString();
                boolean date = (boolean) params[1];
                String reason = params[2].toString();

                code = applyGoingout(id, date, reason);
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

            if (code == 200) {
                // succeed
                Toast.makeText(getContext(), R.string.goingoutapply_apply_success, Toast.LENGTH_SHORT)
                        .show();
                clearView();
            } else if (code == 204) {
                // failed
                Toast.makeText(getContext(), R.string.goingoutapply_apply_failure, Toast.LENGTH_SHORT)
                        .show();
            } else {
                // error
                Toast.makeText(getContext(), R.string.goingoutapply_apply_error, Toast.LENGTH_SHORT)
                        .show();
            }
        }

        private int applyGoingout(String id, boolean date, String reason)
                throws IOException, JSONException {
            JSONObject requestJSONObject = new JSONObject();
            requestJSONObject.put("id", id);
            requestJSONObject.put("date", date);
            requestJSONObject.put("reason", reason);

            Response response = HttpBox.post()
                    .setCommand(Commands.APPLY_GOINGOUT)
                    .putBodyData(requestJSONObject)
                    .push();

            return response.getCode();
        }
    }

}
