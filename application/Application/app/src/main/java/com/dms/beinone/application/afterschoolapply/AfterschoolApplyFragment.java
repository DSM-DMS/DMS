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
import com.dms.beinone.application.JSONParser;
import com.dms.beinone.application.R;
import com.dms.beinone.application.RecyclerViewUtils;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Commands;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;

import java.io.IOException;
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

        mRecyclerView = (EmptySupportedRecyclerView) rootView.findViewById(R.id.rv_appcontent);

        View emptyView = rootView.findViewById(R.id.view_afterschoolapply_empty);
        RecyclerViewUtils.setupRecyclerView(mRecyclerView, getContext(), emptyView);

        new LoadAfterschoolListTask().execute();
    }

    private class LoadAfterschoolListTask extends AsyncTask<Void, Void, List<Afterschool>> {

        @Override
        protected List<Afterschool> doInBackground(Void... params) {
            List<Afterschool> afterschoolList = null;

            try {
                afterschoolList = loadAfterschoolList();
            } catch (IOException e) {
                return null;
            } catch (JSONException e) {
                return null;
            }

            return afterschoolList;
        }

        @Override
        protected void onPostExecute(List<Afterschool> afterschoolList) {
            super.onPostExecute(afterschoolList);

            if (afterschoolList == null) {
                Toast.makeText(getContext(), R.string.afterschoolapply_error, Toast.LENGTH_SHORT)
                        .show();
            } else {
                mRecyclerView.setAdapter(new AfterschoolAdapter(afterschoolList));
            }
        }

        private List<Afterschool> loadAfterschoolList() throws IOException, JSONException {
            Response response =
                    HttpBox.post().setCommand(Commands.LOAD_AFTERSCHOOL_LIST).putBodyData().push();

            return JSONParser.parseAfterschoolListJSON(response.getJsonObject());
        }
    }

}
