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
import android.widget.TextView;

import com.dms.beinone.application.R;
import com.dms.beinone.application.managers.EditTextManager;

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
    public void onCreate(Bundle SavedInstanceState) {
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

                EditTextManager.hideKeyboard(ChangePasswordActivity.this, (EditText) view);
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
                checkInput();
                checkEquals();
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
                checkInput();
                checkEquals();
            }
        });

        ibChangeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View view) {

                finish();
            }
        });
    }

    private void checkInput () {

        String existingPassword = etChangePassword.getText().toString();
        String changePassword = etChangePassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        Log.e("check","ID : "+existingPassword);

        if(!existingPassword.isEmpty() && !changePassword.isEmpty() && !confirmPassword.isEmpty() && checkPassword) {

            ibChangeButton.setEnabled(true);
            ibChangeButton.setClickable(true);
        }
    }

    private void checkEquals () {

        String changePassword = etChangePassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        if(changePassword.equals(confirmPassword)) {

            checkPassword = true;
            int checkColor = ContextCompat.getColor(ChangePasswordActivity.this, R.color.valid);
            tvCheckPasswordEquals.setText("비밀번호가 일치합니다.");
            tvCheckPasswordEquals.setTextColor(checkColor);
            ivCheckPasswordEquals.setImageResource(R.drawable.ic_done_white_18dp);
            ivCheckPasswordEquals.setColorFilter(checkColor, PorterDuff.Mode.MULTIPLY);
        }
        else {

            checkPassword = false;
            int checkColor = ContextCompat.getColor(ChangePasswordActivity.this, R.color.invalid);
            tvCheckPasswordEquals.setText("비밀번호가 일치하지 않습니다.");
            tvCheckPasswordEquals.setTextColor(checkColor);
            ivCheckPasswordEquals.setImageResource(R.drawable.ic_warning_white_18dp);
            ivCheckPasswordEquals.setColorFilter(checkColor, PorterDuff.Mode.MULTIPLY);
        }
    }
}
