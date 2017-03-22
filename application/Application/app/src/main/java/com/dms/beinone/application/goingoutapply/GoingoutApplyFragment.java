package com.dms.beinone.application.goingoutapply;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.dms.beinone.application.R;
import com.dms.beinone.application.utils.JSONParser;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Request;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BeINone on 2017-03-20.
 */

public class GoingoutApplyFragment extends Fragment {

    private ToggleButton mSaturdayTB;
    private ToggleButton mSundayTB;
    private Button mApplyBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goingoutapply, container, false);
        init(view);

        return view;
    }

    /**
     * 초기화
     *
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        getActivity().setTitle(R.string.nav_goingoutapply);

        mSaturdayTB = (ToggleButton) rootView.findViewById(R.id.tb_goingoutapply_saturday);
        mSundayTB = (ToggleButton) rootView.findViewById(R.id.tb_goingoutapply_sunday);
        mApplyBtn = (Button) rootView.findViewById(R.id.btn_goingoutapply_apply);

        mApplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean sat = false;
                boolean sun = false;
                if (mSaturdayTB.isChecked()) sat = true;
                if (mSundayTB.isChecked()) sun = true;

                new ApplyGoingoutTask().execute(new Goingout(sat, sun));
            }
        });

        new LoadGoingoutApplyStatusTask().execute();
    }

    private void setApplyStatus(Goingout goingout) {
        mSaturdayTB.setChecked(goingout.isSat());
        mSundayTB.setChecked(goingout.isSun());
    }

    private class LoadGoingoutApplyStatusTask extends AsyncTask<Void, Void, Object[]> {

        @Override
        protected Object[] doInBackground(Void... params) {
            Object[] results = null;

            try {
                results = loadGoingoutApplyStatus();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

            return results;
        }

        @Override
        protected void onPostExecute(Object[] results) {
            super.onPostExecute(results);

            if (results != null) {
                int code = (int) results[0];
                Goingout goingout = (Goingout) results[1];

                if (code == 200) {
                    // success
                    setApplyStatus(goingout);
                } else if (code == 204) {
                    Toast.makeText(getContext(), R.string.goingoutapply_load_failure, Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(getContext(), R.string.goingoutapply_load_error, Toast.LENGTH_SHORT)
                            .show();
                }
            } else {
                Toast.makeText(getContext(), R.string.goingoutapply_load_error, Toast.LENGTH_SHORT)
                        .show();
            }
        }

        private Object[] loadGoingoutApplyStatus() throws IOException, JSONException {
            Response response = HttpBox.post(getContext(), "/apply/goingout", Request.TYPE_GET)
                    .push();

            JSONObject responseJSONObject = response.getJsonObject();
            Goingout goingout = JSONParser.parseGoingoutApplyStatusJSON(responseJSONObject);

            return new Object[]{response.getCode(), goingout};
        }
    }

    private class ApplyGoingoutTask extends AsyncTask<Goingout, Void, Object[]> {

        @Override
        protected Object[] doInBackground(Goingout... params) {
            Object[] results = null;

            try {
                boolean sat = params[0].isSat();
                boolean sun = params[0].isSun();
                results = applyGoingout(sat, sun);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

            return results;
        }

        @Override
        protected void onPostExecute(Object[] results) {
            super.onPostExecute(results);

            int code = (int) results[0];
            Goingout goingout = (Goingout) results[1];

            if (code == 201) {
                // succeed
                setApplyStatus(goingout);
                Toast.makeText(getContext(), R.string.goingoutapply_apply_success, Toast.LENGTH_SHORT)
                        .show();
            } else {
                // error
                Toast.makeText(getContext(), R.string.goingoutapply_apply_error, Toast.LENGTH_SHORT)
                        .show();
            }
        }

        private Object[] applyGoingout(boolean sat, boolean sun) throws IOException, JSONException {
            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("sat", String.valueOf(sat));
            requestParams.put("sun", String.valueOf(sun));

            Response response = HttpBox.post(getContext(), "/apply/goingout", Request.TYPE_PUT)
                    .putBodyData(requestParams)
                    .push();

            return new Object[]{response.getCode(), new Goingout(sat, sun)};
        }
    }

}
