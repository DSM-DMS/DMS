package com.dms.beinone.application.faq;

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
 * Created by BeINone on 2017-01-19.
 */

public class FAQFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(R.string.nav_faq);
        View view = inflater.inflate(R.layout.fragment_faq, container, false);

        init(view);

        return view;
    }

    /**
     * 초기화, RecyclerView 세팅
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        EmptySupportedRecyclerView recyclerView =
                (EmptySupportedRecyclerView) rootView.findViewById(R.id.rv_faq);

        View emptyView = rootView.findViewById(R.id.view_faq_empty);
        RecyclerViewUtils.setupRecyclerView(recyclerView, getContext(), emptyView);

        List<FAQTitle> faqTitleList = new ArrayList<>();
        List<FAQContent> faqContentList = new ArrayList<>();
        faqContentList.add(new FAQContent("질문입니다~"));
        faqTitleList.add(new FAQTitle("질문이요", faqContentList));
        faqTitleList.add(new FAQTitle("질문이요", faqContentList));
        recyclerView.setAdapter(new FAQAdapter(getContext(), faqTitleList));
    }

}
