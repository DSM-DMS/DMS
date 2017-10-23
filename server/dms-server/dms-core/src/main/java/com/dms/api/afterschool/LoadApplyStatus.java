package com.dms.api.afterschool;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;

import com.dms.account_manager.Guardian;
import com.dms.account_manager.UserManager;
import com.dms.utilities.database.DB;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/afterschool", method = { HttpMethod.GET })
public class LoadApplyStatus implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		String id = UserManager.getIdFromSession(ctx);
		String uid = null;

		try {
			if (id != null) {
				uid = UserManager.getUid(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (!Guardian.checkParameters(id, uid)) {
			ctx.response().setStatusCode(400).end();
			ctx.response().close();
			return;
		}

		try {
			ResultSet rs = DB.executeQuery("SELECT no FROM afterschool_apply WHERE uid=?", uid);
			JSONArray response = new JSONArray();

			while (rs.next()) {
				response.put(rs.getInt("no"));
			}

			if (response.length() != 0) {
				ctx.response().setStatusCode(200);
				ctx.response().end(response.toString());
				ctx.response().close();
			} else {
				ctx.response().setStatusCode(204).end();
				ctx.response().close();
			}
		} catch (SQLException e) {
			Log.l("SQLException");
		}
	}
}
