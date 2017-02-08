package com.dms.beinone.application.appcontent;

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
import java.util.List;

/**
 * Created by BeINone on 2017-02-03.
 */

public class LoadAppcontentListTask extends AsyncTask<Integer, Void, List<Appcontent>> {

    private Context mContext;
    private int mCategory;
    private RecyclerView mRecyclerView;

    public LoadAppcontentListTask(Context context, int category, RecyclerView recyclerView) {
        mContext = context;
        mCategory = category;
        mRecyclerView = recyclerView;
    }

    @Override
    protected List<Appcontent> doInBackground(Integer... params) {
        List<Appcontent> appcontentList = null;

        try {
            int page = params[0];
            appcontentList = loadAppcontentList(page);
        } catch (IOException ie) {
            return null;
        } catch (JSONException je) {
            return null;
        }

        return appcontentList;
    }

    @Override
    protected void onPostExecute(List<Appcontent> appcontentList) {
        super.onPostExecute(appcontentList);

        if (appcontentList == null) {
            // error
            Toast.makeText(mContext, R.string.notice_error, Toast.LENGTH_SHORT).show();
        } else if (appcontentList.size() == 0) {
            // failed
            Toast.makeText(mContext, R.string.notice_failure, Toast.LENGTH_SHORT).show();
        } else {
            // success
            if (mRecyclerView.getAdapter() == null) {
                // set a new adapter if there is no adapter on recycler view
                mRecyclerView.setAdapter(new AppcontentAdapter(mContext, mCategory, appcontentList));
            } else {
                // if there is adapter on recycler view, add items
                ((AppcontentAdapter) mRecyclerView.getAdapter()).addAll(appcontentList);
            }
        }
    }

    private List<Appcontent> loadAppcontentList(int page) throws IOException, JSONException {
        JSONObject requestJSONObject = new JSONObject();
        requestJSONObject.put("category", mCategory);
        requestJSONObject.put("page", page);

        int command = 0;

        switch (mCategory) {
            case Appcontent.NOTICE:
                command = Commands.LOAD_NOTICE_LIST;
                break;
            case Appcontent.NEWSLETTER:
                command = Commands.LOAD_NEWSLETTER_LIST;
                break;
            case Appcontent.COMPETITION:
                command = Commands.LOAD_COMPETITION_LIST;
                break;
            default:
                break;
        }

        Response response = HttpBox.post()
                .setCommand(command)
                .putBodyData(requestJSONObject)
                .push();

        return JSONParser.parseAppcontentListJSON(response.getJsonObject());
    }
}