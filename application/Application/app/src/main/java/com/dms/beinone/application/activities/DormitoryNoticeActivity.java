package com.dms.beinone.application.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.dms.beinone.application.R;

public class DormitoryNoticeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dormitory_notice);

        Button button=(Button)findViewById(R.id.testButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Notice.class);
                startActivity(intent);
                finish();
            }
        });
        //여기서 작업하고 아이템 클릭리스너 주고 notcie 액티비티로 가게 하면돼
        // 힘들면 우선 리사이클 뷰 만든는것만 해봐 저거 버튼 코드 지워도됌
    }
}
