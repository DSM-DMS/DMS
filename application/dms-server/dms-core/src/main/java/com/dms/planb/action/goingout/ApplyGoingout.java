package com.dms.planb.action.goingout;

import java.sql.SQLException;

<<<<<<< HEAD
import io.netty.handler.codec.http.QueryStringDecoder;
import org.boxfox.dms.util.Guardian;
=======
>>>>>>> 528d7477707b748431919b248173d1cae1c96cd8
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.QueryUtils;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.PrecedingWork;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/apply/goingout", method = {HttpMethod.PUT})
public class ApplyGoingout implements Handler<RoutingContext> {
<<<<<<< HEAD
    private UserManager userManager;

    public ApplyGoingout() {
        userManager = new UserManager();
    }

    @Override
    public void handle(RoutingContext context) {
        context = PrecedingWork.putHeaders(context);


        String sat = context.request().getParam("sat");
        String sun = context.request().getParam("sun");
        if (Guardian.checkParameters(sat, sun) && userManager.isLogined(context)) {
            try {
                String uid = userManager.getUid(userManager.getIdFromSession(context));
                boolean satBool = Boolean.valueOf(sat);
                boolean sunBool = Boolean.valueOf(sun);
                DataBase.getInstance().executeUpdate("update goingout_apply set sat=", satBool, ", sun=", sunBool, " where uid='", uid, "'");
                context.response().setStatusCode(201).end();
                context.response().close();
            } catch (SQLException e) {
                context.response().setStatusCode(500).end();
                context.response().close();
                e.printStackTrace();
                Log.l("SQLException");
            }
        }else {

        }
    }
=======
	UserManager userManager;
	
	public ApplyGoingout() {
		userManager = new UserManager();
	}
	
	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		DataBase database = DataBase.getInstance();
		
		String id = userManager.getIdFromSession(context);
        String uid = null;
        try {
            if (id != null)
                uid = userManager.getUid(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		boolean date = Boolean.parseBoolean(context.request().getParam("date"));
		
		try {
			database.executeUpdate("DELETE FROM goingout_apply WHERE uid='", uid, "' AND date=", date);
			database.executeUpdate("INSERT INTO goingout_apply(uid, date) VALUES('", uid, "', ", date, ")");
		
			context.response().setStatusCode(201).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
>>>>>>> 528d7477707b748431919b248173d1cae1c96cd8
}
