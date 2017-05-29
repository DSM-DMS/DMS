package com.dms.beinone.application.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.dms.beinone.application.R;
import com.dms.beinone.application.network.DownloadAttachmentTask;
import com.dms.beinone.application.models.Attachment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BeINone on 2017-01-31.
 */

public class DownloadAttachmentDialogFragment extends DialogFragment {

    private Context mContext;

    public static DownloadAttachmentDialogFragment newInstance(Context context, List<Attachment> attachmentList) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(
                context.getString(R.string.ARGS_DOWNLOAD), (ArrayList<Attachment>) attachmentList);

        DownloadAttachmentDialogFragment fragment = new DownloadAttachmentDialogFragment();
        fragment.setArguments(args);
        fragment.mContext = context;

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
                                        new DownloadAttachmentTask(mContext)
                                                .execute(attachment.getLink(), attachment.getName());
                                    }
                                }
                            }
                        })
                .show();
    }

}
