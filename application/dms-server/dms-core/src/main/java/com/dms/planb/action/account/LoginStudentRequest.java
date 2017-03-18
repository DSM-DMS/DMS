package com.dms.planb.action.account;

import java.sql.SQLException;
import java.util.Map;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.JobResult;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import org.boxfox.dms.utilities.actions.support.PrecedingWork;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/account/login/student", method = {HttpMethod.POST})
public class LoginStudentRequest implements Handler<RoutingContext> {
    private UserManager userManager;

    public LoginStudentRequest() {
        userManager = new UserManager();
    }

    @Override
    public void handle(RoutingContext context) {
        context = PrecedingWork.putHeaders(context);

        EasyJsonObject responseObject = new EasyJsonObject();

        String id = context.request().getParam("id");
        String password = context.request().getParam("password");
        String remember = context.request().getParam("remember");
        String recapcha = context.request().getParam("g-recaptcha-response"); //recapcha response 이름 수정해야함
        remember = (remember == null) ? "false" : "true";
        
        if(!Guardian.checkParameters(id, password, remember)) {
        	context.response().setStatusCode(400).end();
        	context.response().close();
        	return;
        }
        try {
            boolean check = userManager.login(id, password);
            if (check) {
            	userManager.registerSession(context, Boolean.valueOf(remember), id);
            	context.response().setStatusCode(201);
                context.response().end(responseObject.toString());
                context.response().close();
            } else {
                context.response().setStatusCode(400);
                context.response().end(responseObject.toString());
                context.response().close();
            }
        } catch (SQLException e) {
            context.response().setStatusCode(500).end();
            context.response().close();

            Log.l("SQLException");
        }
        Log.l("Login Request (", id, ", ", context.request().remoteAddress(), ") status : " + context.response().getStatusCode());
    }

    public EasyJsonObject getUserData(JobResult result, EasyJsonObject responseObject) throws SQLException {
        Map<String, Object> datas = (Map) result.getArgs()[0];
        responseObject.put("number", datas.get("number"));
        responseObject.put("name", datas.get("name"));
        responseObject.put("merit", datas.get("merit"));
        responseObject.put("demerit", datas.get("demerit"));

        return responseObject;
    }
}
