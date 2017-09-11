package com.dms.api.stay;

import java.sql.SQLException;
import java.util.Calendar;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;

import com.dms.utilities.database.DataBase;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.RouteRegistration;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/apply/stay", method={HttpMethod.PUT})
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
		int minute = c.get(Calendar.MINUTE);
		
		if(dayOfWeek >= 6 /* 금요일 이상 */ || dayOfWeek == 5 && hour > 20 /* 금요일이고 9시 이후 */ || dayOfWeek == 5 && hour == 20 && minute > 30 /* 금요일이고 8시 30분 이후 */) {
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
