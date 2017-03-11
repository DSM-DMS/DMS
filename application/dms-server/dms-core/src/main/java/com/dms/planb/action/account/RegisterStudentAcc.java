package com.dms.planb.action.account;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.JobResult;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.PrecedingWork;

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
        context = PrecedingWork.putHeaders(context);
        
        String uid = null;
        String id = null;
        String password = null;
        
        uid = context.request().getParam("uid");
        id = context.request().getParam("id");
        password = context.request().getParam("password");
        
        try {
        	if (Guardian.checkParameters(uid, id, password) && uid.length() > 0 && id.length() > 0 && password.length() > 0) {
                JobResult result = userManager.register(uid, id, password);
                if (result.isSuccess()) {
                    context.response().setStatusCode(201);
                    context.response().setStatusMessage(result.getMessage()).end();
                    context.response().close();
                } else {
                	// Conflict
                    context.response().setStatusCode(409);
                    context.response().setStatusMessage(result.getMessage()).end();
                    context.response().close();
                }
            } else {
            	// Any null in parameters
            	context.response().setStatusCode(404).end();
                context.response().close();
            }
        } catch (SQLException e) {
            context.response().setStatusCode(500).end();
            context.response().close();

            Log.l("SQLException");
        }
        Log.l("Register Request (",id,", ",context.request().remoteAddress(),") status : "+context.response().getStatusCode());

    }
}
