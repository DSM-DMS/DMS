package com.dms.beinone.application.meal;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.beinone.application.dmsview.DMSButton;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Commands;
import com.dms.boxfox.networking.datamodel.Response;
import com.samsistemas.calendarview.widget.CalendarView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by BeINone on 2017-01-18.
 */

public class MealFragment extends Fragment {

    private TextView mBreakfastTV;
    private TextView mLunchTV;
    private TextView mDinnerTV;

    private Meal mSelectedDayMeal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(R.string.nav_meal);
        View view = inflater.inflate(R.layout.fragment_meal, container, false);

        init(view);

        return view;
    }

    /**
     * 초기화, 더보기 버튼 터치 이벤트 및 클릭 이벤트 설정, 달력 날짜 클릭 이벤트 설정
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        mBreakfastTV = (TextView) rootView.findViewById(R.id.tv_meal_breakfast);
        mLunchTV = (TextView) rootView.findViewById(R.id.tv_meal_lunch);
        mDinnerTV = (TextView) rootView.findViewById(R.id.tv_meal_dinner);
        final DMSButton breakfastExpandBtn = (DMSButton) rootView.findViewById(R.id.btn_meal_expandbreakfast);
        final DMSButton lunchExpandBtn = (DMSButton) rootView.findViewById(R.id.btn_meal_expandlunch);
        final DMSButton dinnerExpandBtn = (DMSButton) rootView.findViewById(R.id.btn_meal_expanddinner);

        // display meal information as today initially
        new LoadMealTask().execute(new Date());

        breakfastExpandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (breakfastExpandBtn.isSelected()) {
                    // collapse and display meal info
                    collapse(breakfastExpandBtn);
                } else {
                    // expand and display allergy info
                    expand(breakfastExpandBtn);
                }
            }
        });
        lunchExpandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lunchExpandBtn.isSelected()) {
                    collapse(lunchExpandBtn);
                } else {
                    expand(lunchExpandBtn);
                }
            }
        });
        dinnerExpandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dinnerExpandBtn.isSelected()) {
                    collapse(dinnerExpandBtn);
                } else {
                    expand(dinnerExpandBtn);
                }
            }
        });

        CalendarView calendarView = (CalendarView) rootView.findViewById(R.id.calendar_meal);
        calendarView.setOnDateClickListener(new CalendarView.OnDateClickListener() {
            @Override
            public void onDateClick(@NonNull Date selectedDate) {
                // load the meal information of selected date from server when select date
                new LoadMealTask().execute(selectedDate);
            }
        });
    }

    /**
     * change to collapse image of button and display meal information
     * @param button Button to change image
     */
    private void collapse(Button button) {
        if (mSelectedDayMeal != null) {
            mBreakfastTV.setText(mSelectedDayMeal.getBreakfast());
        }

        button.setText(R.string.meal_allergy);

        Drawable moreDrawable =
                ContextCompat.getDrawable(getContext(), R.drawable.ic_expand_more_white_18dp);
        button.setCompoundDrawablesWithIntrinsicBounds(null, null, moreDrawable, null);

        button.setSelected(false);
    }

    /**
     * change to expand image of button and display allergy information
     * @param button Button to change image
     */
    private void expand(Button button) {
        if (mSelectedDayMeal != null) {
            mBreakfastTV.setText(mSelectedDayMeal.getBreakfastAllergy());
        }

        button.setText(R.string.meal_meal);

        Drawable lessDrawable =
                ContextCompat.getDrawable(getContext(), R.drawable.ic_expand_less_white_18dp);
        button.setCompoundDrawablesWithIntrinsicBounds(null, null, lessDrawable, null);

        button.setSelected(true);
    }

    private class LoadMealTask extends AsyncTask<Date, Void, Boolean> {

//        private Context mContext;
//
//        public LoadMealTask(Context context) {
//            mContext = context;
//        }

        @Override
        protected Boolean doInBackground(Date... dates) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dates[0]);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

            try {
                mSelectedDayMeal = loadMeal(year, month, dayOfMonth);
            } catch (IOException ie) {
                return false;
            } catch (JSONException je) {
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if (result) {
                bind();
            } else {
                Toast.makeText(getContext(), R.string.meal_error, Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * load the meal information from server
         * @return Meal object that contains the meal information of the date
         * @throws IOException
         * @throws JSONException
         */
        private Meal loadMeal(int year, int month, int dayOfMonth) throws IOException, JSONException {
            JSONObject requestJSONObject = new JSONObject();
            requestJSONObject.put("year", year);
            requestJSONObject.put("month", month);
            requestJSONObject.put("day_of_month", dayOfMonth);
            Response response = HttpBox.post()
                    .setCommand(Commands.LOAD_MEAL)
                    .putBodyData(requestJSONObject)
                    .push();

            JSONObject mealJSONObject = response.getJsonObject();

            String breakfast = mealJSONObject.getString("breakfast");
            String lunch = mealJSONObject.getString("lunch");
            String dinner = mealJSONObject.getString("dinner");
            String breakfastAllergy = mealJSONObject.getString("breakfast_allergy");
            String lunchAllergy = mealJSONObject.getString("lunch_allergy");
            String dinnerAllergy = mealJSONObject.getString("dinner_allergy");

            return new Meal(breakfast, lunch, dinner, breakfastAllergy, lunchAllergy, dinnerAllergy);
        }

        private void bind() {
            mBreakfastTV.setText(mSelectedDayMeal.getBreakfast());
            mLunchTV.setText(mSelectedDayMeal.getLunch());
            mDinnerTV.setText(mSelectedDayMeal.getDinner());
        }

    }

}
