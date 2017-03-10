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
import com.dms.beinone.application.JSONParser;
import com.dms.beinone.application.R;
import com.dms.beinone.application.RecyclerViewUtils;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Commands;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        getActivity().setTitle(R.string.nav_rule);

        mRecyclerView = (EmptySupportedRecyclerView) rootView.findViewById(R.id.rv_rule);

        View emptyView = rootView.findViewById(R.id.view_rule_empty);
        RecyclerViewUtils.setupRecyclerView(mRecyclerView, getContext(), emptyView);

        new LoadRuleTask().execute();
    }

    private class LoadRuleTask extends AsyncTask<Void, Void, List<Rule>> {

        @Override
        protected List<Rule> doInBackground(Void... params) {
            List<Rule> ruleList = null;

            try {
                ruleList = loadRule();
            } catch (IOException ie) {
                return null;
            } catch (JSONException je) {
                return null;
            }

            return ruleList;
        }

        @Override
        protected void onPostExecute(List<Rule> ruleList) {
            super.onPostExecute(ruleList);

            if (ruleList == null) {
                Toast.makeText(getContext(), R.string.rule_error, Toast.LENGTH_SHORT).show();
            } else {
                mRecyclerView.setAdapter(new RuleAdapter(getContext(), ruleList));
            }
        }

        private List<Rule> loadRule() throws IOException, JSONException {
            Response response = HttpBox.post().setCommand(Commands.LOAD_RULE).putBodyData().push();
            JSONObject responseJSONObject = response.getJsonObject();

            return JSONParser.parseRuleJSON(responseJSONObject);
        }
    }

}
