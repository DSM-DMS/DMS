package com.dms.beinone.application.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dms.beinone.application.R;
import com.dms.beinone.application.models.DormitoryNotice;
import com.dms.beinone.application.views.adapters.DormitoryNoticeAdapter;

import java.util.ArrayList;

public class DormitoryNoticeActivity extends AppCompatActivity {

    RecyclerView maRecyclerView;
    RecyclerView.Adapter maAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dormitory_notice);

        //여기서 작업하고 아이템 클릭리스너 주고 notcie 액티비티로 가게 하면돼
        // 힘들면 우선 리사이클 뷰 만든는것만 해봐 저거 버튼 코드 지워도됌

        maRecyclerView = (RecyclerView) findViewById(R.id.dormitory_notice_recycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        maRecyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<DormitoryNotice> item = new ArrayList<>();
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
        item.add(new DormitoryNotice("사감부","연장신청에 관하여"));
        item.add(new DormitoryNotice("사감부","연장신청에 관하여"));

        maAdapter =  new DormitoryNoticeAdapter(this, item);
        maRecyclerView.setAdapter(maAdapter);
    }
}
