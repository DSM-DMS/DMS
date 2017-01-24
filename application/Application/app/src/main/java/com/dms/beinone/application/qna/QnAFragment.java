package com.dms.beinone.application.qna;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dms.beinone.application.EmptySupportedRecyclerView;
import com.dms.beinone.application.R;
import com.dms.beinone.application.RecyclerViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BeINone on 2017-01-23.
 */

public class QnAFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(R.string.nav_qna);
        View view = inflater.inflate(R.layout.fragment_qna, container, false);

        init(view);

        return view;
    }

    /**
     * 초기화, RecyclerView 세팅
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        EmptySupportedRecyclerView recyclerView =
                (EmptySupportedRecyclerView) rootView.findViewById(R.id.rv_qna);

        View emptyView = rootView.findViewById(R.id.view_qna_empty);
        RecyclerViewUtils.setupRecyclerView(recyclerView, getContext(), emptyView);

        List<QnA> qnaList = new ArrayList<>();
        qnaList.add(new QnA("이거 어떻게 하죠?", "이거 어떻게 해야 할까요, 선생님?", "2017-01-23",
                "조성빈", null, null, true));
        qnaList.add(new QnA("이거 어떻게 하죠?", "이거 어떻게 해야 할까요, 선생님?", "2017-01-23",
                "조성빈", null, null, false));
        recyclerView.setAdapter(new QnAAdapter(getContext(), qnaList));
    }

}
