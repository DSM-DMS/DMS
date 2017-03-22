package com.dms.beinone.application.extensionapply;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Request;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BeINone on 2017-03-15.
 */

public class ExtensionApplyDialog extends DialogFragment {

    private Context mContext;

    public static ExtensionApplyDialog newInstance(Context context, Extension extension) {
        Bundle args = new Bundle();
        args.putParcelable(context.getString(R.string.EXTRA_EXTENSION), extension);

        ExtensionApplyDialog fragment = new ExtensionApplyDialog();
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
                        new ApplyExtensionTask().execute(extension);
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

    private class ApplyExtensionTask extends AsyncTask<Extension, Void, int[]> {

        @Override
        protected int[] doInBackground(Extension... params) {
            int[] results = null;

            Extension extension = params[0];
            int classId = extension.getClassId();
            int seat = extension.getSeat();
            try {
                results = applyExtension(classId, seat);
            } catch (IOException e) {
                e.printStackTrace();
                return new int[]{-1, -1};
            } catch (JSONException e) {
                e.printStackTrace();
                return new int[]{-1, -1};
            }

            return results;
        }

        @Override
        protected void onPostExecute(int[] results) {
            super.onPostExecute(results);

            int code = results[0];
            int seat = results[1];

            if (code == 200) {
                Toast.makeText(mContext, R.string.extensionapply_apply_success,
                        Toast.LENGTH_SHORT).show();
            } else if (code == 204) {
                Toast.makeText(mContext, R.string.extensionapply_apply_failure,
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, R.string.extensionapply_apply_error,
                        Toast.LENGTH_SHORT).show();
            }
        }

        private int[] applyExtension(int classId, int seat) throws IOException, JSONException {
            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("class", String.valueOf(classId));
            requestParams.put("seat", String.valueOf(seat));

            Response response = HttpBox.post(mContext, "/apply/extension", Request.TYPE_PUT)
                    .putBodyData(requestParams)
                    .push();

            return new int[]{response.getCode(), seat};
        }
    }

}
