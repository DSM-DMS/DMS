package com.dms.planb.template_routers.qna;

import java.io.IOException;
import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.log.Log;

import com.dms.boxfox.templates.DmsTemplate;
import org.boxfox.dms.utilities.actions.support.PrecedingWork;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/post/answer/write", method = {HttpMethod.GET})
public class QnaAnswerWriteRouter implements Handler<RoutingContext> {
    private UserManager userManager;

    public QnaAnswerWriteRouter() {
        userManager = new UserManager();
    }

    public void handle(RoutingContext context) {
        if (!Guardian.isAdmin(context)) return;
        context = PrecedingWork.putHeaders(context);

        DmsTemplate templates = new DmsTemplate("editor");

        try {
            templates.put("category", "qnaAnswer");
            templates.put("type", "write");

            context.response().setStatusCode(200);
            context.response().end(templates.process());
            context.response().close();
        } catch (IOException e) {
            Log.l("IOException");
        } catch (TemplateException e) {
            Log.l("TemplateException");
        }
    }
}
