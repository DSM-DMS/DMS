package com.dms.planb.action.account;

import java.sql.SQLException;
import java.util.Map;

import org.boxfox.dms.secure.Guardian;
import org.boxfox.dms.user.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.JobResult;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/account/login", method={HttpMethod.POST})
public class LoginStudentRequest implements Handler<RoutingContext> {
	private UserManager userManager;
	EasyJsonObject responseObject = new EasyJsonObject();

    public LoginStudentRequest() {
        userManager = new UserManager();
    }
    
	@Override
	public void handle(RoutingContext context) {
		String id = context.request().getParam("id");
		String password = context.request().getParam("password");
		
		try {
			if (Guardian.checkParameters(id, password)) {
	            if (!userManager.login(id, password)) {
	                responseObject.put("permit", false);
	                
	                context.response().setStatusCode(404).end();
	                context.response().close();
	            } else {
	            	responseObject.put("permit", true);
	                JobResult result = userManager.getUserInfo(id);
	                if (result.isSuccess()) {
	                    Map<String, Object> datas = (Map) result.getArgs()[0];
	                    responseObject.put("number", datas.get("number"));
	                    responseObject.put("name", datas.get("name"));
	                    responseObject.put("merit", datas.get("merit"));
	                    responseObject.put("demerit", datas.get("demerit"));
	                } else {
	                	
	                }
	                
	                context.response().setStatusCode(201);
	                context.response().end(responseObject.toString());
	                context.response().close();
	            }
	        } else {
	            //파라미터중 null이 존재
	        }
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
