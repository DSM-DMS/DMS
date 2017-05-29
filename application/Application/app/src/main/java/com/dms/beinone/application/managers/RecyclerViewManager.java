package com.dms.beinone.application.managers;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.dms.beinone.application.views.custom.EmptySupportedRecyclerView;
import com.dms.beinone.application.VerticalSpaceItemDecoration;
import com.dms.beinone.application.utils.DensityConverter;

/**
 * Created by BeINone on 2017-01-23.
 */

public class RecyclerViewManager {

    public static final int TYPE_ITEM = 1;
    public static final int TYPE_FOOTER = 2;

    public static void setupRecyclerView(EmptySupportedRecyclerView recyclerView,
                                         Context context, View emptyView) {

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

    public static void setupCardRecyclerView(EmptySupportedRecyclerView recyclerView,
                                             Context context, View emptyView) {

        // set view to display when there is any content
        recyclerView.setEmptyView(emptyView);

        recyclerView.setHasFixedSize(true);

        // set layout manager as linear
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        VerticalSpaceItemDecoration spaceItemDecoration =
                new VerticalSpaceItemDecoration((int) DensityConverter.pxTodp(context, 16));
        recyclerView.addItemDecoration(spaceItemDecoration);
    }

}
