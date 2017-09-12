package com.dms.templates.qna;

import java.io.IOException;

import com.dms.account_manager.Guardian;
import com.dms.account_manager.UserManager;
import com.dms.templates.DmsTemplate;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/post/answer/write", method = {HttpMethod.GET})
public class QnaAnswerWriteRouter implements Handler<RoutingContext> {
    private UserManager userManager;

    public QnaAnswerWriteRouter() {
        userManager = new UserManager();
    }

    public void handle(RoutingContext ctx) {
        if (!Guardian.isAdmin(ctx)) return;

        DmsTemplate templates = new DmsTemplate("editor");

        try {
            templates.put("category", "qnaAnswer");
            templates.put("type", "write");

            ctx.response().setStatusCode(200);
            ctx.response().end(templates.process());
            ctx.response().close();
        } catch (IOException e) {
            Log.l("IOException");
        } catch (TemplateException e) {
            Log.l("TemplateException");
        }
    }
}
