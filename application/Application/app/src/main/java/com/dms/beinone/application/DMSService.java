package com.dms.beinone.application;

import com.dms.beinone.application.mypage.Account;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by BeINone on 2017-04-18.
 */

public interface DMSService {

    @GET("account/student")
    Call<Account> loadMyPage();
}
