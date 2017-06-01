package com.dms.beinone.application.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
        View view = inflater.inflate(R.layout.fragment_rewardscore, container, false);
        init(view);

        return view;
    }

    /**
     * 초기화, Tab 선택 이벤트 설정
     *
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        getActivity().setTitle(R.string.rewardscore_apply);

        mRewardscoreFragment = RewardscoreFragment.newInstance(new RewardscoreFragment.OnApplyBtnClickListener() {
            @Override
            public void onApplyBtnClick(String content) {
                try {
                    applyRewardscore(null, content);
                } catch (IOException e) {
                    System.out.println("IOException in RewardscoreApplyFragment: applyRewardscore()");
                    e.printStackTrace();
                }
            }
        });
        mRecommendFragment = RecommendFragment.newInstance(new RecommendFragment.OnApplyBtnClickListener() {
            @Override
            public void onApplyBtnClick(String target, String content) {
                try {
                    applyRewardscore(target, content);
                } catch (IOException e) {
                    System.out.println("IOException in RewardscoreApplyFragment: applyRewardscore()");
                    e.printStackTrace();
                }
            }
        });

        // 초기 화면 설정
        replaceFragment(mRewardscoreFragment);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tablayout_rewardscore);
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
     *
     * @param fragment 교체할 새로운 Fragment
     */
    private void replaceFragment(Fragment fragment) {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_rewardscore_container, fragment)
                .commit();
    }

    private void applyRewardscore(String target, String content) throws IOException {
        try {
            JSONObject params = new JSONObject();
            if (target != null) {
                params.put("target", target);
            }
            params.put("content", content);

            HttpBox.post(getContext(), "/apply/merit")
                    .putBodyData(params)
                    .push(new HttpBoxCallback() {
                        @Override
                        public void done(Response response) {
                            int code = response.getCode();
                            switch (code) {
                                case HttpBox.HTTP_CREATED:
                                    Toast.makeText(getContext(), R.string.rewardscore_created, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_BAD_REQUEST:
                                    Toast.makeText(getContext(), R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                    Toast.makeText(getContext(), R.string.rewardscore_internal_server_error, Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void err(Exception e) {
                            System.out.println("Error in RecommendFragment: POST /apply/merit");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException in RecommendFragment: POST /apply/merit");
            e.printStackTrace();
        }
    }
}
