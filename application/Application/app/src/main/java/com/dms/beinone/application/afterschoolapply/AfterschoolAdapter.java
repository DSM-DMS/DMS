package com.dms.beinone.application.afterschoolapply;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.beinone.application.R;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Commands;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by BeINone on 2017-01-31.
 */

public class AfterschoolAdapter extends RecyclerView.Adapter<AfterschoolAdapter.ViewHolder> {

    private Context mContext;
    private List<Afterschool> mAfterschoolList;
    private SharedPreferences mAccountPrefs;

    public AfterschoolAdapter(Context context, List<Afterschool> afterschoolList) {
        mContext = context;
        mAfterschoolList = afterschoolList;
        mAccountPrefs = context.getSharedPreferences(
                context.getString(R.string.PREFS_ACCOUNT), Context.MODE_PRIVATE);
    }

    @Override
    public AfterschoolAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_afterschool, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AfterschoolAdapter.ViewHolder holder, int position) {
        holder.bind(mAfterschoolList.get(position));
    }

    @Override
    public int getItemCount() {
        return mAfterschoolList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTV;
        private TextView mInstructorTV;
        private TextView mPlaceTV;
        private TextView mDayTV;
        private TextView mTargetTV;
        private Button mApplyBtn;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleTV = (TextView) itemView.findViewById(R.id.tv_afterschool_title);
            mInstructorTV = (TextView) itemView.findViewById(R.id.tv_afterschool_instructor);
            mPlaceTV = (TextView) itemView.findViewById(R.id.tv_afterschool_place);
            mDayTV = (TextView) itemView.findViewById(R.id.tv_afterschool_day);
            mTargetTV = (TextView) itemView.findViewById(R.id.tv_afterschool_target);
            mApplyBtn = (Button) itemView.findViewById(R.id.btn_afterschoolapply_apply);
        }

        public void bind(final Afterschool afterschool) {
            mTitleTV.setText(afterschool.getTitle());
            mInstructorTV.setText(afterschool.getInstructor());
            mPlaceTV.setText(afterschool.getPlace());
            mDayTV.setText(getAfterschoolDayString(afterschool));
            mTargetTV.setText(String.valueOf(afterschool.getTarget()));

            mApplyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = mAccountPrefs.getString(
                            mContext.getString(R.string.PREFS_ACCOUNT_ID), "");

                    new ApplyAfterschoolTask().execute(id, afterschool.getNo());
                }
            });
        }

        private String getAfterschoolDayString(Afterschool afterschool) {
            boolean isFirstDay = true;
            String afterschoolDayString = "";

            if (afterschool.isOnMonday()) {
                afterschoolDayString = "월";
                isFirstDay = false;
            }

            if (afterschool.isOnTuesday()) {
                if (isFirstDay) {
                    afterschoolDayString = "화";
                    isFirstDay = false;
                } else {
                    afterschoolDayString += ", 화";
                }
            }

            if (afterschool.isOnWednesday()) {
                if (isFirstDay) {
                    afterschoolDayString = "수";
                    isFirstDay = false;
                } else {
                    afterschoolDayString += ", 수";
                }
            }

            if (afterschool.isOnSaturday()) {
                if (isFirstDay) {
                    afterschoolDayString = "토";
                } else {
                    afterschoolDayString += ", 토";
                }
            }

            return afterschoolDayString;
        }
    }

    private class ApplyAfterschoolTask extends AsyncTask<Object, Void, Integer> {

        @Override
        protected Integer doInBackground(Object... params) {
            int code = -1;

            String id = params[0].toString();
            int no = (int) params[1];

            try {
                code = applyAfterschool(id, no);
            } catch (IOException e) {
                return -1;
            } catch (JSONException e) {
                return -1;
            }

            return code;
        }

        @Override
        protected void onPostExecute(Integer code) {
            super.onPostExecute(code);

            if (code == 200) {
                // success
                Toast.makeText(mContext, R.string.afterschoolapply_apply_success, Toast.LENGTH_SHORT).show();
            } else if (code == 204) {
                // failure
                Toast.makeText(mContext, R.string.afterschoolapply_apply_failure, Toast.LENGTH_SHORT).show();
            } else {
                // error
                Toast.makeText(mContext, R.string.afterschoolapply_apply_error, Toast.LENGTH_SHORT).show();
            }
        }

        private int applyAfterschool(String id, int no) throws IOException, JSONException {
            JSONObject requestJSONObject = new JSONObject();
            requestJSONObject.put("id", id);
            requestJSONObject.put("no", no);

            Response response = HttpBox.post()
                    .setCommand(Commands.APPLY_AFTERSCHOOL)
                    .putBodyData(requestJSONObject)
                    .push();

            return response.getCode();
        }
    }

}
