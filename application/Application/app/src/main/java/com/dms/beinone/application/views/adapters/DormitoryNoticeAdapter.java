package com.dms.beinone.application.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.dms.beinone.application.R;
import com.dms.beinone.application.models.DormitoryNotice;

import java.util.ArrayList;

/**
 * Created by dsm2017 on 2017-08-28.
 */

public class DormitoryNoticeAdapter extends RecyclerView.Adapter<DormitoryNoticeAdapter.ViewHolder>{

    Context maContext;
    private ArrayList<DormitoryNotice> items;
    MaOnTouchListener maOnTouchListener = new MaOnTouchListener();

    public  DormitoryNoticeAdapter (Context context, ArrayList<DormitoryNotice> arrayList){

        items = arrayList;
        maContext = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView DormitoryTitle;
        TextView DormitoryBackOffice;

        public ViewHolder(View view)  {

            super(view);

            DormitoryTitle = (TextView) view.findViewById(R.id.dormitory_notice_item_title);
            DormitoryBackOffice = (TextView) view.findViewById(R.id.dormitory_back_office);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_notice_same_item, parent, false);

        ViewHolder vH = new ViewHolder(v);
        return vH;
    }

    @Override
    public void onBindViewHolder (ViewHolder v, int position) {

        v.DormitoryBackOffice.setText(items.get(position).getBackOffice());
        v.DormitoryTitle.setText(items.get(position).getNoticeTitle());
    }

    @Override
    public int getItemCount () {

        return items.size();
    }
}


class MaOnTouchListener implements RecyclerView.OnItemTouchListener {

    public MaOnTouchListener(){};

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

        Log.d("tag3", "what should i do");
    }
}