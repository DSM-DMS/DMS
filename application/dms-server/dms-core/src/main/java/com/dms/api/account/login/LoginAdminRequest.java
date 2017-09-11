package com.dms.api.account.login;

import java.sql.SQLException;

import org.boxfox.dms.secure.SecureManager;
import org.boxfox.dms.util.AdminManager;
import org.boxfox.dms.util.Guardian;

import com.dms.utilities.log.Log;
import com.dms.utilities.routing.RouteRegistration;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/account/login/admin", method = {HttpMethod.POST})
public class LoginAdminRequest implements Handler<RoutingContext> {
    private AdminManager adminManager;
    private SecureManager secureManager;
    private SecureManager loginRequestSecureManager;

    public LoginAdminRequest() {
        adminManager = new AdminManager();
        secureManager = SecureManager.create(LoginAdminRequest.class);
        loginRequestSecureManager = SecureManager.create("AdminLoginRequest", 10, 15);
    }

    @Override
    public void handle(RoutingContext ctx) {
        String id = ctx.request().getParam("id");
        String password = ctx.request().getParam("password");
        String remember = ctx.request().getParam("remember");
        remember = (remember == null) ? "false" : "true";
        
        if(!Guardian.checkParameters(id, password)) {
        	ctx.response().setStatusCode(400).end();
        	ctx.response().close();
            secureManager.invalidRequest(ctx);
        	return;
        }
        
        try {
            boolean check = adminManager.login(id, password);
            if (check) {
            	adminManager.registerSession(ctx, Boolean.valueOf(remember), id);
            	
            	ctx.response().setStatusCode(201).end();
                ctx.response().close();
            } else {
                ctx.response().setStatusCode(400).end();
                ctx.response().close();
                loginRequestSecureManager.invalidRequest(ctx);
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
