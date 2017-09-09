package com.dms.beinone.application.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dms.beinone.application.activities.AfterSchoolActivity;

import java.util.ArrayList;
import com.dms.beinone.application.R;


/**
 * Created by dsm2016 on 2017-08-10.
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> items;
    private int listPosition;

    public QuestionAdapter(Context context, ArrayList<String> items, int position){
        this.context=context;
        this.items=items;
        this.listPosition = position;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_afterschool_choose_item,parent,false);
        return new ViewHolder(view, viewType, listPosition);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.question_name_text.setText(items.get(position));

        if(position == AfterSchoolActivity.currentSet[this.listPosition] - 1){
            holder.checkBox.setChecked(true);
        }else{
            holder.checkBox.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public RadioButton checkBox;
        private TextView question_name_text;

        public ViewHolder(View v, final int position, final int listPosition) {
            super(v);
            checkBox = (RadioButton) v.findViewById(R.id.choose_class_checkbox);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AfterSchoolActivity.currentSet[listPosition] = position + 1;
                    notifyDataSetChanged();
                }
            });

            question_name_text=(TextView)v.findViewById(R.id.class_category);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
