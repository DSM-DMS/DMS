package com.dms.planb.action.post;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import org.json.simple.JSONArray;

@RouteRegistration(path="/post/list/:category", method={HttpMethod.GET})
public class LoadPostsToList implements Handler<RoutingContext> {

	@Override
	public void handle(RoutingContext ctx) {

		DataBase database = DataBase.getInstance();
		SafeResultSet resultSet;
		EasyJsonObject responseObject = new EasyJsonObject();

		try {
			String category = ctx.request().getParam("category");
			if(!Guardian.checkParameters(ctx, "page", "limit")) {
				resultSet = database.executeQuery("SELECT * FROM "+category+" order by no desc");
			} else {
				int page = Integer.parseInt(ctx.request().getParam("page"));
				int limit = Integer.parseInt(ctx.request().getParam("limit"));
				resultSet = database.executeQuery("SELECT * FROM "+category+" order by no desc limit ", ((page - 1) * limit), ", ", limit);
			}
			
			if(resultSet.next()) {
				JSONArray arr = resultSet.convertToJSONArray();
				responseObject.put("num_of_post", arr.size());
				responseObject.put("result", arr);
				
				ctx.response().setStatusCode(200);
				ctx.response().end(responseObject.toString());
				ctx.response().close();
			} else {
				ctx.response().setStatusCode(204).end();
				ctx.response().close();
			}
		} catch(SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();
			e.printStackTrace();
			Log.l("SQLException");
		}
	}
}
