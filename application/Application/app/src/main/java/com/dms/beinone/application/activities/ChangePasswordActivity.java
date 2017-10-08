package com.dms.beinone.application.activities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
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
import static com.dms.beinone.application.DMSService.HTTP_OK;

/**
 * Created by dsm2017 on 2017-09-27.
 */

public class ChangePasswordActivity extends AppCompatActivity {

    private boolean checkPassword;
    private ImageButton ibChangeButton;
    private EditText etChangePassword;
    private EditText etConfirmPassword;
    private EditText etExistingPassword;
    private ImageView ivCheckPasswordEquals;
    private TextView tvCheckPasswordEquals;

    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_change_password);

        ivCheckPasswordEquals = (ImageView) findViewById(R.id.iv_change_validate_password);
        tvCheckPasswordEquals = (TextView) findViewById(R.id.tv_change_validate_password);
        ibChangeButton = (ImageButton) findViewById(R.id.btn_change_password);
        ibChangeButton.setEnabled(false);
        ibChangeButton.setClickable(false);

        etExistingPassword = (EditText) findViewById(R.id.et_existing_password);
        etExistingPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                EditTextManager.hideKeyboard(ChangePasswordActivity.this, (EditText)view);
            }
        });

        etExistingPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkEquals();
                checkInput();
            }
        });

        etChangePassword = (EditText) findViewById(R.id.et_change_password);
        etChangePassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                EditTextManager.hideKeyboard(ChangePasswordActivity.this, (EditText) view);
            }
        });

        etChangePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkEquals();
                checkInput();
            }
        });

        etConfirmPassword = (EditText) findViewById(R.id.et_change_confirm_password);
        etConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                EditTextManager.hideKeyboard(ChangePasswordActivity.this, (EditText) view);
            }
        });

        etConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkEquals();
                checkInput();
            }
        });

        ibChangeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View view) {

                String change = etChangePassword.getText().toString().trim();
                String exist = etExistingPassword.getText().toString().trim();
                changePassword(exist, change);
            }
        });
    }

    private void checkInput () {

        String existingPassword = etExistingPassword.getText().toString();
        String changePassword = etChangePassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        if(!existingPassword.isEmpty() && !changePassword.isEmpty() && !confirmPassword.isEmpty() && checkPassword) {

            ibChangeButton.setEnabled(true);
            ibChangeButton.setClickable(true);
        } else {

            ibChangeButton.setEnabled(false);
            ibChangeButton.setClickable(false);
        }
    }

    private void checkEquals () {

        String changePassword = etChangePassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        String existPassword = etExistingPassword.getText().toString();

        // 기존 비밀번호와 새 비밀번호가 일치하는지 확인하는 조건문 새 비밀번호와 확인이 공백이 아닐때만 판별
        if((!changePassword.isEmpty() && existPassword.equals(changePassword)) || (existPassword.equals(confirmPassword) && !confirmPassword.isEmpty())) {
            checkPassword=false;
            int checkColor = ContextCompat.getColor(ChangePasswordActivity.this, R.color.invalid);
            tvCheckPasswordEquals.setText(R.string.change_check_exist);
            tvCheckPasswordEquals.setTextColor(checkColor);
            ivCheckPasswordEquals.setImageResource(R.drawable.ic_warning_white_18dp);
            ivCheckPasswordEquals.setColorFilter(checkColor, PorterDuff.Mode.MULTIPLY);
        }
        else {
            // 새 비밀번호가 확인과 일치하는지 확인하는 조건문 단 새 비밀번호와 확인이 공백이 아닐때만 판별
            if (changePassword.equals(confirmPassword) && !changePassword.isEmpty()) {
                checkPassword = true;
                int checkColor = ContextCompat.getColor(ChangePasswordActivity.this, R.color.valid);
                tvCheckPasswordEquals.setText(R.string.change_check_confirm_succes);
                tvCheckPasswordEquals.setTextColor(checkColor);
                ivCheckPasswordEquals.setImageResource(R.drawable.ic_done_white_18dp);
                ivCheckPasswordEquals.setColorFilter(checkColor, PorterDuff.Mode.MULTIPLY);
                ivCheckPasswordEquals.setVisibility(View.VISIBLE);
            }
            // 새 비밀번호와 확인이 서로 일치하지 않음을 확인하는 조건문 단 새 비밀번호와 확인이 공백이 아닐때만 판별
            else if(!changePassword.isEmpty() || !confirmPassword.isEmpty()){
                checkPassword = false;
                int checkColor = ContextCompat.getColor(ChangePasswordActivity.this, R.color.invalid);
                tvCheckPasswordEquals.setText(R.string.change_check_confirm_fail);
                tvCheckPasswordEquals.setTextColor(checkColor);
                ivCheckPasswordEquals.setImageResource(R.drawable.ic_warning_white_18dp);
                ivCheckPasswordEquals.setColorFilter(checkColor, PorterDuff.Mode.MULTIPLY);
                ivCheckPasswordEquals.setVisibility(View.VISIBLE);
            }
            // 새 비밀번호와 확인이 공백임을 확인하는 조건문
            else if (changePassword.isEmpty() && confirmPassword.isEmpty()){
                tvCheckPasswordEquals.setText("");
                ivCheckPasswordEquals.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void changePassword (final String existPassword, final String changePassword) {

        DMSService dmsService = HttpManager.createDMSService(this);
        Call<Void> call = dmsService.change(existPassword, changePassword);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                int code = response.code();

                switch (code) {

                    case HTTP_OK:
                        Toast.makeText(ChangePasswordActivity.this, R.string.change_eror_HTTP_OK, Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case HTTP_BAD_REQUEST:
                        Toast.makeText(ChangePasswordActivity.this, R.string.change_eror_HTTP_BAD, Toast.LENGTH_SHORT).show();
                        break;
                    case HTTP_INTERNAL_SERVER_ERROR:
                        Toast.makeText(ChangePasswordActivity.this, R.string.change_eror_HTTP_INTERNAL_SERVER_EROR, Toast.LENGTH_SHORT).show();
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