package com.dms.beinone.application.login;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    }

    private class LoginTask extends AsyncTask<String, Void, Boolean> {

        private String mID;
        private String mPassword;

        @Override
        protected Boolean doInBackground(String... account) {
            mID = account[0];
            mPassword = account[1];

            boolean permit = false;

            try {
                permit = login(mID, mPassword);
            } catch (IOException ie) {
                return null;
            } catch (JSONException je) {
                return null;
            }

            return permit;
        }

        @Override
        protected void onPostExecute(Boolean permit) {
            super.onPostExecute(permit);

            if (permit == null) {
                // error
                Toast.makeText(LoginActivity.this, R.string.login_error, Toast.LENGTH_SHORT).show();
            } else if (permit) {
                // success
                mPrefs.edit().putString(getString(R.string.PREFS_ACCOUNT_ID), mID).apply();
                mPrefs.edit().putString(getString(R.string.PREFS_ACCOUNT_PASSWORD), mPassword).apply();
                Toast.makeText(LoginActivity.this,
                        mID + getString(R.string.login_success), Toast.LENGTH_SHORT).show();

                finish();
            } else {
                // failure
                Toast.makeText(LoginActivity.this, R.string.login_failure, Toast.LENGTH_SHORT).show();
            }
        }

        private boolean login(String id, String password) throws IOException, JSONException {
            JSONObject requestJSONObject = new JSONObject();
            requestJSONObject.put("id", id);
            requestJSONObject.put("password", password);
            Response response = HttpBox.post()
                    .setCommand(Commands.LOAD_ACCOUNT)
                    .putBodyData(requestJSONObject)
                    .push();

            JSONObject responseJSONObject = response.getJsonObject();

            return responseJSONObject.getBoolean("permit");
        }
    }

}
