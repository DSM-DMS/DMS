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
     * Initializes the first display and the tab layout.
     * @param rootView view to find child views
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
     * Replaces old fragment with new fragment
     * @param fragment fragment to replace
     */
    private void replaceFragment(Fragment fragment) {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.relativelayout_rewardscoreapply_container, fragment)
                .commit();
    }

}
