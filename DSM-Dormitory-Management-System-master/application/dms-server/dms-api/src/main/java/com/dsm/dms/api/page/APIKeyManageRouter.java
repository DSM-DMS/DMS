package com.dsm.dms.api.page;

import com.dms.account_manager.UserManager;
import com.dms.utilities.database.SafeResultSet;
import com.dms.utilities.routing.Route;
import com.dms.utilities.support.JobResult;
import com.dsm.dms.api.secure.ApiRequestManager;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

import java.sql.SQLException;

/**
 * Created by boxfox on 2017-04-05.
 */
@Route(path = "/api/", method = {HttpMethod.POST})
public class APIKeyManageRouter implements Handler<RoutingContext> {
    private ApiRequestManager requestManager;
    private UserManager userManager;

    public APIKeyManageRouter() {
        requestManager = new ApiRequestManager();
        userManager = new UserManager();
    }

    public void handle(RoutingContext ctx) {
        HttpServerResponse response = ctx.response();
        if (userManager.isLogined(ctx)) {
            try {
                String uid = userManager.getUid(userManager.getIdFromSession(ctx));
                JobResult result = requestManager.getApiKeys(uid);
                if (result.isSuccess()) {
                    SafeResultSet rs = (SafeResultSet) result.getArgs()[0];
                    while (rs.next()) {
                        //
                        //
                        //
                    }
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
