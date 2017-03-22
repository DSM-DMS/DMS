package com.dms.beinone.application.rule;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dms.beinone.application.EmptySupportedRecyclerView;
import com.dms.beinone.application.R;
import com.dms.beinone.application.utils.JSONParser;
import com.dms.beinone.application.utils.RecyclerViewUtils;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Request;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BeINone on 2017-01-20.
 */

public class RuleFragment extends Fragment {

    private EmptySupportedRecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rule, container, false);

        init(view);

        return view;
    }

    /**
     * 초기화
     *
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        getActivity().setTitle(R.string.nav_rule);

        mRecyclerView = (EmptySupportedRecyclerView) rootView.findViewById(R.id.rv_rule);

        View emptyView = rootView.findViewById(R.id.view_rule_empty);
        RecyclerViewUtils.setupRecyclerView(mRecyclerView, getContext(), emptyView);

        new LoadRuleTask().execute();
    }

    private class LoadRuleTask extends AsyncTask<Void, Void, Object[]> {

        @Override
        protected Object[] doInBackground(Void... params) {
            Object[] results = null;

            try {
                results = loadRule();
            } catch (IOException e) {
                return null;
            } catch (JSONException e) {
                return null;
            }

            return results;
        }

        @Override
        protected void onPostExecute(Object[] results) {
            super.onPostExecute(results);

            if (results != null) {
                int code = (int) results[0];
                List<Rule> ruleList = (ArrayList<Rule>) results[1];

                if (code == 200) {
                    // success
                    mRecyclerView.setAdapter(new RuleAdapter(getContext(), ruleList));
                } else if (code == 204) {
                    // no data
                    Toast.makeText(getContext(), R.string.rule_nodata, Toast.LENGTH_SHORT).show();
                } else {
                    // error
                    Toast.makeText(getContext(), R.string.rule_error, Toast.LENGTH_SHORT).show();
                }
            } else {
                // error
                Toast.makeText(getContext(), R.string.rule_error, Toast.LENGTH_SHORT).show();
            }
        }

        private Object[] loadRule() throws IOException, JSONException {
            Response response = HttpBox.post(getContext(), "/post/rule", Request.TYPE_GET).push();
            JSONObject responseJSONObject = response.getJsonObject();

            int code = response.getCode();

            List<Rule> ruleList = null;
            if (code == 200) {
                ruleList = JSONParser.parseRuleJSON(responseJSONObject);
            }

            return new Object[] { code, ruleList };
        }
    }

}
