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
@RouteRegistration(path = "/recruit/apply", method = {HttpMethod.POST})
public class ApplyRecruit implements Handler<RoutingContext> {
    private UserManager userManager;
    private RecruitManager recruitManager;

    public ApplyRecruit() {
        userManager = new UserManager();
        recruitManager = new RecruitManager(userManager);
    }

    public void handle(RoutingContext ctx) {
        int code = 400;
        if (userManager.isLogined(ctx) && recruitManager.canApply(ctx) && !recruitManager.isApply(ctx)) {
            String language = ctx.request().getParam("language");
            String project = ctx.request().getParam("project");
            String content = ctx.request().getParam("content");
            String area = ctx.request().getParam("area");
            try {
                String uid = userManager.getUid(userManager.getIdFromSession(ctx));
                DataBase.getInstance().executeUpdate("DELETE FROM recruit WHERE uid='", uid, "'");
                DataBase.getInstance().executeUpdate("insert into recruit values('", uid, "', '", language, "', '", project, "', '", content, "', '", area, "')");
                code = 200;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ctx.response().setStatusCode(code).end("<script>window.location.href=document.referrer;</script>");
        ctx.response().close();
    }
}
