package com.dms.beinone.application.login;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by BeINone on 2017-01-25.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText mIdET;
    private EditText mPasswordET;

    private SharedPreferences mPrefs;
    private CookieManager mCookieManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPrefs = getSharedPreferences(getString(R.string.PREFS_ACCOUNT), MODE_PRIVATE);
        mCookieManager = new CookieManager(this);

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
                    try {
                        login(id, password);
                    } catch (IOException e) {
                        System.out.println("IOException in LoginActivity: login()");
                        e.printStackTrace();
                    }
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

    private void login(String id, String password) throws IOException {
        try {
            JSONObject params = new JSONObject();
            params.put("id", id);
            params.put("password", password);

            HttpBox.post(LoginActivity.this, "/app/account/login/student")
                    .putBodyData(params)
                    .push(new HttpBoxCallback() {
                        @Override
                        public void done(Response response) {
                            int code = response.getCode();
                            switch (code) {
                                case HttpBox.HTTP_CREATED:
                                    Map<String, List<String>> headers = response.getHeaders();
                                    addLoginInfoCookies(headers);
                            }
                        }

                        @Override
                        public void err(Exception e) {
                            System.out.println("Error in LoginActivity: POST /app/account/login/student");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException in LoginActivity: POST /app/account/login/student");
            e.printStackTrace();
        }
    }

    private void addLoginInfoCookies(Map<String, List<String>> headers) {
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
    }
}
