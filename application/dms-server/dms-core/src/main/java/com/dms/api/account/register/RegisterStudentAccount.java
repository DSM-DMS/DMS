package com.dms.api.account.register;

import java.sql.SQLException;

import org.boxfox.dms.secure.SecureManager;
import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;

import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;
import com.dms.utilities.support.JobResult;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/account/register/student", method = {HttpMethod.POST})
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
    public void handle(RoutingContext ctx) {

        String uid = ctx.request().getParam("uid");
        String id = ctx.request().getParam("id");
        String password = ctx.request().getParam("password");
        
        try {
            if (Guardian.checkParameters(uid, id, password) && uid.length() > 0 && id.length() > 0 && password.length() > 0) {
                JobResult result = userManager.register(uid, id, password);
                if (result.isSuccess()) {
                    ctx.response().setStatusCode(201);
                    ctx.response().setStatusMessage(result.getMessage()).end();
                } else {
                    // Conflict
                    ctx.response().setStatusCode(409);
                    ctx.response().setStatusMessage(result.getMessage()).end();
                    registerRequestSecureManager.invalidRequest(ctx);
                }
            } else {
                // Any null in parameters
                ctx.response().setStatusCode(400).end();
                ctx.response().close();
                secureManager.invalidRequest(ctx);
            }
        } catch (SQLException e) {
            ctx.response().setStatusCode(500).end();
            ctx.response().close();
            secureManager.invalidRequest(ctx);
            Log.l("SQLException");
        }
        Log.l("Register Request (", id, ", ", ctx.request().remoteAddress(), ") status : " + ctx.response().getStatusCode());
    }

}
