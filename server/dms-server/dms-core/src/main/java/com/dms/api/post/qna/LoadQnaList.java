package com.dms.api.post.qna;

import java.sql.SQLException;

import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.SafeResultSet;
import com.dms.utilities.json.EasyJsonArray;
import com.dms.utilities.json.EasyJsonObject;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path="/post/qna/list", method={HttpMethod.GET})
public class LoadQnaList implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {

		DataBase database = DataBase.getInstance();
		SafeResultSet resultSet;
		EasyJsonObject responseObject = new EasyJsonObject();
		EasyJsonObject tempObject = new EasyJsonObject();
		EasyJsonArray tempArray = new EasyJsonArray();
		
		try {
			if(!context.request().params().contains("page") && !context.request().params().contains("limit")) {
				resultSet = database.executeQuery("SELECT * FROM qna");
			} else {
				int page = Integer.parseInt(context.request().getParam("page"));
				int limit = Integer.parseInt(context.request().getParam("limit"));
				
				resultSet = database.executeQuery("SELECT * FROM qna limit ", ((page - 1) * limit), ", ", limit);
			}
			
			int postCount = 0;
			if(resultSet.next()) {
				do {
					tempObject = new EasyJsonObject();
					
					tempObject.put("no", resultSet.getInt("no"));
					tempObject.put("title", resultSet.getString("title"));
					tempObject.put("question_date", resultSet.getString("question_date"));
					tempObject.put("privacy", resultSet.getBoolean("privacy"));
					tempObject.put("writer", resultSet.getString("writer"));
					
					tempArray.add(tempObject);
					
					postCount++;
				} while(resultSet.next());
				
				responseObject.put("num_of_post", postCount);
				responseObject.put("result", tempArray);
				
				context.response().setStatusCode(200);
				context.response().end(responseObject.toString());
				context.response().close();
			} else {
				context.response().setStatusCode(204).end();
				context.response().close();
			}
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
