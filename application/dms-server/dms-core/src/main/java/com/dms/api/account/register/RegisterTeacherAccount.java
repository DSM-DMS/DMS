package com.dms.api.account.register;

import java.sql.SQLException;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

import com.dms.account_manager.AdminManager;
import com.dms.account_manager.Guardian;
import com.dms.utilities.routing.Route;
import com.dms.utilities.support.JobResult;

@Route(path = "/account/register/admin", method = {HttpMethod.POST})
public class RegisterTeacherAccount implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext ctx) {
        if (!AdminManager.isAdmin(ctx)) {
            ctx.response().setStatusCode(404).end();
            ctx.response().close();
            return;
        }

        String id = ctx.request().getFormAttribute("id");
        String password = ctx.request().getFormAttribute("password");
        String passwordConfirm = ctx.request().getFormAttribute("password-confirm");
        String name = ctx.request().getFormAttribute("name");
        if (!Guardian.checkParameters(id, password, passwordConfirm, name)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
        } else {
            if (!password.equals(passwordConfirm) || AdminManager.checkIdExists(id)) {
                ctx.response().setStatusCode(204).end();
                ctx.response().close();
            }else{
                try {
                    JobResult result = AdminManager.register(id, password, name);
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
