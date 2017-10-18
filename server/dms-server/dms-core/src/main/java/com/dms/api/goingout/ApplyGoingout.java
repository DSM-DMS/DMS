package com.dms.api.goingout;

import java.sql.SQLException;

import com.dms.account_manager.Guardian;
import com.dms.account_manager.UserManager;
import com.dms.utilities.database.DB;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/apply/goingout", method = {HttpMethod.PUT})
public class ApplyGoingout implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext ctx) {
        boolean sat = Boolean.parseBoolean(ctx.request().getFormAttribute("sat"));
        boolean sun = Boolean.parseBoolean(ctx.request().getFormAttribute("sun"));

        if (Guardian.checkParameters(sat, sun) && UserManager.isLogined(ctx)) {
            try {
                String uid = UserManager.getUid(UserManager.getIdFromSession(ctx));
                DB.executeUpdate("DELETE FROM goingout_apply WHERE uid=?", uid);
                DB.executeUpdate("INSERT INTO goingout_apply(uid, sat, sun) VALUES(?, ?, ?)", uid, sat, sun);
                
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
