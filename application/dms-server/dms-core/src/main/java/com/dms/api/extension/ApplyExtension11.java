package com.dms.api.extension;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dms.account_manager.Guardian;
import com.dms.account_manager.UserManager;
import com.dms.utilities.database.DB;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;
import com.dms.utilities.support.ApplyDataUtil;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/apply/extension", method = {HttpMethod.PUT})
public class ApplyExtension11 implements Handler<RoutingContext> {
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
            ResultSet rs = DB.executeQuery("select name from student_data where uid=?", uid);
            if (rs.next()) {
                name = rs.getString(1);
            }
            if (!ApplyDataUtil.canApplyExtension12()) {
                ctx.response().setStatusCode(204).end();
                ctx.response().close();
            } else {
                DB.executeUpdate("DELETE FROM extension_apply WHERE uid=?", uid);
                DB.executeUpdate("INSERT INTO extension_apply(class, seat, name, uid) VALUES(?, ?, ?, ?)", classId, seatId, name, uid);
                
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
