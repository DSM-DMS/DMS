package com.dms.boxfox.templates;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

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
    private DataBase db;

    public IndexRouter() {
        db = DataBase.getInstance();
    }

    public void handle(RoutingContext context) {
        Calendar calendar = Calendar.getInstance();
        JSONArray meal = (JSONArray) MealModel.getMealAtDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH)).toJSONObject().get("Meals");
        DmsTemplate templates = new DmsTemplate("index");
        templates.put("Rules", getPosts("rule"));
        templates.put("Notices", getPosts("notice"));
        templates.put("Breakfast", (String)((JSONObject)meal.get(0)).get("Menu"));
        templates.put("Lunch", (String)((JSONObject)meal.get(1)).get("Menu"));
        templates.put("Dinner", (String)((JSONObject)meal.get(2)).get("Menu"));
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

    private List<HashMap<String, Object>> getPosts(String category) {
        List<HashMap<String, Object>> map = null;
        try {
            SafeResultSet rs = DataBase.getInstance().executeQuery("select * from ", category, " no desc limit 5");
            map = rs.toHashMap();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
}
