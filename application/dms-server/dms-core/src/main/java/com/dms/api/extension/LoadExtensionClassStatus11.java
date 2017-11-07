package com.dms.api.extension;

import java.sql.SQLException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.dms.account_manager.Guardian;
import com.dms.crypto.AES256;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.SafeResultSet;
import com.dms.utilities.json.EasyJson;
import com.dms.utilities.json.EasyJsonArray;
import com.dms.utilities.json.EasyJsonObject;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/apply/extension/class/11", method = { HttpMethod.GET })
public class LoadExtensionClassStatus11 implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		EasyJson json = null;

		String option = ctx.request().getParam("option");
		
		if(!Guardian.checkParameters(option)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
        	return;
        }

		try {
			int classId = -1;
			
			switch (option) {
			case "map":
				if (ctx.request().params().contains("class")) {
					classId = Integer.parseInt(ctx.request().getParam("class"));
					json = new EasyJsonObject(getMapdata(classId));
				} else {
					json = new EasyJsonArray(getMapdataAll());
				}
				
				break;
			case "status":
				classId = Integer.parseInt(ctx.request().getParam("class"));
				break;
			}
			
			ctx.response().setStatusCode(200);
			ctx.response().end(json.toString());
			ctx.response().close();
		} catch (SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();

			Log.l("SQLException");
		}
	}

	private HashMap<Integer, String> getSeatDatas(int classId) throws SQLException {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		SafeResultSet resultSet = DataBase.getInstance().executeQuery("SELECT * FROM extension_apply WHERE class=", classId);

		while (resultSet.next()) {
			map.put(resultSet.getInt("seat"), AES256.decrypt(resultSet.getString("name")));
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
