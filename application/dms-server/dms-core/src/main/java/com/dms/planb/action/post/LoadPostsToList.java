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
	private static final String []CATEGORY_LIST = {"faq", "rule", "notice"};

	@Override
	public void handle(RoutingContext ctx) {

		DataBase database = DataBase.getInstance();
		int statusCode = 400;

		String category = ctx.request().getParam("category");
		if(Guardian.matchParameters(category, CATEGORY_LIST))
		try {
			SafeResultSet resultSet;
			EasyJsonObject responseObject = new EasyJsonObject();
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

				ctx.response().write(responseObject.toString());
				statusCode = 200;
			} else {
				statusCode = 204;
			}
		} catch(SQLException e) {
			statusCode = 500;
			e.printStackTrace();
		}
		ctx.response().setStatusCode(statusCode);
		ctx.response().close();
	}
}
