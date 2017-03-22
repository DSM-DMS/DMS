package com.dms.beinone.application.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dms.beinone.application.R;

/**
 * Created by BeINone on 2017-03-12.
 */

public class HomeMealFragment extends Fragment {

    public static HomeMealFragment newInstance(Context context, String meal) {
        Bundle args = new Bundle();
        args.putString(context.getString(R.string.ARGS_MEAL), meal);

        HomeMealFragment fragment = new HomeMealFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_meal, container, false);
        init(view);

        return view;
    }

    /**
     * 초기화
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        String meal = getArguments().getString(getString(R.string.ARGS_MEAL));
        TextView mealTV = (TextView) rootView.findViewById(R.id.tv_home_meal);
        mealTV.setText(meal);
    }

}
