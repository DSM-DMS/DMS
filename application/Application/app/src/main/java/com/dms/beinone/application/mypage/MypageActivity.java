package com.dms.beinone.application.mypage;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dms.beinone.application.R;
import com.dms.beinone.application.utils.JSONParser;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Request;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by BeINone on 2017-02-20.
 */

public class MypageActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private ImageView mProfileIV;
    private TextView mNameTV;
    private TextView mMeritTV;
    private TextView mDemeritTV;
    private TextView mTotalTV;

    private String mProfileImageString;

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

        final CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout_mypage);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout_mypage);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("조성빈");
                } else {
                    collapsingToolbarLayout.setTitle(" ");
                }
            }
        });

        setSupportActionBar(mToolbar);
        // display back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new LoadMypageTask().execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mProfileImageString != null) {
            Glide.with(this).load(mProfileImageString).centerCrop().into(mProfileIV);
        }
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
        if (student.getProfileImage() != null) {
            Glide.with(this).load(student.getProfileImage()).centerCrop().into(mProfileIV);
        }
    }

    private class LoadMypageTask extends AsyncTask<Void, Void, Student> {

        @Override
        protected Student doInBackground(Void... params) {
            Student student = null;

            try {
                student = loadMypage();
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
                mProfileImageString = student.getProfileImage();
                bind(student);
            }
        }

//        public Bitmap stringToBitmap(String encodedString){
//            try{
//                byte [] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
//                Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//                return bitmap;
//            }catch(Exception e){
//                e.getMessage();
//                return null;
//            }
//        }

        private Student loadMypage() throws IOException, JSONException {
            Response response =
                    HttpBox.post(MypageActivity.this, "/account/student", Request.TYPE_GET)
                    .push();

            JSONObject responseJSONObject = response.getJsonObject();

            return JSONParser.parseStudentJSON(responseJSONObject);
        }
    }

}
