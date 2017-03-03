package com.dms.beinone.application.facilityreport;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.dms.beinone.application.JSONParser;
import com.dms.beinone.application.R;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Commands;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BeINone on 2017-02-22.
 */

public class LoadFacilityReportListTask extends AsyncTask<Void, Void, Object[]> {

    private static final int LIMIT = 10;

    private Context mContext;
    private RecyclerView mRecyclerView;

    public LoadFacilityReportListTask(Context context, RecyclerView recyclerView) {
        mContext = context;
        mRecyclerView = recyclerView;
    }

    @Override
    protected Object[] doInBackground(Void... params) {
        Object[] results = null;

        try {
            results = loadFacilityReportList();
        } catch (IOException ie) {
            return null;
        } catch (JSONException je) {
            return null;
        }

        return results;
    }

    @Override
    protected void onPostExecute(Object[] results) {
        super.onPostExecute(results);

        if (results != null) {
            int code = (int) results[0];
            List<FacilityReport> facilityReportList = (ArrayList<FacilityReport>) results[1];

            if (code == 200) {
                // success
                mRecyclerView.setAdapter(new FacilityReportAdapter(mContext, facilityReportList));

                // increase the page number if completed loading successfully
                FacilityReportFragment.page++;
            } else if (code == 204) {
                // empty
                Toast.makeText(mContext, R.string.facilityreport_empty, Toast.LENGTH_SHORT).show();
            } else {
                // error
                Toast.makeText(mContext, R.string.facilityreport_error, Toast.LENGTH_SHORT).show();
            }
        } else {
            // error
            Toast.makeText(mContext, R.string.facilityreport_error, Toast.LENGTH_SHORT).show();
        }
    }

    private Object[] loadFacilityReportList() throws IOException, JSONException {
        JSONObject requestJSONObject = new JSONObject();
        requestJSONObject.put("page", FacilityReportFragment.page);
        requestJSONObject.put("limit", LIMIT);

        Response response = HttpBox.post()
                .setCommand(Commands.LOAD_REPORT_FACILITY_LIST)
                .putBodyData()
                .push();

        JSONObject responseJSONObject = response.getJsonObject();

        int code = response.getCode();
        List<FacilityReport> facilityReportList = null;
        if (code == 200) {
            facilityReportList = JSONParser.parseFacilityReportListJSON(responseJSONObject);
        }

        return new Object[] { code, facilityReportList };
    }

}