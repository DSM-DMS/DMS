package com.dms.beinone.application.activities;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        TextView appBarText = (TextView) findViewById(R.id.tv_toolbar_title);
        appBarText.setText("공지사항");


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

        MaOnTouchListener maOnTouchListener = new MaOnTouchListener(this);
        maAdapter =  new DormitoryNoticeAdapter(this, item);
        maRecyclerView.setAdapter(maAdapter);
    }
}



class MaOnTouchListener implements RecyclerView.OnItemTouchListener {

    Context maContext;

    public MaOnTouchListener (Context context) {

        maContext = context;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        Log.d("tag1", "helloWorld");
        return true;
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        Log.d("tag2", "we can do it");
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        Toast.makeText(maContext, "we can do it!!",Toast.LENGTH_SHORT).show();
        Log.d("tag3", "what should i do");
    }
}
