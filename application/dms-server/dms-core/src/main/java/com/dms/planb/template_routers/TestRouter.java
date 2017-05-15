package com.dms.planb.template_routers;

import com.dms.boxfox.templates.DmsTemplate;
import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.util.AdminManager;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.log.Log;

import java.io.IOException;

@RouteRegistration(path = "/test", method = {HttpMethod.GET})
public class TestRouter implements Handler<RoutingContext> {
    private UserManager userManager;

    public TestRouter() {
        userManager = new UserManager();
    }

    public void handle(RoutingContext context) {
        String htmlName = "nonLogin_new_index";
        if (userManager.isLogined(context)) {
            htmlName = "new_index";
        }
        DmsTemplate templates = new DmsTemplate(htmlName);
        try {
            context.response().setStatusCode(200);
            context.response().end(templates.process());
            context.response().close();
        } catch (TemplateException e) {
            Log.l("TemplateException");
        } catch (IOException e) {
            Log.l("IOException");
        }
    }
}
