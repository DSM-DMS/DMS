package com.dms.beinone.application.mypage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class MypageFragment extends Fragment {

    private SharedPreferences mAccountPrefs;

    private ImageView mProfileIV;
    private TextView mNameTV;
    private TextView mMeritTV;
    private TextView mDemeritTV;
    private TextView mTotalTV;
    private TextView mSexTV;
    private TextView mPhoneTV;
    private TextView mStatusTV;
    private TextView mParentTV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        init(view);

        return view;
    }

    /**
     * 초기화
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        getActivity().setTitle(R.string.nav_mypage);

        mProfileIV = (ImageView) rootView.findViewById(R.id.iv_mypage_profile);
        mNameTV = (TextView) rootView.findViewById(R.id.tv_mypage_name);
        mMeritTV = (TextView) rootView.findViewById(R.id.tv_mypage_merit);
        mDemeritTV = (TextView) rootView.findViewById(R.id.tv_mypage_demerit);
        mTotalTV = (TextView) rootView.findViewById(R.id.tv_mypage_total);
        mSexTV = (TextView) rootView.findViewById(R.id.tv_mypage_sex);
        mPhoneTV = (TextView) rootView.findViewById(R.id.tv_mypage_phone);
        mStatusTV = (TextView) rootView.findViewById(R.id.tv_mypage_status);
        mParentTV = (TextView) rootView.findViewById(R.id.tv_mypage_parent);

        mAccountPrefs = getContext().getSharedPreferences(
                getString(R.string.PREFS_ACCOUNT), Context.MODE_PRIVATE);

        String id = mAccountPrefs.getString("id", "");
        new LoadMypageTask().execute(id);
    }

    @Override
    public void onStart() {
        super.onStart();

        Glide.with(getContext()).load(R.drawable.test).centerCrop().into(mProfileIV);
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
                Toast.makeText(getContext(), R.string.mypage_error, Toast.LENGTH_SHORT).show();
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
