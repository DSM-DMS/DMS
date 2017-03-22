package com.dms.beinone.application.login;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.beinone.application.utils.EditTextUtils;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Request;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BeINone on 2017-02-24.
 */

public class RegisterActivity extends AppCompatActivity {

    private static final int POS_TAB_MAN = 0;
    private static final int POS_TAB_WOMAN = 1;

    private EditText mUidET;
    private EditText mIdET;
    private EditText mPasswordET;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUidET = (EditText) findViewById(R.id.et_register_uid);
        mUidET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditTextUtils.hideKeyboard(RegisterActivity.this, (EditText) v);
            }
        });

        mIdET = (EditText) findViewById(R.id.et_register_id);
        mIdET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditTextUtils.hideKeyboard(RegisterActivity.this, (EditText) v);
            }
        });

        mPasswordET = (EditText) findViewById(R.id.et_register_password);
        mPasswordET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditTextUtils.hideKeyboard(RegisterActivity.this, (EditText) v);
            }
        });

        Button registerBtn = (Button) findViewById(R.id.btn_register_register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = mUidET.getText().toString().trim();
                String id = mIdET.getText().toString().trim();
                String password = mPasswordET.getText().toString().trim();

                if (uid.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, R.string.register_nouid,
                            Toast.LENGTH_SHORT).show();
                } else if (id.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, R.string.register_id,
                            Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, R.string.register_password,
                            Toast.LENGTH_SHORT).show();
                } else {
                    StudentAccount studentAccount = new StudentAccount(uid, id, password);
                    new RegisterStudentAccountTask().execute(studentAccount);
                }

            }
        });
    }

    private class RegisterStudentAccountTask extends AsyncTask<StudentAccount, Void, Integer> {

        @Override
        protected Integer doInBackground(StudentAccount... params) {
            int code = -1;

            try {
                code = registerStudentAccount(params[0]);
            } catch (IOException e) {
                return -1;
            } catch (JSONException e) {
                return -1;
            }

            return code;
        }

        @Override
        protected void onPostExecute(Integer code) {
            super.onPostExecute(code);

            if (code == 201) {
                // success
                Toast.makeText(RegisterActivity.this, R.string.register_success,
                        Toast.LENGTH_SHORT).show();
                finish();
            } else if (code == 409) {
                // conflict
                Toast.makeText(RegisterActivity.this, R.string.register_conflict,
                        Toast.LENGTH_SHORT).show();
            } else if (code == 404) {
                // empty
                Toast.makeText(RegisterActivity.this, R.string.register_failure,
                        Toast.LENGTH_SHORT).show();
            } else {
                // error
                Toast.makeText(RegisterActivity.this, R.string.register_error, Toast.LENGTH_SHORT)
                        .show();
            }
        }

        private int registerStudentAccount(StudentAccount studentAccount)
                throws IOException, JSONException {
            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("uid", studentAccount.getUid());
            requestParams.put("id", studentAccount.getId());
            requestParams.put("password", studentAccount.getPassword());

            Response response =
                    HttpBox.post(RegisterActivity.this, "/account/register/student", Request.TYPE_POST)
                    .putBodyData(requestParams)
                    .push();

            return response.getCode();
        }
    }

}
