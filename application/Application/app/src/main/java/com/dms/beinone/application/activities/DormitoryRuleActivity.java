package com.dms.beinone.application.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dms.beinone.application.DMSService;
import com.dms.beinone.application.R;
import com.dms.beinone.application.managers.HttpManager;
import com.dms.beinone.application.models.DormitoryNotice;
import com.dms.beinone.application.models.Notice;
import com.dms.beinone.application.views.adapters.DormitoryNoticeAdapter;
import com.dms.beinone.application.views.adapters.DormitoryRuleAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DormitoryRuleActivity extends AppCompatActivity {

    Context context;
    private ImageButton back_button;
    RecyclerView maRecyclerView;
    RecyclerView.Adapter maAdapter;
    private ArrayList<Notice> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dormitory_rule);

        TextView appBarText = (TextView) findViewById(R.id.tv_toolbar_title);
        appBarText.setText("기숙사 규정");
        appBarText.setGravity(Gravity.CENTER_HORIZONTAL);

        DMSService service= HttpManager.createDMSService(getApplicationContext());


        final ArrayList<DormitoryNotice> item = new ArrayList<>();
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

        service.loadRule().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                Log.d("NOTICE",response.body().toString());
                arrayList=getNoticeJsonParser(response.body().getAsJsonArray("result"));
                maAdapter =  new DormitoryRuleAdapter(getApplicationContext(),arrayList);
                maRecyclerView.setAdapter(maAdapter);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });



        maRecyclerView = (RecyclerView) findViewById(R.id.dormitory_notice_recycler);

        back_button=(ImageButton) findViewById(R.id.ib_toolbar_back);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        if(maRecyclerView == null){
            Log.e("Error", "e");
        }
        maRecyclerView.setLayoutManager(linearLayoutManager);

    }

    public void loadNotice(){

    }

    public ArrayList<Notice> getNoticeJsonParser(JsonArray jsonArray){
        arrayList=new ArrayList<>();

        for(int i=0;i<jsonArray.size();i++){
            JsonObject jsonObject= (JsonObject) jsonArray.get(i);
            int no=jsonObject.get("no").getAsInt();
            String title=jsonObject.get("title").toString();
            String content= jsonObject.get("content").toString();
           // String writer=jsonObject.get("writer").toString();

            arrayList.add(new Notice(no,title,content));
        }

        return  arrayList;
    }
}
