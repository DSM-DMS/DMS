package com.dms.beinone.application.facilityreport;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
                    try {
                        uploadFacilityReport(new FacilityReport(title, content, Integer.valueOf(room)));
                    } catch (IOException e) {
                        System.out.println("IOException in FacilityReportUploadActivity: uploadFacilityReport()");
                        e.printStackTrace();
                    }
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

    private void uploadFacilityReport(FacilityReport facilityReport) throws IOException {
        try {
            JSONObject params = new JSONObject();
            params.put("title", facilityReport.getTitle());
            params.put("content", facilityReport.getContent());
            params.put("room", facilityReport.getRoom());

            HttpBox.post(FacilityReportUploadActivity.this, "/post/report")
                    .putBodyData(params)
                    .push(new HttpBoxCallback() {
                        @Override
                        public void done(Response response) {
                            int code = response.getCode();
                            switch (code) {
                                case HttpBox.HTTP_CREATED:
                                    Toast.makeText(FacilityReportUploadActivity.this, R.string.facilityreport_write_created, Toast.LENGTH_SHORT).show();
                                    finish();
                                    break;
                                case HttpBox.HTTP_BAD_REQUEST:
                                    Toast.makeText(FacilityReportUploadActivity.this, R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                    Toast.makeText(FacilityReportUploadActivity.this, R.string.facilityreport_write_internal_server_error, Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void err(Exception e) {
                            System.out.println("Error in FacilityReportUploadActivity: POST /post/report");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException in FacilityReportUploadActivity: POST /post/report");
            e.printStackTrace();
        }
    }
}
