package com.dms.beinone.application.meal;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
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
import com.dms.beinone.application.utils.JSONParser;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
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

    private static final int BREAKFAST = 1;
    private static final int LUNCH = 2;
    private static final int DINNER = 3;

    private TextView mBreakfastTV;
    private TextView mLunchTV;
    private TextView mDinnerTV;
    private Button mBreakfastExpandBtn;
    private Button mLunchExpandBtn;
    private Button mDinnerExpandBtn;

    private Drawable mMoreDrawable;
    private Drawable mLessDrawable;

    private Meal mSelectedDayMeal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal, container, false);
        init(view);

        return view;
    }

    /**
     * 초기화, 더보기 버튼 터치 이벤트 및 클릭 이벤트 설정, 달력 날짜 클릭 이벤트 설정
     *
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        getActivity().setTitle(R.string.nav_meal);

        mBreakfastTV = (TextView) rootView.findViewById(R.id.tv_meal_breakfast);
        mLunchTV = (TextView) rootView.findViewById(R.id.tv_meal_lunch);
        mDinnerTV = (TextView) rootView.findViewById(R.id.tv_meal_dinner);
        mBreakfastExpandBtn = (Button) rootView.findViewById(R.id.btn_meal_expandbreakfast);
        mLunchExpandBtn = (Button) rootView.findViewById(R.id.btn_meal_expandlunch);
        mDinnerExpandBtn = (Button) rootView.findViewById(R.id.btn_meal_expanddinner);

        mMoreDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_expand_more_white_18dp);
        mMoreDrawable.setColorFilter(
                ContextCompat.getColor(getContext(), R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        mLessDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_expand_less_white_18dp);
        mLessDrawable.setColorFilter(
                ContextCompat.getColor(getContext(), R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);

        // display meal information as today initially
        try {
            loadMeal(new Date());
        } catch (IOException e) {
            System.out.println("IOException in MealFragment: loadMeal()");
            e.printStackTrace();
        }

        mBreakfastExpandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBreakfastExpandBtn.isSelected()) {
                    // collapse and display meal info
                    collapse(BREAKFAST);
                } else {
                    // expand and display allergy info
                    expand(BREAKFAST);
                }
            }
        });
        mLunchExpandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLunchExpandBtn.isSelected()) {
                    collapse(LUNCH);
                } else {
                    expand(LUNCH);
                }
            }
        });
        mDinnerExpandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDinnerExpandBtn.isSelected()) {
                    collapse(DINNER);
                } else {
                    expand(DINNER);
                }
            }
        });

        CalendarView calendarView = (CalendarView) rootView.findViewById(R.id.calendar_meal);
        calendarView.setOnDateClickListener(new CalendarView.OnDateClickListener() {
            @Override
            public void onDateClick(@NonNull Date selectedDate) {
                clear();
                // load the meal information of selected date from server when select date
                try {
                    loadMeal(selectedDate);
                } catch (IOException e) {
                    System.out.println("IOException in MealFragment: loadMeal()");
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * display meal information
     *
     * @param meal Meal object contains meal information to display
     */
    private void bind(Meal meal) {
        mBreakfastTV.setText(meal.getBreakfast());
        mLunchTV.setText(meal.getLunch());
        mDinnerTV.setText(meal.getDinner());
    }

    /**
     * change to collapse image of button and display meal information
     *
     * @param when Integer constant of meal time to collapse
     */
    private void collapse(int when) {
        TextView textView = null;
        Button button = null;
        String mealMenu = null;

        switch (when) {
            case BREAKFAST:
                textView = mBreakfastTV;
                button = mBreakfastExpandBtn;
                if (mSelectedDayMeal != null) mealMenu = mSelectedDayMeal.getBreakfast();
                break;
            case LUNCH:
                textView = mLunchTV;
                button = mLunchExpandBtn;
                if (mSelectedDayMeal != null) mealMenu = mSelectedDayMeal.getLunch();
                break;
            case DINNER:
                textView = mDinnerTV;
                button = mDinnerExpandBtn;
                if (mSelectedDayMeal != null) mealMenu = mSelectedDayMeal.getDinner();
                break;
            default:
                break;
        }

        textView.setText(mealMenu);
        button.setText(R.string.meal_allergy);

        Drawable moreDrawable =
                ContextCompat.getDrawable(getContext(), R.drawable.ic_expand_more_white_18dp);
        button.setCompoundDrawablesWithIntrinsicBounds(null, null, moreDrawable, null);

        button.setSelected(false);
    }

    /**
     * change to expand image of button and display allergy information
     *
     * @param when Integer constant of meal time to expand
     */
    private void expand(int when) {
        TextView textView = null;
        Button button = null;
        String mealAllergy = null;

        switch (when) {
            case BREAKFAST:
                textView = mBreakfastTV;
                button = mBreakfastExpandBtn;
                if (mSelectedDayMeal != null) mealAllergy = mSelectedDayMeal.getBreakfastAllergy();
                break;
            case LUNCH:
                textView = mLunchTV;
                button = mLunchExpandBtn;
                if (mSelectedDayMeal != null) mealAllergy = mSelectedDayMeal.getLunchAllergy();
                break;
            case DINNER:
                textView = mDinnerTV;
                button = mDinnerExpandBtn;
                if (mSelectedDayMeal != null) mealAllergy = mSelectedDayMeal.getDinnerAllergy();
                break;
            default:
                break;
        }

        textView.setText(mealAllergy);
        button.setText(R.string.meal_meal);

        Drawable lessDrawable =
                ContextCompat.getDrawable(getContext(), R.drawable.ic_expand_less_white_18dp);
        lessDrawable.setColorFilter(
                ContextCompat.getColor(getContext(), R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        button.setCompoundDrawablesWithIntrinsicBounds(null, null, lessDrawable, null);

        button.setSelected(true);
    }

    /**
     * empty the TextView that displaying meal information and set expand button as expanded state
     */
    private void clear() {
        mBreakfastTV.setText(null);
        mLunchTV.setText(null);
        mDinnerTV.setText(null);
        mBreakfastExpandBtn.setText(R.string.meal_allergy);
        mLunchExpandBtn.setText(R.string.meal_allergy);
        mDinnerExpandBtn.setText(R.string.meal_allergy);
        mBreakfastExpandBtn.setCompoundDrawablesWithIntrinsicBounds(null, null, mMoreDrawable, null);
        mLunchExpandBtn.setCompoundDrawablesWithIntrinsicBounds(null, null, mMoreDrawable, null);
        mDinnerExpandBtn.setCompoundDrawablesWithIntrinsicBounds(null, null, mMoreDrawable, null);
    }

    /**
     * load the meal information from server
     *
     * @return Meal object that contains the meal information of the date
     * @throws IOException
     * @throws JSONException
     */
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
                                        mSelectedDayMeal = meal;
                                        bind(meal);
                                    } catch (JSONException e) {
                                        System.out.println("JSONException in MealFragment: GET /school/meal");
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
                            System.out.println("Error in MealFragment: GET /school/meal");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException in MealFragment: GET /school/meal");
            e.printStackTrace();
        }
    }

}
