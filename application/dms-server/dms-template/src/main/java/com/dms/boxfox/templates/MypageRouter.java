package com.dms.boxfox.templates;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.JobResult;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;

@RouteRegistration(path = "/mypage/", method = {HttpMethod.GET})
public class MypageRouter implements Handler<RoutingContext> {
    private UserManager userManager;

    public MypageRouter() {
        this.userManager = new UserManager();
    }

    public void handle(RoutingContext context) {
        boolean isLogin = userManager.isLogined(context);
        if (isLogin) {
            try {
                JobResult result = userManager.getUserInfo(userManager.getIdFromSession(context));
                if (result.isSuccess()) {
                    HashMap<String, Object> infos = (HashMap<String, Object>) result.getArgs()[0];
                    DmsTemplate templates = new DmsTemplate("mypage");
                    templates.put("name", infos.get("name"));
                    templates.put("number", infos.get("number"));
                    templates.put("merit", infos.get("merit"));
                    templates.put("demerit", infos.get("demerit"));
                    templates.put("room", infos.get("room"));
                    templates.put("seat", infos.get("seat"));
                    templates.put("stay_status", userManager.getStayStatus(userManager.getIdFromSession(context), currentWeek()));
                    templates.put("profile", userManager.getIdFromSession(context));

                    context.response().setStatusCode(200);
                    context.response().end(templates.process());
                    context.response().close();
                }else{
                    context.response().setStatusCode(500);
                    context.response().close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TemplateException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            context.response().setStatusCode(200);
            context.response().putHeader("Content-type", "twext/html; utf-8");
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
