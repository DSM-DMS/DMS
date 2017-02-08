package com.dms.beinone.application.appcontent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;

import com.dms.beinone.application.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BeINone on 2017-01-31.
 */

public class DownloadAttachmentDialogFragment extends DialogFragment {

    public static DownloadAttachmentDialogFragment newInstance(Context context, List<Attachment> attachmentList) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(
                context.getString(R.string.ARGS_DOWNLOAD), (ArrayList<Attachment>) attachmentList);

        DownloadAttachmentDialogFragment fragment = new DownloadAttachmentDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final List<Attachment> attachmentList =
                getArguments().getParcelableArrayList(getString(R.string.ARGS_DOWNLOAD));

        final String[] items = new String[attachmentList.size()];
        for (int index = 0; index < attachmentList.size(); index++) {
            items[index] = attachmentList.get(index).getName();
        }

        final boolean[] checkedItems = new boolean[items.length];

        return new AlertDialog.Builder(getContext()).setTitle(R.string.appcontent_dialog_title)
                .setMultiChoiceItems(items, checkedItems,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                // updates the current focused item's checked status
                                checkedItems[which] = isChecked;
                            }
                        })
                .setNegativeButton(R.string.appcontent_dialog_negative,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                .setPositiveButton(R.string.appcontent_dialog_positive,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for (int index = 0; index < checkedItems.length; index++) {
                                    if (checkedItems[index]) {
                                        Attachment attachment = attachmentList.get(index);
                                        new DownloadAttachmentTask()
                                                .execute(attachment.getLink(), attachment.getName());
                                    }
                                }
                            }
                        })
                .show();
    }

    private class DownloadAttachmentTask extends AsyncTask<String, Void, Boolean> {

        private DownloadManager mDownloadManager;
        private long enqueue;

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
                    (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);

            Uri urlToDownload = Uri.parse(url);

            List<String> pathSegments = urlToDownload.getPathSegments();
            DownloadManager.Request request = new DownloadManager.Request(urlToDownload);
            request.setTitle(fileName)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                            pathSegments.get(pathSegments.size() - 1));

            enqueue = mDownloadManager.enqueue(request);

            getContext().registerReceiver(mDownloadCompleteReceiver,
                    new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }

        private BroadcastReceiver mDownloadCompleteReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(enqueue);
                    Cursor cursor = mDownloadManager.query(query);
                    if (cursor.moveToFirst()) {
                        int statusColumnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                        if (cursor.getInt(statusColumnIndex) == DownloadManager.STATUS_SUCCESSFUL) {
                            int titleCoulmnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_TITLE);
                            Snackbar.make(getView(), cursor.getString(titleCoulmnIndex),
                                    Snackbar.LENGTH_LONG)
                                    .show();
                        }
                    }
                }
            }
        };
    }

}
