package com.dms.templates.recruit;

import java.io.IOException;

import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;

import com.dms.boxfox.templates.DmsTemplate;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by boxfox on 2017-05-29.
 */
@RouteRegistration(path = "/recruit", method = {HttpMethod.GET})
public class RecruitRouter implements Handler<RoutingContext> {
    private UserManager userManager;
    private RecruitManager recruitManager;

    public RecruitRouter(){
        userManager = new UserManager();
        recruitManager = new RecruitManager(userManager);
    }

    public void handle(RoutingContext context) {
        boolean isLogin = userManager.isLogined(context);
        boolean canApply = recruitManager.canApply(context);
        boolean isApply = recruitManager.isApply(context);

        DmsTemplate template = new DmsTemplate("recruit");
        template.put("isLogin", isLogin);
        template.put("isApply", isApply);
        template.put("canApply", canApply);
        try {
            context.response().setStatusCode(200);
            context.response().end(template.process());
            context.response().close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

}
