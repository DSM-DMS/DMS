package com.dms.beinone.application.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.dms.beinone.application.R;
import com.dms.beinone.application.activities.DormitoryNoticeActivity;

/**
 * Created by BeINone on 2017-05-31.
 */

public class DormitoryListFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dormitory_list, container, false);
        nextPage(view,getActivity());
        return view;
    }



    public void nextPage(View view, final Context context){
        ImageButton imageButton=(ImageButton)view.findViewById(R.id.ib_dormitory_list_notice);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DormitoryNoticeActivity.class);
                startActivity(intent);

            }
        });
    }
}
