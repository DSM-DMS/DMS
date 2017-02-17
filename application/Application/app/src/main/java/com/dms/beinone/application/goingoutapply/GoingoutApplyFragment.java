package com.dms.beinone.application.goingoutapply;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.beinone.application.dmsview.DMSButton;
import com.dms.beinone.application.dmsview.DMSToggleButton;

/**
 * Created by BeINone on 2017-01-16.
 */

public class GoingoutApplyFragment extends Fragment {

    private static final int SATURDAY = 1;
    private static final int SUNDAY = 2;
    private static final int BOTH = 3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goingoutapply, container, false);
        init(view);

        return view;
    }

    /**
     * 초기화, 신청 버튼 클릭 이벤트 설정
     *
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        getActivity().setTitle(R.string.nav_goingoutapply);

        final DMSToggleButton saturdayTB =
                (DMSToggleButton) rootView.findViewById(R.id.tb_goingoutapply_saturday);
        final DMSToggleButton sundayTB =
                (DMSToggleButton) rootView.findViewById(R.id.tb_goingoutapply_sunday);

        DMSButton applyBtn = (DMSButton) rootView.findViewById(R.id.btn_goingoutapply_apply);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (saturdayTB.isChecked()) {
                    apply(SATURDAY);
                } else if (sundayTB.isChecked()) {
                    apply(SUNDAY);
                } else if (saturdayTB.isChecked() && sundayTB.isChecked()) {
                    apply(BOTH);
                } else {
                    Toast.makeText(getContext(), R.string.goingoutapply_nochecked, Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    private void apply(int day) {

    }

//    private class ApplyGoingoutTask extends AsyncTask<String, Void, Void> {
//
//        @Override
//        protected Void doInBackground(String... params) {
//            String id = params[0];
//            String d
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//        }
//    }

}
