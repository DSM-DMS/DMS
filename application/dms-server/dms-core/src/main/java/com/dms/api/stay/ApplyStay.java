package com.dms.api.stay;

import java.sql.SQLException;
import java.util.Calendar;

import com.dms.account_manager.Guardian;
import com.dms.account_manager.UserManager;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path="/apply/stay", method={HttpMethod.PUT})
public class ApplyStay implements Handler<RoutingContext> {
	UserManager userManager;
	
	public ApplyStay() {
		userManager = new UserManager();
	}
	
	@Override
	public void handle(RoutingContext ctx) {
		Calendar c = Calendar.getInstance();
		
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		int hour = c.get(Calendar.HOUR);
		
		if(dayOfWeek >= 6 /* 금요일 이상 */ || dayOfWeek == 5 && hour > 21) {
			ctx.response().setStatusCode(204).end();
			ctx.response().close();
		}
		
		DataBase database = DataBase.getInstance();
		
		String id = userManager.getIdFromSession(ctx);
        String uid = null;
        try {
            if (id != null) {
                uid = userManager.getUid(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		int value = Integer.parseInt(ctx.request().getParam("value"));
		
		if(!Guardian.checkParameters(id, uid, value)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
        	return;
        }
		try {
			database.executeUpdate("DELETE FROM stay_apply WHERE uid='", uid, "'");
			database.executeUpdate("INSERT INTO stay_apply(uid, value) VALUES('", uid, "', ", value, ")");
			
			ctx.response().setStatusCode(200).end();
			ctx.response().close();
		} catch(SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();

			Log.l("SQLException");
		}
	}
}
