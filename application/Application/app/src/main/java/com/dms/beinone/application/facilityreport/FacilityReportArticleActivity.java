package com.dms.beinone.application.facilityreport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.JSONParser;
import com.dms.beinone.application.R;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Commands;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by BeINone on 2017-01-23.
 */

public class FacilityReportArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilityreport_article);

        // display back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int no = getIntent().getIntExtra(getString(R.string.EXTRA_NO), 0);
        new LoadFacilityReportTask().execute(no);
    }

    /**
     * sets text of article
     * @param facilityReport FacilityReport object that contains information of article
     */
    private void bind(FacilityReport facilityReport) {
        TextView titleTV = (TextView) findViewById(R.id.tv_facilityreport_article_title);
        TextView writerTV = (TextView) findViewById(R.id.tv_facilityreport_article_writer);
        TextView dateTV = (TextView) findViewById(R.id.tv_facilityreport_article_writedate);
        TextView contentTV = (TextView) findViewById(R.id.tv_facilityreport_article_content);

        titleTV.setText(facilityReport.getTitle());
        writerTV.setText(facilityReport.getWriter());
        dateTV.setText(facilityReport.getWriteDate());
        contentTV.setText(facilityReport.getContent());
    }

    private class LoadFacilityReportTask extends AsyncTask<Integer, Void, FacilityReport> {

        @Override
        protected FacilityReport doInBackground(Integer... params) {
            FacilityReport facilityReport = null;

            try {
                facilityReport = loadFacilityReport(params[0]);
            } catch (IOException ie) {
                return null;
            } catch (JSONException je) {
                return null;
            }

            return facilityReport;
        }

        @Override
        protected void onPostExecute(FacilityReport facilityReport) {
            super.onPostExecute(facilityReport);

            if (facilityReport == null) {
                // error
                Toast.makeText(FacilityReportArticleActivity.this,
                        R.string.facilityreport_article_error, Toast.LENGTH_SHORT).show();
            } else {
                bind(facilityReport);
            }
        }

        private FacilityReport loadFacilityReport(int no) throws IOException, JSONException {
            JSONObject requestJSONObject = new JSONObject();
            requestJSONObject.put("no", no);
            Response response = HttpBox.post()
                    .setCommand(Commands.LOAD_REPORT_FACILITY)
                    .putBodyData(requestJSONObject)
                    .push();

            JSONObject responseJSONObject = response.getJsonObject();

            return JSONParser.parseFacilityReportJSON(responseJSONObject, no);
        }
    }

}
