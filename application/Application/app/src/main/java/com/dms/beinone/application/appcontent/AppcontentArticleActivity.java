package com.dms.beinone.application.appcontent;

import android.os.AsyncTask;
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
import com.dms.boxfox.networking.datamodel.Request;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        bind(appcontent);

        new LoadAppcontentTask(appcontent.getCategory())
                .execute(appcontent.getCategory(), appcontent.getNumber());
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

    private class LoadAppcontentTask extends AsyncTask<Integer, Void, Object[]> {

        private int mCategory;

        public LoadAppcontentTask(int category) {
            mCategory = category;
        }

        @Override
        protected Object[] doInBackground(Integer... params) {
            Object[] results = null;

            try {
                results = loadAppcontent(params[0], params[1]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

            return results;
        }

        @Override
        protected void onPostExecute(Object[] results) {
            super.onPostExecute(results);

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

            int code = (int) results[0];
            Appcontent appcontent = (Appcontent) results[1];

            if (code == 200) {
                // success
                bind(appcontent);
            } else if (code == 204) {
                // failure
                Toast.makeText(AppcontentArticleActivity.this, emptyMsgResId, Toast.LENGTH_SHORT)
                        .show();
            } else {
                // error
                Toast.makeText(AppcontentArticleActivity.this, errorMsgResId, Toast.LENGTH_SHORT)
                        .show();
            }
        }

        private Object[] loadAppcontent(int category, int number) throws IOException, JSONException {
            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("no", String.valueOf(number));

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
                default: break;
            }
            Response response = HttpBox.post(AppcontentArticleActivity.this, path, Request.TYPE_GET)
                    .putBodyData(requestParams)
                    .push();

            int code = response.getCode();
            Appcontent appcontent = null;
            if (code == 200) {
                appcontent = JSONParser.parseAppcontentJSON(response.getJsonObject());
            }

            return new Object[]{code, appcontent};
        }
    }

}
