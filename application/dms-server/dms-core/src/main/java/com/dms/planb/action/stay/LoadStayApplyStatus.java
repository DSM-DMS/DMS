package com.dms.planb.action.stay;

import java.sql.SQLException;

import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.PrecedingWork;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/apply/stay", method={HttpMethod.GET})
public class LoadStayApplyStatus implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		DataBase database = DataBase.getInstance();
		SafeResultSet resultSet;
		EasyJsonObject responseObject = new EasyJsonObject();
		
		String id = context.request().getParam("id");
		String uid = null;
		try {
			uid = UserManager.getUid(id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String week = context.request().getParam("week");
		
		try {
			resultSet = database.executeQuery("SELECT * FROM stay_apply WHERE uid='", uid, "' AND week='", week, "'");
			
			if(resultSet.next()) {
				responseObject.put("value", resultSet.getInt("value"));
				
				context.response().setStatusCode(200);
				context.response().end(responseObject.toString());
				context.response().close();
			} else {
				context.response().setStatusCode(404).end();
				context.response().close();
			}
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
