//package com.dms.beinone.application.goingoutapply;
//
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.dms.beinone.application.R;
//import com.dms.beinone.application.utils.JSONParser;
//import com.dms.boxfox.networking.HttpBox;
//import com.dms.boxfox.networking.datamodel.Request;
//import com.dms.boxfox.networking.datamodel.Response;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//
///**
// * Created by BeINone on 2017-02-24.
// */
//
//public class NewGoingoutApplyFragment extends Fragment {
//
//    private static final int POS_SATURDAY = 0;
//    private static final int POS_SUNDAY = 1;
//
//    private GoingoutContentFragment mSaturdayFragment;
//    private GoingoutContentFragment mSundayFragment;
//    private TextView mApplyStatusTV;
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_goingoutapply_new, container, false);
//        init(view);
//
//        return view;
//    }
//
//    /**
//     * 초기화, Tab 선택 이벤트 설정
//     *
//     * @param rootView 필요한 뷰를 찾을 최상위 뷰
//     */
//    private void init(View rootView) {
//        getActivity().setTitle(R.string.nav_goingoutapply);
//
//        mSaturdayFragment = GoingoutContentFragment
//                .newInstance(getContext(), GoingoutContentFragment.SATURDAY);
//        mSundayFragment = GoingoutContentFragment
//                .newInstance(getContext(), GoingoutContentFragment.SUNDAY);
//        mApplyStatusTV = (TextView) rootView.findViewById(R.id.tv_goingoutapply_applystatus);
//
//
//
//        // 초기 화면 설정
//        replaceFragment(mSaturdayFragment);
//
//        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tablayout_goingoutapply);
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                if (tab.getPosition() == POS_SATURDAY) {
//                    replaceFragment(mSaturdayFragment);
//                } else if (tab.getPosition() == POS_SUNDAY) {
//                    replaceFragment(mSundayFragment);
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//    }
//
//    private void setApplyStatusTV(String applyStatus) {
//        mApplyStatusTV.setText(applyStatus);
//    }
//
//    /**
//     * 기존의 Fragment를 새로운 Fragment로 교체
//     *
//     * @param fragment 교체할 새로운 Fragment
//     */
//    private void replaceFragment(Fragment fragment) {
//        getChildFragmentManager()
//                .beginTransaction()
//                .replace(R.id.relativelayout_goingoutapply_container, fragment)
//                .commit();
//    }
//
//    private class LoadGoingoutApplyStatusTask extends AsyncTask<String, Void, Object[]> {
//
//        @Override
//        protected Object[] doInBackground(String... params) {
//            Object[] results = null;
//
//            try {
//                results = loadGoingoutApplyStatus();
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;
//            } catch (JSONException e) {
//                e.printStackTrace();
//                return null;
//            }
//
//            return results;
//        }
//
//        @Override
//        protected void onPostExecute(Object[] results) {
//            super.onPostExecute(results);
//
//            int code = (int) results[0];
//            String applyStatus = results[1].toString();
//
//            if (code == 200) {
//                // success
//                setApplyStatusTV(applyStatus);
//            } else if (code == 204) {
//                Toast.makeText(getContext(), R.string.goingoutapply_load_failure, Toast.LENGTH_SHORT)
//                        .show();
//            } else {
//                Toast.makeText(getContext(), R.string.goingoutapply_load_error, Toast.LENGTH_SHORT)
//                        .show();
//            }
//        }
//
//        private Object[] loadGoingoutApplyStatus() throws IOException, JSONException {
//            Response response = HttpBox.post(getContext(), "/apply/goingout", Request.TYPE_PUT)
//                    .push();
//
//            JSONObject responseJSONObject = response.getJsonObject();
//            String applyStatus = JSONParser.parseGoingoutApplyStatusJSON(responseJSONObject);
//
//            return new Object[]{response.getCode(), applyStatus};
//        }
//    }
//
//}
