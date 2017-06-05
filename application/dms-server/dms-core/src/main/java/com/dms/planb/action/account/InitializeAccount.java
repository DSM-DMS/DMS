package com.dms.planb.action.account;

import java.sql.SQLException;

import org.boxfox.dms.algorithm.SHA256;
import org.boxfox.dms.util.AdminManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/account/initialize", method = { HttpMethod.POST })
public class InitializeAccount implements Handler<RoutingContext> {

	@Override
	public void handle(RoutingContext ctx) {
		DataBase database = DataBase.getInstance();

		if (!AdminManager.isAdmin(ctx)) {
			ctx.response().setStatusCode(400).end();
			ctx.response().close();
			return;
		}

		String number = ctx.request().getParam("number");
		String encryptedNumber = SHA256.encrypt(number);

		try {
			SafeResultSet studentData = database.executeQuery("SELECT uid FROM student_data WHERE number='", encryptedNumber, "'");
			if(studentData.next()) {
				String uid = studentData.getString("uid");
				database.executeUpdate("UPDATE account SET id=null, password=null, session_key=null WHERE uid='", uid, "'");
				
				ctx.response().setStatusCode(200).end();
				ctx.response().close();
			} else {
				ctx.response().setStatusCode(204).end();
				ctx.response().close();
			}
		} catch (SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();

			Log.l("SQLException");
		}
	}
}
