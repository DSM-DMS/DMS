package com.dms.beinone.application.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.beinone.application.views.custom.DMSButton;
import com.dms.beinone.application.models.QnA;
import com.dms.beinone.application.utils.JSONParser;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by BeINone on 2017-01-23.
 */

public class QnAArticleActivity extends AppCompatActivity {

    private SharedPreferences mAccountPrefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_article);
        setTitle(R.string.qna);
        // display back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccountPrefs = getSharedPreferences(getString(R.string.PREFS_ACCOUNT), MODE_PRIVATE);

        final int no = getIntent().getIntExtra(getString(R.string.EXTRA_NO), 0);

        DMSButton commentBtn = (DMSButton) findViewById(R.id.btn_qna_article_comment);
        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewComment(no);
            }
        });

        try {
            loadQnA(no);
        } catch (IOException e) {
            System.out.println("IOException in QnAArticleActivity: GET /post/qna");
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String id = mAccountPrefs.getString(getString(R.string.PREFS_ACCOUNT_ID), "");
        String writer = getIntent().getStringExtra(getString(R.string.EXTRA_WRITER));

        if (id.equals(writer)) {
            // show delete menu if writer's id and the ic_user's id are same
            getMenuInflater().inflate(R.menu.activity_article, menu);
            return true;
        } else {
            return super.onCreateOptionsMenu(menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;
    }

    /**
     * sets text of article
     *
     * @param qna QnA object that contains information of article
     */
    private void bind(QnA qna) {
        TextView titleTV = (TextView) findViewById(R.id.tv_qna_article_title);
        TextView writerTV = (TextView) findViewById(R.id.tv_qna_article_writer);
        TextView questionDateTV = (TextView) findViewById(R.id.tv_qna_article_questiondate);
        TextView questionContentTV = (TextView) findViewById(R.id.tv_qna_article_questioncontent);
        Button answerBtn = (Button) findViewById(R.id.btn_qna_article_answer);

        titleTV.setText(qna.getTitle());
        writerTV.setText(qna.getWriter());
        questionDateTV.setText(qna.getQuestionDate());
        questionContentTV.setText(qna.getQuestionContent());
        if (qna.getAnswerContent() == null) {
            answerBtn.setEnabled(false);
            answerBtn.setText("답변이 없습니다!");
        }
    }

    /**
     * start a new activity to display comments
     *
     * @param no index of the article
     */
    private void viewComment(int no) {
        Intent intent = new Intent(this, CommentActivity.class);
        intent.putExtra(getString(R.string.EXTRA_NO), no);
        startActivity(intent);
    }

    private void loadQnA(final int no) throws IOException {
        try {
            JSONObject params = new JSONObject();
            params.put("no", no);

            HttpBox.get(QnAArticleActivity.this, "/post/qna")
                    .putQueryString(params)
                    .push(new HttpBoxCallback() {
                        @Override
                        public void done(Response response) {
                            int code = response.getCode();
                            switch (code) {
                                case HttpBox.HTTP_OK:
                                    try {
                                        QnA qna = JSONParser.parseQnAJSON(response.getJsonObject(), no);
                                        String id = mAccountPrefs.getString(getString(R.string.PREFS_ACCOUNT_ID), "");
                                        if (qna.getWriter().equals(id)) {

                                        }
                                        bind(qna);
                                    } catch (JSONException e) {
                                        System.out.println("JSONException in QnAArticleActivity: GET /post/qna");
                                        e.printStackTrace();
                                    }
                                    break;
                                case HttpBox.HTTP_NO_CONTENT:
                                    Toast.makeText(QnAArticleActivity.this, R.string.qna_article_no_content, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_BAD_REQUEST:
                                    Toast.makeText(QnAArticleActivity.this, R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                    Toast.makeText(QnAArticleActivity.this, R.string.qna_article_internal_server_error, Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void err(Exception e) {
                            System.out.println("Error in QnAArticleActivity: GET /post/qna");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException in QnAArticleActivity: GET /post/qna");
            e.printStackTrace();
        }
    }
}
