package com.dms.beinone.application;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

/**
 * Created by BeINone on 2017-01-23.
 */

public class RecyclerViewUtils {

    public static void setupRecyclerView(
            EmptySupportedRecyclerView recyclerView, Context context, View emptyView) {

        // set view to display when there is any content
        recyclerView.setEmptyView(emptyView);

        recyclerView.setHasFixedSize(true);

        // set layout manager as linear
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        // add divider between items
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(context, linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

}
