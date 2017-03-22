package com.dms.beinone.application.facilityreport;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Request;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BeINone on 2017-01-23.
 */

public class FacilityReportUploadActivity extends AppCompatActivity {

    private FacilityReportWriteFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilityreport_upload);
        setTitle(R.string.title_write);

        // display back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFragment = new FacilityReportWriteFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_facilityreport_upload_container, mFragment)
                .commit();

        SharedPreferences prefs =
                getSharedPreferences(getString(R.string.PREFS_ACCOUNT), MODE_PRIVATE);
        final String writer = prefs.getString(getString(R.string.PREFS_ACCOUNT_ID), "");

        Button submitBtn = (Button) findViewById(R.id.btn_facilityreport_upload_submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mFragment.getTitle();
                String room = mFragment.getRoom();
                String content = mFragment.getContent();

                if (title.isEmpty()) {
                    Toast.makeText(FacilityReportUploadActivity.this, R.string.facilityreport_notitle,
                            Toast.LENGTH_SHORT).show();
                } else if (room.isEmpty()) {
                    Toast.makeText(FacilityReportUploadActivity.this, R.string.facilityreport_noroom,
                            Toast.LENGTH_SHORT).show();
                } else if (content.isEmpty()) {
                    Toast.makeText(FacilityReportUploadActivity.this, R.string.facilityreport_nocontent,
                            Toast.LENGTH_SHORT).show();
                } else {
                    // upload the facility report if there is no blank
                    new UploadFacilityReportTask()
                            .execute(new FacilityReport(title, content, Integer.valueOf(room), writer));
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return false;
    }

    private class UploadFacilityReportTask extends AsyncTask<FacilityReport, Void, Integer> {

        @Override
        protected Integer doInBackground(FacilityReport... params) {
            int code = -1;

            try {
                code = uploadFacilityReport(params[0]);
            } catch (IOException ie) {
                return -1;
            } catch (JSONException je) {
                return -1;
            }

            return code;
        }

        @Override
        protected void onPostExecute(Integer code) {
            super.onPostExecute(code);

            if (code == 201) {
                // success
                Toast.makeText(FacilityReportUploadActivity.this,
                        R.string.facilityreport_write_success, Toast.LENGTH_SHORT).show();
                finish();
            } else if (code == 500) {
                // failure
                Toast.makeText(FacilityReportUploadActivity.this,
                        R.string.facilityreport_write_failure, Toast.LENGTH_SHORT).show();
            } else {
                // error
                Toast.makeText(FacilityReportUploadActivity.this,
                        R.string.facilityreport_write_error, Toast.LENGTH_SHORT).show();
            }
        }

        private int uploadFacilityReport(FacilityReport facilityReport)
                throws IOException, JSONException {

            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("title", facilityReport.getTitle());
            requestParams.put("content", facilityReport.getContent());
            requestParams.put("room", String.valueOf(facilityReport.getRoom()));
            requestParams.put("writer", String.valueOf(facilityReport.getWriter()));

            Response response =
                    HttpBox.post(FacilityReportUploadActivity.this, "/post/report", Request.TYPE_POST)
                    .putBodyData(requestParams)
                    .push();

            return response.getCode();
        }
    }

}
