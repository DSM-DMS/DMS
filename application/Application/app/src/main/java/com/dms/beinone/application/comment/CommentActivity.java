package com.dms.beinone.application.comment;

import android.content.SharedPreferences;
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
import com.dms.beinone.application.utils.RecyclerViewUtils;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
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

        View emptyView = findViewById(R.id.view_comment_empty);
        RecyclerViewUtils.setupRecyclerView(mRecyclerView, this, emptyView);

        mCommentET = (EditText) findViewById(R.id.et_comment_comment);
        ImageButton postIB = (ImageButton) findViewById(R.id.ib_comment_post);
        postIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = mCommentET.getText().toString().trim();
                try {
                    uploadComment(mNo, comment);
                } catch (IOException e) {
                    System.out.println("IOException in CommentActivity: POST /post/qna/comment");
                    e.printStackTrace();
                }
            }
        });

        // loads comments of the article
        mNo = getIntent().getIntExtra(getString(R.string.EXTRA_NO), 0);
        if (mNo != 0) {
            try {
                loadComment(mNo);
            } catch (IOException e) {
                System.out.println("IOException in CommentActivity: GET /post/qna/comment");
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;
    }

    private void clearView() {
        mCommentET.setText("");
    }

    private void loadComment(int no) throws IOException {
        try {
            JSONObject params = new JSONObject();
            params.put("no", String.valueOf(no));

            HttpBox.get(CommentActivity.this, "/post/qna/comment")
                    .putQueryString(params)
                    .push(new HttpBoxCallback() {
                        @Override
                        public void done(Response response) {
                            switch (response.getCode()) {
                                case HttpBox.HTTP_OK:
                                    try {
                                        List<Comment> comments = JSONParser.parseCommentJSON(response.getJsonObject());
                                        mRecyclerView.setAdapter(new CommentAdapter(comments));
                                    } catch (JSONException e) {
                                        System.out.println("JSONException in AppcontentFragment: GET /post/qna/comment");
                                        e.printStackTrace();
                                    }
                                    break;
                                case HttpBox.HTTP_NO_CONTENT:
//                                    Toast.makeText(CommentActivity.this, R.string.comment_load_no_content, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_BAD_REQUEST:
                                    Toast.makeText(CommentActivity.this, R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                    Toast.makeText(CommentActivity.this, R.string.comment_load_internal_server_error, Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void err(Exception e) {
                            System.out.println("Error in CommentActivity: GET /post/qna/comment");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException in CommentActivity: GET /post/qna/comment");
            e.printStackTrace();
        }
    }

    private void uploadComment(final int no, String content) throws IOException {
        try {
            JSONObject params = new JSONObject();
            params.put("no", no);
            params.put("content", content);

            HttpBox.post(CommentActivity.this, "/post/qna/comment")
                    .putBodyData(params)
                    .push(new HttpBoxCallback() {
                        @Override
                        public void done(Response response) {
                            int code = response.getCode();
                            switch (code) {
                                case HttpBox.HTTP_CREATED:
                                    clearView();
                                    try {
                                        loadComment(no);
                                    } catch (IOException e) {
                                        System.out.println("IOException in CommentActivity: POST /post/qna/comment");
                                        e.printStackTrace();
                                    }
                                    break;
                                case HttpBox.HTTP_BAD_REQUEST:
                                    Toast.makeText(CommentActivity.this, R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                    Toast.makeText(CommentActivity.this, R.string.comment_upload_internal_server_error, Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void err(Exception e) {
                            System.out.println("Error in CommentActivity: POST /post/qna/comment");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException in CommentActivity: POST /post/qna/comment");
            e.printStackTrace();
        }
    }

}
