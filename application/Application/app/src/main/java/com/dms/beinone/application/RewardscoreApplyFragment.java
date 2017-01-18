package com.dms.beinone.application;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by BeINone on 2017-01-17.
 */

public class RewardscoreApplyFragment extends Fragment {

    private static final int POS_REWARDSCORE = 0;
    private static final int POS_RECOMMEND = 1;

    private RewardscoreFragment mRewardscoreFragment;
    private RecommendFragment mRecommendFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(R.string.nav_rewardscoreapply);
        View view = inflater.inflate(R.layout.fragment_rewardscoreapply, container, false);

        init(view);

        return view;
    }

    /**
     * 초기화, Tab 선택 이벤트 설정
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        mRewardscoreFragment = new RewardscoreFragment();
        mRecommendFragment = new RecommendFragment();

        // 초기 화면 설정
        replaceFragment(mRewardscoreFragment);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tablayout_rewardscoreapply);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == POS_REWARDSCORE) {
                    replaceFragment(mRewardscoreFragment);
                } else if (tab.getPosition() == POS_RECOMMEND) {
                    replaceFragment(mRecommendFragment);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    /**
     * 기존의 Fragment를 새로운 Fragment로 교체
     * @param fragment 교체할 새로운 Fragment
     */
    private void replaceFragment(Fragment fragment) {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.relativelayout_rewardscoreapply_container, fragment)
                .commit();
    }

}
