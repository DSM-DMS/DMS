package com.dms.beinone.application.newsletter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dms.beinone.application.R;

/**
 * Created by BeINone on 2017-01-24.
 */

public class NewsletterArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsletter_article);

        // display back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Newsletter newsletter = getIntent().getParcelableExtra(getString(R.string.EXTRA_NEWSLETTER));
        bind(newsletter);
    }

    /**
     * sets text of article
     * @param newsletter Newsletter object that contains information of article
     */
    private void bind(Newsletter newsletter) {
        TextView titleTV = (TextView) findViewById(R.id.tv_newsletter_article_title);
        TextView writerTV = (TextView) findViewById(R.id.tv_newsletter_article_writer);
        TextView dateTV = (TextView) findViewById(R.id.tv_newsletter_article_date);
        TextView contentTV = (TextView) findViewById(R.id.tv_newsletter_article_content);

        titleTV.setText(newsletter.getTitle());
        writerTV.setText(newsletter.getWriter());
        dateTV.setText(newsletter.getDate());
        contentTV.setText(newsletter.getContent());
    }

}
