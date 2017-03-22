package com.dms.beinone.application.meal;

import android.content.Context;
import android.os.AsyncTask;

import com.dms.beinone.application.utils.DateUtils;
import com.dms.beinone.application.utils.JSONParser;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Request;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BeINone on 2017-03-11.
 */

public class LoadMealTask extends AsyncTask<Date, Void, Meal> {

    private Context mContext;
    private OnPostExecuteListener mListener;

    public LoadMealTask(Context context, OnPostExecuteListener l) {
        mContext = context;
        mListener = l;
    }

    @Override
    protected Meal doInBackground(Date... dates) {
        Meal meal = null;

        int year = DateUtils.getYear(dates[0]);
        int month = DateUtils.getMonth(dates[0]);
        int date = DateUtils.getDate(dates[0]);

        try {
            meal = loadMeal(year, month, date);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return meal;
    }

    @Override
    protected void onPostExecute(Meal meal) {
        super.onPostExecute(meal);
        mListener.onPostExecute(meal);
    }

    /**
     * load the meal information from server
     * @return Meal object that contains the meal information of the date
     * @throws IOException
     * @throws JSONException
     */
    private Meal loadMeal(int year, int month, int day) throws IOException, JSONException {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("year", String.valueOf(year));
        requestParams.put("month", String.valueOf(month));
        requestParams.put("day", String.valueOf(day));

        Response response = HttpBox.post(mContext, "/school/meal", Request.TYPE_GET)
                .putBodyData(requestParams)
                .push();

        JSONObject mealJSONObject = response.getJsonObject();

        return JSONParser.parseMealJSON(mealJSONObject);
    }

    public interface OnPostExecuteListener {
        void onPostExecute(Meal meal);
    }

}