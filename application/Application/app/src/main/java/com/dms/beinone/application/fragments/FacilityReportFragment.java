package com.dms.beinone.application.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dms.beinone.application.views.custom.EmptySupportedRecyclerView;
import com.dms.beinone.application.OnMoreBtnClickListener;
import com.dms.beinone.application.R;
import com.dms.beinone.application.views.adapters.FacilityReportAdapter;
import com.dms.beinone.application.activities.FacilityReportUploadActivity;
import com.dms.beinone.application.models.FacilityReport;
import com.dms.beinone.application.utils.JSONParser;
import com.dms.beinone.application.managers.RecyclerViewManager;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by BeINone on 2017-01-20.
 */

public class FacilityReportFragment extends Fragment {

    public static final int LIMIT = 10;

    public static int page = 1;

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
    public void onStart() {
        super.onStart();
        mFAB.setVisibility(View.VISIBLE);

        try {
            loadFacilityReportList();
        } catch (IOException e) {
            System.out.println("IOException in FacilityReportFragment: loadFacilityReportList()");
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mFAB.setVisibility(View.INVISIBLE);

        page = 1;
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
                startActivity(new Intent(getContext(), FacilityReportUploadActivity.class));
            }
        });

        mRecyclerView = (EmptySupportedRecyclerView) rootView.findViewById(R.id.rv_facilityreport);

        View emptyView = rootView.findViewById(R.id.view_facilityreport_empty);
        RecyclerViewManager.setupRecyclerView(mRecyclerView, getContext(), emptyView);
    }

    private void loadFacilityReportList() throws IOException {
        try {
            JSONObject params = new JSONObject();
            params.put("page", FacilityReportFragment.page);
            params.put("limit", LIMIT);

            HttpBox.get(getContext(), "/post/report/list")
                    .putQueryString(params)
                    .push(new HttpBoxCallback() {
                        @Override
                        public void done(Response response) {
                            int code = response.getCode();
                            switch (code) {
                                case HttpBox.HTTP_OK:
                                    try {
                                        List<FacilityReport> facilityReports =
                                                JSONParser.parseFacilityReportListJSON(response.getJsonObject());
                                        mRecyclerView.setAdapter(new FacilityReportAdapter(getContext(), facilityReports, new OnMoreBtnClickListener() {
                                            @Override
                                            public void onMoreBtnClick() {
                                                try {
                                                    loadFacilityReportList();
                                                } catch (IOException e) {
                                                    System.out.println("IOException in FacilityReportFragment: loadFacilityReportList()");
                                                    e.printStackTrace();
                                                }
                                            }
                                        }));
                                        // increase the page number if completed loading successfully
                                        FacilityReportFragment.page++;
                                    } catch (JSONException e) {
                                        System.out.println("JSONException in FacilityReportFragment: /post/report/list");
                                        e.printStackTrace();
                                    }
                                    break;
                                case HttpBox.HTTP_NO_CONTENT:
//                                    Toast.makeText(getContext(), R.string.facilityreport_list_no_content, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                    Toast.makeText(getContext(), R.string.facilityreport_list_internal_server_error, Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void err(Exception e) {
                            System.out.println("Error in FacilityReportFragment: /post/report/list");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException in FacilityReportFragment: /post/report/list");
            e.printStackTrace();
        }
    }

}
