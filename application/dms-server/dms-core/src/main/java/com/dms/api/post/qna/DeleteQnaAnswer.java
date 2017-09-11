package com.dms.api.post.qna;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;

import com.dms.utilities.database.DataBase;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.RouteRegistration;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/post/qna/answer", method = {HttpMethod.DELETE})
public class DeleteQnaAnswer implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext ctx) {

		if (!Guardian.isAdmin(ctx)) {
			ctx.response().setStatusCode(400).end();
			ctx.response().close();
			return;
		}

        DataBase database = DataBase.getInstance();

        int no = Integer.parseInt(ctx.request().getParam("no"));

        if (!Guardian.checkParameters(no)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
            return;
        }

        try {
            database.executeUpdate("UPDATE qna SET answer_content=NULL, answer_date=NULL WHERE no=", no);

            ctx.response().setStatusCode(200).end();
            ctx.response().close();
        } catch (SQLException e) {
            ctx.response().setStatusCode(500).end();
            ctx.response().close();

            Log.l("SQLException");
        }
    }
}
