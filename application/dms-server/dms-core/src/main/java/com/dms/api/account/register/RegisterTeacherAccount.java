package com.dms.api.account.register;

import java.sql.SQLException;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.util.AdminManager;
import org.boxfox.dms.util.Guardian;

import com.dms.utilities.routing.RouteRegistration;
import com.dms.utilities.support.JobResult;

@RouteRegistration(path = "/account/register/admin", method = {HttpMethod.POST})
public class RegisterTeacherAccount implements Handler<RoutingContext> {
    private AdminManager adminManager;

    public RegisterTeacherAccount() {
        adminManager = new AdminManager();
    }

    @Override
    public void handle(RoutingContext ctx) {
        if (!AdminManager.isAdmin(ctx)) {
            ctx.response().setStatusCode(404).end();
            ctx.response().close();
            return;
        }

        String id = ctx.request().getParam("id");
        String password = ctx.request().getParam("password");
        String passwordConfirm = ctx.request().getParam("password-confirm");
        String name = ctx.request().getParam("name");
        if (!Guardian.checkParameters(id, password, passwordConfirm, name)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
        } else {
            if (!password.equals(passwordConfirm) || adminManager.checkIdExists(id)) {
                ctx.response().setStatusCode(204).end();
                ctx.response().close();
            }else{
                try {
                    JobResult result = adminManager.register(id, password, name);
                    if(result.isSuccess()){
                        ctx.response().setStatusCode(200).end();
                        ctx.response().close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(!ctx.response().closed()){
                    ctx.response().setStatusCode(500).end();
                    ctx.response().close();
                }
            }
        }
    }
}
