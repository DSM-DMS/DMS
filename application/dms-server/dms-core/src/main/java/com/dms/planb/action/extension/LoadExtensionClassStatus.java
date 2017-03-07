package com.dms.planb.action.extension;

import java.sql.SQLException;
import java.util.HashMap;

import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJson;
import org.boxfox.dms.utilities.json.EasyJsonArray;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

@RouteRegistration(path = "/apply/extension/class", method = {HttpMethod.GET})
public class LoadExtensionClassStatus implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext context) {
        EasyJsonObject responseObject = new EasyJsonObject();
        String classIdStr = context.request().getParam("class");
        try {
            EasyJson json;
            if(classIdStr==null){
                json = new EasyJsonArray(getMapdataAll());
            }else{
                json = new EasyJsonObject(getMapdata(Integer.parseInt(classIdStr)));
            }
            responseObject.put("result", json);

            context.response().setStatusCode(200);
            context.response().end(responseObject.toString());
            context.response().close();
            context.response().setStatusCode(404).end();
            context.response().close();
        } catch (SQLException e)

        {
            context.response().setStatusCode(500).end();
            context.response().close();

            Log.l("SQLException");
        }
    }


    private HashMap<Integer, String> getSeatDatas(int classId) throws SQLException {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        SafeResultSet resultSet = DataBase.getInstance().executeQuery("SELECT * FROM extension_apply WHERE class=", classId);

        while (resultSet.next()) {
            map.put(resultSet.getInt("seat"), resultSet.getString("name"));
        }
        return map;
    }

    public JSONObject getMapdata(int room) throws SQLException {
        SafeResultSet rs = DataBase.getInstance().executeQuery("select * from extension_map where room=",room);
        JSONObject object = null;
        if(rs.next()) {
            object = new JSONObject();
            object.put("no", rs.getInt("room"));
            object.put("name", rs.getString("name"));
            object.put("map", process((JSONArray) JSONValue.parse(rs.getString("map")), rs.getInt("room")));
        }
        return object;
    }

    public JSONArray getMapdataAll() throws SQLException {
        SafeResultSet rs = DataBase.getInstance().executeQuery("select * from extension_map");
        JSONArray classs = new JSONArray();
        while (rs.next()) {
            JSONObject object = new JSONObject();
            object.put("no", rs.getInt("room"));
            object.put("name", rs.getString("name"));
            object.put("map", process((JSONArray) JSONValue.parse(rs.getString("map")), rs.getInt("room")));
            classs.add(object);
        }
        return classs;
    }

    private JSONArray process(JSONArray arr, int room) throws SQLException {
        HashMap<Integer, String> map = getSeatDatas(room);
        int count = 1;
        for (int i = 0; i < arr.size(); i++) {
            JSONArray row = (JSONArray) arr.get(i);
            for (int k = 0; k < row.size(); k++) {
                if (((long) row.get(k)) == 1) {
                    row.remove(k);
                    if (map.get(count) != null) {
                        row.add(k, map.get(count));
                    } else {
                        row.add(k, count);
                    }
                    count++;
                }
            }
        }
        return arr;
    }
}
