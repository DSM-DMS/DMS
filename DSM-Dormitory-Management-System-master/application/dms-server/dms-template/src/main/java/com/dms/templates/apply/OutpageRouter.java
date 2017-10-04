package com.dms.templates.apply;

import com.dms.account_manager.UserManager;
import com.dms.templates.DmsTemplate;
import com.dms.utilities.routing.Route;
import com.dms.utilities.support.JobResult;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;

@Route(path = "/out/", method = {HttpMethod.GET})
public class OutpageRouter implements Handler<RoutingContext> {
    private UserManager userManager;

    public OutpageRouter() {
        this.userManager = new UserManager();
    }

    public void handle(RoutingContext context) {
        boolean isLogin = userManager.isLogined(context);
        if (isLogin) {
            try {
                DmsTemplate templates = new DmsTemplate("go_out_apply");
                boolean[] status = userManager.getOutStatus(userManager.getIdFromSession(context));
                templates.put("status_1", !status[0]);
                templates.put("status_2", !status[1]);
                context.response().setStatusCode(200);
                context.response().end(templates.process());
                context.response().close();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (TemplateException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            context.response().setStatusCode(200);
            context.response().putHeader("Content-type", "text/html; utf-8");
            context.response().end("<script>window.location.href='/'</script>");
            context.response().close();
        }
    }

    private String currentWeek() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        return year + "-" + month + "-" + week;
    }
}
