package com.dms.beinone.application.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.dms.beinone.application.R;

public class FaqActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        TextView toolbar_title = (TextView) findViewById(R.id.tv_toolbar_title);
        toolbar_title.setText("자주하는 질문");

        TextView title_TextView=(TextView)findViewById(R.id.tv_notice_title);
        title_TextView.setText("제목");

        ImageView imageView=(ImageView)findViewById(R.id.ib_toolbar_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),DormitoryNoticeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        String content=intent.getStringExtra("content");

        title_TextView.setText(title);
        WebView webView=(WebView)findViewById(R.id.notice_webView);
        webView.loadData(content,"text/html; charset=utf-8", "UTF-8");
        webView.getSettings().setBuiltInZoomControls(true);

        webView.setWebViewClient(new WebViewClient());
    }
}
