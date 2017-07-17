//package com.dms.beinone.application.activities;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.dms.beinone.application.R;
//import com.dms.beinone.application.managers.EditTextManager;
//import com.dms.beinone.application.models.StudentAccount;
//import com.dms.boxfox.networking.HttpBox;
//import com.dms.boxfox.networking.HttpBoxCallback;
//import com.dms.boxfox.networking.datamodel.Response;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//
///**
// * Created by BeINone on 2017-02-24.
// */
//
//public class RegisterActivity extends AppCompatActivity {
//
//    private EditText mUidET;
//    private EditText mIdET;
//    private EditText mPasswordET;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        mUidET = (EditText) findViewById(R.id.et_register_uid);
//        mUidET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                EditTextManager.hideKeyboard(RegisterActivity.this, (EditText) v);
//            }
//        });
//
//        mIdET = (EditText) findViewById(R.id.et_register_id);
//        mIdET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                EditTextManager.hideKeyboard(RegisterActivity.this, (EditText) v);
//            }
//        });
//
//        mPasswordET = (EditText) findViewById(R.id.et_register_password);
//        mPasswordET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                EditTextManager.hideKeyboard(RegisterActivity.this, (EditText) v);
//            }
//        });
//
//        Button registerBtn = (Button) findViewById(R.id.btn_register_register);
//        registerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String uid = mUidET.getText().toString().trim();
//                String id = mIdET.getText().toString().trim();
//                String password = mPasswordET.getText().toString().trim();
//
//                if (uid.isEmpty()) {
//                    Toast.makeText(RegisterActivity.this, R.string.register_nouid, Toast.LENGTH_SHORT).show();
//                } else if (id.isEmpty()) {
//                    Toast.makeText(RegisterActivity.this, R.string.register_id, Toast.LENGTH_SHORT).show();
//                } else if (password.isEmpty()) {
//                    Toast.makeText(RegisterActivity.this, R.string.register_password, Toast.LENGTH_SHORT).show();
//                } else {
//                    StudentAccount studentAccount = new StudentAccount(uid, id, password);
//                    try {
//                        registerStudentAccount(studentAccount);
//                    } catch (IOException e) {
//                        System.out.println("IOException in LoginActivity: registerStudentAccount()");
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//    }
//
//    private void registerStudentAccount(StudentAccount studentAccount) throws IOException {
//        try {
//            JSONObject params = new JSONObject();
//            params.put("uid", studentAccount.getUid());
//            params.put("id", studentAccount.getId());
//            params.put("password", studentAccount.getPassword());
//
//            HttpBox.post(RegisterActivity.this, "/account/register/student")
//                    .putBodyData(params)
//                    .push(new HttpBoxCallback() {
//                        @Override
//                        public void done(Response response) {
//                            int code = response.getCode();
//                            switch (code) {
//                                case HttpBox.HTTP_CREATED:
//                                    Toast.makeText(RegisterActivity.this, R.string.register_created, Toast.LENGTH_SHORT).show();
//                                    break;
//                                case HttpBox.HTTP_BAD_REQUEST:
//                                    Toast.makeText(RegisterActivity.this, R.string.http_bad_request, Toast.LENGTH_SHORT).show();
//                                    break;
//                                case HttpBox.HTTP_CONFLICT:
//                                    Toast.makeText(RegisterActivity.this, R.string.register_conflict, Toast.LENGTH_SHORT).show();
//                                    break;
//                                case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
//                                    Toast.makeText(RegisterActivity.this, R.string.register_internal_server_error, Toast.LENGTH_SHORT).show();
//                                    break;
//                                default:
//                                    break;
//                            }
//                        }
//
//                        @Override
//                        public void err(Exception e) {
//                            System.out.println("Error in LoginActivity: POST /account/register/student");
//                            e.printStackTrace();
//                        }
//                    });
//        } catch (JSONException e) {
//            System.out.println("JSONException in LoginActivity: POST /account/register/student");
//            e.printStackTrace();
//        }
//    }
//}
