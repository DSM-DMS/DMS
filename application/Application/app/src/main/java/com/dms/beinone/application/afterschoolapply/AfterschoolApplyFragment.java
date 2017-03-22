package com.dms.beinone.application.afterschoolapply;

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
 * Created by BeINone on 2017-01-31.
 */

public class AfterschoolApplyFragment extends Fragment {

    private EmptySupportedRecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_afterschoolapply, container, false);
        init(view);

        return view;
    }

    /**
     * 초기화, RecyclerView 세팅
     *
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        getActivity().setTitle(R.string.nav_afterschoolapply);

        mRecyclerView = (EmptySupportedRecyclerView) rootView.findViewById(R.id.rv_afterschoolapply);

        View emptyView = rootView.findViewById(R.id.view_afterschoolapply_empty);
        RecyclerViewUtils.setupCardRecyclerView(mRecyclerView, getContext(), emptyView);

        new LoadAfterschoolListTask().execute();
    }

    private class LoadAfterschoolListTask extends AsyncTask<Void, Void, Object[]> {

        @Override
        protected Object[] doInBackground(Void... params) {
            Object[] results = null;

            try {
                results = loadAfterschoolList();
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
                List<Afterschool> afterschoolList = (ArrayList<Afterschool>) results[1];

                if (code == 200) {
                    // success
                    mRecyclerView.setAdapter(new AfterschoolAdapter(getContext(), afterschoolList));
                } else if (code == 204) {
                    // empty
                    Toast.makeText(getContext(), R.string.afterschoolapply_list_empty, Toast.LENGTH_SHORT).show();
                } else {
                    // error
                    Toast.makeText(getContext(), R.string.afterschoolapply_list_error, Toast.LENGTH_SHORT).show();
                }
            } else {
                // error
                Toast.makeText(getContext(), R.string.afterschoolapply_list_error, Toast.LENGTH_SHORT).show();
            }
        }

        private Object[] loadAfterschoolList() throws IOException, JSONException {
            Response response = HttpBox.post(getContext(), "/apply/afterschool/item", Request.TYPE_GET).push();

            JSONObject responseJSONObject = response.getJsonObject();

            int code = response.getCode();
            List<Afterschool> afterschoolList = null;
            if (code == 200) {
                afterschoolList = JSONParser.parseAfterschoolListJSON(responseJSONObject);
            }

            return new Object[] { code, afterschoolList };
        }
    }

}
