package com.dms.beinone.application.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dms.beinone.application.R;
import com.dms.beinone.application.models.ItemList;
import com.dms.beinone.application.models.ItemListContent;
import com.dms.beinone.application.views.custom.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BeINone on 2017-05-18.
 */

public class ApplyListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apply_list, container, false);
        init(view);

        return view;
    }

    private void init(View rootView) {
//        LinearLayout itemlist = (LinearLayout) rootView.findViewById(R.id.layout_itemlist);
//        final LinearLayout content = (LinearLayout) rootView.findViewById(R.id.layout_itemlist_content);

//        itemlist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                content.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                final int targetHeight = content.getMeasuredHeight();
//
//                // Older versions of android (pre API 21) cancel animations for views with a height of 0.
//                content.getLayoutParams().height = 1;
//                content.setVisibility(View.VISIBLE);
//                Animation a = new Animation() {
//                    @Override
//                    protected void applyTransformation(float interpolatedTime, Transformation t) {
//                        content.getLayoutParams().height = (int)(targetHeight * interpolatedTime);
//                        Log.d("testLog", "" + interpolatedTime);
//                        content.requestLayout();
//                    }
//
//                    @Override
//                    public boolean willChangeBounds() {
//                        return true;
//                    }
//                };
//
//                // 1dp/ms
//                a.setDuration(500);
//                content.startAnimation(a);


//                ScaleAnimation animation = new ScaleAnimation();
//                content.animate().scaleY(content.getHeight()).setDuration(2000)
//                .setListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        content.setVisibility(View.VISIBLE);
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//
//                    }
//                });
//            }
//        });


//        List<ItemList> itemLists = createItemLists();
//
//        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_apply_list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(new ItemListAdapter(getContext(), itemLists));


        ExpandableLayout expandableLayout = (ExpandableLayout) rootView.findViewById(R.id.expandablelayout_apply_list);

        expandableLayout.addView(createParentView("연장신청", ContextCompat.getColor(getContext(), R.color.applyList1)),
                createChildView(ContextCompat.getColor(getContext(), R.color.applyList1)));
        expandableLayout.addView(createParentView("잔류신청", ContextCompat.getColor(getContext(), R.color.applyList2)),
                createChildView(ContextCompat.getColor(getContext(), R.color.applyList2)));
        expandableLayout.addView(createParentView("외출신청", ContextCompat.getColor(getContext(), R.color.applyList3)),
                createChildView(ContextCompat.getColor(getContext(), R.color.applyList3)));
        expandableLayout.addView(createParentView("상점신청", ContextCompat.getColor(getContext(), R.color.applyList4)),
                createChildView(ContextCompat.getColor(getContext(), R.color.applyList4)));
    }

    private View createParentView(String text, int backgroundColor) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_apply_list_parent, null);

//        view.setBackgroundColor(backgroundColor);
        TextView titleTV = (TextView) view.findViewById(R.id.tv_apply_list_parent_title);
        titleTV.setText(text);
        titleTV.setBackgroundColor(backgroundColor);
        return view;
    }

    private View createChildView(int backgroundColor) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_apply_list_child, null);

        View layout = view.findViewById(R.id.layout_apply_list_child);
        layout.setBackgroundColor(backgroundColor);
        return view;
    }

    private List<ItemList> createItemLists() {
        List<ItemList> itemLists = new ArrayList<>();

        List<ItemListContent> itemListContents1 = new ArrayList<>();
        itemListContents1.add(new ItemListContent(""));
        itemLists.add(new ItemList("연장신청", itemListContents1));

        List<ItemListContent> itemListContents2 = new ArrayList<>();
        itemListContents2.add(new ItemListContent(""));
        itemLists.add(new ItemList("잔류신청", itemListContents2));

        List<ItemListContent> itemListContents3 = new ArrayList<>();
        itemListContents3.add(new ItemListContent(""));
        itemLists.add(new ItemList("외출신청", itemListContents3));

        List<ItemListContent> itemListContents4 = new ArrayList<>();
        itemListContents4.add(new ItemListContent(""));
        itemLists.add(new ItemList("상점신청", itemListContents4));

        return itemLists;
    }
}
