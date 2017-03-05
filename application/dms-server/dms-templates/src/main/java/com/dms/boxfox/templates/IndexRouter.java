package com.dms.boxfox.templates;

import org.boxfox.dms.utilities.actions.RouteRegistration;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;

import java.io.IOException;
import java.sql.SQLException;

@RouteRegistration(path = "/", method = {HttpMethod.POST})
public class IndexRouter implements Handler<RoutingContext> {
    private DataBase db;

    public IndexRouter() {
        db = DataBase.getInstance();
    }

    public void handle(RoutingContext context) {
        DmsTemplate templates = new DmsTemplate("index");
        try {
            SafeResultSet rs = db.executeQuery("select title, no, writer from notice no desc limit 5");
            System.out.println(templates.process());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
