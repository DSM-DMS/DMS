package com.dms.beinone.application.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.beinone.application.models.Extension;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by BeINone on 2017-03-15.
 */

public class ExtensionApplyDialogFragment extends DialogFragment {

    private Context mContext;

    public static ExtensionApplyDialogFragment newInstance(Context context, Extension extension) {
        Bundle args = new Bundle();
        args.putParcelable(context.getString(R.string.EXTRA_EXTENSION), extension);

        ExtensionApplyDialogFragment fragment = new ExtensionApplyDialogFragment();
        fragment.setArguments(args);
        fragment.mContext = context;

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Extension extension = getArguments().getParcelable(getString(R.string.EXTRA_EXTENSION));

        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.applydialog_title)
                .setMessage(R.string.applydialog_message)
                .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            applyExtension(extension.getClazz(), extension.getSeat());
                        } catch (IOException e) {
                            System.out.println("IOException in ExtensionApplyDialog: PUT /apply/extension");
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
    }

    private void applyExtension(int clazz, int seat) throws IOException {
        try {
            JSONObject params = new JSONObject();
            params.put("class", clazz);
            params.put("seat", seat);

            HttpBox.put(mContext, "/apply/extension")
                    .putBodyData(params)
                    .push(new HttpBoxCallback() {
                        @Override
                        public void done(Response response) {
                            int code = response.getCode();
                            switch (code) {
                                case HttpBox.HTTP_OK:
                                    Toast.makeText(mContext, R.string.extension_apply_created, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_NO_CONTENT:
                                    Toast.makeText(mContext, R.string.extension_apply_no_content, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_BAD_REQUEST:
                                    Toast.makeText(mContext, R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                    Toast.makeText(mContext, R.string.extension_apply_internal_server_error, Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void err(Exception e) {
                            System.out.println("Error in ExtensionApplyDialog: PUT /apply/extension");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException in ExtensionApplyDialog: PUT /apply/extension");
            e.printStackTrace();
        }
    }
}
