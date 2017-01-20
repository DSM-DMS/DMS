package com.dms.beinone.application.rule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dms.beinone.application.EmptySupportedRecyclerView;
import com.dms.beinone.application.R;

/**
 * Created by BeINone on 2017-01-20.
 */

public class RuleFragment extends Fragment {

    FloatingActionButton mFAB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(R.string.nav_rule);
        View view = inflater.inflate(R.layout.fragment_rule, container, false);

        init(view);

        return view;
    }

    /**
     * 초기화
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        EmptySupportedRecyclerView recyclerView =
                (EmptySupportedRecyclerView) rootView.findViewById(R.id.rv_rule);

        View emptyView = rootView.findViewById(R.id.view_rule_empty);
        recyclerView.setEmptyView(emptyView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(new RuleAdapter(getContext(), ));
    }

}
