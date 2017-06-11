package com.dsm.dms.api.process;

import com.dsm.dms.api.secure.ApiRequestManager;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;

import java.sql.SQLException;

/**
 * Created by boxfox on 2017-04-05.
 */
@RouteRegistration(path = "/api/host/", method = {HttpMethod.PUT})
public class APIHostAddRouter implements Handler<RoutingContext> {
    private ApiRequestManager requestManager;
    private UserManager userManager;

    public APIHostAddRouter() {
        requestManager = new ApiRequestManager();
        userManager = new UserManager();
    }

    public void handle(RoutingContext ctx) {
        HttpServerResponse response = ctx.response();
        HttpServerRequest request = ctx.request();
        String apiKey = request.getParam("api_key");
        String host = request.getParam("host_name");
        if (Guardian.checkParameters(apiKey, host) && userManager.isLogined(ctx)) {
            try {
                String uid = userManager.getUid(userManager.getIdFromSession(ctx));
                if(requestManager.addHost(uid, apiKey, host)){
                    response.setStatusCode(200);
                }else{
                    response.setStatusCode(304);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.setStatusCode(500);
            }
        } else {
            response.setStatusCode(400);
        }
        response.end();
        response.close();
    }

}
