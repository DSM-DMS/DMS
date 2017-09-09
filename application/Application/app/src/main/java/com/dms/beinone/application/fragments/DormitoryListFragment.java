package com.dms.beinone.application.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.WindowInsetsCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.beinone.application.activities.DormitoryNoticeActivity;
import com.dms.beinone.application.dialogs.ProblemReportDialog;

/**
 * Created by BeINone on 2017-05-31.
 */

public class DormitoryListFragment extends Fragment {

    Dialog maDialog;
    private int check = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dormitory_list, container, false);
        nextPage(view,getActivity());
        return view;
    }



    public void nextPage(View view, final Context context){
        ImageButton noticeButton =(ImageButton)view.findViewById(R.id.ib_dormitory_list_notice);
        final ImageButton problemButton = (ImageButton) view.findViewById(R.id.ib_dormitory_list_facility_report);
        final View.OnClickListener reportListenr;
        final View.OnClickListener cancleListener;

        noticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DormitoryNoticeActivity.class);
                startActivity(intent);

            }
        });

        reportListenr = new View.OnClickListener() {

            @Override
            public void onClick (View view) {

                Toast.makeText(context, "신고되었습니다.", Toast.LENGTH_SHORT).show();
            }
        };

        cancleListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                maDialog.dismiss();
            }
        };

        problemButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                maDialog = new ProblemReportDialog(context, reportListenr, cancleListener);
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(maDialog.getWindow().getAttributes());
                layoutParams.width = 950;
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                maDialog.show();
                Window window = maDialog.getWindow();
                window.setAttributes(layoutParams);
            }
        });
    }
}
