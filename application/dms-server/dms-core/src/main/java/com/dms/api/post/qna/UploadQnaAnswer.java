package com.dms.api.post.qna;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;

import com.dms.utilities.database.DataBase;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.RouteRegistration;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/post/qna/answer", method = {HttpMethod.PUT})
public class UploadQnaAnswer implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext ctx) {
        if (!Guardian.isAdmin(ctx)) return;

        DataBase database = DataBase.getInstance();

        int no = Integer.parseInt(ctx.request().getParam("no"));
        String content = ctx.request().getParam("content");

        if (!Guardian.checkParameters(no, content)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
            return;
        }

        try {
            database.executeUpdate("UPDATE qna SET answer_content='", content, "', answer_date=now() WHERE no=", no);

            ctx.response().setStatusCode(201).end();
            ctx.response().close();
        } catch (SQLException e) {
            ctx.response().setStatusCode(500).end();
            ctx.response().close();

            Log.l("SQLException");
        }
    }
}
