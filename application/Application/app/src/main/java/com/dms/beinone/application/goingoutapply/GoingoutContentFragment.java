package com.dms.beinone.application.goingoutapply;

import android.content.Context;
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
 * Created by BeINone on 2017-02-24.
 */

public class GoingoutContentFragment extends Fragment {

    public static final String SATURDAY = "saturday";
    public static final String SUNDAY = "sunday";

    private EditText mReasonET;
    private TextView mReasonTV;

    public static GoingoutContentFragment newInstance(Context context, String date) {
        Bundle args = new Bundle();
        args.putString(context.getString(R.string.ARGS_DATE), date);

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

        DMSButton applyBtn = (DMSButton) rootView.findViewById(R.id.btn_goingoutcontent_apply);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reason = mReasonET.getText().toString().trim();
                if (reason.length() == 0) {
                    Toast.makeText(getContext(), R.string.rewardscoreapply_nocontent, Toast.LENGTH_SHORT)
                            .show();
                } else {
                    String date = getArguments().getString(getString(R.string.ARGS_DATE));
                    new ApplyGoingoutTask().execute(date, reason);
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
                String date = params[0].toString();
                boolean sat = false;
                boolean sun = false;
                if (date.equals(SATURDAY)) sat = true;
                else sun = true;

                code = applyGoingout(sat, sun);
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            } catch (JSONException e) {
                e.printStackTrace();
                return -1;
            }

            return code;
        }

        @Override
        protected void onPostExecute(Integer code) {
            super.onPostExecute(code);

            if (code == 201) {
                // succeed
                Toast.makeText(getContext(), R.string.goingoutapply_apply_success, Toast.LENGTH_SHORT)
                        .show();
                clearView();
            } else {
                // error
                Toast.makeText(getContext(), R.string.goingoutapply_apply_error, Toast.LENGTH_SHORT)
                        .show();
            }
        }

        private int applyGoingout(boolean sat, boolean sun)
                throws IOException, JSONException {
            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("sat", String.valueOf(sat));
            requestParams.put("sun", String.valueOf(sun));

            Response response = HttpBox.post(getContext(), "/apply/goingout", Request.TYPE_PUT)
                    .putBodyData(requestParams)
                    .push();

            return response.getCode();
        }
    }

}
