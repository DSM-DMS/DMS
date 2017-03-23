package com.dms.boxfox.templates.admin;

import com.dms.boxfox.templates.DmsTemplate;
import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by boxfox on 2017-03-23.
 */
@RouteRegistration(path = "/admin/", method = {HttpMethod.GET})
public class AdminPageRouter implements Handler<RoutingContext> {
    private UserManager userManager;

    public AdminPageRouter() {
        this.userManager = new UserManager();
    }

    public void handle(RoutingContext context) {
        try {
            if (userManager.isAdmin(context)) {
                DmsTemplate template = new DmsTemplate("adminpage");
                context.response().write(template.process());
                context.response().setStatusCode(200);
            } else {
                context.response().setStatusCode(400);
                context.response().setStatusMessage("You are not admin");
            }
            context.response().end();
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        context.response().setStatusCode(400);
        context.response().end();
    }
}
