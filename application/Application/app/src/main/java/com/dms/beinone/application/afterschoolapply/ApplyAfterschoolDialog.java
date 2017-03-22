package com.dms.beinone.application.afterschoolapply;

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
 * Created by BeINone on 2017-03-09.
 */

public class ApplyAfterschoolDialog extends DialogFragment {

    private Context mContext;

    public static ApplyAfterschoolDialog newInstance(Context context, int no) {
        Bundle args = new Bundle();
        args.putInt(context.getString(R.string.ARGS_NO), no);

        ApplyAfterschoolDialog fragment = new ApplyAfterschoolDialog();
        fragment.setArguments(args);
        fragment.mContext = context;

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
                        new ApplyAfterschoolTask().execute(no);
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

    private class ApplyAfterschoolTask extends AsyncTask<Object, Void, Integer> {

        @Override
        protected Integer doInBackground(Object... params) {
            int code = -1;

            int no = (int) params[0];
            try {
                code = applyAfterschool(no);
            } catch (IOException e) {
                return -1;
            } catch (JSONException e) {
                return -1;
            }

            return code;
        }

        @Override
        protected void onPostExecute(Integer code) {
            super.onPostExecute(code);

            if (code == 201) {
                // success
                Toast.makeText(mContext, R.string.afterschoolapply_apply_success,
                        Toast.LENGTH_SHORT).show();
            } else if (code == 204) {
                // failure
                Toast.makeText(mContext, R.string.afterschoolapply_apply_failure,
                        Toast.LENGTH_SHORT).show();
            } else {
                // error
                Toast.makeText(mContext, R.string.afterschoolapply_apply_error,
                        Toast.LENGTH_SHORT).show();
            }
        }

        private int applyAfterschool(int no) throws IOException, JSONException {
            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("no", String.valueOf(no));

            Response response = HttpBox.post(mContext, "/apply/afterschool", Request.TYPE_POST)
                    .putBodyData(requestParams)
                    .push();

            return response.getCode();
        }
    }

}
