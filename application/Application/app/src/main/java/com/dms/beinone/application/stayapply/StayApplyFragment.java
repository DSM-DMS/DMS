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
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.DateUtils;
import com.dms.beinone.application.PrefsConst;
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

/**
 * Created by BeINone on 2017-01-14.
 */

public class StayApplyFragment extends Fragment {

    private static final int FRIDAY_GO = 1;
    private static final int SATURDAY_GO = 2;
    private static final int SATURDAY_COME = 3;
    private static final int STAY = 4;

    private TextView mDefaultStatusTV;
    private TextView mSelectedWeekTV;
    private TextView mSelectedWeekStatusTV;

    private SharedPreferences mPrefs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.nav_stayapply);
        View view = inflater.inflate(R.layout.fragment_stayapply, container, false);

        init(view);

        return view;
    }

    /**
     * 초기화, 달력 날짜 클릭 이벤트 설정, 신청 버튼 클릭 이벤트 설정
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        mSelectedWeekTV = (TextView) rootView.findViewById(R.id.tv_stayapply_selectedweek);
        mSelectedWeekStatusTV = (TextView) rootView.findViewById(R.id.tv_stayapply_selectedweekstatus);

        mPrefs = getActivity().getSharedPreferences(PrefsConst.NAME, MODE_PRIVATE);

        final CalendarView calendarView = (CalendarView) rootView.findViewById(R.id.calendar_stayapply);
//        calendarView.setOnDateLongClickListener(null);
        calendarView.setOnDateClickListener(new CalendarView.OnDateClickListener() {
            @Override
            public void onDateClick(@NonNull Date date) {
                setSelectedWeekTV(date);

                // load and display stay status of selected date
                String id = mPrefs.getString(PrefsConst.ID, null);
                String strDate = DateUtils.dateToString(date);
                new LoadStayStatusTask().execute(id, strDate);
            }
        });

        setSelectedWeekTV(calendarView.getLastSelectedDay());

        final DMSRadioButton fridayGoRB =
                (DMSRadioButton) rootView.findViewById(R.id.rb_stayapply_fridaygo);
        final DMSRadioButton saturdayGoRB =
                (DMSRadioButton) rootView.findViewById(R.id.rb_stayapply_saturdaygo);
        final DMSRadioButton saturdayComeRB =
                (DMSRadioButton) rootView.findViewById(R.id.rb_stayapply_saturdaycome);
        final DMSRadioButton stayRB = (DMSRadioButton) rootView.findViewById(R.id.rb_stayapply_stay);

        DMSButton applyBtn = (DMSButton) rootView.findViewById(R.id.btn_stayapply_apply);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fridayGoRB.isChecked()) {
                    apply(FRIDAY_GO);
                } else if (saturdayGoRB.isChecked()) {
                    apply(SATURDAY_GO);
                } else if (saturdayComeRB.isChecked()) {
                    apply(SATURDAY_COME);
                } else if (stayRB.isChecked()) {
                    apply(STAY);
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

    /**
     * 데이터베이스에 잔류 상태 정보 기록
     * @param stayStatus 잔류 상태
     */
    private void apply(int stayStatus) {

    }

    /**
     * load stay status of the date from server and display it
     */
    private class LoadStayStatusTask extends AsyncTask<Object, Void, Integer> {

        @Override
        protected Integer doInBackground(Object... objects) {
            int stayStatus = -1;

            try {
                stayStatus = loadStayStatus((int) objects[0], (String) objects[1]);
            } catch (IOException ie) {
                stayStatus = -1;
            } catch (JSONException je) {
                stayStatus = -1;
            }

            return stayStatus;
        }

        @Override
        protected void onPostExecute(Integer stayStatus) {
            super.onPostExecute(stayStatus);

            if (stayStatus == -1) {
                Toast.makeText(getContext(), R.string.stayapply_error, Toast.LENGTH_SHORT).show();
            } else if (stayStatus == FRIDAY_GO) {
                setSelectedWeekValueTV("금요귀가");
            } else if (stayStatus == SATURDAY_GO) {
                setSelectedWeekValueTV("토요귀가");
            } else if (stayStatus == SATURDAY_COME) {
                setSelectedWeekValueTV("토요귀사");
            } else if (stayStatus == STAY) {
                setSelectedWeekValueTV("잔류");
            }
        }

        private int loadStayStatus(int id, String date) throws IOException, JSONException {
            JSONObject requestJsonObject = new JSONObject();
            requestJsonObject.put("id", id);
            requestJsonObject.put("date", date);
            Response response = HttpBox.post()
                    .setCommand(Commands.LOAD_STAY_STATUS)
                    .putBodyData(requestJsonObject)
                    .push();

            JSONObject stayStatusJSONObject = response.getJsonObject();

            return stayStatusJSONObject.getInt("value");
        }

        private void setSelectedWeekValueTV(String stayState) {
            mSelectedWeekStatusTV.setText(stayState);
        }

    }

    /**
     * load default stay status of the user from server and display it
     */
    private class LoadDefaultStayStatusTask extends AsyncTask<Object, Void, Integer> {

        @Override
        protected Integer doInBackground(Object... objects) {
            int stayStatus = -1;

            try {
                stayStatus = loadDefaultStayStatus((int) objects[0]);
            } catch (IOException ie) {
                stayStatus = -1;
            } catch (JSONException je) {
                stayStatus = -1;
            }

            return stayStatus;
        }

        @Override
        protected void onPostExecute(Integer stayStatus) {
            super.onPostExecute(stayStatus);

            if (stayStatus == -1) {
                Toast.makeText(getContext(), R.string.stayapply_error, Toast.LENGTH_SHORT).show();
            } else if (stayStatus == FRIDAY_GO) {
                setDefaultStatusTV("금요귀가");
            } else if (stayStatus == SATURDAY_GO) {
                setDefaultStatusTV("토요귀가");
            } else if (stayStatus == SATURDAY_COME) {
                setDefaultStatusTV("토요귀사");
            } else if (stayStatus == STAY) {
                setDefaultStatusTV("잔류");
            }
        }

        private int loadDefaultStayStatus(int id) throws IOException, JSONException {
            JSONObject requestJsonObject = new JSONObject();
            requestJsonObject.put("id", id);
            Response response = HttpBox.post()
                    .setCommand(Commands.LOAD_STAY_STATUS)
                    .putBodyData(requestJsonObject)
                    .push();

            JSONObject stayStatusJSONObject = response.getJsonObject();

            return stayStatusJSONObject.getInt("value");
        }

        private void setDefaultStatusTV(String stayState) {
            mDefaultStatusTV.setText(stayState);
        }

    }

}
