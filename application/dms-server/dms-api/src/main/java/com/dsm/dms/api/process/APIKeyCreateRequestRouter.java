package com.dsm.dms.api.process;

import com.dsm.dms.api.secure.ApiRequestManager;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;

import java.sql.SQLException;

/**
 * Created by boxfox on 2017-04-05.
 */
@RouteRegistration(path = "/api/create/", method = {HttpMethod.POST})
public class APIKeyCreateRequestRouter implements Handler<RoutingContext> {
    private ApiRequestManager requestManager;
    private UserManager userManager;

    public APIKeyCreateRequestRouter(){
        requestManager = new ApiRequestManager();
        userManager = new UserManager();
    }

    public void handle(RoutingContext ctx)
    {
        HttpServerResponse response = ctx.response();
        if(userManager.isLogined(ctx)){
            try {
                String uid = userManager.getUid(userManager.getIdFromSession(ctx));
                String result = requestManager.createApiKey(uid);
                if(result != null){
                    response.setStatusCode(200);
                    response.setStatusMessage(result);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.setStatusCode(500);
            }
        }else{
            response.setStatusCode(400);
        }
        response.end();
        response.close();
    }

}
