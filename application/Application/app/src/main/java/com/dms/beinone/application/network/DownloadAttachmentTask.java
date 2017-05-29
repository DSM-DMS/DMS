package com.dms.beinone.application.network;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import com.dms.beinone.application.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by BeINone on 2017-02-17.
 */

public class DownloadAttachmentTask extends AsyncTask<String, Void, Boolean> {

//    private static List<Long> enqueues;

    private Context mContext;
    private DownloadManager mDownloadManager;
//    private long enqueue;

    public DownloadAttachmentTask(Context context) {
        mContext = context;
//        enqueues = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(mContext, R.string.appcontent_download_start, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            downloadAttachment(params[0], params[1]);
        } catch (MalformedURLException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }

    private void downloadAttachment(String url, String fileName) throws IOException {
        mDownloadManager =
                (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);

        Uri urlToDownload = Uri.parse(url);

        List<String> pathSegments = urlToDownload.getPathSegments();
        DownloadManager.Request request = new DownloadManager.Request(urlToDownload);
        request.setTitle(fileName)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                        pathSegments.get(pathSegments.size() - 1));

        mDownloadManager.enqueue(request);
//        enqueues.add(mDownloadManager.enqueue(request));

//        mContext.registerReceiver(mDownloadCompleteReceiver,
//                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

//    private BroadcastReceiver mDownloadCompleteReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            if (action.equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
//                DownloadManager.Query query = new DownloadManager.Query();
//                query.setFilterById(enqueue);
//                Cursor cursor = mDownloadManager.query(query);
//                if (cursor.moveToFirst()) {
//                    int statusColumnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
//                    if (cursor.getInt(statusColumnIndex) == DownloadManager.STATUS_SUCCESSFUL) {
////                            int titleCoulmnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_TITLE);
////                            Snackbar.make(getView(), cursor.getString(titleCoulmnIndex),
////                                    Snackbar.LENGTH_LONG)
////                                    .show();
//                        Toast.makeText(mContext, R.string.appcontent_download_finish,
//                                Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        }
//    };
}