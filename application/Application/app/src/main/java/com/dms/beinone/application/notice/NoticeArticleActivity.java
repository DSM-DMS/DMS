package com.dms.beinone.application.notice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.dms.beinone.application.R;

/**
 * Created by BeINone on 2017-01-23.
 */

public class NoticeArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_article);

        // display back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Notice notice = getIntent().getParcelableExtra(getString(R.string.EXTRA_NOTICE));
        bind(notice);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return false;
    }

    /**
     * sets text of article
     * @param notice Notice object that contains information of article
     */
    private void bind(Notice notice) {
        TextView titleTV = (TextView) findViewById(R.id.tv_notice_article_title);
        TextView writerTV = (TextView) findViewById(R.id.tv_notice_article_writer);
        TextView dateTV = (TextView) findViewById(R.id.tv_notice_article_date);
        TextView contentTV = (TextView) findViewById(R.id.tv_notice_article_content);

        titleTV.setText(notice.getTitle());
        writerTV.setText(notice.getWriter());
        dateTV.setText(notice.getDate());
        contentTV.setText(notice.getContent());
    }
}
