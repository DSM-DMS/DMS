package com.dms.beinone.application.facilityreport;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dms.beinone.application.EmptySupportedRecyclerView;
import com.dms.beinone.application.R;
import com.dms.beinone.application.RecyclerViewUtils;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Commands;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BeINone on 2017-01-20.
 */

public class FacilityReportFragment extends Fragment {

    private FloatingActionButton mFAB;
    private EmptySupportedRecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facilityreport, container, false);
        init(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        new LoadFacilityReportListTask().execute();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mFAB.setVisibility(View.INVISIBLE);
    }

    /**
     * 초기화, FloatingActionButton 나타내기, RecyclerView 세팅
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        getActivity().setTitle(R.string.nav_facilityreport);

        mFAB = (FloatingActionButton) getActivity().findViewById(R.id.fab_main);
        mFAB.setVisibility(View.VISIBLE);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FacilityReportWriteActivity.class));
            }
        });

        mRecyclerView = (EmptySupportedRecyclerView) rootView.findViewById(R.id.rv_facilityreport);

        View emptyView = rootView.findViewById(R.id.view_facilityreport_empty);
        RecyclerViewUtils.setupRecyclerView(mRecyclerView, getContext(), emptyView);
    }

    private class LoadFacilityReportListTask extends AsyncTask<Void, Void, List<FacilityReport>> {

        @Override
        protected List<FacilityReport> doInBackground(Void... params) {
            List<FacilityReport> facilityReportList = null;

            try {
                facilityReportList = loadFacilityReportList();
            } catch (IOException ie) {
                return null;
            } catch (JSONException je) {
                return null;
            }

            return facilityReportList;
        }

        @Override
        protected void onPostExecute(List<FacilityReport> facilityReportList) {
            super.onPostExecute(facilityReportList);

            if (facilityReportList == null) {
                Toast.makeText(getContext(), R.string.facilityreport_error, Toast.LENGTH_SHORT)
                        .show();
            } else {
                mRecyclerView.setAdapter(
                        new FacilityReportAdapter(getContext(), facilityReportList));
            }
        }

        private List<FacilityReport> loadFacilityReportList() throws IOException, JSONException {
            List<FacilityReport> facilityReportList = new ArrayList<>();

            Response response =
                    HttpBox.post().
                            setCommand(Commands.LOAD_REPORT_FACILITY_LIST)
                            .putBodyData()
                            .push();

            JSONObject responseJSONObject = response.getJsonObject();

            JSONArray resultJSONArray = responseJSONObject.getJSONArray("result");
            for (int index = 0; index < resultJSONArray.length(); index++) {
                JSONObject facilityReportJSONObject = resultJSONArray.getJSONObject(index);

                int no = facilityReportJSONObject.getInt("no");
                String title = facilityReportJSONObject.getString("title");
                int room = facilityReportJSONObject.getInt("room");
                String writeDate = facilityReportJSONObject.getString("write_date");
                String writer = facilityReportJSONObject.getString("writer");
                boolean hasResult = facilityReportJSONObject.getBoolean("has_result");

                facilityReportList.add(
                        new FacilityReport(no, title, room, writeDate, writer, hasResult));
            }

            return facilityReportList;
        }
    }

}
