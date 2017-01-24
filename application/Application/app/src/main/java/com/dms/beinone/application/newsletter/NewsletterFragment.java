package com.dms.beinone.application.newsletter;

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
 * Created by BeINone on 2017-01-24.
 */

public class NewsletterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(R.string.nav_newsletter);
        View view = inflater.inflate(R.layout.fragment_newsletter, container, false);

        init(view);

        return view;
    }

    /**
     * 초기화, RecyclerView 세팅
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        EmptySupportedRecyclerView recyclerView =
                (EmptySupportedRecyclerView) rootView.findViewById(R.id.rv_newsletter);

        View emptyView = rootView.findViewById(R.id.view_newsletter_empty);
        RecyclerViewUtils.setupRecyclerView(recyclerView, getContext(), emptyView);

        List<Newsletter> newsletterList = new ArrayList<>();
        newsletterList.add(new Newsletter("가정통신문입니다.", "가정통신문이에요.", "조성빈", "2017-01-24"));
        newsletterList.add(new Newsletter("두번째입니다.", "두번째에요.", "마이스터부", "2017-01-24"));

        recyclerView.setAdapter(new NewsletterAdapter(getContext(), newsletterList));
    }

}
