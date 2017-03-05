package com.dms.planb.action.account;

import java.sql.SQLException;

import org.boxfox.dms.secure.Guardian;
import org.boxfox.dms.user.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.JobResult;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "account/register/student", method = {HttpMethod.POST})
public class RegisterStudentAcc implements Handler<RoutingContext> {
    private UserManager userManager;
    @Override
    public void handle(RoutingContext context) {
        DataBase database = DataBase.getInstance();
        SafeResultSet resultSet;

        String uid = context.request().getParam("uid");
        String id = context.request().getParam("id");
        String password = context.request().getParam("password");

        try {
        if (Guardian.checkParameters(uid, id, password)) {
            JobResult result = userManager.register(uid, id, password);
            if (result.isSuccess()) {
                context.response().setStatusCode(200).end();
                context.response().setStatusMessage(result.getMessage());
                context.response().close();
            } else {
                context.response().setStatusMessage(result.getMessage());
            }
        } else {
            //파라미터중 null이 존재
        }
        } catch (SQLException e) {
            context.response().setStatusCode(500).end();
            context.response().close();

            Log.l("SQLException");
        }
    }


}
