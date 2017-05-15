package com.dms.beinone.application.stayapply;

import android.content.SharedPreferences;
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

import com.dms.beinone.application.R;
import com.dms.beinone.application.dmsview.DMSButton;
import com.dms.beinone.application.dmsview.DMSRadioButton;
import com.dms.beinone.application.utils.DateUtils;
import com.dms.beinone.application.utils.JSONParser;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.datamodel.Response;
import com.samsistemas.calendarview.widget.CalendarView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

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
        try {
            loadDefaultStayStatus();
        } catch (IOException e) {
            System.out.println("IOException in StayApplyFragment: loadStayStatus()");
            e.printStackTrace();
        }

        Button changeDefaultStatusBtn = (Button) rootView.findViewById(R.id.btn_stayapply_changedefaultstatus);
        changeDefaultStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeDefaultStatusDialog.newInstance(
                        getContext(),
                        mDefaultStatusPrefs.getInt(getString(R.string.PREFS_DEFAULTSTATUS_DEFAULTSTATUS), STAY),
                        new ChangeDefaultStatusDialog.ChangeDefaultStatusListener() {
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
        setSelectedWeekTV(calendarView.getLastSelectedWeekString());

        // load and display stay status of selected week at initially
        mSelectedDate = new Date();
        try {
            loadStayStatus(DateUtils.dateToWeekDateString(mSelectedDate));
        } catch (IOException e) {
            System.out.println("IOException in StayApplyFragment: loadStayStatus()");
            e.printStackTrace();
        }

        calendarView.setOnDateClickListener(new CalendarView.OnDateClickListener() {
            @Override
            public void onDateClick(@NonNull Date date) {
                mSelectedDate = date;
                setSelectedWeekTV(calendarView.getLastSelectedWeekString());

                setSelectedWeekStatusTV(null);

                // load and display stay status of selected week
                try {
                    loadStayStatus(DateUtils.dateToWeekDateString(mSelectedDate));
                } catch (IOException e) {
                    System.out.println("IOException in StayApplyFragment: loadStayStatus()");
                    e.printStackTrace();
                }
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
        final DMSButton applyBtn = (DMSButton) rootView.findViewById(R.id.btn_stayapply_apply);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fridayGoRB.isChecked()) {
                    try {
                        applyStayStatus(FRIDAY_GO, DateUtils.dateToWeekDateString(mSelectedDate));
                    } catch (IOException e) {
                        System.out.println("IOException in StayApplyFragment: applyStayStatus");
                        e.printStackTrace();
                    }
                } else if (saturdayGoRB.isChecked()) {
                    try {
                        applyStayStatus(SATURDAY_GO, DateUtils.dateToWeekDateString(mSelectedDate));
                    } catch (IOException e) {
                        System.out.println("IOException in StayApplyFragment: applyStayStatus");
                        e.printStackTrace();
                    }
                } else if (saturdayComeRB.isChecked()) {
                    try {
                        applyStayStatus(SATURDAY_COME, DateUtils.dateToWeekDateString(mSelectedDate));
                    } catch (IOException e) {
                        System.out.println("IOException in StayApplyFragment: applyStayStatus");
                        e.printStackTrace();
                    }
                } else if (stayRB.isChecked()) {
                    try {
                        applyStayStatus(STAY, DateUtils.dateToWeekDateString(mSelectedDate));
                    } catch (IOException e) {
                        System.out.println("IOException in StayApplyFragment: applyStayStatus");
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getContext(), R.string.stayapply_nochecked, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setSelectedWeekTV(String week) {
        mSelectedWeekTV.setText(week);
    }

    private void setDefaultStatusTV(String stayState) {
        mDefaultStatusTV.setText(stayState);
    }

    private void setSelectedWeekStatusTV(String stayState) {
        mSelectedWeekStatusTV.setText(stayState);
    }

    private void loadStayStatus(String week) throws IOException {
        try {
            JSONObject params = new JSONObject();
            params.put("week", week);

            HttpBox.get(getContext(), "/apply/stay")
                    .putQueryString(params)
                    .push(new HttpBoxCallback() {
                        @Override
                        public void done(Response response) {
                            int code = response.getCode();
                            switch (code) {
                                case HttpBox.HTTP_OK:
                                    try {
                                        int stayStatusValue = JSONParser.parseStayApplyStatusJSON(response.getJsonObject());
                                        setSelectedWeekStatusTV(StayApplyUtils.getStringFromStayStatus(stayStatusValue));
                                    } catch (JSONException e) {
                                        System.out.println("JSONException in StayApplyFragment: GET /apply/stay");
                                        e.printStackTrace();
                                    }
                                    break;
                                case HttpBox.HTTP_NO_CONTENT:
                                    int stayStatusValue = mDefaultStatusPrefs.getInt(getString(R.string.PREFS_DEFAULTSTATUS_DEFAULTSTATUS), 0);
                                    setSelectedWeekStatusTV(StayApplyUtils.getStringFromStayStatus(stayStatusValue));
                                    break;
                                case HttpBox.HTTP_BAD_REQUEST:
                                    Toast.makeText(getContext(), R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                    Toast.makeText(getContext(), R.string.stayapply_internal_server_error, Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void err(Exception e) {
                            System.out.println("Error in StayApplyFragment: GET /apply/stay");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException in StayApplyFragment: GET /apply/stay");
            e.printStackTrace();
        }
    }

    private void loadDefaultStayStatus() throws IOException {
        HttpBox.get(getContext(), "/apply/stay/default")
                .push(new HttpBoxCallback() {
                    @Override
                    public void done(Response response) {
                        int code = response.getCode();
                        switch (code) {
                            case HttpBox.HTTP_OK:
                                try {
                                    int stayStatusValue = JSONParser.parseStayApplyStatusJSON(response.getJsonObject());
                                    setDefaultStatusTV(StayApplyUtils.getStringFromStayStatus(stayStatusValue));
                                    mDefaultStatusPrefs.edit()
                                            .putInt(getString(R.string.PREFS_DEFAULTSTATUS_DEFAULTSTATUS), stayStatusValue)
                                            .apply();
                                } catch (JSONException e) {
                                    System.out.println("JSONException in StayApplyFragment: GET /apply/stay/default");
                                    e.printStackTrace();
                                }
                                break;
                            case HttpBox.HTTP_NO_CONTENT:
                                Toast.makeText(getContext(), R.string.stayapply_default_no_content, Toast.LENGTH_SHORT).show();
                                break;
                            case HttpBox.HTTP_BAD_REQUEST:
                                Toast.makeText(getContext(), R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                                break;
                            case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                Toast.makeText(getContext(), R.string.stayapply_default_internal_server_error, Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void err(Exception e) {
                        System.out.println("Error in StayApplyFragment: GET /apply/stay/default");
                        e.printStackTrace();
                    }
                });
    }

    private void applyStayStatus(final int value, String week) throws IOException {
        try {
            JSONObject params = new JSONObject();
            params.put("value", String.valueOf(value));
            params.put("week", week);

            HttpBox.put(getContext(), "/apply/stay")
                    .putBodyData(params)
                    .push(new HttpBoxCallback() {
                        @Override
                        public void done(Response response) {
                            int code = response.getCode();
                            switch (code) {
                                case HttpBox.HTTP_OK:
                                    setSelectedWeekStatusTV(StayApplyUtils.getStringFromStayStatus(value));
                                    Toast.makeText(getContext(), R.string.stayapply_apply_ok, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_NO_CONTENT:
                                    Toast.makeText(getContext(), R.string.stayapply_apply_no_content, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_BAD_REQUEST:
                                    Toast.makeText(getContext(), R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                    Toast.makeText(getContext(), R.string.stayapply_apply_internal_server_error, Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void err(Exception e) {
                            System.out.println("Error in StayApplyFragment: PUT /apply/stay");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException in StayApplyFragment: PUT /apply/stay");
            e.printStackTrace();
        }
    }
}
