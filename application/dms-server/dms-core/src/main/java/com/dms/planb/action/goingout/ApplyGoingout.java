package com.dms.planb.action.goingout;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import org.boxfox.dms.utilities.actions.support.PrecedingWork;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/apply/goingout", method = {HttpMethod.PUT})
public class ApplyGoingout implements Handler<RoutingContext> {
    private UserManager userManager;

    public ApplyGoingout() {
        userManager = new UserManager();
    }

    @Override
    public void handle(RoutingContext context) {
        context = PrecedingWork.putHeaders(context);
        
        DataBase database = DataBase.getInstance();
        
        String sat = context.request().getParam("sat");
        String sun = context.request().getParam("sun");
        
        if (Guardian.checkParameters(sat, sun) && userManager.isLogined(context)) {
            try {
                String uid = userManager.getUid(userManager.getIdFromSession(context));
                
                database.executeUpdate("DELETE FROM goingout_apply WHERE uid='", uid, "'");
                database.executeUpdate("INSERT INTO goingout_apply(uid, sat, sun) VALUES('", uid, "', ", sat, ", ", sun, ")");
                
                context.response().setStatusCode(200).end();
                context.response().close();
            } catch (SQLException e) {
            	e.printStackTrace();
                context.response().setStatusCode(500).end();
                context.response().close();
                Log.l("SQLException");
            }
        } else {
            context.response().setStatusCode(400).end();
            context.response().close();
        }
    }
}
