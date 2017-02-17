package com.dms.beinone.application.qna;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dms.beinone.application.EmptySupportedRecyclerView;
import com.dms.beinone.application.JSONParser;
import com.dms.beinone.application.R;
import com.dms.beinone.application.RecyclerViewUtils;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Commands;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by BeINone on 2017-01-23.
 */

public class QnAFragment extends Fragment {

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
    public void onResume() {
        super.onResume();

        new LoadQnAListTask().execute();
    }

    /**
     * 초기화, RecyclerView 세팅
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        getActivity().setTitle(R.string.nav_qna);

        mFAB = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        mFAB.setVisibility(View.VISIBLE);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), QnAWriteActivity.class));
            }
        });

        mRecyclerView = (EmptySupportedRecyclerView) rootView.findViewById(R.id.rv_qna);

        View emptyView = rootView.findViewById(R.id.view_qna_empty);
        RecyclerViewUtils.setupRecyclerView(mRecyclerView, getContext(), emptyView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mFAB.setVisibility(View.INVISIBLE);
    }

    private class LoadQnAListTask extends AsyncTask<Void, Void, List<QnA>> {

        @Override
        protected List<QnA> doInBackground(Void... params) {
            List<QnA> qnaList = null;

            try {
                qnaList = loadQnAList();
            } catch (IOException e) {
                return null;
            } catch (JSONException e) {
                return null;
            }

            return qnaList;
        }

        @Override
        protected void onPostExecute(List<QnA> qnaList) {
            super.onPostExecute(qnaList);

            if (qnaList == null) {
                Toast.makeText(getContext(), R.string.qna_error, Toast.LENGTH_SHORT).show();
            } else {
                mRecyclerView.setAdapter(new QnAAdapter(getContext(), qnaList));
            }
        }

        private List<QnA> loadQnAList() throws IOException, JSONException {
            Response response =
                    HttpBox.post().setCommand(Commands.LOAD_QNA_LIST).putBodyData().push();

            JSONObject responseJSONObject = response.getJsonObject();
            return JSONParser.parseQnAListJSON(responseJSONObject);
        }
    }

}
