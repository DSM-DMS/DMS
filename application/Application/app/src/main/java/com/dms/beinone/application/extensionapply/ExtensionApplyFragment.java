package com.dms.beinone.application.extensionapply;

import android.os.AsyncTask;
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
import com.dms.beinone.application.utils.DensityConverter;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Request;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
     * @param rootView 필요한 뷰를 찾을 최상위 뷰
     */
    private void init(View rootView) {
        getActivity().setTitle(R.string.nav_extensionapply);

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
                if (className.equals("가온실")) {
                    new LoadExtensionClassTask().execute(new Extension("map", Extension.CLASS_GA));
                } else if (className.equals("나온실")) {
                    new LoadExtensionClassTask().execute(new Extension("map", Extension.CLASS_NA));
                } else if (className.equals("다온실")) {
                    new LoadExtensionClassTask().execute(new Extension("map", Extension.CLASS_DA));
                } else if (className.equals("라온실")) {
                    new LoadExtensionClassTask().execute(new Extension("map", Extension.CLASS_RA));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        new LoadExtensionClassTask().execute(new Extension("map", Extension.CLASS_GA));
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
                            int selectedClass = getClassId(mSpinner.getSelectedItemPosition());
                            ExtensionApplyDialog
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

    private int getClassId(int position) {
        return position + 1;
    }

    private class LoadExtensionClassTask extends AsyncTask<Extension, Void, Object[]> {

        @Override
        protected Object[] doInBackground(Extension... params) {
            Object[] results = null;

            try {
                results = loadExtensionClass(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

            return results;
        }

        @Override
        protected void onPostExecute(Object[] results) {
            super.onPostExecute(results);

            if (results == null) {
                Toast.makeText(getContext(), R.string.extensionapply_load_error, Toast.LENGTH_SHORT)
                        .show();
            } else {
                int code = (int) results[0];
                JSONObject mapJSONObject = (JSONObject) results[1];

                try {
                    if (code == 200) {
                        drawSeat(mapJSONObject);
                    } else if (code == 204) {
                        Toast.makeText(getContext(), R.string.extensionapply_load_failure,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), R.string.extensionapply_load_error,
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), R.string.extensionapply_draw_error,
                            Toast.LENGTH_SHORT).show();
                }
            }
        }

        private Object[] loadExtensionClass(Extension extension) throws IOException, JSONException {
            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("option", extension.getOption());
            requestParams.put("class", String.valueOf(extension.getClassId()));

            Response response = HttpBox.post(getContext(), "/apply/extension/class", Request.TYPE_GET)
                    .putBodyData(requestParams)
                    .push();

            return new Object[]{response.getCode(), response.getJsonObject()};
        }
    }

}
