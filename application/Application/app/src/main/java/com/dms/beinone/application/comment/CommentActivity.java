package com.dms.beinone.application.comment;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dms.beinone.application.EmptySupportedRecyclerView;
import com.dms.beinone.application.R;
import com.dms.beinone.application.utils.JSONParser;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Request;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        ImageButton postIB = (ImageButton) findViewById(R.id.ib_comment_post);
        postIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = mCommentET.getText().toString().trim();

                new UploadCommentTask().execute(mNo, comment);
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
            } catch (IOException e) {
                return null;
            } catch (JSONException e) {
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
            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("no", String.valueOf(no));

            Response response =
                    HttpBox.post(CommentActivity.this, "/post/qna/comment", Request.TYPE_GET)
                    .putBodyData(requestParams)
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

                code = uploadComment(no, content);
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            } catch (JSONException e) {
                e.printStackTrace();
                return -1;
            }

            return code;
        }

        @Override
        protected void onPostExecute(Integer code) {
            super.onPostExecute(code);

            if (code == 201) {
                // success
                clearView();
                new LoadCommentTask().execute(mNo);
            } else if (code == 400) {
                // failure
                Toast.makeText(CommentActivity.this, R.string.comment_upload_failure,
                        Toast.LENGTH_SHORT).show();
            } else {
                // error
                Toast.makeText(CommentActivity.this, R.string.comment_upload_error,
                        Toast.LENGTH_SHORT).show();
            }
        }

        private int uploadComment(int no, String content)
            throws IOException, JSONException {

            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("no", String.valueOf(no));
            requestParams.put("content", content);

            Response response =
                    HttpBox.post(CommentActivity.this, "/post/qna/comment", Request.TYPE_POST)
                    .putBodyData(requestParams)
                    .push();

            return response.getCode();
        }
    }

}
