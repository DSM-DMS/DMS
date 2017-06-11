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
    public void handle(RoutingContext ctx) {

        String id = ctx.request().getParam("id");
        String password = ctx.request().getParam("password");
        String remember = ctx.request().getParam("remember");
        remember = (remember == null) ? "false" : "true";

        if (!Guardian.checkParameters(id, password, remember)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
            secureManager.invalidRequest(ctx);
            return;
        }

            try {
                boolean check = userManager.login(id, password);
                if (check) {
                    userManager.registerSession(ctx, Boolean.valueOf(remember), id);

                    ctx.response().setStatusCode(201).end("<script>window.location.href=document.referrer;</script>");
                    ctx.response().close();
                } else {
                    loginRequestSecureManager.invalidRequest(ctx);
                    ctx.response().setStatusCode(400).end();
                    ctx.response().close();
                }
            } catch (SQLException e) {
                ctx.response().setStatusCode(500).end();
                ctx.response().close();
                secureManager.invalidRequest(ctx);
                Log.l("SQLException");
            }

        Log.l("Login Request (", id, ", ", ctx.request().remoteAddress(), ") status : " + ctx.response().getStatusCode());
    }
}
