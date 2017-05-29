package com.dms.beinone.application.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.ListView;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by BeINone on 2017-01-31.
 */

public class ChangeDefaultStatusDialogFragment extends DialogFragment {

    private Context mContext;
    private ChangeDefaultStatusListener mListener;

    private SharedPreferences mAccountPrefs;

    public static ChangeDefaultStatusDialogFragment newInstance(
            Context context, int defaultStatus, ChangeDefaultStatusListener listener) {

        Bundle args = new Bundle();
        args.putInt(context.getString(R.string.ARGS_DEFAULTSTATUS), defaultStatus);

        ChangeDefaultStatusDialogFragment fragment = new ChangeDefaultStatusDialogFragment();
        fragment.setArguments(args);
        fragment.mContext = context;
        fragment.mListener = listener;

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mAccountPrefs = mContext.getSharedPreferences(
                getString(R.string.PREFS_ACCOUNT), Context.MODE_PRIVATE);

        final String id = mAccountPrefs.getString(getString(R.string.PREFS_ACCOUNT_ID), "");
        final int defStatus = getArguments().getInt(getString(R.string.ARGS_DEFAULTSTATUS), -1);

        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.stayapply_dialog_title)
                .setSingleChoiceItems(R.array.change_default_status, defStatus - 1, null)
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
                                ListView listView = ((AlertDialog) dialog).getListView();
                                int value = listView.getCheckedItemPosition() + 1;

                                try {
                                    changeDefaultStatus(value);
                                } catch (IOException e) {
                                    System.out.println("IOException in RecommendFragment: changeDefaultStatus()");
                                    e.printStackTrace();
                                }
                            }
                        })
                .create();
    }

    private void changeDefaultStatus(final int value) throws IOException {
        try {
            JSONObject params = new JSONObject();
            params.put("value", value);

            HttpBox.patch(mContext, "/apply/stay/default")
                    .putBodyData(params)
                    .push(new HttpBoxCallback() {
                        @Override
                        public void done(Response response) {
                            int code = response.getCode();
                            switch (code) {
                                case HttpBox.HTTP_OK:
                                    Toast.makeText(mContext, R.string.stayapply_default_load_ok, Toast.LENGTH_SHORT).show();
                                    // notify the StayApplyFragment of changed default status
                                    mListener.onChangeDefaultStatus(value);
                                    break;
                                case HttpBox.HTTP_BAD_REQUEST:
                                    Toast.makeText(mContext, R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                    Toast.makeText(mContext, R.string.stayapply_default_load_error, Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void err(Exception e) {
                            System.out.println("Error in RecommendFragment: PATCH /apply/stay/default");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException in RecommendFragment: PATCH /apply/stay/default");
            e.printStackTrace();
        }
    }

    public interface ChangeDefaultStatusListener {
        void onChangeDefaultStatus(int defaultStatus);
    }
}
