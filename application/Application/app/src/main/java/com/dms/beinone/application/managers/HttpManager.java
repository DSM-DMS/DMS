package com.dms.beinone.application.managers;

import android.content.Context;

import com.dms.beinone.application.DMSService;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by BeINone on 2017-07-10.
 */

public class HttpManager {

    public static DMSService createDMSService(Context context) {
        PersistentCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));

        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();

        return new Retrofit.Builder()
                .baseUrl(DMSService.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(DMSService.class);
    }
}
