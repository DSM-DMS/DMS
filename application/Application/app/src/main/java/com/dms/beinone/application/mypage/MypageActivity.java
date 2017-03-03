package com.dms.beinone.application.mypage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dms.beinone.application.JSONParser;
import com.dms.beinone.application.R;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Commands;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by BeINone on 2017-02-20.
 */

public class MypageActivity extends AppCompatActivity {

    private SharedPreferences mAccountPrefs;

    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private ImageView mProfileIV;
    private TextView mNameTV;
    private TextView mMeritTV;
    private TextView mDemeritTV;
    private TextView mTotalTV;
    private TextView mSexTV;
    private TextView mPhoneTV;
    private TextView mStatusTV;
    private TextView mParentTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mypage);

        init();
    }

    /**
     * 초기화
     */
    private void init() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_mypage);
        mProfileIV = (ImageView) findViewById(R.id.iv_mypage_profile);
        mNameTV = (TextView) findViewById(R.id.tv_mypage_name);
        mMeritTV = (TextView) findViewById(R.id.tv_mypage_merit);
        mDemeritTV = (TextView) findViewById(R.id.tv_mypage_demerit);
        mTotalTV = (TextView) findViewById(R.id.tv_mypage_total);
        mSexTV = (TextView) findViewById(R.id.tv_mypage_sex);
        mPhoneTV = (TextView) findViewById(R.id.tv_mypage_phone);
        mStatusTV = (TextView) findViewById(R.id.tv_mypage_status);
        mParentTV = (TextView) findViewById(R.id.tv_mypage_parent);

        mAccountPrefs = getSharedPreferences(
                getString(R.string.PREFS_ACCOUNT), Context.MODE_PRIVATE);

        setTitle("조성빈");
        setSupportActionBar(mToolbar);
        // display back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String id = mAccountPrefs.getString("id", "");
        new LoadMypageTask().execute(id);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Glide.with(this).load(R.drawable.test).centerCrop().into(mProfileIV);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;
    }

    @Override
    public void onStop() {
        super.onStop();

        Glide.clear(mProfileIV);
    }

    private void bind(Student student) {
        mNameTV.setText(student.getName());
        mMeritTV.setText(String.valueOf(student.getMerit()));
        mDemeritTV.setText(String.valueOf(student.getDemerit()));
        mTotalTV.setText(String.valueOf(student.getMerit() - student.getDemerit()));
        // true = "여자", false = "남자"
        mSexTV.setText(student.getSex() ? "여자" : "남자");
        mPhoneTV.setText(student.getPhone());
        mStatusTV.setText(String.valueOf(student.getStatus()));
        mParentTV.setText(student.getParent());
    }

    private class LoadMypageTask extends AsyncTask<String, Void, Student> {

        @Override
        protected Student doInBackground(String... params) {
            Student student = null;

            try {
                String id = params[0];
                student = loadMypage(id);
            } catch (IOException e) {
                return null;
            } catch (JSONException e) {
                return null;
            }

            return student;
        }

        @Override
        protected void onPostExecute(Student student) {
            super.onPostExecute(student);

            if (student == null) {
                Toast.makeText(MypageActivity.this, R.string.mypage_error, Toast.LENGTH_SHORT).show();
            } else {
                bind(student);
            }
        }

        private Student loadMypage(String id) throws IOException, JSONException {
            JSONObject requestJSONObject = new JSONObject();
            requestJSONObject.put("id", id);

            Response response = HttpBox.post()
                    .setCommand(Commands.LOAD_MYPAGE)
                    .putBodyData(requestJSONObject)
                    .push();
            JSONObject responseJSONObject = response.getJsonObject();

            return JSONParser.parseStudentJSON(responseJSONObject);
        }
    }

}
