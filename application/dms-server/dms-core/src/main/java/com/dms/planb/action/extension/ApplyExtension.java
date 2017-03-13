package com.dms.planb.action.extension;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Map;

import org.boxfox.dms.algorithm.SHA256;
import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.PrecedingWork;
import com.dms.planb.support.LimitConfig;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/apply/extension", method = {HttpMethod.PUT})
public class ApplyExtension implements Handler<RoutingContext> {
    private UserManager userManager;

    public ApplyExtension() {
        userManager = new UserManager();
    }

    @Override
    public void handle(RoutingContext context) {
        context = PrecedingWork.putHeaders(context);

        DataBase database = DataBase.getInstance();
        Calendar currentTime = Calendar.getInstance();

        String classId = context.request().getParam("class");
        String seatId = context.request().getParam("seat");
        String id = userManager.getIdFromSession(context);
        String uid = null;
        try {
            if (id != null)
                uid = userManager.getUid(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (Guardian.checkParameters(classId, id, seatId, uid)) {
            try {
                String name = ((Map<String, Object>) userManager.getUserInfo(id).getArgs()[0]).get("name").toString();
                System.out.println(name);
                int hour = currentTime.get(Calendar.HOUR_OF_DAY);
                int minute = currentTime.get(Calendar.MINUTE);

                int setHour = Integer.valueOf(LimitConfig.EXTENSION_APPLY_TIME.split(":")[0]);
                int setMinute = Integer.valueOf(LimitConfig.EXTENSION_APPLY_TIME.split(":")[1]);
                if (hour < setHour || (hour == setHour && minute < setMinute)) {
                    context.response().setStatusCode(404).end();
                    context.response().close();
                } else {
                    database.executeUpdate("DELETE FROM extension_apply WHERE uid='", uid, "'");
                    database.executeUpdate("INSERT INTO extension_apply(class, seat, name, uid) VALUES(", classId, ", ", seatId, ", '", name, "', '", uid, "')");
                    context.response().setStatusCode(200).end();
                    context.response().close();
                }
            } catch (SQLException e) {
                context.response().setStatusCode(500).end();
                context.response().close();

                Log.l("SQLException");
            }
        } else {
            context.response().setStatusMessage("Check parameter or after than login");
            context.response().setStatusCode(400).end();
            context.response().close();
        }
        Log.l("Extension Apply (", id, ", ", context.request().remoteAddress(), ") status : " + context.response().getStatusCode());
    }
}
