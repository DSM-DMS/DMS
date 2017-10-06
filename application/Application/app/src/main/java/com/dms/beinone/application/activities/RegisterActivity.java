package com.dms.beinone.application.activities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.DMSService;
import com.dms.beinone.application.R;
import com.dms.beinone.application.managers.EditTextManager;
import com.dms.beinone.application.managers.HttpManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;

import static com.dms.beinone.application.DMSService.HTTP_BAD_REQUEST;
import static com.dms.beinone.application.DMSService.HTTP_CONFLICT;
import static com.dms.beinone.application.DMSService.HTTP_CREATED;
import static com.dms.beinone.application.DMSService.HTTP_INTERNAL_SERVER_ERROR;

/**
 * Created by BeINone on 2017-02-24.
 */

public class RegisterActivity extends AppCompatActivity {

    private EditText mCodeET;
    private EditText mIdET;
    private EditText mPasswordET;
    private EditText mConfirmPasswordET;
    private TextView mValidatePasswordTV;
    private ImageView mValidatePasswordIV;
    private ImageButton mRegisterIB;

    private boolean mIsValidPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_membership);

        mCodeET = (EditText) findViewById(R.id.et_register_code);
        mCodeET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditTextManager.hideKeyboard(RegisterActivity.this, (EditText) v);
            }
        });

        mCodeET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateInput();
            }
        });

        mIdET = (EditText) findViewById(R.id.et_register_id);
        mIdET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditTextManager.hideKeyboard(RegisterActivity.this, (EditText) v);
            }
        });
        mIdET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateInput();
            }
        });

        mPasswordET = (EditText) findViewById(R.id.et_register_password);
        mPasswordET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditTextManager.hideKeyboard(RegisterActivity.this, (EditText) v);
            }
        });
        mPasswordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validatePassword();
                validateInput();
            }
        });

        mValidatePasswordTV = (TextView) findViewById(R.id.tv_register_validate_password);
        mValidatePasswordIV = (ImageView) findViewById(R.id.iv_register_validate_password);

        mConfirmPasswordET = (EditText) findViewById(R.id.et_register_confirm_password);
        mConfirmPasswordET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditTextManager.hideKeyboard(RegisterActivity.this, (EditText) v);
            }
        });
        mConfirmPasswordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validatePassword();
                validateInput();
            }
        });

        mRegisterIB = (ImageButton) findViewById(R.id.btn_register_register);
        mRegisterIB.setEnabled(false);
        mRegisterIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = mCodeET.getText().toString().trim();
                String id = mIdET.getText().toString().trim();
                String password = mPasswordET.getText().toString().trim();

                if (code.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, R.string.register_no_code, Toast.LENGTH_SHORT).show();
                } else if (id.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, R.string.register_no_id, Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, R.string.register_no_password, Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        registerStudentAccount(code, id, password);
                    } catch (IOException e) {
                        System.out.println("IOException in RegisterActivity: registerStudentAccount()");
                        e.printStackTrace();
                    }
                }

                finish();
            }
        });
    }

    private void validateInput() {
        String code = mCodeET.getText().toString();
        String id = mIdET.getText().toString();
        String password = mPasswordET.getText().toString();

        if (!code.isEmpty() && !id.isEmpty() && !password.isEmpty() && mIsValidPassword) {
            mRegisterIB.setEnabled(true);
            mRegisterIB.setClickable(true);
        } else {
            mRegisterIB.setEnabled(false);
            mRegisterIB.setClickable(false);
        }
    }

    private void validatePassword() {
        String password = mPasswordET.getText().toString();
        String confirm = mConfirmPasswordET.getText().toString();

        if (confirm.equals(password)) {
            mValidatePasswordTV.setText(R.string.register_valid_password);
            int validColor = ContextCompat.getColor(this, R.color.valid);
            mValidatePasswordTV.setTextColor(validColor);
            mValidatePasswordIV.setImageResource(R.drawable.ic_done_white_18dp);
            mValidatePasswordIV.setColorFilter(validColor, PorterDuff.Mode.MULTIPLY);
            mIsValidPassword = true;
        } else {
            mValidatePasswordTV.setText(R.string.register_invalid_password);
            int invalidColor = ContextCompat.getColor(this, R.color.invalid);
            mValidatePasswordTV.setTextColor(invalidColor);
            mValidatePasswordIV.setImageResource(R.drawable.ic_warning_white_18dp);
            mValidatePasswordIV.setColorFilter(invalidColor, PorterDuff.Mode.MULTIPLY);
            mIsValidPassword = false;
        }
    }

    private void registerStudentAccount(String uid, String id, String password) throws IOException {
        DMSService dmsService = HttpManager.createDMSService(this);
        DMSService dmsService1 = HttpManager.createDMSService(this);
        Call<Void> call = dmsService.register(uid, id, password);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                switch (response.code()) {
                    case HTTP_CREATED:
                        Toast.makeText(RegisterActivity.this, R.string.register_created, Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case HTTP_BAD_REQUEST:
                        Toast.makeText(RegisterActivity.this, R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                        break;
                    case HTTP_CONFLICT:
                        Toast.makeText(RegisterActivity.this, R.string.register_conflict, Toast.LENGTH_SHORT).show();
                        break;
                    case HTTP_INTERNAL_SERVER_ERROR:
                        Toast.makeText(RegisterActivity.this, R.string.register_internal_server_error, Toast.LENGTH_SHORT).show();
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
