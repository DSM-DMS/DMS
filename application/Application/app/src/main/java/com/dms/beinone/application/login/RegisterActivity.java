package com.dms.beinone.application.login;

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
import java.util.Arrays;
import java.util.List;

import lib.kingja.switchbutton.SwitchMultiButton;

/**
 * Created by BeINone on 2017-02-24.
 */

public class RegisterActivity extends AppCompatActivity {

    private static final int POS_TAB_MAN = 0;
    private static final int POS_TAB_WOMAN = 1;

    private EditText mIdET;
    private EditText mPasswordET;
    private EditText mNameET;
    private EditText mNumberET;
    private SwitchMultiButton mSexSwitchBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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

        mNameET = (EditText) findViewById(R.id.et_register_name);
        mNameET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditTextUtils.hideKeyboard(RegisterActivity.this, (EditText) v);
            }
        });

        mNumberET = (EditText) findViewById(R.id.et_register_number);
        mNumberET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditTextUtils.hideKeyboard(RegisterActivity.this, (EditText) v);
            }
        });

        List<String> sexTabTextList = Arrays.asList("남자", "여자");
        mSexSwitchBtn = (SwitchMultiButton) findViewById(R.id.switchbtn_register_sex);
        mSexSwitchBtn.setText(sexTabTextList);

        Button registerBtn = (Button) findViewById(R.id.btn_register_register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = mIdET.getText().toString().trim();
                String password = mPasswordET.getText().toString().trim();
                String name = mNameET.getText().toString().trim();
                int number = Integer.valueOf(mNumberET.getText().toString().trim());
                // man: false, woman: true
                boolean sex = mSexSwitchBtn.getSelectedTab() == POS_TAB_WOMAN;

                StudentAccount studentAccount = new StudentAccount(id, password, name, number, sex);
                new RegisterStudentAccountTask().execute(studentAccount);
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

            if (code == 200) {
                // success
                Toast.makeText(RegisterActivity.this, R.string.register_success, Toast.LENGTH_SHORT)
                        .show();
                finish();
            } else if (code == 204) {
                // empty
                Toast.makeText(RegisterActivity.this, R.string.register_failure, Toast.LENGTH_SHORT)
                        .show();
            } else {
                // error
                Toast.makeText(RegisterActivity.this, R.string.register_error, Toast.LENGTH_SHORT)
                        .show();
            }
        }

        private int registerStudentAccount(StudentAccount studentAccount)
                throws IOException, JSONException {
            JSONObject requestJSONObject = new JSONObject();
            requestJSONObject.put("id", studentAccount.getId());
            requestJSONObject.put("password", studentAccount.getPassword());
            requestJSONObject.put("name", studentAccount.getName());
            requestJSONObject.put("number", studentAccount.getNumber());
            requestJSONObject.put("sex", studentAccount.getSex());

            Response response = HttpBox.post()
                    .setCommand(Commands.REGISTER_STUDENT_ACC)
                    .putBodyData(requestJSONObject)
                    .push();

            return response.getCode();
        }

    }

}
