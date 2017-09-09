package com.dms.beinone.application.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dms.beinone.application.R;

/**
 * Created by dsm2017 on 2017-09-09.
 */

public class ProblemReportDialog extends Dialog{

    private Context context;
    private Button yes;
    private Button no;
    private View.OnClickListener yesListener;
    private View.OnClickListener noListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_trouble_report);

        yes = (Button) findViewById(R.id.report_button);
        no = (Button) findViewById(R.id.cancle_button);
        yes.setOnClickListener(yesListener);
        no.setOnClickListener(noListener);
    }

    public ProblemReportDialog (Context context, View.OnClickListener yesListener, View.OnClickListener noListener) {

        super(context);
        this.context = context;
        this.yesListener = yesListener;
        this.noListener = noListener;
    }
}
