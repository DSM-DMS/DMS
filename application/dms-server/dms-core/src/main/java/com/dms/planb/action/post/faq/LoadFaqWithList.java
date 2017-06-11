package com.dms.planb.action.post.faq;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonArray;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/faq/list", method={HttpMethod.GET})
public class LoadFaqWithList implements Handler<RoutingContext> {
	public LoadFaqWithList() {
		
	}
	
	@Override
	public void handle(RoutingContext ctx) {

		DataBase database = DataBase.getInstance();
		SafeResultSet resultSet;
		EasyJsonObject responseObject = new EasyJsonObject();
		EasyJsonObject tempObject = new EasyJsonObject();
		EasyJsonArray tempArray = new EasyJsonArray();
		
		try {
			if(!ctx.request().params().contains("page") && !ctx.request().params().contains("limit")) {
				resultSet = database.executeQuery("SELECT * FROM faq");
			} else {
				int page = Integer.parseInt(ctx.request().getParam("page"));
				int limit = Integer.parseInt(ctx.request().getParam("limit"));
				resultSet = database.executeQuery("SELECT * FROM faq limit ", ((page - 1) * limit), ", ", limit);
			}
			
			int postCount = 0;
			if(resultSet.next()) {
				do {
					tempObject = new EasyJsonObject();
					
					tempObject.put("no", resultSet.getInt("no"));
					tempObject.put("title", resultSet.getString("title"));
					tempObject.put("content", resultSet.getString("content"));
					
					tempArray.add(tempObject);
					
					postCount++;
				} while(resultSet.next());
				
				responseObject.put("num_of_post", postCount);
				responseObject.put("result", tempArray);
				
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
			
			Log.l("SQLException");
		}
	}
}
