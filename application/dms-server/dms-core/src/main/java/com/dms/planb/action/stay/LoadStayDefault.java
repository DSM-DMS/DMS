package com.dms.planb.action.stay;

import java.sql.SQLException;

import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.PrecedingWork;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/apply/stay/default", method = {HttpMethod.GET})
public class LoadStayDefault implements Handler<RoutingContext> {
    private UserManager userManager;

    public LoadStayDefault() {
        userManager = new UserManager();
    }

    @Override
    public void handle(RoutingContext context) {
        context = PrecedingWork.putHeaders(context);

        DataBase database = DataBase.getInstance();
        SafeResultSet resultSet;
        EasyJsonObject responseObject = new EasyJsonObject();

        String id = userManager.getIdFromSession(context);
        String uid = null;
        try {
            if (id != null)
                uid = userManager.getUid(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            resultSet = database.executeQuery("SELECT * FROM stay_apply_default WHERE uid='", uid, "'");

            if (resultSet.next()) {
                responseObject.put("value", resultSet.getInt("value"));

                context.response().setStatusCode(200);
                context.response().end(responseObject.toString());
                context.response().close();
            } else {
                context.response().setStatusCode(204).end();
                context.response().close();
            }
        } catch (SQLException e) {
            context.response().setStatusCode(500).end();
            context.response().close();

            Log.l("SQLException");
        }
    }
}