package com.dms.api.afterschool;

import java.sql.SQLException;

import com.dms.account_manager.Guardian;
import com.dms.account_manager.UserManager;
import com.dms.utilities.database.DB;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/afterschool", method = { HttpMethod.DELETE })
public class WithdrawApply implements Handler<RoutingContext> {
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

		int no = Integer.parseInt(ctx.request().getFormAttribute("no"));

		if (!Guardian.checkParameters(id, uid, no)) {
			ctx.response().setStatusCode(400).end();
			ctx.response().close();
			
			return;
		}

		DB.executeUpdate("DELETE FROM afterschool_apply WHERE uid=? AND no=?", uid, no);

		ctx.response().setStatusCode(200).end();
		ctx.response().close();
	}
}
