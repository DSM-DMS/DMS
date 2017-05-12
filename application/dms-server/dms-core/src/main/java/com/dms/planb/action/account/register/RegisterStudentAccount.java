package com.dms.planb.action.account.register;

import java.sql.SQLException;

import org.boxfox.dms.secure.SecureManager;
import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.JobResult;
import org.boxfox.dms.utilities.log.Log;


import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/account/register/student", method = {HttpMethod.POST})
public class RegisterStudentAccount implements Handler<RoutingContext> {
    private UserManager userManager;
    private SecureManager secureManager;
    private SecureManager registerRequestSecureManager;

    public RegisterStudentAccount() {
        userManager = new UserManager();
        secureManager = SecureManager.create(this.getClass());
        registerRequestSecureManager = SecureManager.create("RegisterRequestSecureManager", 5,10);
    }

    @Override
    public void handle(RoutingContext context) {

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
                    registerRequestSecureManager.invalidRequest(context);
                }
            } else {
                // Any null in parameters
                context.response().setStatusCode(400).end();
                context.response().close();
                secureManager.invalidRequest(context);
            }
        } catch (SQLException e) {
            context.response().setStatusCode(500).end();
            context.response().close();
            secureManager.invalidRequest(context);
            Log.l("SQLException");
        }
        Log.l("Register Request (", id, ", ", context.request().remoteAddress(), ") status : " + context.response().getStatusCode());
    }

}
