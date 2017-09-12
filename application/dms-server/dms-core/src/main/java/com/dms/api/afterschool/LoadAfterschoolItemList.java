package com.dms.api.afterschool;

import java.sql.SQLException;

import com.dms.account_manager.UserManager;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.SafeResultSet;
import com.dms.utilities.json.EasyJsonArray;
import com.dms.utilities.json.EasyJsonObject;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path="/apply/afterschool/item", method={HttpMethod.GET})
public class LoadAfterschoolItemList implements Handler<RoutingContext> {
	public LoadAfterschoolItemList() {
		
	}
	
	@Override
	public void handle(RoutingContext ctx) {

		DataBase database = DataBase.getInstance();
		SafeResultSet resultSet;
		EasyJsonObject responseObject = new EasyJsonObject();
		EasyJsonObject tempObject = new EasyJsonObject();
		EasyJsonArray tempArray = new EasyJsonArray();
		
		try {
			resultSet = database.executeQuery("SELECT * FROM afterschool_list");
			
			if(resultSet.next()) {
				do {
					tempObject = new EasyJsonObject();
					
					tempObject.put("no", resultSet.getInt("no"));
					tempObject.put("title", resultSet.getString("title"));
					tempObject.put("target", resultSet.getInt("target"));
					tempObject.put("place", resultSet.getString("place"));
					tempObject.put("on_monday", resultSet.getBoolean("on_monday"));
					tempObject.put("on_tuesday", resultSet.getBoolean("on_tuesday"));
					tempObject.put("on_wednesday", resultSet.getBoolean("on_wednesday"));
					tempObject.put("on_saturday", resultSet.getBoolean("on_saturday"));
					tempObject.put("instructor", resultSet.getString("instructor"));
					tempObject.put("personnel", resultSet.getInt("personnel"));

					tempArray.add(tempObject);
				} while(resultSet.next());
				
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