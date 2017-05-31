package com.dms.beinone.application.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.beinone.application.dialogs.ExtensionApplyDialogFragment;
import com.dms.beinone.application.models.Extension;
import com.dms.beinone.application.utils.DensityConverter;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by BeINone on 2017-03-02.
 */

public class ExtensionApplyFragment extends Fragment {

    private LinearLayout mContainer;
    private Spinner mSpinner;
    private Button mClickedSeatView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_extensionapply, container, false);
        init(view);

        return view;
    }

    /**
     * 초기화, FloatingActionButton 나타내기, RecyclerView 세팅
     *
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        getActivity().setTitle(R.string.extension_apply);

        mContainer = (LinearLayout) rootView.findViewById(R.id.container_extensionapply);

        mSpinner = (Spinner) rootView.findViewById(R.id.spinner_extensionapply);
        ArrayAdapter<CharSequence> spinnerAdapter =
                ArrayAdapter.createFromResource(getContext(), R.array.apply_extension,
                        android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(spinnerAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String className = parent.getItemAtPosition(position).toString();
                try {
                    if (className.equals("가온실")) {
                        loadExtensionClass(new Extension(Extension.OPTION_MAP, Extension.CLASS_GA));
                    } else if (className.equals("나온실")) {
                        loadExtensionClass(new Extension(Extension.OPTION_MAP, Extension.CLASS_NA));
                    } else if (className.equals("다온실")) {
                        loadExtensionClass(new Extension(Extension.OPTION_MAP, Extension.CLASS_DA));
                    } else if (className.equals("라온실")) {
                        loadExtensionClass(new Extension(Extension.OPTION_MAP, Extension.CLASS_RA));
                    }
                } catch (IOException e) {
                    System.out.println("IOException in ExtensionApplyFragment: loadExtensionClass()");
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        try {
            loadExtensionClass(new Extension(Extension.OPTION_MAP, Extension.CLASS_GA));
        } catch (IOException e) {
            System.out.println("IOException in ExtensionApplyFragment: loadExtensionClass()");
            e.printStackTrace();
        }
    }

    private void drawSeat(JSONObject rootJSONObject) throws JSONException {
        JSONArray mapJSONArray = rootJSONObject.getJSONArray("map");

        mContainer.removeAllViews();
        for (int y = 0; y < mapJSONArray.length(); y++) {
            LinearLayout linearLayout = new LinearLayout(getContext());

            JSONArray seatJSONArray = mapJSONArray.getJSONArray(y);
            for (int x = 0; x < seatJSONArray.length(); x++) {
                Button seatView = null;

                String seatStatus = seatJSONArray.getString(x);
                if (seatStatus.equals("0")) {
                    // wall
                    seatView = (Button) LayoutInflater.from(getContext())
                            .inflate(R.layout.view_extensionapply_seat_wall, null);
                } else if (isNumeric(seatStatus)) {
                    // available seat
                    seatView = (Button) LayoutInflater.from(getContext())
                            .inflate(R.layout.view_extensionapply_seat_available, null);
                    seatView.setText(seatStatus);
                    final int seat = Integer.valueOf(seatView.getText().toString().trim());
                    seatView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mClickedSeatView = (Button) v;
                            int selectedClass = getClazz(mSpinner.getSelectedItemPosition());
                            ExtensionApplyDialogFragment
                                    .newInstance(getContext(), new Extension(selectedClass, seat))
                                    .show(getFragmentManager(), null);
                        }
                    });
                } else {
                    // unavailable seat
                    seatView = (Button) LayoutInflater.from(getContext())
                            .inflate(R.layout.view_extensionapply_seat_unavailable, null);
                    seatView.setText(seatStatus);
                }
                seatView.setLayoutParams(new LinearLayout.LayoutParams(
                        0, (int) DensityConverter.dpToPx(getContext(), 106f), 1.0f));

                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(seatView);
            }

            mContainer.addView(linearLayout);
        }
    }

    private boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    private int getClazz(int position) {
        return position + 1;
    }

    private void loadExtensionClass(Extension extension) throws IOException {
        try {
            JSONObject params = new JSONObject();
            params.put("option", extension.getOption());
            params.put("class", extension.getClazz());

            HttpBox.get(getContext(), "/apply/extension/class")
                    .putQueryString(params)
                    .push(new HttpBoxCallback() {
                        @Override
                        public void done(Response response) {
                            int code = response.getCode();
                            switch (code) {
                                case HttpBox.HTTP_OK:
                                    try {
                                        drawSeat(response.getJsonObject());
                                    } catch (JSONException e) {
                                        System.out.println("JSONException in ExtensionApplyFragment: drawSeat()");
                                        e.printStackTrace();
                                    }
                                    break;
                                case HttpBox.HTTP_BAD_REQUEST:
                                    Toast.makeText(getContext(), R.string.http_bad_request, Toast.LENGTH_SHORT).show();
                                    break;
                                case HttpBox.HTTP_INTERNAL_SERVER_ERROR:
                                    Toast.makeText(getContext(), R.string.extensionapply_load_internal_server_error, Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void err(Exception e) {
                            System.out.println("Error in ExtensionApplyFragment: GET /apply/extension/class");
                            e.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            System.out.println("JSONException in ExtensionApplyFragment: GET /apply/extension/class");
            e.printStackTrace();
        }
    }

}
