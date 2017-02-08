package com.dms.beinone.application.facilityreport;

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

        Button submitBtn = (Button) findViewById(R.id.btn_facilityreport_write_submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    private class UploadFacilityReportTask extends AsyncTask<FacilityReport, Void, Boolean> {

        @Override
        protected Boolean doInBackground(FacilityReport... params) {
            boolean status = false;

            try {
                status = uploadFacilityReport(params[0]);
            } catch (IOException ie) {
                return null;
            } catch (JSONException je) {
                return null;
            }

            return status;
        }

        @Override
        protected void onPostExecute(Boolean status) {
            super.onPostExecute(status);

            if (status == null) {
                // error
                Toast.makeText(FacilityReportWriteActivity.this,
                        R.string.facilityreport_write_error, Toast.LENGTH_SHORT).show();
            } else if (status) {
                // success
                Toast.makeText(FacilityReportWriteActivity.this,
                        R.string.facilityreport_write_success, Toast.LENGTH_SHORT).show();
                finish();
            } else {
                // failure
                Toast.makeText(FacilityReportWriteActivity.this,
                        R.string.facilityreport_write_failure, Toast.LENGTH_SHORT).show();
            }
        }

        private boolean uploadFacilityReport(FacilityReport facilityReport)
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

            JSONObject responseJSONObject = response.getJsonObject();

            return responseJSONObject.getInt("status") != 0;
        }
    }

}
