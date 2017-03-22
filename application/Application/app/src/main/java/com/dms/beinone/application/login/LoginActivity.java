package com.dms.beinone.application.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.beinone.application.utils.Cookie;
import com.dms.beinone.application.utils.CookieManager;
import com.dms.beinone.application.utils.EditTextUtils;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Request;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BeINone on 2017-01-25.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText mIdET;
    private EditText mPasswordET;

    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPrefs = getSharedPreferences(getString(R.string.PREFS_ACCOUNT), MODE_PRIVATE);

        mIdET = (EditText) findViewById(R.id.et_login_id);
        mIdET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditTextUtils.hideKeyboard(LoginActivity.this, (EditText) v);
            }
        });

        mPasswordET = (EditText) findViewById(R.id.et_login_password);
        mPasswordET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditTextUtils.hideKeyboard(LoginActivity.this, (EditText) v);
            }
        });

        Button loginBtn = (Button) findViewById(R.id.btn_login_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = mIdET.getText().toString();
                String password = mPasswordET.getText().toString();
                if (id.equals("")) {
                    Toast.makeText(LoginActivity.this, R.string.login_noid, Toast.LENGTH_SHORT)
                            .show();
                } else if (password.equals("")) {
                    Toast.makeText(LoginActivity.this, R.string.login_nopassword, Toast.LENGTH_SHORT)
                            .show();
                } else {
                    new LoginTask().execute(id, password);
                }
            }
        });

        Button registerBtn = (Button) findViewById(R.id.btn_login_register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private class LoginTask extends AsyncTask<String, Void, Integer> {

        private CookieManager mCookieManager;
        private String mID;
        private String mPassword;

        public LoginTask() {
            mCookieManager = new CookieManager(LoginActivity.this);
        }

        @Override
        protected Integer doInBackground(String... account) {
            int code = -1;

            mID = account[0];
            mPassword = account[1];

            try {
                code = login(mID, mPassword);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

            return code;
        }

        @Override
        protected void onPostExecute(Integer code) {
            super.onPostExecute(code);

            if (code == 201) {
                // success
                Toast.makeText(LoginActivity.this,
                        mID + getString(R.string.login_success), Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = mPrefs.edit();
                editor.putString(getString(R.string.PREFS_ACCOUNT_ID), mID).apply();
                editor.putString(getString(R.string.PREFS_ACCOUNT_PASSWORD), mPassword).apply();
                finish();
            } else if (code == 400) {
                // failure
                Toast.makeText(LoginActivity.this, R.string.login_failure, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, R.string.login_error, Toast.LENGTH_SHORT).show();
            }
        }

//        @Override
//        protected void onPostExecute(Student student) {
//            super.onPostExecute(student);
//
//            if (student == null) {
//                // error
//                Toast.makeText(LoginActivity.this, R.string.login_error, Toast.LENGTH_SHORT).show();
//            } else if (student.getNumber() == 0) {
//                // failure
//                Toast.makeText(LoginActivity.this, R.string.login_failure, Toast.LENGTH_SHORT).show();
//            } else {
//                SharedPreferences.Editor editor = mPrefs.edit();
//                // success
//                editor.putString(getString(R.string.PREFS_ACCOUNT_ID), mID).apply();
//                editor.putString(getString(R.string.PREFS_ACCOUNT_PASSWORD), mPassword).apply();
//                editor.putInt(getString(R.string.PREFS_ACCOUNT_NUMBER), student.getNumber()).apply();
//                editor.putString(getString(R.string.PREFS_ACCOUNT_NAME), student.getName()).apply();
//                editor.putInt(getString(R.string.PREFS_ACCOUNT_MERIT), student.getMerit()).apply();
//                editor.putInt(getString(R.string.PREFS_ACCOUNT_DEMERIT), student.getDemerit()).apply();
//
//                Toast.makeText(LoginActivity.this,
//                        mID + getString(R.string.login_success), Toast.LENGTH_SHORT).show();
//
//                finish();
//            }
//        }

        private int login(String id, String password) throws IOException, JSONException {
            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("id", id);
            requestParams.put("password", password);

            Response response =
                    HttpBox.post(LoginActivity.this, "/app/account/login/student", Request.TYPE_POST)
                    .putBodyData(requestParams)
                    .push();

            Map<String, List<String>> headers = response.getHeaders();

            String[] cookieValue = mCookieManager.findCookieValue(headers, "vertx-web.session");
            if (cookieValue != null) {
                String key = cookieValue[0];
                String value = cookieValue[1];
                mCookieManager.addCookie(new Cookie(key, value));
            }

            cookieValue = mCookieManager.findCookieValue(headers, "UserSession");
            if (cookieValue != null) {
                String key = cookieValue[0];
                String value = cookieValue[1];
                mCookieManager.addCookie(new Cookie(key, value));
            }

            return response.getCode();
        }
    }

}
