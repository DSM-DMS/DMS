package com.dms.planb.action.account.login;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.PrecedingWork;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/account/login/student", method = {HttpMethod.POST})
public class LoginStudentRequest implements Handler<RoutingContext> {
    private UserManager userManager;

    public LoginStudentRequest() {
        userManager = new UserManager();
    }

    @Override
    public void handle(RoutingContext context) {
        context = PrecedingWork.putHeadersOnly(context);

        String id = context.request().getParam("id");
        String password = context.request().getParam("password");
        String remember = context.request().getParam("remember");
        String recaptcha = context.request().getParam("g-recaptcha-response"); //recaptcha response 이름 수정해야함
        remember = (remember == null) ? "false" : "true";
        
        if(!Guardian.checkParameters(id, password, remember)) {
        	context.response().setStatusCode(400).end();
        	context.response().close();
        	return;
        }
        
        try {
            boolean check = userManager.login(id, password);
            if (check) {
            	userManager.registerSession(context, Boolean.valueOf(remember), id);
            	
            	context.response().setStatusCode(201).end();
                context.response().close();
            } else {
                context.response().setStatusCode(400).end();
                context.response().close();
            }
        } catch (SQLException e) {
            context.response().setStatusCode(500).end();
            context.response().close();

            Log.l("SQLException");
        }
        Log.l("Login Request (", id, ", ", context.request().remoteAddress(), ") status : " + context.response().getStatusCode());
    }
}
