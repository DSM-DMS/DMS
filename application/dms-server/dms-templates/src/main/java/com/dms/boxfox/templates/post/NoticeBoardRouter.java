package com.dms.boxfox.templates.post;

import com.dms.boxfox.templates.DmsTemplate;
import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by boxfox on 2017-03-10.
 */

@RouteRegistration(path = "/post/notice", method = {HttpMethod.GET})
public class NoticeBoardRouter implements Handler<RoutingContext> {
    private DataBase db;

    public NoticeBoardRouter() {
        db = DataBase.getInstance();
    }

    public void handle(RoutingContext context) {
        int page = getPageNumber(context);

        try {
            DmsTemplate templates = new DmsTemplate("notice-board");
            SafeResultSet rs = db.executeQuery("select * from notice order by no desc limit ", page, ", ", page + 10, "");
            templates.put("lists", rs.toHashMap());
            rs.toHashMap();
            context.response().setStatusCode(200);
            context.response().end(templates.process());
            context.response().close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private int getPageNumber(RoutingContext context) {
        int page;
        if (context.request().getParam("page") == null) {
            page = 0;
        } else
            page = Integer.valueOf(context.request().getParam("page"));
        page *= 10;
        return page;
    }
}
