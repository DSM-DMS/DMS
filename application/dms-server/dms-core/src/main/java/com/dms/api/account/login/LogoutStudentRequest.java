package com.dms.api.account.login;

import org.boxfox.dms.util.UserManager;

import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/account/logout/student", method = {HttpMethod.POST})
public class LogoutStudentRequest implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext ctx) {
        UserManager.removeCookie(ctx);
        
        ctx.response().setStatusCode(201).end();
        ctx.response().close();
    }
}
