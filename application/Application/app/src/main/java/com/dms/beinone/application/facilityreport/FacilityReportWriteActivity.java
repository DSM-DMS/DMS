package com.dms.beinone.application.facilityreport;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.EditTextUtils;
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

public class FacilityReportWriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilityreport_write);

        // display back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences prefs =
                getSharedPreferences(getString(R.string.PREFS_ACCOUNT), MODE_PRIVATE);
        final String writer = prefs.getString(getString(R.string.PREFS_ACCOUNT_ID), "");

        final TextView titleTV = (TextView) findViewById(R.id.tv_facilityreport_write_title);
        final EditText titleET = (EditText) findViewById(R.id.et_facilityreport_write_title);
        titleET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    titleTV.setTextColor(
                            ContextCompat.getColor(FacilityReportWriteActivity.this, R.color.colorPrimary));
                } else {
                    titleTV.setTextColor(
                            ContextCompat.getColor(FacilityReportWriteActivity.this, android.R.color.primary_text_light));
                    // hide the soft keyboard when touch outside
                    EditTextUtils.hideKeyboard(FacilityReportWriteActivity.this, (EditText) v);
                }
            }
        });

        final TextView roomTV = (TextView) findViewById(R.id.tv_facilityreport_write_room);
        final EditText roomET = (EditText) findViewById(R.id.et_facilityreport_write_room);
        roomET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    roomTV.setTextColor(
                            ContextCompat.getColor(FacilityReportWriteActivity.this, R.color.colorPrimary));
                } else {
                    roomTV.setTextColor(
                            ContextCompat.getColor(FacilityReportWriteActivity.this, android.R.color.primary_text_light));
                    // hide the soft keyboard when touch outside
                    EditTextUtils.hideKeyboard(FacilityReportWriteActivity.this, (EditText) v);
                }
            }
        });

        final TextView contentTV = (TextView) findViewById(R.id.tv_facilityreport_write_content);
        final EditText contentET = (EditText) findViewById(R.id.et_facilityreport_write_content);
        contentET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    contentTV.setTextColor(
                            ContextCompat.getColor(FacilityReportWriteActivity.this, R.color.colorPrimary));
                } else {
                    contentTV.setTextColor(
                            ContextCompat.getColor(FacilityReportWriteActivity.this, android.R.color.primary_text_light));
                    // hide the soft keyboard when touch outside
                    EditTextUtils.hideKeyboard(FacilityReportWriteActivity.this, (EditText) v);
                }
            }
        });

        Button submitBtn = (Button) findViewById(R.id.btn_facilityreport_write_submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleET.getText().toString().trim();
                int room = Integer.valueOf(roomET.getText().toString().trim());
                String content = contentET.getText().toString().trim();

                new UploadFacilityReportTask().execute(new FacilityReport(title, content, room, writer));
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

            if (code == 200) {
                // success
                Toast.makeText(FacilityReportWriteActivity.this,
                        R.string.facilityreport_write_success, Toast.LENGTH_SHORT).show();
                finish();
            } else if (code == 500) {
                // failure
                Toast.makeText(FacilityReportWriteActivity.this,
                        R.string.facilityreport_write_failure, Toast.LENGTH_SHORT).show();
            } else {
                // error
                Toast.makeText(FacilityReportWriteActivity.this,
                        R.string.facilityreport_write_error, Toast.LENGTH_SHORT).show();
            }
        }

        private int uploadFacilityReport(FacilityReport facilityReport)
                throws IOException, JSONException {

            JSONObject requestJSONObject = new JSONObject();
            requestJSONObject.put("title", facilityReport.getTitle());
            requestJSONObject.put("content", facilityReport.getContent());
            requestJSONObject.put("room", facilityReport.getRoom());
            requestJSONObject.put("writer", facilityReport.getWriter());

            Response response = HttpBox.post()
                    .setCommand(Commands.UPLOAD_REPORT_FACILITY)
                    .putBodyData(requestJSONObject)
                    .push();

            return response.getCode();
        }
    }

}
