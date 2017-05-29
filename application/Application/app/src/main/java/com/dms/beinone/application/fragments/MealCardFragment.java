package com.dms.beinone.application.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.beinone.application.models.Meal;
import com.dms.beinone.application.utils.JSONParser;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by BeINone on 2017-05-16.
 */

public class MealCardFragment extends Fragment {

    private static final String ARGS_KEY_DATE = "date";

    private TextView mDateTV;
    private TextView mMonthTV;
    private TextView mYearTV;
    private TextView mBreakfastTV;
    private TextView mLunchTV;
    private TextView mDinnerTV;

    private Meal mMeal;

    public static MealCardFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putLong(ARGS_KEY_DATE, date.getTime());

        MealCardFragment fragment = new MealCardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_card, container, false);

        Date date = new Date(getArguments().getLong(ARGS_KEY_DATE));
        init(view, date);

        return view;
    }

    private void init(View rootView, Date date) {
        mDateTV = (TextView) rootView.findViewById(R.id.tv_meal_date);
        mMonthTV = (TextView) rootView.findViewById(R.id.tv_meal_month);
        mYearTV = (TextView) rootView.findViewById(R.id.tv_meal_year);
        mBreakfastTV = (TextView) rootView.findViewById(R.id.tv_meal_breakfast_content);
        mLunchTV = (TextView) rootView.findViewById(R.id.tv_meal_lunch_content);
        mDinnerTV = (TextView) rootView.findViewById(R.id.tv_meal_dinner_content);

        setDate(date);
        if (mMeal != null) {
            bind();
        } else {
            try {
                loadMeal(date);
            } catch (IOException e) {
                System.out.println("IOException in MealCardFragment: loadMeal()");
                e.printStackTrace();
            }
        }
    }

    private void setDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        mDateTV.setText(String.valueOf(cal.get(Calendar.DATE)));
        mMonthTV.setText(cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH));
        mYearTV.setText(String.valueOf(cal.get(Calendar.YEAR)));
    }

    private void setMeal(Meal meal) {
        mMeal = meal;
        bind();
    }

    private void bind() {
        if (mMeal != null) {
            mBreakfastTV.setText(mMeal.getBreakfast());
            mLunchTV.setText(mMeal.getLunch());
            mDinnerTV.setText(mMeal.getDinner());
        }
    }

    private void loadMeal(Date date) throws IOException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        try {
            JSONObject params = new JSONObject();
            params.put("year", calendar.get(Calendar.YEAR));
            params.put("month", calendar.get(Calendar.MONTH) + 1);
            params.put("day", calendar.get(Calendar.DAY_OF_MONTH));

            HttpBox.get(getContext(), "/school/meal")
                    .putQueryString(params)
                    .push(new HttpBoxCallback() {
                        @Override
                        public void done(Response response) {
                            int code = response.getCode();
                            switch (code) {
                                case HttpBox.HTTP_OK:
                                    try {
                                        Meal meal = JSONParser.parseMealJSON(response.getJsonObject());
                                        setMeal(meal);
                                    } catch (JSONException e) {
                                        System.out.println("JSONException in MealCardFragment: GET /school/meal");
                                        e.printStackTrace();
                                    }
                                    break;
                                case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                    Toast.makeText(getContext(), R.string.meal_internal_server_error, Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void err(Exception e) {
                            System.out.println("Error in MealCardFragment: GET /school/meal");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException in MealCardFragment: GET /school/meal");
            e.printStackTrace();
        }
    }
}
