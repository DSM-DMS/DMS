package com.dms.planb.template_routers.recruit;

import java.sql.SQLException;

import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by boxfox on 2017-05-29.
 */
@RouteRegistration(path = "/recruit/apply", method = {HttpMethod.GET})
public class RecruitApplyRouter implements Handler<RoutingContext> {
    private UserManager userManager;
    private RecruitManager recruitManager;

    public RecruitApplyRouter() {
        userManager = new UserManager();
        recruitManager = new RecruitManager(userManager);
    }

    public void handle(RoutingContext context) {
        int code = 400;
        if (userManager.isLogined(context) && recruitManager.canApply(context) && !recruitManager.isApply(context)) {
            String language = context.request().getParam("language");
            String project = context.request().getParam("project");
            String content = context.request().getParam("content");
            String area = context.request().getParam("area");
            try {
                String uid = userManager.getUid(userManager.getIdFromSession(context));
                DataBase.getInstance().executeUpdate("insert into recruit values('", uid, "', '", language, "', '", project, "', '", content, "', '", area, "')");
                code = 200;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        context.response().setStatusCode(code).end("<script>window.location.href=document.referrer;</script>");
        context.response().close();
    }
}
