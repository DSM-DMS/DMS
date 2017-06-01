package com.dms.beinone.application.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.dms.beinone.application.R;
import com.dms.beinone.application.models.Goingout;
import com.dms.beinone.application.utils.JSONParser;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by BeINone on 2017-03-20.
 */

public class GoingoutApplyFragment extends Fragment {

    private ToggleButton mSaturdayTB;
    private ToggleButton mSundayTB;
    private Button mApplyBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goingout, container, false);
        init(view);

        return view;
    }

    /**
     * 초기화
     *
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        getActivity().setTitle(R.string.goingout_apply);

        mSaturdayTB = (ToggleButton) rootView.findViewById(R.id.tb_goingout_saturday);
        mSundayTB = (ToggleButton) rootView.findViewById(R.id.tb_goingout_sunday);
        mApplyBtn = (Button) rootView.findViewById(R.id.btn_goingout_apply);

        mApplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean sat = false;
                boolean sun = false;
                if (mSaturdayTB.isChecked()) sat = true;
                if (mSundayTB.isChecked()) sun = true;

                try {
                    applyGoingout(sat, sun);
                } catch (IOException e) {
                    System.out.println("IOException in GoingoutApplyFragment: applyGoingout()");
                    e.printStackTrace();
                }
            }
        });

        try {
            loadGoingoutApplyStatus();
        } catch (IOException e) {
            System.out.println("IOException in GoingoutApplyFragment: loadGoingoutApplyStatus()");
            e.printStackTrace();
        }
    }

    private void setApplyStatus(Goingout goingout) {
        mSaturdayTB.setChecked(goingout.isSat());
        mSundayTB.setChecked(goingout.isSun());
    }

    private void loadGoingoutApplyStatus() throws IOException {
        HttpBox.get(getContext(), "/apply/goingout")
                .push(new HttpBoxCallback() {
                    @Override
                    public void done(Response response) {
                        int code = response.getCode();
                        switch (code) {
                            case HttpBox.HTTP_OK:
                                try {
                                    Goingout goingout = JSONParser.parseGoingoutApplyStatusJSON(response.getJsonObject());
                                    setApplyStatus(goingout);
                                } catch (JSONException e) {
                                    System.out.println("JSONException in GoingoutApplyFragment: /apply/goingout");
                                    e.printStackTrace();
                                }
                                break;
                            case HttpBox.HTTP_NO_CONTENT:
                                Toast.makeText(getContext(), R.string.goingout_load_no_content, Toast.LENGTH_SHORT).show();
                                break;
                            case HttpBox.HTTP_BAD_REQUEST:
                                Toast.makeText(getContext(), R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                                break;
                            case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                Toast.makeText(getContext(), R.string.goingout_load_internal_server_error, Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void err(Exception e) {
                        System.out.println("Error in GoingoutApplyFragment: /apply/goingout");
                        e.printStackTrace();
                    }
                });
    }

    private void applyGoingout(final boolean sat, final boolean sun) throws IOException {
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
                                    setApplyStatus(new Goingout(sat, sun));
                                    Toast.makeText(getContext(), R.string.goingout_apply_ok, Toast.LENGTH_SHORT).show();
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
                            System.out.println("Error in GoingoutApplyFragment: PUT /apply/goingout");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException in GoingoutApplyFragment: PUT /apply/goingout");
            e.printStackTrace();
        }
    }
}
