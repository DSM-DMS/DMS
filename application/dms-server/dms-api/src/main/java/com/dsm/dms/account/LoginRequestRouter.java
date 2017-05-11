package com.dsm.dms.account;

import com.dsm.dms.secure.ApiRequestManager;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.utilities.actions.RouteRegistration;

/**
 * Created by boxfox on 2017-04-05.
 */
@RouteRegistration(path = "/api/login/", method = {HttpMethod.POST})
public class LoginRequestRouter implements Handler<RoutingContext> {
    private ApiRequestManager requestManager;

    public LoginRequestRouter(){
        requestManager = new ApiRequestManager();
    }

    public void handle(RoutingContext context)
    {
        String apiKey = context.request().getParam("api_key");
        String id = context.request().getParam("id");
        String password = context.request().getParam("password");
        if(!Guardian.checkParameters(apiKey, id, password)||!requestManager.checkRequestValid(apiKey)){
            
        }


        context.response().end("");
    }

}
