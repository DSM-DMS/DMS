package com.dms.beinone.application.qna;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dms.beinone.application.EmptySupportedRecyclerView;
import com.dms.beinone.application.R;
import com.dms.beinone.application.utils.RecyclerViewUtils;

/**
 * Created by BeINone on 2017-01-23.
 */

public class QnAFragment extends Fragment {

    public static int page = 1;

    private FloatingActionButton mFAB;
    private EmptySupportedRecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qna, container, false);
        init(view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mFAB.setVisibility(View.VISIBLE);

        new LoadQnAListTask(getContext(), mRecyclerView).execute();
    }

    @Override
    public void onStop() {
        super.onStop();
        mFAB.setVisibility(View.INVISIBLE);

        // initialize the page on stop the fragment
        page = 1;
    }

    /**
     * 초기화, RecyclerView 세팅
     *
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        getActivity().setTitle(R.string.nav_qna);

        mFAB = (FloatingActionButton) getActivity().findViewById(R.id.fab_main);
        mFAB.setVisibility(View.VISIBLE);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), QnAUploadActivity.class));
            }
        });

        mRecyclerView = (EmptySupportedRecyclerView) rootView.findViewById(R.id.rv_qna);

        View emptyView = rootView.findViewById(R.id.view_qna_empty);
        RecyclerViewUtils.setupRecyclerView(mRecyclerView, getContext(), emptyView);
    }

}
