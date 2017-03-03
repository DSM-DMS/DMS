package com.dms.beinone.application.facilityreport;

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
import com.dms.boxfox.networking.datamodel.Commands;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by BeINone on 2017-03-02.
 */

public class DeleteFacilityReportDialog extends DialogFragment {

    public static DeleteFacilityReportDialog newInstance(Context context, int no) {
        Bundle args = new Bundle();
        args.putInt(context.getString(R.string.ARGS_NO), no);

        DeleteFacilityReportDialog fragment = new DeleteFacilityReportDialog();
        fragment.setArguments(args);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final int no = getArguments().getInt(getString(R.string.ARGS_NO));

        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.deletedialog_article_title)
                .setMessage(R.string.deletedialog_article_message)
                .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DeleteFacilityReportTask().execute(no);
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

    private class DeleteFacilityReportTask extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            int code = -1;

            try {
                int no = params[0];
                code = deleteFacilityReport(no);
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

            if (code == 200) {
                // success
                Toast.makeText(getContext(), R.string.deletedialog_article_success,
                        Toast.LENGTH_SHORT).show();
                dismiss();
                getActivity().finish();
            } else if (code == 204) {
                // failure
                Toast.makeText(getContext(), R.string.deletedialog_article_failure,
                        Toast.LENGTH_SHORT).show();
            } else {
                // error
                Toast.makeText(getContext(), R.string.deletedialog_article_error,
                        Toast.LENGTH_SHORT).show();
            }
        }

        private int deleteFacilityReport(int no) throws IOException, JSONException {
            JSONObject requestJSONObject = new JSONObject();
            requestJSONObject.put("no", no);

            Response response = HttpBox.post()
                    .setCommand(Commands.DELETE_REPORT_FACILITY)
                    .putBodyData(requestJSONObject)
                    .push();

            return response.getCode();
        }
    }

}
