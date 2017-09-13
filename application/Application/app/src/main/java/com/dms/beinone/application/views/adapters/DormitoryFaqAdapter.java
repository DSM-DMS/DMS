package com.dms.beinone.application.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dms.beinone.application.R;
import com.dms.beinone.application.activities.FaqActivity;
import com.dms.beinone.application.models.Notice;

import java.util.ArrayList;

public class DormitoryFaqAdapter extends RecyclerView.Adapter<DormitoryFaqAdapter.ViewHolder> {

    WebView webView;
    Context mContext;
    private ArrayList<Notice> items;

    public DormitoryFaqAdapter(Context context, ArrayList<Notice> arrayList){

        items = arrayList;
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_notice_same_item, parent, false);
        ViewHolder vH = new ViewHolder(v);
        return vH;
    }

    @Override
    public void onBindViewHolder (ViewHolder v, final int position) {

        v.DormitoryBackOffice.setText(items.get(position).getWriter());
        v.DormitoryTitle.setText(items.get(position).getTitle());
        v.Dormitory_next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,FaqActivity.class);
                String title=items.get(position).getTitle();
                String content=items.get(position).getContent();
                intent.putExtra("title",title);
                intent.putExtra("content",content);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount () {

        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView DormitoryTitle;
        TextView DormitoryBackOffice;
        ImageView Dormitory_next_button;


        public ViewHolder(View view)  {

            super(view);
            Dormitory_next_button=(ImageView)view.findViewById(R.id.ib_notice_next);
            DormitoryTitle = (TextView) view.findViewById(R.id.dormitory_notice_item_title);
            DormitoryBackOffice = (TextView) view.findViewById(R.id.dormitory_back_office);
        }
    }

}
