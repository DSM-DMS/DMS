package com.dms.beinone.application;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by BeINone on 2017-01-18.
 */

public class NoticeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(R.string.nav_notice);
        View view = inflater.inflate(R.layout.fragment_notice, container, false);

        init(view);

        return view;
    }

    /**
     * 초기화, RecyclerView 세팅
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        EmptySupportedRecyclerView recyclerView =
                (EmptySupportedRecyclerView) rootView.findViewById(R.id.rv_notice);

        View emptyView = rootView.findViewById(R.id.view_notice_empty);
        recyclerView.setEmptyView(emptyView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(new NoticeAdapter());

    }

}
