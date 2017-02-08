package com.dms.beinone.application.appcontent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.dms.beinone.application.JSONParser;
import com.dms.beinone.application.R;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by BeINone on 2017-01-26.
 */

public class AppcontentArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsletter_article);

        // display back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Appcontent appcontent = getIntent().getParcelableExtra(getString(R.string.EXTRA_APPCONTENT));
        bind(appcontent);

        new LoadAppcontentTask().execute(appcontent.getCategory(), appcontent.getNumber());
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
        TextView contentTV = (TextView) findViewById(R.id.tv_appcontent_article_content);

        titleTV.setText(appcontent.getTitle());
        writerTV.setText(appcontent.getWriter());
        dateTV.setText(appcontent.getDate());
        contentTV.setText(appcontent.getContent());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final List<Attachment> attachmentList = appcontent.getAttachmentList();
        if (attachmentList.size() != 0) {
            fab.setImageResource(R.drawable.ic_file_download_white_24dp);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DownloadAttachmentDialogFragment
                            .newInstance(AppcontentArticleActivity.this, attachmentList)
                            .show(getSupportFragmentManager(), null);
                }
            });
            fab.setVisibility(View.VISIBLE);
        }
    }

    private class LoadAppcontentTask extends AsyncTask<Integer, Void, Appcontent> {

        @Override
        protected Appcontent doInBackground(Integer... params) {
            Appcontent appcontent = null;

            try {
                appcontent = loadAppcontent(params[0], params[1]);
            } catch (IOException ie) {
                return null;
            } catch (JSONException je) {
                return null;
            }

            return appcontent;
        }

        @Override
        protected void onPostExecute(Appcontent appcontent) {
            super.onPostExecute(appcontent);

            bind(appcontent);
        }

        private Appcontent loadAppcontent(int category, int number) throws IOException, JSONException {
            JSONObject requestJSONObject = new JSONObject();
            requestJSONObject.put("category", category);
            requestJSONObject.put("number", number);

            int command = 0;
            switch (category) {
                case Appcontent.NOTICE:
                    command = 0;
                    break;
                case Appcontent.NEWSLETTER:
                    command = 1;
                    break;
                case Appcontent.COMPETITION:
                    command = 2;
                    break;
                default:
                    break;
            }
            Response response =
                    HttpBox.post().setCommand(command).putBodyData(requestJSONObject).push();

            JSONObject responseJSONObject = response.getJsonObject();

            return JSONParser.parseAppcontentJSON(responseJSONObject);
        }
    }

    private class DownloadAttachmentTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

}
