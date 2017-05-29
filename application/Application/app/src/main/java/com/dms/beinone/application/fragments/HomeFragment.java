package com.dms.beinone.application.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dms.beinone.application.R;

import java.util.Date;

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
     *
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        getActivity().setTitle(R.string.nav_home);

        mMealViewPager = (ViewPager) rootView.findViewById(R.id.viewpager_home_meal);

        TabLayout mealTabLayout = (TabLayout) rootView.findViewById(R.id.tablayout_home_meal);
        mealTabLayout.setupWithViewPager(mMealViewPager);
    }
}
