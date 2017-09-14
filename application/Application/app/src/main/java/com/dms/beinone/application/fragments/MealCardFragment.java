package com.dms.beinone.application.fragments;

import android.app.Service;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.DMSService;
import com.dms.beinone.application.R;
import com.dms.beinone.application.managers.HttpManager;
import com.dms.beinone.application.models.Meal;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dms.beinone.application.DMSService.HTTP_INTERNAL_SERVER_ERROR;
import static com.dms.beinone.application.DMSService.HTTP_OK;

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
        if (mMeal == null) {
            try {
                String ALL_DATE = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date);

                int idx = ALL_DATE.indexOf("-");

                String year = ALL_DATE.substring(0, idx);
                String month = ALL_DATE.substring(idx + 1, 7);
                String day = ALL_DATE.substring(8, 10);

                loadMeal(year, month, day);
            } catch (Exception e) {
                System.out.println("IOException in MealCardFragment: loadMeal()");
                e.printStackTrace();
            }
        } else {
            bind();
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
            String breakfast = TextUtils.join(", ", mMeal.getBreakfast());
            mBreakfastTV.setText(breakfast);
            String lunch = TextUtils.join(", ", mMeal.getLunch());
            mLunchTV.setText(lunch);
            String dinner = TextUtils.join(", ", mMeal.getDinner());
            mDinnerTV.setText(dinner);
        }
    }

    private Meal parseMealJson(JsonObject mealJson) {
        String breakfastArrStr = mealJson.get("breakfast").getAsString();
        JsonArray breakfastJsonArr = new JsonParser().parse(breakfastArrStr).getAsJsonArray();
        List<String> breakfast = new Gson().fromJson(breakfastJsonArr, new TypeToken<List<String>>(){}.getType());

        String lunchArrStr = mealJson.get("lunch").getAsString();
        JsonArray lunchJsonArr = new JsonParser().parse(lunchArrStr).getAsJsonArray();
        List<String> lunch = new Gson().fromJson(lunchJsonArr, new TypeToken<List<String>>(){}.getType());

        String dinnerArrStr = mealJson.get("dinner").getAsString();
        JsonArray dinnerJsonArr = new JsonParser().parse(dinnerArrStr).getAsJsonArray();
        List<String> dinner = new Gson().fromJson(dinnerJsonArr, new TypeToken<List<String>>(){}.getType());

        return new Meal(breakfast, lunch, dinner);
    }

    private void loadMeal(String year, String month, String day) throws IOException {
        DMSService dmsService = HttpManager.createDMSService_MEAL(getContext());
        Call<JsonObject> call = dmsService.loadMeal(year, month, day);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                switch (response.code()) {
                    case HTTP_OK:
                        Log.d("MEAL", response.body().toString());
                        setMeal(parseMealJson(response.body()));
                        break;
                    case HTTP_INTERNAL_SERVER_ERROR:
                        Toast.makeText(getContext(), R.string.meal_internal_server_error, Toast.LENGTH_SHORT).show();
                        Log.d("MEAL_ERROR", response.message().toString());
                        break;
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
