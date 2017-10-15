package com.dms.api.afterschool;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dms.account_manager.Guardian;
import com.dms.utilities.database.DB;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/afterschool/item/array", method = { HttpMethod.POST })
public class UploadItemArray implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		String data = ctx.request().getFormAttribute("data");
		JSONArray afterschoolArray = new JSONArray(data);

		if (!Guardian.checkParameters(data)) {
			ctx.response().setStatusCode(400).end();
			ctx.response().close();
			return;
		}
		
		for(int i = 0; i< afterschoolArray.length(); i++) {
			JSONObject afterschool = afterschoolArray.getJSONObject(i);
			
			DB.executeUpdate("INSERT INTO afterschool_items(title, target, on_monday, on_tuesday, on_saturday) VALUES(?, ?, ?, ?, ?)", afterschool.getString("title"), afterschool.getInt("target"), afterschool.getBoolean("on_monday"), afterschool.getBoolean("on_tuesday"), afterschool.getBoolean("on_saturday"));
		}

		ctx.response().setStatusCode(201).end();
		ctx.response().close();
	}
}
