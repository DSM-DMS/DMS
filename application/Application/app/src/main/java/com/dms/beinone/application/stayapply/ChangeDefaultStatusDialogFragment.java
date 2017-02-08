package com.dms.beinone.application.stayapply;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Commands;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by BeINone on 2017-01-31.
 */

public class ChangeDefaultStatusDialogFragment extends DialogFragment {

    private ChangeDefaultStatusListener mListener;

    public static ChangeDefaultStatusDialogFragment newInstance(
            Context context, int defaultStatus, ChangeDefaultStatusListener listener) {

        Bundle args = new Bundle();
        args.putInt(context.getString(R.string.ARGS_DEFAULTSTATUS), defaultStatus);

        ChangeDefaultStatusDialogFragment fragment = new ChangeDefaultStatusDialogFragment();
        fragment.setArguments(args);
        fragment.mListener = listener;

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        SharedPreferences accountPrefs = getContext().getSharedPreferences(
                getString(R.string.PREFS_ACCOUNT), Context.MODE_PRIVATE);
        final String id = accountPrefs.getString(getString(R.string.PREFS_ACCOUNT_ID), "");
        final int defStatus = getArguments().getInt(getString(R.string.ARGS_DEFAULTSTATUS), -1) - 1;

        return new AlertDialog.Builder(getContext()).setTitle(R.string.stayapply_dialog_title)
                .setSingleChoiceItems(R.array.change_default_status, defStatus, null)
                .setNegativeButton(R.string.stayapply_dialog_negative,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                .setPositiveButton(R.string.stayapply_dialog_positive,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new ChangeDefaultStatusTask().execute(id, defStatus);
                            }
                        })
                .show();
    }

    private class ChangeDefaultStatusTask extends AsyncTask<Object, Void, Integer> {

        // default status
        private int mValue;

        @Override
        protected Integer doInBackground(Object... params) {
            int status = -1;

            try {
                mValue = (int) params[1];
                status = changeDefaultStatus(params[0].toString(), mValue);
            } catch (IOException e) {
                return -1;
            } catch (JSONException e) {
                return -1;
            }

            return status;
        }

        @Override
        protected void onPostExecute(Integer status) {
            super.onPostExecute(status);

            if (status > 0) {
                /* success */
                Toast.makeText(getContext(), R.string.stayapply_dialog_success, Toast.LENGTH_SHORT)
                        .show();

                SharedPreferences prefs = getContext().getSharedPreferences(
                        getString(R.string.PREFS_DEFAULTSTATUS), Context.MODE_PRIVATE);
                prefs.edit()
                        .putInt(getString(R.string.PREFS_DEFAULTSTATUS_DEFAULTSTATUS), mValue)
                        .apply();

                // notify the StayApplyFragment of changed default status
                mListener.onChangeDefaultStatus(mValue);
            } else if (status == 0) {
                /* failure */
                Toast.makeText(getContext(), R.string.stayapply_dialog_failure, Toast.LENGTH_SHORT)
                        .show();
            } else {
                /* error */
                Toast.makeText(getContext(), R.string.stayapply_dialog_error, Toast.LENGTH_SHORT)
                        .show();
            }

            dismiss();
        }

        private int changeDefaultStatus(String id, int value) throws IOException, JSONException {
            JSONObject requestJSONObject = new JSONObject();
            requestJSONObject.put("id", id);
            requestJSONObject.put("value", value);
            Response response = HttpBox.post()
                    .setCommand(Commands.MODIFY_STAY_DEFAULT)
                    .putBodyData(requestJSONObject)
                    .push();

            JSONObject responseJSONObject = response.getJsonObject();
            return responseJSONObject.getInt("status");
        }
    }

    public interface ChangeDefaultStatusListener {

        void onChangeDefaultStatus(int defaultStatus);
    }

}
