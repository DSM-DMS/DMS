package com.dms.templates;

import java.io.IOException;

import com.dms.account_manager.UserManager;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/", method = {HttpMethod.GET})
public class IndexRouter implements Handler<RoutingContext> {
	private UserManager userManager;

    public IndexRouter() {
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
