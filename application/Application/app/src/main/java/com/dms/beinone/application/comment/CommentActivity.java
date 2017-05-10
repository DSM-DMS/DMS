package com.dms.beinone.application.comment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.dms.beinone.application.EmptySupportedRecyclerView;
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
 * Created by BeINone on 2017-01-24.
 */

public class CommentActivity extends AppCompatActivity {

    private EmptySupportedRecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        // display back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (EmptySupportedRecyclerView) findViewById(R.id.rv_comment);

        // loads comments of the article
        int no = getIntent().getIntExtra(getString(R.string.EXTRA_NO), 0);
        if (no != 0) {
            new LoadCommentTask().execute(no);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;
    }

    private class LoadCommentTask extends AsyncTask<Integer, Void, List<Comment>> {

        @Override
        protected List<Comment> doInBackground(Integer... nos) {
            List<Comment> commentList = null;

            try {
                commentList = loadComment(nos[0]);
            } catch (IOException ie) {
                return null;
            } catch (JSONException je) {
                return null;
            }

            return commentList;
        }

        @Override
        protected void onPostExecute(List<Comment> commentList) {
            super.onPostExecute(commentList);

            mRecyclerView.setAdapter(new CommentAdapter(commentList));
        }

        private List<Comment> loadComment(int no) throws IOException, JSONException {
            JSONObject requestJSONObject = new JSONObject();
            requestJSONObject.put("no", no);
            Response response = HttpBox.post()
                    .setCommand(Commands.LOAD_QNA_COMMENT)
                    .putBodyData(requestJSONObject)
                    .push();

            JSONObject responseJsonObject = response.getJsonObject();

            return JSONParser.parseCommentJSON(responseJsonObject);
        }
    }

}
