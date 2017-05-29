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
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by BeINone on 2017-03-02.
 */

public class DeleteFacilityReportDialogFragment extends DialogFragment {

    private Context mContext;

    public static DeleteFacilityReportDialogFragment newInstance(Context context, int no) {
        Bundle args = new Bundle();
        args.putInt(context.getString(R.string.ARGS_NO), no);

        DeleteFacilityReportDialogFragment fragment = new DeleteFacilityReportDialogFragment();
        fragment.setArguments(args);
        fragment.mContext = context;

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
                        try {
                            deleteFacilityReport(no);
                        } catch (IOException e) {
                            System.out.println("IOException in DeleteFacilityReportDialog: deleteFacilityReport()");
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

    private void deleteFacilityReport(int no) throws IOException {
        try {
            JSONObject params = new JSONObject();
            params.put("no", no);

            HttpBox.delete(mContext, "/post/report")
                    .putBodyData(params)
                    .push(new HttpBoxCallback() {
                        @Override
                        public void done(Response response) {
                            int code = response.getCode();
                            switch (code) {
                                case HttpBox.HTTP_OK:
                                    Toast.makeText(getContext(), R.string.deletedialog_article_ok, Toast.LENGTH_SHORT).show();
                                    dismiss();
                                    getActivity().finish();
                                    break;
                                case HttpBox.HTTP_BAD_REQUEST:
                                    Toast.makeText(getContext(), R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                    Toast.makeText(getContext(), R.string.deletedialog_article_internal_server_error, Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void err(Exception e) {
                            System.out.println("Error in DeleteFacilityReportDialog: DELETE /post/report");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException in DeleteFacilityReportDialog: DELETE /post/report");
            e.printStackTrace();
        }
    }
}
