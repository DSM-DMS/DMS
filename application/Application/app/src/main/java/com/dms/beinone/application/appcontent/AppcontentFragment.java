package com.dms.beinone.application.appcontent;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dms.beinone.application.EmptySupportedRecyclerView;
import com.dms.beinone.application.R;
import com.dms.beinone.application.RecyclerViewUtils;

/**
 * Created by BeINone on 2017-01-26.
 */

public class AppcontentFragment extends Fragment {

    private EmptySupportedRecyclerView mRecyclerView;

    public static int page = 1;

    public static AppcontentFragment newInstance(Context context, int category) {
        Bundle args = new Bundle();
        args.putInt(context.getString(R.string.ARGS_CATEGORY), category);

        AppcontentFragment fragment = new AppcontentFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appcontent, container, false);
        init(view);

        return view;
    }

    /**
     * 초기화, RecyclerView 세팅
     *
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        int category = getArguments().getInt(getString(R.string.ARGS_CATEGORY));

        String title = null;
        switch (category) {
            case Appcontent.NOTICE:
                title = getString(R.string.nav_notice);
                break;
            case Appcontent.NEWSLETTER:
                title = getString(R.string.nav_newsletter);
                break;
            case Appcontent.COMPETITION:
                title = getString(R.string.nav_competition);
                break;
            default:
                break;
        }
        getActivity().setTitle(title);

        mRecyclerView = (EmptySupportedRecyclerView) rootView.findViewById(R.id.rv_appcontent);

        View emptyView = rootView.findViewById(R.id.view_appcontent_empty);
        switch (category) {
            case Appcontent.NOTICE:
                ((TextView) emptyView).setText(R.string.notice_empty);
                break;
            case Appcontent.NEWSLETTER:
                ((TextView) emptyView).setText(R.string.newsletter_empty);
                break;
            case Appcontent.COMPETITION:
                ((TextView) emptyView).setText(R.string.competition_empty);
                break;
            default: break;
        }
        RecyclerViewUtils.setupRecyclerView(mRecyclerView, getContext(), emptyView);

        new LoadAppcontentListTask(getContext(), category, mRecyclerView).execute(page++);
    }

}
