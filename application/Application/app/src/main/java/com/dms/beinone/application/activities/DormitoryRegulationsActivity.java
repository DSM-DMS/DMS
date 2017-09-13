package com.dms.beinone.application.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dms.beinone.application.R;
import com.dms.beinone.application.models.DormitoryNotice;
import com.dms.beinone.application.views.adapters.DormitoryNoticeAdapter;

import java.util.ArrayList;

public class DormitoryRegulationsActivity extends AppCompatActivity {

    private RecyclerView maRecyclerView;
    private ImageButton back_button;
    RecyclerView.Adapter maAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dormitory_regulations);

        TextView appBarText = (TextView) findViewById(R.id.tv_toolbar_title);
        appBarText.setText("자주하는 질문");



















        appBarText.setGravity(Gravity.CENTER_HORIZONTAL);


        maRecyclerView = (RecyclerView) findViewById(R.id.dormitory_regulations_recycler);

        back_button=(ImageButton) findViewById(R.id.ib_toolbar_back);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        maRecyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<DormitoryNotice> item = new ArrayList<>();
        item.add(new DormitoryNotice("사감부","연장신청에 관하여"));
        item.add(new DormitoryNotice("사감부", "연장신청에 관하여"));
        item.add(new DormitoryNotice("사감부","연장신청에 관하여"));
        item.add(new DormitoryNotice("사감부","연장신청에 관하여"));
        item.add(new DormitoryNotice("사감부","연장신청에 관하여"));
        item.add(new DormitoryNotice("사감부","연장신청에 관하여"));
        item.add(new DormitoryNotice("사감부","연장신청에 관하여"));
        item.add(new DormitoryNotice("사감부","연장신청에 관하여"));
        item.add(new DormitoryNotice("사감부","연장신청에 관하여"));
        item.add(new DormitoryNotice("사감부","연장신청에 관하여"));
        item.add(new DormitoryNotice("사감부","연장신청에 관하여"));
        item.add(new DormitoryNotice("사감부","연장신청에 관하여"));
        item.add(new DormitoryNotice("사감부","연장신청에 관하여"));
        item.add(new DormitoryNotice("사감부","연장신청에 관하여"));

        //maAdapter =  new DormitoryNoticeAdapter(this, item);
        maRecyclerView.setAdapter(maAdapter);
    }
}
