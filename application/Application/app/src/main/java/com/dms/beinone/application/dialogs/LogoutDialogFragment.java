package com.dms.beinone.application.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.dms.beinone.application.DMSService;
import com.dms.beinone.application.R;
import com.dms.beinone.application.managers.AccountManager;
import com.dms.beinone.application.managers.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dms.beinone.application.DMSService.HTTP_CREATED;

/**
 * Created by BeINone on 2017-08-02.
 */

public class LogoutDialogFragment extends DialogFragment {

    private Context context;

    public LogoutDialogFragment (Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.logout_dialog_message)
                .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                        dialog.dismiss();
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

    private void logout() {
        DMSService dmsService = HttpManager.createDMSService(getContext());
        Call<Void> call = dmsService.logout();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int code = response.code();
                switch (code) {
                    case HTTP_CREATED:
                        AccountManager.setLogined(context, false);
                        Toast.makeText(getActivity(), getString(R.string.logout_created), Toast.LENGTH_SHORT).show();
                        dismiss();
                        break;
                    default:
                        Toast.makeText(getActivity(), R.string.logout_failed, Toast.LENGTH_SHORT).show();
                        break;
                }
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, getActivity().getIntent());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
