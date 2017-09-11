package com.dms.api.goingout;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;


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
    public void handle(RoutingContext ctx) {
        DataBase database = DataBase.getInstance();

        String sat = ctx.request().getParam("sat");
        String sun = ctx.request().getParam("sun");

        if (Guardian.checkParameters(sat, sun) && userManager.isLogined(ctx)) {
            try {
                String uid = userManager.getUid(userManager.getIdFromSession(ctx));
                database.executeUpdate("REPLACE INTO goingout_apply(uid, sat, sun) VALUES('", uid, "', ", sat, ", ", sun, ")");
                ctx.response().setStatusCode(200).end();
                ctx.response().close();
            } catch (SQLException e) {
            	e.printStackTrace();
                ctx.response().setStatusCode(500).end();
                ctx.response().close();
                Log.l("SQLException");
            }
        } else {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
        }
    }
}
