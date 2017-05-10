package com.dms.planb.action.account.register;

import java.sql.SQLException;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.util.AdminManager;
import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.JobResult;

@RouteRegistration(path = "/account/register/admin", method = {HttpMethod.POST})
public class RegisterTeacherAccount implements Handler<RoutingContext> {
    private AdminManager adminManager;

    public RegisterTeacherAccount() {
        adminManager = new AdminManager();
    }

    @Override
    public void handle(RoutingContext context) {

        if (!AdminManager.isAdmin(context)) {
            context.response().setStatusCode(404).end();
            context.response().close();
            return;
        }
        String id = context.request().getParam("id");
        String password = context.request().getParam("password");
        String passwordConfirm = context.request().getParam("password-confirm");
        String name = context.request().getParam("name");
        if (!Guardian.checkParameters(id, password, passwordConfirm, name)) {
            context.response().setStatusCode(400).end();
            context.response().close();
        } else {
            if (!password.equals(passwordConfirm) || adminManager.checkIdExists(id)) {
                context.response().setStatusCode(204).end();
                context.response().close();
            }else{
                try {
                    JobResult result = adminManager.register(id, password, name);
                    if(result.isSuccess()){
                        context.response().setStatusCode(200).end();
                        context.response().close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(!context.response().closed()){
                    context.response().setStatusCode(500).end();
                    context.response().close();
                }
            }
        }
    }
}
