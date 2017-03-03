package com.dms.beinone.application.comment;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    private EditText mCommentET;

    private SharedPreferences mAccountPrefs;
    private int mNo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        // display back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccountPrefs = getSharedPreferences(getString(R.string.PREFS_ACCOUNT), MODE_PRIVATE);

        mRecyclerView = (EmptySupportedRecyclerView) findViewById(R.id.rv_comment);

        mCommentET = (EditText) findViewById(R.id.et_comment_comment);
        Button postBtn = (Button) findViewById(R.id.ib_comment_post);
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = mCommentET.getText().toString().trim();
                String id = mAccountPrefs.getString("id", "");

                new UploadCommentTask().execute(mNo, comment, id);
            }
        });

        // loads comments of the article
        mNo = getIntent().getIntExtra(getString(R.string.EXTRA_NO), 0);
        if (mNo != 0) {
            new LoadCommentTask().execute(mNo);
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
        protected List<Comment> doInBackground(Integer... params) {
            List<Comment> commentList = null;

            try {
                int no = params[0];
                commentList = loadComment(no);
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

    private void clearView() {
        mCommentET.setText("");
    }

    private class UploadCommentTask extends AsyncTask<Object, Void, Integer> {

        @Override
        protected Integer doInBackground(Object... params) {
            int code = -1;

            try {
                int no = (int) params[0];
                String content = params[1].toString();
                String writer = params[2].toString();

                code = uploadComment(no, content, writer);
            } catch (IOException e) {
                return -1;
            } catch (JSONException e) {
                return -1;
            }

            return code;
        }

        @Override
        protected void onPostExecute(Integer code) {
            super.onPostExecute(code);

            if (code == 200) {
                // success
                clearView();
                new LoadCommentTask().execute(mNo);
            } else if (code == 204) {
                // failure
                Toast.makeText(CommentActivity.this, R.string.comment_upload_failure,
                        Toast.LENGTH_SHORT).show();
            } else {
                // error
                Toast.makeText(CommentActivity.this, R.string.comment_upload_error,
                        Toast.LENGTH_SHORT).show();
            }
        }

        private int uploadComment(int no, String content, String writer)
            throws IOException, JSONException {

            JSONObject requestJSONObject = new JSONObject();
            requestJSONObject.put("no", no);
            requestJSONObject.put("content", content);
            requestJSONObject.put("writer", writer);

            Response response = HttpBox.post()
                    .setCommand(Commands.UPLOAD_QNA_COMMENT)
                    .putBodyData(requestJSONObject)
                    .push();

            return response.getCode();
        }
    }

}
