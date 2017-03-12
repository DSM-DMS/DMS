package com.dms.boxfox.templates;

import com.dms.parser.dataio.meal.MealModel;
import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@RouteRegistration(path = "/extensionapply/", method = {HttpMethod.GET})
public class ExtensionRouter implements Handler<RoutingContext> {
    private UserManager userManager;

    public ExtensionRouter() {
        this.userManager = new UserManager();
    }

    public void handle(RoutingContext context) {
        boolean isLogin = userManager.isLogined(context);
        if (isLogin) {
            DmsTemplate templates = new DmsTemplate("extensionapply");
            try {
                context.response().setStatusCode(200);
                context.response().end(templates.process());
                context.response().close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        } else {
            context.response().setStatusCode(200);
            context.response().putHeader("Content-type", "text/html; utf-8");
            context.response().end("<script>window.location.href='/'</script>");
            context.response().close();
        }
    }
}
