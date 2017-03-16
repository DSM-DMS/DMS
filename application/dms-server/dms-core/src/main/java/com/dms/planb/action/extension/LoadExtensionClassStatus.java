package com.dms.planb.action.extension;

import java.sql.SQLException;
import java.util.HashMap;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
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

import com.dms.planb.support.PrecedingWork;

@RouteRegistration(path = "/apply/extension/class", method = { HttpMethod.GET })
public class LoadExtensionClassStatus implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		EasyJson json = null;

		String option = context.request().getParam("option");
		
		if(!Guardian.checkParameters(option)) {
            context.response().setStatusCode(400).end();
            context.response().close();
        	return;
        }

		try {
			int classId = -1;
			
			switch (option) {
			case "map":
				if (context.request().params().contains("class")) {
					classId = Integer.parseInt(context.request().getParam("class"));
					json = new EasyJsonObject(getMapdata(classId));
				} else {
					json = new EasyJsonArray(getMapdataAll());
				}
				
				break;
			case "status":
				classId = Integer.parseInt(context.request().getParam("class"));
				break;
			}
			context.response().setStatusCode(200);
			context.response().end(json.toString());
			context.response().close();
		} catch (SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			e.printStackTrace();

			Log.l("SQLException");
		}
	}

	private HashMap<Integer, String> getSeatDatas(int classId) throws SQLException {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		SafeResultSet resultSet = DataBase.getInstance().executeQuery("SELECT * FROM extension_apply WHERE class=", classId);

		while (resultSet.next()) {
			System.out.println(resultSet.getString("name"));
			map.put(resultSet.getInt("seat"), UserManager.getAES().decrypt(resultSet.getString("name")));
		}
		
		return map;
	}

	public JSONObject getMapdata(int room) throws SQLException {
		SafeResultSet rs = DataBase.getInstance().executeQuery("select * from extension_map where room=", room);
		JSONObject object = null;
		if (rs.next()) {
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
