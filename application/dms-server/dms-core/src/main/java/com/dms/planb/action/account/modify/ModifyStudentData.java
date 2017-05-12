package com.dms.planb.action.account.modify;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;


import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/account/student", method={HttpMethod.PATCH})
public class ModifyStudentData implements Handler<RoutingContext> {
	private UserManager userManager;

	public ModifyStudentData(){
		userManager = new UserManager();
	}

	@Override
	public void handle(RoutingContext context) {

		DataBase database = DataBase.getInstance();
		
		String id = userManager.getIdFromSession(context);
        String uid = null;
        try {
            if (id != null) {
                uid = userManager.getUid(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		int number = Integer.parseInt(context.request().getParam("number"));
		String name = context.request().getParam("name");
		
		if(!Guardian.checkParameters(id, uid, number, name)) {
        	context.response().setStatusCode(400).end();
        	context.response().close();
        	return;
        }
		
		try {
			database.executeUpdate("UPDATE student_data SET number=", number, " WHERE uid='", uid, "'");
			database.executeUpdate("UPDATE student_data SET name='", name, "' WHERE uid='", uid, "'");
			
			context.response().setStatusCode(200).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
