package com.dms.beinone.application.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.beinone.application.utils.DateUtils;
import com.dms.beinone.application.utils.StayApplyUtils;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

/**
 * Created by BeINone on 2017-05-31.
 */

public class StayActivity extends AppCompatActivity {

    public static final int FRIDAY_GO = 1;
    public static final int SATURDAY_GO = 2;
    public static final int SATURDAY_COME = 3;
    public static final int STAY = 4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stay);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView titleTV = (TextView) findViewById(R.id.tv_toolbar_title);
        titleTV.setText(R.string.stay);

        ImageButton backIB = (ImageButton) findViewById(R.id.ib_toolbar_back);
        backIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button applyBtn = (Button) findViewById(R.id.btn_stay_apply);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rg_stay);
                int value = 0;
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rb_stay_friday_go:
                        value = FRIDAY_GO;
                        break;
                    case R.id.rb_stay_saturday_go:
                        value = SATURDAY_GO;
                        break;
                    case R.id.rb_stay_saturday_come:
                        value = SATURDAY_COME;
                        break;
                    case R.id.rb_stay_stay:
                        value = STAY;
                        break;
                    default:
                        break;
                }

                try {
                    apply(value);
                } catch (IOException e) {
                    System.out.println("IOException in StayActivity: PUT /apply/stay");
                    e.printStackTrace();
                }
            }
        });
    }

    private void apply(final int value) throws IOException {
        try {
            JSONObject params = new JSONObject();
            params.put("value", value);
            params.put("week", DateUtils.dateToWeekDateString(new Date()));

            HttpBox.put(StayActivity.this, "/apply/stay")
                    .putBodyData(params)
                    .push(new HttpBoxCallback() {
                        @Override
                        public void done(Response response) {
                            int code = response.getCode();
                            switch (code) {
                                case HttpBox.HTTP_OK:
                                    Toast.makeText(StayActivity.this, R.string.stay_apply_ok, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_NO_CONTENT:
                                    Toast.makeText(StayActivity.this, R.string.stay_apply_no_content, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_BAD_REQUEST:
                                    Toast.makeText(StayActivity.this, R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                    Toast.makeText(StayActivity.this, R.string.stay_apply_internal_server_error, Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void err(Exception e) {
                            System.out.println("Error in StayActivity: PUT /apply/stay");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException in StayActivity: PUT /apply/stay");
            e.printStackTrace();
        }
    }
}
