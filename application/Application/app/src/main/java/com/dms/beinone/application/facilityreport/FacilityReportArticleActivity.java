package com.dms.beinone.application.facilityreport;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.beinone.application.utils.JSONParser;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Request;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

        // display back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccountPrefs = getSharedPreferences(getString(R.string.PREFS_ACCOUNT), MODE_PRIVATE);
        mFacilityReport = getIntent().getParcelableExtra(getString(R.string.EXTRA_FACILITYREPORT));

        new LoadFacilityReportTask().execute(mFacilityReport.getNo());
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
            DeleteFacilityReportDialog.newInstance(this, mFacilityReport.getNo());
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

    private class LoadFacilityReportTask extends AsyncTask<Integer, Void, FacilityReport> {

        @Override
        protected FacilityReport doInBackground(Integer... params) {
            FacilityReport facilityReport = null;

            try {
                facilityReport = loadFacilityReport(params[0]);
            } catch (IOException e) {
                return null;
            } catch (JSONException e) {
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
            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("no", String.valueOf(no));

            Response response =
                    HttpBox.post(FacilityReportArticleActivity.this, "/post/report", Request.TYPE_GET)
                    .putBodyData(requestParams)
                    .push();

            JSONObject responseJSONObject = response.getJsonObject();

            return JSONParser.parseFacilityReportJSON(responseJSONObject, no);
        }
    }

}
