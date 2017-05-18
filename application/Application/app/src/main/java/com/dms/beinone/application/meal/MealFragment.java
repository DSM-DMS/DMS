package com.dms.beinone.application.meal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.dms.beinone.application.R;
import com.dms.beinone.application.utils.DensityConverter;

/**
 * Created by BeINone on 2017-01-18.
 */

public class MealFragment extends Fragment {

    private ViewPager mViewPager;
    private ImageButton mPrevBtn;
    private ImageButton mNextBtn;

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

        mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager_meal);
        mPrevBtn = (ImageButton) rootView.findViewById(R.id.ib_meal_prev);
        mNextBtn = (ImageButton) rootView.findViewById(R.id.ib_meal_next);

        mViewPager.setPageMargin((int) DensityConverter.dpToPx(getContext(), 16));
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new MealPagerAdapter(getChildFragmentManager()));
        mViewPager.setCurrentItem(MealPagerAdapter.START_POSITION);
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            private int mCurrPageIndex = MealPagerAdapter.MIDDLE_INDEX;
//            private int mPrevPageIndex = MealPagerAdapter.MIDDLE_INDEX;
//
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                mCurrPageIndex = position;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                if (state == ViewPager.SCROLL_STATE_IDLE) {
//                    if (mCurrPageIndex < mPrevPageIndex) {
//                        ((MealPagerAdapter) mViewPager.getAdapter()).onPrev(mCurrPageIndex);
//                    } else if (mCurrPageIndex > mPrevPageIndex) {
//                        ((MealPagerAdapter) mViewPager.getAdapter()).onNext(mCurrPageIndex);
//                    }
//                    mPrevPageIndex = mCurrPageIndex;
//                }
//            }
//        });

        mPrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevDay();
            }
        });
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextDay();
            }
        });
    }

    private void prevDay() {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
    }

    private void nextDay() {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
    }
}
