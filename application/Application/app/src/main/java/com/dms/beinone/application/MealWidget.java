package com.dms.beinone.application;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.dms.beinone.application.managers.HttpManager;
import com.dms.beinone.application.models.Meal;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dms.beinone.application.DMSService.HTTP_INTERNAL_SERVER_ERROR;
import static com.dms.beinone.application.DMSService.HTTP_OK;

/**
 * Implementation of App Widget functionality.
 */
public class MealWidget extends AppWidgetProvider {

    private static Meal mMeal;
    private ComponentName appWidgetId;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // Construct the RemoteViews object
        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.meal_app_widget);
        appWidgetId = new ComponentName(context, MealWidget.class);

        Date date = new Date();
/*
        if (mMeal == null) {
            try {
                loadMeal(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date), context, views);
            } catch (IOException e) {
                System.out.println("IOException in WidgetMeal error: loadMeal()");
                e.printStackTrace();
            }
        } else {
            bind(views);
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);*/


    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private void setMeal(Meal meal,RemoteViews views) {
        mMeal = meal;
        bind(views);
    }


    //다이얼로그에서는 RemoteViews 찾아서 거기서 뷰의아이디 찾아주고 데이터바인딩을 진행함
    private void bind(RemoteViews views) {
        if (mMeal != null) {
            String breakfast = TextUtils.join("\n", mMeal.getBreakfast());
            views.setTextViewText(R.id.meal_widget_content,breakfast);
//            String lunch = TextUtils.join(", ", mMeal.getLunch());
//            views.setTextViewText(R.id.meal_widget_launch,lunch);
//            String dinner = TextUtils.join(", ", mMeal.getDinner());
//            views.setTextViewText(R.id.meal_widget_diner,dinner);
        }
    }


    private Meal parseMealJson(JsonObject mealJson) {
        String breakfastArrStr = mealJson.get("breakfast").getAsString();
        JsonArray breakfastJsonArr = new JsonParser().parse(breakfastArrStr).getAsJsonArray();
        List<String> breakfast = new Gson().fromJson(breakfastJsonArr, new TypeToken<List<String>>() {
        }.getType());

        String lunchArrStr = mealJson.get("lunch").getAsString();
        JsonArray lunchJsonArr = new JsonParser().parse(lunchArrStr).getAsJsonArray();
        List<String> lunch = new Gson().fromJson(lunchJsonArr, new TypeToken<List<String>>() {
        }.getType());

        String dinnerArrStr = mealJson.get("dinner").getAsString();
        JsonArray dinnerJsonArr = new JsonParser().parse(dinnerArrStr).getAsJsonArray();
        List<String> dinner = new Gson().fromJson(dinnerJsonArr, new TypeToken<List<String>>() {
        }.getType());

        return new Meal(breakfast, lunch, dinner);
    }

   /* private void loadMeal(String date, final Context context, final RemoteViews views) throws IOException {
        DMSService dmsService = HttpManager.createDMSService(context);
        Call<JsonObject> call = dmsService.loadMeal(date);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                switch (response.code()) {
                    case HTTP_OK:
                        Meal meal = parseMealJson(response.body());
                        setMeal(meal,views);
                        break;
                    case HTTP_INTERNAL_SERVER_ERROR:
                        Toast.makeText(context, R.string.meal_internal_server_error, Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }*/

}

