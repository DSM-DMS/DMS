package com.dms.beinone.application.appcontent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.beinone.application.utils.JSONParser;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by BeINone on 2017-01-26.
 */

public class AppcontentArticleActivity extends AppCompatActivity {

    private FloatingActionButton mFAB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appcontent_article);
        // display back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Appcontent appcontent = getIntent().getParcelableExtra(getString(R.string.EXTRA_APPCONTENT));

        switch (appcontent.getCategory()) {
            case Appcontent.NOTICE:
                setTitle(R.string.nav_notice);
                break;
            case Appcontent.NEWSLETTER:
                setTitle(R.string.nav_newsletter);
                break;
            case Appcontent.COMPETITION:
                setTitle(R.string.nav_competition);
                break;
            default:
                break;
        }

        try {
            loadAppcontent(appcontent.getCategory(), appcontent.getNumber());
        } catch (IOException e) {
            System.out.println("IOException in AppcontentArticleActivity: GET /post/school/appcontent");
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFAB.setVisibility(View.INVISIBLE);
    }

    /**
     * sets text of article
     *
     * @param appcontent Appcontent object that contains information of article
     */
    private void bind(final Appcontent appcontent) {
        TextView titleTV = (TextView) findViewById(R.id.tv_appcontent_article_title);
        TextView writerTV = (TextView) findViewById(R.id.tv_appcontent_article_writer);
        TextView dateTV = (TextView) findViewById(R.id.tv_appcontent_article_date);
        WebView contentWV = (WebView) findViewById(R.id.wv_appcontent_article_content);

        titleTV.setText(appcontent.getTitle());
        writerTV.setText(appcontent.getWriter());
        dateTV.setText(appcontent.getDate());
        contentWV.loadData(appcontent.getContent(), "text/html; charset=UTF-8", null);

        mFAB = (FloatingActionButton) findViewById(R.id.fab_appcontent_article);
        final List<Attachment> attachmentList = appcontent.getAttachmentList();
        if (attachmentList != null && attachmentList.size() != 0) {
            mFAB.setImageResource(R.drawable.ic_file_download_white_24dp);
            mFAB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DownloadAttachmentDialogFragment
                            .newInstance(AppcontentArticleActivity.this, attachmentList)
                            .show(getSupportFragmentManager(), null);
                }
            });
            mFAB.setVisibility(View.VISIBLE);
        }
    }

    private void loadAppcontent(final int category, int number) throws IOException {
        try {
            JSONObject params = new JSONObject();
            params.put("no", number);

            String path = null;
            switch (category) {
                case Appcontent.NOTICE:
                    path = "/post/school/notice";
                    break;
                case Appcontent.NEWSLETTER:
                    path = "/post/school/newsletter";
                    break;
                case Appcontent.COMPETITION:
                    path = "/post/school/competition";
                    break;
                default:
                    break;
            }

            HttpBox.get(AppcontentArticleActivity.this, path)
                    .putQueryString(params)
                    .push(new HttpBoxCallback() {
                        @Override
                        public void done(Response response) {
                            int errorMsgResId = 0;
                            int emptyMsgResId = 0;
                            switch (category) {
                                case Appcontent.NOTICE:
                                    errorMsgResId = R.string.appcontent_notice_error;
                                    emptyMsgResId = R.string.appcontent_notice_empty;
                                    break;
                                case Appcontent.NEWSLETTER:
                                    errorMsgResId = R.string.appcontent_newsletter_error;
                                    emptyMsgResId = R.string.appcontent_newsletter_empty;
                                    break;
                                case Appcontent.COMPETITION:
                                    errorMsgResId = R.string.appcontent_competition_error;
                                    emptyMsgResId = R.string.appcontent_competition_empty;
                                    break;
                                default:
                                    break;
                            }

                            switch (response.getCode()) {
                                case HttpBox.HTTP_OK:
                                    try {
                                        Appcontent appcontent = JSONParser.parseAppcontentJSON(response.getJsonObject());
                                        bind(appcontent);
                                    } catch (JSONException e) {
                                        System.out.println("JSONException in AppcontentArticleActivity: GET /post/school/appcontent");
                                        e.printStackTrace();
                                    }
                                    break;
                                case HttpBox.HTTP_NO_CONTENT:
                                    Toast.makeText(AppcontentArticleActivity.this, emptyMsgResId, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_BAD_REQUEST:
                                    Toast.makeText(AppcontentArticleActivity.this, R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                    Toast.makeText(AppcontentArticleActivity.this, errorMsgResId, Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void err(Exception e) {
                            System.out.println("Error in AppcontentArticleActivity: GET /post/school/appcontent");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException AppcontentArticleActivity: GET /post/school/appcontent");
            e.printStackTrace();
        }
    }
}
