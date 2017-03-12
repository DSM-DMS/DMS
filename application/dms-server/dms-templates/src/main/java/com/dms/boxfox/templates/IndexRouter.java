package com.dms.boxfox.templates;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.sstore.impl.SessionImpl;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dms.parser.dataio.meal.MealModel;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/", method = {HttpMethod.GET})
public class IndexRouter implements Handler<RoutingContext> {
    private UserManager userManager;
    private DataBase db;

    public IndexRouter() {
        this.db = DataBase.getInstance();
        this.userManager = new UserManager();
    }

    public void handle(RoutingContext context) {
        Calendar calendar = Calendar.getInstance();
        JSONArray meal = (JSONArray) MealModel.getMealAtDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)).toJSONObject().get("Meals");
        DmsTemplate templates = new DmsTemplate("index");
        templates.put("Rules", getPosts("rule"));
        templates.put("Faqs", getPosts("faq"));
        templates.put("Notices", getPosts("notice"));
        templates.put("Breakfast", clearMenus(meal, 0));
        templates.put("Lunch", clearMenus(meal, 1));
        templates.put("Dinner", clearMenus(meal, 2));
        templates.put("Notification", createNotification());
        templates.put("isLogin", userManager.isLogined(context));
        try {
            context.response().setStatusCode(200);
            context.response().end(templates.process());
            context.response().close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private String clearMenus(JSONArray meal, int index) {
        JSONArray arr = ((JSONArray) ((JSONObject) meal.get(index)).get("Menu"));
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arr.size(); i++) {
            builder.append(arr.get(i));
            if (i != arr.size() - 1)
                builder.append(", ");
        }
        return builder.toString();
    }

    private List<HashMap<String, Object>> getPosts(String category) {
        List<HashMap<String, Object>> map = null;
        try {
            SafeResultSet rs = DataBase.getInstance().executeQuery("select * from ", category, " order by no asc limit 5");
            map = rs.toHashMap();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    private List<Notification> createNotification() {
        List<Notification> list = new ArrayList<Notification>();

        return list;
    }

    private class Notification {
        private String text, styleClass;

        Notification(String text, String styleClass) {
            this.text = text;
            this.styleClass = styleClass;
        }

        public String getStyleClass() {
            return styleClass;
        }

        public void setStyleClass(String styleClass) {
            this.styleClass = styleClass;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
