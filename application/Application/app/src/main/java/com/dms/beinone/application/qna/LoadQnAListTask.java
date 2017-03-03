package com.dms.beinone.application.qna;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.dms.beinone.application.JSONParser;
import com.dms.beinone.application.R;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Commands;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BeInone on 2017-02-22.
 */

public class LoadQnAListTask extends AsyncTask<Void, Void, Object[]> {

    private static final int LIMIT = 10;

    private Context mContext;
    private RecyclerView mRecyclerView;

    public LoadQnAListTask(Context context, RecyclerView recyclerView) {
        mContext = context;
        mRecyclerView = recyclerView;
    }

    @Override
    protected Object[] doInBackground(Void... params) {
        Object[] results = null;

        try {
            results = loadQnAList();
        } catch (IOException e) {
            return null;
        } catch (JSONException e) {
            return null;
        }

        return results;
    }

    @Override
    protected void onPostExecute(Object[] results) {
        super.onPostExecute(results);

        if (results != null) {
            int code = (int) results[0];
            List<QnA> qnaList = (ArrayList<QnA>) results[1];

            if (code == 200) {
                // success
                mRecyclerView.setAdapter(new QnAAdapter(mContext, qnaList));

                // increase the page number if completed loading successfully
                QnAFragment.page++;
            } else if (code == 204) {
                // empty
                Toast.makeText(mContext, R.string.qna_empty, Toast.LENGTH_SHORT).show();
            } else {
                // error
                Toast.makeText(mContext, R.string.qna_error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Object[] loadQnAList() throws IOException, JSONException {
        JSONObject requestJSONObject = new JSONObject();
        requestJSONObject.put("page", QnAFragment.page);
        requestJSONObject.put("limit", LIMIT);

        Response response = HttpBox.post()
                .setCommand(Commands.LOAD_QNA_LIST)
                .putBodyData(requestJSONObject)
                .push();

        JSONObject responseJSONObject = response.getJsonObject();

        int code = response.getCode();
        List<QnA> qnaList = null;
        if (code == 200) {
            qnaList = JSONParser.parseQnAListJSON(responseJSONObject);
        }

        return new Object[] { code, qnaList };
    }
}