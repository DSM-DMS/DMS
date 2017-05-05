//package com.dms.beinone.application.goingoutapply;
//
//import android.content.Context;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.content.ContextCompat;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.dms.beinone.application.R;
//import com.dms.beinone.application.dmsview.DMSButton;
//import com.dms.beinone.application.dmsview.DMSEditText;
//import com.dms.beinone.application.utils.EditTextUtils;
//import com.dms.boxfox.networking.HttpBox;
//import com.dms.boxfox.networking.HttpBoxCallback;
//import com.dms.boxfox.networking.datamodel.Request;
//import com.dms.boxfox.networking.datamodel.Response;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by BeINone on 2017-02-24.
// */
//
//public class GoingoutContentFragment extends Fragment {
//
//    public static final String SATURDAY = "saturday";
//    public static final String SUNDAY = "sunday";
//
//    private EditText mReasonET;
//    private TextView mReasonTV;
//
//    public static GoingoutContentFragment newInstance(Context context, String date) {
//        Bundle args = new Bundle();
//        args.putString(context.getString(R.string.ARGS_DATE), date);
//
//        GoingoutContentFragment fragment = new GoingoutContentFragment();
//        fragment.setArguments(args);
//
//        return fragment;
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_goingoutapply_content, container, false);
//        init(view);
//
//        return view;
//    }
//
//    /**
//     * Initializes the edit text
//     *
//     * @param rootView view to find child views
//     */
//    private void init(View rootView) {
//        mReasonTV = (TextView) rootView.findViewById(R.id.tv_goingoutcontent_reason);
//
//        mReasonET = (DMSEditText) rootView.findViewById(R.id.et_goingoutcontent_reason);
//        mReasonET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    mReasonTV.setTextColor(
//                            ContextCompat.getColor(getContext(), R.color.colorPrimary));
//                } else {
//                    mReasonTV.setTextColor(
//                            ContextCompat.getColor(getContext(), android.R.color.primary_text_light));
//                    // hide the soft keyboard when touch outside
//                    EditTextUtils.hideKeyboard(getContext(), (EditText) v);
//                }
//            }
//        });
//
//        DMSButton applyBtn = (DMSButton) rootView.findViewById(R.id.btn_goingoutcontent_apply);
//        applyBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String reason = mReasonET.getText().toString().trim();
//                if (reason.length() == 0) {
//                    Toast.makeText(getContext(), R.string.rewardscoreapply_nocontent, Toast.LENGTH_SHORT)
//                            .show();
//                } else {
//                    String date = getArguments().getString(getString(R.string.ARGS_DATE));
//                    applyGoingout();
//                }
//            }
//        });
//    }
//
//    private void clearView() {
//        mReasonET.setText("");
//    }
//
//    private void applyGoingout(final boolean sat, final boolean sun) throws IOException {
//        try {
//            JSONObject params = new JSONObject();
//            params.put("sat", sat);
//            params.put("sun", sun);
//
//            HttpBox.put(getContext(), "/apply/goingout")
//                    .putBodyData(params)
//                    .push(new HttpBoxCallback() {
//                        @Override
//                        public void done(Response response) {
//                            int code = response.getCode();
//                            switch (code) {
//                                case HttpBox.HTTP_OK:
//                                    setApplyStatus(new Goingout(sat, sun));
//                                    Toast.makeText(getContext(), R.string.goingoutapply_apply_ok, Toast.LENGTH_SHORT).show();
//                                    break;
//                                case HttpBox.HTTP_BAD_REQUEST:
//                                    Toast.makeText(getContext(), R.string.http_bad_request, Toast.LENGTH_SHORT).show();
//                                    break;
//                                case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
//                                    Toast.makeText(getContext(), R.string.goingoutapply_apply_internal_server_error, Toast.LENGTH_SHORT).show();
//                                    break;
//                                default:
//                                    break;
//                            }
//                        }
//
//                        @Override
//                        public void err(Exception e) {
//                            System.out.println("Error in GoingoutApplyFragment: PUT /apply/goingout");
//                            e.printStackTrace();
//                        }
//                    });
//        } catch (JSONException e) {
//            System.out.println("JSONException in GoingoutApplyFragment: PUT /apply/goingout");
//            e.printStackTrace();
//        }
//    }
//}
