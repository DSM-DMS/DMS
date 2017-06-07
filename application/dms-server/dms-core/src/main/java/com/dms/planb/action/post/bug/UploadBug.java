package com.dms.planb.action.post.bug;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/post/bug", method = {HttpMethod.POST})
public class UploadBug implements Handler<RoutingContext> {
    public void handle(RoutingContext ctx) {

        DataBase database = DataBase.getInstance();

        String title = ctx.request().getParam("title");
        String content = ctx.request().getParam("content");

        if (!Guardian.checkParameters(title, content)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
            return;
        }

        try {
            database.executeUpdate("INSERT INTO bug_report(title, content) VALUES('", title, "', '", content, "')");

            ctx.response().setStatusCode(201).end();
            ctx.response().close();
        } catch (SQLException e) {
            ctx.response().setStatusCode(500).end();
            ctx.response().close();

            Log.l("SQLException");
        }
    }
}
