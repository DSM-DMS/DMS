package com.dms.beinone.application.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dms.beinone.application.R;
import com.dms.beinone.application.dmsview.SlimDatePicker;
import com.dms.beinone.application.meal.LoadMealTask;
import com.dms.beinone.application.meal.Meal;

import java.util.Date;

import static com.dms.beinone.application.home.HomeMealFragment.newInstance;

/**
 * Created by BeINone on 2017-01-14.
 */

public class HomeFragment extends Fragment {

    private ViewPager mMealViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);

        return view;
    }

    /**
     * 초기화
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        getActivity().setTitle(R.string.nav_home);

        mMealViewPager = (ViewPager) rootView.findViewById(R.id.viewpager_home_meal);

        TabLayout mealTabLayout = (TabLayout) rootView.findViewById(R.id.tablayout_home_meal);
        mealTabLayout.setupWithViewPager(mMealViewPager);

        SlimDatePicker mealSDP = (SlimDatePicker) rootView.findViewById(R.id.sdp_home_meal);
        mealSDP.setOnDateChangedListener(new SlimDatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(Date date) {
                loadMeal(date);
            }
        });

        loadMeal(new Date());
    }

    private void loadMeal(Date date) {
        new LoadMealTask(getContext(), new LoadMealTask.OnPostExecuteListener() {
            @Override
            public void onPostExecute(Meal meal) {
                HomeMealFragment[] homeMealFragments = new HomeMealFragment[3];

                if (meal == null) {
                    homeMealFragments[0] = HomeMealFragment.newInstance(
                            getContext(), "급식 정보를 불러오지 못했습니다.");
                    homeMealFragments[1] = HomeMealFragment.newInstance(
                            getContext(), "급식 정보를 불러오지 못했습니다.");
                    homeMealFragments[2] = HomeMealFragment.newInstance(
                            getContext(), "급식 정보를 불러오지 못했습니다.");
//                    Toast.makeText(getContext(), R.string.meal_error, Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("testLog", meal.getBreakfast());
                    Log.d("testLog", meal.getLunch());
                    Log.d("testLog", meal.getDinner());
                    homeMealFragments[0] = newInstance(getContext(), meal.getBreakfast());
                    homeMealFragments[1] = newInstance(getContext(), meal.getLunch());
                    homeMealFragments[2] = newInstance(getContext(), meal.getDinner());
                }

                if (mMealViewPager.getAdapter() == null) {
                    mMealViewPager.setAdapter(new HomeMealFragmentPagerAdapter(
                            getFragmentManager(), homeMealFragments));
                } else {
                    ((HomeMealFragmentPagerAdapter) mMealViewPager.getAdapter())
                            .changeItems(homeMealFragments);
                }
            }
        }).execute(date);
    }

}
