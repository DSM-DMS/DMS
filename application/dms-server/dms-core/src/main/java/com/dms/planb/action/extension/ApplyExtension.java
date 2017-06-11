package com.dms.planb.action.extension;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.ApplyDataUtil;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.log.Log;


import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/apply/extension", method = {HttpMethod.PUT})
public class ApplyExtension implements Handler<RoutingContext> {
    private UserManager userManager;

    public ApplyExtension() {
        userManager = new UserManager();
    }

    @Override
    public void handle(RoutingContext ctx) {

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
        
        String classId = ctx.request().getParam("class");
        String seatId = ctx.request().getParam("seat");
        
        if(!Guardian.checkParameters(classId, seatId, id, uid)) {
        	ctx.response().setStatusMessage("Check parameter or after than login");
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
        	return;
        }
        try {
            String name = null;
            SafeResultSet rs = database.executeQuery("select name from student_data where uid='", uid, "'");
            if (rs.next()) {
                name = rs.getString(1);
            }
            if (!ApplyDataUtil.canApplyExtension()) {
                ctx.response().setStatusCode(204).end();
                ctx.response().close();
//            } else if(database.executeQuery("SELECT FROM extension_apply WHERE class=", classId, " AND seat=", seatId).next()) {
//            	ctx.response().setStatusCode(409).end();
//            	ctx.response().close();
            } else {
                database.executeUpdate("DELETE FROM extension_apply WHERE uid='", uid, "'");
                database.executeUpdate("INSERT INTO extension_apply(class, seat, name, uid) VALUES(", classId, ", ", seatId, ", '", name, "', '", uid, "')");
                ctx.response().setStatusCode(200).end();
                ctx.response().close();
            }
        } catch (SQLException e) {
            ctx.response().setStatusCode(500).end();
            ctx.response().close();
            e.printStackTrace();
            Log.l("SQLException");
        }
        Log.l("Extension Apply (", id, ", ", ctx.request().remoteAddress(), ") status : " + ctx.response().getStatusCode());
    }
}
