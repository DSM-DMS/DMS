package com.dms.beinone.application.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.dms.beinone.application.R;

public class Notice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        TextView TextView = (TextView) findViewById(R.id.tv_toolbar_title);
        TextView.setText("공지사항");

        TextView textView=(TextView)findViewById(R.id.tv_notice_title);
        textView.setText("제목");

        WebView webView=(WebView)findViewById(R.id.notice_webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.naver.com/");
    }
}
