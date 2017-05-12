package com.dms.planb.action.account;

import java.sql.SQLException;

import org.boxfox.dms.algorithm.SHA256;
import org.boxfox.dms.util.AdminManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.PrecedingWork;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/account/initialize", method = { HttpMethod.POST })
public class InitializeAccount implements Handler<RoutingContext> {

	@Override
	public void handle(RoutingContext context) {
		DataBase database = DataBase.getInstance();

		context = PrecedingWork.putHeaders(context);

		if (!AdminManager.isAdmin(context)) {
			context.response().setStatusCode(400).end();
			context.response().close();
			return;
		}

		String number = context.request().getParam("number");
		String encryptedNumber = SHA256.encrypt(number);

		try {
			SafeResultSet studentData = database.executeQuery("SELECT uid FROM student_data WHERE number='", encryptedNumber, "'");
			if(studentData.next()) {
				String uid = studentData.getString("uid");
				database.executeUpdate("UPDATE account SET id=null, password=null, session_key=null WHERE uid='", uid, "'");
				
				context.response().setStatusCode(200).end();
				context.response().close();
			} else {
				context.response().setStatusCode(204).end();
				context.response().close();
			}
		} catch (SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();

			Log.l("SQLException");
		}
	}
}
