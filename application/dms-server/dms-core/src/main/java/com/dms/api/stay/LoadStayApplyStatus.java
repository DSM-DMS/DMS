package com.dms.api.stay;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;


import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/apply/stay", method = {HttpMethod.GET})
public class LoadStayApplyStatus implements Handler<RoutingContext> {
    private UserManager userManager;

    public LoadStayApplyStatus() {
        this.userManager = new UserManager();
    }

    @Override
    public void handle(RoutingContext ctx) {

        DataBase database = DataBase.getInstance();
        SafeResultSet resultSet;
        EasyJsonObject responseObject = new EasyJsonObject();

        String id = userManager.getIdFromSession(ctx);
        String uid = null;
        try {
            if (id != null) {
                uid = userManager.getUid(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if(!Guardian.checkParameters(id, uid)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
        	return;
        }

        try {
        	resultSet = database.executeQuery("SELECT * FROM stay_apply WHERE uid='", uid, "'");

        	if(resultSet.next()) {
        		responseObject.put("value", resultSet.getInt("value"));
        		
        		ctx.response().setStatusCode(200);
        		ctx.response().end(responseObject.toString());
        		ctx.response().close();
        	} else {
        		ctx.response().setStatusCode(204).end();
        		ctx.response().close();
        	}
        } catch (SQLException e) {
            ctx.response().setStatusCode(500).end();
            ctx.response().close();

            Log.l("SQLException");
        }
    }
}
