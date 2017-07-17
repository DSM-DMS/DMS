package com.dms.beinone.application.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.dms.beinone.application.DMSService;
import com.dms.beinone.application.R;
import com.dms.beinone.application.managers.EditTextManager;
import com.dms.beinone.application.managers.HttpManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dms.beinone.application.DMSService.HTTP_BAD_REQUEST;
import static com.dms.beinone.application.DMSService.HTTP_CREATED;
import static com.dms.beinone.application.DMSService.HTTP_INTERNAL_SERVER_ERROR;

/**
 * Created by BeINone on 2017-01-25.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText mIdET;
    private EditText mPasswordET;
    private CheckBox mRememberCB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mIdET = (EditText) findViewById(R.id.et_login_id);
        mPasswordET = (EditText) findViewById(R.id.et_login_password);
        mRememberCB = (CheckBox) findViewById(R.id.cb_login_remember);

        mIdET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditTextManager.hideKeyboard(LoginActivity.this, (EditText) v);
            }
        });
        mPasswordET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditTextManager.hideKeyboard(LoginActivity.this, (EditText) v);
            }
        });

        Button loginBtn = (Button) findViewById(R.id.btn_login_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = mIdET.getText().toString();
                String password = mPasswordET.getText().toString();
                boolean remember = mRememberCB.isChecked();
                if (id.equals("")) {
                    Toast.makeText(LoginActivity.this, R.string.login_noid, Toast.LENGTH_SHORT)
                            .show();
                } else if (password.equals("")) {
                    Toast.makeText(LoginActivity.this, R.string.login_nopassword, Toast.LENGTH_SHORT)
                            .show();
                } else {
                    try {
                        login(id, password, remember);
                    } catch (IOException e) {
                        System.out.println("IOException in LoginActivity: login()");
                        e.printStackTrace();
                    }
                }
            }
        });

//        Button registerBtn = (Button) findViewById(R.id.btn_login_register);
//        registerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
//            }
//        });
    }

    private void login(final String id, final String password, boolean remember) throws IOException {
        DMSService dmsService = HttpManager.createDMSService(this);
        Call<Void> call = dmsService.login(id, password, remember);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int code = response.code();
                switch (code) {
                    case HTTP_CREATED:
                        Toast.makeText(LoginActivity.this, id + getString(R.string.login_created), Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case HTTP_BAD_REQUEST:
                        Toast.makeText(LoginActivity.this, R.string.login_bad_request, Toast.LENGTH_SHORT).show();
                        break;
                    case HTTP_INTERNAL_SERVER_ERROR:
                        Toast.makeText(LoginActivity.this, R.string.login_internal_server_error, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
