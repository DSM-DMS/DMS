package com.dms.planb.action.stay;

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
    public void handle(RoutingContext context) {

        DataBase database = DataBase.getInstance();
        SafeResultSet resultSet;
        EasyJsonObject responseObject = new EasyJsonObject();

        String id = userManager.getIdFromSession(context);
        String uid = null;
        try {
            if (id != null) {
                uid = userManager.getUid(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        String month = context.request().getParam("month");
        String week = context.request().getParam("week");
        
        if(!Guardian.checkParameters(id, uid, week)) {
            context.response().setStatusCode(400).end();
            context.response().close();
        	return;
        }

        try {
//            resultSet = database.executeQuery("SELECT * FROM stay_apply WHERE uid='", uid, "' AND week like'%", month, "'");
        	resultSet = database.executeQuery("SELECT * FROM stay_apply WHERE uid='", uid, "' AND week='", week, "'");

        	if(resultSet.next()) {
        		responseObject.put("value", resultSet.getInt("value"));
        		
        		context.response().setStatusCode(200);
        		context.response().end(responseObject.toString());
        		context.response().close();
        	} else {
        		context.response().setStatusCode(204).end();
        		context.response().close();
        	}
//            if (resultSet.next()) {
//            	int weekCount = 1;
//            	
//            	do {
//            		if(resultSet.toHashMap().contains("value")) {
//            			responseObject.put(month + "-0" + Integer.toString(weekCount++), resultSet.getInt("value"));
//            		} else {
//            			responseObject.put(month + "-0" + Integer.toString(weekCount++), 0);
//            		}
//            	} while(resultSet.next());
//
//                context.response().setStatusCode(200);
//                context.response().end(responseObject.toString());
//                context.response().close();
//            } else {
//                context.response().setStatusCode(204).end();
//                context.response().close();
//            }
        } catch (SQLException e) {
            context.response().setStatusCode(500).end();
            context.response().close();

            Log.l("SQLException");
        }
    }
}
