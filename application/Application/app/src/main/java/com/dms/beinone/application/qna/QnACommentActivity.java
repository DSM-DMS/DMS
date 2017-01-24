package com.dms.beinone.application.qna;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.dms.beinone.application.R;

/**
 * Created by BeINone on 2017-01-24.
 */

public class QnACommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_comment);

        // display back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 댓글 불러오기
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;
    }

}
