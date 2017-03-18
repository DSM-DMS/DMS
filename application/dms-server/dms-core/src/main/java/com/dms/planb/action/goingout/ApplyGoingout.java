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
                Log.l("SQLException");
            }
        } else {
            context.response().setStatusCode(400).end();
            context.response().close();
        }
    }
}
