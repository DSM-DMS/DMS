package com.dms.beinone.application.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dms.beinone.application.views.custom.EmptySupportedRecyclerView;
import com.dms.beinone.application.R;
import com.dms.beinone.application.views.adapters.FAQAdapter;
import com.dms.beinone.application.models.FAQ;
import com.dms.beinone.application.utils.JSONParser;
import com.dms.beinone.application.managers.RecyclerViewManager;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by BeINone on 2017-01-19.
 */

public class FAQFragment extends Fragment {

    private EmptySupportedRecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq, container, false);
        init(view);

        return view;
    }

    /**
     * 초기화, RecyclerView 세팅
     *
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        getActivity().setTitle(R.string.faq);

        mRecyclerView = (EmptySupportedRecyclerView) rootView.findViewById(R.id.rv_faq);

        View emptyView = rootView.findViewById(R.id.view_faq_empty);
        RecyclerViewManager.setupRecyclerView(mRecyclerView, getContext(), emptyView);

        try {
            loadFAQList();
        } catch (IOException e) {
            System.out.println("IOException in FAQFragment: loadFAQList()");
            e.printStackTrace();
        }
    }

    private void loadFAQList() throws IOException {
        HttpBox.get(getContext(), "/post/faq/list")
                .push(new HttpBoxCallback() {
                    @Override
                    public void done(Response response) {
                        int code = response.getCode();
                        switch (code) {
                            case HttpBox.HTTP_OK:
                                try {
                                    List<FAQ> faqs = JSONParser.parseFAQJSON(response.getJsonObject());
                                    mRecyclerView.setAdapter(new FAQAdapter(getContext(), faqs));
                                } catch (JSONException e) {
                                    System.out.println("JSONException in FAQFragment: /post/faq/list");
                                    e.printStackTrace();
                                }
                                break;
                            case HttpBox.HTTP_NO_CONTENT:
//                                Toast.makeText(getContext(), R.string.faq_no_content, Toast.LENGTH_SHORT).show();
                                break;
                            case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                Toast.makeText(getContext(), R.string.faq_internal_server_error, Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void err(Exception e) {
                        System.out.println("Error in FAQFragment: /post/faq/list");
                        e.printStackTrace();
                    }
                });
    }
}
