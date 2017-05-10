package com.dms.beinone.application.afterschoolapply;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by BeINone on 2017-03-09.
 */

public class ApplyAfterschoolDialog extends DialogFragment {

    public static ApplyAfterschoolDialog newInstance(Context context, int no) {
        Bundle args = new Bundle();
        args.putInt(context.getString(R.string.ARGS_NO), no);

        ApplyAfterschoolDialog fragment = new ApplyAfterschoolDialog();
        fragment.setArguments(args);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final int no = getArguments().getInt(getString(R.string.ARGS_NO));

        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.applydialog_title)
                .setMessage(R.string.applydialog_message)
                .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            applyAfterschool(no);
                        } catch (IOException e) {
                            System.out.println("IOException in ApplyAfterschoolDialog: POST /apply/afterschool");
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

    private void applyAfterschool(int no) throws IOException {
        try {
            JSONObject params = new JSONObject();
            params.put("no", no);

            HttpBox.post(getContext(), "/apply/afterschool").putBodyData(params).push(new HttpBoxCallback() {
                @Override
                public void done(Response response) {
                    int code = response.getCode();

                    if (code == HttpBox.HTTP_CREATED) {
                        Toast.makeText(getContext(), R.string.afterschool_apply_created, Toast.LENGTH_SHORT).show();
                    } else if (code == HttpBox.HTTP_BAD_REQUEST) {
                        Toast.makeText(getContext(), R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                    } else if (code == HttpBox.HTTP_CONFLICT) {
                        Toast.makeText(getContext(), R.string.afterschool_apply_conflict, Toast.LENGTH_SHORT).show();
                    } else if (code == HttpBox.HTTP_INTERNAL_SERVER_ERROR) {
                        Toast.makeText(getContext(), R.string.afterschool_apply_internal_server_error, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void err(Exception e) {
                    System.out.println("Error in ApplyAfterschoolDialog: POST /apply/afterschool");
                    e.printStackTrace();
                }
            });
        } catch (JSONException e) {
            System.out.println("JSONException in ApplyAfterschoolDialog: POST /apply/afterschool");
            e.printStackTrace();
        }
    }
}
