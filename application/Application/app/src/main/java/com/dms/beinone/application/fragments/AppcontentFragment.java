package com.dms.beinone.application.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.views.custom.EmptySupportedRecyclerView;
import com.dms.beinone.application.OnMoreBtnClickListener;
import com.dms.beinone.application.R;
import com.dms.beinone.application.views.adapters.AppcontentAdapter;
import com.dms.beinone.application.models.Appcontent;
import com.dms.beinone.application.utils.JSONParser;
import com.dms.beinone.application.managers.RecyclerViewManager;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by BeINone on 2017-01-26.
 */

public class AppcontentFragment extends Fragment {

    public static int page = 1;

    private int mCategory;
    private EmptySupportedRecyclerView mRecyclerView;

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

    @Override
    public void onDestroy() {
        super.onDestroy();

        // initialize the page on destroy the fragment
        page = 1;
    }

    /**
     * 초기화, RecyclerView 세팅
     *
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        mCategory = getArguments().getInt(getString(R.string.ARGS_CATEGORY));

        String title = null;
        switch (mCategory) {
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
        switch (mCategory) {
            case Appcontent.NOTICE:
                ((TextView) emptyView).setText(R.string.appcontent_notice_empty);
                break;
            case Appcontent.NEWSLETTER:
                ((TextView) emptyView).setText(R.string.appcontent_newsletter_empty);
                break;
            case Appcontent.COMPETITION:
                ((TextView) emptyView).setText(R.string.appcontent_competition_empty);
                break;
            default:
                break;
        }
        RecyclerViewManager.setupRecyclerView(mRecyclerView, getContext(), emptyView);

        try {
            loadAppcontentList();
        } catch (IOException e) {
            System.out.println("IOException in AppcontentFragment: GET /post/school/appcontent/list");
            e.printStackTrace();
        }
    }

    private void loadAppcontentList() throws IOException {
        String path = "";
        switch (mCategory) {
            case Appcontent.NOTICE:
                path = "/post/school/notice/list";
                break;
            case Appcontent.NEWSLETTER:
                path = "/post/school/newsletter/list";
                break;
            case Appcontent.COMPETITION:
                path = "/post/school/competition/list";
                break;
            default:
                break;
        }

        try {
            JSONObject params = new JSONObject();
            params.put("category", mCategory);
            params.put("page", AppcontentFragment.page);

            HttpBox.get(getContext(), path)
                    .putQueryString(params)
                    .push(new HttpBoxCallback() {
                        @Override
                        public void done(Response response) {
                            int errorMsgResId = 0;
                            int emptyMsgResId = 0;
                            switch (mCategory) {
                                case Appcontent.NOTICE:
                                    errorMsgResId = R.string.appcontent_notice_error;
                                    emptyMsgResId = R.string.appcontent_notice_empty;
                                    break;
                                case Appcontent.NEWSLETTER:
                                    errorMsgResId = R.string.appcontent_newsletter_error;
                                    emptyMsgResId = R.string.appcontent_newsletter_empty;
                                    break;
                                case Appcontent.COMPETITION:
                                    errorMsgResId = R.string.appcontent_competition_error;
                                    emptyMsgResId = R.string.appcontent_competition_empty;
                                    break;
                                default:
                                    break;
                            }

                            switch (response.getCode()) {
                                case HttpBox.HTTP_OK:
                                    try {
                                        List<Appcontent> appcontents = JSONParser.parseAppcontentListJSON(response.getJsonArray());
                                        if (mRecyclerView.getAdapter() == null) {
                                            // set a new adapter if there is no adapter on recycler view
                                            mRecyclerView.setAdapter(new AppcontentAdapter(getContext(), mCategory, appcontents,
                                                    new OnMoreBtnClickListener() {
                                                        @Override
                                                        public void onMoreBtnClick() {
                                                            try {
                                                                loadAppcontentList();
                                                            } catch (IOException e) {
                                                                System.out.println("IOException in AppcontentFragment: GET /post/school/appcontent/list");
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    }));
                                        } else {
                                            // if there is adapter on recycler view, add items
                                            ((AppcontentAdapter) mRecyclerView.getAdapter()).addAll(appcontents);
                                        }
                                        // increase the page number if completed loading successfully
                                        AppcontentFragment.page++;
                                    } catch (JSONException e) {
                                        System.out.println("JSONException in AppcontentFragment: GET /post/school/appcontent/list");
                                        e.printStackTrace();
                                    }
                                    break;
                                case HttpBox.HTTP_NO_CONTENT:
                                    Toast.makeText(getContext(), emptyMsgResId, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                    Toast.makeText(getContext(), errorMsgResId, Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void err(Exception e) {
                            System.out.println("Error in AppcontentFragment: GET /post/school/appcontent/list");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException AppcontentFragment: GET /post/school/appcontent/list");
            e.printStackTrace();
        }
    }

}
