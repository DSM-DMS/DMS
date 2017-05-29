package com.dms.beinone.application.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.beinone.application.dialogs.DeleteFacilityReportDialogFragment;
import com.dms.beinone.application.models.FacilityReport;
import com.dms.beinone.application.utils.JSONParser;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by BeINone on 2017-01-23.
 */

public class FacilityReportArticleActivity extends AppCompatActivity {

    private SharedPreferences mAccountPrefs;
    private FacilityReport mFacilityReport;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilityreport_article);
        setTitle(R.string.nav_facilityreport);
        // display back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccountPrefs = getSharedPreferences(getString(R.string.PREFS_ACCOUNT), MODE_PRIVATE);
        mFacilityReport = getIntent().getParcelableExtra(getString(R.string.EXTRA_FACILITYREPORT));

        try {
            loadFacilityReport(mFacilityReport.getNo());
        } catch (IOException e) {
            System.out.println("IOException in FacilityReportArticleActivity: loadFacilityReport()");
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String id = mAccountPrefs.getString(getString(R.string.PREFS_ACCOUNT_ID), "");
        String writer = getIntent().getStringExtra(getString(R.string.EXTRA_WRITER));

        if (id.equals(writer)) {
            // show delete menu if writer's id and the user's id are same
            getMenuInflater().inflate(R.menu.activity_article, menu);
            return true;
        } else {
            return super.onCreateOptionsMenu(menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.menu_edit) {
            Intent intent = new Intent(this, FacilityReportUploadActivity.class);
            intent.putExtra(getString(R.string.EXTRA_FACILITYREPORT), mFacilityReport);
            startActivity(intent);
        } else if (item.getItemId() == R.id.menu_delete) {
            DeleteFacilityReportDialogFragment.newInstance(this, mFacilityReport.getNo());
        }

        return false;
    }

    /**
     * sets text of article
     *
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

    private void loadFacilityReport(final int no) throws IOException {
        try {
            JSONObject params = new JSONObject();
            params.put("no", no);

            HttpBox.get(FacilityReportArticleActivity.this, "/post/report")
                    .putQueryString(params)
                    .push(new HttpBoxCallback() {
                        @Override
                        public void done(Response response) {
                            int code = response.getCode();
                            try {
                                FacilityReport facilityReport = JSONParser.parseFacilityReportJSON(response.getJsonObject(), no);
                                switch (code) {
                                    case HttpBox.HTTP_OK:
                                        bind(facilityReport);
                                        break;
                                    case HttpBox.HTTP_NO_CONTENT:
                                        Toast.makeText(FacilityReportArticleActivity.this, R.string.facilityreport_article_no_content, Toast.LENGTH_SHORT).show();
                                        break;
                                    case HttpBox.HTTP_BAD_REQUEST:
                                        Toast.makeText(FacilityReportArticleActivity.this, R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                                        break;
                                    case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                        Toast.makeText(FacilityReportArticleActivity.this, R.string.facilityreport_article_internal_server_error, Toast.LENGTH_SHORT).show();
                                        break;
                                    default:
                                        break;
                                }
                            } catch (JSONException e) {
                                System.out.println("JSONException in FacilityReportArticleActivity: GET /post/report");
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void err(Exception e) {
                            System.out.println("Error in FacilityReportArticleActivity: GET /post/report");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException in FacilityReportArticleActivity: GET /post/report");
            e.printStackTrace();
        }
    }
}
