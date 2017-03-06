package com.dms.planb.action.account;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.ProfileImage;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/account/mypage/student", method={HttpMethod.GET})
public class LoadMypage implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		DataBase database = DataBase.getInstance();
		SafeResultSet resultSet;
		EasyJsonObject responseObject = new EasyJsonObject();
		
		String uid = context.request().getParam("uid");
		
		try {
			responseObject.put("profile_image", ProfileImage.getProfileImage(uid));
			
			resultSet = database.executeQuery("SELECT * FROM student_data WHERE id='", uid, "'");
			
			if(resultSet.next()) {
				responseObject.put("number", resultSet.getInt("number"));
				responseObject.put("name", resultSet.getString("name"));
			} else {
				 context.response().setStatusCode(404).end();
				 context.response().close();
				 return;
			}
			
			resultSet = database.executeQuery("SELECT * FROM student_score WHERE uid='", uid, "'");
			
			if(resultSet.next()) {
				responseObject.put("merit", resultSet.getInt("merit"));
				responseObject.put("demerit", resultSet.getInt("demerit"));
			} else {
				context.response().setStatusCode(404).end();
				context.response().close();
				return;
			}			
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
