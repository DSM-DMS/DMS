package com.dms.planb.action.account;

import java.sql.SQLException;

import org.boxfox.dms.secure.Guardian;
import org.boxfox.dms.user.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.JobResult;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.CORSHeader;

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
        context = CORSHeader.putHeaders(context);

        String uid = context.request().getParam("uid");
        String id = context.request().getParam("id");
        String password = context.request().getParam("password");
        try {
            if (Guardian.checkParameters(uid, id, password)) {
                JobResult result = userManager.register(uid, id, password);
                if (result.isSuccess()) {
                    context.response().setStatusCode(201);
                    context.response().setStatusMessage(result.getMessage()).end();
                    context.response().close();
                } else {
                    context.response().setStatusCode(409);
                    context.response().setStatusMessage(result.getMessage()).end();
                    context.response().close();
                }
            } else {
                Log.l("null 입니다.");
                // Null in any parameters
            }
        } catch (SQLException e) {
            context.response().setStatusCode(500).end();
            context.response().close();

            Log.l("SQLException");
        }
        Log.l("Register Request (",id,", ",context.request().remoteAddress(),") status : "+context.response().getStatusCode());

    }
}
