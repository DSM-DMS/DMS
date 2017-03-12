package com.dms.planb.action.account;

import java.sql.SQLException;
import java.util.Map;

import org.boxfox.dms.secure.VerifyRecaptcha;
import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.JobResult;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.PrecedingWork;

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
        String recapcha = context.request().getParam("recapcha"); //recapcha response 이름 수정해야함

        if (VerifyRecaptcha.verify(recapcha))
            try {
                if (context.request().params().contains("auto_login")) {
                    // Checked auto login
                    boolean autoLogin = Boolean.parseBoolean(context.request().getParam("auto_login"));

                    if (Guardian.checkParameters(id, password) && userManager.login(id, password)) {
                        if (userManager.registerSession(context, autoLogin, id)) {
                            JobResult result = userManager.getUserInfo(id);

                            responseObject = getUserData(result, responseObject);

                            context.response().setStatusCode(201);
                            context.response().end(responseObject.toString());
                            context.response().close();
                        } else {
                            // Any null in parameters
                            context.response().setStatusCode(404).end();
                            context.response().close();
                        }
                    } else {
                        // Any null in parameters, or login failed
                        context.response().setStatusCode(404).end();
                        context.response().close();
                    }
                } else if (context.request().params().contains("has_session")) {
                    // Session login
                    JobResult result = userManager.sessionLogin(context);

                    responseObject = getUserData(result, responseObject);

                    context.response().setStatusCode(201);
                    context.response().end(responseObject.toString());
                    context.response().close();
                } else {
                    // Unchecked auto login
                    if (Guardian.checkParameters(id, password)) {
                        if (userManager.login(id, password)) {
                            JobResult result = userManager.getUserInfo(id);
                            responseObject = getUserData(result, responseObject);

                            context.response().setStatusCode(201);
                            context.response().end(responseObject.toString());
                            context.response().close();
                        } else {
                            // Login failed
                            context.response().setStatusCode(404).end();
                            context.response().close();
                        }
                    } else {
                        // Any null in parameters
                        context.response().setStatusCode(404).end();
                        context.response().close();
                    }
                }
            } catch (SQLException e) {
                context.response().setStatusCode(500).end();
                context.response().close();

                Log.l("SQLException");
            }
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
