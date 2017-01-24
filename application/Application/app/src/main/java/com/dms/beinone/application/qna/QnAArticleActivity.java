package com.dms.beinone.application.qna;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dms.beinone.application.R;
import com.dms.beinone.application.dmsview.DMSButton;

/**
 * Created by BeINone on 2017-01-23.
 */

public class QnAArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_article);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;
    }

    private void init() {
        // display back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DMSButton commentBtn = (DMSButton) findViewById(R.id.btn_qna_article_comment);
        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewComment();
            }
        });

        QnA qna = getIntent().getParcelableExtra(getString(R.string.EXTRA_QNA));
        bind(qna);
    }

    /**
     * sets text of article
     * @param qna QnA object that contains information of article
     */
    private void bind(QnA qna) {
        TextView titleTV = (TextView) findViewById(R.id.tv_qna_article_title);
        TextView questionerTV = (TextView) findViewById(R.id.tv_qna_article_questioner);
        TextView questionDateTV = (TextView) findViewById(R.id.tv_qna_article_questiondate);
        TextView questionContentTV = (TextView) findViewById(R.id.tv_qna_article_questioncontent);

        titleTV.setText(qna.getTitle());
        questionerTV.setText(qna.getQuestioner());
        questionDateTV.setText(qna.getQuestionDate());
        questionContentTV.setText(qna.getQuestionContent());
    }

    private void viewComment() {
        Intent intent = new Intent(this, QnACommentActivity.class);
        startActivity(intent);
    }

}
