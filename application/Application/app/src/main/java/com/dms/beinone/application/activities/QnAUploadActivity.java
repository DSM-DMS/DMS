package com.dms.beinone.application.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.beinone.application.models.QnA;
import com.dms.beinone.application.managers.EditTextManager;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by BeINone on 2017-02-13.
 */

public class QnAUploadActivity extends AppCompatActivity {

    private TextView mTitleTV;
    private EditText mTitleET;
    private TextView mContentTV;
    private EditText mContentET;
    private SwitchCompat mPrivacySwitch;
    private Button mSubmitBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_write);
        setTitle(R.string.title_write);

        // display back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTitleTV = (TextView) findViewById(R.id.tv_qna_write_title);
        mTitleET = (EditText) findViewById(R.id.et_qna_write_title);
        mTitleET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mTitleTV.setTextColor(
                            ContextCompat.getColor(QnAUploadActivity.this, R.color.colorPrimary));
                } else {
                    mTitleTV.setTextColor(
                            ContextCompat.getColor(QnAUploadActivity.this, android.R.color.primary_text_light));
                    // hide the soft keyboard when touch outside
                    EditTextManager.hideKeyboard(QnAUploadActivity.this, (EditText) v);
                }
            }
        });

        mContentTV = (TextView) findViewById(R.id.tv_qna_write_content);
        mContentET = (EditText) findViewById(R.id.et_qna_write_content);
        mContentET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mContentTV.setTextColor(
                            ContextCompat.getColor(QnAUploadActivity.this, R.color.colorPrimary));
                } else {
                    mContentTV.setTextColor(
                            ContextCompat.getColor(QnAUploadActivity.this, android.R.color.primary_text_light));
                    // hide the soft keyboard when touch outside
                    EditTextManager.hideKeyboard(QnAUploadActivity.this, (EditText) v);
                }
            }
        });

        mPrivacySwitch = (SwitchCompat) findViewById(R.id.switch_qna_write_privacy);

        mSubmitBtn = (Button) findViewById(R.id.btn_qna_write_submit);
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mTitleET.getText().toString().trim();
                String content = mContentET.getText().toString().trim();
                boolean privacy = mPrivacySwitch.isChecked();

                try {
                    uploadQnA(new QnA(title, content, privacy));
                } catch (IOException e) {
                    System.out.println("IOException in QnAUploadActivity: uploadQnA()");
                    e.printStackTrace();
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

    private void uploadQnA(QnA qna) throws IOException {
        try {
            JSONObject params = new JSONObject();
            params.put("title", qna.getTitle());
            params.put("content", qna.getQuestionContent());
            params.put("privacy", qna.isPrivacy());

            HttpBox.post(QnAUploadActivity.this, "/post/qna/question")
                    .putBodyData(params)
                    .push(new HttpBoxCallback() {
                        @Override
                        public void done(Response response) {
                            int code = response.getCode();
                            switch (code) {
                                case HttpBox.HTTP_CREATED:
                                    Toast.makeText(QnAUploadActivity.this, R.string.qna_write_created, Toast.LENGTH_SHORT).show();
                                    finish();
                                    break;
                                case HttpBox.HTTP_BAD_REQUEST:
                                    Toast.makeText(QnAUploadActivity.this, R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                    Toast.makeText(QnAUploadActivity.this, R.string.qna_write_internal_server_error, Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void err(Exception e) {
                            System.out.println("Error in QnAUploadActivity: POST /post/qna/question");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException in QnAUploadActivity: POST /post/qna/question");
            e.printStackTrace();
        }
    }
}
