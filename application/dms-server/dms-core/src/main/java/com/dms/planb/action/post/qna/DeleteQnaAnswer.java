package com.dms.planb.action.post.qna;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import org.boxfox.dms.utilities.actions.support.PrecedingWork;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/post/qna/answer", method = {HttpMethod.DELETE})
public class DeleteQnaAnswer implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext context) {
        if (!Guardian.isAdmin(context)) return;
        context = PrecedingWork.putHeaders(context);

        DataBase database = DataBase.getInstance();

        int no = Integer.parseInt(context.request().getParam("no"));

        if (!Guardian.checkParameters(no)) {
            context.response().setStatusCode(400).end();
            context.response().close();
            return;
        }

        try {
            database.executeUpdate("UPDATE qna SET answer_content=NULL, answer_date=NULL WHERE no=", no);

            context.response().setStatusCode(200).end();
            context.response().close();
        } catch (SQLException e) {
            context.response().setStatusCode(500).end();
            context.response().close();

            Log.l("SQLException");
        }
    }
}
