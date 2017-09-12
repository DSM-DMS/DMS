package com.dms.api.post.report_facility;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;

import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.SafeResultSet;
import com.dms.utilities.json.EasyJsonObject;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Deprecated
@Route(path="/post/report", method={HttpMethod.GET})
public class LoadReportFacility implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {

		DataBase database = DataBase.getInstance();
		SafeResultSet resultSet;
		EasyJsonObject responseObject = new EasyJsonObject();
		
		int no = Integer.parseInt(context.request().getParam("no"));
		
		if(!Guardian.checkParameters(no)) {
            context.response().setStatusCode(400).end();
            context.response().close();
        	return;
        }
		
		try {
			resultSet = database.executeQuery("SELECT * FROM facility_report WHERE no=", no);
			
			if(resultSet.next()) {
				responseObject.put("title", resultSet.getString("title"));
				responseObject.put("content", resultSet.getString("content"));
				responseObject.put("room", resultSet.getInt("room"));
				responseObject.put("write_date", resultSet.getString("write_date"));
				responseObject.put("writer", resultSet.getString("writer"));
				if(resultSet.getString("result") != null){
					responseObject.put("has_result", true);
					responseObject.put("result", resultSet.getString("result"));
					responseObject.put("result_date", resultSet.getString("result_date"));
				} else {
					responseObject.put("has_result", false);
				}
				
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
