package com.dms.api.extension;

import java.sql.SQLException;

import com.dms.account_manager.Guardian;
import com.dms.account_manager.UserManager;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.SafeResultSet;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;
import com.dms.utilities.support.ApplyDataUtil;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/apply/extension", method = {HttpMethod.PUT})
public class ApplyExtension implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext ctx) {
        DataBase database = DataBase.getInstance();

        String id = UserManager.getIdFromSession(ctx);
        String uid = null;
        
        try {
            if (id != null) {
                uid = UserManager.getUid(id);
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
