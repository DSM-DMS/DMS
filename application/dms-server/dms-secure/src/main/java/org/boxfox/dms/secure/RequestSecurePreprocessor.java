package org.boxfox.dms.secure;

import com.google.common.net.HttpHeaders;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.utilities.log.Log;

/**
 * Created by boxfox on 2017-05-11.
 */
public class RequestSecurePreprocessor {

    public static Handler<RoutingContext> create() {
        return new SecurePreprocessHandler();
    }

    private static class SecurePreprocessHandler implements Handler<RoutingContext> {
        private SecureManager secureManager;

        public SecurePreprocessHandler() {
            secureManager = SecureManager.create(this.getClass());
        }

        @Override
        public void handle(RoutingContext context) {
            context.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Cookie, Origin, X-Requested-With, Content-Type");
            context.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, PUT, PATCH, GET, DELETE, OPTIONS, HEAD, CONNECT");
            context.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://dsm2015.cafe24.com/*");
            context.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://dsm2015.cafe24.com/");
            context.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://dsm2015.cafe24.com");
            context.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            if (secureManager.isBanned(context)) {
                context.response().setStatusCode(400);
                context.response().setStatusMessage("You are banned!");
                context.response().end();
                context.response().close();
                return;
            }

            Log.l("Requested : " + context.request().host());
            Log.l("Method : " + context.request().method());
            Log.l("Absolute URI : " + context.request().absoluteURI());
            Log.l("Data : " + context.request().params());

            context.next();
        }
    }
}
