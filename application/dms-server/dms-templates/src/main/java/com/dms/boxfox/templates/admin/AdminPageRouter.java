//package com.dms.boxfox.templates.admin;
//
//import java.io.IOException;
//
//import org.boxfox.dms.util.UserManager;
//import org.boxfox.dms.utilities.actions.RouteRegistration;
//
//import com.dms.boxfox.templates.DmsTemplate;
//
//import freemarker.template.TemplateException;
//import io.vertx.core.Handler;
//import io.vertx.core.http.HttpMethod;
//import io.vertx.ext.web.RoutingContext;
//
///**
// * Created by boxfox on 2017-03-23.
// */
//@RouteRegistration(path = "/admin/", method = {HttpMethod.GET})
//public class AdminPageRouter implements Handler<RoutingContext> {
//    public void handle(RoutingContext context) {
//        try {
//            if (UserManager.isAdmin(context)) {
//                DmsTemplate template = new DmsTemplate("admin_page");
//                context.response().write(template.process());
//                context.response().setStatusCode(200);
//            } else {
//                context.response().setStatusCode(400);
//                context.response().setStatusMessage("You are not admin");
//            }
//            context.response().end();
//            return;
//        } catch (TemplateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        context.response().setStatusCode(400);
//        context.response().end();
//    }
//}
