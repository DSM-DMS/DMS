package com.dms.api.account.login;

import org.boxfox.dms.util.UserManager;

import com.dms.utilities.routing.RouteRegistration;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/account/logout/student", method = {HttpMethod.POST})
public class LogoutStudentRequest implements Handler<RoutingContext> {
    private UserManager userManager;

    public LogoutStudentRequest() {
        userManager = new UserManager();
    }

    @Override
    public void handle(RoutingContext ctx) {

        userManager.removeCookie(ctx);
        
        ctx.response().setStatusCode(201).end();
        ctx.response().close();
    }
}
