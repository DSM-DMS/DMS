package com.dms.planb.action.account.login;

import java.sql.SQLException;

import org.boxfox.dms.secure.SecureManager;
import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/account/login/student", method = {HttpMethod.POST})
public class LoginStudentRequest implements Handler<RoutingContext> {
    private UserManager userManager;
    private SecureManager secureManager;
    private SecureManager loginRequestSecureManager;

    public LoginStudentRequest() {
        userManager = new UserManager();
        secureManager = SecureManager.create(this.getClass());
        loginRequestSecureManager = SecureManager.create("StudentLoginRequest", 5,30);
    }

    @Override
    public void handle(RoutingContext context) {

        String id = context.request().getParam("id");
        String password = context.request().getParam("password");
        String remember = context.request().getParam("remember");
        remember = (remember == null) ? "false" : "true";

        if (!Guardian.checkParameters(id, password, remember)) {
            context.response().setStatusCode(400).end();
            context.response().close();
            secureManager.invalidRequest(context);
            return;
        }

            try {
                boolean check = userManager.login(id, password);
                if (check) {
                    userManager.registerSession(context, Boolean.valueOf(remember), id);

                    context.response().setStatusCode(201).end("<script>window.location.href=document.referrer;</script>");
                    context.response().close();
                } else {
                    loginRequestSecureManager.invalidRequest(context);
                    context.response().setStatusCode(400).end();
                    context.response().close();
                }
            } catch (SQLException e) {
                context.response().setStatusCode(500).end();
                context.response().close();
                secureManager.invalidRequest(context);
                Log.l("SQLException");
            }

        Log.l("Login Request (", id, ", ", context.request().remoteAddress(), ") status : " + context.response().getStatusCode());
    }
}
