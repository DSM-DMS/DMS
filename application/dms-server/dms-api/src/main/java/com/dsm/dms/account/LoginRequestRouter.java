package com.dsm.dms.account;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.utilities.actions.RouteRegistration;

/**
 * Created by boxfox on 2017-04-05.
 */
@RouteRegistration(path = "/api/login/form", method = {HttpMethod.GET})
public class LoginRequestRouter implements Handler<RoutingContext> {

    public void handle(RoutingContext context)
    {
        context.response().end("");
    }

}
