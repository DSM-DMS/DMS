package com.dms.beinone.application.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.beinone.application.activities.ExtensionActivity;
import com.dms.beinone.application.activities.LoginActivity;
import com.dms.beinone.application.activities.MeritActivity;
import com.dms.beinone.application.activities.StayActivity;
import com.dms.beinone.application.models.ItemList;
import com.dms.beinone.application.models.ItemListContent;
import com.dms.beinone.application.views.custom.ExpandableLayout;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
                createChildView(ContextCompat.getColor(getContext(), R.color.applyList1), R.drawable.fish, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getContext(), ExtensionActivity.class));
                    }
                }));
        expandableLayout.addView(createParentView("잔류신청", ContextCompat.getColor(getContext(), R.color.applyList2)),
                createChildView(ContextCompat.getColor(getContext(), R.color.applyList2), R.drawable.whale, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getContext(), StayActivity.class));
                    }
                }));
        expandableLayout.addView(createParentView("외출신청", ContextCompat.getColor(getContext(), R.color.applyList3)),
                createGoingoutChildView());
        expandableLayout.addView(createParentView("상점신청", ContextCompat.getColor(getContext(), R.color.applyList4)),
                createChildView(ContextCompat.getColor(getContext(), R.color.applyList4), R.drawable.seahorse, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getContext(), MeritActivity.class));
                    }
                }));
    }

    private View createParentView(String text, int backgroundColor) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_apply_list_parent, null);

        TextView titleTV = (TextView) view.findViewById(R.id.tv_apply_list_parent_title);
        titleTV.setText(text);
        titleTV.setBackgroundColor(backgroundColor);

        return view;
    }

    private View createChildView(int backgroundColor, int image, View.OnClickListener listener) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_apply_list_child, null);

        View layout = view.findViewById(R.id.layout_apply_list_child);
        layout.setBackgroundColor(backgroundColor);

        ImageView imageView = (ImageView) view.findViewById(R.id.iv_apply_list_child);
        imageView.setImageResource(image);

        ImageButton enterIB = (ImageButton) view.findViewById(R.id.ib_apply_list_child_enter);
        enterIB.setOnClickListener(listener);

        return view;
    }

    private View createGoingoutChildView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_apply_list_child_goingout, null);

        final Switch satSwitch = (Switch) view.findViewById(R.id.switch_apply_list_child_goingout_saturday);
        final Switch sunSwitch = (Switch) view.findViewById(R.id.switch_apply_list_child_goingout_sunday);

        Button applyBtn = (Button) view.findViewById(R.id.btn_apply_list_child_goingout_apply);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean sat = satSwitch.isChecked();
                boolean sun = sunSwitch.isChecked();

                try {
                    applyGoingout(sat, sun);
                } catch (IOException e) {
                    System.out.println("IOException in ApplyListFragment: PUT /apply/goingout");
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    private void applyGoingout(boolean sat, boolean sun) throws IOException {
        try {
            JSONObject params = new JSONObject();
            params.put("sat", sat);
            params.put("sun", sun);

            HttpBox.put(getContext(), "/apply/goingout")
                    .putBodyData(params)
                    .push(new HttpBoxCallback() {
                        @Override
                        public void done(Response response) {
                            int code = response.getCode();
                            switch (code) {
                                case HttpBox.HTTP_OK:
                                    Toast.makeText(getContext(), R.string.apply_ok, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_BAD_REQUEST:
                                    Toast.makeText(getContext(), R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                    Toast.makeText(getContext(), R.string.goingout_apply_internal_server_error, Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void err(Exception e) {
                            System.out.println("Error in ApplyListFragment: PUT /apply/goingout");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException in ApplyListFragment: PUT /apply/goingout");
            e.printStackTrace();
        }
    }
}
