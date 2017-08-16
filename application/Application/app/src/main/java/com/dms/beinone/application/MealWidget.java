package com.dms.beinone.application;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

import com.dms.beinone.application.managers.HttpManager;
import com.dms.beinone.application.models.Meal;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

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

    private Meal mMeal;
    private static String title;

    static void updateAppWidget(final Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        DMSService dmsService = HttpManager.createDMSService(context);
        Date date = new Date();
        views.setTextViewText(R.id.appwidget_text,"Dddd");
        Calendar calendar=Calendar.getInstance();
        Call<JsonObject> call = dmsService.loadMeal(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime()));
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                switch (response.code()) {
                    case HTTP_OK:
                        Log.d("aaaaaaaaaaaa",String.valueOf(response.code()));
                        Meal meal;

                        JsonObject mealJson=response.body();

                        String breakfastArrStr = mealJson.get("breakfast").getAsString();
                        JsonArray breakfastJsonArr = new JsonParser().parse(breakfastArrStr).getAsJsonArray();
                        List<String> breakfast = new Gson().fromJson(breakfastJsonArr, new TypeToken<List<String>>(){}.getType());

                        String lunchArrStr = mealJson.get("lunch").getAsString();
                        JsonArray lunchJsonArr = new JsonParser().parse(lunchArrStr).getAsJsonArray();
                        List<String> lunch = new Gson().fromJson(lunchJsonArr, new TypeToken<List<String>>(){}.getType());

                        String dinnerArrStr = mealJson.get("dinner").getAsString();
                        JsonArray dinnerJsonArr = new JsonParser().parse(dinnerArrStr).getAsJsonArray();
                        List<String> dinner = new Gson().fromJson(dinnerJsonArr, new TypeToken<List<String>>(){}.getType());

                        meal=new Meal(breakfast, lunch, dinner);

                        CharSequence charSequence;
                        title=meal.getBreakfast().get(0);

                        break;
                    case HTTP_INTERNAL_SERVER_ERROR:
                        //Log.d("00000000",response.message());
                        break;
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
            }
        });

        //views.setTextViewText(R.id.appwidget_text, widgetText);
        views.setTextViewText(R.id.appwidget_text,title);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private void bind(int position,RemoteViews views) {
        if (mMeal != null) {
            switch (position){
                case 0:
                    String breakfast = TextUtils.join(", ", mMeal.getBreakfast());
                    views.setTextViewText(R.id.tv_meal_breakfast_content,breakfast);
                    break;
                case 1:
                    String lunch = TextUtils.join(", ", mMeal.getLunch());
                    views.setTextViewText(R.id.tv_meal_breakfast_content,lunch);
                    break;
                case 2:
                    String dinner = TextUtils.join(", ", mMeal.getDinner());
                    views.setTextViewText(R.id.tv_meal_breakfast_content,dinner);
                    break;
                default:
                    break;

            }

        }
    }

    private void setMeal(Meal meal,RemoteViews views) {
        mMeal = meal;
        bind(0,views);
    }


    private Meal parseMealJson(JsonObject mealJson) {
        String breakfastArrStr = mealJson.get("breakfast").getAsString();
        JsonArray breakfastJsonArr = new JsonParser().parse(breakfastArrStr).getAsJsonArray();
        List<String> breakfast = new Gson().fromJson(breakfastJsonArr, new TypeToken<List<String>>(){}.getType());

        String lunchArrStr = mealJson.get("lunch").getAsString();
        JsonArray lunchJsonArr = new JsonParser().parse(lunchArrStr).getAsJsonArray();
        List<String> lunch = new Gson().fromJson(lunchJsonArr, new TypeToken<List<String>>(){}.getType());

        String dinnerArrStr = mealJson.get("dinner").getAsString();
        JsonArray dinnerJsonArr = new JsonParser().parse(dinnerArrStr).getAsJsonArray();
        List<String> dinner = new Gson().fromJson(dinnerJsonArr, new TypeToken<List<String>>(){}.getType());

        return new Meal(breakfast, lunch, dinner);
    }


}

