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

public class LoadAppcontentListTask extends AsyncTask<Void, Void, List<Appcontent>> {

    private Context mContext;
    private int mCategory;
    private RecyclerView mRecyclerView;

    public LoadAppcontentListTask(Context context, int category, RecyclerView recyclerView) {
        mContext = context;
        mCategory = category;
        mRecyclerView = recyclerView;
    }

    @Override
    protected List<Appcontent> doInBackground(Void... params) {
        List<Appcontent> appcontentList = null;

        try {
            appcontentList = loadAppcontentList();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return appcontentList;
    }

    @Override
    protected void onPostExecute(List<Appcontent> appcontentList) {
        super.onPostExecute(appcontentList);

        int errorMsgResId = -1;
        int emptyMsgResId = -1;

        if (mCategory == Appcontent.NOTICE) {
            errorMsgResId = R.string.appcontent_notice_error;
            emptyMsgResId = R.string.appcontent_notice_empty;
        } else if (mCategory == Appcontent.NEWSLETTER) {
            errorMsgResId = R.string.appcontent_newsletter_error;
            emptyMsgResId = R.string.appcontent_newsletter_empty;
        } else {
            errorMsgResId = R.string.appcontent_competition_error;
            emptyMsgResId = R.string.appcontent_competition_empty;
        }

        if (appcontentList == null) {
            // error
            Toast.makeText(mContext, errorMsgResId, Toast.LENGTH_SHORT).show();
        } else if (appcontentList.size() == 0) {
            // empty
            Toast.makeText(mContext, emptyMsgResId, Toast.LENGTH_SHORT).show();
        } else {
            // success
            if (mRecyclerView.getAdapter() == null) {
                // set a new adapter if there is no adapter on recycler view
                mRecyclerView.setAdapter(new AppcontentAdapter(mContext, mCategory, appcontentList));
            } else {
                // if there is adapter on recycler view, add items
                ((AppcontentAdapter) mRecyclerView.getAdapter()).addAll(appcontentList);
            }

            // increase the page number if completed loading successfully
            AppcontentFragment.page++;
        }
    }

    private List<Appcontent> loadAppcontentList() throws IOException, JSONException {
        JSONObject requestJSONObject = new JSONObject();
        requestJSONObject.put("category", mCategory);
        requestJSONObject.put("page", AppcontentFragment.page);

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