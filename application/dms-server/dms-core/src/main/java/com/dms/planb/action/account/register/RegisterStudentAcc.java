package com.dms.planb.action.account.register;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.JobResult;
import org.boxfox.dms.utilities.log.Log;

import org.boxfox.dms.utilities.actions.support.PrecedingWork;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/account/register/student", method = {HttpMethod.POST})
public class RegisterStudentAcc implements Handler<RoutingContext> {
    private UserManager userManager;

    public RegisterStudentAcc() {
        userManager = new UserManager();
    }

    @Override
    public void handle(RoutingContext context) {
        context = PrecedingWork.putHeadersOnly(context);
        
        String uid = context.request().getParam("uid");
        String id = context.request().getParam("id");
        String password = context.request().getParam("password");
        
        try {
            if (Guardian.checkParameters(uid, id, password) && uid.length() > 0 && id.length() > 0 && password.length() > 0) {
                JobResult result = userManager.register(uid, id, password);
                if (result.isSuccess()) {
                    context.response().setStatusCode(201);
                    context.response().setStatusMessage(result.getMessage()).end();
                } else {
                    // Conflict
                    context.response().setStatusCode(409);
                    context.response().setStatusMessage(result.getMessage()).end();
                }
            } else {
                // Any null in parameters
                context.response().setStatusCode(400).end();
                context.response().close();
            }
        } catch (SQLException e) {
            context.response().setStatusCode(500).end();
            context.response().close();
            Log.l("SQLException");
        }
        Log.l("Register Request (", id, ", ", context.request().remoteAddress(), ") status : " + context.response().getStatusCode());
    }

}
