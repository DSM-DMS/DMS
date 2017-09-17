package com.dms.beinone.application.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.dms.beinone.application.views.adapters.DormitoryFaqAdapter;
import com.dms.beinone.application.views.adapters.DormitoryNoticeAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DormitoryFaqActivity extends AppCompatActivity {

    private RecyclerView maRecyclerView;
    private ImageButton back_button;

    private ArrayList<Notice> arrayList;

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

        DMSService service= HttpManager.createDMSService(getApplicationContext());
        service.loadFag().enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("FAG",response.body().toString());
                arrayList=getFaqJsonParser(response.body().getAsJsonArray("result"));
                maRecyclerView.setAdapter(new DormitoryFaqAdapter(getApplicationContext(),arrayList));
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    public ArrayList<Notice> getFaqJsonParser(JsonArray jsonArray){
        arrayList=new ArrayList<>();

        for(int i=0;i<jsonArray.size();i++){
            JsonObject jsonObject= (JsonObject) jsonArray.get(i);
            int no=jsonObject.get("no").getAsInt();
            String title=jsonObject.get("title").toString();
            String content= jsonObject.get("content").toString();

            arrayList.add(new Notice(no,title,content));
        }

        return  arrayList;
    }
}
