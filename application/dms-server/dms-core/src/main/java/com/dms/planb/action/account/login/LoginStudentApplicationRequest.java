//package com.dms.planb.action.account.login;
//
//import io.vertx.core.Handler;
//import io.vertx.core.http.HttpMethod;
//import io.vertx.ext.web.RoutingContext;
//import org.boxfox.dms.util.Guardian;
//import org.boxfox.dms.util.UserManager;
//import org.boxfox.dms.utilities.actions.RouteRegistration;
//import org.boxfox.dms.utilities.actions.support.JobResult;
//import org.boxfox.dms.utilities.json.EasyJsonObject;
//import org.boxfox.dms.utilities.log.Log;
//
//import java.sql.SQLException;
//import java.util.Map;
//
//@RouteRegistration(path = "/app/account/login/student", method = {HttpMethod.POST})
//public class LoginStudentApplicationRequest implements Handler<RoutingContext> {
//    private UserManager userManager;
//
//    public LoginStudentApplicationRequest() {
//        userManager = new UserManager();
//    }
//
//    @Override
//    public void handle(RoutingContext ctx) {
//
//        EasyJsonObject responseObject = new EasyJsonObject();
//
//        String id = ctx.request().getParam("id");
//        String password = ctx.request().getParam("password");
//        String remember = ctx.request().getParam("remember");
//        remember = (remember == null) ? "false" : "true";
//
//        if(!Guardian.checkParameters(id, password, remember)) {
//        	ctx.response().setStatusCode(400).end();
//        	ctx.response().close();
//        	return;
//        }
//
//        Log.l("Login Request (", id, ", ", ctx.request().remoteAddress(), ") status : " + ctx.response().getStatusCode());
//
//        try {
//        	boolean check = userManager.login(id, password);
//            if (check) {
//            	userManager.registerSession(ctx, Boolean.valueOf(remember), id);
//
//                ctx.response().setStatusCode(201);
//                ctx.response().end(responseObject.toString());
//                ctx.response().close();
//            } else {
//            	ctx.response().setStatusCode(400);
//                ctx.response().end(responseObject.toString());
//                ctx.response().close();
//            }
//        } catch (SQLException e) {
//        	ctx.response().setStatusCode(500).end();
//        	ctx.response().close();
//
//        	Log.l("SQLException");
//        }
//    }
//
//    public EasyJsonObject getUserData(JobResult result, EasyJsonObject responseObject) throws SQLException {
//        Map<String, Object> datas = (Map) result.getArgs()[0];
//        responseObject.put("number", datas.get("number"));
//        responseObject.put("name", datas.get("name"));
//        responseObject.put("merit", datas.get("merit"));
//        responseObject.put("demerit", datas.get("demerit"));
//
//        return responseObject;
//    }
//}
