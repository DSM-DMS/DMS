package com.dms.boxfox.templates;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import org.boxfox.dms.util.UserManager;
import org.json.JSONObject;

import com.dms.utilities.routing.RouteRegistration;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/mypage/", method = {HttpMethod.GET})
public class MypageRouter implements Handler<RoutingContext> {
    public void handle(RoutingContext context) {
        boolean isLogin = UserManager.isLogined(context);
        if (isLogin) {
            try {
                JSONObject result = UserManager.getUserInfo(UserManager.getIdFromSession(context));
                if (result.length() != 0) {
                    DmsTemplate templates = new DmsTemplate("mypage");
                    templates.put("name", result.getString("name"));
                    templates.put("number", result.getString("number"));
                    templates.put("merit", result.getString("merit"));
                    templates.put("demerit", result.getString("demerit"));
                    templates.put("room", result.getString("room"));
                    templates.put("seat", result.getString("seat"));
                    templates.put("stay_status", UserManager.getStayStatus(UserManager.getIdFromSession(context), currentWeek()));
                    templates.put("profile", UserManager.getIdFromSession(context));

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
