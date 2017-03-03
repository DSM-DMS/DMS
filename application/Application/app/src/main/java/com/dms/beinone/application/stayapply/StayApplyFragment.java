package com.dms.beinone.application.stayapply;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.DateUtils;
import com.dms.beinone.application.R;
import com.dms.beinone.application.dmsview.DMSButton;
import com.dms.beinone.application.dmsview.DMSRadioButton;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Commands;
import com.dms.boxfox.networking.datamodel.Response;
import com.samsistemas.calendarview.widget.CalendarView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;
import static com.dms.beinone.application.stayapply.StayApplyUtils.FRIDAY_GO;
import static com.dms.beinone.application.stayapply.StayApplyUtils.SATURDAY_COME;
import static com.dms.beinone.application.stayapply.StayApplyUtils.SATURDAY_GO;
import static com.dms.beinone.application.stayapply.StayApplyUtils.STAY;

/**
 * Created by BeINone on 2017-01-14.
 */

public class StayApplyFragment extends Fragment {

    private TextView mDefaultStatusTV;
    private TextView mSelectedWeekTV;
    private TextView mSelectedWeekStatusTV;

    private SharedPreferences mAccountPrefs;
    private SharedPreferences mDefaultStatusPrefs;

    private Date mSelectedDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stayapply, container, false);
        init(view);

        return view;
    }

    /**
     * 초기화, 달력 날짜 클릭 이벤트 설정, 신청 버튼 클릭 이벤트 설정
     *
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        getActivity().setTitle(R.string.nav_stayapply);
        mAccountPrefs = getActivity()
                .getSharedPreferences(getString(R.string.PREFS_ACCOUNT), MODE_PRIVATE);
        mDefaultStatusPrefs = getActivity()
                .getSharedPreferences(getString(R.string.PREFS_DEFAULTSTATUS), MODE_PRIVATE);

        mDefaultStatusTV = (TextView) rootView.findViewById(R.id.tv_stayapply_defaultstatus);
        mSelectedWeekTV = (TextView) rootView.findViewById(R.id.tv_stayapply_selectedweek);
        mSelectedWeekStatusTV = (TextView) rootView.findViewById(R.id.tv_stayapply_selectedweekstatus);
        final CalendarView calendarView = (CalendarView) rootView.findViewById(R.id.calendar_stayapply);

        // load and display default stay status of user
        final String id = mAccountPrefs.getString(getString(R.string.PREFS_ACCOUNT_ID), null);
        new LoadDefaultStayStatusTask().execute(id);

        Button changeDefaultStatusBtn = (Button) rootView.findViewById(R.id.btn_stayapply_changedefaultstatus);
        changeDefaultStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeDefaultStatusDialogFragment.newInstance(
                        getContext(),
                        mDefaultStatusPrefs.getInt(getString(R.string.PREFS_DEFAULTSTATUS_DEFAULTSTATUS), STAY),
                        new ChangeDefaultStatusDialogFragment.ChangeDefaultStatusListener() {
                            @Override
                            public void onChangeDefaultStatus(int defaultStatus) {
                                mDefaultStatusPrefs.edit()
                                        .putInt(getString(R.string.PREFS_DEFAULTSTATUS_DEFAULTSTATUS), defaultStatus)
                                        .apply();
                                setDefaultStatusTV(StayApplyUtils.getStringFromStayStatus(defaultStatus));
                            }
                        })
                        .show(getChildFragmentManager(), null);
            }
        });

        // display selected week at initially
        setSelectedWeekTV(calendarView.getLastSelectedDay());

        // load and display stay status of selected week at initially
        mSelectedDate = new Date();
        new LoadStayStatusTask().execute(id, DateUtils.dateToWeekDateString(mSelectedDate));

        calendarView.setOnDateClickListener(new CalendarView.OnDateClickListener() {
            @Override
            public void onDateClick(@NonNull Date date) {
                mSelectedDate = date;
                setSelectedWeekTV(date);

                setSelectedWeekStatusTV(null);

                // load and display stay status of selected week
                String id = mAccountPrefs.getString(getString(R.string.PREFS_ACCOUNT_ID), null);
                new LoadStayStatusTask().execute(id, DateUtils.dateToWeekDateString(mSelectedDate));
            }
        });

        final DMSRadioButton fridayGoRB =
                (DMSRadioButton) rootView.findViewById(R.id.rb_stayapply_fridaygo);
        final DMSRadioButton saturdayGoRB =
                (DMSRadioButton) rootView.findViewById(R.id.rb_stayapply_saturdaygo);
        final DMSRadioButton saturdayComeRB =
                (DMSRadioButton) rootView.findViewById(R.id.rb_stayapply_saturdaycome);
        final DMSRadioButton stayRB = (DMSRadioButton) rootView.findViewById(R.id.rb_stayapply_stay);

        // apply stay status when apply button is clicked
        DMSButton applyBtn = (DMSButton) rootView.findViewById(R.id.btn_stayapply_apply);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fridayGoRB.isChecked()) {
                    new ApplyStayStatusTask()
                            .execute(id, FRIDAY_GO, DateUtils.dateToWeekDateString(mSelectedDate));
                } else if (saturdayGoRB.isChecked()) {
                    new ApplyStayStatusTask()
                            .execute(id, SATURDAY_GO, DateUtils.dateToWeekDateString(mSelectedDate));
                } else if (saturdayComeRB.isChecked()) {
                    new ApplyStayStatusTask()
                            .execute(id, SATURDAY_COME, DateUtils.dateToWeekDateString(mSelectedDate));
                } else if (stayRB.isChecked()) {
                    new ApplyStayStatusTask()
                            .execute(id, STAY, DateUtils.dateToWeekDateString(mSelectedDate));
                } else {
                    Toast.makeText(getContext(), R.string.stayapply_nochecked, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setSelectedWeekTV(Date date) {
        // 토요일 기준으로 주 구분
        Calendar cal = Calendar.getInstance(Locale.KOREA);
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 M월 W주", Locale.KOREA);
        mSelectedWeekTV.setText(sdf.format(cal.getTime()));
    }

    private void setDefaultStatusTV(String stayState) {
        mDefaultStatusTV.setText(stayState);
    }

    private void setSelectedWeekStatusTV(String stayState) {
        mSelectedWeekStatusTV.setText(stayState);
    }

    /**
     * load stay status of the date from server and display it
     */
    private class LoadStayStatusTask extends AsyncTask<String, Void, int[]> {

        @Override
        protected int[] doInBackground(String... params) {
            int[] values = null;

            try {
                values = loadStayStatus(params[0], params[1]);
            } catch (IOException e) {
                return new int[]{ -1, -1 };
            } catch (JSONException e) {
                return new int[]{ -1, -1 };
            }

            return values;
        }

        @Override
        protected void onPostExecute(int[] values) {
            super.onPostExecute(values);

            int code = values[0];
            int stayStatus = values[1];

            if (code == 200 || code == 204) {
                /* succeed */
                setSelectedWeekStatusTV(StayApplyUtils.getStringFromStayStatus(stayStatus));
            } else {
                /* error */
                Toast.makeText(getContext(), R.string.stayapply_error, Toast.LENGTH_SHORT).show();
            }
        }

        private int[] loadStayStatus(String id, String week) throws IOException, JSONException {
            JSONObject requestJsonObject = new JSONObject();
            requestJsonObject.put("id", id);
            requestJsonObject.put("week", week);
            Response response = HttpBox.post()
                    .setCommand(Commands.LOAD_STAY_APPLY_STATUS)
                    .putBodyData(requestJsonObject)
                    .push();

            JSONObject responseJSONObject = response.getJsonObject();

            int stayStatus = 0;
            if (responseJSONObject.has("value")) {
                stayStatus = responseJSONObject.getInt("value");
            } else {
                stayStatus = mDefaultStatusPrefs.getInt(
                        getString(R.string.PREFS_DEFAULTSTATUS_DEFAULTSTATUS), 0);
            }

            return new int[]{ response.getCode(), stayStatus };
        }

    }

    /**
     * load default stay status of the user from server and display it
     */
    private class LoadDefaultStayStatusTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            int stayStatus = -1;

            try {
                stayStatus = loadDefaultStayStatus(params[0]);
            } catch (IOException e) {
                return -1;
            } catch (JSONException e) {
                return -1;
            }

            return stayStatus;
        }

        @Override
        protected void onPostExecute(Integer stayStatus) {
            super.onPostExecute(stayStatus);

            if (stayStatus == -1) {
                Toast.makeText(getContext(), R.string.stayapply_default_error, Toast.LENGTH_SHORT).show();
            }

            setDefaultStatusTV(StayApplyUtils.getStringFromStayStatus(stayStatus));
            mDefaultStatusPrefs.edit()
                    .putInt(getString(R.string.PREFS_DEFAULTSTATUS_DEFAULTSTATUS), stayStatus)
                    .apply();
        }

        private int loadDefaultStayStatus(String id) throws IOException, JSONException {
            JSONObject requestJsonObject = new JSONObject();
            requestJsonObject.put("id", id);
            Response response = HttpBox.post()
                    .setCommand(Commands.LOAD_STAY_DEFAULT)
                    .putBodyData(requestJsonObject)
                    .push();

            JSONObject stayStatusJSONObject = response.getJsonObject();

            return stayStatusJSONObject.getInt("value");
        }

    }

    private class ApplyStayStatusTask extends AsyncTask<Object, Void, int[]> {

        @Override
        protected int[] doInBackground(Object... params) {
            int[] values = null;

            try {
                values = applyStayStatus(params[0].toString(), (int) params[1], params[2].toString());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

            return values;
        }

        @Override
        protected void onPostExecute(int[] values) {
            super.onPostExecute(values);

            int code = values[0];
            int stayStatus = values[1];

            if (code == 200) {
                /* succeed */
                Toast.makeText(getContext(), R.string.stayapply_apply_success, Toast.LENGTH_SHORT)
                        .show();
                setSelectedWeekStatusTV(StayApplyUtils.getStringFromStayStatus(stayStatus));
            } else if (code == 204) {
                /* failed */
                Toast.makeText(getContext(), R.string.stayapply_apply_failure, Toast.LENGTH_SHORT)
                        .show();
            } else {
                /* error */
                Toast.makeText(getContext(), R.string.stayapply_apply_error, Toast.LENGTH_SHORT)
                        .show();
            }
        }

        private int[] applyStayStatus(String id, int value, String week)
                throws IOException, JSONException {

            JSONObject requestJSONObject = new JSONObject();
            requestJSONObject.put("id", id);
            requestJSONObject.put("value", value);
            requestJSONObject.put("week", week);

            Response response = HttpBox.post()
                    .setCommand(Commands.APPLY_STAY)
                    .putBodyData(requestJSONObject)
                    .push();

            return new int[]{response.getCode(), value};
        }
    }

}
